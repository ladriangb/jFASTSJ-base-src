package com.jswitch.siniestros.modelo.utilitario;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.fas.modelo.Dominios.TipoDetalleSiniestro;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.siniestros.modelo.maestra.Siniestro;

/**
 * Buscar el siniestro
 * @author Luis Adrian Gonzalez Benavides
 */
public class BuscarSiniestro extends BeanVO {

    /**
     * Persona a la que se le realiza el pago
     */
    private Persona personaPago;
    /**
     * Persona asegurado q sufrio el siniestro
     */
    private Persona personaAseg;
    /**
     * Persona titular del certificado
     */
    private Persona personaTit;
    /**
     * Que tipo de detalle de siniestro es el q se busca
     */
    private TipoDetalleSiniestro tipoDetalleSiniestro;
    /**
     * Siniestro al que Pertenece
     */
    private Siniestro siniestro;

    public BuscarSiniestro() {
    }

    /**
     * Persona asegurado q sufrio el siniestro
     * @return the personaAseg
     */
    public Persona getPersonaAseg() {
        return personaAseg;
    }

    /**
     * Persona a la que se le realiza el pago
     * @return the personaPago
     */
    public Persona getPersonaPago() {
        return personaPago;
    }

    /**
     * Persona titular del certificado
     * @return the personaTit
     */
    public Persona getPersonaTit() {
        return personaTit;
    }

    /**
     * Siniestro al que Pertenece
     * @return the siniestro
     */
    public Siniestro getSiniestro() {
        return siniestro;
    }

    /**
     * Que tipo de detalle de siniestro es el q se busca
     * @return the tipoDetalleSiniestro
     */
    public TipoDetalleSiniestro getTipoDetalleSiniestro() {
        return tipoDetalleSiniestro;
    }

    /**
     * Persona asegurado q sufrio el siniestro
     * @param personaAseg the personaAseg to set
     */
    public void setPersonaAseg(Persona personaAseg) {
        this.personaAseg = personaAseg;
    }

    /**
     * Persona a la que se le realiza el pago
     * @param personaPago the personaPago to set
     */
    public void setPersonaPago(Persona personaPago) {
        this.personaPago = personaPago;
    }

    /**
     * Persona titular del certificado
     * @param personaTit the personaTit to set
     */
    public void setPersonaTit(Persona personaTit) {
        this.personaTit = personaTit;
    }

    /**
     * Siniestro al que Pertenece
     * @param siniestro the siniestro to set
     */
    public void setSiniestro(Siniestro siniestro) {
        this.siniestro = siniestro;
    }

    /**
     * Que tipo de detalle de siniestro es el q se busca
     * @param tipoDetalleSiniestro the tipoDetalleSiniestro to set
     */
    public void setTipoDetalleSiniestro(TipoDetalleSiniestro tipoDetalleSiniestro) {
        this.tipoDetalleSiniestro = tipoDetalleSiniestro;
    }
}
