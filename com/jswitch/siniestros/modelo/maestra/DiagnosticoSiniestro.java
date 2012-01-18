package com.jswitch.siniestros.modelo.maestra;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import com.jswitch.configuracion.modelo.dominio.patologias.Tratamiento;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 *
 * @author Adrian
 */
@Entity
@Table(name = "SINI_DiagnosticoSiniestro")
public class DiagnosticoSiniestro extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * detalle siniestro al q pertenece la reserva
     */
    @ManyToOne()
    @BusinessKey(exclude = Method.ALL)
    private DetalleSiniestro detalleSiniestro;
    /**
     *diagnostico al q se realizara la reserva de activos
     */
    @ManyToOne()
    @BusinessKey
    private Diagnostico diagnostico;
    /**
     * monto pendiente de la reserva actual
     */
    @Column
    @BusinessKey
    private Double montoPendiente;
    /**
     * monto q se ha canselado de la reserva actual
     */
    @Column
    @BusinessKey
    private Double montoPagado;
    /**
     * Total Disponible de la Cobertura
     */
    @Transient
    private transient Double totalDisponible;
    /**
     * total reservado hasta ahora
     */
    @Transient
    private transient Double totalReservado;
    /**
     * total que se ha utilizado
     */
    @Transient
    private transient Double totalUtilizado;
    /**
     * tratamiento general escrito por el medico
     */
    @Column
    @BusinessKey
    private String tratamientoEscrito;
    /**
     * Detalles
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Tratamiento> tratamientos = new HashSet<Tratamiento>(0);
    /**
     * version
     */
    @Version
    @Column
    private Integer optLock;
    /**
     * bitacora
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public DiagnosticoSiniestro() {
        montoPagado = 0d;
        montoPendiente = 0d;
    }

    public DiagnosticoSiniestro(DetalleSiniestro detalleSiniestro, Diagnostico diagnostico, Double montoPendiente) {
        this.detalleSiniestro = detalleSiniestro;
        this.diagnostico = diagnostico;
        this.montoPendiente = montoPendiente;
        this.montoPagado = 0d;
    }

    /**
     * bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * detalle siniestro al q pertenece la reserva
     * @return the detalleSiniestro
     */
    public DetalleSiniestro getDetalleSiniestro() {
        return detalleSiniestro;
    }

    /**
     * diagnostico al q se realizara la reserva de activos
     * @return the diagnostico
     */
    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * monto q se ha canselado de la reserva actual
     * @return the montoPagado
     */
    public Double getMontoPagado() {
        return montoPagado;
    }

    /**
     * monto pendiente de la reserva actual
     * @return the montoPendiente
     */
    public Double getMontoPendiente() {
        return montoPendiente;
    }

    /**
     * version
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * Total Disponible de la Cobertura
     * @return the totalDisponible
     */
    public Double getTotalDisponible() {
        return totalDisponible;
    }

    /**
     * total reservado hasta ahora
     * @return the totalReservado
     */
    public Double getTotalReservado() {
        return totalReservado;
    }

    /**
     * total que se ha utilizado
     * @return the totalUtilizado
     */
    public Double getTotalUtilizado() {
        return totalUtilizado;
    }

    /**
     * tratamiento general escrito por el medico
     * @return the tratamientoEscrito
     */
    public String getTratamientoEscrito() {
        return tratamientoEscrito;
    }

    /**
     * Detalles
     * @return the tratamientos
     */
    public Set<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    /**
     * bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * detalle siniestro al q pertenece la reserva
     * @param detalleSiniestro the detalleSiniestro to set
     */
    public void setDetalleSiniestro(DetalleSiniestro detalleSiniestro) {
        this.detalleSiniestro = detalleSiniestro;
    }

    /**
     * diagnostico al q se realizara la reserva de activos
     * @param diagnostico the diagnostico to set
     */
    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * monto q se ha canselado de la reserva actual
     * @param montoPagado the montoPagado to set
     */
    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }

    /**
     * monto pendiente de la reserva actual
     * @param montoPendiente the montoPendiente to set
     */
    public void setMontoPendiente(Double montoPendiente) {
        this.montoPendiente = montoPendiente;
    }

    /**
     * version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * Total Disponible de la Cobertura
     * @param totalDisponible the totalDisponible to set
     */
    public void setTotalDisponible(Double totalDisponible) {
        this.totalDisponible = totalDisponible;
    }

    /**
     * total reservado hasta ahora
     * @param totalReservado the totalReservado to set
     */
    public void setTotalReservado(Double totalReservado) {
        this.totalReservado = totalReservado;
    }

    /**
     * total que se ha utilizado
     * @param totalUtilizado the totalUtilizado to set
     */
    public void setTotalUtilizado(Double totalUtilizado) {
        this.totalUtilizado = totalUtilizado;
    }

    /**
     * tratamiento general escrito por el medico
     * @param tratamientoEscrito the tratamientoEscrito to set
     */
    public void setTratamientoEscrito(String tratamientoEscrito) {
        this.tratamientoEscrito = tratamientoEscrito;
    }

    /**
     * Detalles
     * @param tratamientos the tratamientos to set
     */
    public void setTratamientos(Set<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

}
