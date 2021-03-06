package com.jswitch.certificados.controlador;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.certificados.modelo.maestra.Certificado;
import com.jswitch.polizas.modelo.maestra.Poliza;
import com.jswitch.certificados.vista.CertificadoDetailFrame;
import com.jswitch.asegurados.vista.RifAseguradoDialog;
import com.jswitch.asegurados.vista.RifBeneficiarioDialog;
import java.awt.event.ActionEvent;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.client.InsertButton;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Luis Adrian Gonzalez
 */
public class CertificadoDetailController extends DefaultDetailFrameController {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((InsertButton) e.getSource()).getButtonId().equals("asegurado")) {
            new RifAseguradoDialog((CertificadoDetailFrame) vista);
        } else {
            new RifBeneficiarioDialog((CertificadoDetailFrame) vista);
        }
    }
    Poliza padre;

    public CertificadoDetailController(
            String detailFramePath, GridControl gridControl, BeanVO beanVO, BeanVO parentVO, Boolean aplicarLogicaNegocio) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        if (parentVO != null) {
            padre = (Poliza) parentVO;
        }
    }

    public CertificadoDetailController(
            String detailFramePath, GridControl gridControl, BeanVO beanVO, boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        if (beanVO == null) {
            ((CertificadoDetailFrame) vista).setVisiblePaneles(false);
        }
    }

    public CertificadoDetailController(
            String detailFramePath, GridControl gridControl, boolean aplicarLogicaNegocio, Asegurado aseg) {
        super(detailFramePath, gridControl, aseg != null ? aseg.getCertificado() : null, aplicarLogicaNegocio);
        if (beanVO == null) {
            ((CertificadoDetailFrame) vista).setVisiblePaneles(false);
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        if (((Certificado) beanVO).getId() != null) {
            Session s = HibernateUtil.getSessionFactory().openSession();
            Certificado p =
                    (Certificado) s.get(Certificado.class, ((Certificado) beanVO).getId());
            Hibernate.initialize(p.getDocumentos());
            Hibernate.initialize(p.getObservaciones());
            Hibernate.initialize(p.getNotasTecnicas());
            Hibernate.initialize(p.getAsegurados());
            s.close();
            beanVO = p;
        }
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Session s = null;
        Certificado c = (Certificado) newPersistentObject;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("SELECT COUNT (C) FROM " + Certificado.class.getName()
                    + " C WHERE C.titular.persona.rif.rif=?").setString(0, c.getTitular().getPersona().getRif().getRif());
            if ((Long) q.uniqueResult() > 0) {
                return new ErrorResponse("Titular ya posee su Certificado");
            }
        } finally {
            s.close();
        }
        Response res = super.insertRecord(newPersistentObject);
        if ((res instanceof VOResponse)) {
            agregarAlGrid((Certificado) newPersistentObject);
        }
        return res;
    }

    public void agregarAlGrid(Certificado p) {
        //gridContro
        if (padre != null) {
            padre.getCertificados().add(p);
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = s.beginTransaction();
                s.update(padre);
                tx.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                s.close();
            }
            gridControl.reloadData();
        }
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        String errorMsj = "";
        if (errorMsj.length() > 0) {
            return new ErrorResponse(errorMsj);
        } else {
            return new VOResponse(persistentObject);
        }
    }
}
