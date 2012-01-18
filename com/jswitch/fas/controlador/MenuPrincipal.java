package com.jswitch.fas.controlador;

import com.jswitch.asegurados.controlador.AseguradoDetailFrameController;
import com.jswitch.asegurados.controlador.AseguradoGridFrameController;
import com.jswitch.asegurados.controlador.BeneficiarioGridFrameController;
import com.jswitch.asegurados.modelo.dominio.Departamento;
import com.jswitch.asegurados.modelo.dominio.TipoContrato;
import com.jswitch.certificados.vista.CertificadoDetailFrame;
import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.helpcenter.CorreosGridFrameController;
import com.jswitch.base.controlador.sistema.usuarios.UsuarioDetailController;
import com.jswitch.base.controlador.sistema.usuarios.UsuariosGridController;
import com.jswitch.base.controlador.util.DefaultAllGridFrameController;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.Encabezado;
import com.jswitch.base.modelo.entidades.Licencia;
import com.jswitch.base.modelo.entidades.TipoDocumento;
import com.jswitch.base.modelo.entidades.Usuario;
import com.jswitch.base.modelo.entidades.helpcenter.maestra.Mensaje;
import com.jswitch.base.vista.mant.DefaultMantenimientoGridFrame;
import com.jswitch.base.vista.mant.EncabezadoGridFrame;
import com.jswitch.base.vista.mant.TipoDocumentoGridFrame;
import com.jswitch.base.vista.sistema.ConfiguracionDetailFrame;
import com.jswitch.base.vista.sistema.EmpresaDetailFrame;
import com.jswitch.base.vista.sistema.LicenciasGridFrame;
import com.jswitch.base.vista.sistema.UsuarioDetailFrame;
import com.jswitch.base.vista.sistema.UsuariosGridFrame;
import com.jswitch.certificados.controlador.CertificadoDetailController;
import com.jswitch.certificados.controlador.CertificadosGridController;
import com.jswitch.polizas.controlador.PolizaDetailFrameController;
import com.jswitch.asegurados.controlador.TitularDetailFrameController;
import com.jswitch.asegurados.controlador.TitularGridController;
import com.jswitch.configuracion.controlador.RamoCoberturaGridFrameController;
import com.jswitch.asegurados.modelo.dominio.Parentesco;
import com.jswitch.configuracion.modelo.maestra.Plan;
import com.jswitch.configuracion.modelo.dominio.PlazoEspera;
import com.jswitch.configuracion.modelo.dominio.Ramo;
import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.asegurados.modelo.maestra.Beneficiario;
import com.jswitch.certificados.modelo.maestra.Certificado;
import com.jswitch.polizas.modelo.maestra.Poliza;
import com.jswitch.asegurados.modelo.maestra.Titular;
import com.jswitch.asegurados.vista.AseguradoDetailFrame;
import com.jswitch.asegurados.vista.AseguradoGridFrame;
import com.jswitch.asegurados.vista.BeneficiarioDetailFrame;
import com.jswitch.asegurados.vista.BeneficiarioGridFrame;
import com.jswitch.asegurados.vista.BuscarAseguradoDialog;
import com.jswitch.certificados.vista.CertificadosGridFrame;
import com.jswitch.configuracion.controlador.ConfiguracionPrimaAllGridFrameController;
import com.jswitch.configuracion.modelo.maestra.ConfiguracionPrima;
import com.jswitch.configuracion.vista.ConfiguracionPrimaGridFrame;
import com.jswitch.configuracion.controlador.PlanesGridFrameController;
import com.jswitch.polizas.vista.PolizaDetailFrame;
import com.jswitch.polizas.vista.PolizasGridFrame;
import com.jswitch.asegurados.vista.TitularDetailFrame;
import com.jswitch.asegurados.vista.TitularGridFrame;
import com.jswitch.auditoria.controlador.LogGridController;
import com.jswitch.auditoria.vista.LogGridFrame;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.entidades.auditoria.AuditLogRecord;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.sistema.CambiarPassDialog;
import com.jswitch.certificados.controlador.CertificadoNuevoDetrailController;
import com.jswitch.certificados.modelo.utilitario.CertificadoNuevo;
import com.jswitch.certificados.vista.CertificadoNuevoDetailFrame;
import com.jswitch.configuracion.controlador.patologias.RamoGridFrameController;
import com.jswitch.configuracion.modelo.dominio.Cobertura;
import com.jswitch.configuracion.modelo.maestra.ConfiguracionCobertura;
import com.jswitch.pagos.vista.CoberturaGridFrame;
import com.jswitch.configuracion.vista.ConfiguracionCoberturaGridFrame;
import com.jswitch.configuracion.vista.PlanesGridFrame;
import com.jswitch.configuracion.vista.RamosCoberturasGridFrame;
import com.jswitch.configuracion.vista.patologias.PatologiasGridFrame;
import com.jswitch.pagos.controlador.OrdenDePagoDetailFrameController;
import com.jswitch.pagos.controlador.OrdenDePagoGridFrameController;
import com.jswitch.pagos.controlador.RemesaDetailFrameController;
import com.jswitch.pagos.controlador.RemesaGridFrameController;
import com.jswitch.persona.controlador.PersonasGridController;
import com.jswitch.persona.controlador.mant.TipoPersonaGridFrameController;
import com.jswitch.persona.modelo.dominio.TipoActividadEconomica;
import com.jswitch.persona.modelo.dominio.TipoCapacidadEconomica;
import com.jswitch.persona.modelo.dominio.TipoCodigoArea;
import com.jswitch.persona.modelo.dominio.TipoCuentaBancaria;
import com.jswitch.persona.modelo.dominio.TipoDireccion;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.persona.modelo.dominio.TipoTelefono2;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.persona.vista.PersonaDetailFrame;
import com.jswitch.persona.vista.Personas2GridFrame;
import com.jswitch.persona.vista.RifDialog;
import com.jswitch.persona.vista.mant.CodigoAreaGridFrame;
import com.jswitch.persona.vista.mant.TipoPersonaGridFrame;
import com.jswitch.polizas.controlador.PolizasGridFrameController;
import com.jswitch.reporte.controlador.ReportesGridController;
import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.reporte.vista.ReportesGridFrame;
import com.jswitch.rol.controlador.RolGridFrameController;
import com.jswitch.rol.controlador.RolOptionFrameController;
import com.jswitch.rol.modelo.Item;
import com.jswitch.rol.modelo.MenuByRol;
import com.jswitch.rol.modelo.Rol;
import com.jswitch.rol.vista.RolOptionFrame;
import com.jswitch.pagos.modelo.dominio.ConceptoSENIAT;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.modelo.maestra.Remesa;
import com.jswitch.pagos.vista.ConceptoSENIATGridFrame;
import com.jswitch.pagos.vista.OrdenDePagoDetailFrame;
import com.jswitch.pagos.vista.OrdenDePagoGridFrame;
import com.jswitch.pagos.vista.RemesaDetailFrame;
import com.jswitch.pagos.vista.RemesaGridFrame;
import com.jswitch.persona.controlador.PersonasDetailController;
import com.jswitch.persona.modelo.maestra.PersonaNatural;
import com.jswitch.persona.vista.BuscarPersonaDialog;
import com.jswitch.persona.vista.RifBusquedaDialog;
import com.jswitch.siniestros.controlador.SiniestroGridFrameController;
import com.jswitch.siniestros.controlador.detalle.DetalleSiniestroGridFrameController;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.dominio.TipoSiniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.APS;
import com.jswitch.siniestros.modelo.maestra.detalle.AyudaSocial;
import com.jswitch.siniestros.modelo.maestra.detalle.CartaAval;
import com.jswitch.siniestros.modelo.maestra.detalle.Emergencia;
import com.jswitch.siniestros.modelo.maestra.detalle.Funerario;
import com.jswitch.siniestros.modelo.maestra.detalle.Reembolso;
import com.jswitch.siniestros.modelo.maestra.detalle.Vida;
import com.jswitch.siniestros.vista.EtapaSiniestroGridFrame;
import com.jswitch.siniestros.vista.TipoSiniestroGridFrame;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroGridFrame;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
import de.muntjak.tinylookandfeel.TinyLookAndFeel;
import de.muntjak.tinylookandfeel.controlpanel.ControlPanel;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.OptionPane;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.mdi.client.*;
import org.openswing.swing.mdi.java.ApplicationFunction;
import org.openswing.swing.tree.java.OpenSwingTreeNode;

/**
 * @author Orlando Becerra
 * @author Enrique Becerra
 */
public class MenuPrincipal implements ClientFacade {

    Session s = null;
    private boolean tinyCP1 = true;
    private ControlPanel cp = null;

    private MenuByRol addFuntion(DefaultMutableTreeNode padre, Item funcion) {

        String hql = "FROM " + MenuByRol.class.getName()
                + " WHERE itemId=" + funcion.getId() + " AND "
                + "rolId=" + General.usuario.getRol().getId();
        Query query = s.createQuery(hql);
        MenuByRol mbr = (MenuByRol) query.list().get(0);
        boolean sw = (mbr).isEnable();

        if (sw) {
            ApplicationFunction hijo = null;
            if (funcion.getMetodo() != null) {
                hijo = new ApplicationFunction(funcion.getNombre(),
                        funcion.getFuncionId(), funcion.getIcono(),
                        funcion.getMetodo());
            } else {
                hijo = new ApplicationFunction(funcion.getNombre(),
                        funcion.getIcono());

            }
            padre.add(hijo);
            for (Item it : funcion.getItems()) {
                addFuntion(hijo, it);
            }
        }
        return mbr;
    }

    /**
     * Monta el menu dependiendo del rol del usuario
     */
    public DefaultTreeModel getApplicationFunctions() {
        DefaultMutableTreeNode root = new OpenSwingTreeNode("Menu");

        DefaultTreeModel model = new DefaultTreeModel(root);
        {
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                {
                    String hql = "FROM " + Item.class.getName() + " WHERE nombre='root'";
                    Query query = s.createQuery(hql);
                    List mensajes = query.list();
                    Item rootItem = (Item) mensajes.get(0);
                    for (Item it : rootItem.getItems()) {
                        //Collections.sort(it.getItems());
                        MenuByRol mbr = addFuntion(root, it);
                        General.permisologiaModulo.put(it.getNombre(), mbr);
                    }
                }
                t.commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                s.close();
            }

        }

        return model;
    }

    // <editor-fold defaultstate="collapsed" desc="Personas">
    public void getPersonas() {
        new PersonasGridController(Personas2GridFrame.class.getName(), PersonaDetailFrame.class.getName(), Persona.class.getName(), null);
    }

    public void getPersonaNueva() {
        new RifDialog();
    }

    public void getTipoPersona() {
        new TipoPersonaGridFrameController(TipoPersonaGridFrame.class.getName(), null, TipoPersona.class.getName(), "Tipos de Persona");
    }

    public void getTipoActividadEconomica() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoActividadEconomica.class.getName(), "Tipo Actividad Economica");
    }

    public void getTipoCapacidadEconomica() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoCapacidadEconomica.class.getName(), "Tipo Capacidad Economica");
    }

    public void getTipoTelefono() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoTelefono2.class.getName(), "Tipo Telefono");
    }

    public void getTipoDireccion() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoDireccion.class.getName(), "Tipo Direccion");
    }

    public void getTipoCuentaBancaria() {
        new DefaultAllGridFrameController(CodigoAreaGridFrame.class.getName(), null, TipoCuentaBancaria.class.getName(), "Tipo Cuenta Bancaria");
    }

    public void getCodigoArea() {
        new DefaultAllGridFrameController(CodigoAreaGridFrame.class.getName(), null, TipoCodigoArea.class.getName(), "Codigos de Area");
    }

    public void getBuscarPersona() {
        new BuscarPersonaDialog(null).setVisible(true);
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Asegurados">
    public void getNuevoTitular() {
        new TitularDetailFrameController(TitularDetailFrame.class.getName(), null, null, false);
    }

    public void getTitulares() {
        new TitularGridController(TitularGridFrame.class.getName(), TitularDetailFrame.class.getName(), Titular.class.getName(), null);
    }

    public void getTipoContrato() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoContrato.class.getName(), "Tipo de Contrato");
    }

    public void getDepartamento() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, Departamento.class.getName(), "Departamento");
    }

    public void getParentesco() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, Parentesco.class.getName(), "Parentesco");
    }

    public void getNuevoAsegurado() {
        new AseguradoDetailFrameController(AseguradoDetailFrame.class.getName(), null, null, false);
    }

    public void getBuscarAsegurado() {
        new BuscarAseguradoDialog("Buscar Asegurado").setVisible(true);
    }

    public void getAsegurados() {
        new AseguradoGridFrameController(AseguradoGridFrame.class.getName(), AseguradoDetailFrame.class.getName(), Asegurado.class.getName(), null);
    }

    public void getBeneficiarios() {
        new BeneficiarioGridFrameController(BeneficiarioGridFrame.class.getName(), BeneficiarioDetailFrame.class.getName(), Beneficiario.class.getName(), null);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Roles">
    public void getRoles() {
        new RolOptionFrameController(RolOptionFrame.class.getName(), Rol.class.getName(), "Roles");
    }

    public void getNuevoRol() {

        if (General.usuario.getAdministraUsuarios()) {
            new RolGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, Rol.class.getName(), "Nuevo Rol");
        } else {
            JOptionPane.showMessageDialog(MDIFrame.getInstance(), "Notiene permisos para crear Nuevos Roles", "Error de Validacion de Roles", JOptionPane.ERROR_MESSAGE);
        }
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Sistema Base">
    public void getEncabezado(String name) {
        new DefaultAllGridFrameController(EncabezadoGridFrame.class.getName(), null, Encabezado.class.getName(), "Encabezados de Reporte");
    }

    public void getTipoDocAnex(String name) {
        new DefaultAllGridFrameController(TipoDocumentoGridFrame.class.getName(), null, TipoDocumento.class.getName(), "Tipo Docuemtnos Anexos");
    }

    public void getTipoDocAnex() {
        new DefaultAllGridFrameController(TipoDocumentoGridFrame.class.getName(), null, TipoDocumento.class.getName(), "Tipo Docuemtnos Anexos");
    }

    public void getUsuarios(String name) {
        if (General.usuario.getAdministraUsuarios()) {
            new UsuariosGridController(UsuariosGridFrame.class.getName(), UsuarioDetailFrame.class.getName(), Usuario.class.getName(), null);
        } else {
            new UsuarioDetailController(UsuarioDetailFrame.class.getName(), null, General.usuario, false);
        }
    }

    public void getEmpresa(String name) {
        new DefaultDetailFrameController(EmpresaDetailFrame.class.getName(), null, General.empresa, false);
    }

    public void getConfiguracion(String name) {
        new DefaultDetailFrameController(ConfiguracionDetailFrame.class.getName(), null, General.empresa, false);
    }

    public void getLicencias(String name) {
        new DefaultAllGridFrameController(LicenciasGridFrame.class.getName(), null, Licencia.class.getName(), "Licencias");
    }

    public void getReporteH(String name) {
        new ReportesGridController(ReportesGridFrame.class.getName(), null, Reporte.class.getName(), null);
    }

    public void getHelpCenter() {
        new CorreosGridFrameController(Mensaje.class.getName());
    }

    public void getExit() {
        MDIFrame.getInstance().menuFileExit_actionPerformed(null);
    }

    public void getHelp() {
        if (Principal.helpManager != null) {
            Principal.helpManager.showNavigatorWindow();
        }
    }

    public void AUN_NO_FUN() {
        OptionPane.showMessageDialog(
                MDIFrame.getInstance(),
                "build",
                "warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public void getConfigLnF() {
        if (tinyCP1) {
            TinyLookAndFeel.controlPanelInstantiated = true;
            System.setProperty("swing.aatext", "true");
            try {
                UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            cp = new ControlPanel();
            cp.openTheme2(new File(System.getProperty("user.dir") + "/Default.theme"));
            tinyCP1 = false;
        } else if (cp != null) {
            cp.theFrame.setVisible(true);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Certificados">
    public void getCertificados() {
        new CertificadosGridController(CertificadosGridFrame.class.getName(), CertificadoDetailFrame.class.getName(), Certificado.class.getName(), null);
    }

    public void getNuevoCertificado() {
        new CertificadoDetailController(CertificadoDetailFrame.class.getName(), null, null, true);
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Configuracion">
    public void getConfiguracionPrima() {
        new ConfiguracionPrimaAllGridFrameController(ConfiguracionPrimaGridFrame.class.getName(), null, ConfiguracionPrima.class.getName(), "Configuracion de la Prima");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Coberturas">
    public void getConfiguracionCoberturas() {
        new DefaultAllGridFrameController(ConfiguracionCoberturaGridFrame.class.getName(), null, ConfiguracionCobertura.class.getName(),
                "Configuracion Cobertura");
    }

    public void getCoberturas() {
        new DefaultAllGridFrameController(CoberturaGridFrame.class.getName(), null, Cobertura.class.getName(),
                "Listado de Coberturas");
    }
    // </editor-fold >

    // <editor-fold defaultstate="collapsed" desc="polizas">
    public void getRamoCobertura() {
        new RamoCoberturaGridFrameController(RamosCoberturasGridFrame.class.getName(), null, Ramo.class.getName(), null);

    }

    public void getGrupoFamiliarGridFrame() {
        new CertificadosGridController(CertificadosGridFrame.class.getName(), CertificadoDetailFrame.class.getName(), Certificado.class.getName(), "Grupos Familiares");
    }

    public void getNuevoGrupoFamiliar() {
        new CertificadoDetailController(CertificadoDetailFrame.class.getName(), null, null, false);
    }

    public void getPlan() {
        new PlanesGridFrameController(PlanesGridFrame.class.getName(), null, Plan.class.getName(), "Plan");
    }

    public void getPlazoEspera() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, PlazoEspera.class.getName(), "Plazo de Espera");
    }

    public void getPolizaNueva() {
        new PolizaDetailFrameController(PolizaDetailFrame.class.getName(), null, null, false);
    }

    public void getPolizas() {
        new PolizasGridFrameController(PolizasGridFrame.class.getName(), PolizaDetailFrame.class.getName(), Poliza.class.getName(), null);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Siniestros">
    public void getPatologias() {
        new RamoGridFrameController(PatologiasGridFrame.class.getName(),
                null, Ramo.class.getName(), "Ramos");
    }

    public void getTipoSiniestro() {
        new DefaultAllGridFrameController(TipoSiniestroGridFrame.class.getName(), null, TipoSiniestro.class.getName(), "Tipo Siniestro");
    }

    public void getConceptoSENIAT() {
        new DefaultAllGridFrameController(ConceptoSENIATGridFrame.class.getName(), null, ConceptoSENIAT.class.getName(), "Concepto SENIAT");
    }

    public void getSiniestros() {
        new SiniestroGridFrameController();
    }

    public void getSiniestroNuevo() {
        new BuscarAseguradoDialog("Asignar Asegurado a Siniestro").setVisible(true);
    }

    public void getApsGrid() {
        new DetalleSiniestroGridFrameController(DetalleSiniestroGridFrame.class.getName(), DetalleSiniestroDetailFrame.class.getName(), APS.class.getName(), "APS");

    }

    public void getCartaAvalGrid() {
        new DetalleSiniestroGridFrameController(DetalleSiniestroGridFrame.class.getName(), DetalleSiniestroDetailFrame.class.getName(), CartaAval.class.getName(), "CartaAval");

    }

    public void getAyudaSocialGrid() {
        new DetalleSiniestroGridFrameController(DetalleSiniestroGridFrame.class.getName(), DetalleSiniestroDetailFrame.class.getName(), AyudaSocial.class.getName(), "AyudaSocial");

    }

    public void getEmergenciaGrid() {
        new DetalleSiniestroGridFrameController(DetalleSiniestroGridFrame.class.getName(), DetalleSiniestroDetailFrame.class.getName(), Emergencia.class.getName(), "Emergencia");

    }

    public void getReembolsoGrid() {
        new DetalleSiniestroGridFrameController(DetalleSiniestroGridFrame.class.getName(), DetalleSiniestroDetailFrame.class.getName(), Reembolso.class.getName(), "Reembolso");
    }

    public void getFunerarioGrid() {
        new DetalleSiniestroGridFrameController(DetalleSiniestroGridFrame.class.getName(), DetalleSiniestroDetailFrame.class.getName(), Funerario.class.getName(), "Funerario");
    }

    public void getVidaGrid() {
        new DetalleSiniestroGridFrameController(DetalleSiniestroGridFrame.class.getName(), DetalleSiniestroDetailFrame.class.getName(), Vida.class.getName(), "Vida");
    }

    public void getEtapaSiniestro() {
        new DefaultAllGridFrameController(EtapaSiniestroGridFrame.class.getName(), null, EtapaSiniestro.class.getName(), "Etapas del Sinistros");

    }
    // </editor-fold>

    public void getNewPago() {
        new BuscarPersonaDialog((java.awt.Component)null,
                OrdenDePagoDetailFrameController.class.getName(),
                OrdenDePago.class.getName());
    }

    public void getPagosGrid() {
        new OrdenDePagoGridFrameController(OrdenDePagoGridFrame.class.getName(),
                OrdenDePagoDetailFrame.class.getName(), OrdenDePago.class.getName(),
                "Orden de Pago");
    }
    
    public void getNewRemesa() {
        new RemesaDetailFrameController(RemesaDetailFrame.class.getName(),
                null, null, true);
    }
    public void getGridRemesa()
    {
        new RemesaGridFrameController(RemesaGridFrame.class.getName(), 
                RemesaDetailFrame.class.getName(), Remesa.class.getName(), null);
    }

    // <editor-fold defaultstate="collapsed" desc="Cambiar Pass">
    public void getCambiarPass() {
        CambiarPassDialog.showDialog();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Auditoria">
    public void getLog() {
        new LogGridController(LogGridFrame.class.getName(), null, AuditLogRecord.class.getName(), null);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Certificado">
    public void getCertificadoNuevo() {
        RifBusquedaDialog busquedaDialog = new RifBusquedaDialog(
                new String[]{
                    Certificado.class.getName(),
                    Titular.class.getName(),
                    Asegurado.class.getName()},
                new String[]{
                    "titular.persona",
                    "persona",
                    "persona"});
        BeanVO certif = busquedaDialog.getBenVO();

        if (certif != null && certif instanceof Certificado) {
            new CertificadoDetailController(CertificadoDetailFrame.class.getName(), null, certif, null, false);
        }
        if (certif != null && certif instanceof Titular
                && quitarAsegurado((Titular) certif)) {
            Certificado cert = new Certificado();
            cert.setTitular((Titular) certif);
            try {
                Session s = HibernateUtil.getSessionFactory().openSession();
                s.beginTransaction();
                Query q = s.createQuery("FROM " + Asegurado.class.getName()
                        + " WHERE persona.rif.rif=?").
                        setString(0, cert.getTitular().getPersona().getRif().getRif());
                Asegurado a = (Asegurado) q.uniqueResult();
                cert.getAsegurados().add(a);
                cert.setAuditoria(new AuditoriaBasica(new Date(),
                        General.usuario.getUserName(), true));
                s.save(cert);
                s.getTransaction().commit();
                s.close();
                new CertificadoDetailController(
                        CertificadoDetailFrame.class.getName(),
                        null, cert, null, false);
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "actionPerformed", ex);
            }

        }
        if (certif instanceof Asegurado) {
            quitarAsegurado((Asegurado) certif);
        }

        if (certif instanceof Persona) {
            Asegurado n = new Asegurado();
            n.setPersona((PersonaNatural) certif);
            certif = n;
        }

        if (certif instanceof Asegurado) {

            CertificadoNuevo c = new CertificadoNuevo();
            c.setAsegurado((Asegurado) certif);
            new CertificadoNuevoDetrailController(CertificadoNuevoDetailFrame.class.getName(), null, c, null, false);

        }
        if (certif == null && busquedaDialog.getRif().getRif() != null) {
            CertificadoNuevoDetrailController c =
                    new CertificadoNuevoDetrailController(
                    CertificadoNuevoDetailFrame.class.getName(), null, null, null, false);
            Form linkForm = c.getVista().getMainPanel();
            String linkAttName = "asegurado.persona";
            new PersonasDetailController(linkForm, linkAttName, new Object[]{"ASE", "TIT"}, null, null, busquedaDialog.getRif());
        }

    }

    private boolean quitarAsegurado(Titular titular) {
        Session s = null;
        Asegurado a = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("FROM " + Asegurado.class.getName()
                    + " WHERE persona.rif.rif=:id").
                    setString("id", ((Titular) titular).getPersona().getRif().getRif());
            a = (Asegurado) q.uniqueResult();

        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "quitarAsegurado(Titular titular)", ex);
            return false;
        } finally {
            s.close();
        }
        return quitarAsegurado(a);
    }

    private boolean quitarAsegurado(Asegurado aseg) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            Query q = s.createQuery("SELECT a, C FROM " + Certificado.class.getName()
                    + " C JOIN C.asegurados a WHERE a.persona.id=:id").
                    setLong("id", ((Asegurado) aseg).getId());
            Object[] a = (Object[]) q.uniqueResult();
            if (a[0] != null) {
                int res = JOptionPane.showConfirmDialog(
                        MDIFrame.getInstance(),
                        ((Asegurado) aseg).getPersona().getNombreLargo() + " esta asegurado\n"
                        + "en el Certificado de "
                        + ((Certificado) a[1]).getTitular().getPersona().getNombreLargo(),
                        "Certificados", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    ((Certificado) a[1]).getAsegurados().remove((Asegurado) a[0]);
                    s.update(a[1]);
                } else {
                    return false;
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "quitarAsegurado(Asegurado aseg)", ex);
            return false;
        } finally {
            s.getTransaction().commit();
            s.close();
        }
        return true;
    }
    // </editor-fold>

    public void getTest() {
//        "17930"
        //AUN_NO_FUN();
        new RemesaDetailFrameController(RemesaDetailFrame.class.getName(),
                null, null, true);
    }
}
