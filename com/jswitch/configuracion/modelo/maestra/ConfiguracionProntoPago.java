package com.jswitch.configuracion.modelo.maestra;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.transaccional.RangoValor;
import com.jswitch.persona.modelo.maestra.Persona;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Configuracion de Convenios con las clinicas
 * @author Adrian
 */
@Entity
@Table(name = "CONF_ConfiguracionProntoPago")
public class ConfiguracionProntoPago extends BeanVO implements Auditable {
    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Persona a la que se le realizara el pago
     */
    @ManyToOne
    @BusinessKey
    private Persona persona;
    /**
     * monto a pagar de timbre municipal
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prontoPago")
    private Set<RangoValor> rangoValor= new HashSet<RangoValor>();
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
    @BusinessKey(exclude={Method.EQUALS, Method.HASH_CODE})
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
     * version
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * Persona a la que se le realizara el pago
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
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
     * version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * Persona a la que se le realizara el pago
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * monto a pagar de timbre municipal
     * @return the rangoValor
     */
    public Set<RangoValor> getRangoValor() {
        return rangoValor;
    }

    /**
     * monto a pagar de timbre municipal
     * @param rangoValor the rangoValor to set
     */
    public void setRangoValor(Set<RangoValor> rangoValor) {
        this.rangoValor = rangoValor;
    }

    
}
