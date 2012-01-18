package com.jswitch.certificados.controlador;

import com.jswitch.asegurados.modelo.dominio.Parentesco;
import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.certificados.modelo.utilitario.CertificadoNuevo;
import com.jswitch.certificados.modelo.maestra.Certificado;
import com.jswitch.certificados.vista.CertificadoDetailFrame;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.polizas.modelo.maestra.Poliza;
import java.util.Date;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author orlandobcrra
 */
public class CertificadoNuevoDetrailController extends DefaultDetailFrameController {

    private Poliza poliza;

    public CertificadoNuevoDetrailController(String detailFramePath, GridControl gridControl, CertificadoNuevo beanVO, Poliza poliza, Boolean aplicarLogicaNegocio) {
        this.poliza = poliza;
        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.aplicarLogicaNegocio = aplicarLogicaNegocio;
        try {
            Class<DefaultDetailFrame> t = (Class<com.jswitch.base.vista.util.DefaultDetailFrame>) Class.forName(detailFramePath);
            vista = t.newInstance();
            vista.inicializar(this, true);

        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }
        vista.getMainPanel().setMode(Consts.INSERT);

        if (beanVO != null) {
            vista.getMainPanel().getVOModel().setValue("asegurado", beanVO.getAsegurado());
            vista.getMainPanel().pull("asegurado");
        }

        if (poliza != null) {
            vista.getMainPanel().getVOModel().setValue("poliza", poliza);
            vista.getMainPanel().pull("poliza");
        }
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        Session s = null;
        try {
            vista.saveGridsData();
            s = HibernateUtil.getSessionFactory().openSession();

            if (poliza == null) {
                poliza = (Poliza) s.get(Poliza.class,
                        (((CertificadoNuevo) newPersistentObject).getPoliza()).getId());

                Hibernate.initialize(poliza.getCertificados());
                Hibernate.initialize(poliza.getDocumentos());
                Hibernate.initialize(poliza.getNotasTecnicas());
                Hibernate.initialize(poliza.getObservaciones());
            }

            //s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            CertificadoNuevo certificadoNuevo = (CertificadoNuevo) newPersistentObject;
            certificadoNuevo.setCertificado(new Certificado());
            AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
            certificadoNuevo.getCertificado().setAuditoria(ab);
            certificadoNuevo.getTitular().setAuditoria(ab);
            //addTipoPersona(certificadoNuevo.getAsegurado().getPersona(), new String[]{"ASE", "TIT"});
            if (certificadoNuevo.getAsegurado().getId() == null) {
                certificadoNuevo.getAsegurado().setAuditoria(ab);
            }

            Parentesco p = (Parentesco) s.createQuery("FROM " + Parentesco.class.getName() + " WHERE nombre=:nombre").
                    setString("nombre", "TITULAR").
                    uniqueResult();

            certificadoNuevo.getAsegurado().setCertificado(certificadoNuevo.getCertificado());

            certificadoNuevo.getAsegurado().setParentesco(p);
            certificadoNuevo.getTitular().setPersona(certificadoNuevo.getAsegurado().getPersona());
            certificadoNuevo.getCertificado().setTitular(certificadoNuevo.getTitular());
            certificadoNuevo.getCertificado().getAsegurados().add(certificadoNuevo.getAsegurado());
            certificadoNuevo.getCertificado().setPoliza(poliza);
            poliza.getCertificados().add(certificadoNuevo.getCertificado());
            s.saveOrUpdate(certificadoNuevo.getTitular());
            s.saveOrUpdate(certificadoNuevo.getAsegurado());
            s.save(certificadoNuevo.getCertificado());
            s.update(poliza);
            t.commit();

            vista.dispose();
            new CertificadoDetailController(CertificadoDetailFrame.class.getName(), null, certificadoNuevo.getCertificado(), poliza, false);


            
            return new VOResponse(newPersistentObject);
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
        } finally {
            s.close();
        }
    }

    void addTipoPersona(Persona persona, String[] tags) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();

            if (persona.getId() != null) {
                Hibernate.initialize(persona);
            }

            //Transaction t = s.beginTransaction();
            for (String string : tags) {
                TipoPersona tp = (TipoPersona) s.createQuery("FROM " + TipoPersona.class.getName() + " T WHERE T.idPropio=:idP").
                        setString("idP", string).
                        uniqueResult();
                (persona).getTiposPersona().add(tp);
            }
            //s.update(s)
            //t.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            s.close();
        }
    }

    public DefaultDetailFrame getVista() {
        return vista;
    }
}
