package com.jswitch.asegurados.modelo.utilitario;

import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.persona.modelo.maestra.Persona;

/**
 *
 * @author Luis Adrian
 * 
 */
public class RevisionAsegurado extends BeanVO {

    private Persona persona;
    private Persona personaBuscadora;
    private String detalle;
    private AuditoriaBasica auditoria;

    public RevisionAsegurado() {
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersonaBuscadora() {
        return personaBuscadora;
    }

    public void setPersonaBuscadora(Persona personaBuscadora) {
        this.personaBuscadora = personaBuscadora;
    }
}
