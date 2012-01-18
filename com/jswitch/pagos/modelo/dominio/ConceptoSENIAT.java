package com.jswitch.pagos.modelo.dominio;

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
 *
 * @author Personal
 */
@Entity
@Table(name = "CONF_ConceptoSENIAT")
public class ConceptoSENIAT extends BeanVO implements Serializable, Auditable {

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
    private String codigo;
    /**
     *
     */
    @Column
    @BusinessKey
    private String nombre;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double porcentajeRetencionIslr;
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

    public ConceptoSENIAT() {
    }

    public ConceptoSENIAT(String codigo, String nombre,
            Double porcentajeRetencionIslr, AuditoriaBasica auditoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.porcentajeRetencionIslr = porcentajeRetencionIslr;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getPorcentajeRetencionIslr() {
        return porcentajeRetencionIslr;
    }

    public void setPorcentajeRetencionIslr(Double porcentajeRetencionIslr) {
        this.porcentajeRetencionIslr = porcentajeRetencionIslr;
    }
}
