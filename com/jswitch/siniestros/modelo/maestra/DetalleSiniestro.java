package com.jswitch.siniestros.modelo.maestra;

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
import javax.persistence.JoinColumn;
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
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Numero de Detalle de siniestro
     */
    @Column
    @BusinessKey
    private Integer numero;
    /**
     * para filtar la persona a la que se le va a pagar
     */
    @ManyToOne()
    private TipoPersona tipoPersona;
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
    @OneToOne(mappedBy="detalleSiniestro")
    private SumaDetalle sumaDetalle;
    /**
     * siniestro q contiene el detalle
     */
    @ManyToOne
    private Siniestro siniestro;
    /**
     * total facturado en todas las facturas
     */
    @Column
    private Double montoRetenido;
    /**
     * total facturado en todas las facturas
     */
    @Column
    private Double montoFacturado;
    /**
     * total liquidado todas las facturas
     */
    @Column
    private Double montoLiquidado;
    /**
     * total a cancelar
     */
    @Column
    private Double montoACancelar;
    /**
     * total gastos medicos
     */
    @Column
    private Double montoHonorariosMedicos;
    /**
     * total gastos clinicos
     */
    @Column
    private Double montoGastosClinicos;
    /**
     * total Amparado
     */
    @Column
    private Double montoAmparado;
    /**
     * total monto no amparado
     */
    @Column
    private Double montoNoAmparado;
    /**
     * total base de la base del iva
     */
    @Column
    private Double montoBaseIva;
    /**
     * total monto del iva
     */
    @Column
    private Double montoIva;
    /**
     * monto retenido por iva
     */
    @Column
    private Double montoRetenidoIva;
    /**
     * total base de la base del islr
     */
    @Column
    private Double montoBaseIslr;
    /**
     * monto retenido por Islr
     */
    @Column
    private Double montoRetenidoIslr;
    /**
     * monto deducible
     */
    @Column
    private Double montoDeducible;
    /**
     * total monto Pronto Pago
     */
    @Column
    private Double montoProntoPago;
    /**
     * porcentaje pronto pago
     */
    @Column
    private Double porcentajeProntoPago;
    /**
     * total monto timbre municipal
     */
    @Column
    private Double montoTM;
    /**
     * Cantidad de facturas activas en el Detalle
     */
    @Column
    private Integer cantidadFacturas;
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
     * total a cancelar
     * @return the montoACancelar
     */
    public Double getMontoACancelar() {
        return montoACancelar;
    }

    /**
     * total Amparado
     * @return the montoAmparado
     */
    public Double getMontoAmparado() {
        return montoAmparado;
    }

    /**
     * total base de la base del islr
     * @return the montoBaseIslr
     */
    public Double getMontoBaseIslr() {
        return montoBaseIslr;
    }

    /**
     * total base de la base del iva
     * @return the montoBaseIva
     */
    public Double getMontoBaseIva() {
        return montoBaseIva;
    }

    /**
     * monto deducible
     * @return the montoDeducible
     */
    public Double getMontoDeducible() {
        return montoDeducible;
    }

    /**
     * total facturado en todas las facturas
     * @return the montoFacturado
     */
    public Double getMontoFacturado() {
        return montoFacturado;
    }

    /**
     * total gastos clinicos
     * @return the montoGastosClinicos
     */
    public Double getMontoGastosClinicos() {
        return montoGastosClinicos;
    }

    /**
     * total gastos medicos
     * @return the montoHonorariosMedicos
     */
    public Double getMontoHonorariosMedicos() {
        return montoHonorariosMedicos;
    }

    /**
     * total monto del iva
     * @return the montoIva
     */
    public Double getMontoIva() {
        return montoIva;
    }

    /**
     * total liquidado todas las facturas
     * @return the montoLiquidado
     */
    public Double getMontoLiquidado() {
        return montoLiquidado;
    }

    /**
     * total monto no amparado
     * @return the montoNoAmparado
     */
    public Double getMontoNoAmparado() {
        return montoNoAmparado;
    }

    /**
     * total monto Pronto Pago
     * @return the montoProntoPago
     */
    public Double getMontoProntoPago() {
        return montoProntoPago;
    }

    /**
     * total facturado en todas las facturas
     * @return the montoRetenido
     */
    public Double getMontoRetenido() {
        return montoRetenido;
    }

    /**
     * monto retenido por Islr
     * @return the montoRetenidoIslr
     */
    public Double getMontoRetenidoIslr() {
        return montoRetenidoIslr;
    }

    /**
     * monto retenido por iva
     * @return the montoRetenidoIva
     */
    public Double getMontoRetenidoIva() {
        return montoRetenidoIva;
    }

    /**
     * total monto timbre municipal
     * @return the montoTM
     */
    public Double getMontoTM() {
        return montoTM;
    }

    /**
     * notas tecnicas asociadas
     * @return the notasTecnicas
     */
    public List<NotaTecnica> getNotasTecnicas() {
        return notasTecnicas;
    }

    /**
     * Numero de Detalle de siniestro
     * @return the numero
     */
    public Integer getNumero() {
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
     * porcentaje pronto pago
     * @return the porcentajeProntoPago
     */
    public Double getPorcentajeProntoPago() {
        return porcentajeProntoPago;
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
        if (selected == null) {
            selected = Boolean.FALSE;
        }
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
     * total a cancelar
     * @param montoACancelar the montoACancelar to set
     */
    public void setMontoACancelar(Double montoACancelar) {
        this.montoACancelar = montoACancelar;
    }

    /**
     * total Amparado
     * @param montoAmparado the montoAmparado to set
     */
    public void setMontoAmparado(Double montoAmparado) {
        this.montoAmparado = montoAmparado;
    }

    /**
     * total base de la base del islr
     * @param montoBaseIslr the montoBaseIslr to set
     */
    public void setMontoBaseIslr(Double montoBaseIslr) {
        this.montoBaseIslr = montoBaseIslr;
    }

    /**
     * total base de la base del iva
     * @param montoBaseIva the montoBaseIva to set
     */
    public void setMontoBaseIva(Double montoBaseIva) {
        this.montoBaseIva = montoBaseIva;
    }

    /**
     * monto deducible
     * @param montoDeducible the montoDeducible to set
     */
    public void setMontoDeducible(Double montoDeducible) {
        this.montoDeducible = montoDeducible;
    }

    /**
     * total facturado en todas las facturas
     * @param montoFacturado the montoFacturado to set
     */
    public void setMontoFacturado(Double montoFacturado) {
        this.montoFacturado = montoFacturado;
    }

    /**
     * total gastos clinicos
     * @param montoGastosClinicos the montoGastosClinicos to set
     */
    public void setMontoGastosClinicos(Double montoGastosClinicos) {
        this.montoGastosClinicos = montoGastosClinicos;
    }

    /**
     * total gastos medicos
     * @param montoHonorariosMedicos the montoHonorariosMedicos to set
     */
    public void setMontoHonorariosMedicos(Double montoHonorariosMedicos) {
        this.montoHonorariosMedicos = montoHonorariosMedicos;
    }

    /**
     * total monto del iva
     * @param montoIva the montoIva to set
     */
    public void setMontoIva(Double montoIva) {
        this.montoIva = montoIva;
    }

    /**
     * total liquidado todas las facturas
     * @param montoLiquidado the montoLiquidado to set
     */
    public void setMontoLiquidado(Double montoLiquidado) {
        this.montoLiquidado = montoLiquidado;
    }

    /**
     * total monto no amparado
     * @param montoNoAmparado the montoNoAmparado to set
     */
    public void setMontoNoAmparado(Double montoNoAmparado) {
        this.montoNoAmparado = montoNoAmparado;
    }

    /**
     * total monto Pronto Pago
     * @param montoProntoPago the montoProntoPago to set
     */
    public void setMontoProntoPago(Double montoProntoPago) {
        this.montoProntoPago = montoProntoPago;
    }

    /**
     * total facturado en todas las facturas
     * @param montoRetenido the montoRetenido to set
     */
    public void setMontoRetenido(Double montoRetenido) {
        this.montoRetenido = montoRetenido;
    }

    /**
     * monto retenido por Islr
     * @param montoRetenidoIslr the montoRetenidoIslr to set
     */
    public void setMontoRetenidoIslr(Double montoRetenidoIslr) {
        this.montoRetenidoIslr = montoRetenidoIslr;
    }

    /**
     * monto retenido por iva
     * @param montoRetenidoIva the montoRetenidoIva to set
     */
    public void setMontoRetenidoIva(Double montoRetenidoIva) {
        this.montoRetenidoIva = montoRetenidoIva;
    }

    /**
     * total monto timbre municipal
     * @param montoTM the montoTM to set
     */
    public void setMontoTM(Double montoTM) {
        this.montoTM = montoTM;
    }

    /**
     * notas tecnicas asociadas
     * @param notasTecnicas the notasTecnicas to set
     */
    public void setNotasTecnicas(List<NotaTecnica> notasTecnicas) {
        this.notasTecnicas = notasTecnicas;
    }

    /**
     * Numero de Detalle de siniestro
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
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
     * porcentaje pronto pago
     * @param porcentajeProntoPago the porcentajeProntoPago to set
     */
    public void setPorcentajeProntoPago(Double porcentajeProntoPago) {
        this.porcentajeProntoPago = porcentajeProntoPago;
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

    /**
     * Cantidad de facturas activas en el Detalle
     * @return the cantidadFacturas
     */
    public Integer getCantidadFacturas() {
        return cantidadFacturas;
    }

    /**
     * Cantidad de facturas activas en el Detalle
     * @param cantidadFacturas the cantidadFacturas to set
     */
    public void setCantidadFacturas(Integer cantidadFacturas) {
        this.cantidadFacturas = cantidadFacturas;
    }

    /**
     * suma de valores por factura
     * @return the sumaDetalle
     */
    public SumaDetalle getSumaDetalle() {
        return sumaDetalle;
    }

    /**
     * suma de valores por factura
     * @param sumaDetalle the sumaDetalle to set
     */
    public void setSumaDetalle(SumaDetalle sumaDetalle) {
        this.sumaDetalle = sumaDetalle;
    }

    /**
     * Orden de pago a la que esta
     * @return the ordenDePago
     */
    public OrdenDePago getOrdenDePago() {
        return ordenDePago;
    }

    /**
     * Orden de pago a la que esta
     * @param ordenDePago the ordenDePago to set
     */
    public void setOrdenDePago(OrdenDePago ordenDePago) {
        this.ordenDePago = ordenDePago;
    }
}
