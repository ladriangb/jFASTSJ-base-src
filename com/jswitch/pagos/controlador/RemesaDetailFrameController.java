package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.fas.modelo.Dominios.TipoDetalleSiniestro;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.modelo.maestra.Remesa;
import com.jswitch.pagos.modelo.transaccional.lote.Transaccion;
import com.jswitch.pagos.vista.RemesaDetailFrame;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.openswing.swing.client.ExportButton;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.client.InsertButton;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientSettings;

/**
 * Genera y mantiene la orden de pago 
 * @author Adrian
 */
public class RemesaDetailFrameController
        extends DefaultDetailFrameController {

    /**
     * crea la instancia del objeto de 
     * <code>OrdenDePagoDetailFrameController</code>
     */
    public RemesaDetailFrameController() {
    }

    /**
     * crea la instancia del objeto de 
     * <code>OrdenDePagoDetailFrameController</code>
     * @param detailFramePath
     * @param gridControl
     * @param beanVO
     * @param aplicarLogicaNegocio 
     */
    public RemesaDetailFrameController(String detailFramePath,
            GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Remesa remesa = (Remesa) s.get(Remesa.class, ((Remesa) beanVO).getId());
        Hibernate.initialize(remesa.getOrdenDePagos());
        Hibernate.initialize(remesa.getObservaciones());
        Hibernate.initialize(remesa.getDocumentos());
        Hibernate.initialize(remesa.getNotasTecnicas());
        s.close();
        getVista().hideAll(remesa.getEstatusPago());
        beanVO = remesa;
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Remesa remesa = (Remesa) newPersistentObject;
        if (remesa.getAutoSearch()) {
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                String sql = null;
                if (remesa.getTipoDetalleSiniestro().equals(TipoDetalleSiniestro.Todos)) {
                    sql = "FROM "
                            + OrdenDePago.class.getName() + " C WHERE "
                            + "C.estatusPago=? AND C.codigoSIGECOF is not null";
                } else {
                    sql = "FROM "
                            + OrdenDePago.class.getName() + " C WHERE "
                            + "C.estatusPago=? AND C.codigoSIGECOF is not null AND "
                            + "C.tipoDetalleSiniestro=?";
                }
                Query q = s.createQuery(sql);
                List ordenes = null;
                if (remesa.getTipoDetalleSiniestro().equals(TipoDetalleSiniestro.Todos)) {
                    ordenes = q.setString(0, EstatusPago.PENDIENTE.toString()).list();
                } else {
                    ordenes = q.setString(0, EstatusPago.PENDIENTE.toString()).
                            setString(1, remesa.getTipoDetalleSiniestro().toString()).list();
                }
                for (Object objeto : ordenes) {
                    remesa.getOrdenDePagos().add(
                            (OrdenDePago) objeto);
                }
            } finally {
                s.close();
            }
        }
        Response response = super.insertRecord(newPersistentObject);
        return response;

    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        Session s = null;
        Remesa remesa = (Remesa) persistentObject;
        if (remesa.getCuentaBancaria() != null) {
            remesa.setTipoCuenta(remesa.getCuentaBancaria().getTipoCuenta());
            remesa.setNumeroCuentaDebitar(remesa.getCuentaBancaria().getNumero());
        }
        return new VOResponse(remesa);
    }

    @Override
    public Response logicaNegocioDespuesSave(ValueObject persistentObject, Session s) {
        Long seq = null;
        try {
            seq = ((BigInteger) s.createSQLQuery("SELECT nextval('seq_remesa');").uniqueResult()).longValue();
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "logicaNegocioDespuesSave", ex));
        }
        DecimalFormat nf = new DecimalFormat("00000");
        Remesa remesa = (Remesa) persistentObject;
        remesa.setNumRefLot(seq.intValue());
        remesa.setRefLot(nf.format(seq));
        for (OrdenDePago ordenDePago : remesa.getOrdenDePagos()) {
            ordenDePago.setEstatusPago(EstatusPago.SELECCIONADO);
            s.update(ordenDePago);
        }
        return new VOResponse(remesa);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof ExportButton) {
            Remesa rm = (Remesa) beanVO;
            if (rm.getFechaEnvio() == null || rm.getFechaPropuestaPago() == null || rm.getFechaValor() == null) {
                JOptionPane.showMessageDialog(vista, "DEBE INGRESAR LAS FECHAS\nPARA PODER GENERAR EL .TXT", "", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (rm.getCuentaBancaria() == null) {
                JOptionPane.showMessageDialog(vista, "DEBE INGRESAR LA CUENTA BANCARIA A DEBITAR\nPARA PODER GENERAR EL .TXT", "", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (rm.getNumRefCre() == null || rm.getNumRefDeb() == null) {
                JOptionPane.showMessageDialog(vista, "DEBE INGRESAR LAS REFERENCIAS BANCARIAS\nPARA PODER GENERAR EL .TXT", "", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Transaccion tr = null;

            tr = new Transaccion((Remesa) beanVO);

            JFileChooser f = new JFileChooser(new File(""));
            f.setFileFilter(new FileFilter() {

                public boolean accept(File f) {
                    return f.isDirectory()
                            || f.getName().toLowerCase().endsWith(".txt");
                }

                /**
                 * The description of this filter. For example: "JPG and GIF Images"
                 * @see FileView#getName
                 */
                public String getDescription() {
                    return "Text Files(*.txt)";
                }
            });
            f.showSaveDialog(new JFrame());
            File file = f.getSelectedFile();
            File error = new File(file.getAbsoluteFile() + "-Fallos.txt");
            if (!file.getName().contains(".")) {
                file = new File(file.getAbsoluteFile() + ".txt");
            }
            try {
                if (file.exists()) {
                    int res = JOptionPane.showConfirmDialog(MDIFrame.getInstance(),
                            ClientSettings.getInstance().getResources().
                            getResource("DESEA.SOBRE-ESCRIBIR"), "",
                            JOptionPane.YES_NO_OPTION);
                    if (res == JOptionPane.YES_OPTION) {
                        file.delete();
                    }
                }
                if (!file.exists()) {
                    if (file.createNewFile()) {
                        if (tr.hasError()) {
                            tr.printError(new FileOutputStream(error));
                        }
                        tr.printReport(new FileOutputStream(file));
                        try {
                            java.awt.Desktop.getDesktop().open(file);
                            if (tr.hasError()) {
                                java.awt.Desktop.getDesktop().open(error);
                            }
                        } catch (Exception ex) {

                            JOptionPane.showMessageDialog(new JFrame(),
                                    ClientSettings.getInstance().getResources().
                                    getResource("CAN.NOT.OPEN.FILE") + " "
                                    + file.getAbsolutePath());
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(),
                                ClientSettings.getInstance().getResources().
                                getResource("NO.SE.PUEDE.CREAR.ARCHIVO"));
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() instanceof InsertButton) {
            new BuscarOrdenDePagoGridFrameController((Remesa) beanVO, this);
        } else if (((JButton) e.getSource()).getText().equalsIgnoreCase("PAGAR")) {
            new PagarDetailFrameController(vista.getMainPanel());
        } else {
            int res = JOptionPane.showConfirmDialog(vista, "Esta a punto de Anular la \"Remesa\"\nEsta acción no se puede revertir\n¿Desea Continuar? ",
                    General.empresa.getNombre(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (res == JOptionPane.YES_OPTION) {
                anular();
                vista.getMainPanel().getReloadButton().doClick();
            }
        }
    }

    /**
     * Establese la Orden de Pago como Nula
     */
    private void anular() {
        Session s = null;
        Remesa remesa = (Remesa) vista.getMainPanel().getVOModel().getValueObject();
        remesa.setEstatusPago(EstatusPago.ANULADO);
        remesa.getAuditoria().setActivo(false);
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.update(remesa);
            List<OrdenDePago> l = s.createQuery("FROM " + OrdenDePago.class.getName() + " C "
                    + "WHERE C.remesa.id=?").setLong(0, remesa.getId()).list();
            for (OrdenDePago ordenDePago : l) {
                ordenDePago.setEstatusPago(EstatusPago.PENDIENTE);
                ordenDePago.setRemesa(null);
                s.update(ordenDePago);
            }

            s.getTransaction().commit();
        } finally {
            s.close();
        }
    }

    /**
     * La vista de detalle de remesa
     * @return the vista
     */
    public RemesaDetailFrame getVista() {
        return (RemesaDetailFrame) vista;
    }
}
