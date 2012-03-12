package com.jswitch.vistasbd;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
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
@Table(name = "view_listadiagnostico")
public class ListaDiagnostico extends BeanVO implements Serializable {

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
    private DetalleSiniestro detalleSiniestro;
    /**
     * Diagnostico afectado
     */
    @ManyToOne
    @BusinessKey
    private Diagnostico diagnostico;
    /**
     * Partida Presupuestaria 
     */
    @ManyToOne
    @BusinessKey
    private Siniestro siniestro;

    public ListaDiagnostico() {
    }

    /**
     * Detalle al que pertenece la factura
     * @return the detalleSiniestro
     */
    public DetalleSiniestro getDetalleSiniestro() {
        return detalleSiniestro;
    }

    /**
     * Diagnostico afectado
     * @return the diagnostico
     */
    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Partida Presupuestaria
     * @return the siniestro
     */
    public Siniestro getSiniestro() {
        return siniestro;
    }

    /**
     * Detalle al que pertenece la factura
     * @param detalleSiniestro the detalleSiniestro to set
     */
    public void setDetalleSiniestro(DetalleSiniestro detalleSiniestro) {
        this.detalleSiniestro = detalleSiniestro;
    }

    /**
     * Diagnostico afectado
     * @param diagnostico the diagnostico to set
     */
    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Partida Presupuestaria
     * @param siniestro the siniestro to set
     */
    public void setSiniestro(Siniestro siniestro) {
        this.siniestro = siniestro;
    }

}
