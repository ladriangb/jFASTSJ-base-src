package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.utilitario.BuscarSiniestro;
import com.jswitch.siniestros.vista.SiniestroDetailFrame;
import com.jswitch.vistasbd.ListaDiagnostico;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 * Buscador de Siniestros
 * @author Luis Adrian Gonzalez Benavides
 */
public class BuscarSiniestroDetrailController extends DefaultDetailFrameController {

    public BuscarSiniestroDetrailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return insertRecord(persistentObject);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        BuscarSiniestro bs = (BuscarSiniestro) newPersistentObject;
        Session s = HibernateUtil.getSessionFactory().openSession();
        boolean Snum = false, Sayo = false, Smes = false,
                Anombre = false, Arif = false, Tnombre = false,
                Trif = false, diag = false;
        String where = "";
        String and = "";
        //<editor-fold defaultstate="collapsed" desc="Siniestro">
        if (bs.getSiniestro().getNumero() != null
                && !bs.getSiniestro().getNumero().trim().isEmpty()) {
            where += " siniestro.numero like :num";
            Snum = true;
            and = " AND ";
        }
        if (bs.getSiniestro().getAyo() != null) {
            where += and + " siniestro.ayo=:ayo";
            Sayo = true;
            and = " AND ";
        }
        if (bs.getSiniestro().getMes() != null) {
            where += and + " siniestro.mes=:mes";
            Smes = true;
            and = " AND ";
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Asegurado">
        if (bs.getPersonaAseg().getRif().getRif() != null
                && !bs.getPersonaAseg().getRif().getRif().trim().isEmpty()) {
            where += and + " siniestro.asegurado.persona.rif.rif like :Arif";
            Arif = true;
            and = " AND ";
        }
        if (bs.getPersonaAseg().getNombreLargo() != null
                && !bs.getPersonaAseg().getNombreLargo().trim().isEmpty()) {
            where += and + " siniestro.asegurado.persona.nombreLargo like :Anom";
            Anombre = true;
            and = " AND ";
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Titular">
        if (bs.getPersonaTit().getRif().getRif() != null
                && !bs.getPersonaTit().getRif().getRif().trim().isEmpty()) {
            where += and + " siniestro.certificado.titular.persona.rif.rif like :Trif";
            Trif = true;
            and = " AND ";
        }
        if (bs.getPersonaTit().getNombreLargo() != null
                && !bs.getPersonaTit().getNombreLargo().trim().isEmpty()) {
            where += and + " siniestro.certificado.titular.persona.nombreLargo like :Tnom";
            Tnombre = true;
            and = " AND ";
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Diagnostico">
        if (bs.getDiagnostico().getId() != null) {
            where += and + " diagnosticoSiniestro.diagnostico.id=:di";
            diag = true;
        }
        //</editor-fold>
        try {
            String sql = "FROM " + ListaDiagnostico.class.getName() + " C ";
            sql = where.trim().isEmpty() ? sql : (sql + "WHERE " + where);
            Query q = s.createQuery(sql);
            //<editor-fold defaultstate="collapsed" desc="siniestro">
            if (Snum) {
                q = q.setString("num", "%" + bs.getSiniestro().getNumero() + "%");
            }
            if (Sayo) {
                q = q.setInteger("ayo", bs.getSiniestro().getAyo());
            }
            if (Smes) {
                q = q.setInteger("mes", bs.getSiniestro().getMes());
            }
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Asegurado">
            if (Anombre) {
                q = q.setString("Anom", "%" + bs.getPersonaAseg().getNombreLargo() + "%");
            }
            if (Arif) {
                q = q.setString("Arif", "%" + bs.getPersonaAseg().getRif().getRif() + "%");
            }
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="titular">
            if (Tnombre) {
                q = q.setString("Tnom", "%" + bs.getPersonaTit().getNombreLargo() + "%");
            }
            if (Trif) {
                q = q.setString("Trif", "%" + bs.getPersonaTit().getRif().getRif() + "%");
            }
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="diagnostico">
            if (diag) {
                q = q.setLong("di", bs.getDiagnostico().getId());
            }
            //</editor-fold>
            List list = q.list();
            if (list != null) {
                if (list.size() > 1) {
                    List<Siniestro> sin = new ArrayList<Siniestro>(0);
                    for (Object object : list) {
                        sin.add(((ListaDiagnostico) object).getSiniestro());
                    }
                    new SiniestroGridFrameController(new VOListResponse(sin, false, sin.size()));
                } else if (!list.isEmpty()) {
                    Siniestro d = ((ListaDiagnostico) list.get(0)).getSiniestro();
                    new SiniestroDetailFrameController(SiniestroDetailFrame.class.getName(), null, d, false);
                } else {
                    JOptionPane.showMessageDialog(vista, "No se encuentran registros");
                }
            } else {
                JOptionPane.showMessageDialog(vista, "No se encuentran registros");
            }
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "insertRecord", e);
        } finally {
            s.close();
        }

        return new VOResponse(newPersistentObject);

    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        return super.logicaNegocio(persistentObject);
    }

    @Override
    public Response logicaNegocioDespuesSave(ValueObject persistentObject, Session s) {
        return super.logicaNegocioDespuesSave(persistentObject, s);
    }
}
