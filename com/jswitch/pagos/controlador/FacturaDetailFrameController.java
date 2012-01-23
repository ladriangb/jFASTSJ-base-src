package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.configuracion.modelo.dominio.Cobertura;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.pagos.modelo.transaccional.DesgloseCobertura;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
import com.jswitch.pagos.vista.FacturaDetailFrame;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.util.Collection;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.client.OptionPane;
import org.openswing.swing.client.ReloadButton;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientSettings;
import org.openswing.swing.util.client.ClientUtils;

/**
 * Clase controlodora de la vista de faturas
 * @author Adrian
 */
public class FacturaDetailFrameController extends DefaultDetailFrameController {

    private DetalleSiniestro detalleSiniestro;
    private ReloadButton reload;

    public FacturaDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, DetalleSiniestro detalleSiniestro, Boolean aplicarLogicaNegocio, ReloadButton reloadButton) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        this.detalleSiniestro = detalleSiniestro;
        ((FacturaDetailFrame) vista).createDiagnostocoCodLookup(this.detalleSiniestro);
        checkStatus();
    }

    public FacturaDetailFrameController(String detailFramePath,
            GridControl gridControl, DetalleSiniestro beanVO,
            Boolean aplicarLogicaNegocio, ReloadButton reload) {
        super(detailFramePath, gridControl, (BeanVO) null, aplicarLogicaNegocio);
        this.detalleSiniestro = beanVO;
        ((FacturaDetailFrame) vista).createDiagnostocoCodLookup(detalleSiniestro);
        this.reload = reload;
        checkStatus();
    }

    public DetalleSiniestro getDetalleSiniestro() {
        return detalleSiniestro;
    }

    @Override
    protected Response logicaNegocioConCambioEnVista(ValueObject persistentObject, boolean mostrarMensajeError) {
        Response response = null;
        response = logicaNegocio(persistentObject);
        if (!response.isError()) {
            vista.getMainPanel().getVOModel().setValueObject((ValueObject) ((VOResponse) response).getVo());
            vista.getMainPanel().pull();
        } else if (mostrarMensajeError) {
            OptionPane.showMessageDialog(
                    ClientUtils.getParentFrame(vista.getMainPanel()),
                    ClientSettings.getInstance().getResources().getResource("Error aplicando la logica del negocio:") + "\n"
                    + ClientSettings.getInstance().getResources().getResource(response.getErrorMessage()),
                    ClientSettings.getInstance().getResources().getResource("Error Logica de Negocio"),
                    JOptionPane.WARNING_MESSAGE);
        }
        return response;
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        Response res = super.updateRecord(oldPersistentObject, persistentObject);
        if (res instanceof VOResponse) {
            updateDetalleSiniestro();
        }
        return res;
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Factura liquidacion = (Factura) newPersistentObject;
        liquidacion.setDetalleSiniestro(detalleSiniestro);
        Response res = super.insertRecord(newPersistentObject);
        if (res instanceof VOResponse) {
            detalleSiniestro.getPagos().add(liquidacion);
            updateDetalleSiniestro();
        }
        return res;
    }

    @Override
    public void afterInsertData() {
        vista.getMainPanel().getReloadButton().doClick();
        if (reload != null) {
            reload.doClick();
        }
    }

    @Override
    public void afterEditData() {
        vista.getMainPanel().getReloadButton().doClick();
        if (reload != null) {
            reload.doClick();
        }
    }

    @Override
    public void afterReloadData() {
        if (reload != null) {
            reload.doClick();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Factura sin = (Factura) s.get(Factura.class, ((Factura) beanVO).getId());
        Hibernate.initialize(sin.getDesgloseSumaAsegurada());
        Hibernate.initialize(sin.getDesgloseCobertura());
        s.close();
        beanVO = sin;
        return new VOResponse(beanVO);
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        Factura factura = (Factura) persistentObject;
        factura.setPorcentajeRetencionIsrl(
                factura.getTipoConceptoSeniat().getPorcentajeRetencionIslr());
        Date fF = factura.getFechaFactura();
        Date fR = factura.getFechaRecepcion();
        if (fF.compareTo(fR) > 0) {
            return new ErrorResponse("errorFechaFacturaRecepcion");
        }
        long l = ((fR.getTime() - fF.getTime())
                / 1000 / 60 / 60 / 24);
        if (l > 90) {
            int s = JOptionPane.showConfirmDialog(vista,
                    "Han transcurrido " + l + " dias desde la factura desea Continuar ", "",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (s != JOptionPane.YES_OPTION) {
                return new ErrorResponse("user.aborted");
            }
        }

        Double x = 0d;
        for (DesgloseCobertura li : factura.getDesgloseCobertura()) {
            x += li.getMontoFacturado();
        }
        if (x.doubleValue() > factura.getTotalFacturado().doubleValue()) {
            return new ErrorResponse("monto menor al reflejado");
        }
        x = 0d;
        for (DesgloseSumaAsegurada li : factura.getDesgloseSumaAsegurada()) {
            x += li.getMonto();
        }
        if (x.doubleValue() > factura.getTotalFacturado().doubleValue()) {
            return new ErrorResponse("monto menor al reflejado");
        }
        Double islr = factura.getPorcentajeRetencionIsrl();
        Double montoNoAmparado = 0d;
        Double montoAmparado = 0d;
        Double baseIva = 0d;
        Double baseIslr = 0d;
        Double gastosClinicos = 0d;
        Double honorariosMedicos = 0d;
        Double iva = factura.getPorcentajeIva();

        for (DesgloseCobertura dc : factura.getDesgloseCobertura()) {
            if (dc.getAuditoria().getActivo()) {
                Cobertura c = dc.getCobertura();
                if (c.getBaseImponible()) {
                    montoNoAmparado += dc.getMontoNoAmparado();
                    montoAmparado += dc.getMontoAmparado();
                    if (c.getIva()) {
                        baseIva += dc.getMontoAmparado();
                    }
                    if (c.getIslr()) {
                        baseIslr += dc.getMontoAmparado();
                    }
                    if (c.getGastosClinicos()) {
                        gastosClinicos += dc.getMontoAmparado();
                    }
                    if (c.getHonorariosMedicos()) {
                        honorariosMedicos += dc.getMontoAmparado();
                    }
                }
            }
        }

        factura.setMontoNoAmparado(montoNoAmparado);
        factura.setMontoAmparado(montoAmparado);

        factura.setBaseIva(baseIva);
        factura.setMontoIva(baseIva * iva);
        factura.setMontoRetencionIva(
                factura.getMontoIva() * factura.getPorcentajeRetencionIva());

        factura.setBaseIslr(baseIslr);
        factura.setMontoRetencionIsrl(baseIslr * islr);
        factura.setPorcentajeRetencionIsrl(islr);

        factura.setGastosClinicos(gastosClinicos);
        factura.setHonorariosMedicos(honorariosMedicos);

        factura.setTotalRetenido(
                factura.getMontoRetencionIva() + factura.getMontoRetencionIsrl());

        factura.setTotalLiquidado(
                (baseIva * iva) + (baseIslr * islr) + montoAmparado);

        factura.setTotalACancelar(
                factura.getTotalLiquidado() - factura.getTotalRetenido());

        return new VOResponse(factura);
    }

    public void updateDetalleSiniestro() {
        Session s = null;

        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Collection<Factura> fac = detalleSiniestro.getPagos();
            Double facturado = 0d;
            for (Factura factura : fac) {
                facturado += factura.getTotalFacturado();
            }
            detalleSiniestro.setMontoFacturado(facturado);
            s.beginTransaction();
            s.update(detalleSiniestro);
            s.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            s.close();
        }
    }

    public DefaultDetailFrame getVista() {
        return vista;
    }

    private void checkStatus() {
        DetalleSiniestro ds = detalleSiniestro;
        if (ds.getEtapaSiniestro().getIdPropio().compareTo("ORD_PAG") == 0
                || ds.getEtapaSiniestro().getEstatusSiniestro().getNombre().
                compareTo("PENDIENTE") != 0) {
            ((FacturaDetailFrame) vista).hideAll();
        }
        if (ds.getEtapaSiniestro().getIdPropio().compareTo("LIQ") == 0
                && !General.usuario.getSuperusuario()) {
            ((FacturaDetailFrame) vista).hideAll();
        }
    }
}
