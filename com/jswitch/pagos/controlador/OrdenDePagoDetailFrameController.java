package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.vista.OrdenDePagoDetailFrame;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.awt.event.ActionEvent;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 * Genera y mantiene la orden de pago 
 * @author Adrian
 */
public class OrdenDePagoDetailFrameController
        extends DefaultDetailFrameController {

    /**
     * crea la instancia del objeto de 
     * <code>OrdenDePagoDetailFrameController</code>
     */
    public OrdenDePagoDetailFrameController() {
    }

    /**
     * crea la instancia del objeto de 
     * <code>OrdenDePagoDetailFrameController</code>
     * @param detailFramePath
     * @param gridControl
     * @param beanVO
     * @param aplicarLogicaNegocio 
     */
    public OrdenDePagoDetailFrameController(String detailFramePath,
            GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    /**
     * inicializa los valores del BeanVO del OrdenDePago
     * @param gridControl
     * @param beanVO
     * @param aplicarLogicaNegocio 
     */
    public void init(GridControl gridControl, BeanVO beanVO,
            Boolean aplicarLogicaNegocio) {
        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.aplicarLogicaNegocio = aplicarLogicaNegocio;
        try {
            vista = new OrdenDePagoDetailFrame();
            vista.inicializar(this, true);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }
        vista.getMainPanel().setMode(Consts.INSERT);
        if (beanVO != null) {
            vista.getMainPanel().getVOModel().setValue("personaPago",
                    ((OrdenDePago) beanVO).getPersonaPago());
            vista.getMainPanel().pull("personaPago");
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        OrdenDePago sin = (OrdenDePago) s.get(OrdenDePago.class, ((OrdenDePago) beanVO).getId());
        Hibernate.initialize(sin.getDetalleSiniestros());
        Hibernate.initialize(sin.getObservaciones());
        Hibernate.initialize(sin.getDocumentos());
        Hibernate.initialize(sin.getNotasTecnicas());
        s.close();
        beanVO = sin;
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        OrdenDePago p = (OrdenDePago) newPersistentObject;
        p.setEstatusPago(EstatusPago.PENDIENTE);
        if (p.getAutoSearch()) {
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                List l = s.createQuery("FROM "
                        + p.getTipoDetalleSiniestro().getClase() + " C WHERE "
                        + "C.personaPago.id=? AND etapaSiniestro.idPropio=?").
                        setLong(0, p.getPersonaPago().getId()).
                        setString(1, "LIQ").list();
                for (Object detalleSiniestro : l) {
                    p.getDetalleSiniestros().add(
                            (DetalleSiniestro) detalleSiniestro);
                }
            } finally {
                s.close();
            }
        }
        return super.insertRecord(newPersistentObject);
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        Session s = null;
        OrdenDePago pago = (OrdenDePago) persistentObject;
        if (pago.getCodigoSIGECOF() != null && pago.getCodigoSIGECOF().trim().isEmpty()) {
            pago.setCodigoSIGECOF(null);
        }
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            EtapaSiniestro etS = null;
            if (pago.getEstatusPago() == EstatusPago.ANULADO) {
                etS = (EtapaSiniestro) s.createQuery("FROM "
                        + EtapaSiniestro.class.getName() + " C WHERE "
                        + "idPropio=?").setString(0, "LIQ").uniqueResult();
            } else if (pago.getEstatusPago() == EstatusPago.PENDIENTE
                    || pago.getEstatusPago() == EstatusPago.SELECCIONADO) {
                etS = (EtapaSiniestro) s.createQuery("FROM "
                        + EtapaSiniestro.class.getName() + " C WHERE "
                        + "idPropio=?").setString(0, "ORD_PAG").uniqueResult();
            } else if (pago.getEstatusPago() == EstatusPago.PAGADO) {
                etS = (EtapaSiniestro) s.createQuery("FROM "
                        + EtapaSiniestro.class.getName() + " C WHERE "
                        + "idPropio=?").setString(0, "PAG").uniqueResult();
            }
            for (DetalleSiniestro detalleSiniestro : pago.getDetalleSiniestros()) {
                detalleSiniestro.setEtapaSiniestro(etS);
                s.update(detalleSiniestro);
            }
            s.getTransaction().commit();
        } finally {
            s.close();
        }
        if (pago.getCodigoSIGECOF() != null) {
            pago.setMontoPagar(0d);
            for (DetalleSiniestro detalleSiniestro : pago.getDetalleSiniestros()) {
                pago.setMontoPagar(pago.getMontoPagar() + detalleSiniestro.getMontoACancelar());
            }
        }


        return new VOResponse(persistentObject);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        OrdenDePago op = (OrdenDePago) beanVO;
        new BuscarDetallesGridFrameController(op.getPersonaPago(), op);
    }
}
