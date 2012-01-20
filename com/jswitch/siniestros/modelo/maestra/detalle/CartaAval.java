package com.jswitch.siniestros.modelo.maestra.detalle;

import com.jswitch.base.controlador.General;
import com.jswitch.base.modelo.Dominios;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Past;

/**
 *
 * @author Adrian
 */
@Entity
public class CartaAval extends DetalleSiniestro {

    /**
     * Fecha de Emision de Carta Aval
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaEmision;
    /**
     * Fecha de realizacion del Presupuesto
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaPresupuesto;
    /**
     * Numero del presupuesto
     */
    @Column
    @BusinessKey
    private String numeroPresupuesto;
    /**
     * Reportes
     */
    @Transient
    protected static transient Set<Reporte> reportes = new HashSet<Reporte>(0);

    public CartaAval() {
        this.fechaEmision = new Date();
        if (General.parametros != null && General.parametros.get("cartaAval.diasVencimiento") != null) {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(this.fechaEmision);
            c1.add(Calendar.DAY_OF_MONTH, General.parametros.get("cartaAval.diasVencimiento").getValorInteger());
            super.setFechaVencimiento(c1.getTime());
            c1.setTime(this.fechaEmision);
        }
    }


    public Set<Reporte> getReportes() {
        if (reportes.isEmpty()) {
            reportes.add(new Reporte(Dominios.CategoriaReporte.PERSONAS, 0, "SINI_D_CartaAval_001", "SINI_D_CartaAval_001", "SINI_D_CartaAval_001", null, "Carta 8½ x 11 Vertical", false));
        }
        return reportes;
    }

    /**
     * Fecha de Emision de Carta Aval
     * @return the fechaEmision
     */
    public Date getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Fecha de realizacion del Presupuesto
     * @return the fechaPresupuesto
     */
    public Date getFechaPresupuesto() {
        return fechaPresupuesto;
    }

    /**
     * Numero del presupuesto
     * @return the numeroPresupuesto
     */
    public String getNumeroPresupuesto() {
        return numeroPresupuesto;
    }

    /**
     * Fecha de Emision de Carta Aval
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Fecha de realizacion del Presupuesto
     * @param fechaPresupuesto the fechaPresupuesto to set
     */
    public void setFechaPresupuesto(Date fechaPresupuesto) {
        this.fechaPresupuesto = fechaPresupuesto;
    }

    /**
     * Numero del presupuesto
     * @param numeroPresupuesto the numeroPresupuesto to set
     */
    public void setNumeroPresupuesto(String numeroPresupuesto) {
        this.numeroPresupuesto = numeroPresupuesto;
    }   
}
