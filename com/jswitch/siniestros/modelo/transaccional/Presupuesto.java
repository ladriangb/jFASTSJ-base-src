package com.jswitch.siniestros.modelo.transaccional;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.persona.modelo.maestra.Persona;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Past;

/**
 *
 * @author Personal
 */
@Entity
@Table(name="SINI_Presupuesto")
public class Presupuesto extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Persona q se encarga de negociar con la clinica
     */
    @ManyToOne()
    private Persona analistaNegociador;
    /**
     * presupuesto entregado por la clinica
     */
    @Column
    @BusinessKey
    private Double presupuestadoInicial;
    /**
     * Presupuesto ajustado por el Analista
     */
    @Column
    @BusinessKey
    private Double presupuestadoAjustado;
    /**
     * Fecha de Presupuesto
     */ 
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fecha;
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

    public Presupuesto() {
    }

    /**
     * Persona q se encarga de negociar con la clinica
     * @return the analistaNegociador
     */
    public Persona getAnalistaNegociador() {
        return analistaNegociador;
    }

    /**
     * bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * Fecha de Presupuesto
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * version
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * Presupuesto ajustado por el Analista
     * @return the presupuestadoAjustado
     */
    public Double getPresupuestadoAjustado() {
        return presupuestadoAjustado;
    }

    /**
     * presupuesto entregado por la clinica
     * @return the presupuestadoInicial
     */
    public Double getPresupuestadoInicial() {
        return presupuestadoInicial;
    }

    /**
     * Persona q se encarga de negociar con la clinica
     * @param analistaNegociador the analistaNegociador to set
     */
    public void setAnalistaNegociador(Persona analistaNegociador) {
        this.analistaNegociador = analistaNegociador;
    }

    /**
     * bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Fecha de Presupuesto
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * Presupuesto ajustado por el Analista
     * @param presupuestadoAjustado the presupuestadoAjustado to set
     */
    public void setPresupuestadoAjustado(Double presupuestadoAjustado) {
        this.presupuestadoAjustado = presupuestadoAjustado;
    }

    /**
     * presupuesto entregado por la clinica
     * @param presupuestadoInicial the presupuestadoInicial to set
     */
    public void setPresupuestadoInicial(Double presupuestadoInicial) {
        this.presupuestadoInicial = presupuestadoInicial;
    }

}
