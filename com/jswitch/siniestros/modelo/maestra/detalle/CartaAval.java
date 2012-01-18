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
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaEmision;
    /**
     * 
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

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Set<Reporte> getReportes() {
        if (reportes.isEmpty()) {
            reportes.add(new Reporte(Dominios.CategoriaReporte.PERSONAS, 0, "SINI_D_CartaAval_001", "SINI_D_CartaAval_001", "SINI_D_CartaAval_001", null, "Carta 8½ x 11 Vertical",false));
        }
        return reportes;
    }
}
