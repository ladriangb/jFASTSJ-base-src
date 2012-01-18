package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.fas.modelo.Dominios.TipoDetalleSiniestro;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.modelo.maestra.Remesa;
import com.jswitch.pagos.modelo.transaccional.lote.Transaccion;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Remesa sin = (Remesa) s.get(Remesa.class, ((Remesa) beanVO).getId());
        Hibernate.initialize(sin.getOrdenDePagos());
        Hibernate.initialize(sin.getObservaciones());
        Hibernate.initialize(sin.getDocumentos());
        Hibernate.initialize(sin.getNotasTecnicas());
        s.close();
        beanVO = sin;
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Remesa p = (Remesa) newPersistentObject;
        if (p.getAutoSearch()) {
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                String sql = null;
                if (p.getTipoDetalleSiniestro().equals(TipoDetalleSiniestro.Todos)) {
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
                if (p.getTipoDetalleSiniestro().equals(TipoDetalleSiniestro.Todos)) {
                    ordenes = q.setString(0, EstatusPago.PENDIENTE.toString()).list();
                } else {
                    ordenes = q.setString(0, EstatusPago.PENDIENTE.toString()).
                            setString(1, p.getTipoDetalleSiniestro().toString()).list();
                }

                for (Object objeto : ordenes) {
                    p.getOrdenDePagos().add(
                            (OrdenDePago) objeto);
                }
            } finally {
                s.close();
            }
        }
        return super.insertRecord(newPersistentObject);
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        Session s = null;
        Remesa remesa = (Remesa) persistentObject;
        EstatusPago etS = null;
        EtapaSiniestro es = null;
        if (remesa.getEstatusPago() == EstatusPago.ANULADO) {
            etS = EstatusPago.PENDIENTE;
        } else if (remesa.getEstatusPago() == EstatusPago.PENDIENTE
                || remesa.getEstatusPago() == EstatusPago.SELECCIONADO) {
            etS = EstatusPago.SELECCIONADO;

        } else if (remesa.getEstatusPago() == EstatusPago.PAGADO) {
            etS = EstatusPago.PAGADO;
        }
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            if (etS == EstatusPago.ANULADO) {
                es = (EtapaSiniestro) s.createQuery("FROM "
                        + EtapaSiniestro.class.getName() + " C WHERE "
                        + "idPropio=?").setString(0, "LIQ").uniqueResult();
            } else if (etS == EstatusPago.PENDIENTE
                    || etS == EstatusPago.SELECCIONADO) {
                es = (EtapaSiniestro) s.createQuery("FROM "
                        + EtapaSiniestro.class.getName() + " C WHERE "
                        + "idPropio=?").setString(0, "ORD_PAG").uniqueResult();
            } else if (etS == EstatusPago.PAGADO) {
                es = (EtapaSiniestro) s.createQuery("FROM "
                        + EtapaSiniestro.class.getName() + " C WHERE "
                        + "idPropio=?").setString(0, "PAG").uniqueResult();
            }
            for (OrdenDePago ordenDePago : remesa.getOrdenDePagos()) {
                ordenDePago = (OrdenDePago) s.get(OrdenDePago.class, ordenDePago.getId());
                Hibernate.initialize(ordenDePago.getDetalleSiniestros());
                for (DetalleSiniestro detalleSiniestro : ordenDePago.getDetalleSiniestros()) {
                    detalleSiniestro.setEtapaSiniestro(es);
                    s.update(detalleSiniestro);
                }
                ordenDePago.setEstatusPago(etS);
                s.update(ordenDePago);
            }
            s.getTransaction().commit();
        } finally {
            s.close();
        }
        return new VOResponse(persistentObject);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof ExportButton) {
            Transaccion tr = new Transaccion((Remesa) beanVO);

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
            new BuscarOrdenDePagoGridFrameController((Remesa) beanVO);
        }
//        OrdenDePago op = (OrdenDePago) beanVO;
//        new BuscarDetallesGridFrameController(op.getPersonaPago(), op);
    }
}
