package com.jswitch.certificados.modelo.maestra;

import com.jswitch.asegurados.modelo.maestra.Beneficiario;
import com.jswitch.asegurados.modelo.maestra.Titular;
import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.base.modelo.Dominios;
import com.jswitch.base.modelo.entidades.Documento;
import com.jswitch.base.modelo.entidades.NotaTecnica;
import com.jswitch.base.modelo.entidades.Observacion;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.polizas.modelo.maestra.Poliza;
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
import javax.persistence.ManyToMany;
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
@Table(name = "ASEG_Certificado")
public class Certificado extends BeanVO implements Serializable, Auditable {

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
    private Titular titular;
    /**
     */
    @ManyToOne
    @BusinessKey
    private Poliza poliza;
    /**
     * HCM
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "certificado")
    @BusinessKey(exclude = Method.ALL)
    private Set<Asegurado> asegurados = new HashSet<Asegurado>(0);
    /**
     * Vida
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "certificado")
    @BusinessKey(exclude = Method.ALL)
    private Set<Beneficiario> beneficiarios = new HashSet<Beneficiario>(0);
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.TIME)
    @BusinessKey
    private Date fechaPrimaHCM;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.TIME)
    @BusinessKey
    private Date fechaPrimaVida;
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
    /**
     * reportes especificos
     */
    @Transient
    private transient Set<Reporte> reportes = new HashSet<Reporte>(0);

    public Certificado() {
    }

    /**
     * HCM
     * @return the asegurados
     */
    public Set<Asegurado> getAsegurados() {
        return asegurados;
    }

    /**
     *
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * Vida
     * @return the beneficiarios
     */
    public Set<Beneficiario> getBeneficiarios() {
        return beneficiarios;
    }

    /**
     * Coleccion de documentos anexos
     * @return the documentos
     */
    public Set<Documento> getDocumentos() {
        return documentos;
    }

    /**
     *
     * @return the fechaPrimaHCM
     */
    public Date getFechaPrimaHCM() {
        return fechaPrimaHCM;
    }

    /**
     *
     * @return the fechaPrimaVida
     */
    public Date getFechaPrimaVida() {
        return fechaPrimaVida;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return the notasTecnicas
     */
    public List<NotaTecnica> getNotasTecnicas() {
        return notasTecnicas;
    }

    /**
     *
     * @return the observaciones
     */
    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    /**
     *
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     *
     * @return the poliza
     */
    public Poliza getPoliza() {
        return poliza;
    }

    /**
     * reportes especificos
     * @return the reportes
     */
    public Set<Reporte> getReportes() {
        if (reportes.isEmpty()) {
            reportes.add(new Reporte(Dominios.CategoriaReporte.CERTIFICADOS, 0,
                    "CERT-D001", "CERTIFICADO DE ASEGURADOS", "CERTIFICIADO CON FOTO DE TITULAR",
                    null,
                    "Carta 8½ x 11 Vertical", false, true, true, false));
        }
        return reportes;
    }

    /**
     *
     * @return the titular
     */
    public Titular getTitular() {
        return titular;
    }

    /**
     * HCM
     * @param asegurados the asegurados to set
     */
    public void setAsegurados(Set<Asegurado> asegurados) {
        this.asegurados = asegurados;
    }

    /**
     *
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Vida
     * @param beneficiarios the beneficiarios to set
     */
    public void setBeneficiarios(Set<Beneficiario> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    /**
     * Coleccion de documentos anexos
     * @param documentos the documentos to set
     */
    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    /**
     *
     * @param fechaPrimaHCM the fechaPrimaHCM to set
     */
    public void setFechaPrimaHCM(Date fechaPrimaHCM) {
        this.fechaPrimaHCM = fechaPrimaHCM;
    }

    /**
     *
     * @param fechaPrimaVida the fechaPrimaVida to set
     */
    public void setFechaPrimaVida(Date fechaPrimaVida) {
        this.fechaPrimaVida = fechaPrimaVida;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @param notasTecnicas the notasTecnicas to set
     */
    public void setNotasTecnicas(List<NotaTecnica> notasTecnicas) {
        this.notasTecnicas = notasTecnicas;
    }

    /**
     *
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    /**
     *
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     *
     * @param poliza the poliza to set
     */
    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }

    /**
     * reportes especificos
     * @param reportes the reportes to set
     */
    public void setReportes(Set<Reporte> reportes) {
        this.reportes = reportes;
    }

    /**
     *
     * @param titular the titular to set
     */
    public void setTitular(Titular titular) {
        this.titular = titular;
    }
}
