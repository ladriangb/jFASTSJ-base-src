package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.fas.modelo.Dominios.TipoDetalleSiniestro;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.modelo.maestra.Remesa;
import com.jswitch.pagos.vista.BuscaOrdenDePagoGridFrame;
import com.jswitch.pagos.vista.OrdenDePagoDetailFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.openswing.swing.client.CheckBoxControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.table.model.client.VOListTableModel;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Adrian
 */
public class BuscarOrdenDePagoGridFrameController extends DefaultGridFrameController
        implements ActionListener {

    /**
     * remesa para guardar 
     */
    private Remesa remesa;

    /**
     * crea una instancia de BuscarOrdenDePagoGridFrameController
     * @param remesa 
     */
    public BuscarOrdenDePagoGridFrameController(Remesa remesa) {
        super(BuscaOrdenDePagoGridFrame.class.getName(),
                OrdenDePagoDetailFrame.class.getName(), OrdenDePago.class.getName(), null);
        this.remesa = remesa;
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {

            String sql = "FROM " + claseModeloFullPath
                    + " C WHERE C.estatusPago=? "
                    + "AND C.codigoSIGECOF is not null ";

            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();

            Object[] ob = null;
            Type[] ty = null;

            if (remesa.getTipoDetalleSiniestro().equals(TipoDetalleSiniestro.Todos)) {
                ob = new Object[]{
                    EstatusPago.PENDIENTE.toString()};
                ty = new Type[]{new StringType()};
            } else {
                sql += "AND (C.tipoDetalleSiniestro=?)";
                ob = new Object[]{
                    EstatusPago.PENDIENTE.toString(),
                    remesa.getTipoDetalleSiniestro().toString()};
                ty = new Type[]{new StringType(), new StringType()};
            }
            Response res = HibernateUtils.getAllFromQuery(filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    sql,
                    ob,
                    ty,
                    "C", sf, s);
            return res;
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "loadData", ex);
            return new ErrorResponse(ex.getMessage());
        } finally {
            s.close();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VOListTableModel model = ((VOListTableModel) gridFrame.getGridControl().
                getTable().getGrid().getModel());

        if (e.getSource() instanceof CheckBoxControl) {
            for (int i = 0; i < model.getRowCount(); i++) {
                model.setField(i, "selected",
                        ((CheckBoxControl) e.getSource()).isSelected());
            }
            gridFrame.getGridControl().repaint();
        } else {
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                s.beginTransaction();
                for (int i = 0; i < model.getRowCount(); i++) {
                    OrdenDePago orden = (OrdenDePago) s.get(
                            OrdenDePago.class,
                            ((OrdenDePago) model.getObjectForRow(i)).getId());
                    if (((OrdenDePago) model.getObjectForRow(i)).getSelected()) {
                        Hibernate.initialize(orden.getNotasTecnicas());
                        Hibernate.initialize(orden.getObservaciones());
                        Hibernate.initialize(orden.getDocumentos());
                        Hibernate.initialize(orden.getDetalleSiniestros());
                        orden.setEstatusPago(EstatusPago.SELECCIONADO);
                        s.update(orden);
                        remesa.getOrdenDePagos().add(orden);
                    }
                }
                s.update(remesa);
                s.getTransaction().commit();
                gridFrame.dispose();
            } catch (Exception ex) {
                LoggerUtil.error(BuscarOrdenDePagoGridFrameController.class,
                        "actionPerformed", ex);
            } finally {
                s.close();
            }
        }
    }
}
