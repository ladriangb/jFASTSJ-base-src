package com.jswitch.configuracion.modelo.dominio;

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
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Entidad de la partida presupuestaria
 * 
 * @author Luis Adrian Gonzalez Benavides
 */
@Entity
@Table(name = "CONF_PartidaPresupuestaria")
public class PartidaPresupuestaria extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Identificador de la Partida
     */
    @Column
    @BusinessKey
    private String nombre;
    /**
     * Numero de la Partida
     */
    @Column
    @BusinessKey
    private String numero;
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

    public PartidaPresupuestaria() {
    }

    public PartidaPresupuestaria(String nombre, AuditoriaBasica auditoria) {
        this.nombre = nombre;
        this.auditoria = auditoria;
    }

    public PartidaPresupuestaria(String nombre, String numero, AuditoriaBasica auditoria) {
        this.nombre = nombre;
        this.numero = numero;
        this.auditoria = auditoria;
    }

    /**
     * Auditoria
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
     * Identificador de la Partida
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Numero de la Partida
     * @return the numero
     */
    public String getNumero() {
        return numero;
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
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Identificador de la Partida
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Numero de la Partida
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }
    
}
