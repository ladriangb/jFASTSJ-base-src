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
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
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
                        + "C.personaPago.id=? AND C.etapaSiniestro.idPropio=?").
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
        Response res = super.insertRecord(newPersistentObject);
        calcularMontos(p);
        return res;
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
        SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
        OrdenDePago ordenPago = (OrdenDePago) persistentObject;
        ordenPago.setNumeroOrden(df.format(c.getTime()) + "-" + nf.format(seq));
        return new VOResponse(ordenPago);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        OrdenDePago ordenDePago = (OrdenDePago) beanVO;
        new BuscarDetallesGridFrameController(this, ordenDePago);
    }

    /**
     * Calcula los montos de todos los Detalles Siniestros Internos
     */
    public void calcularMontos(OrdenDePago ordenDePago) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            Double l = 0d, r = 0d, f = 0d, c = 0d;
            Double baseIva = 0d, iva = 0d, rIva = 0d, baseIslr = 0d, rIslr = 0d;
            Double gC = 0d, hM = 0d, nA = 0d, am = 0d, de = 0d, tm = 0d, dPP = 0d;
            Double mTi = 0d, mFa = 0d;
            Integer tit = 0, fam = 0, det = 0, fac = 0;
            for (DetalleSiniestro detalleSin : ordenDePago.getDetalleSiniestros()) {
                if (detalleSin.getAuditoria().getActivo().booleanValue()) {
                    det++;
                    if (detalleSin.getSiniestro().getCertificado().getTitular().
                            getPersona().getId().compareTo(
                            detalleSin.getSiniestro().getAsegurado().
                            getPersona().getId()) == 0) {
                        mTi += detalleSin.getMontoACancelar();
                        tit++;
                    } else {
                        mFa += detalleSin.getMontoACancelar();
                        fam++;
                    }
                    fac += detalleSin.getCantidadFacturas();
                    baseIva += detalleSin.getMontoBaseIva();
                    iva += detalleSin.getMontoIva();
                    rIva += detalleSin.getMontoRetenidoIva();
                    baseIslr += detalleSin.getMontoBaseIslr();
                    rIslr += detalleSin.getMontoRetenidoIslr();
                    gC += detalleSin.getMontoGastosClinicos();
                    hM += detalleSin.getMontoHonorariosMedicos();
                    am += detalleSin.getMontoAmparado();
                    de += detalleSin.getMontoDeducible();
                    dPP += detalleSin.getMontoProntoPago();
                    nA += detalleSin.getMontoNoAmparado();
                    tm += detalleSin.getMontoTM();
                    r += detalleSin.getMontoRetenido();
                    l += detalleSin.getMontoLiquidado();
                    f += detalleSin.getMontoFacturado();
                    c += detalleSin.getMontoACancelar();
                }
            }
            ordenDePago.setCantidadDetalles(det);
            ordenDePago.setCantidadFacturas(fac);
            ordenDePago.setNumeroSiniestrosTitular(tit);
            ordenDePago.setNumeroSiniestrosFamiliar(fam);
            ordenDePago.setMontoTitulares(mTi);
            ordenDePago.setMontoFamiliar(mFa);
            ordenDePago.setMontoIva(iva);
            ordenDePago.setMontoBaseIva(baseIva);
            ordenDePago.setMontoRetenidoIva(rIva);
            ordenDePago.setMontoBaseIslr(baseIslr);
            ordenDePago.setMontoRetenidoIslr(rIslr);
            ordenDePago.setMontoGastosClinicos(gC);
            ordenDePago.setMontoHonorariosMedicos(hM);
            ordenDePago.setMontoAmparado(am);
            ordenDePago.setMontoDeducible(de);
            ordenDePago.setMontoProntoPago(dPP);
            ordenDePago.setMontoNoAmparado(nA);
            ordenDePago.setMontoTM(tm);
            ordenDePago.setMontoRetenido(r);
            ordenDePago.setMontoACancelar(c);
            ordenDePago.setMontoFacturado(f);
            ordenDePago.setMontoLiquidado(l);
            s.update(ordenDePago);
            s.getTransaction().commit();
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "calcularMontos", e);
        } finally {
            s.close();
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
