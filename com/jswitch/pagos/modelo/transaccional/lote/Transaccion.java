package com.jswitch.pagos.modelo.transaccional.lote;

import com.jswitch.base.controlador.General;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.modelo.maestra.Remesa;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.persona.modelo.transac.CuentaBancariaPersona;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;

/**
 *
 * @author Adrian
 */
public class Transaccion {

    /**
     * conjunto de ordenes de pago
     */
    private Remesa remesa;
    /**
     * cabecera del archivo 
     */
    private Header header;
    /**
     * total de transaccion
     */
    private Total total;
    /**
     * lista de movimientos por transaccion
     */
    private List<Body> list = new ArrayList<Body>(0);
    /**
     * Lista con las ordenes de pago q no se pudieron reportar por error
     */
    private Set<Persona> error = new HashSet<Persona>(0);

    public Transaccion(Remesa remesa) {
        this.remesa = remesa;
        header = new Header();
        total = new Total();
        initTransaccion();

    }

    public void printReport(OutputStream stream) {
        PrintStream out = new PrintStream(stream);
        out.format("%8s%08d%08d%10s%5$td/%5$tm/%5$tY%6$td/%6$tm/%6$tY\n",
                header.getRecordID(), header.getNumRefLot(), header.getNumNeg(),
                header.getRif(), header.getFechaPagoPropuesta(), header.getFechaEnvio());
        for (Body body : list) {
            Credito cre = body.getCredito();
            Debito deb = body.getDebito();
            out.format("%1$8s%2$08d%3$10s%4$35s%5$td/%5$tm/%5$tY%6$2s%7$20s%8$015.2f%9$3s%10$2s\n",
                    deb.getRecordID(), deb.getNumRefDeb(), deb.getRif(), deb.getNombre(),
                    deb.getFechaValor(), deb.getTipoCuenta(), deb.getNumCuent(),
                    deb.getMonto(), deb.getMoneda(), deb.getTipoPago());
            out.format("%8s%08d%10s%30s%2s%20s%015.2f%2s%12s",
                    cre.getRecordID(), cre.getNumRefCre(), cre.getRifBen(), cre.getNombre(),
                    cre.getTipoCuenta(), cre.getNumCuent(),
                    cre.getMonto(), cre.getTipoPago(), cre.getBanco());
            if (cre.getTipoPago().equals("20")) {
                out.format("%03d%4s", cre.getDuracionCheq(), cre.getAngenciaBancaria());
            }
            if (cre.getEmail() != null) {
                out.format("%50s", cre.getEmail());
            }
            out.format("\n");
        }
        out.format("%8s%05d%05d%015.2f\n",
                total.getRecordID(), total.getDebitCount(), total.getCreditCount(), total.getTotalMontoLote());
        out.close();


    }

    public void printError(OutputStream stream) {
        PrintStream out = new PrintStream(stream);
        out.println("Personas que no tienen Numero de cuenta Domiciliado:\n");
        for (Persona persona : error) {
            out.println(persona.getRif().getRif() + " " + persona.getNombreLargo());
        }
        out.close();
    }

    public static void main(String[] args) {
        General.empresa.setNombre("TRIBUNAL SUPREMO DE JUSTICIA");
        General.empresa.setRif2("G11000011-1");

        Session s = null;
        Remesa rem = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            rem = (Remesa) s.get(Remesa.class, 57315L);
            Hibernate.initialize(rem.getDocumentos());
            Hibernate.initialize(rem.getNotasTecnicas());
            Hibernate.initialize(rem.getObservaciones());
            Hibernate.initialize(rem.getOrdenDePagos());
        } catch (Exception hibernateException) {
            System.out.println(hibernateException);
            return;
        } finally {
            s.close();
        }
        Transaccion n = new Transaccion(rem);

    }

    private void initTransaccion() {

        Double totalMontoLote = 0d;
        //<editor-fold defaultstate="collapsed" desc="Headers">
        header.setFechaEnvio(remesa.getFechaEnvio());
        header.setFechaPagoPropuesta(remesa.getFechaPropuestaPago());
        header.setNumNeg(remesa.getNumNeg());
        header.setNumRefLot(remesa.getNumRefLot());
        header.setRecordID("HEADER  ");
        header.setRif(General.empresa.getRif2());
        //</editor-fold>

        for (OrdenDePago ordenDePago : remesa.getOrdenDePagos()) {
            //<editor-fold defaultstate="collapsed" desc="download">
            Session s = null;
            Persona personaPago = null;
            CuentaBancariaPersona bancariaPersona = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                personaPago = (Persona) s.get(Persona.class,
                        ordenDePago.getPersonaPago().getId());
                Hibernate.initialize(personaPago.getCuentasBancarias());
                for (CuentaBancariaPersona cuentaBancariaPersona : personaPago.getCuentasBancarias()) {
                    if (cuentaBancariaPersona.getDomicilio()) {
                        bancariaPersona = cuentaBancariaPersona;
                        break;
                    }
                }
            } catch (Exception ex) {
                bancariaPersona = null;
            } finally {
                s.close();
            }
            //</editor-fold>
            if (bancariaPersona != null) {
                //<editor-fold defaultstate="collapsed" desc="Body">
                Body body = new Body();
                //<editor-fold defaultstate="collapsed" desc="Credito">
                body.getCredito().setBanco(bancariaPersona.getBanco().getNombreCorto());
                body.getCredito().setNumCuent(bancariaPersona.getNumero());
                body.getCredito().setTipoCuenta(bancariaPersona.getTipoCuenta().getNumero());
                if (personaPago.getEmail() != null && !personaPago.getEmail().isEmpty()) {
                    body.getCredito().setEmail(personaPago.getEmail());
                }
                body.getCredito().setNombre(personaPago.getNombreCorto());
                body.getCredito().setRifBen(personaPago.getRif().getRif());

                switch (remesa.getTipoPago()) {
                    case ABONO_EN_CUENTA_BANCO_DE_VENEZUELA:
                        body.getCredito().setTipoPago("10");
                        break;
                    case CHEQUE_DE_GERENCIA:
                        body.getCredito().setTipoPago("20");
                        break;
                    case TRANSFERENCIA_SWIFT:
                        body.getCredito().setTipoPago("00");
                        break;
                }

                body.getCredito().setNumRefCre(remesa.getNumRefCre());
                body.getCredito().setMonto(ordenDePago.getMontoPagar());
                body.getCredito().setDuracionCheq(Integer.parseInt(
                        remesa.getDuracionCheque().toString().substring(1)));
                //</editor-fold>
                body.getDebito().setFechaValor(remesa.getFechaValor());
                body.getDebito().setMonto(ordenDePago.getMontoPagar());
                body.getDebito().setNombre(General.empresa.getNombre());
                body.getDebito().setNumCuent(remesa.getNumeroCuentaDebitar());
                body.getDebito().setNumRefDeb(remesa.getNumRefDeb());
                body.getDebito().setRif(header.getRif());
                body.getDebito().setTipoCuenta(remesa.getTipoCuenta().getNumero());
                totalMontoLote += ordenDePago.getMontoPagar();
                list.add(body);
                //</editor-fold>
            } else {
                error.add(ordenDePago.getPersonaPago());
            }
        }
        total.setTotalMontoLote(totalMontoLote);
        total.setCreditCount(list.size());
        total.setDebitCount(list.size());
    }

    public boolean hasError() {
        return !error.isEmpty();
    }
}
