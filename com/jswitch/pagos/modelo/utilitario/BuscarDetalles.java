/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.pagos.modelo.utilitario;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;

/**
 *
 * @author Adrian
 */
public class BuscarDetalles extends BeanVO {

    private Boolean selected;
    private DetalleSiniestro detalleSiniestro;

    public BuscarDetalles(Boolean selected, DetalleSiniestro detalleSiniestro) {
        this.selected = selected;
        this.detalleSiniestro = detalleSiniestro;
    }

    public BuscarDetalles() {
    }

    public DetalleSiniestro getDetalleSiniestro() {
        return detalleSiniestro;
    }

    public void setDetalleSiniestro(DetalleSiniestro detalleSiniestro) {
        this.detalleSiniestro = detalleSiniestro;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
