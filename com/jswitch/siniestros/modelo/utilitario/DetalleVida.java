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

    private Diagnostico diagnostico;
    private Double montoPendiente;
    private Date fechaDefuncion;
    private TipoSiniestro tipoSiniestro;

    public DetalleVida() {
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Date getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(Date fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    public Double getMontoPendiente() {
        return montoPendiente;
    }

    public void setMontoPendiente(Double montoPendiente) {
        this.montoPendiente = montoPendiente;
    }

    public TipoSiniestro getTipoSiniestro() {
        return tipoSiniestro;
    }

    public void setTipoSiniestro(TipoSiniestro tipoSiniestro) {
        this.tipoSiniestro = tipoSiniestro;
    }
  
}
