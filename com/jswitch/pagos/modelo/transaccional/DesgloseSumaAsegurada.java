package com.jswitch.pagos.modelo.transaccional;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
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

@Entity
@Table(name = "SINI_DesgloseSumaAsegurada")
public class DesgloseSumaAsegurada extends BeanVO implements Serializable, Auditable {

    /**
     *  PK autoincremtado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Diagnostico al cual se esta cubriendo
     */
    @ManyToOne
    @BusinessKey(exclude= Method.ALL)
    private DiagnosticoSiniestro diagnosticoSiniestro;
    /**
     * Diagnostico al cual se esta cubriendo
     */
    @ManyToOne
    @BusinessKey
    private Factura factura;
    /**
     * 
     */
    @Column
    private Double monto;
    /**
     *
     */
    @Column
    private String detalle;   
    /**
     *
     */
    @Version
    @Column
    private Integer optLock;
    /**
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public DesgloseSumaAsegurada() {
    }


    @Override
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    @Override
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    @Override
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

    public DiagnosticoSiniestro getDiagnosticoSiniestro() {
        return diagnosticoSiniestro;
    }

    public void setDiagnosticoSiniestro(DiagnosticoSiniestro diagnosticoSiniestro) {
        this.diagnosticoSiniestro = diagnosticoSiniestro;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    
    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}
