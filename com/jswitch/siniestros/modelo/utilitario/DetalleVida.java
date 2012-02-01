package com.jswitch.siniestros.modelo.utilitario;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import com.jswitch.siniestros.modelo.dominio.TipoSiniestro;
import java.util.Date;

/**
 *
 * @author Adrian
 */
public class DetalleVida extends BeanVO {

    /**
     * Diagnostico de la defuncion
     */
    private Diagnostico diagnostico;
    /**
     * Fecha en que muere el titular
     */
    private Date fechaDefuncion;
    /**
     * Tipo De siniestro 
     */
    private TipoSiniestro tipoSiniestro;

    public DetalleVida() {
    }

    /**
     * Diagnostico de la defuncion
     * @return the diagnostico
     */
    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    /**
     * Fecha en que muere el titular
     * @return the fechaDefuncion
     */
    public Date getFechaDefuncion() {
        return fechaDefuncion;
    }

    /**
     * Tipo De siniestro
     * @return the tipoSiniestro
     */
    public TipoSiniestro getTipoSiniestro() {
        return tipoSiniestro;
    }

    /**
     * Diagnostico de la defuncion
     * @param diagnostico the diagnostico to set
     */
    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * Fecha en que muere el titular
     * @param fechaDefuncion the fechaDefuncion to set
     */
    public void setFechaDefuncion(Date fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    /**
     * Tipo De siniestro
     * @param tipoSiniestro the tipoSiniestro to set
     */
    public void setTipoSiniestro(TipoSiniestro tipoSiniestro) {
        this.tipoSiniestro = tipoSiniestro;
    }
}
