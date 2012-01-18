package com.jswitch.pagos.modelo.transaccional.lote;

import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Adrian
 */
public class Header {

    /**
     * Identificador del Registro
     * Valor fijo: HEADER
     */
    private String recordID;
    /**
     * Número referencia del lote
     * Identificación del Lote de Pago. Valor asignado por la empresa.
     * Ej. 00001500
     */
    private Integer numRefLot;
    /**
     * numero negociacion
     * Valor asignado por el Banco.  Será informado por éste a la implantación
     * del servicio.
     * Ej. 00002100
     */
    private Integer numNeg;
    /**
     * R.I.F de la Empresa Ordenante
     * Letra Calificadora (J – Jurídico, G – Gobierno) + Número + Dígito
     * Verificador. El resultado debe ser un número de RIF válido.
     * Debe ser el mismo RIF para todos los registros de Débito de una 
     * Orden de Pago. En caso de que el RIF no tenga los 9 dígitos numéricos,
     * se debe completar con ceros a la izquierda (0).
     * Ej. J001104003.
     */
    private String rif;
    /**
     * Fecha Propuesta de Pago
     * Para uso interno de la empresa a objeto de identificar la fecha 
     * de generación del archivo de pago.
     * Formato: dd/mm/aaaa
     */
    private Date fechaPagoPropuesta;
    /**
     * Fecha de Envío
     * Para uso interno de la empresa, con el objeto de identificar la
     * fecha de envío del archivo de pago. 
     * Formato: dd/mm/aaaa 
     * 
     */
    private Date fechaEnvio;

    /**
     * Fecha de Envío
     * Para uso interno de la empresa, con el objeto de identificar la
     * fecha de envío del archivo de pago.
     * Formato: dd/mm/aaaa
     * @return the fechaEnvio
     */
    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * Fecha Propuesta de Pago
     * Para uso interno de la empresa a objeto de identificar la fecha
     * de generación del archivo de pago.
     * Formato: dd/mm/aaaa
     * @return the fechaPagoPropuesta
     */
    public Date getFechaPagoPropuesta() {
        return fechaPagoPropuesta;
    }

    /**
     * numero negociacion
     * Valor asignado por el Banco.  Será informado por éste a la implantación
     * del servicio.
     * Ej. 00002100
     * @return the numNeg
     */
    public Integer getNumNeg() {
        return numNeg;
    }

    /**
     * Número referencia del lote
     * Identificación del Lote de Pago. Valor asignado por la empresa.
     * Ej. 00001500
     * @return the numRefLot
     */
    public Integer getNumRefLot() {
        return numRefLot;
    }

    /**
     * Identificador del Registro
     * Valor fijo: HEADER
     * @return the recordID
     */
    public String getRecordID() {
        return recordID;
    }

    /**
     * R.I.F de la Empresa Ordenante
     * Letra Calificadora (J – Jurídico, G – Gobierno) + Número + Dígito
     * Verificador. El resultado debe ser un número de RIF válido.
     * Debe ser el mismo RIF para todos los registros de Débito de una
     * Orden de Pago. En caso de que el RIF no tenga los 9 dígitos numéricos,
     * se debe completar con ceros a la izquierda (0).
     * Ej. J001104003.
     * @return the rif
     */
    public String getRif() {
        return rif;
    }

    /**
     * Fecha de Envío
     * Para uso interno de la empresa, con el objeto de identificar la
     * fecha de envío del archivo de pago.
     * Formato: dd/mm/aaaa
     * @param fechaEnvio the fechaEnvio to set
     */
    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    /**
     * Fecha Propuesta de Pago
     * Para uso interno de la empresa a objeto de identificar la fecha
     * de generación del archivo de pago.
     * Formato: dd/mm/aaaa
     * @param fechaPagoPropuesta the fechaPagoPropuesta to set
     */
    public void setFechaPagoPropuesta(Date fechaPagoPropuesta) {
        this.fechaPagoPropuesta = fechaPagoPropuesta;
    }

    /**
     * numero negociacion
     * Valor asignado por el Banco.  Será informado por éste a la implantación
     * del servicio.
     * Ej. 00002100
     * @param numNeg the numNeg to set
     */
    public void setNumNeg(Integer numNeg) {
        this.numNeg = numNeg;
    }

    /**
     * Número referencia del lote
     * Identificación del Lote de Pago. Valor asignado por la empresa.
     * Ej. 00001500
     * @param numRefLot the numRefLot to set
     */
    public void setNumRefLot(Integer numRefLot) {
        this.numRefLot = numRefLot;
    }

    /**
     * Identificador del Registro
     * Valor fijo: HEADER
     * @param recordID the recordID to set
     */
    protected void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    /**
     * R.I.F de la Empresa Ordenante
     * Letra Calificadora (J – Jurídico, G – Gobierno) + Número + Dígito
     * Verificador. El resultado debe ser un número de RIF válido.
     * Debe ser el mismo RIF para todos los registros de Débito de una
     * Orden de Pago. En caso de que el RIF no tenga los 9 dígitos numéricos,
     * se debe completar con ceros a la izquierda (0).
     * Ej. J001104003.
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
}