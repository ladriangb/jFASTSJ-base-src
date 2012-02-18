package com.jswitch.asegurados.modelo.dominio;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.dominio.PartidaPresupuestaria;
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
@Table(name = "ASEG_TipoContrato")
public class TipoContrato extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Identificador de tipo de Contrato
     */
    @Column
    @BusinessKey
    private String nombre;
    /**
     * Partida por la que se consume
     */
    @ManyToOne
    @BusinessKey
    private PartidaPresupuestaria partidaPresupuestaria;
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

    public TipoContrato() {
    }

    public TipoContrato(String nombre, AuditoriaBasica auditoria) {
        this.nombre = nombre;
        this.auditoria = auditoria;
    }

    /**
     * Bitacora
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
     * Identificador de tipo de Contrato
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
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
     * @return the partidaPresupuestaria
     */
    public PartidaPresupuestaria getPartidaPresupuestaria() {
        return partidaPresupuestaria;
    }

    /**
     * Bitacora
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
     * Identificador de tipo de Contrato
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @param partidaPresupuestaria the partidaPresupuestaria to set
     */
    public void setPartidaPresupuestaria(PartidaPresupuestaria partidaPresupuestaria) {
        this.partidaPresupuestaria = partidaPresupuestaria;
    }

    
}
