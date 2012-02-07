package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.NotaTecnica;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.vista.util.SuperusuarioLoginDialog;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
import com.jswitch.siniestros.modelo.transaccional.MantenimientoDiagnostico;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.client.ReloadButton;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.util.client.ClientSettings;

/**
 *
 * Manejador al agregar un desglose de suma asegurada a la factura
 * @author Luis Adrian Gonzalez
 */
public class DesgloseSumaAseguradaGridInternalController extends DefaultGridInternalController {

    private DetalleSiniestro detalleSiniestro;
    private ReloadButton reloadButton;
    private FacturaDetailFrameController vista;

    public DesgloseSumaAseguradaGridInternalController(String classNameModelFullPath,
            String getMethodName, GridControl miGrid,
            FacturaDetailFrameController detailFrameController,
            DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
        this.reloadButton = detailFrameController.getVista().getMainPanel().getReloadButton();
        vista = detailFrameController;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        if (persistentObjects.size() > 0) {
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                s.beginTransaction();
                loadDetalleSiniestro();
                DesgloseSumaAsegurada desgloseSumaAsegurada = ((DesgloseSumaAsegurada) persistentObjects.get(0));
                DesgloseSumaAsegurada oldDesgloseSumaAsegurada = ((DesgloseSumaAsegurada) oldPersistentObjects.get(0));
                String x = logicaNegocio(desgloseSumaAsegurada);
                if (x == null) {
                    DiagnosticoSiniestro ds = (DiagnosticoSiniestro) s.createQuery("FROM " + DiagnosticoSiniestro.class.getName()
                            + " C WHERE C.id=?").setLong(0, desgloseSumaAsegurada.getDiagnosticoSiniestro().getId()).uniqueResult();
                    Response res = pagarDiagnostico(ds,
                            desgloseSumaAsegurada.getMonto()
                            - oldDesgloseSumaAsegurada.getMonto(), s);
                    if (res instanceof ErrorResponse) {
                        return res;
                    }
                } else {
                    return new ErrorResponse(x);
                }
                s.getTransaction().commit();
            } catch (Exception ex) {
                return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecords", ex));
            } finally {
                s.close();
            }
        }
        return super.updateRecords(rowNumbers, oldPersistentObjects, persistentObjects);
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            loadDetalleSiniestro();
            Object object = persistentObjects.get(0);
            DesgloseSumaAsegurada desgloseSumaAsegurada = ((DesgloseSumaAsegurada) object);
            DiagnosticoSiniestro ds = (DiagnosticoSiniestro) s.createQuery("FROM " + DiagnosticoSiniestro.class.getName()
                    + " C WHERE C.id=?").setLong(0, desgloseSumaAsegurada.getDiagnosticoSiniestro().getId()).uniqueResult();
            Response res = pagarDiagnostico(ds,
                    desgloseSumaAsegurada.getMonto() * -1, s);
            if (res instanceof ErrorResponse) {
                return res;
            }
            desgloseSumaAsegurada.setFactura(null);
            s.delete(desgloseSumaAsegurada);
            s.getTransaction().commit();
            return new VOResponse(true);
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecords", ex));
        } finally {
            s.close();
        }

    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Factura factura = (Factura) beanVO;
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            loadDetalleSiniestro();
            DesgloseSumaAsegurada desgloseSumaAsegurada = ((DesgloseSumaAsegurada) newValueObjects.get(0));
            String x = logicaNegocio(desgloseSumaAsegurada);
            if (x == null) {
                desgloseSumaAsegurada.setFactura(factura);
                DiagnosticoSiniestro ds = (DiagnosticoSiniestro) s.createQuery("FROM " + DiagnosticoSiniestro.class.getName()
                        + " C WHERE C.id=?").setLong(0, desgloseSumaAsegurada.getDiagnosticoSiniestro().getId()).uniqueResult();
                Response res = pagarDiagnostico(ds,
                        desgloseSumaAsegurada.getMonto(), s);
                if (res instanceof ErrorResponse) {
                    return res;
                }
                AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
                if (desgloseSumaAsegurada instanceof Auditable) {
                    desgloseSumaAsegurada.setAuditoria(ab);
                }
            } else {
                return new ErrorResponse(x);
            }
            s.save(desgloseSumaAsegurada);
            s.getTransaction().commit();
            List l = new ArrayList(0);
            l.add(desgloseSumaAsegurada);
            return new VOListResponse(l, false, l.size());
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecords", ex));
        } finally {
            s.close();
        }
    }

    private String logicaNegocio(DesgloseSumaAsegurada asegurada) {
        Factura liquidacion = (Factura) beanVO;
        Double liquidado = asegurada.getMonto();
        for (DesgloseSumaAsegurada desgloseSumaAsegurada : liquidacion.getDesgloseSumaAsegurada()) {
            if (asegurada.getId() == null || desgloseSumaAsegurada.getId().compareTo(asegurada.getId()) != 0) {
                if (desgloseSumaAsegurada.getAuditoria().getActivo()) {
                    liquidado += desgloseSumaAsegurada.getMonto();
                }
            }
        }
        if (liquidado > liquidacion.getTotalFacturado()) {
            return "Valor Supera a La Factura";
        }
        if (liquidado < liquidacion.getTotalLiquidado()) {
            return "El total amparado es menor al total liquidado";
        }
        return null;
    }

    /**
     * Actualiza los montos del diagnosticoSiniestro
     * @param diagnosticoSiniestro diagnostico a cambiar
     * @param monto monto actual a guardar
     * @param s session 
     * @return Response de creasion
     */
    private Response pagarDiagnostico(DiagnosticoSiniestro diagnosticoSiniestro, Double monto, Session s) {
        Double montoPendiente = diagnosticoSiniestro.getMontoPendiente(), montoPagado = diagnosticoSiniestro.getMontoPagado();
        montoPendiente -= monto;
        montoPagado += monto;
        MantenimientoDiagnostico mantenimientoDiagnostico = null;
        if (montoPendiente < 0) {
            if (!SuperusuarioLoginDialog.VerificarSuperusuario("Necesita Aumento de Reserva")) {
                return new ErrorResponse("Cancelado por el usuario");
            }
            String nota = JOptionPane.showInputDialog(miGrid,
                    ClientSettings.getInstance().getResources().getResource("Justificacion de Aumento"),
                    "Fondo Auto-Administrado de Salud", JOptionPane.INFORMATION_MESSAGE);
            if (nota != null) {
                mantenimientoDiagnostico = new MantenimientoDiagnostico();
                mantenimientoDiagnostico.setAuditoria(new AuditoriaBasica(new Date(), 
                        General.usuario.getUserName(), Boolean.TRUE));
                mantenimientoDiagnostico.setDiagnosticoSiniestro(diagnosticoSiniestro);
                mantenimientoDiagnostico.setJustificacion(nota);
                mantenimientoDiagnostico.setMontoAnterior(diagnosticoSiniestro.getMontoPendiente());
                mantenimientoDiagnostico.setMontoActual(montoPendiente);
            } else {
                return new ErrorResponse("Cancelado por el usuario");
            }
            montoPendiente = 0d;
        }
        diagnosticoSiniestro.setMontoPagado(montoPagado);
        diagnosticoSiniestro.setMontoPendiente(montoPendiente);
        loadDetalleSiniestro();
        s.update(diagnosticoSiniestro);
        if (mantenimientoDiagnostico != null) {
            s.save(mantenimientoDiagnostico);
        }
        return new VOResponse(diagnosticoSiniestro);
    }

    private void loadDetalleSiniestro() {
        this.detalleSiniestro = vista.getDetalleSiniestro();
    }

    @Override
    public void afterDeleteGrid() {
        reloadButton.doClick();
    }

    @Override
    public void afterEditGrid(GridControl grid) {
        reloadButton.doClick();
    }

    @Override
    public void afterInsertGrid(GridControl grid) {
        reloadButton.doClick();
    }

    @Override
    public void afterReloadGrid() {
        reloadButton.doClick();
    }
}
