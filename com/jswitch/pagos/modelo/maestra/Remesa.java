package com.jswitch.pagos.modelo.maestra;

import com.jswitch.base.controlador.General;
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
import com.jswitch.persona.modelo.transac.CuentaBancariaPersona;
import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.vistasbd.SumaRemesa;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;

/**
 * @author Luis Adrian Gonzalez
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
     * suma de valores detalles
     */
    @OneToOne(mappedBy = "remesa")
    private SumaRemesa sumaRemesa;
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
    private Date fechaPagado;
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
    private String numNegociacion;
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
     *Identificacion del lote del pago.
     * Valor asignado por la Empresa
     * Ej. 00002100
     */
    @Column
    @BusinessKey
    private String refLot;
    /**
     *Identificacion del lote del pago.
     * Valor asignado por la Empresa
     * Ej. 00002100
     */
    @Column
    @BusinessKey
    private String numeroRemesa;
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
     * Cuenta bancaria de la empresa de la cual se debitara
     * 
     */
    @ManyToOne
    @BusinessKey
    private CuentaBancariaPersona cuentaBancaria;
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "remesa")
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
    /**
     * Reportes del modulo en la vista de detalle
     */
    @Transient
    private transient List<Reporte> reportes = new ArrayList<Reporte> (0);

    public Remesa() {
        estatusPago = EstatusPago.PENDIENTE;
        tipoDetalleSiniestro = Dominios.TipoDetalleSiniestro.Todos;
        if (General.parametros != null && General.parametros.get("remesa.numNeg") != null) {
            numNeg = General.parametros.get("remesa.numNeg").getValorInteger();
            numNegociacion = General.parametros.get("remesa.numNeg").getValorString();
        }
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
     * Cuenta bancaria de la empresa de la cual se debitara
     * @return the cuentaBancaria
     */
    public CuentaBancariaPersona getCuentaBancaria() {
        return cuentaBancaria;
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
     * @return the fechaPagado
     */
    public Date getFechaPagado() {
        return fechaPagado;
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
     * Numero de negociacion
     * Valor asignado por el Banco.  Será informado por éste a la implantación del servicio.
     * Ej. 00002100
     * @return the numNegociacion
     */
    public String getNumNegociacion() {
        return numNegociacion;
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
     * Identificacion del lote del pago.
     * Valor asignado por la Empresa
     * Ej. 00002100
     * @return the numeroRemesa
     */
    public String getNumeroRemesa() {
        return numeroRemesa;
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
     * Identificacion del lote del pago.
     * Valor asignado por la Empresa
     * Ej. 00002100
     * @return the refLot
     */
    public String getRefLot() {
        return refLot;
    }

    /**
     * suma de valores detalles
     * @return the sumaRemesa
     */
    public SumaRemesa getSumaRemesa() {
        return sumaRemesa;
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
     * Cuenta bancaria de la empresa de la cual se debitara
     * @param cuentaBancaria the cuentaBancaria to set
     */
    public void setCuentaBancaria(CuentaBancariaPersona cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
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
        this.documentos = documentos;
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
     * @param fechaPagado the fechaPagado to set
     */
    public void setFechaPagado(Date fechaPagado) {
        this.fechaPagado = fechaPagado;
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
     * nota interna de la empresa
     * @param notasTecnicas the notasTecnicas to set
     */
    public void setNotasTecnicas(List<NotaTecnica> notasTecnicas) {
        this.notasTecnicas = notasTecnicas;
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
     * Numero de negociacion
     * Valor asignado por el Banco.  Será informado por éste a la implantación del servicio.
     * Ej. 00002100
     * @param numNegociacion the numNegociacion to set
     */
    public void setNumNegociacion(String numNegociacion) {
        this.numNegociacion = numNegociacion;
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
     * Identificacion del lote del pago.
     * Valor asignado por la Empresa
     * Ej. 00002100
     * @param numeroRemesa the numeroRemesa to set
     */
    public void setNumeroRemesa(String numeroRemesa) {
        this.numeroRemesa = numeroRemesa;
    }

    /**
     * coleccion de Observaciones Generales
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
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
        this.ordenDePagos = ordenDePagos;
    }

    /**
     * Identificacion del lote del pago.
     * Valor asignado por la Empresa
     * Ej. 00002100
     * @param refLot the refLot to set
     */
    public void setRefLot(String refLot) {
        this.refLot = refLot;
    }

    /**
     * suma de valores detalles
     * @param sumaRemesa the sumaRemesa to set
     */
    public void setSumaRemesa(SumaRemesa sumaRemesa) {
        this.sumaRemesa = sumaRemesa;
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
     * Reportes del modulo en la vista de detalle
     * @return the reportes
     */
    public List<Reporte> getReportes() {
        if (reportes.isEmpty()) {
            reportes.add(new Reporte(com.jswitch.base.modelo.Dominios.CategoriaReporte.REMESAS, 0, "REM-D001",
                    "LIQUIDACION DE REMESA",
                    "Remesa",
                    null,
                    "Carta 8½ x 11 Horizontal",
                    false, false, false, false));
            reportes.add(new Reporte(com.jswitch.base.modelo.Dominios.CategoriaReporte.REMESAS, 0, "REM-R004",
                    "Listado de Ordenes de Pago por Remesa",
                    "Agrupados por Remesa, Nombre de Persona a Pagar",
                    "FROM " + OrdenDePago.class.getName() + " as P "
                    + "WHERE P.remesa.id=" + id,
                    "Carta 8½ x 11 Vertical",
                    false, true, true, false));
            reportes.add(new Reporte(com.jswitch.base.modelo.Dominios.CategoriaReporte.REMESAS, 0, "REM-R005",
                    "REMESAS PAGADAS POR TIPO DE CLIENTE A PAGAR",
                    "Remesas Pagadas por Tipo de Cliente a Pagar",
                    "SELECT P.tipoPersona.nombre AS tipoPersona,"
                    + " count(P) AS cantSiniestros,"
                    + " sum(P.sumaDetalle.totalACancelar) AS total,"
                    + " sum(case when P.siniestro.certificado.titular.persona.id = P.siniestro.asegurado.persona.id then P.sumaDetalle.totalACancelar else 0 end) AS montoTitular,"
                    + " sum(case when P.siniestro.certificado.titular.persona.id = P.siniestro.asegurado.persona.id then 0.0 else P.sumaDetalle.totalACancelar end) AS montoBeneficiario,"
                    + " sum(P.sumaDetalle.montoRetencionIslr) AS isrl "
                    + " FROM " + DetalleSiniestro.class.getName() + " AS P"
                    + " WHERE P.ordenDePago.remesa.id= " + id
                    + " GROUP BY P.tipoPersona.nombre",
                    "Carta 8½ x 11 Horizontal",
                    false, true, true, true));

            reportes.add(new Reporte(com.jswitch.base.modelo.Dominios.CategoriaReporte.REMESAS, 0, "REM-R006",
                    "REMESAS PAGADAS POR TIPO DE SINIESTRO",
                    "Remesas Pagadas por Tipo por Tipo Siniestro",
                    "SELECT P.tipoSiniestro.nombre AS tipoPersona,"
                    + " count(P) AS cantSiniestros,"
                    + " sum(P.sumaDetalle.totalACancelar) AS total,"
                    + " sum(case when P.siniestro.certificado.titular.persona.id = P.siniestro.asegurado.persona.id then P.sumaDetalle.totalACancelar else 0 end) AS montoTitular,"
                    + " sum(case when P.siniestro.certificado.titular.persona.id = P.siniestro.asegurado.persona.id then 0.0 else P.sumaDetalle.totalACancelar end) AS montoBeneficiario,"
                    + " sum(P.sumaDetalle.montoRetencionIslr) AS isrl "
                    + " FROM " + DetalleSiniestro.class.getName() + " AS P"
                    + " WHERE P.ordenDePago.remesa.id= " + id
                    + " GROUP BY P.tipoSiniestro.nombre",
                    "Carta 8½ x 11 Horizontal",
                    false, true, true, true));

            reportes.add(new Reporte(com.jswitch.base.modelo.Dominios.CategoriaReporte.REMESAS, 0, "REM-R007",
                    "REMESAS PAGADAS POR TIPO TRAMITE",
                    "Remesas Pagadas por Tipo de Tramite",
                    "SELECT P.tipoDetalle AS tipoPersona,"
                    + " count(P) AS cantSiniestros,"
                    + " sum(P.sumaDetalle.totalACancelar) AS total,"
                    + " sum(case when P.siniestro.certificado.titular.persona.id = P.siniestro.asegurado.persona.id then P.sumaDetalle.totalACancelar else 0 end) AS montoTitular,"
                    + " sum(case when P.siniestro.certificado.titular.persona.id = P.siniestro.asegurado.persona.id then 0.0 else P.sumaDetalle.totalACancelar end) AS montoBeneficiario,"
                    + " sum(P.sumaDetalle.montoRetencionIslr) AS isrl "
                    + " FROM " + DetalleSiniestro.class.getName() + " AS P"
                    + " WHERE P.ordenDePago.remesa.id= " + id
                    + " GROUP BY P.tipoDetalle",
                    "Carta 8½ x 11 Horizontal",
                    false, true, true, true));
             reportes.add(new Reporte(com.jswitch.base.modelo.Dominios.CategoriaReporte.REMESAS, 0, "REM-R007",
                    "REMESAS PAGADAS POR TIPO DE CONTRATO",
                    "Remesas Pagadas por Tipo de Contrato",
                    "SELECT P.tipoContrato.nombre AS tipoPersona,"
                    + " count(P) AS cantSiniestros,"
                    + " sum(P.sumaDetalle.totalACancelar) AS total,"
                    + " sum(case when P.siniestro.certificado.titular.persona.id = P.siniestro.asegurado.persona.id then P.sumaDetalle.totalACancelar else 0 end) AS montoTitular,"
                    + " sum(case when P.siniestro.certificado.titular.persona.id = P.siniestro.asegurado.persona.id then 0.0 else P.sumaDetalle.totalACancelar end) AS montoBeneficiario,"
                    + " sum(P.sumaDetalle.montoRetencionIslr) AS isrl "
                    + " FROM " + DetalleSiniestro.class.getName() + " AS P"
                    + " WHERE P.ordenDePago.remesa.id= " + id
                    + " GROUP BY P.tipoContrato.nombre",
                    "Carta 8½ x 11 Horizontal",
                    false, true, true, true));
             reportes.add(new Reporte(com.jswitch.base.modelo.Dominios.CategoriaReporte.REMESAS, 0, "REM-R007",
                    "REMESAS PAGADAS POR PARTIDA PRESUPUESTARIA",
                    "Remesas Pagadas por Partida Presupuestaria",
                    "SELECT P.tipoContrato.partidaPresupuestaria.nombre AS tipoPersona,"
                    + " count(P) AS cantSiniestros,"
                    + " sum(P.sumaDetalle.totalACancelar) AS total,"
                    + " sum(case when P.siniestro.certificado.titular.persona.id = P.siniestro.asegurado.persona.id then P.sumaDetalle.totalACancelar else 0 end) AS montoTitular,"
                    + " sum(case when P.siniestro.certificado.titular.persona.id = P.siniestro.asegurado.persona.id then 0.0 else P.sumaDetalle.totalACancelar end) AS montoBeneficiario,"
                    + " sum(P.sumaDetalle.montoRetencionIslr) AS isrl "
                    + " FROM " + DetalleSiniestro.class.getName() + " AS P"
                    + " WHERE P.ordenDePago.remesa.id= " + id
                    + " GROUP BY P.tipoContrato.partidaPresupuestaria.nombre",
                    "Carta 8½ x 11 Horizontal",
                    false, true, true, true));
             reportes.add(new Reporte(com.jswitch.base.modelo.Dominios.CategoriaReporte.REMESAS, 0, "REM-R007",
                    "REMESAS PAGADAS POR PARENTESCO",
                    "Remesas Pagadas por Parentesco",
                    "SELECT P.siniestro.asegurado.parentesco.nombre AS tipoPersona,"
                    + " count(P) AS cantSiniestros,"
                    + " sum(P.sumaDetalle.totalACancelar) AS total,"
                    + " sum(case when P.siniestro.certificado.titular.persona.id = P.siniestro.asegurado.persona.id then P.sumaDetalle.totalACancelar else 0 end) AS montoTitular,"
                    + " sum(case when P.siniestro.certificado.titular.persona.id = P.siniestro.asegurado.persona.id then 0.0 else P.sumaDetalle.totalACancelar end) AS montoBeneficiario,"
                    + " sum(P.sumaDetalle.montoRetencionIslr) AS isrl "
                    + " FROM " + DetalleSiniestro.class.getName() + " AS P"
                    + " WHERE P.ordenDePago.remesa.id= " + id
                    + " GROUP BY P.siniestro.asegurado.parentesco.nombre",
                    "Carta 8½ x 11 Horizontal",
                    false, true, true, true));
        }
        return reportes;
    }

    /**
     * Reportes del modulo en la vista de detalle
     * @param aReportes the reportes to set
     */
    public void setReportes(List<Reporte> aReportes) {
        reportes = aReportes;
    }
}
