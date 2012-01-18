package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.dominio.Cobertura;
import com.jswitch.configuracion.modelo.maestra.ConfiguracionCobertura;
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

    FacturaDetailFrame vista;

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
            dc.setMontoNoAmparado(dc.getMontoFacturado() - dc.getMontoAmparado());
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
        Double amparado = cobertura.getMontoAmparado();
        for (DesgloseCobertura desgloseCobertura : factura.getDesgloseCobertura()) {
            if (cobertura.getId() == null
                    || (desgloseCobertura.getId().compareTo(cobertura.getId()) != 0 && desgloseCobertura.getAuditoria().getActivo())) {
                facturado += desgloseCobertura.getMontoFacturado();
                amparado += desgloseCobertura.getMontoAmparado();
            }
        }
        if (facturado > factura.getTotalFacturado()) {
            return "Valor Supera a La Factura";
        }
        Double liquidado = 0d;
        for (DesgloseSumaAsegurada desgloseSumaAsegurada : factura.getDesgloseSumaAsegurada()) {
            liquidado += desgloseSumaAsegurada.getMonto();
        }
        if (liquidado < amparado) {
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
        updateFactura((Factura) beanVO);
    }

    /**
     * actualiza los valores de la factura
     * @param  factura 
     */
    private void updateFactura(Factura factura) {
        Double islr = factura.getTipoConceptoSeniat().getPorcentajeRetencionIslr();
        Double montoNoAmparado = 0d;
        Double montoSujeto = 0d;
        Double montoIva = 0d;

        for (DesgloseCobertura dc : factura.getDesgloseCobertura()) {
            if (dc.getAuditoria().getActivo()) {
                ConfiguracionCobertura c = getConfiCober(dc.getCobertura());
                if (c.getBaseImponible()) {
                    double iva = !c.getIva() ? 0
                            : (factura.getPorcentajeIva());
                    double isl = !c.getIslr() ? 0 : islr;
                    montoNoAmparado += dc.getMontoNoAmparado() * (1 - iva) * (1 - isl);
                    montoSujeto += dc.getMontoAmparado() * (1 - iva) * (1 - isl);
                    montoIva += dc.getMontoFacturado() * iva;
                }
            }
        }

        factura.setMontoNoAmparado(montoNoAmparado);
        factura.setMontoSujetoRetencion(montoSujeto);
        factura.setMontoIva(montoIva);

        factura.setMontoRetencionIva(factura.getMontoIva() * factura.getPorcentajeRetencionIva());
        factura.setMontoReteniconIsrl(montoSujeto * islr);

        factura.setTotalRetenido(factura.getMontoRetencionIva() + factura.getMontoReteniconIsrl());
        factura.setTotalLiquidado(montoIva + montoSujeto);
        factura.setTotalACancelar(factura.getTotalLiquidado() - factura.getTotalRetenido());

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

    /**
     * configuracion cobertura para una cobertura
     * @param cobertura
     * @return the ConfiguracionCobertura
     */
    private ConfiguracionCobertura getConfiCober(Cobertura cobertura) {
        Session s = null;
        ConfiguracionCobertura c = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            c = (ConfiguracionCobertura) s.createQuery("FROM " + ConfiguracionCobertura.class.getName() + " C "
                    + "WHERE C.cobertura.id = ?").setLong(0, cobertura.getId()).uniqueResult();

        } catch (Exception e) {
            LoggerUtil.error(DesgloseCoberturaGridInternalController.class,
                    "getConfiCober", e);
        } finally {
            s.close();
        }
        return c;
    }
}
