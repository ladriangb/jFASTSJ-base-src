package com.jswitch.pagos.modelo.utilitario;

import com.jswitch.base.modelo.util.bean.BeanVO;

/**
 * clase para las busquedas de Remesas y Ordenes de Pago
 * @author Adrian
 */
public class BuscarFactura extends BeanVO {
/**
     * Numero de Factura
     */
    private String numeroFactura;
    /**
     * Numero de control de la factura
     */
    private String numeroControl;
    /**
     * mes de pagado
     */
    private Integer mes;
    /**
     * año de pagado
     */
    private Integer ayo;

    public BuscarFactura() {
    }

    /**
     * año de pagado
     * @return the ayo
     */
    public Integer getAyo() {
        return ayo;
    }

    /**
     * mes de pagado
     * @return the mes
     */
    public Integer getMes() {
        return mes;
    }

    /**
     * Numero de control de la factura
     * @return the numeroControl
     */
    public String getNumeroControl() {
        return numeroControl;
    }

    /**
     * Numero de Factura
     * @return the numeroFactura
     */
    public String getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * año de pagado
     * @param ayo the ayo to set
     */
    public void setAyo(Integer ayo) {
        this.ayo = ayo;
    }

    /**
     * mes de pagado
     * @param mes the mes to set
     */
    public void setMes(Integer mes) {
        this.mes = mes;
    }

    /**
     * Numero de control de la factura
     * @param numeroControl the numeroControl to set
     */
    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    /**
     * Numero de Factura
     * @param numeroFactura the numeroFactura to set
     */
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

}
