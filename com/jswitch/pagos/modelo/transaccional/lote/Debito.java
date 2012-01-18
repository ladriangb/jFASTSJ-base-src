/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.pagos.modelo.transaccional.lote;

import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Adrian
 */
public class Debito {

    /**
     * Identificador del Registro
     * Valor fijo: DEBITO
     */
    private String recordID;
    /**
     * Número de Referencia del Debito
     * Número asignado por la empresa que identifica al débito. Es utilizado
     * para reconocer la Nota de Débito en el Estado de Cuenta del ordenante.
     * Ej. 00000015
     */
    private Integer numRefDeb;
    /**
     * R.I.F de la Empresa Ordenante
     * Letra Calificadora (J – Jurídico, G – Gobierno) + Número + 
     * Dígito Verificador. El resultado debe ser un número de RIF válido.
     * Debe ser el mismo RIF para todos los registros de Débito de una
     * Orden de Pago. En caso de que el RIF no tenga los 9 dígitos numéricos,
     * se debe completar con ceros a la izquierda (0). Ej. J001104003.
     */
    private String rif;
    /**
     * Nombre del Ordenante
     * Ej. Consorcio ABC, C.A.
     */
    private String nombre;
    /**
     * Fecha Valor
     * Fecha efectiva del Débito.
     * dd/mm/aaaa
     */
    private Date fechaValor;
    /**
     * tipo cuenta
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
     * moneda
     * Valor Fijo: VEF
     */
    private String moneda;
    /**
     * Tipo de Pago
     * Valor:
     * 40 – Proveedores
     */
    private String tipoPago;

    public Debito() {
        recordID = "DEBITO  ";
        moneda = "VEF";
        tipoCuenta = "00";
        tipoPago = "40";
    }

    /**
     * Fecha Valor
     * Fecha efectiva del Débito.
     * dd/mm/aaaa
     * @return the fechaValor
     */
    public Date getFechaValor() {
        return fechaValor;
    }

    /**
     * moneda
     * Valor Fijo: VEF
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
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
     * Número de Referencia del Debito
     * Número asignado por la empresa que identifica al débito. Es utilizado
     * para reconocer la Nota de Débito en el Estado de Cuenta del ordenante.
     * Ej. 00000015
     * @return the numRefDeb
     */
    public Integer getNumRefDeb() {
        return numRefDeb;
    }

    /**
     * Identificador del Registro
     * Valor fijo: DEBITO
     * @return the recordID
     */
    public String getRecordID() {
        return recordID;
    }

    /**
     * R.I.F de la Empresa Ordenante
     * Letra Calificadora (J – Jurídico, G – Gobierno) + Número +
     * Dígito Verificador. El resultado debe ser un número de RIF válido.
     * Debe ser el mismo RIF para todos los registros de Débito de una
     * Orden de Pago. En caso de que el RIF no tenga los 9 dígitos numéricos,
     * se debe completar con ceros a la izquierda (0). Ej. J001104003.
     * @return the rif
     */
    public String getRif() {
        return rif;
    }

    /**
     * tipo cuenta
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
     * Tipo de Pago
     * Valor:
     * 40 – Proveedores
     * @return the tipoPago
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * Fecha Valor
     * Fecha efectiva del Débito.
     * dd/mm/aaaa
     * @param fechaValor the fechaValor to set
     */
    public void setFechaValor(Date fechaValor) {
        this.fechaValor = fechaValor;
    }

    /**
     * moneda
     * Valor Fijo: VEF
     * @param moneda the moneda to set
     */
    protected void setMoneda(String moneda) {
        this.moneda = moneda;
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
        if (nombre.length() < 35) {
            char[] c = new char[35 - nombre.length()];
            Arrays.fill(c, ' ');
            this.nombre += new String(c);
        } else {
            this.nombre = this.nombre.substring(0, 35);
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
     * Número de Referencia del Debito
     * Número asignado por la empresa que identifica al débito. Es utilizado
     * para reconocer la Nota de Débito en el Estado de Cuenta del ordenante.
     * Ej. 00000015
     * @param numRefDeb the numRefDeb to set
     */
    public void setNumRefDeb(Integer numRefDeb) {
        this.numRefDeb = numRefDeb;
    }

    /**
     * Identificador del Registro
     * Valor fijo: DEBITO
     * @param recordID the recordID to set
     */
    protected void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    /**
     * R.I.F de la Empresa Ordenante
     * Letra Calificadora (J – Jurídico, G – Gobierno) + Número +
     * Dígito Verificador. El resultado debe ser un número de RIF válido.
     * Debe ser el mismo RIF para todos los registros de Débito de una
     * Orden de Pago. En caso de que el RIF no tenga los 9 dígitos numéricos,
     * se debe completar con ceros a la izquierda (0). Ej. J001104003.
     * @param rif the rif to set
     */
    public void setRif(String rif) {

        StringBuffer buf = new StringBuffer(rif.replaceAll("-", ""));
        if (buf.length() < 10) {
            char[] c = new char[10 - buf.length()];
            Arrays.fill(c, '0');
            buf.insert(1, c);
        }

        this.rif = buf.toString();
    }

    /**
     * tipo cuenta
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
     * Tipo de Pago
     * Valor:
     * 40 – Proveedores
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
}
