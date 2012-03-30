package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.fas.modelo.Dominios.TipoDetalleSiniestro;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.vista.BuscaDetallesGridFrame;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.LongType;
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
public class BuscarDetallesGridFrameController extends DefaultGridFrameController
        implements ActionListener {

    private Persona personaPago;
    private OrdenDePago ordenDePago;
    private OrdenDePagoDetailFrameController controller;

    public BuscarDetallesGridFrameController(OrdenDePagoDetailFrameController controller, OrdenDePago ordenDePago) {
        super(BuscaDetallesGridFrame.class.getName(), DetalleSiniestroDetailFrame.class.getName(),
                ordenDePago.getTipoDetalleSiniestro().getClase(), null);
        this.personaPago = ordenDePago.getPersonaPago();
        this.ordenDePago = ordenDePago;
        this.controller = controller;
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {

            String sql = "FROM " + claseModeloFullPath
                    + " C WHERE C.personaPago.id=? "
                    + "AND C.etapaSiniestro.idPropio=?";

            ArrayList<Object> al = new ArrayList<Object>(0);
            ArrayList<Type> ty = new ArrayList<Type>(0);
            al.add(personaPago.getId());
            ty.add(new LongType());
            al.add("LIQ");
            ty.add(new StringType());
            if (ordenDePago.getTipoDetalleSiniestro().equals(TipoDetalleSiniestro.Todos)) {
                sql += " AND C.tipoDetalle <> ? ";
                al.add(TipoDetalleSiniestro.Reembolso.toString());
                ty.add(new StringType());
            }
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            Response res = HibernateUtils.getAllFromQuery(filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    sql,
                    al.toArray(),
                    ty.toArray(new Type[0]),
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
                EtapaSiniestro es = (EtapaSiniestro) s.createQuery("FROM "
                        + EtapaSiniestro.class.getName() + " C WHERE "
                        + "idPropio=?").setString(0, "ORD_PAG").uniqueResult();
                OrdenDePago p = (OrdenDePago) s.get(OrdenDePago.class, ordenDePago.getId());

                List lista = model.getChangedRows();
                if (lista == null || lista.isEmpty()) {
                    lista = getSubList(model.getDataVector());
                }
                s.createQuery("UPDATE " + DetalleSiniestro.class.getName()
                        + " D SET D.etapaSiniestro=:es, D.ordenDePago=:ord WHERE D in(:ds)").
                        setEntity("es", es).setEntity("ord", p).
                        setParameterList("ds", lista).executeUpdate();

                s.getTransaction().commit();
                gridFrame.dispose();

            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "actionPerformed", ex);
            } finally {
                s.close();
                controller.getVista().getMainPanel().getReloadButton().doClick();
            }
        }
    }

    private List getSubList(Vector dataVector) {
        List l = new ArrayList(0);
        for (Object object : dataVector) {
            if (((DetalleSiniestro) object).getSelected()) {
                l.add(object);
            }
        }
        return l;
    }
}
