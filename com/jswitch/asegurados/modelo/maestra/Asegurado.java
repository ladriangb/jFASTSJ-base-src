package com.jswitch.asegurados.modelo.maestra;

import com.jswitch.asegurados.modelo.dominio.Parentesco;
import com.jswitch.base.modelo.entidades.Documento;
import com.jswitch.base.modelo.entidades.NotaTecnica;
import com.jswitch.base.modelo.entidades.Observacion;
import com.jswitch.configuracion.modelo.maestra.Plan;
import com.jswitch.configuracion.modelo.dominio.PlazoEspera;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.certificados.modelo.maestra.Certificado;
import com.jswitch.persona.modelo.maestra.PersonaNatural;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 *
 * @author Personal
 */
@Entity
@Table(name = "ASEG_Asegurado")
public class Asegurado extends BeanVO implements Serializable, Auditable {

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
    @ManyToOne()
    @BusinessKey
    private Certificado certificado;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private PersonaNatural persona;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private Parentesco parentesco;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private PlazoEspera plazoEspera;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private Plan plan;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @BusinessKey
    private Date fechaIngresoFondo;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @BusinessKey
    private Date fechaEgresoFondo;
    /**
     * lo que aporta la empresa
     */
    @Column
    @BusinessKey
    private Double primaAporte;
    /**
     * lo que aporta el asegurado
     */
    @Column
    @BusinessKey
    private Double primaAsegurado;
    /**
     * prima total
     */
    @Column
    @BusinessKey
    private Double primaTotal;
    /**
     * lo que aporta la empresa
     */
    @Transient
    private Double proRataAporte;
    /**
     * lo que aporta el asegurado
     */
    @Transient
    private Double proRatAsegurado;
    /**
     * prima total
     */
    @Transient
    private Double proRatTotal;
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
    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<Observacion> observaciones = new ArrayList<Observacion>(0);
    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<NotaTecnica> notasTecnicas = new ArrayList<NotaTecnica>(0);
    /**
     * Coleccion de documentos anexos
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Documento> documentos = new HashSet<Documento>(0);

    public Asegurado() {
        primaAporte = 0d;
        primaAsegurado = 0d;
        primaTotal = 0d;
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

    public Date getFechaEgresoFondo() {
        return fechaEgresoFondo;
    }

    public void setFechaEgresoFondo(Date fechaEgresoFondo) {
        this.fechaEgresoFondo = fechaEgresoFondo;
    }

    public Date getFechaIngresoFondo() {
        return fechaIngresoFondo;
    }

    public void setFechaIngresoFondo(Date fechaIngresoFondo) {
        this.fechaIngresoFondo = fechaIngresoFondo;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public PersonaNatural getPersona() {
        return persona;
    }

    public void setPersona(PersonaNatural persona) {
        this.persona = persona;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public PlazoEspera getPlazoEspera() {
        return plazoEspera;
    }

    public void setPlazoEspera(PlazoEspera plazoEspera) {
        this.plazoEspera = plazoEspera;
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

    public Double getProRatAsegurado() {
        return proRatAsegurado;
    }

    public void setProRatAsegurado(Double proRatAsegurado) {
        this.proRatAsegurado = proRatAsegurado;
    }

    public Double getProRatTotal() {
        return proRatTotal;
    }

    public void setProRatTotal(Double proRatTotal) {
        this.proRatTotal = proRatTotal;
    }

    public Double getProRataAporte() {
        return proRataAporte;
    }

    public void setProRataAporte(Double proRataAporte) {
        this.proRataAporte = proRataAporte;
    }

    public Certificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public List<NotaTecnica> getNotasTecnicas() {
        return notasTecnicas;
    }

    public void setNotasTecnicas(List<NotaTecnica> notasTecnicas) {
        this.notasTecnicas = notasTecnicas;
    }

    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }
}
