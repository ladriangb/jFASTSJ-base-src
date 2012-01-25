package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.dominio.Cobertura;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.pagos.modelo.transaccional.DesgloseCobertura;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
import com.jswitch.pagos.vista.FacturaDetailFrame;
import java.util.ArrayList;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;

/**
 * 
 * @author Adrian
 */
public class DesgloseCoberturaGridInternalController extends DefaultGridInternalController {

    private FacturaDetailFrame vista;

    public DesgloseCoberturaGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, FacturaDetailFrame vista, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
        this.vista = vista;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        for (Object object : persistentObjects) {
            DesgloseCobertura dc = (DesgloseCobertura) object;
            if (dc.getMontoAmparado() == null) {
                dc.setMontoAmparado(dc.getMontoFacturado());
            }
            dc.setMontoNoAmparado(dc.getMontoFacturado() - dc.getMontoAmparado());
            if (dc.getMontoAmparado() > dc.getMontoFacturado()) {
                return new ErrorResponse("monto amparado mayor al monto facturado");
            }
            String logica = logicaNegocio(dc);
            if (logica != null) {
                return new ErrorResponse(logica);
            }
        }
        return super.updateRecords(rowNumbers, oldPersistentObjects, persistentObjects);
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        for (Object object : newValueObjects) {
            DesgloseCobertura dc = (DesgloseCobertura) object;
            if (dc.getMontoAmparado() == null) {
                dc.setMontoAmparado(dc.getMontoFacturado());
            }
            dc.setMontoNoAmparado(dc.getMontoFacturado() - dc.getMontoAmparado());
            if (dc.getMontoAmparado() > dc.getMontoFacturado()) {
                return new ErrorResponse("No se puede amparar mas del monto facturado");
            }
            String logica = logicaNegocio(dc);
            if (logica != null) {
                return new ErrorResponse(logica);
            }
        }
        return super.insertRecords(rowNumbers, newValueObjects);
    }

    /**
     * logica de negocios del sistema
     * @param cobertura
     * @return boolean si falla la verificacion 
     */
    private String logicaNegocio(DesgloseCobertura cobertura) {
        Factura factura = (Factura) beanVO;
        Double facturado = cobertura.getMontoFacturado();
        Double baseIva = !cobertura.getCobertura().getIva()
                ? 0 : (cobertura.getMontoAmparado() * factura.getPorcentajeIva());
        Double baseIslr = !cobertura.getCobertura().getIslr()
                ? 0 : (cobertura.getMontoAmparado() * factura.getTipoConceptoSeniat().getPorcentajeRetencionIslr());
        Double amparado = cobertura.getMontoAmparado();
        for (DesgloseCobertura desgloseCobertura : factura.getDesgloseCobertura()) {
            if (cobertura.getId() == null
                    || (desgloseCobertura.getId().compareTo(cobertura.getId()) != 0
                    && desgloseCobertura.getAuditoria().getActivo())) {
                facturado += desgloseCobertura.getMontoFacturado();
                amparado += desgloseCobertura.getMontoAmparado();
                baseIva += !desgloseCobertura.getCobertura().getIva()
                        ? 0 : (desgloseCobertura.getMontoAmparado() * factura.getPorcentajeIva());
                baseIslr += !desgloseCobertura.getCobertura().getIslr()
                        ? 0 : (desgloseCobertura.getMontoAmparado() * factura.getTipoConceptoSeniat().getPorcentajeRetencionIslr());
            }
        }
        Double totalLiquidado = ((baseIva) + (baseIslr) + amparado);
        if (facturado > factura.getTotalFacturado()) {
            return "Valor Supera a La Factura";
        }
        Double liquidado = 0d;
        for (DesgloseSumaAsegurada desgloseSumaAsegurada : factura.getDesgloseSumaAsegurada()) {
            liquidado += desgloseSumaAsegurada.getMonto();
        }
        if (liquidado < totalLiquidado) {
            return "Cantidad no puede ser amparada";
        }
        return null;
    }

    @Override
    public void afterDeleteGrid() {
        updateFactura((Factura) beanVO);
    }

    @Override
    public void afterInsertGrid(GridControl grid) {
        updateFactura((Factura) beanVO);
    }

    @Override
    public void afterEditGrid(GridControl grid) {
        updateFactura((Factura) beanVO);
    }

    @Override
    public void afterReloadGrid() {
        vista.getMainPanel().getReloadButton().doClick();
    }

    /**
     * actualiza los valores de la factura
     * @param  factura 
     */
    private void updateFactura(Factura fact) {
        Factura factura = fact;
        factura = ((FacturaDetailFrameController) vista.getMainPanel().
                getFormController()).updateFactura(factura);
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.update(factura);
            s.getTransaction().commit();
        } catch (Exception e) {
            LoggerUtil.error(DesgloseCoberturaGridInternalController.class,
                    "updateFactura", e);
        } finally {
            s.close();
        }
        vista.getMainPanel().getReloadButton().doClick();
    }
}
