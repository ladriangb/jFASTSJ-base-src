package com.jswitch.configuracion.modelo.maestra;

import com.jswitch.configuracion.modelo.transaccional.ConfiguracionSiniestro;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.transaccional.SumaAmparada;
import com.jswitch.configuracion.modelo.transaccional.SumaAsegurada;
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

/**
 *
 * @author Adrian
 */
@Entity
@Table(name = "ASEG_Plan")
public class Plan extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * nombre del plan
     */
    @Column
    @BusinessKey
    private String nombre;
    /**
     * suma Asegurada Diagnostico
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plan")
    @BusinessKey(exclude = Method.ALL)
    private Set<SumaAsegurada> sumasAseguradas = new HashSet<SumaAsegurada>(0);
    /**
     * Configuracion de Valores por tipo de Siniestro
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plan")
    @BusinessKey(exclude = Method.ALL)
    private Set<ConfiguracionSiniestro> configuracionSiniestros = new HashSet<ConfiguracionSiniestro>(0);
    /**
     * Suma Amparada valor
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plan")
    @BusinessKey(exclude = Method.ALL)
    private Set<SumaAmparada> sumaAmparadas = new HashSet<SumaAmparada>(0);
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

    public Plan() {
    }

    public Plan(String nombre, AuditoriaBasica auditoria) {
        this();
        this.nombre = nombre;
        this.auditoria = auditoria;

    }

    /**
     * bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * Configuracion de Valores por tipo de Siniestro
     * @return the configuracionSiniestros
     */
    public Set<ConfiguracionSiniestro> getConfiguracionSiniestros() {
        return configuracionSiniestros;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * nombre del plan
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
     * Suma Amparada valor
     * @return the sumaAmparadas
     */
    public Set<SumaAmparada> getSumaAmparadas() {
        return sumaAmparadas;
    }

    /**
     * suma Asegurada Diagnostico
     * @return the sumasAseguradas
     */
    public Set<SumaAsegurada> getSumasAseguradas() {
        return sumasAseguradas;
    }

    /**
     * bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Configuracion de Valores por tipo de Siniestro
     * @param configuracionSiniestros the configuracionSiniestros to set
     */
    public void setConfiguracionSiniestros(Set<ConfiguracionSiniestro> configuracionSiniestros) {
        this.configuracionSiniestros = configuracionSiniestros;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * nombre del plan
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
     * Suma Amparada valor
     * @param sumaAmparadas the sumaAmparadas to set
     */
    public void setSumaAmparadas(Set<SumaAmparada> sumaAmparadas) {
        this.sumaAmparadas = sumaAmparadas;
    }

    /**
     * suma Asegurada Diagnostico
     * @param sumasAseguradas the sumasAseguradas to set
     */
    public void setSumasAseguradas(Set<SumaAsegurada> sumasAseguradas) {
        this.sumasAseguradas = sumasAseguradas;
    }

}
