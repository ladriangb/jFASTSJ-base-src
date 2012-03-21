package com.jswitch.siniestros.controlador;

import com.jswitch.siniestros.controlador.detalle.DetalleSiniestroDetailFrameController;
import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.SuperusuarioLoginDialog;
import com.jswitch.fas.modelo.Dominios;
import com.jswitch.fas.utils.ReportesUtil;
import com.jswitch.siniestros.controlador.detalle.DetalleVidaNuevoDetrailController;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.Vida;
import com.jswitch.siniestros.vista.SiniestroDetailFrame;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroChousser;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
import com.jswitch.siniestros.vista.detalle.DetalleVidaNuevoDetailFrame;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.client.InsertButton;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientSettings;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author Luis Adrian
 */
public class SiniestroDetailFrameController extends DefaultDetailFrameController {

    public SiniestroDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    public SiniestroDetailFrame getVista() {

        return (SiniestroDetailFrame) vista;
    }

    public SiniestroDetailFrameController(String detailFramePath, GridControl gridControl, Boolean aplicarLogicaNegocio, Asegurado asegurado) {
        super(detailFramePath,
                gridControl,
                null, aplicarLogicaNegocio);
        vista.getMainPanel().getVOModel().setValue("asegurado", asegurado);
        vista.getMainPanel().pull("asegurado");

        vista.getMainPanel().getVOModel().setValue("certificado", asegurado.getCertificado());
        vista.getMainPanel().pull("certificado");
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Siniestro sin = (Siniestro) s.get(Siniestro.class, ((Siniestro) beanVO).getId());
        Hibernate.initialize(sin.getDetalleSiniestro());
        Hibernate.initialize(sin.getObservaciones());
        Hibernate.initialize(sin.getDocumentos());
        Hibernate.initialize(sin.getNotasTecnicas());
        s.close();
        beanVO = sin;
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Siniestro s = (Siniestro) newPersistentObject;
        s.setCertificado(s.getAsegurado().getCertificado());
        return super.insertRecord(s);
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        Siniestro siniestro = (Siniestro) persistentObject;
        if (ReportesUtil.diferenciaEnDias(new Date(), siniestro.getFechaCreacion()) < 0) {
            if (!SuperusuarioLoginDialog.VerificarSuperusuario()) {
                return new ErrorResponse("Usuario no Verificado");
            }
        }
        return new VOResponse(persistentObject);
    }

    @Override
    public Response logicaNegocioDespuesSave(ValueObject persistentObject, Session s) {
        Long seq = null;
        try {
            seq = ((BigInteger) s.createSQLQuery("SELECT nextval('seq_siniestro');").uniqueResult()).longValue();
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "logicaNegocioDespuesSave", ex));
        }
        Siniestro siniestro = (Siniestro) persistentObject;
        Calendar c = Calendar.getInstance();
        c.setTime(siniestro.getFechaCreacion());
        DecimalFormat nf = new DecimalFormat("00000");
        SimpleDateFormat df = new SimpleDateFormat("yyMM-");
        siniestro.setNumero(df.format(c.getTime()) + nf.format(seq));
        siniestro.setAyo(c.get(Calendar.YEAR));
        siniestro.setMes(c.get(Calendar.MONTH) + 1);
        siniestro.setSeq(seq);
        return new VOResponse(siniestro);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof InsertButton) {
            //<editor-fold defaultstate="collapsed" desc="Crear nuevo">
            if (beanVO != null) {
                if (((Siniestro) beanVO).getCertificado().getPoliza().getVigenciaHasta().before(new Date())) {
                    JOptionPane.showMessageDialog(gridControl,
                            ClientSettings.getInstance().getResources().getResource(
                            "No se Permite Realizar el Siniestro a Este Asegurado\n"
                            + "La Poliza no esta Vigente"),
                            General.edition, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (!((Siniestro) beanVO).getAsegurado().getAuditoria().getActivo()) {
                    JOptionPane.showMessageDialog(gridControl,
                            ClientSettings.getInstance().getResources().getResource(
                            "No se Permite Realizar el Siniestro a Este Asegurado\n"
                            + "El Asegurado no está Activo"),
                            General.edition, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (!((Siniestro) beanVO).getCertificado().getAuditoria().getActivo()) {
                    JOptionPane.showMessageDialog(gridControl,
                            ClientSettings.getInstance().getResources().getResource(
                            "No se Permite Realizar el Siniestro a Este Asegurado\n"
                            + "El Certificado no está Activo"),
                            General.edition, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (!((Siniestro) beanVO).getCertificado().getPoliza().getAuditoria().getActivo()) {
                    JOptionPane.showMessageDialog(gridControl,
                            ClientSettings.getInstance().getResources().getResource(
                            "No se Permite Realizar el Siniestro a Este Asegurado\n"
                            + "El Poliza no está Activo"),
                            General.edition, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if (!((Siniestro) beanVO).getCertificado().getTitular().getAuditoria().getActivo()) {
                    JOptionPane.showMessageDialog(gridControl,
                            ClientSettings.getInstance().getResources().getResource(
                            "No se Permite Realizar el Siniestro a Este Asegurado\n"
                            + "El Titular del certificado no está Activo"),
                            General.edition, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }


                Class c = DetalleSiniestroChousser.showDialog();
                if (c != null && c.getClass() != null) {
                    if (c.equals(Vida.class)) {
                        new DetalleVidaNuevoDetrailController(DetalleVidaNuevoDetailFrame.class.getName(), ((SiniestroDetailFrame) vista).getGridData(), null, (Siniestro) beanVO, false);
                    } else {
                        new DetalleSiniestroDetailFrameController(DetalleSiniestroDetailFrame.class.getName(), ((SiniestroDetailFrame) vista).getGridData(), null, true, (Siniestro) beanVO, c);
                    }
                }
            }
            //</editor-fold>
        } else if (e.getSource() == ((SiniestroDetailFrame) vista).getEstadoButton()) {
            //<editor-fold defaultstate="collapsed" desc="Cambiar Estado">
            if (vista.getMainPanel().getMode() == Consts.READONLY) {
                Siniestro ss = ((Siniestro) beanVO);
                switch (((Siniestro) beanVO).getEstadoSiniestro()) {
                    case ABIERTO:
                        int op = JOptionPane.showConfirmDialog(MDIFrame.getInstance(),
                                "Si Cierra el siniestro necesitara privilegios de Super usuario para poder abrirlo nuevamente. Desea Cerralo?",
                                "Cerrar Sinietro",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
                        if (op == JOptionPane.YES_OPTION) {
                            ss.setEstadoSiniestro(Dominios.EstadoSiniestro.CERRADO);
                            ((SiniestroDetailFrame) vista).validarEstadoSiniestro(ss.getEstadoSiniestro());
                        }
                        break;
                    case CERRADO:
                        if (SuperusuarioLoginDialog.VerificarSuperusuario()) {
                            ss.setEstadoSiniestro(Dominios.EstadoSiniestro.ABIERTO);
                            ((SiniestroDetailFrame) vista).validarEstadoSiniestro(ss.getEstadoSiniestro());
                        } else {
                            return;
                        }
                        break;
                }

                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    Transaction t = s.beginTransaction();
                    s.update(ss);
                    t.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    s.close();
                }
                ((SiniestroDetailFrame) vista).validarEstado(ss);
            } else {
                JOptionPane.showMessageDialog(vista, "Debes guardar primero el Registro");
            }
            //</editor-fold>
        }
    }
}
