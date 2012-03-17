package com.jswitch.pagos.modelo.utilitario;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.fas.modelo.Dominios.TipoPago;
import com.jswitch.persona.modelo.maestra.Persona;
import java.util.Date;

/**
 * clase para las busquedas de Remesas y Ordenes de Pago
 * @author Adrian
 */
public class BuscarPagos extends BeanVO {

    /**
     * fecha en q se pago la remesa
     */
    private Date fechaPagado;
    /**
     * fecha en que se paso a administracion
     */
    private Date fechaAprobado;
    /**
     * cedula o rif de la persona pago
     */
    private String rif;
    /**
     * cedula o nombre de la persona a pagar
     */
    private String nombreLargo;
    /**
     * codigo sigecof asociado
     */
    private String codigoSIGECOF;
    /**
     * numero de Remesa
     */
    private String numeroRemesa;
    /**
     * numero de la orden de pago
     */
    private String numerdoDeOrden;
    /**
     * detalle de la Remesa
     */
    private String detalle;
    /**
     * Persona a la cual se le realiza el pago
     */
    private Persona personaPago;
    /**
     * estatus en que se encuentra el pago
     */
    private EstatusPago estatusPago;
    /**
     * Tipo de Pago 
     */
    private TipoPago tipoPago;

    public BuscarPagos() {
    }

    /**
     * codigo sigecof asociado
     * @return the codigoSIGECOF
     */
    public String getCodigoSIGECOF() {
        return codigoSIGECOF;
    }

    /**
     * detalle de la Remesa
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * estatus en que se encuentra el pago
     * @return the estatusPago
     */
    public EstatusPago getEstatusPago() {
        return estatusPago;
    }

    /**
     * fecha en que se paso a administracion
     * @return the fechaAprobado
     */
    public Date getFechaAprobado() {
        return fechaAprobado;
    }

    /**
     * fecha en q se pago la remesa
     * @return the fechaPagado
     */
    public Date getFechaPagado() {
        return fechaPagado;
    }

    /**
     * cedula o nombre de la persona a pagar
     * @return the nombreLargo
     */
    public String getNombreLargo() {
        return nombreLargo;
    }

    /**
     * numero de la orden de pago
     * @return the numerdoDeOrden
     */
    public String getNumerdoDeOrden() {
        return numerdoDeOrden;
    }

    /**
     * numero de Remesa
     * @return the numeroRemesa
     */
    public String getNumeroRemesa() {
        return numeroRemesa;
    }

    /**
     * Persona a la cual se le realiza el pago
     * @return the personaPago
     */
    public Persona getPersonaPago() {
        return personaPago;
    }

    /**
     * cedula o rif de la persona pago
     * @return the rif
     */
    public String getRif() {
        return rif;
    }

    /**
     * Tipo de Pago
     * @return the tipoPago
     */
    public TipoPago getTipoPago() {
        return tipoPago;
    }

    /**
     * codigo sigecof asociado
     * @param codigoSIGECOF the codigoSIGECOF to set
     */
    public void setCodigoSIGECOF(String codigoSIGECOF) {
        this.codigoSIGECOF = codigoSIGECOF;
    }

    /**
     * detalle de la Remesa
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * estatus en que se encuentra el pago
     * @param estatusPago the estatusPago to set
     */
    public void setEstatusPago(EstatusPago estatusPago) {
        this.estatusPago = estatusPago;
    }

    /**
     * fecha en que se paso a administracion
     * @param fechaAprobado the fechaAprobado to set
     */
    public void setFechaAprobado(Date fechaAprobado) {
        this.fechaAprobado = fechaAprobado;
    }

    /**
     * fecha en q se pago la remesa
     * @param fechaPagado the fechaPagado to set
     */
    public void setFechaPagado(Date fechaPagado) {
        this.fechaPagado = fechaPagado;
    }

    /**
     * cedula o nombre de la persona a pagar
     * @param nombreLargo the nombreLargo to set
     */
    public void setNombreLargo(String nombreLargo) {
        this.nombreLargo = nombreLargo;
    }

    /**
     * numero de la orden de pago
     * @param numerdoDeOrden the numerdoDeOrden to set
     */
    public void setNumerdoDeOrden(String numerdoDeOrden) {
        this.numerdoDeOrden = numerdoDeOrden;
    }

    /**
     * numero de Remesa
     * @param numeroRemesa the numeroRemesa to set
     */
    public void setNumeroRemesa(String numeroRemesa) {
        this.numeroRemesa = numeroRemesa;
    }

    /**
     * Persona a la cual se le realiza el pago
     * @param personaPago the personaPago to set
     */
    public void setPersonaPago(Persona personaPago) {
        this.personaPago = personaPago;
    }

    /**
     * cedula o rif de la persona pago
     * @param rif the rif to set
     */
    public void setRif(String rif) {
        this.rif = rif;
    }

    /**
     * Tipo de Pago
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }
}
