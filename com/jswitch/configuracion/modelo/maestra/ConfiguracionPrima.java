package com.jswitch.configuracion.modelo.maestra;

import com.jswitch.asegurados.modelo.dominio.Parentesco;
import com.jswitch.base.modelo.Dominios.Sexo;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name="CONF_ConfiguracionPrima")
public class ConfiguracionPrima extends BeanVO implements Serializable, Auditable {

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
    @ManyToOne
    private Parentesco parentesco;
    /**
     *
     */
    @Column
    private Integer edadDesde;
    /**
     *
     */
    @Column
    private Boolean maternidad;
    /**
     *
     */
    @Column
    private Integer edadHasta;
    /**
     *
     */
    @Column
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    /**
     * lo que aporta la empresa
     */
    @Column
    private Double primaAporte;
    /**
     * lo que aporta el asegurado
     */
    @Column
    private Double primaAsegurado;
    /**
     * prima total
     */
    @Column
    private Double primaTotal;
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

    public ConfiguracionPrima() {
        this.maternidad=false;
        this.primaAporte=0.0;
        this.primaTotal=0.0;
        this.primaAsegurado=0.0;
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

    public Integer getEdadDesde() {
        return edadDesde;
    }

    public void setEdadDesde(Integer edadDesde) {
        this.edadDesde = edadDesde;
    }

    public Integer getEdadHasta() {
        return edadHasta;
    }

    public void setEdadHasta(Integer edadHasta) {
        this.edadHasta = edadHasta;
    }

    public Boolean getMaternidad() {
        return maternidad;
    }

    public void setMaternidad(Boolean maternidad) {
        this.maternidad = maternidad;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public Double getPrimaAporte() {
        return primaAporte;
    }

    public void setPrimaAporte(Double primaAporte) {
        this.primaAporte = primaAporte;
    }

    public Double getPrimaAsegurado() {
        return primaAsegurado;
    }

    public void setPrimaAsegurado(Double primaAsegurado) {
        this.primaAsegurado = primaAsegurado;
    }

    public Double getPrimaTotal() {
        return primaTotal;
    }

    public void setPrimaTotal(Double primaTotal) {
        this.primaTotal = primaTotal;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

}
