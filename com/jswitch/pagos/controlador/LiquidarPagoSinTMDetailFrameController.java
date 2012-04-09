package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.vista.util.ProgressDialog;
import com.jswitch.configuracion.modelo.maestra.ConfiguracionProntoPago;
import com.jswitch.configuracion.modelo.maestra.TimbreMunicipal;
import com.jswitch.configuracion.modelo.transaccional.RangoValor;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.fas.modelo.Dominios.TipoDescuentoProntoPago;
import com.jswitch.fas.modelo.Dominios.TipoDetalleSiniestro;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.modelo.utilitario.Pagar;
import com.jswitch.pagos.vista.LiquidarPagoDetailFrame;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.awt.event.ActionEvent;
import java.util.List;
import org.hibernate.classic.Session;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 * Pagar Siniestro
 * @author Luis Adrian Gonzalez Benavides
 */
public class LiquidarPagoSinTMDetailFrameController extends DefaultDetailFrameController {

    /**
     * panel a pagar 
     */
    private Form mainPane;
    /**
     * barra de progreso del pago
     */
    private ProgressDialog progress;
    /**
     * valor del progreso
     */
    private int value;
    /**
     * lista de Id de Facturas
     */
    private List<Long> facturas;

    /**
     * 
     * @param mainPane 
     */
    public LiquidarPagoSinTMDetailFrameController(Form mainPane) {
        super(LiquidarPagoDetailFrame.class.getName(), null, null, false);
        this.mainPane = mainPane;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                vista.getMainPanel().getSaveButton().doClick();
                vista.getMainPanel().setMode(Consts.EDIT);
            }
        }).start();
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return insertRecord(persistentObject);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        try {
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
                    if (General.empresa.getSustraendoEnOrden()) {
                        sustraendoACancelar((OrdenDePago) valueObject, s);
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
        } finally {
            if (progress != null) {
                progress.dispose();
            }
        }
    }

    /**
     * Para pagar una orden de pago espesifica
     * @param ordenDePago a ser pagado
     * @param pagar parametros de pago
     * @param s session de conexion a Base de Datos
     * @return Respuesta en la creacion del pago
     */
    private Response pagar(OrdenDePago ordenDePago, Pagar pagar, Session s) {
        if (progress == null) {
            int max = 4;
            if (pagar.getTipoDescuentoProntoPago().equals(TipoDescuentoProntoPago.POR_CONVENIO)) {
                max = 5;
            }
            progress = new ProgressDialog("Pagando", "Pagando Orden n° " + ordenDePago.getNumeroOrden(),
                    max);
            value = 0;

        }
        ordenDePago.setFechaAprobado(pagar.getFechaDePago());
        ordenDePago.setCodigoSIGECOF(pagar.getReferencia());
        ordenDePago.setEstatusPago(EstatusPago.EN_ADMINISTRACION);
        s.update(ordenDePago);
        facturas = s.createQuery("SELECT C.id FROM " + Factura.class.getName() + " C "
                + "WHERE C.detalleSiniestro.ordenDePago.id=?").setLong(0, ordenDePago.getId()).list();
        value++;
        progress.setValue(value);
        if (ordenDePago.getTipoDetalleSiniestro().equals(TipoDetalleSiniestro.Reembolso)) {
            pagarReembolso(ordenDePago, pagar, s);
        } else {
            switch (pagar.getTipoDescuentoProntoPago()) {
                case POR_CONVENIO:
                    pagarConvenio(ordenDePago, pagar, s);
                    break;
                case POR_MONTO_DESCUENTO:
                    pagar.setPorcentajeDescuento(pagar.getMontoDescuento()
                            / ordenDePago.getSumaOrden().getTotalLiquidado());
                case POR_PORCENTAJE_DESCUENTO:
                    pagarConPorcentaje(ordenDePago, pagar, s, pagar.getPorcentajeDescuento());

            }
        }
        progress.setEventoActual("Actualizando Detalles de Siniestro para la orden de pago:"
                + ordenDePago.getNumeroOrden());
        value++;
        progress.setValue(value);
        List<Long> list = s.createQuery("SELECT C.id FROM " + DetalleSiniestro.class.getName() + " C "
                + "WHERE C.ordenDePago.id=?").setLong(0, ordenDePago.getId()).list();

        s.createQuery("UPDATE " + DetalleSiniestro.class.getName()
                + " f SET f.fechaAprobado=:fp,"
                + " f.etapaSiniestro=:ets"
                + " WHERE id in (:li)").
                setDate("fp", pagar.getFechaDePago()).
                setEntity("ets", pagar.getEtapaSiniestro()).
                setParameterList("li", list).
                executeUpdate();

        return new VOResponse();
    }

    /**
     * Paga una serie de facturas especificas de reembolsos donde 
     * Timbre municipal es 0 y descuento pronto Pago es 0
     * @param ordenDePago
     * @param pagar
     * @param s 
     */
    private void pagarReembolso(OrdenDePago ordenDePago, Pagar pagar, Session s) {
        progress.setEventoActual("Actualizando estado de "
                + ordenDePago.getSumaOrden().getCantidadFacturas()
                + " Facturas");
        value++;
        progress.setValue(value);
        s.createQuery("UPDATE " + Factura.class.getName()
                + " f SET f.fechaAprobado=:fp,"
                + " f.valorUT=:ut"
                + " WHERE id in (:li)").
                setDate("fp", pagar.getFechaDePago()).
                setDouble("ut", General.parametros.get("ut").getValorDouble()).
                setParameterList("li", facturas).
                executeUpdate();
    }

    /**
     * Paga una serie de facturas aplicando timbre municipal 
     * y el prontoPago a base de convenios
     * @param ordenDePago
     * @param pagar
     * @param s
    
     */
    private void pagarConvenio(OrdenDePago ordenDePago, Pagar pagar, Session s) {
        progress.setEventoActual("Convenio Pronto pago con " + ordenDePago.getPersonaPago().getNombreCorto());
        value++;
        progress.setValue(value);
        Long val = ordenDePago.getPersonaPago().getId();
        ConfiguracionProntoPago cpp =
                (ConfiguracionProntoPago) s.createQuery("FROM "
                + ConfiguracionProntoPago.class.getName() + " C "
                + "WHERE C.persona.id=?").setLong(0, val).uniqueResult();
        if (cpp == null) {
            pagarConPorcentaje(ordenDePago, pagar, s, 0d);

        } else {
            progress.setEventoActual("Actualizando estado de "
                    + ordenDePago.getSumaOrden().getCantidadFacturas()
                    + " Facturas");
            s.createQuery("UPDATE " + Factura.class.getName()
                    + " AS f SET f.f=:fp,"
                    + " valorUT=:ut,"
                    + " porcentajeRetencionProntoPago=coalesce("
                    + "(SELECT V.monto FROM "
                    + RangoValor.class.getName()
                    + " V "
                    + "WHERE V.prontoPago.id=:pp AND "
                    + "("
                    + "date(:fp)-date(f.fechaFactura)"
                    + ") "
                    + "  BETWEEN V.minValue AND V.maxValue)"
                    + " ,:cero)"
                    + " WHERE id in (:li)").
                    setDate("fp", pagar.getFechaDePago()).
                    setDouble("ut", General.parametros.get("ut").getValorDouble()).
                    setLong("pp", cpp.getId()).
                    setDouble("cero", 0d).
                    setParameterList("li", facturas).
                    executeUpdate();
        }
    }

    /**
     * Paga una serie de facturas aplicando timbre municipal 
     * y el prontoPago directamente
     * @param ordenDePago
     * @param pagar
     * @param s
     */
    private void pagarConPorcentaje(OrdenDePago ordenDePago, Pagar pagar, Session s, Double porcentaje) {
        progress.setEventoActual("Actualizando estado de "
                + ordenDePago.getSumaOrden().getCantidadFacturas()
                + " Facturas");
        value++;
        progress.setValue(value);
        s.createQuery("UPDATE " + Factura.class.getName()
                + " f SET f.fechaAprobado=:fp, "
                + " f.valorUT=:ut,"
                + " f.porcentajeRetencionProntoPago=:pp"
                + " WHERE id in (:li)").
                setDate("fp", pagar.getFechaDePago()).
                setDouble("ut", General.parametros.get("ut").getValorDouble()).
                setDouble("pp", porcentaje).
                setParameterList("li", facturas).
                executeUpdate();
    }

    /**
     * calcular el sustraendo a cancelar por OrdenDePago
     * @param ordenDePago DetalleSiniestro
     * @param s Session
     */
    private void sustraendoACancelar(OrdenDePago ordenDePago, Session s) {
        int aPartirDe = (int) (General.parametros.get("ut").getValorDouble()
                * General.parametros.get("factorISLR").getValorDouble());
        Double sustraendo = 0d;
        String sql = "SELECT SUM(C.baseIslr) FROM " + Factura.class.getName()
                + " C WHERE C.detalleSiniestro.ordenDePago.id=? and C.tipoConceptoSeniat.codigo=?";

        Double baseIslr = (Double) s.createQuery(sql).
                setLong(0, ordenDePago.getId()).
                setString(1, "004").uniqueResult();
        if (baseIslr == null) {
            baseIslr = 0d;
        }
        if (baseIslr >= aPartirDe) {
            String update = "UPDATE "
                    + Factura.class.getName()
                    + " SET sustraendo="
                    + " CAST(baseIslr/:base*:base2*porcentajeRetencionIslr AS big_decimal), "
                    + " montoRetencionIslr="
                    + " (baseIslr*porcentajeRetencionIslr)"
                    + " -CAST(baseIslr/:base*:base2*porcentajeRetencionIslr AS big_decimal)"
                    + " WHERE  id IN (SELECT C.id FROM " + Factura.class.getName()
                    + " C WHERE C.detalleSiniestro.ordenDePago.id=:det and C.tipoConceptoSeniat.codigo=:co)";
            s.beginTransaction();
            s.createQuery(update).
                    setDouble("base", baseIslr).
                    setDouble("base2", aPartirDe).
                    setLong("det", ordenDePago.getId()).
                    setString("co", "004").
                    executeUpdate();
            sql = "SELECT SUM(C.sustraendo),porcentajeRetencionIslr FROM " + Factura.class.getName()
                    + " C WHERE C.detalleSiniestro.ordenDePago.id=? and C.tipoConceptoSeniat.codigo=? GROUP BY"
                    + " porcentajeRetencionIslr";
            Object[] su = (Object[]) s.createQuery(sql).
                    setLong(0, ordenDePago.getId()).
                    setString(1, "004").uniqueResult();
            if (su != null) {
                sustraendo = (Double) su[0] - ((Double) su[1] * aPartirDe);
                if (sustraendo != 0) {
                    sql = "(SELECT C.id FROM " + Factura.class.getName()
                            + " C WHERE C.detalleSiniestro.ordenDePago.id=:det and C.tipoConceptoSeniat.codigo=:co )";
                    Integer id = (Integer) s.createQuery(sql).
                            setLong(0, ordenDePago.getId()).
                            setString(1, "004").setMaxResults(1).uniqueResult();
                    update = "UPDATE "
                            + Factura.class.getName()
                            + " SET sustraendo="
                            + " (sustraendo+:su),"
                            + " montoRetencionIslr=montoRetencionIslr-(sustraendo+:su)"
                            + " WHERE  id = :id";
                    s.createQuery(update).
                            setLong("id", id).
                            setDouble("su", sustraendo).
                            executeUpdate();
                }
            }

            s.getTransaction().commit();
        }

    }
}
