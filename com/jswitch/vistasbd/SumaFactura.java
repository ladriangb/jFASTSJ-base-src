package com.jswitch.vistasbd;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.pagos.modelo.maestra.Factura;
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
@Table(name = "view_sumafactura")
public class SumaFactura extends BeanVO implements Serializable {

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
    private Factura factura;
    /**
     * Total por honorarios medicos
     */
    @Column
    @BusinessKey
    private Double totalACancelar;
    /**
     * monto que la empresa no apara registrados en la factura
     */
    @Column
    @BusinessKey
    private Double totalRetenido;
    /**
     * monto que la empresa apara registrados en la factura
     */
    @Column
    @BusinessKey
    private Double montoRetencionProntoPago;
    /**
     * monto que la empresa 
     * para registrados en la factura
     */
    @Column
    @BusinessKey
    private Double montoRetencionTM;
    

    public SumaFactura() {
    }

    /**
     * Detalle al que pertenece la factura
     * @return the factura
     */
    public Factura getFactura() {
        return factura;
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
     * @return the montoRetencionProntoPago
     */
    public Double getMontoRetencionProntoPago() {
        return montoRetencionProntoPago;
    }

    /**
     * monto que la empresa
     * para registrados en la factura
     * @return the montoRetencionTM
     */
    public Double getMontoRetencionTM() {
        return montoRetencionTM;
    }

    /**
     * Total por honorarios medicos
     * @return the totalACancelar
     */
    public Double getTotalACancelar() {
        return totalACancelar;
    }

    /**
     * monto que la empresa no apara registrados en la factura
     * @return the totalRetenido
     */
    public Double getTotalRetenido() {
        return totalRetenido;
    }

    /**
     * Detalle al que pertenece la factura
     * @param factura the factura to set
     */
    public void setFactura(Factura factura) {
        this.factura = factura;
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
     * @param montoRetencionProntoPago the montoRetencionProntoPago to set
     */
    public void setMontoRetencionProntoPago(Double montoRetencionProntoPago) {
        this.montoRetencionProntoPago = montoRetencionProntoPago;
    }

    /**
     * monto que la empresa
     * para registrados en la factura
     * @param montoRetencionTM the montoRetencionTM to set
     */
    public void setMontoRetencionTM(Double montoRetencionTM) {
        this.montoRetencionTM = montoRetencionTM;
    }

    /**
     * Total por honorarios medicos
     * @param totalACancelar the totalACancelar to set
     */
    public void setTotalACancelar(Double totalACancelar) {
        this.totalACancelar = totalACancelar;
    }

    /**
     * monto que la empresa no apara registrados en la factura
     * @param totalRetenido the totalRetenido to set
     */
    public void setTotalRetenido(Double totalRetenido) {
        this.totalRetenido = totalRetenido;
    }

}
