package com.jswitch.siniestros.controlador;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.base.vista.util.SuperusuarioLoginDialog;
import com.jswitch.configuracion.controlador.TratamientoLookupController;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import com.jswitch.configuracion.modelo.dominio.patologias.Tratamiento;
import com.jswitch.configuracion.modelo.transaccional.ConfiguracionSiniestro;
import com.jswitch.configuracion.modelo.transaccional.SumaAsegurada;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
import com.jswitch.siniestros.vista.DiagnosticoSiniestroDetailFrame;
import com.jswitch.vistasbd.Agotamiento;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Set;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.lookup.client.LookupParent;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author Adrian
 */
public class DiagnosticoSiniestroDetailFrameController extends DefaultDetailFrameController {

    private DetalleSiniestro detalleSin;
    private DefaultDetailFrame frame;

    public DiagnosticoSiniestroDetailFrameController(String detailFramePath, GridControl gridControl,
            BeanVO beanVO, Boolean aplicarLogicaNegocio, DefaultDetailFrame frame) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        this.detalleSin = ((DiagnosticoSiniestro) beanVO).getDetalleSiniestro();
        this.frame = frame;
        ((DiagnosticoSiniestroDetailFrame) vista).setDetalleSiniestro(detalleSin);
        checkStatus();
    }

    public DiagnosticoSiniestroDetailFrameController(GridControl migrid, boolean b, DetalleSiniestro detalleSin, DefaultDetailFrame frame, Diagnostico diagnostico) {
        this.gridControl = migrid;
        this.beanVO = null;
        this.aplicarLogicaNegocio = b;
        this.detalleSin = detalleSin;
        try {
            Class<DefaultDetailFrame> t = (Class<com.jswitch.base.vista.util.DefaultDetailFrame>) Class.forName(DiagnosticoSiniestroDetailFrame.class.getName());
            vista = t.newInstance();
            vista.inicializar(this, true);

        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }
        if (beanVO != null) {
            vista.getMainPanel().reload();
            vista.getMainPanel().setMode(Consts.READONLY);
        } else {
            vista.getMainPanel().setMode(Consts.INSERT);
            vista.getMainPanel().getVOModel().setValue("diagnostico", diagnostico);
            vista.getMainPanel().pull("diagnostico");
            AgotamientoActual agotamiento = getAgotamiento(diagnostico,
                    detalleSin.getSiniestro().getAsegurado());


            Double d = (getDisponiblePorTipoDetalle(detalleSin));
            vista.getMainPanel().getVOModel().setValue("disponiblePorTipoDetalle", d);
            vista.getMainPanel().pull("disponiblePorTipoDetalle");


            vista.getMainPanel().getVOModel().setValue("totalUtilizado", agotamiento.agotamiento.getMontoPagado());
            vista.getMainPanel().pull("totalUtilizado");

            vista.getMainPanel().getVOModel().setValue("totalReservado", agotamiento.agotamiento.getMontoPendiente());
            vista.getMainPanel().pull("totalReservado");

            vista.getMainPanel().getVOModel().setValue("totalDisponible",
                    agotamiento.montoAmparado - (agotamiento.agotamiento.getMontoPagado()
                    + agotamiento.agotamiento.getMontoPendiente()));
            vista.getMainPanel().pull("totalDisponible");
        }
        this.frame = frame;
        ((DiagnosticoSiniestroDetailFrame) vista).setDetalleSiniestro(detalleSin);
        checkStatus();
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        DiagnosticoSiniestro sin = (DiagnosticoSiniestro) s.get(DiagnosticoSiniestro.class, ((DiagnosticoSiniestro) beanVO).getId());
        Hibernate.initialize(sin.getTratamientos());
        s.close();
        detalleSin.getDiagnosticoSiniestros().remove((DiagnosticoSiniestro) beanVO);
        detalleSin.getDiagnosticoSiniestros().add(sin);
        AgotamientoActual agotamiento = getAgotamiento(sin.getDiagnostico(),
                detalleSin.getSiniestro().getAsegurado());
        sin.setDisponiblePorTipoDetalle(getDisponiblePorTipoDetalle(detalleSin));
        sin.setTotalUtilizado(agotamiento.agotamiento.getMontoPagado());
        sin.setTotalReservado(agotamiento.agotamiento.getMontoPendiente());
        sin.setTotalDisponible(
                agotamiento.montoAmparado - (agotamiento.agotamiento.getMontoPagado()
                + agotamiento.agotamiento.getMontoPendiente()));
        beanVO = sin;
        ((DiagnosticoSiniestroDetailFrame) vista).getjButton1().setEnabled(true);
        return new VOResponse(beanVO);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        Session s = null;
        try {
            vista.saveGridsData();
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            if (persistentObject instanceof Auditable) {
                AuditoriaBasica ab = ((Auditable) persistentObject).getAuditoria();
                ab.setFechaUpdate(new Date());
                ab.setUsuarioUpdate(General.usuario.getUserName());
            }
            if (aplicarLogicaNegocio) {
                Response response = logicaNegocioConCambioEnVista(persistentObject, false);
                if (response.isError()) {
                    return response;
                }
                persistentObject = (ValueObject) ((VOResponse) response).getVo();
            }
            s.update(persistentObject);
            t.commit();
            if (linkForm != null && attributeName != null) {
                linkForm.getVOModel().setValue(attributeName, persistentObject);
                linkForm.pull(attributeName);
            }
            if (gridControl != null) {
                gridControl.reloadData();
            }
            return new VOResponse(persistentObject);
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "updateRecord", ex));
        } finally {
            s.close();
        }
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        DiagnosticoSiniestro ds = (DiagnosticoSiniestro) newPersistentObject;
        ds.setDetalleSiniestro(detalleSin);
        // detalleSin.getDiagnosticoSiniestros().add(ds);
        Response res = super.insertRecord(ds);
        if (res instanceof VOResponse) {
            ((DiagnosticoSiniestroDetailFrame) vista).getjButton1().setEnabled(true);
        }
        return res;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(((DiagnosticoSiniestroDetailFrame) vista).getjButton1())) {
            if (SuperusuarioLoginDialog.VerificarSuperusuario()) {
                new MantenimientoDiagnosticoDetailFrameController(vista,
                        false, (DiagnosticoSiniestro) vista.getMainPanel().getVOModel().getValueObject(), detalleSin);
                vista.dispose();
                return;
            }
        } else {
            TratamientoLookupController t = new TratamientoLookupController(((DiagnosticoSiniestro) getBeanVO()).getDiagnostico());
            t.addLookup2ParentLink("tratamiento");
            t.openLookupFrame(vista, new TratamientoLookupParent());
            vista.reloadGridsData();
        }
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        DiagnosticoSiniestro d = ((DiagnosticoSiniestro) persistentObject);
        if (d.getMontoPendiente() > d.getTotalDisponible()) {
            return new ErrorResponse("Exeso de Cobertura");
        }
        Double max = getConfiguracionSiniestro(d);
        if (d.getMontoPendiente() + d.getDisponiblePorTipoDetalle() > max) {
            return new ErrorResponse("Supera el limite maximo para: " + d.getDetalleSiniestro().getTipoDetalle()
                    + "\ntiene libres: " + (max - d.getDisponiblePorTipoDetalle()));
        }
        return new VOResponse(persistentObject);
    }

    @Override
    public void afterInsertData() {
        vista.getMainPanel().getReloadButton().doClick();
    }

    @Override
    public void afterReloadData() {
        frame.getMainPanel().getReloadButton().doClick();


    }

    /**
     * dice cuanto falta por consumir al tope por tipo de detalle de siniestro
     * @param  detalleSin Detalle de siniestro
     */
    private Double getDisponiblePorTipoDetalle(DetalleSiniestro detalleSin) {
        Session s = null;
        Double disp = 0d;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            disp = (Double) s.createQuery("SELECT SUM(montoPendiente+montoPagado) FROM "
                    + DiagnosticoSiniestro.class.getName()
                    + " WHERE detalleSiniestro.siniestro.ayo=:ayo AND "
                    + "detalleSiniestro.siniestro.asegurado.id=:aseg AND "
                    + "detalleSiniestro.tipoDetalle=:tipoDet").
                    setInteger("ayo", detalleSin.getSiniestro().getAyo()).
                    setLong("aseg", detalleSin.getSiniestro().getAsegurado().getId()).
                    setString("tipoDet", detalleSin.getTipoDetalle()).
                    uniqueResult();

        } catch (Exception ex) {
//            ex.printStackTrace();
        } finally {
            s.close();
        }
        if (disp == null) {
            disp = Double.POSITIVE_INFINITY;
        }
        return disp;
    }

    private Double getConfiguracionSiniestro(DiagnosticoSiniestro d) {
        Session s = null;
        ConfiguracionSiniestro c = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            c = (ConfiguracionSiniestro) s.createQuery("FROM "
                    + ConfiguracionSiniestro.class.getName()
                    + " WHERE  plan.id=:plan AND tipoDetalle=:td").
                    setLong("plan", detalleSin.getSiniestro().getAsegurado().getPlan().getId()).
                    setString("td", d.getDetalleSiniestro().getTipoDetalle()).
                    uniqueResult();

        } catch (Exception ex) {
//            ex.printStackTrace();
        } finally {
            s.close();
        }
        if (c != null) {
            return c.getMontoTope();
        }
        return Double.POSITIVE_INFINITY;
    }

    private class TratamientoLookupParent implements LookupParent {

        @Override
        public Object getLookupCodeParentValue() {
            return "";
        }

        @Override
        public ValueObject getValueObject() {
            return null;
        }

        @Override
        public void setValue(String string, Object o) {

            Tratamiento t = (Tratamiento) o;
            System.out.println("--------");
            System.out.println(t);
            if (t != null) {
                System.out.println("antes");
                System.out.println(((DiagnosticoSiniestro) getBeanVO()).getTratamientos().size());
                ((DiagnosticoSiniestro) getBeanVO()).getTratamientos().add(t);
                System.out.println("add rido");
                System.out.println(((DiagnosticoSiniestro) getBeanVO()).getTratamientos().size());
            }
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                s.beginTransaction();
                s.update(getBeanVO());
                System.out.println("update");
                System.out.println(((DiagnosticoSiniestro) getBeanVO()).getTratamientos().size());
                s.getTransaction().commit();
            } catch (Exception ex) {
            } finally {
                s.close();
            }
        }
    }

    private class AgotamientoActual {

        Agotamiento agotamiento;
        Double montoAmparado;

        public AgotamientoActual(Agotamiento agotamiento, Double montoAmparado) {
            this.agotamiento = agotamiento;
            this.montoAmparado = montoAmparado;
        }
    }

    private AgotamientoActual getAgotamiento(Diagnostico diagnostico, Asegurado asegurado) {

        Session s = null;
        Agotamiento agotamiento = null;
        Double montoAmparado = 0d;
        AgotamientoActual a = null;

        try {
            s = HibernateUtil.getSessionFactory().openSession();
            agotamiento = (Agotamiento) s.createQuery("FROM "
                    + Agotamiento.class.getName()
                    + " WHERE ayo=:ayo AND asegurado.id=:aseg AND diagnostico.id=:diag").
                    setInteger("ayo", detalleSin.getSiniestro().getAyo()).
                    // Calendar.getInstance().get(Calendar.YEAR) - 1).
                    setLong("aseg", asegurado.getId()).
                    setLong("diag", diagnostico.getId()).
                    uniqueResult();

            if (agotamiento
                    == null) {
                agotamiento = new Agotamiento(0d, 0d);
            }
            montoAmparado = (Double) s.createQuery("SELECT C.sumaAmparada.monto FROM "
                    + SumaAsegurada.class.getName()
                    + " C WHERE C.sumaAmparada.plan.id=?  AND C.diagnostico.id=?").
                    setLong(0, asegurado.getPlan().getId()).
                    setLong(1, diagnostico.getId()).uniqueResult();
            a = new AgotamientoActual(agotamiento, montoAmparado);
        } catch (Exception ex) {
//            ex.printStackTrace();
        } finally {
            s.close();
        }

        return a;
    }

    private void checkStatus() {
        DetalleSiniestro ds = detalleSin;
        if (ds.getEtapaSiniestro().getIdPropio().compareTo("ORD_PAG") == 0
                || ds.getEtapaSiniestro().getEstatusSiniestro().getNombre().
                compareTo("PENDIENTE") != 0) {
            ((DiagnosticoSiniestroDetailFrame) vista).hideAll();
        }

        if (ds.getEtapaSiniestro().getIdPropio().compareTo("LIQ") == 0
                && !General.usuario.getSuperusuario()) {
            ((DiagnosticoSiniestroDetailFrame) vista).hideAll();
        }
    }
}
