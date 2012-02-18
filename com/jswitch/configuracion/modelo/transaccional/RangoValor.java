package com.jswitch.configuracion.modelo.transaccional;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.maestra.ConfiguracionProntoPago;
import com.jswitch.configuracion.modelo.maestra.TimbreMunicipal;
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
@Table(name = "CONF_RangoValor")
public class RangoValor extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Timbre municipal al que pertenece
     */
    @ManyToOne
    @BusinessKey
    private TimbreMunicipal timbreMunicipal;
    /**
     * Timbre municipal al que pertenece
     */
    @ManyToOne
    @BusinessKey
    private ConfiguracionProntoPago prontoPago;
    /**
     * Valor minimo del Rango
     */
    @Column
    private Double minValue;
    /**
     * Valor maximo del Rango
     */
    @Column
    private Double maxValue;
    /**
     * 
     */
    @Column
    private Double monto;
    /**
     * 
     */
    @Column
    private Boolean montoPorcentual;
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

    public RangoValor() {
        montoPorcentual = Boolean.FALSE;
    }

    /**
     * auditoria Bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     *
     * @return the descuento
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Valor maximo del Rango
     * @return the maxValue
     */
    public Double getMaxValue() {
        return maxValue;
    }

    /**
     * Valor minimo del Rango
     * @return the minValue
     */
    public Double getMinValue() {
        return minValue;
    }

    /**
     *
     * @return the montoPorcentual
     */
    public Boolean getMontoPorcentual() {
        return montoPorcentual;
    }

    /**
     * version
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * auditoria Bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     *
     * @param monto the descuento to set
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Valor maximo del Rango
     * @param maxValue the maxValue to set
     */
    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Valor minimo del Rango
     * @param minValue the minValue to set
     */
    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    /**
     *
     * @param montoPorcentual the montoPorcentual to set
     */
    public void setMontoPorcentual(Boolean montoPorcentual) {
        this.montoPorcentual = montoPorcentual;
    }

    /**
     * version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * Timbre municipal al que pertenece
     * @return the timbreMunicipal
     */
    public TimbreMunicipal getTimbreMunicipal() {
        return timbreMunicipal;
    }

    /**
     * Timbre municipal al que pertenece
     * @param timbreMunicipal the timbreMunicipal to set
     */
    public void setTimbreMunicipal(TimbreMunicipal timbreMunicipal) {
        this.timbreMunicipal = timbreMunicipal;
    }

    /**
     * Timbre municipal al que pertenece
     * @return the prontoPago
     */
    public ConfiguracionProntoPago getProntoPago() {
        return prontoPago;
    }

    /**
     * Timbre municipal al que pertenece
     * @param prontoPago the prontoPago to set
     */
    public void setProntoPago(ConfiguracionProntoPago prontoPago) {
        this.prontoPago = prontoPago;
    }
    
}
