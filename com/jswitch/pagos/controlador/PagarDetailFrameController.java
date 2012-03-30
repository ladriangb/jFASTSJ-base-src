package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.vista.util.ProgressDialog;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.modelo.maestra.Remesa;
import com.jswitch.pagos.modelo.utilitario.Pagar;
import com.jswitch.pagos.vista.PagarDetailFrame;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.hibernate.classic.Session;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 * Pagar Siniestro
 * @author Luis Adrian Gonzalez Benavides
 */
public class PagarDetailFrameController extends DefaultDetailFrameController {

    /**
     * panel a pagar 
     */
    private Form mainPane;
    /**
     * barra de progreso del pago
     */
    private ProgressDialog progress;

    /**
     * 
     * @param mainPane 
     */
    public PagarDetailFrameController(Form mainPane) {
        super(PagarDetailFrame.class.getName(), null, null, false);
        this.mainPane = mainPane;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        vista.getMainPanel().getSaveButton().doClick();
        vista.getMainPanel().setMode(Consts.EDIT);
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
                if (valueObject instanceof Remesa) {
                    Remesa remesa = ((Remesa) valueObject);
                    Long seq = null;
                    try {
                        seq = ((BigInteger) s.createSQLQuery("SELECT nextval('seq_remesa');").uniqueResult()).longValue();
                    } catch (Exception ex) {
                        return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord numero Remesa", ex));
                    }
                    Calendar c = Calendar.getInstance();
                    DecimalFormat nf = new DecimalFormat("00000");
                    SimpleDateFormat df = new SimpleDateFormat("yyMM");
                    remesa.setNumeroRemesa(df.format(c.getTime()) + "-" + nf.format(seq));
                    remesa.setFechaPagado(pagar.getFechaDePago());
                    remesa.setEstatusPago(EstatusPago.PAGADO);
                    s.update(remesa);
                    s.createQuery("UPDATE " + Factura.class.getName() + " C SET "
                            + " C.fechaPagado=:fe "
                            + "WHERE id in ("
                            + "SElECT id FROM " + Factura.class.getName() + " F "
                            + " WHERE F.detalleSiniestro.ordenDePago.remesa=:re"
                            + ")").
                            setEntity("re", remesa).
                            setDate("fe", pagar.getFechaDePago()).executeUpdate();
                    s.createQuery("UPDATE " + DetalleSiniestro.class.getName() + " C SET "
                            + " C.fechaPagado=:fe "
                            + "WHERE id in ("
                            + "SElECT id FROM " + DetalleSiniestro.class.getName() + " F "
                            + " WHERE F.ordenDePago.remesa=:re"
                            + ")").
                            setEntity("re", remesa).
                            setDate("fe", pagar.getFechaDePago()).executeUpdate();
                    s.createQuery("UPDATE " + OrdenDePago.class.getName() + " C SET "
                            + " C.estatusPago=:es,"
                            + " C.fechaPagado=:fe "
                            + "WHERE C.remesa =:re").
                            setString("es", EstatusPago.PAGADO.toString()).
                            setEntity("re", remesa).
                            setDate("fe", pagar.getFechaDePago()).executeUpdate();
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
}
