package com.jswitch.configuracion.modelo.maestra;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.transaccional.RangoValor;
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
 * Tabla de configuracion de timbre municipal por zona postal
 * @author Adrian
 */
@Entity
@Table(name = "CONF_TimbreMunicipal")
public class TimbreMunicipal extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * codigo Postal
     */
    @Column
    @BusinessKey
    private String zipCode;
    /**
     * codigo Postal
     */
    @Column
    @BusinessKey
    private String nombre;
    /**
     * Rango de Valores minimo
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "timbreMunicipal")
    @BusinessKey(exclude = Method.ALL)
    private Set<RangoValor> rangoValor = new HashSet<RangoValor>();
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
     * codigo Postal
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
     * Rango de Valores minimo
     * @return the rangoValor
     */
    public Set<RangoValor> getRangoValor() {
        return rangoValor;
    }

    /**
     * codigo Postal
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
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
     * codigo Postal
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
     * Rango de Valores minimo
     * @param rangoValor the rangoValor to set
     */
    public void setRangoValor(Set<RangoValor> rangoValor) {
        this.rangoValor = rangoValor;
    }

    /**
     * codigo Postal
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
