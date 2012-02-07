package com.jswitch.vistasbd;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
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
@Table(name = "view_sumadetalle")
public class SumaDetalle extends BeanVO implements Serializable {

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
    private DetalleSiniestro detalleSiniestro;
    /**
     * suma de facturas dentro de todos los detalles
     */
    @Column
    private Integer cantidadFacturas;
    /**
     * RetencionTM Timbre Municipal
     * 
     */
    @Column
    @BusinessKey
    private Double montoRetencionTM;
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
    private Double montoRetencionIslr;
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
     * retencion pronto pago
     */
    @Column
    @BusinessKey
    private Double montoRetencionProntoPago;
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

    public SumaDetalle() {
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
     * suma de facturas dentro de todos los detalles
     * @return the cantidadFacturas
     */
    public Integer getCantidadFacturas() {
        return cantidadFacturas;
    }

    /**
     * Detalle al que pertenece la factura
     * @return the detalleSiniestro
     */
    public DetalleSiniestro getDetalleSiniestro() {
        return detalleSiniestro;
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
     * retencion pronto pago
     * @return the montoRetencionProntoPago
     */
    public Double getMontoRetencionProntoPago() {
        return montoRetencionProntoPago;
    }

    /**
     * RetencionTM Timbre Municipal
     * @return the montoRetencionTM
     */
    public Double getMontoRetencionTM() {
        return montoRetencionTM;
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
     * suma de facturas dentro de todos los detalles
     * @param cantidadFacturas the cantidadFacturas to set
     */
    public void setCantidadFacturas(Integer cantidadFacturas) {
        this.cantidadFacturas = cantidadFacturas;
    }

    /**
     * Detalle al que pertenece la factura
     * @param detalleSiniestro the detalleSiniestro to set
     */
    public void setDetalleSiniestro(DetalleSiniestro detalleSiniestro) {
        this.detalleSiniestro = detalleSiniestro;
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
     * retencion pronto pago
     * @param montoRetencionProntoPago the montoRetencionProntoPago to set
     */
    public void setMontoRetencionProntoPago(Double montoRetencionProntoPago) {
        this.montoRetencionProntoPago = montoRetencionProntoPago;
    }

    /**
     * RetencionTM Timbre Municipal
     * @param montoRetencionTM the montoRetencionTM to set
     */
    public void setMontoRetencionTM(Double montoRetencionTM) {
        this.montoRetencionTM = montoRetencionTM;
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
