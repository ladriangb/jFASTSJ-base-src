package com.jswitch.siniestros.modelo.transaccional;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author Adrian
 */
@Entity
@Table(name="SINI_MantenimientoDiagnostico")
public class MantenimientoDiagnostico extends BeanVO implements Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double montoAnterior;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double montoActual;
    /**
     *
     */
    @Column
    @BusinessKey
    private String justificacion;
    /**
     * 
     */
    @ManyToOne
    @BusinessKey
    private DiagnosticoSiniestro diagnosticoSiniestro;
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

    public MantenimientoDiagnostico() {
    }

    public MantenimientoDiagnostico(DiagnosticoSiniestro diagnosticoSiniestro) {
        this.diagnosticoSiniestro = diagnosticoSiniestro;
        this.montoAnterior = diagnosticoSiniestro.getMontoPendiente();
    }

    @Override
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    @Override
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public DiagnosticoSiniestro getDiagnosticoSiniestro() {
        return diagnosticoSiniestro;
    }

    public void setDiagnosticoSiniestro(DiagnosticoSiniestro diagnosticoSiniestro) {
        this.diagnosticoSiniestro = diagnosticoSiniestro;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontoActual() {
        return montoActual;
    }

    public void setMontoActual(Double montoActual) {
        this.montoActual = montoActual;
    }

    public Double getMontoAnterior() {
        return montoAnterior;
    }

    public void setMontoAnterior(Double montoAnterior) {
        this.montoAnterior = montoAnterior;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
}
