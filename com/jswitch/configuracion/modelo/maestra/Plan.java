package com.jswitch.configuracion.modelo.maestra;

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
     *
     */
    @Column
    @BusinessKey
    private String nombre;
    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plan")
    @BusinessKey(exclude = Method.ALL)
    private Set<SumaAsegurada> sumasAseguradas = new HashSet<SumaAsegurada>(0);
    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plan")
    @BusinessKey(exclude = Method.ALL)
    private Set<SumaAmparada> sumaAmparadas = new HashSet<SumaAmparada>(0);
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

    public Plan() {
    }

    public Plan(String nombre, AuditoriaBasica auditoria) {
        this();
        this.nombre = nombre;
        this.auditoria = auditoria;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<SumaAsegurada> getSumasAseguradas() {
        return sumasAseguradas;
    }

    public void setSumasAseguradas(Set<SumaAsegurada> sumasAseguradas) {
        this.sumasAseguradas = sumasAseguradas;
    }

    public Set<SumaAmparada> getSumaAmparadas() {
        return sumaAmparadas;
    }

    public void setSumaAmparadas(Set<SumaAmparada> sumaAmparadas) {
        this.sumaAmparadas = sumaAmparadas;
    }
}
