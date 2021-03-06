package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.asegurados.modelo.maestra.Beneficiario;
import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.certificados.modelo.maestra.Certificado;
import com.jswitch.configuracion.modelo.dominio.Ramo;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import com.jswitch.configuracion.modelo.maestra.Plan;
import com.jswitch.configuracion.modelo.transaccional.SumaAsegurada;
import com.jswitch.fas.controlador.Principal;
import com.jswitch.fas.modelo.Dominios.TratamientoEfectuado;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.Vida;
import com.jswitch.siniestros.modelo.utilitario.DetalleVida;
import com.jswitch.siniestros.vista.detalle.DetalleVidaNuevoDetailFrame;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientSettings;

/**
 *
 * @author orlandobcrra
 */
public class DetalleVidaNuevoDetrailController extends DefaultDetailFrameController {

    private Siniestro siniestro;

    public DetalleVidaNuevoDetrailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Siniestro siniestro, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        this.siniestro = siniestro;

        ((DetalleVidaNuevoDetailFrame) vista).setPlanToLookup(siniestro.getAsegurado().getPlan());
        if (!siniestro.getCertificado().getTitular().getAuditoria().getActivo()
                || !siniestro.getAsegurado().getAuditoria().getActivo() || !checkRamo("VIDA")) {
            JOptionPane.showMessageDialog(gridControl,
                    ClientSettings.getInstance().getResources().getResource("No se Permite Realizar el Siniestro a Este Asegurado"),
                    General.edition, JOptionPane.INFORMATION_MESSAGE);
            vista.setVisible(false);
            vista.dispose();
            return;
        }
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Certificado sin = (Certificado) s.get(Certificado.class, (siniestro.getCertificado()).getId());
        Hibernate.initialize(sin.getBeneficiarios());
        s.close();
        List<Beneficiario> beneficiarios = new ArrayList<Beneficiario>(sin.getBeneficiarios());
        DetalleVida detalleVida = (DetalleVida) newPersistentObject;
        Double indemnizacion = getIndemValue(detalleVida.getDiagnostico());
        Double asignado = 0d;
        for (int i = 0; i < beneficiarios.size(); i++) {
            Beneficiario beneficiario = beneficiarios.get(i);
            Vida vida = new Vida();
            DecimalFormat nf = new DecimalFormat("00");
            vida.setNumero(nf.format(siniestro.getDetalleSiniestro().size() + 1));

            vida.setPersonaPago(beneficiario.getPersona());
            vida.setTipoDetalle("Vida");
            vida.setFechaDefuncion(detalleVida.getFechaDefuncion());
            vida.setTipoSiniestro(detalleVida.getTipoSiniestro());
            vida.setSiniestro(siniestro);
            vida.setTipoContrato(siniestro.getCertificado().getTitular().getTipoContrato());
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("FROM " + EtapaSiniestro.class.getName() + " C"
                    + " WHERE C.idPropio='VIDA'");
            EtapaSiniestro et = (EtapaSiniestro) q.uniqueResult();
            q = s.createQuery("FROM " + TipoPersona.class.getName() + " C"
                    + " WHERE C.idPropio='BEN'");
            TipoPersona tp = (TipoPersona) q.uniqueResult();
            q = s.createQuery("FROM " + Ramo.class.getName() + " C "
                    + "WHERE C.idPropio='VIDA'");
            Ramo ramo = (Ramo) q.uniqueResult();
            s.close();
            vida.setRamo(ramo);
            vida.setEtapaSiniestro(et);
            vida.setTipoPersona(tp);
            vida.setTratamientoEfectuado(TratamientoEfectuado.DESCONOCIDO);
            super.insertRecord(vida);
            Double indem = (i < beneficiarios.size() - 1)
                    ? Principal.truncar(indemnizacion * beneficiario.getIndemnizacion() / 100)
                    : indemnizacion - asignado;
            asignado += indem;
            DiagnosticoSiniestro ds = new DiagnosticoSiniestro(vida,
                    detalleVida.getDiagnostico(), indem);
            vida.getDiagnosticoSiniestros().add(ds);
            super.insertRecord(ds);
        }
        vista.dispose();
        return new VOResponse(newPersistentObject);
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

    private Double getIndemValue(Diagnostico diagnostico) {

        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            String sql = "FROM " + SumaAsegurada.class.getName()
                    + " S WHERE S.diagnostico.id= ?"
                    + " AND S.plan.id= ?";
            List<SumaAsegurada> l = s.createQuery(sql).
                    setLong(0, diagnostico.getId()).
                    setLong(1, siniestro.getAsegurado().
                    getPlan().getId()).
                    list();
            if (l != null && l.size() > 0) {
                return l.get(0).getSumaAmparada().getMonto();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            s.close();
        }
        return 0d;
    }
}
