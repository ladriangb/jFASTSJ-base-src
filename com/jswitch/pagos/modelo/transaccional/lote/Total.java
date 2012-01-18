/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.pagos.modelo.transaccional.lote;

/**
 *
 * @author Adrian
 */
public class Total {

    /**
     * Identificador del Registro
     * Valor fijo: TOTAL
     */
    private String recordID;
    /**
     * Cantidad de Instrucciones de Débitos
     */
    private Integer debitCount;
    /**
     * Cantidad de Instrucciones de Créditos
     */
    private Integer creditCount;
    /**
     * Total monto lote
     */
    private Double totalMontoLote;

    public Total() {
        recordID = "TOTAL   ";
        creditCount = 0;
        debitCount = 0;
        totalMontoLote = 0d;
    }

    /**
     * Cantidad de Instrucciones de Créditos
     * @return the creditCount
     */
    public Integer getCreditCount() {
        return creditCount;
    }

    /**
     * Cantidad de Instrucciones de Débitos
     * @return the debitCount
     */
    public Integer getDebitCount() {
        return debitCount;
    }

    /**
     * Identificador del Registro
     * Valor fijo: TOTAL
     * @return the recordID
     */
    public String getRecordID() {
        return recordID;
    }

    /**
     * Total monto lote
     * @return the totalMontoLote
     */
    public Double getTotalMontoLote() {
        return totalMontoLote;
    }

    /**
     * Cantidad de Instrucciones de Créditos
     * @param creditCount the creditCount to set
     */
    public void setCreditCount(Integer creditCount) {
        this.creditCount = creditCount;
    }

    /**
     * Cantidad de Instrucciones de Débitos
     * @param debitCount the debitCount to set
     */
    public void setDebitCount(Integer debitCount) {
        this.debitCount = debitCount;
    }

    /**
     * Identificador del Registro
     * Valor fijo: TOTAL
     * @param recordID the recordID to set
     */
    protected void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    /**
     * Total monto lote
     * @param totalMontoLote the totalMontoLote to set
     */
    public void setTotalMontoLote(Double totalMontoLote) {
        this.totalMontoLote = totalMontoLote;
    }
}