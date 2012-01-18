package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.NotaTecnica;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.client.ReloadButton;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientSettings;

/**
 *
 * @author Adrian
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
        loadDetalleSiniestro(((Factura) beanVO).getDetalleSiniestro());
        DesgloseSumaAsegurada desgloseSumaAsegurada = ((DesgloseSumaAsegurada) persistentObjects.get(0));
        DesgloseSumaAsegurada oldDesgloseSumaAsegurada = ((DesgloseSumaAsegurada) oldPersistentObjects.get(0));
        if (!logicaNegocio(desgloseSumaAsegurada)) {
            for (DiagnosticoSiniestro ds : detalleSiniestro.getDiagnosticoSiniestros()) {
                if (ds.getId().compareTo(desgloseSumaAsegurada.getDiagnosticoSiniestro().getId()) == 0) {
                    Response res = pagarDiagnostico(ds,
                            desgloseSumaAsegurada.getMonto() - oldDesgloseSumaAsegurada.getMonto());
                    if (res instanceof ErrorResponse) {
                        return res;
                    }
                    break;
                }
            }
        } else {
            return new ErrorResponse("Valor Supera a La Factura");
        }
        return super.updateRecords(rowNumbers, oldPersistentObjects, persistentObjects);
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        loadDetalleSiniestro(((Factura) beanVO).getDetalleSiniestro());
        Object object = persistentObjects.get(0);
        DesgloseSumaAsegurada desgloseSumaAsegurada = ((DesgloseSumaAsegurada) object);
        for (DiagnosticoSiniestro ds : detalleSiniestro.getDiagnosticoSiniestros()) {
            if (ds.getId().compareTo(desgloseSumaAsegurada.getDiagnosticoSiniestro().getId()) == 0) {
                Response res = pagarDiagnostico(ds,
                        desgloseSumaAsegurada.getMonto() * -1);
                if (res instanceof ErrorResponse) {
                    return res;
                }
                break;
            }
        }
        return super.deleteRecords(persistentObjects);
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Factura factura = (Factura) beanVO;
        loadDetalleSiniestro(factura.getDetalleSiniestro());
        DesgloseSumaAsegurada desgloseSumaAsegurada = ((DesgloseSumaAsegurada) newValueObjects.get(0));
        if (!logicaNegocio(desgloseSumaAsegurada)) {
            desgloseSumaAsegurada.setFactura(factura);
            for (DiagnosticoSiniestro ds : detalleSiniestro.getDiagnosticoSiniestros()) {
                if (ds.getId().compareTo(desgloseSumaAsegurada.getDiagnosticoSiniestro().getId()) == 0) {
                    Response res = pagarDiagnostico(ds,
                            desgloseSumaAsegurada.getMonto());
                    if (res instanceof ErrorResponse) {
                        return res;
                    }
                    break;
                }
            }
        } else {
            return new ErrorResponse("Valor Supera a La Factura");
        }

        Session s = null;
        if (getSet() != null) {
            for (Object object : newValueObjects) {
                ValueObject o = (ValueObject) object;
                AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
                if (o instanceof Auditable) {
                    ((Auditable) o).setAuditoria(ab);
                }
                Object aux = o.clone();
                //n2.add(aux);
                newValueObjects.remove(o);
                newValueObjects.add(aux);
                getSet().add(aux);
            }
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                s.beginTransaction();
                s.update(getFactura((factura)));
                selectedCell(0, 0, null, (ValueObject) newValueObjects.get(0));
                s.getTransaction().commit();
                return new VOListResponse(newValueObjects, false, newValueObjects.size());
            } catch (Exception ex) {
                for (Object o : newValueObjects) {
                    getSet().remove(o);
                }
                return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecords", ex));
            } finally {
//                miGrid.reloadData();
//                if(miGrid.getReloadButton()!=null)
//                    miGrid.getReloadButton().doClick();
                s.close();
            }
        } else {
            return new ErrorResponse("Primero tienes que guardar el Registro Principal");
        }
    }

    private boolean logicaNegocio(DesgloseSumaAsegurada asegurada) {
        Factura liquidacion = (Factura) beanVO;
        Double liquidado = asegurada.getMonto();
        for (DesgloseSumaAsegurada desgloseSumaAsegurada : liquidacion.getDesgloseSumaAsegurada()) {
            if (asegurada.getId() == null || desgloseSumaAsegurada.getId().compareTo(asegurada.getId()) != 0) {
                if (desgloseSumaAsegurada.getAuditoria().getActivo()) {
                    liquidado += desgloseSumaAsegurada.getMonto();
                }
            }
        }
        return liquidado > liquidacion.getTotalFacturado();
    }

    private Response pagarDiagnostico(DiagnosticoSiniestro diagnosticoSiniestro, Double monto) {

        Session s = null;
//        try {
//            s = HibernateUtil.getSessionFactory().openSession();
//            diagnosticoSiniestro = (DiagnosticoSiniestro) s.get(DiagnosticoSiniestro.class, diagnosticoSiniestro.getId());
//            Hibernate.initialize(diagnosticoSiniestro.getTratamientos());
//        } catch (Exception ex) {
//            System.out.println(ex);
//        } finally {
//            s.close();
//        }

        Double montoPendiente = diagnosticoSiniestro.getMontoPendiente(), montoPagado = diagnosticoSiniestro.getMontoPagado();
        montoPendiente -= monto;
        montoPagado += monto;
        NotaTecnica notaTecnica = null;
        if (montoPendiente < 0) {
            String nota = JOptionPane.showInputDialog(miGrid,
                    ClientSettings.getInstance().getResources().getResource("Justificacion de Aumento"),
                    "Fondo Auto-Administrado de Salud", JOptionPane.INFORMATION_MESSAGE);
            if (nota != null) {
                notaTecnica = new NotaTecnica("Modificacion de monto por: " + nota,
                        new AuditoriaBasica(new Date(), General.usuario.getUserName(), Boolean.TRUE));
            } else {
                return new ErrorResponse("Cancelado por el usuario");
            }
            montoPendiente = 0d;
        }
        diagnosticoSiniestro.setMontoPagado(montoPagado);
        diagnosticoSiniestro.setMontoPendiente(montoPendiente);
        s = null;
        loadDetalleSiniestro(((Factura) beanVO).getDetalleSiniestro());
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.update(diagnosticoSiniestro);
            if (notaTecnica != null) {
                s.save(notaTecnica);
                detalleSiniestro.getNotasTecnicas().add(notaTecnica);
                s.update(detalleSiniestro);
            }
            s.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
//            return new ErrorResponse("no se puede actualizar el DetalleSiniestro");
        } finally {
            s.close();
        }

        return new VOResponse(diagnosticoSiniestro);
    }

    private void loadDetalleSiniestro(DetalleSiniestro detalleSiniestro) {
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

    private Object getFactura(Factura beanVO) {
        return vista.getBeanVO();

    }
}
