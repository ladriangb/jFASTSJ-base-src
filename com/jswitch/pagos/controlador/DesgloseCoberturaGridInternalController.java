package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.pagos.modelo.transaccional.DesgloseCobertura;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
import com.jswitch.pagos.vista.FacturaDetailFrame;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;

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

        DesgloseCobertura dc = (DesgloseCobertura) newValueObjects.get(0);
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
        dc.setFactura((Factura) beanVO);
        AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
        dc.setAuditoria(ab);
        ((Factura) beanVO).getDesgloseCobertura().add(dc);
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.save(dc);
            s.getTransaction().commit();
            List l = new ArrayList(0);
            l.add(dc);
            return new VOListResponse(l, false, l.size());
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecords", ex));
        } finally {
            s.close();
        }
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {

        Object object = persistentObjects.get(0);
        DesgloseCobertura desgloseC = ((DesgloseCobertura) object);
        for (Object o : persistentObjects) {
            if (getSet() != null) {
                getSet().remove(o);
            }
        }
        desgloseC.setFactura(null);
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.delete(desgloseC);
            s.getTransaction().commit();
            return new VOResponse(true);
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecords", ex));
        } finally {
            s.close();
        }

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
                ? 0 : (cobertura.getMontoFacturado() * factura.getPorcentajeIva());
        Double amparado = cobertura.getMontoAmparado();
        for (DesgloseCobertura desgloseCobertura : factura.getDesgloseCobertura()) {
            if (cobertura.getId() == null
                    || (desgloseCobertura.getId().compareTo(cobertura.getId()) != 0
                    && desgloseCobertura.getAuditoria().getActivo())) {
                facturado += desgloseCobertura.getMontoFacturado();
                amparado += desgloseCobertura.getMontoAmparado();
                baseIva += !desgloseCobertura.getCobertura().getIva()
                        ? 0 : (desgloseCobertura.getMontoFacturado() * factura.getPorcentajeIva());
            }
        }
        Double totalLiquidado = round2((baseIva) + amparado);
        if (round2((baseIva) + facturado) > factura.getTotalFacturado()) {
            return "Valor Supera a La Factura";
        }
        Double liquidado = 0d;
        for (DesgloseSumaAsegurada desgloseSumaAsegurada : factura.getDesgloseSumaAsegurada()) {
            liquidado += desgloseSumaAsegurada.getMonto();
        }
        if (liquidado < totalLiquidado) {
            return "Cantidad no puede ser amparada\n monto Liquidado " 
                    + totalLiquidado + " > "+" suma Amparada" + liquidado;
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
    
    /**
     * Para el numero dado retorna con dos decimales redondeando
     * 
     * @param number numero a ser redondiado
     * @return numero redondiado
     */
     private  Double round2(Double number) {
        DecimalFormat formatter = new DecimalFormat("#0.00");
        DecimalFormatSymbols s = formatter.getDecimalFormatSymbols();
        s.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(s);
        return Double.parseDouble(formatter.format(number));
    }
}
