package com.jswitch.asegurados.modelo.maestra;

import com.jswitch.asegurados.modelo.dominio.Parentesco;
import com.jswitch.asegurados.modelo.transaccional.Revision;
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
import com.jswitch.reporte.modelo.Reporte;
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
     * certificado al que pertenece
     */
    @ManyToOne()
    @BusinessKey
    private Certificado certificado;
    /**
     * persona asegurada
     */
    @ManyToOne()
    @BusinessKey
    private PersonaNatural persona;
    /**
     * parentesco con el titular
     */
    @ManyToOne()
    @BusinessKey
    private Parentesco parentesco;
    /**
     * plazo de espera inicial
     */
    @ManyToOne()
    @BusinessKey
    private PlazoEspera plazoEspera;
    /**
     * plan al que pertence
     */
    @ManyToOne()
    @BusinessKey
    private Plan plan;
    /**
     * fecha en que ingreso al fondo 
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @BusinessKey
    private Date fechaIngresoFondo;
    /**
     * fecha en que salio del fondo
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
     * vesion 
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
    /**
     * observaciones
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<Observacion> observaciones = new ArrayList<Observacion>(0);
    /**
     * revisiones a esa persona por parte de otra persona generalmente clinicas
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<Revision> revisiones = new ArrayList<Revision>(0);
    /**
     * notaTecnicas de la empreas
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
    /**
     * reportes especificos
     */
    @Transient
    private transient Set<Reporte> reportes = new HashSet<Reporte>(0);

    public Asegurado() {
        primaAporte = 0d;
        primaAsegurado = 0d;
        primaTotal = 0d;
    }

    /**
     * bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * certificado al que pertenece
     * @return the certificado
     */
    public Certificado getCertificado() {
        return certificado;
    }

    /**
     * Coleccion de documentos anexos
     * @return the documentos
     */
    public Set<Documento> getDocumentos() {
        return documentos;
    }

    /**
     * fecha en que salio del fondo
     * @return the fechaEgresoFondo
     */
    public Date getFechaEgresoFondo() {
        return fechaEgresoFondo;
    }

    /**
     * fecha en que ingreso al fondo
     * @return the fechaIngresoFondo
     */
    public Date getFechaIngresoFondo() {
        return fechaIngresoFondo;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * notaTecnicas de la empreas
     * @return the notasTecnicas
     */
    public List<NotaTecnica> getNotasTecnicas() {
        return notasTecnicas;
    }

    /**
     * observaciones
     * @return the observaciones
     */
    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    /**
     * vesion
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * parentesco con el titular
     * @return the parentesco
     */
    public Parentesco getParentesco() {
        return parentesco;
    }

    /**
     * persona asegurada
     * @return the persona
     */
    public PersonaNatural getPersona() {
        return persona;
    }

    /**
     * plan al que pertence
     * @return the plan
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * plazo de espera inicial
     * @return the plazoEspera
     */
    public PlazoEspera getPlazoEspera() {
        return plazoEspera;
    }

    /**
     * lo que aporta la empresa
     * @return the primaAporte
     */
    public Double getPrimaAporte() {
        return primaAporte;
    }

    /**
     * lo que aporta el asegurado
     * @return the primaAsegurado
     */
    public Double getPrimaAsegurado() {
        return primaAsegurado;
    }

    /**
     * prima total
     * @return the primaTotal
     */
    public Double getPrimaTotal() {
        return primaTotal;
    }

    /**
     * lo que aporta el asegurado
     * @return the proRatAsegurado
     */
    public Double getProRatAsegurado() {
        return proRatAsegurado;
    }

    /**
     * prima total
     * @return the proRatTotal
     */
    public Double getProRatTotal() {
        return proRatTotal;
    }

    /**
     * lo que aporta la empresa
     * @return the proRataAporte
     */
    public Double getProRataAporte() {
        return proRataAporte;
    }

    /**
     * reportes especificos
     * @return the reportes
     */
    public Set<Reporte> getReportes() {
//        if(reportes.isEmptya())
//            reportes.add()
        return reportes;
    }

    /**
     * revisiones a esa persona por parte de otra persona generalmente clinicas
     * @return the revisiones
     */
    public List<Revision> getRevisiones() {
        return revisiones;
    }

    /**
     * bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * certificado al que pertenece
     * @param certificado the certificado to set
     */
    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    /**
     * Coleccion de documentos anexos
     * @param documentos the documentos to set
     */
    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    /**
     * fecha en que salio del fondo
     * @param fechaEgresoFondo the fechaEgresoFondo to set
     */
    public void setFechaEgresoFondo(Date fechaEgresoFondo) {
        this.fechaEgresoFondo = fechaEgresoFondo;
    }

    /**
     * fecha en que ingreso al fondo
     * @param fechaIngresoFondo the fechaIngresoFondo to set
     */
    public void setFechaIngresoFondo(Date fechaIngresoFondo) {
        this.fechaIngresoFondo = fechaIngresoFondo;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * notaTecnicas de la empreas
     * @param notasTecnicas the notasTecnicas to set
     */
    public void setNotasTecnicas(List<NotaTecnica> notasTecnicas) {
        this.notasTecnicas = notasTecnicas;
    }

    /**
     * observaciones
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * vesion
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * parentesco con el titular
     * @param parentesco the parentesco to set
     */
    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    /**
     * persona asegurada
     * @param persona the persona to set
     */
    public void setPersona(PersonaNatural persona) {
        this.persona = persona;
    }

    /**
     * plan al que pertence
     * @param plan the plan to set
     */
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    /**
     * plazo de espera inicial
     * @param plazoEspera the plazoEspera to set
     */
    public void setPlazoEspera(PlazoEspera plazoEspera) {
        this.plazoEspera = plazoEspera;
    }

    /**
     * lo que aporta la empresa
     * @param primaAporte the primaAporte to set
     */
    public void setPrimaAporte(Double primaAporte) {
        this.primaAporte = primaAporte;
    }

    /**
     * lo que aporta el asegurado
     * @param primaAsegurado the primaAsegurado to set
     */
    public void setPrimaAsegurado(Double primaAsegurado) {
        this.primaAsegurado = primaAsegurado;
    }

    /**
     * prima total
     * @param primaTotal the primaTotal to set
     */
    public void setPrimaTotal(Double primaTotal) {
        this.primaTotal = primaTotal;
    }

    /**
     * lo que aporta el asegurado
     * @param proRatAsegurado the proRatAsegurado to set
     */
    public void setProRatAsegurado(Double proRatAsegurado) {
        this.proRatAsegurado = proRatAsegurado;
    }

    /**
     * prima total
     * @param proRatTotal the proRatTotal to set
     */
    public void setProRatTotal(Double proRatTotal) {
        this.proRatTotal = proRatTotal;
    }

    /**
     * lo que aporta la empresa
     * @param proRataAporte the proRataAporte to set
     */
    public void setProRataAporte(Double proRataAporte) {
        this.proRataAporte = proRataAporte;
    }

    /**
     * reportes especificos
     * @param reportes the reportes to set
     */
    public void setReportes(Set<Reporte> reportes) {
        this.reportes = reportes;
    }

    /**
     * revisiones a esa persona por parte de otra persona generalmente clinicas
     * @param revisiones the revisiones to set
     */
    public void setRevisiones(List<Revision> revisiones) {
        this.revisiones = revisiones;
    }

}
