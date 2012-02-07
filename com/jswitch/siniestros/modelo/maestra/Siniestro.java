package com.jswitch.siniestros.modelo.maestra;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.base.modelo.entidades.Documento;
import com.jswitch.base.modelo.entidades.NotaTecnica;
import com.jswitch.base.modelo.entidades.Observacion;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.certificados.modelo.maestra.Certificado;
import com.jswitch.fas.modelo.Dominios.EstadoSiniestro;
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Adrian
 */
@Entity
@Table(name = "SINI_Siniestro")
public class Siniestro extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * fecha en que creacion del siniestro
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    private Date fechaCreacion;
    /**
     * secuencia de siniestro
     */
    @Column
    @BusinessKey
    private Long seq;
    /**
     * año del siniestro
     */
    @Column
    @BusinessKey
    private Integer ayo;
    /**
     * mes del siniestro
     */
    @Column
    @BusinessKey
    private Integer mes;
    /**
     * numero de siniestro
     */
    @Column
    private String numero;
    /**
     * Estatus en el que se encuentra el siniestro ABIERTO, CERRADO
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private EstadoSiniestro estatusSiniestro;
    /**
     * certificado 
     */
    @ManyToOne()
    private Certificado certificado;
    /**
     * asegurado del siniestro
     */
    @ManyToOne()
    private Asegurado asegurado;
    /**
     * Detalles
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "siniestro")
    @BusinessKey(exclude = Method.ALL)
    private Set<DetalleSiniestro> detalleSiniestro = new HashSet<DetalleSiniestro>(0);
    /**
     * observaciones
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<Observacion> observaciones = new ArrayList<Observacion>(0);
    /**
     * notas internas
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

    public Siniestro() {
        estatusSiniestro = EstadoSiniestro.ABIERTO;
        fechaCreacion= new Date();
    }

    /**
     * asegurado del siniestro
     * @return the asegurado
     */
    public Asegurado getAsegurado() {
        return asegurado;
    }

    /**
     * bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * año del siniestro
     * @return the ayo
     */
    public Integer getAyo() {
        return ayo;
    }

    /**
     * certificado
     * @return the certificado
     */
    public Certificado getCertificado() {
        return certificado;
    }

    /**
     * Detalles
     * @return the detalleSiniestro
     */
    public Set<DetalleSiniestro> getDetalleSiniestro() {
        return detalleSiniestro;
    }

    /**
     * Coleccion de documentos anexos
     * @return the documentos
     */
    public Set<Documento> getDocumentos() {
        return documentos;
    }

    /**
     * Estatus en el que se encuentra el siniestro ABIERTO, CERRADO
     * @return the estatusSiniestro
     */
    public EstadoSiniestro getEstatusSiniestro() {
        return estatusSiniestro;
    }

    /**
     * fecha en que creacion del siniestro
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * mes del siniestro
     * @return the mes
     */
    public Integer getMes() {
        return mes;
    }

    /**
     * notas internas
     * @return the notasTecnicas
     */
    public List<NotaTecnica> getNotasTecnicas() {
        return notasTecnicas;
    }

    /**
     * numero de siniestro
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * observaciones
     * @return the observaciones
     */
    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    /**
     * version
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * secuencia de siniestro
     * @return the seq
     */
    public Long getSeq() {
        return seq;
    }

    /**
     * asegurado del siniestro
     * @param asegurado the asegurado to set
     */
    public void setAsegurado(Asegurado asegurado) {
        this.asegurado = asegurado;
    }

    /**
     * bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * año del siniestro
     * @param ayo the ayo to set
     */
    public void setAyo(Integer ayo) {
        this.ayo = ayo;
    }

    /**
     * certificado
     * @param certificado the certificado to set
     */
    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    /**
     * Detalles
     * @param detalleSiniestro the detalleSiniestro to set
     */
    public void setDetalleSiniestro(Set<DetalleSiniestro> detalleSiniestro) {
        this.detalleSiniestro = detalleSiniestro;
    }

    /**
     * Coleccion de documentos anexos
     * @param documentos the documentos to set
     */
    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    /**
     * Estatus en el que se encuentra el siniestro ABIERTO, CERRADO
     * @param estatusSiniestro the estatusSiniestro to set
     */
    public void setEstatusSiniestro(EstadoSiniestro estatusSiniestro) {
        this.estatusSiniestro = estatusSiniestro;
    }

    /**
     * fecha en que creacion del siniestro
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * mes del siniestro
     * @param mes the mes to set
     */
    public void setMes(Integer mes) {
        this.mes = mes;
    }

    /**
     * notas internas
     * @param notasTecnicas the notasTecnicas to set
     */
    public void setNotasTecnicas(List<NotaTecnica> notasTecnicas) {
        this.notasTecnicas = notasTecnicas;
    }

    /**
     * numero de siniestro
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * observaciones
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * secuencia de siniestro
     * @param seq the seq to set
     */
    public void setSeq(Long seq) {
        this.seq = seq;
    }

    
}
