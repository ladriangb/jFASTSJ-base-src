package com.jswitch.pagos.modelo.transaccional.lote;

/**
 *
 * @author Adrian
 */
public class Body {

    /**
     * Credito transaccion
     */
    private Credito credito;
    /**
     * Debito transaccion
     */
    private Debito debito;

    public Body() {
        credito = new Credito();
        
        debito = new Debito();
    }

    /**
     * Credito transaccion
     * @return the credito
     */
    public Credito getCredito() {
        return credito;
    }

    /**
     * Debito transaccion
     * @return the debito
     */
    public Debito getDebito() {
        return debito;
    }

    /**
     * Credito transaccion
     * @param credito the credito to set
     */
    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    /**
     * Debito transaccion
     * @param debito the debito to set
     */
    public void setDebito(Debito debito) {
        this.debito = debito;
    }
}