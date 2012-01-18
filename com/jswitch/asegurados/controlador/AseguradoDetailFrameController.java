package com.jswitch.asegurados.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.certificados.modelo.maestra.Certificado;
import com.jswitch.asegurados.modelo.maestra.Titular;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.configuracion.modelo.maestra.ConfiguracionPrima;
import com.jswitch.siniestros.controlador.SiniestroDetailFrameController;
import com.jswitch.siniestros.controlador.SiniestroGridFrameController;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.vista.SiniestroDetailFrame;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 * @version 1.10.0
 * @author Luis Adrian Gonzalez Benavides
 */
public class AseguradoDetailFrameController extends DefaultDetailFrameController {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            //asegurado getMainPanel().getVOModel().getValueObject();
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                List l = s.createQuery("FROM " + Siniestro.class.getName() + " S"
                        + " WHERE asegurado.id=?").setLong(0, ((Asegurado) getMainPanel().getVOModel().getValueObject()).getId()).list();
                if (!l.isEmpty()) {
                    if (l.size() < 2) {
                        new SiniestroDetailFrameController(SiniestroDetailFrame.class.getName(), null, (Siniestro) l.get(0), true);
                    } else {
                        new SiniestroGridFrameController(new VOListResponse(l, false, l.size()));
                    }
                } else {
                    new SiniestroDetailFrameController(SiniestroDetailFrame.class.getName(), null, true, ((Asegurado) getMainPanel().getVOModel().getValueObject()));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                s.close();
            }
        }

    }
    private Certificado familia;

    public Form getMainPanel() {
        return vista.getMainPanel();
    }

    public AseguradoDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    public AseguradoDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Certificado familia) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        this.familia = familia;
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Asegurado aseg = (Asegurado) s.get(Asegurado.class, ((Asegurado) beanVO).getId());
        Hibernate.initialize(aseg.getNotasTecnicas());
        Hibernate.initialize(aseg.getObservaciones());
        Hibernate.initialize(aseg.getDocumentos());
        s.close();
        beanVO = aseg;
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        if (canInsert((Asegurado) newPersistentObject)) {
            ((Asegurado) beanVO).setCertificado(familia);
            Response res = super.insertRecord(newPersistentObject);
            if (res instanceof VOResponse && familia != null) {
                familia.getAsegurados().add((Asegurado) beanVO);
                if (familia instanceof Auditable) {
                    AuditoriaBasica ab = ((Auditable) familia).getAuditoria();
                    ab.setFechaUpdate(new Date());
                    ab.setUsuarioUpdate(General.usuario.getUserName());
                }
                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = s.beginTransaction();
                    s.update(familia);
                    tx.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    s.close();
                }
                gridControl.reloadData();
            }
            return res;
        }
        return null;
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        verificarConfigPrima((Asegurado) persistentObject);
        String errorMsj = "";
        if (!errorMsj.isEmpty()) {
            return new ErrorResponse(errorMsj);
        } else {
            return new VOResponse(persistentObject);
        }
    }

    public void verificarConfigPrima(Asegurado aseg) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Query query = s.createQuery("From " + ConfiguracionPrima.class.getName()
                    + " WHERE parentesco.id=:parentesco"
                    + " AND sexo=:sexo"
                    + " AND edadDesde>=:edadD"
                    + " AND edadHasta<=:edadH").setLong("parentesco", aseg.getParentesco().getId()).setString("sexo", aseg.getPersona().getSexo().name()).setInteger("edadD", aseg.getPersona().getEdad()).setInteger("edadH", aseg.getPersona().getEdad());

            ConfiguracionPrima conf = (ConfiguracionPrima) query.uniqueResult();

            if (conf != null) {
                aseg.setPrimaAporte(conf.getPrimaAporte());
                aseg.setPrimaAsegurado(conf.getPrimaAsegurado());
                aseg.setPrimaTotal(conf.getPrimaTotal());

            } else {
                query = s.createQuery("From " + ConfiguracionPrima.class.getName()
                        + " WHERE UPPER(parentesco.nombre)=:parentesco"
                        + " AND sexo=:sexo"
                        + " AND edadDesde>=:edadD"
                        + " AND edadHasta<=:edadH").setString("parentesco", "TODOS").setString("sexo", aseg.getPersona().getSexo().name()).setInteger("edadD", aseg.getPersona().getEdad()).setInteger("edadH", aseg.getPersona().getEdad());
                conf = (ConfiguracionPrima) query.uniqueResult();
                if (conf != null) {
                    aseg.setPrimaAporte(conf.getPrimaAporte());
                    aseg.setPrimaAsegurado(conf.getPrimaAsegurado());
                    aseg.setPrimaTotal(conf.getPrimaTotal());
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            s.close();
        }
    }

    public boolean canInsert(Asegurado aseg) {

        Session s = null;
        Titular data = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = s.beginTransaction();
            Query query = s.createQuery("From " + Titular.class.getName()
                    + " WHERE persona.rif.rif=?").setString(0, aseg.getPersona().getRif().getRif());
            data = (Titular) query.uniqueResult();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            s.close();
        }

        if (data != null
                && !data.getPersona().getRif().getRif().equals(
                familia.getTitular().getPersona().getRif().getRif())) {
            JOptionPane.showMessageDialog(new JFrame(), "El Asegurado seleccionado es ya un"
                    + "Titular en \"" + General.empresa.getNombre() + "\"");
            return false;
        }
        return true;
    }
}
