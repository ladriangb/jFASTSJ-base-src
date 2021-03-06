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
import com.jswitch.persona.modelo.transac.CuentaBancariaPersona;
import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.vistasbd.SumaOrden;
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
//    @Pattern(regexp = "[0-9][0-9]?-[0-9][0-9][0-9][0-9]-[0-9]+",
//    message = "Numero de Orden debe ser formato mes-año-numero Consecutivo")
    private String numeroOrden;
    /**
     * referencia del pago
     */
    @Column
    private String referencia;
    /**
     * Codigo del Sistema SIGECOF O KERSULS
     */
    @Column
//    @Pattern(regexp = "[0-9][0-9]?-[0-9][0-9][0-9][0-9]-[0-9]+",
//    message = "Codigo SIGECOF Invalido")
    private String codigoSIGECOF;
    /**
     * suma de valores detalles
     */
    @OneToOne(mappedBy = "ordenDePago")
    private SumaOrden sumaOrden;
    /**
     * Remesa a la cual esta vinculado el pago
     */
    @ManyToOne
    private Remesa remesa;
    /**
     * Remesa a la cual esta vinculado el pago
     */
    @ManyToOne
    private CuentaBancariaPersona cuentaBancaria;
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
    private Date fechaPagado;
    /**
     * Fecha en que se hace el la orden de pago
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    private Date fechaAprobado;
    /**
     * Estado en el que se encuentra el pago
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Dominios.EstatusPago estatusPago;
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ordenDePago")
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
    /**
     * Reportes de la orden
     */
    @Transient
    private transient Set<Reporte> reportes = new HashSet<Reporte>(0);

    public OrdenDePago() {
        tipoDetalleSiniestro = TipoDetalleSiniestro.Todos;
        estatusPago = Dominios.EstatusPago.PENDIENTE;
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
     * Codigo del Sistema SIGECOF O KERSULS
     * @return the codigoSIGECOF
     */
    public String getCodigoSIGECOF() {
        return codigoSIGECOF;
    }

    /**
     * Remesa a la cual esta vinculado el pago
     * @return the cuentaBancaria
     */
    public CuentaBancariaPersona getCuentaBancaria() {
        return cuentaBancaria;
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
     * Fecha en que se hace el la orden de pago
     * @return the fechaAprobado
     */
    public Date getFechaAprobado() {
        return fechaAprobado;
    }

    /**
     * Fecha en que se hace el pago
     * @return the fechaPagado
     */
    public Date getFechaPagado() {
        return fechaPagado;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
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
     * suma de valores detalles
     * @return the sumaOrden
     */
    public SumaOrden getSumaOrden() {
        return sumaOrden;
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
     * Codigo del Sistema SIGECOF O KERSULS
     * @param codigoSIGECOF the codigoSIGECOF to set
     */
    public void setCodigoSIGECOF(String codigoSIGECOF) {
        this.codigoSIGECOF = codigoSIGECOF;
    }

    /**
     * Remesa a la cual esta vinculado el pago
     * @param cuentaBancaria the cuentaBancaria to set
     */
    public void setCuentaBancaria(CuentaBancariaPersona cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
        org.openswing.swing.export.java.ExportToPDF14 a;
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
     * Fecha en que se hace el la orden de pago
     * @param fechaAprobado the fechaAprobado to set
     */
    public void setFechaAprobado(Date fechaAprobado) {
        this.fechaAprobado = fechaAprobado;
    }

    /**
     * Fecha en que se hace el pago
     * @param fechaPagado the fechaPagado to set
     */
    public void setFechaPagado(Date fechaPagado) {
        this.fechaPagado = fechaPagado;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * suma de valores detalles
     * @param sumaOrden the sumaOrden to set
     */
    public void setSumaOrden(SumaOrden sumaOrden) {
        this.sumaOrden = sumaOrden;
    }

    /**
     * tipo de detalles de siniestro a cancelar
     * @param tipoDetalleSiniestro the tipoDetalleSiniestro to set
     */
    public void setTipoDetalleSiniestro(Dominios.TipoDetalleSiniestro tipoDetalleSiniestro) {
        this.tipoDetalleSiniestro = tipoDetalleSiniestro;
    }

    /**
     * Reportes de la orden
     */
    public Set<Reporte> getReportes() {
        if (id != null) {
            if (reportes.isEmpty()) {
                reportes.add(new Reporte(com.jswitch.base.modelo.Dominios.CategoriaReporte.PAGOS,
                        com.jswitch.base.modelo.Dominios.FiltroReporte.FACTURA, 0, "PAGO_D_FACTURAS_001",
                        "FACTURAS EN LA ORDEN DE PAGO",
                        "DESCRIP",
                        "FROM " + Factura.class.getName() + " as P "
                        + "WHERE P.detalleSiniestro.ordenDePago.id=" + id 
                        + "ORDER BY P.detalleSiniestro.personaPago.id, "
                        + "P.detalleSiniestro.siniestro.certificado.titular.tipoContrato.id ",
                        "Carta 8½ x 11 Vertical",
                        false, true, true, false));
            }
        }
        return reportes;
    }
}
