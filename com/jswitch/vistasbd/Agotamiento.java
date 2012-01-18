package com.jswitch.vistasbd;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Luis Adrian Gonzalez
 */
@Entity
@Table(name = "view_agotamiento")
public class Agotamiento extends BeanVO implements Serializable {

    /**
     * Asegurado en cuestion 
     */
    @ManyToOne()
    @BusinessKey
    @Id
    private Asegurado asegurado;
    /**
     * diagnostico al cual se le agota la cobertura
     */
    @ManyToOne
    @BusinessKey
    @Id
    private Diagnostico diagnostico;
    /**
     * año de insidencia del siniestro
     */
    @Column(updatable = false, insertable = false)
    @BusinessKey
    @Id
    private Integer ayo;
    /**
     * monto reservado para gastos
     */
    @Column(updatable = false, insertable = false)
    @BusinessKey
    private Double montoPendiente;
    /**
     * monto total consumido 
     */
    @Column(updatable = false, insertable = false)
    @BusinessKey
    private Double montoPagado;

    public Agotamiento() {
    }

    public Agotamiento(Double montoPendiente, Double montoPagado) {
        this.montoPendiente = montoPendiente;
        this.montoPagado = montoPagado;
    }

    /**
     * Asegurado en cuestion
     * @return the asegurado
     */
    public Asegurado getAsegurado() {
        return asegurado;
    }

    /**
     * año de insidencia del siniestro
     * @return the ayo
     */
    public Integer getAyo() {
        return ayo;
    }

    /**
     * diagnostico al cual se le agota la cobertura
     * @return the diagnostico
     */
    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    /**
     * monto total consumido
     * @return the montoPagado
     */
    public Double getMontoPagado() {
        return montoPagado;
    }

    /**
     * monto reservado para gastos
     * @return the montoPendiente
     */
    public Double getMontoPendiente() {
        return montoPendiente;
    }

    /**
     * Asegurado en cuestion
     * @param asegurado the asegurado to set
     */
    public void setAsegurado(Asegurado asegurado) {
        this.asegurado = asegurado;
    }

    /**
     * año de insidencia del siniestro
     * @param ayo the ayo to set
     */
    public void setAyo(Integer ayo) {
        this.ayo = ayo;
    }

    /**
     * diagnostico al cual se le agota la cobertura
     * @param diagnostico the diagnostico to set
     */
    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * monto total consumido
     * @param montoPagado the montoPagado to set
     */
    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }

    /**
     * monto reservado para gastos
     * @param montoPendiente the montoPendiente to set
     */
    public void setMontoPendiente(Double montoPendiente) {
        this.montoPendiente = montoPendiente;
    }
}
