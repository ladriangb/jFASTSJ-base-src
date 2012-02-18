package com.jswitch.vistasbd;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.dominio.PartidaPresupuestaria;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Luis Adrian Gonzalez
 */
@Entity
@Table(name = "view_sumapartida")
public class SumaPartida extends BeanVO implements Serializable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private String id;
    /**
     * Detalle al que pertenece la factura
     */
    @ManyToOne
    @BusinessKey
    private OrdenDePago ordenDePago;
    /**
     * Partida Presupuestaria 
     */
    @ManyToOne
    @BusinessKey
    private PartidaPresupuestaria partidaPresupuestaria;
    
    /**
     * Cantidad de Detalles Siniestros en la orden de pago
     */
    @Column
    private Integer cantidadDetalles;
    /**
     * Cantidad de Detalles Siniestros en la orden de pago
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
     * decuento pronto pago
     */
    @Column
    @BusinessKey
    private Double montoRetencionProntoPago;
    /**
     * Retencion deducible
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

    public SumaPartida() {
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
     * Cantidad de Detalles Siniestros en la orden de pago
     * @return the cantidadFacturas
     */
    public Integer getCantidadFacturas() {
        return cantidadFacturas;
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
    public String getId() {
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
     * Retencion deducible
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
     * decuento pronto pago
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
     * Detalle al que pertenece la factura
     * @return the ordenDePago
     */
    public OrdenDePago getOrdenDePago() {
        return ordenDePago;
    }

    /**
     * Partida Presupuestaria
     * @return the partidaPresupuestaria
     */
    public PartidaPresupuestaria getPartidaPresupuestaria() {
        return partidaPresupuestaria;
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
     * Cantidad de Detalles Siniestros en la orden de pago
     * @param cantidadFacturas the cantidadFacturas to set
     */
    public void setCantidadFacturas(Integer cantidadFacturas) {
        this.cantidadFacturas = cantidadFacturas;
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
    public void setId(String id) {
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
     * Retencion deducible
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
     * decuento pronto pago
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
     * Detalle al que pertenece la factura
     * @param ordenDePago the ordenDePago to set
     */
    public void setOrdenDePago(OrdenDePago ordenDePago) {
        this.ordenDePago = ordenDePago;
    }

    /**
     * Partida Presupuestaria
     * @param partidaPresupuestaria the partidaPresupuestaria to set
     */
    public void setPartidaPresupuestaria(PartidaPresupuestaria partidaPresupuestaria) {
        this.partidaPresupuestaria = partidaPresupuestaria;
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
