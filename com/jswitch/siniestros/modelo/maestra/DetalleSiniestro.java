package com.jswitch.siniestros.modelo.maestra;

import com.jswitch.asegurados.modelo.dominio.TipoContrato;
import com.jswitch.base.modelo.Dominios;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.base.modelo.entidades.Documento;
import com.jswitch.base.modelo.entidades.NotaTecnica;
import com.jswitch.base.modelo.entidades.Observacion;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.dominio.Ramo;
import com.jswitch.fas.modelo.Dominios.TipoEnfermedad;
import com.jswitch.fas.modelo.Dominios.TratamientoEfectuado;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.siniestros.modelo.dominio.TipoSiniestro;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.vistasbd.SumaDesgloseCobertura;
import com.jswitch.vistasbd.SumaDetalle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

/**
 *
 * @author Adrian
 */
@Entity
@Table(name = "SINI_DetalleSiniestro")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class DetalleSiniestro extends BeanVO implements Serializable, Auditable {

    /**
     * reportes Generales
     * @return the reportesGenerales
     */
    public static Set<Reporte> getReportesGenerales() {
        return reportesGenerales;
    }

    /**
     * reportes Generales
     * @param aReportesGenerales the reportesGenerales to set
     */
    public static void setReportesGenerales(Set<Reporte> aReportesGenerales) {
        reportesGenerales = aReportesGenerales;
    }

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * numero detalle de siniestro
     */
    @Column
    private String numero;
    /**
     * para filtar la persona a la que se le va a pagar
     */
    @ManyToOne()
    private TipoPersona tipoPersona;
    /**
     * emplado, contratado, funcionario, jubilado, incapacitado, aprendis ince
     */
    @ManyToOne()
    private TipoContrato tipoContrato;
    /**
     * persona a realizar el pago
     */
    @ManyToOne
    @BusinessKey
    private Persona personaPago;
    /**
     * Orden de pago a la que esta
     */
    @ManyToOne
    @BusinessKey
    private OrdenDePago ordenDePago;
    /**
     * suma de valores por factura
     */
    @OneToOne(mappedBy = "detalleSiniestro")
    private SumaDetalle sumaDetalle;
    /**
     * siniestro q contiene el detalle
     */
    @ManyToOne
    private Siniestro siniestro;
    /**
     * tipo de enfermedad
     */
    @Column
    @Enumerated(EnumType.STRING)
    private TipoEnfermedad tipoEnfermedad;
    /**
     * tipo de tratamiento efectuado
     */
    @Column
    @Enumerated(EnumType.STRING)
    private TratamientoEfectuado tratamientoEfectuado;
    /**
     * Tipo de siniestro  
     */
    @ManyToOne
    private TipoSiniestro tipoSiniestro;
    /**
     * persona q se encarga de la negociacion
     */
    @ManyToOne()
    private Persona analistaNegociador;
    /**
     * presupuesto inicial del siniestro
     */
    @Column
    @BusinessKey
    private Double presupuestadoInicial;
    /**
     * presupuesto despues de ajustar
     */
    @Column
    @BusinessKey
    private Double presupuestadoAjustado;
    /**
     * fecha en q se liquida el siniestro
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    @Past
    private Date fechaLiquidado;
    /**
     * fecha en que vence el Detalle
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Future
    @BusinessKey
    private Date fechaVencimiento;
    /**
     * Ramo del detalle
     */
    @ManyToOne
    @BusinessKey(exclude = Method.ALL)
    private Ramo ramo;
    /**
     * Coleccion de etapa en la que se encuentra de siniestro
     */
    @ManyToOne
    private EtapaSiniestro etapaSiniestro;
    /**
     * Fecha en que se hace el la orden de pago
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    private Date fechaAprobado;
    /**
     * fecha en q se paga  el siniestro
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    private Date fechaPagado;
    /**
     * tipo de detalle si es  
     * EMERGENCIA, CARTA_AVAL, REEMBOLSO, FUNERARIO, VIDA ...
     */
    @Column
    @BusinessKey
    private String tipoDetalle;
    /**
     * si esta siendo seleccionado
     */
    @Transient
    private transient Boolean selected;
    /**
     * lista de facturas de pagos
     */
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "detalleSiniestro")
    @BusinessKey(exclude = Method.ALL)
    private Set<SumaDesgloseCobertura> sumaDesgloseCoberturas = new HashSet<SumaDesgloseCobertura>();
    /**
     * lista de facturas de pagos
     */
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "detalleSiniestro")
    @BusinessKey(exclude = Method.ALL)
    private Set<Factura> pagos = new HashSet<Factura>();
    /**
     * observaciones generales
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<Observacion> observaciones = new ArrayList<Observacion>(0);
    /**
     * notas tecnicas asociadas
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<NotaTecnica> notasTecnicas = new ArrayList<NotaTecnica>(0);
    /**
     * Coleccion de documentos anexos
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Documento> documentos = new HashSet<Documento>(0);
    /**
     * Coleccion de diagnosticos asosiados
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detalleSiniestro")
    @BusinessKey(exclude = Method.ALL)
    private Set<DiagnosticoSiniestro> diagnosticoSiniestros = new HashSet<DiagnosticoSiniestro>(0);
    /**
     * reportes especificos
     */
    @Transient
    private transient Set<Reporte> reportes;
    /**
     * reportes Generales
     */
    @Transient
    private static transient Set<Reporte> reportesGenerales = new HashSet<Reporte>(0);
    /**
     * vesion
     */
    @Version
    @Column
    private Integer optLock;
    /**
     * auditoria bitacora
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    /**
     * crea una nueva instancia de DetalleSiniestro
     */
    public DetalleSiniestro() {
    }

    /**
     * persona q se encarga de la negociacion
     * @return the analistaNegociador
     */
    public Persona getAnalistaNegociador() {
        return analistaNegociador;
    }

    /**
     * auditoria bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * Coleccion de diagnosticos asosiados
     * @return the diagnosticoSiniestros
     */
    public Set<DiagnosticoSiniestro> getDiagnosticoSiniestros() {
        return diagnosticoSiniestros;
    }

    /**
     * Coleccion de documentos anexos
     * @return the documentos
     */
    public Set<Documento> getDocumentos() {
        return documentos;
    }

    /**
     * Coleccion de etapa en la que se encuentra de siniestro
     * @return the etapaSiniestro
     */
    public EtapaSiniestro getEtapaSiniestro() {
        return etapaSiniestro;
    }

    /**
     * Fecha en que se hace el la orden de pago
     * @return the fechaAprobado
     */
    public Date getFechaAprobado() {
        return fechaAprobado;
    }

    /**
     * fecha en q se liquida el siniestro
     * @return the fechaLiquidado
     */
    public Date getFechaLiquidado() {
        return fechaLiquidado;
    }

    /**
     * fecha en q se paga  el siniestro
     * @return the fechaPagado
     */
    public Date getFechaPagado() {
        return fechaPagado;
    }

    /**
     * fecha en que vence el Detalle
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * notas tecnicas asociadas
     * @return the notasTecnicas
     */
    public List<NotaTecnica> getNotasTecnicas() {
        return notasTecnicas;
    }

    /**
     * numero detalle de siniestro
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * observaciones generales
     * @return the observaciones
     */
    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    /**
     * vesion
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * Orden de pago a la que esta
     * @return the ordenDePago
     */
    public OrdenDePago getOrdenDePago() {
        return ordenDePago;
    }

    /**
     * lista de facturas de pagos
     * @return the pagos
     */
    public Set<Factura> getPagos() {
        return pagos;
    }

    /**
     * persona a realizar el pago
     * @return the personaPago
     */
    public Persona getPersonaPago() {
        return personaPago;
    }

    /**
     * presupuesto despues de ajustar
     * @return the presupuestadoAjustado
     */
    public Double getPresupuestadoAjustado() {
        return presupuestadoAjustado;
    }

    /**
     * presupuesto inicial del siniestro
     * @return the presupuestadoInicial
     */
    public Double getPresupuestadoInicial() {
        return presupuestadoInicial;
    }

    /**
     * Ramo del detalle
     * @return the ramo
     */
    public Ramo getRamo() {
        return ramo;
    }

    /**
     * reportes especificos
     * @return the reportes
     */
    public Set<Reporte> getReportes() {
        return reportes;
    }

    /**
     * si esta siendo seleccionado
     * @return the selected
     */
    public Boolean getSelected() {
        return selected;
    }

    /**
     * siniestro q contiene el detalle
     * @return the siniestro
     */
    public Siniestro getSiniestro() {
        return siniestro;
    }

    /**
     * lista de facturas de pagos
     * @return the sumaDesgloseCoberturas
     */
    public Set<SumaDesgloseCobertura> getSumaDesgloseCoberturas() {
        return sumaDesgloseCoberturas;
    }

    /**
     * suma de valores por factura
     * @return the sumaDetalle
     */
    public SumaDetalle getSumaDetalle() {
        return sumaDetalle;
    }

    /**
     * emplado, contratado, funcionario, jubilado, incapacitado, aprendis ince
     * @return the tipoContrato
     */
    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    /**
     * tipo de detalle si es
     * EMERGENCIA, CARTA_AVAL, REEMBOLSO, FUNERARIO, VIDA ...
     * @return the tipoDetalle
     */
    public String getTipoDetalle() {
        return tipoDetalle;
    }

    /**
     * tipo de enfermedad
     * @return the tipoEnfermedad
     */
    public TipoEnfermedad getTipoEnfermedad() {
        return tipoEnfermedad;
    }

    /**
     * para filtar la persona a la que se le va a pagar
     * @return the tipoPersona
     */
    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Tipo de siniestro
     * @return the tipoSiniestro
     */
    public TipoSiniestro getTipoSiniestro() {
        return tipoSiniestro;
    }

    /**
     * tipo de tratamiento efectuado
     * @return the tratamientoEfectuado
     */
    public TratamientoEfectuado getTratamientoEfectuado() {
        return tratamientoEfectuado;
    }

    /**
     * persona q se encarga de la negociacion
     * @param analistaNegociador the analistaNegociador to set
     */
    public void setAnalistaNegociador(Persona analistaNegociador) {
        this.analistaNegociador = analistaNegociador;
    }

    /**
     * auditoria bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Coleccion de diagnosticos asosiados
     * @param diagnosticoSiniestros the diagnosticoSiniestros to set
     */
    public void setDiagnosticoSiniestros(Set<DiagnosticoSiniestro> diagnosticoSiniestros) {
        this.diagnosticoSiniestros = diagnosticoSiniestros;
    }

    /**
     * Coleccion de documentos anexos
     * @param documentos the documentos to set
     */
    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    /**
     * Coleccion de etapa en la que se encuentra de siniestro
     * @param etapaSiniestro the etapaSiniestro to set
     */
    public void setEtapaSiniestro(EtapaSiniestro etapaSiniestro) {
        this.etapaSiniestro = etapaSiniestro;
    }

    /**
     * Fecha en que se hace el la orden de pago
     * @param fechaAprobado the fechaAprobado to set
     */
    public void setFechaAprobado(Date fechaAprobado) {
        this.fechaAprobado = fechaAprobado;
    }

    /**
     * fecha en q se liquida el siniestro
     * @param fechaLiquidado the fechaLiquidado to set
     */
    public void setFechaLiquidado(Date fechaLiquidado) {
        this.fechaLiquidado = fechaLiquidado;
    }

    /**
     * fecha en q se paga  el siniestro
     * @param fechaPagado the fechaPagado to set
     */
    public void setFechaPagado(Date fechaPagado) {
        this.fechaPagado = fechaPagado;
    }

    /**
     * fecha en que vence el Detalle
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * notas tecnicas asociadas
     * @param notasTecnicas the notasTecnicas to set
     */
    public void setNotasTecnicas(List<NotaTecnica> notasTecnicas) {
        this.notasTecnicas = notasTecnicas;
    }

    /**
     * numero detalle de siniestro
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * observaciones generales
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * vesion
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * Orden de pago a la que esta
     * @param ordenDePago the ordenDePago to set
     */
    public void setOrdenDePago(OrdenDePago ordenDePago) {
        this.ordenDePago = ordenDePago;
    }

    /**
     * lista de facturas de pagos
     * @param pagos the pagos to set
     */
    public void setPagos(Set<Factura> pagos) {
        this.pagos = pagos;
    }

    /**
     * persona a realizar el pago
     * @param personaPago the personaPago to set
     */
    public void setPersonaPago(Persona personaPago) {
        this.personaPago = personaPago;
    }

    /**
     * presupuesto despues de ajustar
     * @param presupuestadoAjustado the presupuestadoAjustado to set
     */
    public void setPresupuestadoAjustado(Double presupuestadoAjustado) {
        this.presupuestadoAjustado = presupuestadoAjustado;
    }

    /**
     * presupuesto inicial del siniestro
     * @param presupuestadoInicial the presupuestadoInicial to set
     */
    public void setPresupuestadoInicial(Double presupuestadoInicial) {
        this.presupuestadoInicial = presupuestadoInicial;
    }

    /**
     * Ramo del detalle
     * @param ramo the ramo to set
     */
    public void setRamo(Ramo ramo) {
        this.ramo = ramo;
    }

    /**
     * reportes especificos
     * @param reportes the reportes to set
     */
    public void setReportes(Set<Reporte> reportes) {
        this.reportes = reportes;
    }

    /**
     * si esta siendo seleccionado
     * @param selected the selected to set
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    /**
     * siniestro q contiene el detalle
     * @param siniestro the siniestro to set
     */
    public void setSiniestro(Siniestro siniestro) {
        this.siniestro = siniestro;
    }

    /**
     * lista de facturas de pagos
     * @param sumaDesgloseCoberturas the sumaDesgloseCoberturas to set
     */
    public void setSumaDesgloseCoberturas(Set<SumaDesgloseCobertura> sumaDesgloseCoberturas) {
        this.sumaDesgloseCoberturas = sumaDesgloseCoberturas;
    }

    /**
     * suma de valores por factura
     * @param sumaDetalle the sumaDetalle to set
     */
    public void setSumaDetalle(SumaDetalle sumaDetalle) {
        this.sumaDetalle = sumaDetalle;
    }

    /**
     * emplado, contratado, funcionario, jubilado, incapacitado, aprendis ince
     * @param tipoContrato the tipoContrato to set
     */
    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    /**
     * tipo de detalle si es
     * EMERGENCIA, CARTA_AVAL, REEMBOLSO, FUNERARIO, VIDA ...
     * @param tipoDetalle the tipoDetalle to set
     */
    public void setTipoDetalle(String tipoDetalle) {
        this.tipoDetalle = tipoDetalle;
    }

    /**
     * tipo de enfermedad
     * @param tipoEnfermedad the tipoEnfermedad to set
     */
    public void setTipoEnfermedad(TipoEnfermedad tipoEnfermedad) {
        this.tipoEnfermedad = tipoEnfermedad;
    }

    /**
     * para filtar la persona a la que se le va a pagar
     * @param tipoPersona the tipoPersona to set
     */
    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    /**
     * Tipo de siniestro
     * @param tipoSiniestro the tipoSiniestro to set
     */
    public void setTipoSiniestro(TipoSiniestro tipoSiniestro) {
        this.tipoSiniestro = tipoSiniestro;
    }

    /**
     * tipo de tratamiento efectuado
     * @param tratamientoEfectuado the tratamientoEfectuado to set
     */
    public void setTratamientoEfectuado(TratamientoEfectuado tratamientoEfectuado) {
        this.tratamientoEfectuado = tratamientoEfectuado;
    }

}
