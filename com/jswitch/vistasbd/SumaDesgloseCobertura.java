package com.jswitch.vistasbd;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.dominio.Cobertura;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Luis Adrian Gonzalez
 */
@Entity
@Table(name = "view_sumadesglosecobertura")
public class SumaDesgloseCobertura extends BeanVO implements Serializable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Detalle al que pertenece la factura
     */
    @ManyToOne
    private DetalleSiniestro detalleSiniestro;
    /**
     * Cobertura afectada
     */
    @ManyToOne
    private Cobertura cobertura;
   /**
     * monto no amparado
     */
    @Column
    @BusinessKey
    private Double montoNoAmparado;
    /**
     * monto amparado
     */
    @Column
    @BusinessKey
    private Double montoAmparado;
    /**
     * monto Facturado
     */
    @Column
    @BusinessKey
    private Double montoFacturado;

    public SumaDesgloseCobertura() {
    }

    /**
     * Cobertura afectada
     * @return the cobertura
     */
    public Cobertura getCobertura() {
        return cobertura;
    }

    /**
     * Detalle al que pertenece la factura
     * @return the detalleSiniestro
     */
    public DetalleSiniestro getDetalleSiniestro() {
        return detalleSiniestro;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * monto amparado
     * @return the montoAmparado
     */
    public Double getMontoAmparado() {
        return montoAmparado;
    }

    /**
     * monto Facturado
     * @return the montoFacturado
     */
    public Double getMontoFacturado() {
        return montoFacturado;
    }

    /**
     * monto no amparado
     * @return the montoNoAmparado
     */
    public Double getMontoNoAmparado() {
        return montoNoAmparado;
    }

    /**
     * Cobertura afectada
     * @param cobertura the cobertura to set
     */
    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    /**
     * Detalle al que pertenece la factura
     * @param detalleSiniestro the detalleSiniestro to set
     */
    public void setDetalleSiniestro(DetalleSiniestro detalleSiniestro) {
        this.detalleSiniestro = detalleSiniestro;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * monto amparado
     * @param montoAmparado the montoAmparado to set
     */
    public void setMontoAmparado(Double montoAmparado) {
        this.montoAmparado = montoAmparado;
    }

    /**
     * monto Facturado
     * @param montoFacturado the montoFacturado to set
     */
    public void setMontoFacturado(Double montoFacturado) {
        this.montoFacturado = montoFacturado;
    }

    /**
     * monto no amparado
     * @param montoNoAmparado the montoNoAmparado to set
     */
    public void setMontoNoAmparado(Double montoNoAmparado) {
        this.montoNoAmparado = montoNoAmparado;
    } 
}
