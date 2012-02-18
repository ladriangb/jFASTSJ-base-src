package com.jswitch.asegurados.modelo.transaccional;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.persona.modelo.maestra.Persona;
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

/**
 *
 * @author Personal
 */
@Entity
@Table(name = "ASEG_Revision")
public class Revision extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Partida por la que se consume
     */
    @ManyToOne
    @BusinessKey
    private Persona personaBuscadora;
    /**
     * Detalle de porque
     */
    @Column
    @BusinessKey
    private String detalle;
    /**
     * Version
     */
    @Version
    @Column
    private Integer optLock;
    /**
     * Bitacora
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public Revision() {
    }

    /**
     * Bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * Detalle de porque
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Version
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * Partida por la que se consume
     * @return the personaBuscadora
     */
    public Persona getPersonaBuscadora() {
        return personaBuscadora;
    }

    /**
     * Bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Detalle de porque
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * Partida por la que se consume
     * @param personaBuscadora the personaBuscadora to set
     */
    public void setPersonaBuscadora(Persona personaBuscadora) {
        this.personaBuscadora = personaBuscadora;
    }
    
}
