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
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.persona.modelo.dominio.TipoCuentaBancaria;
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
@Table(name = "PAGO_Remesa")
public class Remesa extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Estado en el que se encuentra el pago
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Dominios.EstatusPago estatusPago;
    /**
     * Fecha de Envio
     * Para uso interno de la empresa, con el objeto de identificar la 
     * fecha de envío del archivo de pago. 
     * Formato: dd/mm/aaaa 
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    private Date fechaEnvio;
    /**
     * Fecha en que se Pago la Remesa
     * Formato: dd/mm/aaaa
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    private Date fechaPago;    
    /**
     * Fecha de Pago Propuesta
     * Para uso interno de la empresa a objeto de identificar la fecha 
     * de generación del archivo de pago.
     * Formato: dd/mm/aaaa
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    private Date fechaPropuestaPago;
    /**
     *Numero de negociacion 
     * Valor asignado por el Banco.  Será informado por éste a la implantación del servicio.
     * Ej. 00002100
     */
    @Column
    @BusinessKey
    private Integer numNeg;
    /**
     *Identificacion del lote del pago.
     * Valor asignado por la Empresa
     * Ej. 00002100
     */
    @Column
    @BusinessKey
    private Integer numRefLot;
    /**
     * Tipo de Pago
     * Valor:
     * 40 – Proveedores
     * 00 – Transferencia SWIFT
     * 10– Abono en Cuenta Banco de Venezuela
     * 20– Cheque de Gerencia
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Dominios.TipoPago tipoPago;
    /**
     * duracion del cheque
     * puede ser 30 60 120 180
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Dominios.DuracionCheque duracionCheque;
    /**
     * Fecha Valor:
     * Fecha efectiva del Débito.
     * dd/mm/aaaa
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    private Date fechaValor;
    /**
     * cuenta de la empresa
     */
    @Column
    @Pattern(regexp = "\\d{20}", message = "Solo se permiten 20 numeros")
    @BusinessKey
    private String numeroCuentaDebitar;
    /**
     * Detalle de la Remesa
     */
    @Column
    @BusinessKey
    private String detalle;    
    /**
     * numero referencia Credito
     * Número asignado por la empresa que identifica el crédito. Es
     * utilizado para identificar la nota de crédito en el estado de
     * cuenta del beneficiario.
     * Ej. 00000015
     */
    private Integer numRefCre;
    /**
     * Número de Referencia del Debito
     * Número asignado por la empresa que identifica al débito. Es utilizado
     * para reconocer la Nota de Débito en el Estado de Cuenta del ordenante.
     * Ej. 00000015
     */
    private Integer numRefDeb;
    /**
     * tipo de cuenta
     * corriente 00
     * ahorro    10
     * default   00
     * 
     */
    @ManyToOne
    @BusinessKey
    private TipoCuentaBancaria tipoCuenta;
    /**
     * monto a pagar
     */
    @Column
    private Double montoPagar;
    /**
     * Suma de todos los montos a pagar a Titulares
     */
    @Column
    private Double montoTitulares;
    /**
     * Suma de todos los timbres municipales
     */
    @Column
    private Double montoTimbreMunicipal;    
    /**
     * Suma de todos los montos a pagar a Familiares asegurados
     */
    @Column
    private Double montoFamiliares;
    /**
     * Cantidad de Ordenes de Pago asociadas a la remesa
     */
    @Column
    private Integer numeroOrdenes;    
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
     * Cantidad de Detalles Siniestros en la orden de pago
     */
    @Column
    private Integer cantidadOrdenes;
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
    /**
     * Busqueda automatica de Ordenes de Pago
     */
    @Transient
    private transient Boolean autoSearch;
    /**
     * Version
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
     * Tipos de detalle siniestro q buscnara en las ordenes de pago
     * where this.tipoDetalleSiniestro.equals(
     *                          ordenDePago.getTipoDetalleSiniestro())
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Dominios.TipoDetalleSiniestro tipoDetalleSiniestro;
    /**
     * Coleccion de etapas de siniestro y las fechas de los cambios
     */
    @OneToMany(fetch = FetchType.LAZY,mappedBy="remesa")
    @BusinessKey(exclude = Method.ALL)
    private Set<OrdenDePago> ordenDePagos = new HashSet<OrdenDePago>(0);
    /**
     * coleccion de Observaciones Generales
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<Observacion> observaciones = new ArrayList<Observacion>(0);
    /**
     * nota interna de la empresa
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

    public Remesa() {
        estatusPago = EstatusPago.PENDIENTE;
        tipoDetalleSiniestro = Dominios.TipoDetalleSiniestro.Todos;
    }

    /**
     * Auditoria bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * Busqueda automatica de Ordenes de Pago
     * @return the autoSearch
     */
    public Boolean getAutoSearch() {
        return autoSearch;
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
     * Detalle de la Remesa
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * Coleccion de documentos anexos
     * @return the documentos
     */
    public Set<Documento> getDocumentos() {
        return documentos;
    }

    /**
     * duracion del cheque
     * puede ser 30 60 120 180
     * @return the duracionCheque
     */
    public Dominios.DuracionCheque getDuracionCheque() {
        return duracionCheque;
    }

    /**
     * Estado en el que se encuentra el pago
     * @return the estatusPago
     */
    public Dominios.EstatusPago getEstatusPago() {
        return estatusPago;
    }

    /**
     * Fecha de Envio
     * Para uso interno de la empresa, con el objeto de identificar la
     * fecha de envío del archivo de pago.
     * Formato: dd/mm/aaaa
     * @return the fechaEnvio
     */
    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * Fecha en que se Pago la Remesa
     * Formato: dd/mm/aaaa
     * @return the fechaPago
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * Fecha de Pago Propuesta
     * Para uso interno de la empresa a objeto de identificar la fecha
     * de generación del archivo de pago.
     * Formato: dd/mm/aaaa
     * @return the fechaPropuestaPago
     */
    public Date getFechaPropuestaPago() {
        return fechaPropuestaPago;
    }

    /**
     * Fecha Valor:
     * Fecha efectiva del Débito.
     * dd/mm/aaaa
     * @return the fechaValor
     */
    public Date getFechaValor() {
        return fechaValor;
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
     * Suma de todos los montos a pagar a Familiares asegurados
     * @return the montoFamiliares
     */
    public Double getMontoFamiliares() {
        return montoFamiliares;
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
     * monto a pagar
     * @return the montoPagar
     */
    public Double getMontoPagar() {
        return montoPagar;
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
     * Suma de todos los timbres municipales
     * @return the montoTimbreMunicipal
     */
    public Double getMontoTimbreMunicipal() {
        return montoTimbreMunicipal;
    }

    /**
     * Suma de todos los montos a pagar a Titulares
     * @return the montoTitulares
     */
    public Double getMontoTitulares() {
        return montoTitulares;
    }

    /**
     * nota interna de la empresa
     * @return the notasTecnicas
     */
    public List<NotaTecnica> getNotasTecnicas() {
        return notasTecnicas;
    }

    /**
     * Numero de negociacion
     * Valor asignado por el Banco.  Será informado por éste a la implantación del servicio.
     * Ej. 00002100
     * @return the numNeg
     */
    public Integer getNumNeg() {
        return numNeg;
    }

    /**
     * numero referencia Credito
     * Número asignado por la empresa que identifica el crédito. Es
     * utilizado para identificar la nota de crédito en el estado de
     * cuenta del beneficiario.
     * Ej. 00000015
     * @return the numRefCre
     */
    public Integer getNumRefCre() {
        return numRefCre;
    }

    /**
     * Número de Referencia del Debito
     * Número asignado por la empresa que identifica al débito. Es utilizado
     * para reconocer la Nota de Débito en el Estado de Cuenta del ordenante.
     * Ej. 00000015
     * @return the numRefDeb
     */
    public Integer getNumRefDeb() {
        return numRefDeb;
    }

    /**
     * Identificacion del lote del pago.
     * Valor asignado por la Empresa
     * Ej. 00002100
     * @return the numRefLot
     */
    public Integer getNumRefLot() {
        return numRefLot;
    }

    /**
     * cuenta de la empresa
     * @return the numeroCuentaDebitar
     */
    public String getNumeroCuentaDebitar() {
        return numeroCuentaDebitar;
    }

    /**
     * Cantidad de Ordenes de Pago asociadas a la remesa
     * @return the numeroOrdenes
     */
    public Integer getNumeroOrdenes() {
        return numeroOrdenes;
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
     * coleccion de Observaciones Generales
     * @return the observaciones
     */
    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    /**
     * Version
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * Coleccion de etapas de siniestro y las fechas de los cambios
     * @return the ordenDePagos
     */
    public Set<OrdenDePago> getOrdenDePagos() {
        return ordenDePagos;
    }

    /**
     * porcentaje pronto pago
     * @return the porcentajeProntoPago
     */
    public Double getPorcentajeProntoPago() {
        return porcentajeProntoPago;
    }

    /**
     * tipo de cuenta
     * corriente 00
     * ahorro    10
     * default   00
     * @return the tipoCuenta
     */
    public TipoCuentaBancaria getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Tipos de detalle siniestro q buscnara en las ordenes de pago
     * where this.tipoDetalleSiniestro.equals(
     * ordenDePago.getTipoDetalleSiniestro())
     * @return the tipoDetalleSiniestro
     */
    public Dominios.TipoDetalleSiniestro getTipoDetalleSiniestro() {
        return tipoDetalleSiniestro;
    }

    /**
     * Tipo de Pago
     * Valor:
     * 40 – Proveedores
     * 00 – Transferencia SWIFT
     * 10– Abono en Cuenta Banco de Venezuela
     * 20– Cheque de Gerencia
     * @return the tipoPago
     */
    public Dominios.TipoPago getTipoPago() {
        return tipoPago;
    }

    /**
     * Auditoria bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Busqueda automatica de Ordenes de Pago
     * @param autoSearch the autoSearch to set
     */
    public void setAutoSearch(Boolean autoSearch) {
        this.autoSearch = autoSearch;
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

    /**
     * Detalle de la Remesa
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * Coleccion de documentos anexos
     * @param documentos the documentos to set
     */
    public void setDocumentos(Set<Documento> documentos) {
        this.setDocumentos(documentos);
    }

    /**
     * duracion del cheque
     * puede ser 30 60 120 180
     * @param duracionCheque the duracionCheque to set
     */
    public void setDuracionCheque(Dominios.DuracionCheque duracionCheque) {
        this.duracionCheque = duracionCheque;
    }

    /**
     * Estado en el que se encuentra el pago
     * @param estatusPago the estatusPago to set
     */
    public void setEstatusPago(Dominios.EstatusPago estatusPago) {
        this.estatusPago = estatusPago;
    }

    /**
     * Fecha de Envio
     * Para uso interno de la empresa, con el objeto de identificar la
     * fecha de envío del archivo de pago.
     * Formato: dd/mm/aaaa
     * @param fechaEnvio the fechaEnvio to set
     */
    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    /**
     * Fecha en que se Pago la Remesa
     * Formato: dd/mm/aaaa
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Fecha de Pago Propuesta
     * Para uso interno de la empresa a objeto de identificar la fecha
     * de generación del archivo de pago.
     * Formato: dd/mm/aaaa
     * @param fechaPropuestaPago the fechaPropuestaPago to set
     */
    public void setFechaPropuestaPago(Date fechaPropuestaPago) {
        this.fechaPropuestaPago = fechaPropuestaPago;
    }

    /**
     * Fecha Valor:
     * Fecha efectiva del Débito.
     * dd/mm/aaaa
     * @param fechaValor the fechaValor to set
     */
    public void setFechaValor(Date fechaValor) {
        this.fechaValor = fechaValor;
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
     * Suma de todos los montos a pagar a Familiares asegurados
     * @param montoFamiliares the montoFamiliares to set
     */
    public void setMontoFamiliares(Double montoFamiliares) {
        this.montoFamiliares = montoFamiliares;
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
     * monto a pagar
     * @param montoPagar the montoPagar to set
     */
    public void setMontoPagar(Double montoPagar) {
        this.montoPagar = montoPagar;
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
     * Suma de todos los timbres municipales
     * @param montoTimbreMunicipal the montoTimbreMunicipal to set
     */
    public void setMontoTimbreMunicipal(Double montoTimbreMunicipal) {
        this.montoTimbreMunicipal = montoTimbreMunicipal;
    }

    /**
     * Suma de todos los montos a pagar a Titulares
     * @param montoTitulares the montoTitulares to set
     */
    public void setMontoTitulares(Double montoTitulares) {
        this.montoTitulares = montoTitulares;
    }

    /**
     * nota interna de la empresa
     * @param notasTecnicas the notasTecnicas to set
     */
    public void setNotasTecnicas(List<NotaTecnica> notasTecnicas) {
        this.setNotasTecnicas(notasTecnicas);
    }

    /**
     * Numero de negociacion
     * Valor asignado por el Banco.  Será informado por éste a la implantación del servicio.
     * Ej. 00002100
     * @param numNeg the numNeg to set
     */
    public void setNumNeg(Integer numNeg) {
        this.numNeg = numNeg;
    }

    /**
     * numero referencia Credito
     * Número asignado por la empresa que identifica el crédito. Es
     * utilizado para identificar la nota de crédito en el estado de
     * cuenta del beneficiario.
     * Ej. 00000015
     * @param numRefCre the numRefCre to set
     */
    public void setNumRefCre(Integer numRefCre) {
        this.numRefCre = numRefCre;
    }

    /**
     * Número de Referencia del Debito
     * Número asignado por la empresa que identifica al débito. Es utilizado
     * para reconocer la Nota de Débito en el Estado de Cuenta del ordenante.
     * Ej. 00000015
     * @param numRefDeb the numRefDeb to set
     */
    public void setNumRefDeb(Integer numRefDeb) {
        this.numRefDeb = numRefDeb;
    }

    /**
     * Identificacion del lote del pago.
     * Valor asignado por la Empresa
     * Ej. 00002100
     * @param numRefLot the numRefLot to set
     */
    public void setNumRefLot(Integer numRefLot) {
        this.numRefLot = numRefLot;
    }

    /**
     * cuenta de la empresa
     * @param numeroCuentaDebitar the numeroCuentaDebitar to set
     */
    public void setNumeroCuentaDebitar(String numeroCuentaDebitar) {
        this.numeroCuentaDebitar = numeroCuentaDebitar;
    }

    /**
     * Cantidad de Ordenes de Pago asociadas a la remesa
     * @param numeroOrdenes the numeroOrdenes to set
     */
    public void setNumeroOrdenes(Integer numeroOrdenes) {
        this.numeroOrdenes = numeroOrdenes;
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
     * coleccion de Observaciones Generales
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(List<Observacion> observaciones) {
        this.setObservaciones(observaciones);
    }

    /**
     * Version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * Coleccion de etapas de siniestro y las fechas de los cambios
     * @param ordenDePagos the ordenDePagos to set
     */
    public void setOrdenDePagos(Set<OrdenDePago> ordenDePagos) {
        this.setOrdenDePagos(ordenDePagos);
    }

    /**
     * porcentaje pronto pago
     * @param porcentajeProntoPago the porcentajeProntoPago to set
     */
    public void setPorcentajeProntoPago(Double porcentajeProntoPago) {
        this.porcentajeProntoPago = porcentajeProntoPago;
    }

    /**
     * tipo de cuenta
     * corriente 00
     * ahorro    10
     * default   00
     * @param tipoCuenta the tipoCuenta to set
     */
    public void setTipoCuenta(TipoCuentaBancaria tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * Tipos de detalle siniestro q buscnara en las ordenes de pago
     * where this.tipoDetalleSiniestro.equals(
     * ordenDePago.getTipoDetalleSiniestro())
     * @param tipoDetalleSiniestro the tipoDetalleSiniestro to set
     */
    public void setTipoDetalleSiniestro(Dominios.TipoDetalleSiniestro tipoDetalleSiniestro) {
        this.tipoDetalleSiniestro = tipoDetalleSiniestro;
    }

    /**
     * Tipo de Pago
     * Valor:
     * 40 – Proveedores
     * 00 – Transferencia SWIFT
     * 10– Abono en Cuenta Banco de Venezuela
     * 20– Cheque de Gerencia
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(Dominios.TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    /**
     * Cantidad de Detalles Siniestros en la orden de pago
     * @return the cantidadOrdenes
     */
    public Integer getCantidadOrdenes() {
        return cantidadOrdenes;
    }

    /**
     * Cantidad de Detalles Siniestros en la orden de pago
     * @param cantidadOrdenes the cantidadOrdenes to set
     */
    public void setCantidadOrdenes(Integer cantidadOrdenes) {
        this.cantidadOrdenes = cantidadOrdenes;
    }

}
