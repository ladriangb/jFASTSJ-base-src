package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.General;
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
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.client.InsertButton;
import org.openswing.swing.message.receive.java.ErrorResponse;
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
            getVista().inicializar(this, true);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }
        getVista().getMainPanel().setMode(Consts.INSERT);
        if (beanVO != null) {
            getVista().getMainPanel().getVOModel().setValue("personaPago",
                    ((OrdenDePago) beanVO).getPersonaPago());
            getVista().getMainPanel().pull("personaPago");
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        OrdenDePago sin = (OrdenDePago) s.get(OrdenDePago.class, ((OrdenDePago) beanVO).getId());
        Hibernate.initialize(sin.getRemesa());
        Hibernate.initialize(sin.getObservaciones());
        Hibernate.initialize(sin.getDocumentos());
        Hibernate.initialize(sin.getNotasTecnicas());
        s.close();
        getVista().hideAll(sin.getEstatusPago());
        beanVO = sin;
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        return super.insertRecord(newPersistentObject);
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        return new VOResponse(persistentObject);
    }

    @Override
    public Response logicaNegocioDespuesSave(ValueObject persistentObject, Session s) {
        Long seq = null;
        try {
            seq = ((BigInteger) s.createSQLQuery("SELECT nextval('seq_ordenpago');").uniqueResult()).longValue();
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "logicaNegocioDespuesSave", ex));
        }
        Calendar c = Calendar.getInstance();
        DecimalFormat nf = new DecimalFormat("00000");
        SimpleDateFormat df = new SimpleDateFormat("yyMM");
        OrdenDePago ordenDePago = (OrdenDePago) persistentObject;

        ordenDePago.setNumeroOrden(df.format(c.getTime()) + "-" + nf.format(seq));
        if (ordenDePago.getCodigoSIGECOF() != null && ordenDePago.getCodigoSIGECOF().trim().isEmpty()) {
            ordenDePago.setCodigoSIGECOF(null);
        }

        EtapaSiniestro etS = null;
        etS = (EtapaSiniestro) s.createQuery("FROM "
                + EtapaSiniestro.class.getName() + " C WHERE "
                + "idPropio=?").setString(0, "ORD_PAG").uniqueResult();


        if (ordenDePago.getAutoSearch()) {
            try {
                List l = s.createQuery("SELECT C.id FROM "
                        + ordenDePago.getTipoDetalleSiniestro().getClase() + " C WHERE "
                        + "C.personaPago.id=? AND C.etapaSiniestro.idPropio=?").
                        setLong(0, ordenDePago.getPersonaPago().getId()).
                        setString(1, "LIQ").list();

                s.createQuery("UPDATE " + DetalleSiniestro.class.getName()
                        + " SET etapaSiniestro=:es, ordenDePago=:re WHERE"
                        + " id in (:op)").setEntity("es", etS).
                        setEntity("re", ordenDePago).setParameterList("op", l).executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new VOResponse(ordenDePago);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof InsertButton) {
            OrdenDePago ordenDePago = (OrdenDePago) beanVO;
            new BuscarDetallesGridFrameController(this, ordenDePago);
        } else if (((JButton) e.getSource()).getText().equalsIgnoreCase("Liquidar")) {
            new LiquidarPagoDetailFrameController(vista.getMainPanel());
        } else {
            int res = JOptionPane.showConfirmDialog(vista, "Esta a punto de Anular la \"Orden de Pago\"\nEsta acción no se puede revertir\n¿Desea Continuar? ",
                    General.empresa.getNombre(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (res == JOptionPane.YES_OPTION) {
                anular();
            }
        }
    }

    /**
     * Establese la Orden de Pago como Nula
     */
    private void anular() {
        Session s = null;
        OrdenDePago pago = (OrdenDePago) vista.getMainPanel().getVOModel().getValueObject();
        pago.setEstatusPago(EstatusPago.ANULADO);
        pago.getAuditoria().setActivo(false);
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            EtapaSiniestro etS = null;
            if (pago.getEstatusPago() == EstatusPago.ANULADO) {
                etS = (EtapaSiniestro) s.createQuery("FROM "
                        + EtapaSiniestro.class.getName() + " C WHERE "
                        + "idPropio=?").setString(0, "LIQ").uniqueResult();
            }

            s.createQuery("UPDATE " + DetalleSiniestro.class.getName()
                    + " D SET D.etapaSiniestro=:es, D.ordenDePago=null WHERE D.ordenDePago.id=:ds").
                    setEntity("es", etS).
                    setLong("ds", pago.getId()).executeUpdate();

            s.update(pago);
            s.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            s.close();
            vista.getMainPanel().getReloadButton().doClick();
        }
    }

    /**
     * la vista controlada
     * @return the vista
     */
    public OrdenDePagoDetailFrame getVista() {
        return (OrdenDePagoDetailFrame) vista;
    }
}
