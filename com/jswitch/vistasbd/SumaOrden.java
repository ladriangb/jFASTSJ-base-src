package com.jswitch.vistasbd;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Luis Adrian Gonzalez
 */
@Entity
@Table(name = "view_sumaorden")
public class SumaOrden extends BeanVO implements Serializable {

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
    @OneToOne
    @PrimaryKeyJoinColumn
    private OrdenDePago ordenDePago;
    /**
     * Cantidad de Detalles Siniestros en la orden de pago
     */
    @Column
    private Integer cantidadDetalles;
    /**
     * Cantidad de Siniestros de Titulares
     */
    @Column
    private Integer numeroSiniestrosTitular;
    /**
     * Cantidad de Siniestros de Famililiares asegurados
     */
    @Column
    private Integer numeroSiniestrosFamiliar;
    /**
     * Suma de todos los montos a pagar a Titulares
     */
    @Column
    private Double montoTitulares;
    /**
     * Suma de todos los montos a pagar a Beneficiarios
     */
    @Column
    private Double montoFamiliares;
    /**
     * sustraendo aplica al ISLR
     */
    @Column
    @BusinessKey
    private Double sustraendo;
    /**
     * TM Timbre Municipal
     * 
     */
    @Column
    @BusinessKey
    private Double montoTM;
    /**
     * base para el ISLR
     */
    @Column
    @BusinessKey
    private Double baseIslr;
    /**
     * monto de retencion ISLR
     */
    @Column
    @BusinessKey
    private Double montoRetencionIsrl;
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
    private Double montoDeducible;
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

    public SumaOrden() {
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
     * Cantidad de Detalles Siniestros en la orden de pago
     * @return the cantidadDetalles
     */
    public Integer getCantidadDetalles() {
        return cantidadDetalles;
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
     * descuento deducible
     * @return the montoDeducible
     */
    public Double getMontoDeducible() {
        return montoDeducible;
    }

    /**
     * decuento pronto pago
     * @return the montoDescuentoProntoPago
     */
    public Double getMontoDescuentoProntoPago() {
        return montoDescuentoProntoPago;
    }

    /**
     * Suma de todos los montos a pagar a Beneficiarios
     * @return the montoFamiliares
     */
    public Double getMontoFamiliares() {
        return montoFamiliares;
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
     * monto de retencion ISLR
     * @return the montoRetencionIsrl
     */
    public Double getMontoRetencionIsrl() {
        return montoRetencionIsrl;
    }

    /**
     * cuanto sera retenido por iva
     * @return the montoRetencionIva
     */
    public Double getMontoRetencionIva() {
        return montoRetencionIva;
    }

    /**
     * TM Timbre Municipal
     * @return the montoTM
     */
    public Double getMontoTM() {
        return montoTM;
    }

    /**
     * Suma de todos los montos a pagar a Titulares
     * @return the montoTitulares
     */
    public Double getMontoTitulares() {
        return montoTitulares;
    }

    /**
     * Cantidad de Siniestros de Famililiares asegurados
     * @return the numeroSiniestrosFamiliar
     */
    public Integer getNumeroSiniestrosFamiliar() {
        return numeroSiniestrosFamiliar;
    }

    /**
     * Cantidad de Siniestros de Titulares
     * @return the numeroSiniestrosTitular
     */
    public Integer getNumeroSiniestrosTitular() {
        return numeroSiniestrosTitular;
    }

    /**
     * Detalle al que pertenece la factura
     * @return the ordenDePago
     */
    public OrdenDePago getOrdenDePago() {
        return ordenDePago;
    }

    /**
     * sustraendo aplica al ISLR
     * @return the sustraendo
     */
    public Double getSustraendo() {
        return sustraendo;
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
     * Cantidad de Detalles Siniestros en la orden de pago
     * @param cantidadDetalles the cantidadDetalles to set
     */
    public void setCantidadDetalles(Integer cantidadDetalles) {
        this.cantidadDetalles = cantidadDetalles;
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
     * descuento deducible
     * @param montoDeducible the montoDeducible to set
     */
    public void setMontoDeducible(Double montoDeducible) {
        this.montoDeducible = montoDeducible;
    }

    /**
     * decuento pronto pago
     * @param montoDescuentoProntoPago the montoDescuentoProntoPago to set
     */
    public void setMontoDescuentoProntoPago(Double montoDescuentoProntoPago) {
        this.montoDescuentoProntoPago = montoDescuentoProntoPago;
    }

    /**
     * Suma de todos los montos a pagar a Beneficiarios
     * @param montoFamiliares the montoFamiliares to set
     */
    public void setMontoFamiliares(Double montoFamiliares) {
        this.montoFamiliares = montoFamiliares;
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
     * monto de retencion ISLR
     * @param montoRetencionIsrl the montoRetencionIsrl to set
     */
    public void setMontoRetencionIsrl(Double montoRetencionIsrl) {
        this.montoRetencionIsrl = montoRetencionIsrl;
    }

    /**
     * cuanto sera retenido por iva
     * @param montoRetencionIva the montoRetencionIva to set
     */
    public void setMontoRetencionIva(Double montoRetencionIva) {
        this.montoRetencionIva = montoRetencionIva;
    }

    /**
     * TM Timbre Municipal
     * @param montoTM the montoTM to set
     */
    public void setMontoTM(Double montoTM) {
        this.montoTM = montoTM;
    }

    /**
     * Suma de todos los montos a pagar a Titulares
     * @param montoTitulares the montoTitulares to set
     */
    public void setMontoTitulares(Double montoTitulares) {
        this.montoTitulares = montoTitulares;
    }

    /**
     * Cantidad de Siniestros de Famililiares asegurados
     * @param numeroSiniestrosFamiliar the numeroSiniestrosFamiliar to set
     */
    public void setNumeroSiniestrosFamiliar(Integer numeroSiniestrosFamiliar) {
        this.numeroSiniestrosFamiliar = numeroSiniestrosFamiliar;
    }

    /**
     * Cantidad de Siniestros de Titulares
     * @param numeroSiniestrosTitular the numeroSiniestrosTitular to set
     */
    public void setNumeroSiniestrosTitular(Integer numeroSiniestrosTitular) {
        this.numeroSiniestrosTitular = numeroSiniestrosTitular;
    }

    /**
     * Detalle al que pertenece la factura
     * @param ordenDePago the ordenDePago to set
     */
    public void setOrdenDePago(OrdenDePago ordenDePago) {
        this.ordenDePago = ordenDePago;
    }

    /**
     * sustraendo aplica al ISLR
     * @param sustraendo the sustraendo to set
     */
    public void setSustraendo(Double sustraendo) {
        this.sustraendo = sustraendo;
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

  
}
