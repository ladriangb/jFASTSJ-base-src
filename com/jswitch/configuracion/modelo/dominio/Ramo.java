package com.jswitch.configuracion.modelo.dominio;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.dominio.patologias.Especialidad;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@Entity
@Table(name="CONF_Ramo")
public class Ramo extends BeanVO implements Serializable, Auditable {

    /**
     *  PK autoincremtado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Nombre 
     */
    @Column(unique = true)
    @Size(min = 1, max = 120)
    @BusinessKey
    private String nombre;
    /**
     * codigo interno para evitar busquedas por Nombre 
     */
    @Column(unique = true)
    @Size(min = 1, max = 120)
    @BusinessKey
    private String idPropio;
    /**
     * Version
     */
    @Version
    @Column
    private Integer optLock;
    /**
     * auditoria bitacora
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;
    /**
     * coberturas del ramo
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ramo")
    @BusinessKey(exclude = Method.ALL)
    private Set<Cobertura> cobertura = new HashSet<Cobertura>(0);
    /**
     * especialidades del ramo
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ramo")
    @BusinessKey(exclude = Method.ALL)
    private Set<Especialidad> especialidades = new HashSet<Especialidad>(0);

    public Ramo() {
    }

    /**
     * auditoria bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * coberturas del ramo
     * @return the cobertura
     */
    public Set<Cobertura> getCobertura() {
        return cobertura;
    }

    /**
     * especialidades del ramo
     * @return the especialidades
     */
    public Set<Especialidad> getEspecialidades() {
        return especialidades;
    }

    /**
     * PK autoincremtado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * codigo interno para evitar busquedas por Nombre
     * @return the idPropio
     */
    public String getIdPropio() {
        return idPropio;
    }

    /**
     * Nombre
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
     * auditoria bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * coberturas del ramo
     * @param cobertura the cobertura to set
     */
    public void setCobertura(Set<Cobertura> cobertura) {
        this.cobertura = cobertura;
    }

    /**
     * especialidades del ramo
     * @param especialidades the especialidades to set
     */
    public void setEspecialidades(Set<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    /**
     * PK autoincremtado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * codigo interno para evitar busquedas por Nombre
     * @param idPropio the idPropio to set
     */
    public void setIdPropio(String idPropio) {
        this.idPropio = idPropio;
    }

    /**
     * Nombre
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

}
