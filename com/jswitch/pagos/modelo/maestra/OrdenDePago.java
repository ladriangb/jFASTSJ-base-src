package com.jswitch.pagos.modelo.maestra;

import com.jswitch.base.modelo.entidades.Documento;
import com.jswitch.base.modelo.entidades.NotaTecnica;
import com.jswitch.base.modelo.entidades.Observacion;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.fas.modelo.Dominios;
import com.jswitch.fas.modelo.Dominios.TipoDetalleSiniestro;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;

/**
 * @author Personal
 */
@Entity
@Table(name = "PAGO_OrdenDePago")
public class OrdenDePago extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Numero de la Orden de Pago
     */
    @Column
    @Pattern(regexp = "[0-9][0-9]?-[0-9][0-9][0-9][0-9]-[0-9]+",
    message = "Numero de Orden debe ser formato mes-año-numero Consecutivo")
    private String numeroOrden;
    /**
     * referencia del pago
     */
    @Column
    private String referencia;
    /**
     * Codigo del Sistema SIGECOF O KERSUS
     */
    @Column
    @Pattern(regexp = "[0-9][0-9]?-[0-9][0-9][0-9][0-9]-[0-9]+",
    message = "Codigo SIGECOF Invalido")
    private String codigoSIGECOF;
    /**
     * Remesa a la cual esta vinculado el pago
     */
    @ManyToOne()
    private Remesa remesa;
    /**
     * persona ala cual se le realizara el pago
     */
    @ManyToOne()
    private Persona personaPago;
    /**
     * Fecha en que se hace el pago
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    private Date fechaPago;
    /**
     * Estado en el que se encuentra el pago
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Dominios.EstatusPago estatusPago;
    /**
     * suma de facturas dentro de todos los detalles de la orden
     */
    @Column
    private Integer cantidadFacturas;
    /**
     * Cantidad de Detalles Siniestros en la orden de pago
     */
    @Column
    private Integer cantidadDetalles;
    /**
     * Cantidad de Siniestros de Titulares
     */
    @Column
    private Integer numeroSiniestrosTitular;
    /**
     * Cantidad de Siniestros de Famililiares asegurados
     */
    @Column
    private Integer numeroSiniestrosFamiliar;
    /**
     * Busqueda automatica de Detalles de Siniestro
     */
    @Column
    private Boolean autoSearch;
    /**
     * Si es seleccionado por una orden de pago
     */
    @Transient
    private transient Boolean selected;
    /**
     * Suma de todos los montos a pagar a Titulares
     */
    @Column
    private Double montoTitulares;
    /**
     * Suma de todos los montos a pagar a Beneficiarios
     */
    @Column
    private Double montoFamiliar;
    /**
     * monto a pagar
     */
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
     * tipo de detalles de siniestro a cancelar
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Dominios.TipoDetalleSiniestro tipoDetalleSiniestro;
    /**
     * version
     */
    @Version
    @Column
    private Integer optLock;
    /**
     * Auditoria bitacora
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;
    /**
     * Coleccion de etapas de siniestro y las fechas de los cambios
     */
    @OneToMany(fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<DetalleSiniestro> detalleSiniestros = new HashSet<DetalleSiniestro>(0);
    /**
     * observaciones generales
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<Observacion> observaciones = new ArrayList<Observacion>(0);
    /**
     * notas internas del sistema
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

    public OrdenDePago() {
        tipoDetalleSiniestro = TipoDetalleSiniestro.Todos;
    }

    /**
     * Auditoria bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * Busqueda automatica de Detalles de Siniestro
     * @return the autoSearch
     */
    public Boolean getAutoSearch() {
        return autoSearch;
    }

    /**
     * Codigo del Sistema SIGECOF O KERSUS
     * @return the codigoSIGECOF
     */
    public String getCodigoSIGECOF() {
        return codigoSIGECOF;
    }

    /**
     * Coleccion de etapas de siniestro y las fechas de los cambios
     * @return the detalleSiniestros
     */
    public Set<DetalleSiniestro> getDetalleSiniestros() {
        return detalleSiniestros;
    }

    /**
     * Coleccion de documentos anexos
     * @return the documentos
     */
    public Set<Documento> getDocumentos() {
        return documentos;
    }

    /**
     * Estado en el que se encuentra el pago
     * @return the estatusPago
     */
    public Dominios.EstatusPago getEstatusPago() {
        return estatusPago;
    }

    /**
     * Fecha en que se hace el pago
     * @return the fechaPago
     */
    public Date getFechaPago() {
        return fechaPago;
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
     * Suma de todos los montos a pagar a Beneficiarios
     * @return the montoFamiliar
     */
    public Double getMontoFamiliar() {
        return montoFamiliar;
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
     * Suma de todos los montos a pagar a Titulares
     * @return the montoTitulares
     */
    public Double getMontoTitulares() {
        return montoTitulares;
    }

    /**
     * notas internas del sistema
     * @return the notasTecnicas
     */
    public List<NotaTecnica> getNotasTecnicas() {
        return notasTecnicas;
    }

    /**
     * Numero de la Orden de Pago
     * @return the numeroOrden
     */
    public String getNumeroOrden() {
        return numeroOrden;
    }

    /**
     * Cantidad de Siniestros de Famililiares asegurados
     * @return the numeroSiniestrosFamiliar
     */
    public Integer getNumeroSiniestrosFamiliar() {
        return numeroSiniestrosFamiliar;
    }

    /**
     * Cantidad de Siniestros de Titulares
     * @return the numeroSiniestrosTitular
     */
    public Integer getNumeroSiniestrosTitular() {
        return numeroSiniestrosTitular;
    }

    /**
     * observaciones generales
     * @return the observaciones
     */
    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    /**
     * version
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * persona ala cual se le realizara el pago
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
     * referencia del pago
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Remesa a la cual esta vinculado el pago
     * @return the remesa
     */
    public Remesa getRemesa() {
        return remesa;
    }

    /**
     * Si es seleccionado por una orden de pago
     * @return the selected
     */
    public Boolean getSelected() {
        return selected;
    }

    /**
     * tipo de detalles de siniestro a cancelar
     * @return the tipoDetalleSiniestro
     */
    public Dominios.TipoDetalleSiniestro getTipoDetalleSiniestro() {
        return tipoDetalleSiniestro;
    }

    /**
     * Auditoria bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Busqueda automatica de Detalles de Siniestro
     * @param autoSearch the autoSearch to set
     */
    public void setAutoSearch(Boolean autoSearch) {
        this.autoSearch = autoSearch;
    }

    /**
     * Codigo del Sistema SIGECOF O KERSUS
     * @param codigoSIGECOF the codigoSIGECOF to set
     */
    public void setCodigoSIGECOF(String codigoSIGECOF) {
        this.codigoSIGECOF = codigoSIGECOF;
    }

    /**
     * Coleccion de etapas de siniestro y las fechas de los cambios
     * @param detalleSiniestros the detalleSiniestros to set
     */
    public void setDetalleSiniestros(Set<DetalleSiniestro> detalleSiniestros) {
        this.detalleSiniestros = detalleSiniestros;
    }

    /**
     * Coleccion de documentos anexos
     * @param documentos the documentos to set
     */
    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    /**
     * Estado en el que se encuentra el pago
     * @param estatusPago the estatusPago to set
     */
    public void setEstatusPago(Dominios.EstatusPago estatusPago) {
        this.estatusPago = estatusPago;
    }

    /**
     * Fecha en que se hace el pago
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
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
     * Suma de todos los montos a pagar a Beneficiarios
     * @param montoFamiliar the montoFamiliar to set
     */
    public void setMontoFamiliar(Double montoFamiliar) {
        this.montoFamiliar = montoFamiliar;
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
     * Suma de todos los montos a pagar a Titulares
     * @param montoTitulares the montoTitulares to set
     */
    public void setMontoTitulares(Double montoTitulares) {
        this.montoTitulares = montoTitulares;
    }

    /**
     * notas internas del sistema
     * @param notasTecnicas the notasTecnicas to set
     */
    public void setNotasTecnicas(List<NotaTecnica> notasTecnicas) {
        this.notasTecnicas = notasTecnicas;
    }

    /**
     * Numero de la Orden de Pago
     * @param numeroOrden the numeroOrden to set
     */
    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    /**
     * Cantidad de Siniestros de Famililiares asegurados
     * @param numeroSiniestrosFamiliar the numeroSiniestrosFamiliar to set
     */
    public void setNumeroSiniestrosFamiliar(Integer numeroSiniestrosFamiliar) {
        this.numeroSiniestrosFamiliar = numeroSiniestrosFamiliar;
    }

    /**
     * Cantidad de Siniestros de Titulares
     * @param numeroSiniestrosTitular the numeroSiniestrosTitular to set
     */
    public void setNumeroSiniestrosTitular(Integer numeroSiniestrosTitular) {
        this.numeroSiniestrosTitular = numeroSiniestrosTitular;
    }

    /**
     * observaciones generales
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * persona ala cual se le realizara el pago
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
     * referencia del pago
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * Remesa a la cual esta vinculado el pago
     * @param remesa the remesa to set
     */
    public void setRemesa(Remesa remesa) {
        this.remesa = remesa;
    }

    /**
     * Si es seleccionado por una orden de pago
     * @param selected the selected to set
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    /**
     * tipo de detalles de siniestro a cancelar
     * @param tipoDetalleSiniestro the tipoDetalleSiniestro to set
     */
    public void setTipoDetalleSiniestro(Dominios.TipoDetalleSiniestro tipoDetalleSiniestro) {
        this.tipoDetalleSiniestro = tipoDetalleSiniestro;
    }

    /**
     * Cantidad de Detalles Siniestros en la orden de pago
     * @return the cantidadDetalles
     */
    public Integer getCantidadDetalles() {
        return cantidadDetalles;
    }

    /**
     * suma de facturas dentro de todos los detalles de la orden
     * @return the cantidadFacturas
     */
    public Integer getCantidadFacturas() {
        return cantidadFacturas;
    }

    /**
     * Cantidad de Detalles Siniestros en la orden de pago
     * @param cantidadDetalles the cantidadDetalles to set
     */
    public void setCantidadDetalles(Integer cantidadDetalles) {
        this.cantidadDetalles = cantidadDetalles;
    }

    /**
     * suma de facturas dentro de todos los detalles de la orden
     * @param cantidadFacturas the cantidadFacturas to set
     */
    public void setCantidadFacturas(Integer cantidadFacturas) {
        this.cantidadFacturas = cantidadFacturas;
    }
}
