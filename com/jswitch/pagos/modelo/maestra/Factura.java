package com.jswitch.pagos.modelo.maestra;

import com.jswitch.base.controlador.General;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.pagos.modelo.dominio.ConceptoSENIAT;
import com.jswitch.pagos.modelo.transaccional.DesgloseCobertura;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.vistasbd.SumaFactura;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Past;

/**
 *
 * @author Personal
 */
@Entity
@Table(name = "SINI_Factura")
public class Factura extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Detalle al que pertenece la factura
     */
    @ManyToOne()
    @BusinessKey
    private DetalleSiniestro detalleSiniestro;
    /**
     * Calcula y totaliza
     */
    @OneToOne(mappedBy="factura")
    private SumaFactura sumaFactura;
    /**
     * identificador de la factura
     */
    @Column
    @BusinessKey
    private String numeroFactura;
    /**
     * numero de control de la factura
     * si no existe coloque N/A
     */
    @Column
    @BusinessKey
    private String numeroControl;
    /**
     * fecha en que fue facturado
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaFactura;
    /**
     * fecha en la que se recibio la factura
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaRecepcion;
     /**
     * fecha en la que se recibio la factura
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    private Date fechaPagado;
    /**
     * tipo de consepto
     * para saber q porcentaje de ISLR aplica
     */
    @ManyToOne()
    @BusinessKey
    private ConceptoSENIAT tipoConceptoSeniat;
    /**
     * UT Unidad Tributaria
     */
    @Column
    @BusinessKey
    private Double valorUT;
    /**
     * RetencionTM Timbre Municipal
     */
    @Column
    @BusinessKey
    private Double porcentajeRetencionTM;
    /**
     * base para el ISLR
     */
    @Column
    @BusinessKey
    private Double baseIslr;
    /**
     * porcentaje de retencion Islr
     */
    @Column
    @BusinessKey
    private Double porcentajeRetencionIslr;
    /**
     * monto de retencion ISLR
     */
    @Column
    @BusinessKey
    private Double montoRetencionIslr;
    /**
     * porcentaje de iva para la fecha de facturacion
     */
    @Column
    @BusinessKey
    private Double porcentajeIva;
    /**
     * total de base para el IVA
     */
    @Column
    @BusinessKey
    private Double baseIva;
    /**
     * total IVA
     */
    @Column
    @BusinessKey
    private Double montoIva;
    /**
     * porcentaje de iva que sera retenido
     */
    @Column
    @BusinessKey
    private Double porcentajeRetencionIva;
    /**
     * cuanto sera retenido por iva
     */
    @Column
    @BusinessKey
    private Double montoRetencionIva;
    /**
     * Porcentaje retencion Pronto Pago
     */
    @Column
    @BusinessKey
    private Double porcentajeRetencionProntoPago;
    /**
     * descuento deducible
     */
    @Column
    @BusinessKey
    private Double montoRetencionDeducible;
    /**
     * total de gastos clinicos
     * esto es a lo que se le retenie impuesto
     */
    @Column
    @BusinessKey
    private Double gastosClinicos;
    /**
     * Total por honorarios medicos
     */
    @Column
    @BusinessKey
    private Double honorariosMedicos;
    /**
     * monto que la empresa no apara registrados en la factura
     */
    @Column
    @BusinessKey
    private Double montoNoAmparado;
    /**
     * monto que la empresa apara registrados en la factura
     */
    @Column
    @BusinessKey
    private Double montoAmparado;
    /**
     * total facturado
     */
    @Column
    @BusinessKey
    private Double totalFacturado;
    /**
     * total liquidado por la empresa
     */
    @Column
    @BusinessKey
    private Double totalLiquidado;
    /**
     * Coleccion de gastos por diagnostico
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "factura")
    @BusinessKey(exclude = Method.ALL)
    private Set<DesgloseSumaAsegurada> desgloseSumaAsegurada = new HashSet<DesgloseSumaAsegurada>();
    /**
     * Coleccion de desglose de pagos por cobertura espesifica
     */
    @OneToMany(fetch = FetchType.LAZY,mappedBy="factura")
    @BusinessKey(exclude = Method.ALL)
    private Set<DesgloseCobertura> desgloseCobertura = new HashSet<DesgloseCobertura>();
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

    public Factura() {
        baseIslr = 0d;
        baseIva = 0d;
        montoIva = 0d;
        montoNoAmparado = 0d;
        montoRetencionDeducible = 0d;
        montoRetencionIva = 0d;
        montoRetencionIslr = 0d;
        montoAmparado = 0d;
        gastosClinicos = 0d;
        honorariosMedicos = 0d;
        totalFacturado = 0d;
        totalLiquidado = 0d;
        if (General.parametros != null && General.parametros.get("iva") != null) {
            porcentajeIva = General.parametros.get("iva").getValorDouble();
        } else {
            porcentajeIva = 0d;
        }
        if (General.parametros != null && General.parametros.get("ut") != null) {
            valorUT = General.parametros.get("ut").getValorDouble();
        } else {
            valorUT = 0d;
        }

        porcentajeRetencionIva = 0d;
        porcentajeRetencionTM = 0d;
        porcentajeRetencionIslr = 0d;
        porcentajeRetencionProntoPago = 0d;
    }

    /**
     * auditoria Bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * base para el ISLR
     * @return the baseIslr
     */
    public Double getBaseIslr() {
        return baseIslr;
    }

    /**
     * total de base para el IVA
     * @return the baseIva
     */
    public Double getBaseIva() {
        return baseIva;
    }

    /**
     * Coleccion de desglose de pagos por cobertura espesifica
     * @return the desgloseCobertura
     */
    public Set<DesgloseCobertura> getDesgloseCobertura() {
        return desgloseCobertura;
    }

    /**
     * Coleccion de gastos por diagnostico
     * @return the desgloseSumaAsegurada
     */
    public Set<DesgloseSumaAsegurada> getDesgloseSumaAsegurada() {
        return desgloseSumaAsegurada;
    }

    /**
     * Detalle al que pertenece la factura
     * @return the detalleSiniestro
     */
    public DetalleSiniestro getDetalleSiniestro() {
        return detalleSiniestro;
    }

    /**
     * fecha en que fue facturado
     * @return the fechaFactura
     */
    public Date getFechaFactura() {
        return fechaFactura;
    }

    /**
     * fecha en la que se recibio la factura
     * @return the fechaPagado
     */
    public Date getFechaPagado() {
        return fechaPagado;
    }

    /**
     * fecha en la que se recibio la factura
     * @return the fechaRecepcion
     */
    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    /**
     * total de gastos clinicos
     * esto es a lo que se le retenie impuesto
     * @return the gastosClinicos
     */
    public Double getGastosClinicos() {
        return gastosClinicos;
    }

    /**
     * Total por honorarios medicos
     * @return the honorariosMedicos
     */
    public Double getHonorariosMedicos() {
        return honorariosMedicos;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * monto que la empresa apara registrados en la factura
     * @return the montoAmparado
     */
    public Double getMontoAmparado() {
        return montoAmparado;
    }

    /**
     * total IVA
     * @return the montoIva
     */
    public Double getMontoIva() {
        return montoIva;
    }

    /**
     * monto que la empresa no apara registrados en la factura
     * @return the montoNoAmparado
     */
    public Double getMontoNoAmparado() {
        return montoNoAmparado;
    }

    /**
     * descuento deducible
     * @return the montoRetencionDeducible
     */
    public Double getMontoRetencionDeducible() {
        return montoRetencionDeducible;
    }

    /**
     * monto de retencion ISLR
     * @return the montoRetencionIslr
     */
    public Double getMontoRetencionIslr() {
        return montoRetencionIslr;
    }

    /**
     * cuanto sera retenido por iva
     * @return the montoRetencionIva
     */
    public Double getMontoRetencionIva() {
        return montoRetencionIva;
    }

    /**
     * numero de control de la factura
     * si no existe coloque N/A
     * @return the numeroControl
     */
    public String getNumeroControl() {
        return numeroControl;
    }

    /**
     * identificador de la factura
     * @return the numeroFactura
     */
    public String getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * version
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * porcentaje de iva para la fecha de facturacion
     * @return the porcentajeIva
     */
    public Double getPorcentajeIva() {
        return porcentajeIva;
    }

    /**
     * porcentaje de retencion Islr
     * @return the porcentajeRetencionIslr
     */
    public Double getPorcentajeRetencionIslr() {
        return porcentajeRetencionIslr;
    }

    /**
     * porcentaje de iva que sera retenido
     * @return the porcentajeRetencionIva
     */
    public Double getPorcentajeRetencionIva() {
        return porcentajeRetencionIva;
    }

    /**
     * Porcentaje retencion Pronto Pago
     * @return the porcentajeRetencionProntoPago
     */
    public Double getPorcentajeRetencionProntoPago() {
        return porcentajeRetencionProntoPago;
    }

    /**
     * RetencionTM Timbre Municipal
     * @return the porcentajeRetencionTM
     */
    public Double getPorcentajeRetencionTM() {
        return porcentajeRetencionTM;
    }

    /**
     * Calcula y totaliza
     * @return the sumaFactura
     */
    public SumaFactura getSumaFactura() {
        return sumaFactura;
    }

    /**
     * tipo de consepto
     * para saber q porcentaje de ISLR aplica
     * @return the tipoConceptoSeniat
     */
    public ConceptoSENIAT getTipoConceptoSeniat() {
        return tipoConceptoSeniat;
    }

    /**
     * total facturado
     * @return the totalFacturado
     */
    public Double getTotalFacturado() {
        return totalFacturado;
    }

    /**
     * total liquidado por la empresa
     * @return the totalLiquidado
     */
    public Double getTotalLiquidado() {
        return totalLiquidado;
    }

    /**
     * UT Unidad Tributaria
     * @return the valorUT
     */
    public Double getValorUT() {
        return valorUT;
    }

    /**
     * auditoria Bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * base para el ISLR
     * @param baseIslr the baseIslr to set
     */
    public void setBaseIslr(Double baseIslr) {
        this.baseIslr = baseIslr;
    }

    /**
     * total de base para el IVA
     * @param baseIva the baseIva to set
     */
    public void setBaseIva(Double baseIva) {
        this.baseIva = baseIva;
    }

    /**
     * Coleccion de desglose de pagos por cobertura espesifica
     * @param desgloseCobertura the desgloseCobertura to set
     */
    public void setDesgloseCobertura(Set<DesgloseCobertura> desgloseCobertura) {
        this.desgloseCobertura = desgloseCobertura;
    }

    /**
     * Coleccion de gastos por diagnostico
     * @param desgloseSumaAsegurada the desgloseSumaAsegurada to set
     */
    public void setDesgloseSumaAsegurada(Set<DesgloseSumaAsegurada> desgloseSumaAsegurada) {
        this.desgloseSumaAsegurada = desgloseSumaAsegurada;
    }

    /**
     * Detalle al que pertenece la factura
     * @param detalleSiniestro the detalleSiniestro to set
     */
    public void setDetalleSiniestro(DetalleSiniestro detalleSiniestro) {
        this.detalleSiniestro = detalleSiniestro;
    }

    /**
     * fecha en que fue facturado
     * @param fechaFactura the fechaFactura to set
     */
    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    /**
     * fecha en la que se recibio la factura
     * @param fechaPagado the fechaPagado to set
     */
    public void setFechaPagado(Date fechaPagado) {
        this.fechaPagado = fechaPagado;
    }

    /**
     * fecha en la que se recibio la factura
     * @param fechaRecepcion the fechaRecepcion to set
     */
    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    /**
     * total de gastos clinicos
     * esto es a lo que se le retenie impuesto
     * @param gastosClinicos the gastosClinicos to set
     */
    public void setGastosClinicos(Double gastosClinicos) {
        this.gastosClinicos = gastosClinicos;
    }

    /**
     * Total por honorarios medicos
     * @param honorariosMedicos the honorariosMedicos to set
     */
    public void setHonorariosMedicos(Double honorariosMedicos) {
        this.honorariosMedicos = honorariosMedicos;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * monto que la empresa apara registrados en la factura
     * @param montoAmparado the montoAmparado to set
     */
    public void setMontoAmparado(Double montoAmparado) {
        this.montoAmparado = montoAmparado;
    }

    /**
     * total IVA
     * @param montoIva the montoIva to set
     */
    public void setMontoIva(Double montoIva) {
        this.montoIva = montoIva;
    }

    /**
     * monto que la empresa no apara registrados en la factura
     * @param montoNoAmparado the montoNoAmparado to set
     */
    public void setMontoNoAmparado(Double montoNoAmparado) {
        this.montoNoAmparado = montoNoAmparado;
    }

    /**
     * descuento deducible
     * @param montoRetencionDeducible the montoRetencionDeducible to set
     */
    public void setMontoRetencionDeducible(Double montoRetencionDeducible) {
        this.montoRetencionDeducible = montoRetencionDeducible;
    }

    /**
     * monto de retencion ISLR
     * @param montoRetencionIslr the montoRetencionIslr to set
     */
    public void setMontoRetencionIslr(Double montoRetencionIslr) {
        this.montoRetencionIslr = montoRetencionIslr;
    }

    /**
     * cuanto sera retenido por iva
     * @param montoRetencionIva the montoRetencionIva to set
     */
    public void setMontoRetencionIva(Double montoRetencionIva) {
        this.montoRetencionIva = montoRetencionIva;
    }

    /**
     * numero de control de la factura
     * si no existe coloque N/A
     * @param numeroControl the numeroControl to set
     */
    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    /**
     * identificador de la factura
     * @param numeroFactura the numeroFactura to set
     */
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    /**
     * version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * porcentaje de iva para la fecha de facturacion
     * @param porcentajeIva the porcentajeIva to set
     */
    public void setPorcentajeIva(Double porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    /**
     * porcentaje de retencion Islr
     * @param porcentajeRetencionIslr the porcentajeRetencionIslr to set
     */
    public void setPorcentajeRetencionIslr(Double porcentajeRetencionIslr) {
        this.porcentajeRetencionIslr = porcentajeRetencionIslr;
    }

    /**
     * porcentaje de iva que sera retenido
     * @param porcentajeRetencionIva the porcentajeRetencionIva to set
     */
    public void setPorcentajeRetencionIva(Double porcentajeRetencionIva) {
        this.porcentajeRetencionIva = porcentajeRetencionIva;
    }

    /**
     * Porcentaje retencion Pronto Pago
     * @param porcentajeRetencionProntoPago the porcentajeRetencionProntoPago to set
     */
    public void setPorcentajeRetencionProntoPago(Double porcentajeRetencionProntoPago) {
        this.porcentajeRetencionProntoPago = porcentajeRetencionProntoPago;
    }

    /**
     * RetencionTM Timbre Municipal
     * @param porcentajeRetencionTM the porcentajeRetencionTM to set
     */
    public void setPorcentajeRetencionTM(Double porcentajeRetencionTM) {
        this.porcentajeRetencionTM = porcentajeRetencionTM;
    }

    /**
     * Calcula y totaliza
     * @param sumaFactura the sumaFactura to set
     */
    public void setSumaFactura(SumaFactura sumaFactura) {
        this.sumaFactura = sumaFactura;
    }

    /**
     * tipo de consepto
     * para saber q porcentaje de ISLR aplica
     * @param tipoConceptoSeniat the tipoConceptoSeniat to set
     */
    public void setTipoConceptoSeniat(ConceptoSENIAT tipoConceptoSeniat) {
        this.tipoConceptoSeniat = tipoConceptoSeniat;
    }

    /**
     * total facturado
     * @param totalFacturado the totalFacturado to set
     */
    public void setTotalFacturado(Double totalFacturado) {
        this.totalFacturado = totalFacturado;
    }

    /**
     * total liquidado por la empresa
     * @param totalLiquidado the totalLiquidado to set
     */
    public void setTotalLiquidado(Double totalLiquidado) {
        this.totalLiquidado = totalLiquidado;
    }

    /**
     * UT Unidad Tributaria
     * @param valorUT the valorUT to set
     */
    public void setValorUT(Double valorUT) {
        this.valorUT = valorUT;
    }

}
