package com.jswitch.pagos.modelo.maestra;

import com.jswitch.base.controlador.General;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.pagos.modelo.dominio.ConceptoSENIAT;
import com.jswitch.pagos.modelo.transaccional.DesgloseCobertura;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.validation.constraints.NotNull;
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
     * tipo de consepto
     * para saber q porcentaje de ISLR aplica
     */
    @ManyToOne()
    @BusinessKey
    private ConceptoSENIAT tipoConceptoSeniat;
    /**
     * sustraendo aplica al ISLR
     * //TODO FIX JAVADOC
     */
    @Column
    @BusinessKey
    private Double sustraendo;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double porcentajeReteniconIsrl;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double montoReteniconIsrl;
    /**
     * UT Unidad Tributaria
     */
    @Column
    @BusinessKey
    private Double valorUT;
    /**
     * TM Timbre Municipal
     * //TODO tabla de configuracion dependiendo de cuantas ut pasa y q tasa aplica
     */
    @Column
    @BusinessKey
    private Double porcentajeTM;
    /**
     * TM Timbre Municipal
     * 
     */
    @Column
    @BusinessKey
    private Double montoTM;
    /**
     * porcentaje de iva para la fecha de facturacion
     */
    @Column
    @BusinessKey
    private Double porcentajeIva;
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
     * decuento pronto pago
     */
    @Column
    @BusinessKey
    private Double montoDescuentoProntoPago;
    /**
     * descuento deducible
     */
    @Column
    @BusinessKey
    private Double montoDescuentoDesducible;
    /**
     * base imponible
     */
    @Column
    @BusinessKey
    private Double montoSujetoRetencion;
    /**
     * total de gastos clinicos
     * //TODO VER Q HAGO CON ESTO
     * esto es a lo que se le retenie impuesto
     */
    @Column
    @BusinessKey
    private Double gastosClinicos;
    /**
     * Total por honorarios medicos
     * //TODO VER Q HAGO CON ESTO
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
     * total retenido entre IVA e ISLR
     */
    @Column
    @BusinessKey
    private Double totalRetenido;
    /**
     * totalLiquidado - totalRetenido
     */
    @Column
    @BusinessKey
    private Double totalACancelar;
    /**
     * Estatus en el que se encuentra el pago de la factura
     */
    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private EstatusPago estatusPago;
    /**
     * fecha en q la seleccionan para ser pagada
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaSeleccionado;
    /**
     * Fecha en que se paga
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaPagado;
    /**
     * Coleccion de gastos por diagnostico
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "factura")
    @BusinessKey(exclude = Method.ALL)
    private Set<DesgloseSumaAsegurada> desgloseSumaAsegurada = new HashSet<DesgloseSumaAsegurada>();
    /**
     * Coleccion de desglose de pagos por cobertura espesifica
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
        estatusPago = EstatusPago.PENDIENTE;
        montoDescuentoDesducible = 0d;
        montoDescuentoProntoPago = 0d;
        montoIva = 0d;
        montoNoAmparado = 0d;
        montoRetencionIva = 0d;
        montoReteniconIsrl = 0d;
        montoSujetoRetencion = 0d;
        montoTM = 0d;
        totalACancelar = 0d;
        totalFacturado = 0d;
        totalLiquidado = 0d;
        totalRetenido = 0d;
        if (General.parametros != null && General.parametros.get("iva") != null) {
            porcentajeIva = General.parametros.get("iva").getValorDouble();
        } else {
            porcentajeIva = 0d;
        }
        porcentajeRetencionIva = 0d;
        porcentajeTM = 0d;
        porcentajeReteniconIsrl = 0d;
    }

    /**
     * auditoria Bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
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
     * Estatus en el que se encuentra el pago de la factura
     * @return the estatusPago
     */
    public EstatusPago getEstatusPago() {
        return estatusPago;
    }

    /**
     * fecha en que fue facturado
     * @return the fechaFactura
     */
    public Date getFechaFactura() {
        return fechaFactura;
    }

    /**
     * Fecha en que se paga
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
     * fecha en q la seleccionan para ser pagada
     * @return the fechaSeleccionado
     */
    public Date getFechaSeleccionado() {
        return fechaSeleccionado;
    }

    /**
     * total de gastos clinicos
     * //TODO VER Q HAGO CON ESTO
     * esto es a lo que se le retenie impuesto
     * @return the gastosClinicos
     */
    public Double getGastosClinicos() {
        return gastosClinicos;
    }

    /**
     * Total por honorarios medicos
     * //TODO VER Q HAGO CON ESTO
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
     * descuento deducible
     * @return the montoDescuentoDesducible
     */
    public Double getMontoDescuentoDesducible() {
        return montoDescuentoDesducible;
    }

    /**
     * decuento pronto pago
     * @return the montoDescuentoProntoPago
     */
    public Double getMontoDescuentoProntoPago() {
        return montoDescuentoProntoPago;
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
     * cuanto sera retenido por iva
     * @return the montoRetencionIva
     */
    public Double getMontoRetencionIva() {
        return montoRetencionIva;
    }

    /**
     *
     * @return the montoReteniconIsrl
     */
    public Double getMontoReteniconIsrl() {
        return montoReteniconIsrl;
    }

    /**
     * base imponible
     * @return the montoSujetoRetencion
     */
    public Double getMontoSujetoRetencion() {
        return montoSujetoRetencion;
    }

    /**
     * TM Timbre Municipal
     * @return the montoTM
     */
    public Double getMontoTM() {
        return montoTM;
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
     * porcentaje de iva que sera retenido
     * @return the porcentajeRetencionIva
     */
    public Double getPorcentajeRetencionIva() {
        return porcentajeRetencionIva;
    }

    /**
     *
     * @return the porcentajeReteniconIsrl
     */
    public Double getPorcentajeReteniconIsrl() {
        return porcentajeReteniconIsrl;
    }

    /**
     * TM Timbre Municipal
     * @return the porcentajeTM
     */
    public Double getPorcentajeTM() {
        return porcentajeTM;
    }

    /**
     * sustraendo aplica al ISLR
     * //TODO FIX JAVADOC
     * @return the sustraendo
     */
    public Double getSustraendo() {
        return sustraendo;
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
     * totalLiquidado - totalRetenido
     * @return the totalACancelar
     */
    public Double getTotalACancelar() {
        return totalACancelar;
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
     * total retenido entre IVA e ISLR
     * @return the totalRetenido
     */
    public Double getTotalRetenido() {
        return totalRetenido;
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
     * Estatus en el que se encuentra el pago de la factura
     * @param estatusPago the estatusPago to set
     */
    public void setEstatusPago(EstatusPago estatusPago) {
        this.estatusPago = estatusPago;
    }

    /**
     * fecha en que fue facturado
     * @param fechaFactura the fechaFactura to set
     */
    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    /**
     * Fecha en que se paga
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
     * fecha en q la seleccionan para ser pagada
     * @param fechaSeleccionado the fechaSeleccionado to set
     */
    public void setFechaSeleccionado(Date fechaSeleccionado) {
        this.fechaSeleccionado = fechaSeleccionado;
    }

    /**
     * total de gastos clinicos
     * //TODO VER Q HAGO CON ESTO
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
     * descuento deducible
     * @param montoDescuentoDesducible the montoDescuentoDesducible to set
     */
    public void setMontoDescuentoDesducible(Double montoDescuentoDesducible) {
        this.montoDescuentoDesducible = montoDescuentoDesducible;
    }

    /**
     * decuento pronto pago
     * @param montoDescuentoProntoPago the montoDescuentoProntoPago to set
     */
    public void setMontoDescuentoProntoPago(Double montoDescuentoProntoPago) {
        this.montoDescuentoProntoPago = montoDescuentoProntoPago;
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
     * cuanto sera retenido por iva
     * @param montoRetencionIva the montoRetencionIva to set
     */
    public void setMontoRetencionIva(Double montoRetencionIva) {
        this.montoRetencionIva = montoRetencionIva;
    }

    /**
     *
     * @param montoReteniconIsrl the montoReteniconIsrl to set
     */
    public void setMontoReteniconIsrl(Double montoReteniconIsrl) {
        this.montoReteniconIsrl = montoReteniconIsrl;
    }

    /**
     * base imponible
     * @param montoSujetoRetencion the montoSujetoRetencion to set
     */
    public void setMontoSujetoRetencion(Double montoSujetoRetencion) {
        this.montoSujetoRetencion = montoSujetoRetencion;
    }

    /**
     * TM Timbre Municipal
     * @param montoTM the montoTM to set
     */
    public void setMontoTM(Double montoTM) {
        this.montoTM = montoTM;
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
     * porcentaje de iva que sera retenido
     * @param porcentajeRetencionIva the porcentajeRetencionIva to set
     */
    public void setPorcentajeRetencionIva(Double porcentajeRetencionIva) {
        this.porcentajeRetencionIva = porcentajeRetencionIva;
    }

    /**
     *
     * @param porcentajeReteniconIsrl the porcentajeReteniconIsrl to set
     */
    public void setPorcentajeReteniconIsrl(Double porcentajeReteniconIsrl) {
        this.porcentajeReteniconIsrl = porcentajeReteniconIsrl;
    }

    /**
     * TM Timbre Municipal
     * //TODO tabla de configuracion dependiendo de cuantas ut pasa y q tasa aplica
     * @param porcentajeTM the porcentajeTM to set
     */
    public void setPorcentajeTM(Double porcentajeTM) {
        this.porcentajeTM = porcentajeTM;
    }

    /**
     * sustraendo aplica al ISLR
     * //TODO FIX JAVADOC
     * @param sustraendo the sustraendo to set
     */
    public void setSustraendo(Double sustraendo) {
        this.sustraendo = sustraendo;
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
     * totalLiquidado - totalRetenido
     * @param totalACancelar the totalACancelar to set
     */
    public void setTotalACancelar(Double totalACancelar) {
        this.totalACancelar = totalACancelar;
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
     * total retenido entre IVA e ISLR
     * @param totalRetenido the totalRetenido to set
     */
    public void setTotalRetenido(Double totalRetenido) {
        this.totalRetenido = totalRetenido;
    }

    /**
     * UT Unidad Tributaria
     * @param valorUT the valorUT to set
     */
    public void setValorUT(Double valorUT) {
        this.valorUT = valorUT;
    }

}
