package com.jswitch.configuracion.modelo.transaccional;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.maestra.Plan;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Personal
 */
@Entity
@Table(name = "CONF_ConfiguracionSiniestros")
public class ConfiguracionSiniestro extends BeanVO implements Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * nombre Tipo de detalle de Siniestro
     * Emergencia, 
     */
    @Column
    @BusinessKey
    private String tipoDetalle;
    /**
     * monto Deducible para el tipo detalle de Siniestro
     */
    @Column
    private Double montoDeducible;
    /**
     * monto Tope para el Tipo DetalleSiniestro
     */
    @Column
    private Double montoTope;
    /**
     * Plan al que aplica
     */
    @ManyToOne
    @NotNull
    private Plan plan;
    /**
     * version
     */
    @Version
    @Column
    private Integer optLock;
    /**
     * auditoria Bitacora
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public ConfiguracionSiniestro() {
    }

    public ConfiguracionSiniestro(String tipoDetalle, Double porcentajeDeducible, Double montoTope, Plan plan, AuditoriaBasica auditoria) {
        this.tipoDetalle = tipoDetalle;
        this.montoDeducible = porcentajeDeducible;
        this.montoTope = montoTope;
        this.plan = plan;
        this.auditoria = auditoria;
    }

    /**
     * auditoria Bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * monto Tope para el Tipo DetalleSiniestro
     * @return the montoTope
     */
    public Double getMontoTope() {
        return montoTope;
    }

    /**
     * version
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * @return the plan
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * monto Deducible para el tipo detalle de Siniestro
     * @return the montoDeducible
     */
    public Double getMontoDeducible() {
        return montoDeducible;
    }

    /**
     * nombre Tipo de detalle de Siniestro
     * Emergencia,
     * @return the tipoDetalle
     */
    public String getTipoDetalle() {
        return tipoDetalle;
    }

    /**
     * auditoria Bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * monto Tope para el Tipo DetalleSiniestro
     * @param montoTope the montoTope to set
     */
    public void setMontoTope(Double montoTope) {
        this.montoTope = montoTope;
    }

    /**
     * version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * @param plan the plan to set
     */
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    /**
     * monto Deducible para el tipo detalle de Siniestro
     * @param montoDeducible the montoDeducible to set
     */
    public void setMontoDeducible(Double montoDeducible) {
        this.montoDeducible = montoDeducible;
    }

    /**
     * nombre Tipo de detalle de Siniestro
     * Emergencia,
     * @param tipoDetalle the tipoDetalle to set
     */
    public void setTipoDetalle(String tipoDetalle) {
        this.tipoDetalle = tipoDetalle;
    }
}
