/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.pagos.modelo.utilitario;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.modelo.dominio.EstatusSiniestro;
import java.util.Date;

/**
 *
 * @author Luis Adrian Gonzalez
 */
public class Pagar extends BeanVO {

    /**
     * referencia del pago
     */
    private String referencia;
    /**
     * estatus en que quedan los siniestros pagados
     */
    private EstatusSiniestro estatusSiniestro;
    /**
     * Fecha en que se paga
     */
    private Date fechaDePago;

    public Pagar() {
    }
}
