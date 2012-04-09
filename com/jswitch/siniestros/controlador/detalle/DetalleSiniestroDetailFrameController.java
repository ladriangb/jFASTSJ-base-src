package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.configuracion.modelo.dominio.Ramo;
import com.jswitch.configuracion.modelo.maestra.Plan;
import com.jswitch.configuracion.modelo.transaccional.RangoValor;
import com.jswitch.pagos.controlador.FacturaDetailFrameController;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.pagos.vista.FacturaDetailFrame;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.siniestros.controlador.DiagnosticoPorRamoGridFrameController;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.APS;
import com.jswitch.siniestros.modelo.maestra.detalle.AyudaSocial;
import com.jswitch.siniestros.modelo.maestra.detalle.CartaAval;
import com.jswitch.siniestros.modelo.maestra.detalle.Emergencia;
import com.jswitch.siniestros.modelo.maestra.detalle.Funerario;
import com.jswitch.siniestros.modelo.maestra.detalle.Reembolso;
import com.jswitch.siniestros.modelo.maestra.detalle.Vida;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.transform.AliasedTupleSRT;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientSettings;
import org.openswing.swing.util.java.Consts;

public class DetalleSiniestroDetailFrameController extends DefaultDetailFrameController {

    private HashMap<Class, String> etapaInicial;
    private Siniestro siniestro;

    public DetalleSiniestroDetailFrameController(String detailFramePath,
            GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio, beanVO.getClass());
    }

    public DetalleSiniestroDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Class tipoDetalle) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio, tipoDetalle, null);
    }

    public DetalleSiniestroDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Class tipoDetalle, Siniestro siniestro) {
        this.siniestro = siniestro;
        if (siniestro == null && beanVO != null) {
            this.siniestro = ((DetalleSiniestro) beanVO).getSiniestro();
        }
        if (tipoDetalle.equals(Vida.class) && !checkRamo("VIDA")) {
            JOptionPane.showMessageDialog(gridControl, ClientSettings.getInstance().getResources().getResource("noRamo.vida"), General.edition, JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (tipoDetalle.equals(Funerario.class) && !checkRamo("FUNE")) {
            JOptionPane.showMessageDialog(gridControl,
                    ClientSettings.getInstance().getResources().getResource("noRamo.funerario"),
                    General.edition, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.aplicarLogicaNegocio = aplicarLogicaNegocio;
        vista = new DetalleSiniestroDetailFrame();
        ((DetalleSiniestroDetailFrame) vista).setTipo(tipoDetalle, beanVO);

        vista.inicializar(this, true);
        if (beanVO != null) {
            vista.getMainPanel().reload();
            vista.getMainPanel().setMode(Consts.READONLY);
        } else {
            vista.getMainPanel().setMode(Consts.INSERT);
        }
        etapaInicial = new HashMap<Class, String>(0);
        etapaInicial.put(APS.class, "CARTA");
        etapaInicial.put(AyudaSocial.class, "CARTA");
        etapaInicial.put(CartaAval.class, "CARTA");
        etapaInicial.put(Emergencia.class, "CARTA");
        etapaInicial.put(Funerario.class, "CARTA");
        etapaInicial.put(Reembolso.class, "CARTA");
        etapaInicial.put(Vida.class, "VIDA");

    }

    public DetalleSiniestroDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Siniestro siniestro, Class tipoDetalle) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio, tipoDetalle, siniestro);
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        DetalleSiniestro sin = (DetalleSiniestro) s.get(DetalleSiniestro.class, ((DetalleSiniestro) beanVO).getId());
        Hibernate.initialize(sin.getNotasTecnicas());
        Hibernate.initialize(sin.getObservaciones());
//        Hibernate.initialize(sin.getPagos());
//        Hibernate.initialize(sin.getDiagnosticoSiniestros());
        Hibernate.initialize(sin.getDocumentos());
        Hibernate.initialize(sin.getSumaDesgloseCoberturas());
        s.close();
        beanVO = sin;
        checkStatus();
        siniestro = sin.getSiniestro();
        return new VOResponse(beanVO);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((DetalleSiniestroDetailFrame) vista).getInsertButtonPagos().equals(e.getSource())) {
            new FacturaDetailFrameController(FacturaDetailFrame.class.getName(), ((DetalleSiniestroDetailFrame) vista).getGridPagos(),
                    (DetalleSiniestro) beanVO, true, vista.getMainPanel().getReloadButton());
        } else if (((DetalleSiniestroDetailFrame) vista).getInsertButtonDiagnostico().equals(e.getSource())) {
            new DiagnosticoPorRamoGridFrameController((DetalleSiniestroDetailFrame) vista, (DetalleSiniestro) beanVO);
        }
    }

    @Override
    public Response updateRecord(ValueObject antes, ValueObject ahora) throws Exception {
        TipoPersona tp = ((DetalleSiniestroDetailFrame) vista).getTipoPersonaSelected();
        if (tp != null) {
            ((DetalleSiniestro) ahora).setTipoPersona(tp);
        }
        if (((DetalleSiniestro) antes).getEtapaSiniestro() != null) {
            ((DetalleSiniestro) ahora).setSelected(
                    ((DetalleSiniestro) ahora).getEtapaSiniestro().getId().compareTo(
                    ((DetalleSiniestro) antes).getEtapaSiniestro().getId()) != 0);
        }
        return super.updateRecord(antes, ahora);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        String className = newPersistentObject.getClass().getName();
        className = className.substring(1 + className.lastIndexOf("."));
        ((DetalleSiniestro) newPersistentObject).setTipoDetalle(className);
        DecimalFormat nf = new DecimalFormat("00");
        Session s = null;
        try {
            vista.saveGridsData();
            s = HibernateUtil.getSessionFactory().openSession();
            Long l = (Long) s.createQuery("SELECT COUNT(P) FROM " + DetalleSiniestro.class.getName()
                    + " P WHERE P.siniestro.id = ?").setLong(0, siniestro.getId()).uniqueResult();
            ((DetalleSiniestro) newPersistentObject).setNumero(nf.format(l + 1));
            Query q = s.createQuery("FROM " + EtapaSiniestro.class.getName() + " C"
                    + " WHERE C.idPropio='" + etapaInicial.get(newPersistentObject.getClass()) + "'");

            EtapaSiniestro et = (EtapaSiniestro) q.uniqueResult();
            ((DetalleSiniestro) newPersistentObject).setEtapaSiniestro(et);
            ((DetalleSiniestro) newPersistentObject).setTipoContrato(
                    siniestro.getCertificado().getTitular().getTipoContrato());
            String idPropio = (newPersistentObject instanceof Vida) ? "VIDA"
                    : (newPersistentObject instanceof Funerario) ? "FUNE" : "HCM";
            q = s.createQuery("FROM " + Ramo.class.getName() + " C"
                    + " WHERE C.idPropio='" + idPropio + "'");
            Ramo ramo = (Ramo) q.uniqueResult();
            ((DetalleSiniestro) newPersistentObject).setRamo(ramo);

            if (newPersistentObject instanceof Emergencia) {
                ((Emergencia) newPersistentObject).setClave(siniestro.getNumero()
                        + "-" + ((DetalleSiniestro) newPersistentObject).getNumero());
            }

            if (newPersistentObject instanceof Reembolso) {
                q = s.createQuery("FROM " + TipoPersona.class.getName() + " C"
                        + " WHERE C.idPropio='TIT'");
                TipoPersona tp = (TipoPersona) q.uniqueResult();
                ((DetalleSiniestro) newPersistentObject).setTipoPersona(tp);
                ((DetalleSiniestro) newPersistentObject).setPersonaPago(siniestro.getCertificado().getTitular().getPersona());

            } else {
                TipoPersona tp = ((DetalleSiniestroDetailFrame) vista).getTipoPersonaSelected();
                if (tp != null) {
                    ((DetalleSiniestro) newPersistentObject).setTipoPersona(tp);
                }
            }

            ((DetalleSiniestro) newPersistentObject).setSiniestro(siniestro);
//            siniestro.getDetalleSiniestro().add((DetalleSiniestro) newPersistentObject);
            return super.insertRecord((newPersistentObject));
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
        } finally {
            s.close();
        }
    }

    /**
     * mira si el ramo esta para ese asegurado
     * @param nombreRamo
     * @return 
     */
    private boolean checkRamo(String nombreRamo) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("SELECT 1 AS SW FROM " + Plan.class.getName() + " P "
                    + " JOIN P.sumasAseguradas S "
                    + " WHERE S.diagnostico.especialidad.ramo.idPropio='" + nombreRamo + "'"
                    + " AND P.id=?");
            List l = q.setLong(0, siniestro.getAsegurado().getPlan().getId()).list();
            return (l != null && l.size() > 0)
                    ? true : false;
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "checkRamo", ex);
            return false;
        } finally {
            s.close();
        }
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        DetalleSiniestro d = (DetalleSiniestro) persistentObject;
        Session s = null;
        EtapaSiniestro es = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            es = (EtapaSiniestro) s.createQuery("FROM "
                    + EtapaSiniestro.class.getName() + " C WHERE "
                    + "idPropio=?").setString(0, "LIQ").uniqueResult();

            if (d.getEtapaSiniestro().getId().compareTo(
                    es.getId()) == 0) {

                if (d.getSumaDetalle().getTotalLiquidado() <= 0d) {
                    return new ErrorResponse("No se puede Liquidar\nNo hay monto Liquidado");
                }
                d.setFechaLiquidado(new Date());
                if (d.getFechaVencimiento() != null
                        && d.getFechaVencimiento().before(d.getFechaLiquidado())) {
                    int i = JOptionPane.showConfirmDialog(vista, "Esta a punto de Liquidar un Siniestro Vencido\n¿desea continuar?", "", JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);
                    if (i != JOptionPane.YES_OPTION) {
                        return new ErrorResponse("Operacion Cancelada por el Usuario");
                    }
                }

            }

            if (!General.empresa.getLiquidarEnOrden()) {
                if (!(persistentObject instanceof Reembolso)) {
                    Response res =
                            liquidar(
                            (DetalleSiniestro) persistentObject, s);
                    if (res instanceof ErrorResponse) {
                        return res;
                    } else {
                        persistentObject =
                                (ValueObject) ((VOResponse) res).getVo();
                    }

                } else {
                    liquidarDetalle((DetalleSiniestro) persistentObject, s, 0d);
                }
            }
            if (!General.empresa.getSustraendoEnOrden()) {
                sustraendoACancelar((DetalleSiniestro) persistentObject, s);
            }

        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "logicade negocios", e);
            return new ErrorResponse(e.getMessage());
        } finally {
            s.close();
        }
        if (d instanceof Reembolso) {
            Reembolso r = (Reembolso) d;
            if (r.getFechaOcurrencia().getTime() > r.getFechaNotificacion().getTime()) {
                return new ErrorResponse("El siniestro no puede ser  antes de su ocurrencia.\nInconsistencia en fecha de notificación y ocurrencia.");
            }
        }
        if (d instanceof Emergencia) {
            Emergencia r = (Emergencia) d;
            if (r.getFechaEntrada().getTime() > r.getFechaSalida().getTime()) {
                return new ErrorResponse("Inconsistencia en fecha de Entrada y Salida.");
            }
        }

        return new VOResponse(d);
    }

    /**
     * estatus y si permite o no edicion
     */
    private void checkStatus() {
        DetalleSiniestro ds = ((DetalleSiniestro) beanVO);
        if (ds.getEtapaSiniestro().getIdPropio().compareTo("ORD_PAG") == 0
                || ds.getEtapaSiniestro().getEstatusSiniestro().getNombre().
                compareTo("PENDIENTE") != 0) {
            ((DetalleSiniestroDetailFrame) vista).hideAll();
        }
        if (ds.getEtapaSiniestro().getIdPropio().compareTo("LIQ") == 0
                && !General.usuario.getSuperusuario()) {
            ((DetalleSiniestroDetailFrame) vista).hideAll();
        }
    }

    /**
     * Liquida el siniestro
     * @param detalle de Siniestro
     * @param s Session 
     * @return 
     */
    private Response liquidar(DetalleSiniestro detalle, Session s) {
        String zipCode = "";
        if (detalle.getPersonaPago() != null && detalle.getPersonaPago().getDireccionFiscal() != null) {
            zipCode = detalle.getPersonaPago().getDireccionFiscal().getZonaPostal();
        }
        if (zipCode == null) {
            zipCode = "";
        }
        if (zipCode.trim().isEmpty()) {
            Double tm = timbreACancelar(detalle, zipCode, s);
            liquidarDetalle(detalle, s, tm);
        }
        return new VOResponse(detalle);
    }

    /**
     * Paga una serie de facturas especificas de reembolsos donde 
     * @param tm Timbre municipal 
     * @param detalle Detalle Siniestro
     * @param s session
     */
    private void liquidarDetalle(DetalleSiniestro detalle, Session s, Double tm) {
        s.beginTransaction();
        s.createQuery("UPDATE " + Factura.class.getName()
                + " f SET f.porcentajeRetencionTM=:tm,"
                + " f.valorUT=:ut"
                + " WHERE id in (SELECT P.id FROM "
                + Factura.class.getName()
                + " P WHERE P.detalleSiniestro.id=:det"
                + ")").
                setDouble("tm", tm).
                setDouble("ut", General.parametros.get("ut").getValorDouble()).
                setLong("det", detalle.getId()).
                executeUpdate();
        s.getTransaction().commit();
    }

    /**
     * calcular el timbre municipal a cancelar por DetalleSiniestro
     * @param detalle
     * @param pagar
     * @return timbre municipal a cancelar 
     */
    private Double timbreACancelar(DetalleSiniestro detalle, String zipCode, Session s) {
        int totalUT = (int) (detalle.getSumaDetalle().getTotalACancelar()
                / General.parametros.get("ut").getValorDouble());
        List<RangoValor> list = s.createQuery("FROM "
                + RangoValor.class.getName() + " R WHERE R.timbreMunicipal.zipCode=?").
                setString(0, zipCode).list();
        if (list.isEmpty()) {
            if (General.parametros.get("tm") != null
                    && General.parametros.get("tm").getValorDouble() != null
                    && General.parametros.get("minTM") != null
                    && General.parametros.get("minTM").getValorInteger() != null) {
                if (totalUT >= General.parametros.get("minTM").getValorInteger()) {
                    return General.parametros.get("tm").getValorDouble();
                }
            }
        } else {
            for (RangoValor rangoValor : list) {
                if (totalUT >= rangoValor.getMinValue()
                        && totalUT <= rangoValor.getMaxValue()) {
                    return rangoValor.getMonto();
                }
            }
        }
        return 0d;
    }

    /**
     * calcular el sustraendo a cancelar por DetalleSiniestro
     * @param detalle DetalleSiniestro
     * @param s Session
     */
    private void sustraendoACancelar(DetalleSiniestro detalle, Session s) {
        int aPartirDe = (int) (General.parametros.get("ut").getValorDouble()
                * General.parametros.get("factorISLR").getValorDouble());
        Double sustraendo = 0d;
        String sql = "SELECT SUM(C.baseIslr) FROM " + Factura.class.getName()
                + " C WHERE C.detalleSiniestro.id=? and C.tipoConceptoSeniat.codigo=?";

        Double baseIslr = (Double) s.createQuery(sql).
                setLong(0, detalle.getId()).
                setString(1, "004").uniqueResult();
        if (baseIslr == null) {
            baseIslr = 0d;
        }
        if (baseIslr >= aPartirDe) {
            String update = "UPDATE "
                    + Factura.class.getName()
                    + " SET sustraendo="
                    + " CAST(baseIslr/:base*:base2*porcentajeRetencionIslr AS big_decimal),"
                    + " montoRetencionIslr="
                    + " (baseIslr*porcentajeRetencionIslr)"
                    + " -CAST(baseIslr/:base*:base2*porcentajeRetencionIslr AS big_decimal)"
                    + " WHERE  id IN (SELECT C.id FROM " + Factura.class.getName()
                    + " C WHERE C.detalleSiniestro.id=:det and C.tipoConceptoSeniat.codigo=:co)";
            s.beginTransaction();
            s.createQuery(update).
                    setDouble("base", baseIslr).
                    setDouble("base2", aPartirDe).
                    setLong("det", detalle.getId()).
                    setString("co", "004").
                    executeUpdate();
            sql = "SELECT SUM(C.sustraendo),porcentajeRetencionIslr FROM " + Factura.class.getName()
                    + " C WHERE C.detalleSiniestro.id=? and C.tipoConceptoSeniat.codigo=? GROUP BY"
                    + " porcentajeRetencionIslr";
            Object[] su = (Object[]) s.createQuery(sql).
                    setLong(0, detalle.getId()).
                    setString(1, "004").uniqueResult();
            if (su != null) {
                sustraendo = (Double) su[0] - ((Double) su[1] * aPartirDe);
                if (sustraendo != 0) {
                    sql = "(SELECT C.id FROM " + Factura.class.getName()
                            + " C WHERE C.detalleSiniestro.id=:det and C.tipoConceptoSeniat.codigo=:co )";
                    Integer id = (Integer) s.createQuery(sql).
                            setLong(0, detalle.getId()).
                            setString(1, "004").setMaxResults(1).uniqueResult();
                    update = "UPDATE "
                            + Factura.class.getName()
                            + " SET sustraendo="
                            + " (sustraendo+:su),"
                            + " montoRetencionIslr=(sustraendo+:su)"
                            + " WHERE  id = :id";
                    s.createQuery(update).
                            setLong("id", id).
                            setDouble("su", sustraendo).
                            executeUpdate();
                }
            }

            s.getTransaction().commit();
        }
//        return sustraendo;
    }
}
