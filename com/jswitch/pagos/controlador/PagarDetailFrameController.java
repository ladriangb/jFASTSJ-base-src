package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.maestra.ConfiguracionProntoPago;
import com.jswitch.configuracion.modelo.maestra.TimbreMunicipal;
import com.jswitch.configuracion.modelo.transaccional.RangoValor;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.modelo.maestra.Remesa;
import com.jswitch.pagos.modelo.utilitario.Pagar;
import com.jswitch.pagos.vista.PagarDetailFrame;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.classic.Session;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 * Pagar Siniestro
 * @author Luis Adrian Gonzalez Benavides
 */
public class PagarDetailFrameController extends DefaultDetailFrameController {

    /**
     * panel a pagar 
     */
    private Form mainPane;

    public PagarDetailFrameController(Form mainPane) {
        super(PagarDetailFrame.class.getName(), null, null, false);
        this.mainPane = mainPane;
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return insertRecord(persistentObject);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        ValueObject valueObject = mainPane.getVOModel().getValueObject();
        Pagar pagar = (Pagar) newPersistentObject;
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            if (valueObject instanceof OrdenDePago) {
                Response response = pagar((OrdenDePago) valueObject, pagar, s);
                if (response.isError()) {
                    return response;
                }
            } else if (valueObject instanceof Remesa) {
                Remesa remesa = ((Remesa) valueObject);
                remesa.setFechaPagado(pagar.getFechaDePago());
                remesa.setEstatusPago(EstatusPago.PAGADO);
                s.update(remesa);
                List<OrdenDePago> list = s.createQuery("FROM " + OrdenDePago.class.getName() + " C "
                        + "WHERE C.remesa.id=?").setLong(0, remesa.getId()).list();
                for (OrdenDePago ordenDePago : list) {
                    Response response = pagar(ordenDePago, pagar, s);
                    if (response.isError()) {
                        return response;
                    }
                }
            }
            s.getTransaction().commit();
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "insertRecord", e);
        } finally {
            s.close();
        }
        mainPane.getReloadButton().doClick();
        vista.setVisible(false);
        vista.dispose();
        return new VOResponse(newPersistentObject);
    }

    /**
     * Para pagar una orden de pago espesifica
     * @param ordenDePago a ser pagado
     * @param pagar parametros de pago
     * @param s session de conexion a Base de Datos
     * @return Respuesta en la creacion del pago
     */
    private Response pagar(OrdenDePago ordenDePago, Pagar pagar, Session s) {
        ordenDePago.setFechaPagado(pagar.getFechaDePago());
        ordenDePago.setReferencia(pagar.getReferencia());
        ordenDePago.setEstatusPago(EstatusPago.PAGADO);
        Double tm = timbreACancelar(ordenDePago, pagar, s);
        tm = round2(ordenDePago.getSumaOrden().getTotalACancelar() * tm);

        
        s.update(ordenDePago);
        List<DetalleSiniestro> list = s.createQuery("FROM " + DetalleSiniestro.class.getName() + " C "
                + "WHERE C.ordenDePago.id=?").setLong(0, ordenDePago.getId()).list();
        for (DetalleSiniestro detalleSiniestro : list) {
            detalleSiniestro.setEtapaSiniestro(pagar.getEtapaSiniestro());
            detalleSiniestro.setFechaPagado(pagar.getFechaDePago());
            s.update(detalleSiniestro);
            List<Factura> lis2 = s.createQuery("FROM " + Factura.class.getName() + " C "
                    + "WHERE C.detalleSiniestro.id=?").setLong(0, detalleSiniestro.getId()).list();
            for (Factura factura : lis2) {
                factura.setFechaPagado(pagar.getFechaDePago());
                factura.setValorUT(General.parametros.get("ut").getValorDouble());
                int cant = diferenciaEnDias(factura.getFechaFactura(), factura.getFechaPagado());
                switch (pagar.getTipoDescuentoProntoPago()) {
                    case POR_CONVENIO:
                        Long val = ordenDePago.getPersonaPago().getId();
                        ConfiguracionProntoPago cpp =
                                (ConfiguracionProntoPago) s.createQuery("FROM "
                                + ConfiguracionProntoPago.class.getName() + " C "
                                + "WHERE C.persona.id=?").setLong(0, val).uniqueResult();
                        if (cpp == null) {
                            return new ErrorResponse("Transacción  Cancelada\nNo existe"
                                    + " convenio con: " + ordenDePago.getPersonaPago().getNombreLargo());
                        }
                        pagar.setMontoDescuento(null);
                        for (RangoValor rangoValor : cpp.getRangoValor()) {
                            if (cant >= rangoValor.getMinValue() && cant <= rangoValor.getMaxValue()) {
                                pagar.setMontoDescuento(rangoValor.getMonto());
                                break;
                            }
                        }
                        if (pagar.getMontoDescuento() == null) {
                            return new ErrorResponse("Transacción  Cancelada\nNo existe"
                                    + " convenio con: " + ordenDePago.getPersonaPago().getNombreLargo()
                                    + "\na los " + cant + " dias");
                        }
                    case POR_MONTO_DESCUENTO:
                        pagar.setPorcentajeDescuento(pagar.getMontoDescuento() / factura.getTotalLiquidado());
                }
                factura.setPorcentajeRetencionProntoPago(pagar.getPorcentajeDescuento());
                s.update(factura);
            }
        }
        return new VOResponse();
    }

    /**
     * 
     * numero de días transcurridos entre entre una fecha y otra
     * @param date1 fecha inicial
     * @param date2 fecha final
     * @return numero de dias entre una fecha y otra
     */
    public int diferenciaEnDias(Date date1, Date date2) {
        java.util.GregorianCalendar dateA = (java.util.GregorianCalendar) Calendar.getInstance();
        java.util.GregorianCalendar dateB = (java.util.GregorianCalendar) Calendar.getInstance();
        dateA.setTime(date1);
        dateB.setTime(date2);
        long difms = Math.abs(dateB.getTimeInMillis() - dateA.getTimeInMillis());
        int difd = (int) (difms / 1000 / 60 / 60 / 24);
        return difd;
    }

    /**
     * calcular el timbre municipal a cancelar por orden de pago
     * @param ordenDePago
     * @param pagar
     * @return timbre municipal a cancelar 
     */
    private Double timbreACancelar(OrdenDePago ordenDePago, Pagar pagar, Session s) {
        int totalUT = (int) (ordenDePago.getSumaOrden().getTotalACancelar()
                / General.parametros.get("ut").getValorDouble());

        TimbreMunicipal tm = pagar.getTimbreMunicipal();
        List<RangoValor> list = s.createQuery("FROM "
                + RangoValor.class.getName() + " R WHERE R.timbreMunicipal.id=?").
                setLong(0, tm.getId()).list();
        for (RangoValor rangoValor : list) {
            if (totalUT >= rangoValor.getMinValue()
                    && totalUT <= rangoValor.getMaxValue()) {
                return rangoValor.getMonto();
            }
        }
        return 0d;
    }

    /**
     * Para el numero dado retorna con dos decimales redondeando
     * @param number numero a ser redondiado
     * @return numero redondiado
     */
    private Double round2(Double number) {
        DecimalFormat formatter = new DecimalFormat("#0.00");
        DecimalFormatSymbols s = formatter.getDecimalFormatSymbols();
        s.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(s);
        return Double.parseDouble(formatter.format(number));
    }
}
