package com.jswitch.polizas.controlador;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.asegurados.modelo.maestra.Titular;
import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.certificados.controlador.CertificadoDetailController;
import com.jswitch.certificados.controlador.CertificadoNuevoDetrailController;
import com.jswitch.certificados.modelo.utilitario.CertificadoNuevo;
import com.jswitch.certificados.modelo.maestra.Certificado;
import com.jswitch.certificados.vista.CertificadoDetailFrame;
import com.jswitch.certificados.vista.CertificadoNuevoDetailFrame;
import com.jswitch.persona.controlador.PersonasDetailController;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.persona.modelo.maestra.PersonaNatural;
import com.jswitch.persona.vista.RifBusquedaDialog;
import com.jswitch.polizas.modelo.maestra.Poliza;
import com.jswitch.polizas.vista.PolizaDetailFrame;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class PolizaDetailFrameController extends DefaultDetailFrameController {

    public PolizaDetailFrameController(
            String detailFramePath, GridControl gridControl, BeanVO beanVO, boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        if (beanVO == null) {
            ((PolizaDetailFrame) vista).setVisiblePaneles(false);
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        
        Poliza p =
                (Poliza) s.get(Poliza.class, ((Poliza) beanVO).getId());
        Hibernate.initialize(p.getCertificados());
        Hibernate.initialize(p.getObservaciones());
        Hibernate.initialize(p.getDocumentos());
        Hibernate.initialize(p.getNotasTecnicas());
        s.close();
        beanVO = p;
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Response res = super.insertRecord(newPersistentObject);
        if (!(res instanceof ErrorResponse)) {
            ((PolizaDetailFrame) vista).setVisiblePaneles(true);
        }
        return res;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);


        if (e.getSource().equals(((PolizaDetailFrame) vista).getNuevoCertificicadoButton())) {
            RifBusquedaDialog busquedaDialog = new RifBusquedaDialog(
                    new String[]{
                        Certificado.class.getName(),
                        Titular.class.getName(),
                        Asegurado.class.getName()},
                    new String[]{
                        "titular.persona",
                        "persona",
                        "persona"});
            BeanVO beanVO = busquedaDialog.getBenVO();

            if (beanVO != null && beanVO instanceof Certificado) {
                new CertificadoDetailController(CertificadoDetailFrame.class.getName(), ((PolizaDetailFrame) vista).getGridData(), beanVO, getBeanVO(), false);
            }
            if (beanVO != null && beanVO instanceof Titular
                    && quitarAsegurado((Titular) beanVO)) {
                Certificado cert = new Certificado();
                cert.setTitular((Titular) beanVO);
                try {
                    Session s = HibernateUtil.getSessionFactory().openSession();
                    s.beginTransaction();
                    Query q = s.createQuery("FROM " + Asegurado.class.getName()
                            + " WHERE persona.rif.rif=?").
                            setString(0, cert.getTitular().getPersona().getRif().getRif());
                    Asegurado a = (Asegurado) q.uniqueResult();
                    cert.getAsegurados().add(a);
                    cert.setAuditoria(new AuditoriaBasica(new Date(),
                            General.usuario.getUserName(), true));
                    s.save(cert);
                    s.getTransaction().commit();
                    s.close();
                    new CertificadoDetailController(
                            CertificadoDetailFrame.class.getName(),
                            ((PolizaDetailFrame) vista).getGridData(), cert, getBeanVO(), false);
                } catch (Exception ex) {
                    LoggerUtil.error(this.getClass(), "actionPerformed", ex);
                }

            }
            if (beanVO instanceof Asegurado) {
                quitarAsegurado((Asegurado) beanVO);
            }


            if (beanVO instanceof Persona) {
                Asegurado n = new Asegurado();
                n.setPersona((PersonaNatural) beanVO);
                beanVO = n;
            }

            if (beanVO instanceof Asegurado) {

                CertificadoNuevo c = new CertificadoNuevo();
                c.setAsegurado((Asegurado) beanVO);
                new CertificadoNuevoDetrailController(CertificadoNuevoDetailFrame.class.getName(), null, c, (Poliza) getBeanVO(), false);

            }
            if (beanVO == null && busquedaDialog.getRif().getRif() != null) {
                CertificadoNuevoDetrailController c = new CertificadoNuevoDetrailController(CertificadoNuevoDetailFrame.class.getName(), null, null, (Poliza) getBeanVO(), false);
                Form linkForm = c.getVista().getMainPanel();
                String linkAttName = "asegurado.persona";
                new PersonasDetailController(linkForm, linkAttName, new Object[]{"ASE", "TIT"}, null, null, busquedaDialog.getRif());
            }


        }


    }

    boolean quitarAsegurado(Asegurado aseg) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            Query q = s.createQuery("SELECT a, C FROM " + Certificado.class.getName()
                    + " C JOIN C.asegurados a WHERE a.persona.id=:id").
                    setLong("id", ((Asegurado) aseg).getId());
            Object[] a = (Object[]) q.uniqueResult();
            if (a[0] != null) {
                int res = JOptionPane.showConfirmDialog(
                        MDIFrame.getInstance(),
                        ((Asegurado) aseg).getPersona().getNombreLargo() + " esta asegurado\n"
                        + "en el Certificado de "
                        + ((Certificado) a[1]).getTitular().getPersona().getNombreLargo(),
                        "Certificados", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    ((Certificado) a[1]).getAsegurados().remove((Asegurado) a[0]);
                    s.update(a[1]);
                } else {
                    return false;
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "quitarAsegurado(Asegurado aseg)", ex);
            return false;
        } finally {
            s.getTransaction().commit();
            s.close();
        }
        return true;
    }

    private boolean quitarAsegurado(Titular titular) {
        Session s = null;
        Asegurado a = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("FROM " + Asegurado.class.getName()
                    + " WHERE persona.rif.rif=:id").
                    setString("id", ((Titular) titular).getPersona().getRif().getRif());
            a = (Asegurado) q.uniqueResult();

        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "quitarAsegurado(Titular titular)", ex);
            return false;
        } finally {
            s.close();
        }
        return quitarAsegurado(a);
    }
}
