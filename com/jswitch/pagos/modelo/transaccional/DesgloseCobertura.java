package com.jswitch.pagos.modelo.transaccional;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.dominio.Cobertura;
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
     * 
     */
    @Column
    private Double montoFacturado;
    /**
     *
     */
    @Column
    private Double montoAmparado;
    /**
     * 
     */
    @Column
    private Double montoNoAmparado;
    /**
     *
     */
    @Column
    private String detalle;
    /**
     */
    @Version
    @Column
    private Integer optLock;
    /**
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

    @Override
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    @Override
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Double getMontoAmparado() {
        return montoAmparado;
    }

    public void setMontoAmparado(Double montoAmaparado) {
        this.montoAmparado = montoAmaparado;
    }

    public Double getMontoFacturado() {
        return montoFacturado;
    }

    public void setMontoFacturado(Double montoFacturado) {
        this.montoFacturado = montoFacturado;
    }

    public Double getMontoNoAmparado() {
        return montoNoAmparado;
    }

    public void setMontoNoAmparado(Double montoNoAmparado) {
        this.montoNoAmparado = montoNoAmparado;
    }
}
