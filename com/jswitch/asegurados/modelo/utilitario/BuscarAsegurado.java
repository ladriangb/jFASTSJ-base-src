/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.asegurados.modelo.utilitario;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.fas.modelo.Dominios.TipoBusqueda;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Luis Adrian
 * 
 */
public class BuscarAsegurado extends BeanVO {

    private String rif;
    private String nombreLargo;
    private TipoBusqueda tipoBusqueda;
    private Set<Asegurado> asegurados = new HashSet<Asegurado>(0);

    public BuscarAsegurado() {
    }

    public String getNombreLargo() {
        return nombreLargo;
    }

    public void setNombreLargo(String nombreLargo) {
        this.nombreLargo = nombreLargo;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public TipoBusqueda getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setTipoBusqueda(TipoBusqueda tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public Set<Asegurado> getAsegurados() {
        return asegurados;
    }

    public void setAsegurados(Set<Asegurado> asegurados) {
        this.asegurados = asegurados;
    }
}
