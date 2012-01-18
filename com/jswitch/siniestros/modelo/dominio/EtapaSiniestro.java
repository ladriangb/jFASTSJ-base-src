package com.jswitch.siniestros.modelo.dominio;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author Personal
 */
@Entity
@Table(name = "SINI_EtapaSiniestro")
public class EtapaSiniestro extends BeanVO implements Serializable, Auditable {

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
    @Column
    @BusinessKey
    private String idPropio;
    
    /**
     *
     */
    @Column
    private Boolean aps;
    /**
     *
     */
    @Column
    private Boolean cartaAval;
    /**
     *
     */
    @Column
    private Boolean emergencia;
    /**
     *
     */
    @Column
    private Boolean reembolso;
    /**
     *
     */
    @Column
    private Boolean ayudaSocial;
    /**
     *
     */
    @Column
    private Boolean funerario;
    /**
     *
     */
    @Column
    private Boolean vida;
    /**
     *
     */
    @ManyToOne
    private EstatusSiniestro estatusSiniestro;
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

    public EtapaSiniestro() {
    }

    public EtapaSiniestro(String nombre, Boolean aps, Boolean cartaAval, Boolean emergencia, Boolean reembolso, Boolean ayudaSocial, Boolean funerarios, Boolean vida, EstatusSiniestro estatusSiniestro, AuditoriaBasica auditoria) {
        this.nombre = nombre;
        this.aps = aps;
        this.cartaAval = cartaAval;
        this.emergencia = emergencia;
        this.reembolso = reembolso;
        this.ayudaSocial = ayudaSocial;
        this.funerario = funerarios;
        this.vida = vida;
        this.estatusSiniestro = estatusSiniestro;
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

    public Boolean getAps() {
        return aps;
    }

    public void setAps(Boolean aps) {
        this.aps = aps;
    }

    public Boolean getAyudaSocial() {
        return ayudaSocial;
    }

    public void setAyudaSocial(Boolean ayudaSocial) {
        this.ayudaSocial = ayudaSocial;
    }

    public Boolean getCartaAval() {
        return cartaAval;
    }

    public void setCartaAval(Boolean cartaAval) {
        this.cartaAval = cartaAval;
    }

    public Boolean getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(Boolean emergencia) {
        this.emergencia = emergencia;
    }

    public Boolean getReembolso() {
        return reembolso;
    }

    public void setReembolso(Boolean reembolso) {
        this.reembolso = reembolso;
    }

    public EstatusSiniestro getEstatusSiniestro() {
        return estatusSiniestro;
    }

    public void setEstatusSiniestro(EstatusSiniestro estatusSiniestro) {
        this.estatusSiniestro = estatusSiniestro;
    }

    public Boolean getFunerario() {
        return funerario;
    }

    public void setFunerario(Boolean funerarios) {
        this.funerario = funerarios;
    }

    public Boolean getVida() {
        return vida;
    }

    public void setVida(Boolean vida) {
        this.vida = vida;
    }

    public String getIdPropio() {
        return idPropio;
    }

    public void setIdPropio(String idPropio) {
        this.idPropio = idPropio;
    }
    
}
