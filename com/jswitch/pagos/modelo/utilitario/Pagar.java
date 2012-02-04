package com.jswitch.pagos.modelo.utilitario;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.fas.modelo.Dominios.TipoDescuentoProntoPago;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

/**
 * Clase util para saber quien esta pagando 
 * @author Luis Adrian Gonzalez
 */
public class Pagar extends BeanVO {

    /**
     * referencia del pago
     */
    private String referencia;
    /**
     * estatus en que quedan los siniestros pagados
     */
    private EtapaSiniestro etapaSiniestro;
    /**
     * Fecha en que se paga
     */
    private Date fechaDePago;
    /**
     * Tipo Descuento  ProntoPago
     */
    private TipoDescuentoProntoPago tipoDescuentoProntoPago;
    /**
     * monto Descuento ProntoPago
     */
    private Double montoDescuento;
    /**
     * porcentaje Descuento
     */
    private Double porcentajeDescuento;

    public Pagar() {
        tipoDescuentoProntoPago = TipoDescuentoProntoPago.POR_CONVENIO;
        montoDescuento=0d;
        porcentajeDescuento=0d;
    }

    /**
     * estatus en que quedan los siniestros pagados
     * @return the etapaSiniestro
     */
    public EtapaSiniestro getEtapaSiniestro() {
        return etapaSiniestro;
    }

    /**
     * Fecha en que se paga
     * @return the fechaDePago
     */
    public Date getFechaDePago() {
        return fechaDePago;
    }

    /**
     * monto Descuento ProntoPago
     * @return the montoDescuento
     */
    public Double getMontoDescuento() {
        return montoDescuento;
    }

    /**
     * porcentaje Descuento
     * @return the porcentajeDescuento
     */
    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    /**
     * referencia del pago
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Tipo Descuento  ProntoPago
     * @return the tipoDescuentoProntoPago
     */
    public TipoDescuentoProntoPago getTipoDescuentoProntoPago() {
        return tipoDescuentoProntoPago;
    }

    /**
     * estatus en que quedan los siniestros pagados
     * @param etapaSiniestro the etapaSiniestro to set
     */
    public void setEtapaSiniestro(EtapaSiniestro etapaSiniestro) {
        this.etapaSiniestro = etapaSiniestro;
    }

    /**
     * Fecha en que se paga
     * @param fechaDePago the fechaDePago to set
     */
    public void setFechaDePago(Date fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    /**
     * monto Descuento ProntoPago
     * @param montoDescuento the montoDescuento to set
     */
    public void setMontoDescuento(Double montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    /**
     * porcentaje Descuento
     * @param porcentajeDescuento the porcentajeDescuento to set
     */
    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    /**
     * referencia del pago
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * Tipo Descuento  ProntoPago
     * @param tipoDescuentoProntoPago the tipoDescuentoProntoPago to set
     */
    public void setTipoDescuentoProntoPago(TipoDescuentoProntoPago tipoDescuentoProntoPago) {
        this.tipoDescuentoProntoPago = tipoDescuentoProntoPago;
    }

}
