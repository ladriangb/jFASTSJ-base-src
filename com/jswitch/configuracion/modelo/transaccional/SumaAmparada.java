package com.jswitch.configuracion.modelo.transaccional;

import com.jswitch.configuracion.modelo.maestra.Plan;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CONF_SumaAmparada")
public class SumaAmparada extends BeanVO implements Serializable, Auditable {

    /**
     *  PK autoincremtado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Diagnostico al cual se esta cubriendo.
     */
    @Column
    @BusinessKey
    private String nombre;
    /**
     * monto amparado
     */
    @Column
    private Double monto;
    /**
     * Plan al que esta adjunto
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
     * bitacora
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public SumaAmparada() {
    }

    /**
     * bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * PK autoincremtado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * monto amparado
     * @return the monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * Diagnostico al cual se esta cubriendo.
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * version
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * Plan al que esta adjunto
     * @return the plan
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * PK autoincremtado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * monto amparado
     * @param monto the monto to set
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * Diagnostico al cual se esta cubriendo.
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * Plan al que esta adjunto
     * @param plan the plan to set
     */
    public void setPlan(Plan plan) {
        this.plan = plan;
    }   
}
