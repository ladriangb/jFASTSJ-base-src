package com.jswitch.configuracion.modelo.maestra;

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
@Table(name = "CONF_ConfiguracionCobertura")
public class ConfiguracionCobertura extends BeanVO implements Serializable, Auditable {

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
     * si es un Gasto clinico
     */
    @Column
    private Boolean gastosClinicos;
    /**
     * booleano que dice si es un gasto por honorarios medicos
     */
    @Column
    private Boolean honorariosMedicos;
    /**
     * booleano que dice si aplica no para la base imponible
     */
    @Column
    private Boolean baseImponible;
    /**
     * aplica IVA
     */
    @Column
    private Boolean iva;
    /**
     * aplica ISLR
     */
    @Column
    private Boolean islr;
    /**
     * version
     */
    @Version
    @Column
    private Integer optLock;
    /**
     * Auditoria
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public ConfiguracionCobertura() {
        islr = Boolean.FALSE;
        iva = Boolean.FALSE;
        baseImponible = Boolean.TRUE;
    }

    /**
     * Auditoria
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * booleano que dice si aplica no para la base imponible
     * @return the baseImponible
     */
    public Boolean getBaseImponible() {
        return baseImponible;
    }

    /**
     * Diagnostico al cual se esta cubriendo
     * @return the cobertura
     */
    public Cobertura getCobertura() {
        return cobertura;
    }

    /**
     * PK autoincremtado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * aplica ISLR
     * @return the islr
     */
    public Boolean getIslr() {
        return islr;
    }

    /**
     * aplica IVA
     * @return the iva
     */
    public Boolean getIva() {
        return iva;
    }

    /**
     * version
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * Auditoria
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * booleano que dice si aplica no para la base imponible
     * @param baseImponible the baseImponible to set
     */
    public void setBaseImponible(Boolean baseImponible) {
        this.baseImponible = baseImponible;
    }

    /**
     * Diagnostico al cual se esta cubriendo
     * @param cobertura the cobertura to set
     */
    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    /**
     * PK autoincremtado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * aplica ISLR
     * @param islr the islr to set
     */
    public void setIslr(Boolean islr) {
        this.islr = islr;
    }

    /**
     * aplica IVA
     * @param iva the iva to set
     */
    public void setIva(Boolean iva) {
        this.iva = iva;
    }

    /**
     * version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * si es un Gasto clinico
     * @return the gastosClinicos
     */
    public Boolean getGastosClinicos() {
        return gastosClinicos;
    }

    /**
     * booleano que dice si es un gasto por honorarios medicos
     * @return the honorariosMedicos
     */
    public Boolean getHonorariosMedicos() {
        return honorariosMedicos;
    }

    /**
     * si es un Gasto clinico
     * @param gastosClinicos the gastosClinicos to set
     */
    public void setGastosClinicos(Boolean gastosClinicos) {
        this.gastosClinicos = gastosClinicos;
    }

    /**
     * booleano que dice si es un gasto por honorarios medicos
     * @param honorariosMedicos the honorariosMedicos to set
     */
    public void setHonorariosMedicos(Boolean honorariosMedicos) {
        this.honorariosMedicos = honorariosMedicos;
    }

    
}
