package com.jswitch.siniestros.modelo.maestra.detalle;

import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.fas.utils.ReportesUtil;
import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
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
public class Emergencia extends DetalleSiniestro {

    /**
     * Reportes especificos de emergencia
     * @return the reportes
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaEntrada;
    /**
     * fecha en q sale del centro medico
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    private Date fechaSalida;
    /**
     * clave del siniestro
     */
    @Column
    @BusinessKey
    private String clave;
    /**
     * persona de contacto en la emergencia
     * (con quien estaba y con quien se puede comunicar)
     */
    @Column
    @BusinessKey
    private String nombreContacto;
    /**
     * Telefono de contacto en caso de esta emergencia 
     */
    @Column
    @BusinessKey
    private String telefonoContacto;
    /**
     * cantidad de dias que duro hospitalizado
     */
    @Transient
    private transient Integer diasHospitalizado;
    /**
     * Reportes especificos de emergencia
     */
    @Transient
    private static transient Set<Reporte> reportes = new HashSet<Reporte>(0);

    public Emergencia() {
        fechaEntrada = new Date();
    }

    /**
     * Reportes especificos de emergencia
     * @return the reportes Collection
     * 
     */
    @Override
    public Set<Reporte> getReportes() {
        if (reportes.isEmpty()) {
            reportes.addAll(getReportesGenerales());
        }
        return reportes;
    }

    /**
     * clave del siniestro
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * cantidad de dias que duro hospitalizado
     * @return the diasHospitalizado
     */
    public Integer getDiasHospitalizado() {
        if (diasHospitalizado == null) {
            if (fechaSalida == null) {
                setDiasHospitalizado(0);
            } else {
                setDiasHospitalizado(
                        ReportesUtil.diferenciaEnDias(fechaEntrada, fechaSalida));
            }
        }
        return diasHospitalizado;
    }

    /**
     * Reportes especificos de emergencia
     * @return the fechaEntrada
     */
    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    /**
     * fecha en q sale del centro medico
     * @return the fechaSalida
     */
    public Date getFechaSalida() {
        return fechaSalida;
    }

    /**
     * persona de contacto en la emergencia
     * (con quien estaba y con quien se puede comunicar)
     * @return the nombreContacto
     */
    public String getNombreContacto() {
        return nombreContacto;
    }

    /**
     * Telefono de contacto en caso de esta emergencia
     * @return the telefonoContacto
     */
    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    /**
     * clave del siniestro
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * cantidad de dias que duro hospitalizado
     * @param diasHospitalizado the diasHospitalizado to set
     */
    public void setDiasHospitalizado(Integer diasHospitalizado) {
        this.diasHospitalizado = diasHospitalizado;
    }

    /**
     * Reportes especificos de emergencia
     * @param fechaEntrada the fechaEntrada to set
     */
    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    /**
     * fecha en q sale del centro medico
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * persona de contacto en la emergencia
     * (con quien estaba y con quien se puede comunicar)
     * @param nombreContacto the nombreContacto to set
     */
    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    /**
     * Telefono de contacto en caso de esta emergencia
     * @param telefonoContacto the telefonoContacto to set
     */
    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }
}
