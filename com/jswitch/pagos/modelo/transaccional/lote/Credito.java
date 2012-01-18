package com.jswitch.pagos.modelo.transaccional.lote;

import java.util.Arrays;

/**
 *
 * @author Adrian
 */
public class Credito {

    /** 
     * Identificador del Registro
     * Valor fijo: CREDITO
     */
    private String recordID;
    /**
     * numero referencia Credito
     * Número asignado por la empresa que identifica el crédito. Es
     * utilizado para identificar la nota de crédito en el estado de
     * cuenta del beneficiario.
     * Ej. 00000015
     */
    private Integer numRefCre;
    /**
     * Número de Referencia del Crédito
     * Número asignado por la empresa que identifica el crédito. 
     * Es utilizado para identificar la nota de crédito en el estado de
     * cuenta del beneficiario. Ej. 00000015
     */
    private String rifBen;
    /**
     * Nombre del Beneficiario
     * Ej. Juan José Martínez
     */
    private String nombre;
    /**
     * Tipo de cuenta
     * Los valores pueden ser:
     * 00 – Cuenta Corriente
     * 01 – Cuenta de Ahorro
     * Valor por defecto: 00
     */
    private String tipoCuenta;
    /**
     * Número de Cuenta
     * Número de Cuenta del beneficiario (sin caracteres especiales).
     * Ej. 01020501000000000000
     */
    private String numCuent;
    /**
     * Monto 
     * Ej. 100000000000000,00 (*)
     */
    private Double monto;
    /**
     * Tipo de pago
     * Los valores pueden ser:
     * 00 – Transferencia SWIFT
     * 10– Abono en Cuenta Banco de Venezuela
     * 20– Cheque de Gerencia
     */
    private String tipoPago;
    /**
     * Banco 
     * Código Swift del Banco del beneficiario.
     * Ej. BPROVECA
     */
    private String banco;
    /**
     * Duración del Cheque 
     * Cantidad de días de duración:
     *Los valores pueden ser:
     *30, 45, 60, 90, 120 o 180
     */
    private Integer duracionCheq;
    /**
     * Agencia Bancaria
     * Valor fijo: 0501
     */
    private String angenciaBancaria;
    /**
     *  E-mail del beneficiario
     */
    private String email;

    public Credito() {
        recordID = "CREDITO ";
        tipoCuenta = "00";
        angenciaBancaria = "0501";
    }

    /**
     * Agencia Bancaria
     * Valor fijo: 0501
     * @return the angenciaBancaria
     */
    public String getAngenciaBancaria() {
        return angenciaBancaria;
    }

    /**
     * Banco
     * Código Swift del Banco del beneficiario.
     * Ej. BPROVECA
     * @return the banco
     */
    public String getBanco() {
        return banco;
    }

    /**
     * Duración del Cheque
     * Cantidad de días de duración:
     * Los valores pueden ser:
     * 30, 45, 60, 90, 120 o 180
     * @return the duracionCheq
     */
    public Integer getDuracionCheq() {
        return duracionCheq;
    }

    /**
     * E-mail del beneficiario
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Monto
     * Ej. 100000000000000,00 (*)
     * @return the monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * Nombre del Beneficiario
     * Ej. Juan José Martínez
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Número de Cuenta
     * Número de Cuenta del beneficiario (sin caracteres especiales).
     * Ej. 01020501000000000000
     * @return the numCuent
     */
    public String getNumCuent() {
        return numCuent;
    }

    /**
     * numero referencia Credito
     * Número asignado por la empresa que identifica el crédito. Es
     * utilizado para identificar la nota de crédito en el estado de
     * cuenta del beneficiario.
     * Ej. 00000015
     * @return the numRefCre
     */
    public Integer getNumRefCre() {
        return numRefCre;
    }

    /**
     * Identificador del Registro
     * Valor fijo: CREDITO
     * @return the recordID
     */
    public String getRecordID() {
        return recordID;
    }

    /**
     * Número de Referencia del Crédito
     * Número asignado por la empresa que identifica el crédito.
     * Es utilizado para identificar la nota de crédito en el estado de
     * cuenta del beneficiario. Ej. 00000015
     * @return the rifBen
     */
    public String getRifBen() {
        return rifBen;
    }

    /**
     * Tipo de cuenta
     * Los valores pueden ser:
     * 00 – Cuenta Corriente
     * 01 – Cuenta de Ahorro
     * Valor por defecto: 00
     * @return the tipoCuenta
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Tipo de pago
     * Los valores pueden ser:
     * 00 – Transferencia SWIFT
     * 10– Abono en Cuenta Banco de Venezuela
     * 20– Cheque de Gerencia
     * @return the tipoPago
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * Agencia Bancaria
     * Valor fijo: 0501
     * @param angenciaBancaria the angenciaBancaria to set
     */
    public void setAngenciaBancaria(String angenciaBancaria) {
        this.angenciaBancaria = angenciaBancaria;
    }

    /**
     * Banco
     * Código Swift del Banco del beneficiario.
     * Ej. BPROVECA
     * @param banco the banco to set
     */
    public void setBanco(String banco) {
        this.banco = banco;
        if (banco.length() < 12) {
            char[] c = new char[12 - banco.length()];
            Arrays.fill(c, ' ');
            this.banco += new String(c);
        } else {
            this.banco = this.banco.substring(0, 12);
        }
    }

    /**
     * Duración del Cheque
     * Cantidad de días de duración:
     * Los valores pueden ser:
     * 30, 45, 60, 90, 120 o 180
     * @param duracionCheq the duracionCheq to set
     */
    public void setDuracionCheq(Integer duracionCheq) {
        this.duracionCheq = duracionCheq;
    }

    /**
     * E-mail del beneficiario
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
        if (email.length() < 50) {
            char[] c = new char[50 - email.length()];
            Arrays.fill(c, ' ');
            this.email += new String(c);
        } else {
            this.email = this.email.substring(0, 50);
        }
    }

    /**
     * Monto
     * Ej. 100000000000000,00 (*)
     * @param monto the monto to set
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * Nombre del Beneficiario
     * Ej. Juan José Martínez
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
        if (nombre.length() < 30) {
            char[] c = new char[30 - nombre.length()];
            Arrays.fill(c, ' ');
            this.nombre += new String(c);
        } else {
            this.nombre = this.nombre.substring(0, 30);
        }
    }

    /**
     * Número de Cuenta
     * Número de Cuenta del beneficiario (sin caracteres especiales).
     * Ej. 01020501000000000000
     * @param numCuent the numCuent to set
     */
    public void setNumCuent(String numCuent) {
        this.numCuent = numCuent;
    }

    /**
     * numero referencia Credito
     * Número asignado por la empresa que identifica el crédito. Es
     * utilizado para identificar la nota de crédito en el estado de
     * cuenta del beneficiario.
     * Ej. 00000015
     * @param numRefCre the numRefCre to set
     */
    public void setNumRefCre(Integer numRefCre) {
        this.numRefCre = numRefCre;
    }

    /**
     * Identificador del Registro
     * Valor fijo: CREDITO
     * @param recordID the recordID to set
     */
    protected void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    /**
     * Número de Referencia del Crédito
     * Número asignado por la empresa que identifica el crédito.
     * Es utilizado para identificar la nota de crédito en el estado de
     * cuenta del beneficiario. Ej. 00000015
     * @param rifBen the rifBen to set
     */
    public void setRifBen(String rifBen) {
        String rif = rifBen;
        if (!rif.startsWith("J") && !rif.startsWith("G")) {
            int i = rif.lastIndexOf("-");
            if (i > 1) {
                rif = rif.substring(0, i);
            }
        }
        StringBuffer buf = new StringBuffer(rif.replaceAll("-", ""));
        if (buf.length() < 10) {
            char[] c = new char[10 - buf.length()];
            Arrays.fill(c, '0');
            buf.insert(1, c);
        }

        this.rifBen = buf.toString();
    }

    /**
     * Tipo de cuenta
     * Los valores pueden ser:
     * 00 – Cuenta Corriente
     * 01 – Cuenta de Ahorro
     * Valor por defecto: 00
     * @param tipoCuenta the tipoCuenta to set
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * Tipo de pago
     * Los valores pueden ser:
     * 00 – Transferencia SWIFT
     * 10– Abono en Cuenta Banco de Venezuela
     * 20– Cheque de Gerencia
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
}
