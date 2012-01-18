package com.jswitch.asegurados.controlador;

import com.jswitch.asegurados.modelo.maestra.Beneficiario;
import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.certificados.modelo.maestra.Certificado;
import java.util.Date;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.client.ReloadButton;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author Adrian
 */
public class BeneficiarioDetailFrameController extends DefaultDetailFrameController {

    private Certificado familia;
    private ReloadButton reload;

    public Form getMainPanel() {
        return vista.getMainPanel();
    }

    public BeneficiarioDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio, null, null);
    }

    public BeneficiarioDetailFrameController(String detailFramePath,
            GridControl gridControl,
            BeanVO beanVO,
            Boolean aplicarLogicaNegocio,
            Certificado familia,
            ReloadButton reload) {
        if (this.familia == null) {
            this.familia = familia;
        }
        if (this.familia == null && beanVO != null) {
            Beneficiario b = (Beneficiario) beanVO;
            if (b != null) {
                this.familia = b.getCertificado();
            }
        }
        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.aplicarLogicaNegocio = aplicarLogicaNegocio;
        this.reload = reload;
        try {
            Class<DefaultDetailFrame> t = (Class<com.jswitch.base.vista.util.DefaultDetailFrame>) Class.forName(detailFramePath);
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
            vista.getMainPanel().getVOModel().setValue(
                    "porcentajeDisponible", getPorcentajeDisponible());
            vista.getMainPanel().pull("porcentajeDisponible");
        }

    }

    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Beneficiario benef = (Beneficiario) s.get(Beneficiario.class, ((Beneficiario) beanVO).getId());
        Hibernate.initialize(benef.getNotasTecnicas());
        Hibernate.initialize(benef.getObservaciones());
        Hibernate.initialize(benef.getDocumentos());
        s.close();
        benef.setPorcentajeDisponible(getPorcentajeDisponible());
        beanVO = benef;
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Beneficiario b = (Beneficiario) newPersistentObject;
        b.setPorcentajeDisponible(getPorcentajeDisponible());
        b.setCertificado(familia);
        Response res = super.insertRecord(newPersistentObject);

        if (res instanceof VOResponse && familia != null) {
            familia.getBeneficiarios().add(b);
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

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        Beneficiario b = (Beneficiario) persistentObject;
        b.setPorcentajeDisponible(getPorcentajeDisponible(b) - b.getIndemnizacion());

        return super.updateRecord(oldPersistentObject, b);
    }

    @Override
    public void afterEditData() {
        if (reload != null) {
            reload.doClick();
        }
    }

    @Override
    public void afterInsertData() {
        if (reload != null) {
            reload.doClick();
        }
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        // return super.logicaNegocio(persistentObject);
        Beneficiario b = (Beneficiario) persistentObject;
        String errorMsj = "";
        if (b.getId() != null) {
            if (getPorcentajeDisponible(b) - b.getIndemnizacion() < 0) {
                errorMsj += "Valor Superior al 100%";
            }
        } else if (getPorcentajeDisponible() - b.getIndemnizacion() < 0) {
            errorMsj += "Valor Superior al 100%";
        }

        if (errorMsj.length() > 0) {
            return new ErrorResponse(errorMsj);
        } else {
            return new VOResponse(persistentObject);
        }
    }

    private Double getPorcentajeDisponible() {
        Double porcentajeDisponible = 0d;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            porcentajeDisponible = (Double) session.createQuery("SELECT SUM(B.indemnizacion) FROM "
                    + Certificado.class.getName() + " C LEFT JOIN C.beneficiarios"
                    + " B WHERE C.id=?").setLong(0, familia.getId()).uniqueResult();
        } catch (Exception ex) {
            LoggerUtil.error(BeneficiarioDetailFrameController.class,
                    "getPorcentajeDisponible()", ex);
        } finally {
            session.close();
        }
        return 100 - (porcentajeDisponible == null ? 0d : porcentajeDisponible);
    }

    private Double getPorcentajeDisponible(Beneficiario yo) {
        Double porcentajeDisponible = 0d;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            porcentajeDisponible = (Double) session.createQuery("SELECT SUM(B.indemnizacion) FROM "
                    + Certificado.class.getName() + " C LEFT JOIN C.beneficiarios"
                    + " B WHERE C.id=? AND B.id <> ?").
                    setLong(0, familia.getId()).
                    setLong(1, yo.getId()).uniqueResult();
        } catch (Exception ex) {
            LoggerUtil.error(BeneficiarioDetailFrameController.class,
                    "getPorcentajeDisponible()", ex);
        } finally {
            session.close();
        }

        return 100 - (porcentajeDisponible == null ? 0d : porcentajeDisponible);

    }
}
