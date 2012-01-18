package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.configuracion.modelo.dominio.Ramo;
import com.jswitch.configuracion.modelo.maestra.Plan;
import com.jswitch.siniestros.controlador.DiagnosticoSiniestroDetailFrameController;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;
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
            JOptionPane.showMessageDialog(gridControl, ClientSettings.getInstance().getResources().getResource("noRamo.funerario"), General.edition, JOptionPane.INFORMATION_MESSAGE);
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
            checkStatus();
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
        etapaInicial.put(Vida.class, "CARTA");

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
        Hibernate.initialize(sin.getPagos());
        Hibernate.initialize(sin.getDiagnosticoSiniestros());
        Hibernate.initialize(sin.getDocumentos());
        s.close();
        beanVO = sin;
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
        Session s = null;
        try {
            vista.saveGridsData();
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("FROM " + EtapaSiniestro.class.getName() + " C"
                    + " WHERE C.idPropio='" + etapaInicial.get(newPersistentObject.getClass()) + "'");

            EtapaSiniestro et = (EtapaSiniestro) q.uniqueResult();
            ((DetalleSiniestro) newPersistentObject).setEtapaSiniestro(et);

            String idPropio = (newPersistentObject instanceof Vida) ? "VIDA"
                    : (newPersistentObject instanceof Funerario) ? "FUNE" : "HCM";
            q = s.createQuery("FROM " + Ramo.class.getName() + " C"
                    + " WHERE C.idPropio='" + idPropio + "'");
            Ramo ramo = (Ramo) q.uniqueResult();
            ((DetalleSiniestro) newPersistentObject).setRamo(ramo);

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
            siniestro.getDetalleSiniestro().add((DetalleSiniestro) newPersistentObject);
            return super.insertRecord((newPersistentObject));
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
        } finally {
            s.close();
        }
    }

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
                Double l = 0d, f = 0d, c = 0d;
                for (Factura factura : d.getPagos()) {
                    l += factura.getTotalLiquidado();
                    f += factura.getTotalFacturado();
                    c += factura.getTotalACancelar();
                }
                d.setMontoACancelar(c);
                d.setMontoFacturado(f);
                d.setMontoLiquidado(l);
                d.setFechaLiquidado(new Date());
            }
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "logicade negocios", e);
            return new ErrorResponse(e.getMessage());
        } finally {
            s.close();
        }
        return new VOResponse(d);
    }

    private void checkStatus() {
        DetalleSiniestro ds = ((DetalleSiniestro) beanVO);
        if (ds.getEtapaSiniestro().getIdPropio().compareTo("ORD_PAG") == 0
                || ds.getEtapaSiniestro().getIdPropio().compareTo("LIQ") == 0
                || ds.getEtapaSiniestro().getEstatusSiniestro().getNombre().
                compareTo("PENDIENTE") != 0) {
            ((DetalleSiniestroDetailFrame) vista).getEditButton1().setVisible(false);
            ((DetalleSiniestroDetailFrame) vista).getSaveButton1().setVisible(false);
            ((DetalleSiniestroDetailFrame) vista).getjPanel11().setVisible(false);
            ((DetalleSiniestroDetailFrame) vista).getjPanel13().setVisible(false);
            ((DetalleSiniestroDetailFrame) vista).getjPanel15().setVisible(false);
            ((DetalleSiniestroDetailFrame) vista).getjPanel16().setVisible(false);
            ((DetalleSiniestroDetailFrame) vista).getjPanel23().setVisible(false);
        }
    }
}
