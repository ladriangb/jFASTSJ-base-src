package com.jswitch.pagos.modelo.transaccional;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.dominio.Cobertura;
import com.jswitch.pagos.modelo.maestra.Factura;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "SINI_DesgloseCobertura")
public class DesgloseCobertura extends BeanVO implements Serializable, Auditable {

    /**
     *  PK autoincremtado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Diagnostico al cual se esta cubriendo
     */
    @ManyToOne
    @BusinessKey
    private Cobertura cobertura;
    /**
     * Factura en la que se agrega el desglose actual
     */
    @ManyToOne
    @BusinessKey
    private Factura factura;
    /**
     * Monto Base q esta en la factura
     */
    @Column
    private Double montoFacturado;
    /**
     * monto base que se esta amparando
     */
    @Column
    private Double montoAmparado;
    /**
     * monto base que no se ampara: {<code>montoNoAmparado=montoFacturado-montoAmparado</code>}
     */
    @Column
    private Double montoNoAmparado;
    /**
     * Texto Explicativo
     */
    @Column
    private String detalle;
    /**
     * version 
     */
    @Version
    @Column
    private Integer optLock;
    /**
     * bitacora
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public DesgloseCobertura() {
    }

    public DesgloseCobertura(Cobertura cobertura, AuditoriaBasica auditoria) {
        this.cobertura = cobertura;
        this.montoFacturado = 0d;
        this.montoAmparado = 0d;
        this.montoNoAmparado = 0d;
        this.detalle = "";
        this.auditoria = auditoria;
    }

    /**
     * bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * Diagnostico al cual se esta cubriendo
     * @return the cobertura
     */
    public Cobertura getCobertura() {
        return cobertura;
    }

    /**
     * Texto Explicativo
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * Factura en la que se agrega el desglose actual
     * @return the factura
     */
    public Factura getFactura() {
        return factura;
    }

    /**
     * PK autoincremtado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * monto base que se esta amparando
     * @return the montoAmparado
     */
    public Double getMontoAmparado() {
        return montoAmparado;
    }

    /**
     * Monto Base q esta en la factura
     * @return the montoFacturado
     */
    public Double getMontoFacturado() {
        return montoFacturado;
    }

    /**
     * monto base que no se ampara: {<code>montoNoAmparado=montoFacturado-montoAmparado</code>}
     * @return the montoNoAmparado
     */
    public Double getMontoNoAmparado() {
        return montoNoAmparado;
    }

    /**
     * version
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Diagnostico al cual se esta cubriendo
     * @param cobertura the cobertura to set
     */
    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    /**
     * Texto Explicativo
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * Factura en la que se agrega el desglose actual
     * @param factura the factura to set
     */
    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    /**
     * PK autoincremtado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * monto base que se esta amparando
     * @param montoAmparado the montoAmparado to set
     */
    public void setMontoAmparado(Double montoAmparado) {
        this.montoAmparado = montoAmparado;
    }

    /**
     * Monto Base q esta en la factura
     * @param montoFacturado the montoFacturado to set
     */
    public void setMontoFacturado(Double montoFacturado) {
        this.montoFacturado = montoFacturado;
    }

    /**
     * monto base que no se ampara: {<code>montoNoAmparado=montoFacturado-montoAmparado</code>}
     * @param montoNoAmparado the montoNoAmparado to set
     */
    public void setMontoNoAmparado(Double montoNoAmparado) {
        this.montoNoAmparado = montoNoAmparado;
    }

    /**
     * version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    
}
