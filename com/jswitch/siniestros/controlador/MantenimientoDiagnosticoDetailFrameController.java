package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.NotaTecnica;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
import com.jswitch.siniestros.modelo.transaccional.MantenimientoDiagnostico;
import com.jswitch.siniestros.vista.MantenimientoDiagnosticoDetailFrame;
import java.util.Date;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author Adrian
 */
public class MantenimientoDiagnosticoDetailFrameController extends DefaultDetailFrameController {

    private DiagnosticoSiniestro diagnosticoSin;
    private DetalleSiniestro detalleSiniestro;
    private DefaultDetailFrame frame;

    public MantenimientoDiagnosticoDetailFrameController(DefaultDetailFrame frame,
            boolean b, DiagnosticoSiniestro diagnosticoSiniestro, DetalleSiniestro detalleSiniestro) {
        this.frame = frame;
        this.diagnosticoSin = diagnosticoSiniestro;
        vista = new MantenimientoDiagnosticoDetailFrame();
        ((MantenimientoDiagnosticoDetailFrame) vista).setDiagnosticoSiniestro(diagnosticoSiniestro);
        vista.inicializar(this, true);
        vista.getMainPanel().setMode(Consts.INSERT);
        vista.getMainPanel().getVOModel().setValue("diagnosticoSiniestro", diagnosticoSin);
        vista.getMainPanel().getVOModel().setValue("montoAnterior", diagnosticoSin.getMontoPendiente());
        vista.getMainPanel().pull("diagnosticoSiniestro");
        vista.getMainPanel().pull("montoAnterior");
    }

    private void loadDetalleSiniestro(DetalleSiniestro detalleSiniestro) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            this.detalleSiniestro = (DetalleSiniestro) s.get(DetalleSiniestro.class, detalleSiniestro.getId());
            Hibernate.initialize(this.detalleSiniestro.getNotasTecnicas());
            Hibernate.initialize(this.detalleSiniestro.getDiagnosticoSiniestros());
            Hibernate.initialize(this.detalleSiniestro.getPagos());
            Hibernate.initialize(this.detalleSiniestro.getDocumentos());
            Hibernate.initialize(this.detalleSiniestro.getObservaciones());
        } catch (Exception exception) {
            System.out.println(exception);
        } finally {
            s.close();
        }
    }

    private void loadDiagnosticoSiniestro(DiagnosticoSiniestro ds) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            this.diagnosticoSin = (DiagnosticoSiniestro) s.get(DiagnosticoSiniestro.class, ds.getId());
            Hibernate.initialize(this.diagnosticoSin.getTratamientos());
        } catch (Exception exception) {
            System.out.println(exception);
        } finally {
            s.close();
        }
    }

    @Override
    public void createPersistentObject(ValueObject persistentObject)
            throws Exception {
        ((MantenimientoDiagnostico) persistentObject).setDiagnosticoSiniestro(diagnosticoSin);
        ((MantenimientoDiagnostico) persistentObject).setMontoAnterior(diagnosticoSin.getMontoPendiente());
        super.createPersistentObject(persistentObject);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        loadDiagnosticoSiniestro(diagnosticoSin);
        diagnosticoSin.setMontoPendiente(((MantenimientoDiagnostico) newPersistentObject).getMontoActual());
        Date d = new Date();
        diagnosticoSin.getAuditoria().setFechaUpdate(d);
        diagnosticoSin.getAuditoria().setUsuarioUpdate(General.usuario.getUserName());
        NotaTecnica nota = new NotaTecnica("Modificacion monto reservado: "
                + ((MantenimientoDiagnostico) newPersistentObject).getJustificacion(),
                new AuditoriaBasica(d, General.usuario.getUserName(), Boolean.TRUE));
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.update(diagnosticoSin);
            s.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            s.close();
        }
        loadDetalleSiniestro(diagnosticoSin.getDetalleSiniestro());
        s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.save(nota);
            detalleSiniestro.getNotasTecnicas().add(nota);
            s.update(detalleSiniestro);
            s.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            s.close();
        }
        Response res = super.insertRecord(newPersistentObject);
        frame.getMainPanel().getReloadButton().doClick();
        return res;
    }
}
