package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.fas.modelo.Dominios.TipoDetalleSiniestro;
import com.jswitch.siniestros.controlador.detalle.DetalleSiniestroDetailFrameController;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.APS;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
import com.jswitch.vistasbd.ListaDiagnostico;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.transform.AliasedTupleSRT;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Luis Adrian Gonzalez
 */
public class ListaDiagnosticoGridInternalController extends DefaultGridInternalController {

    public ListaDiagnosticoGridInternalController(GridControl miGrid) {
        super(miGrid);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        String td = ((ListaDiagnostico) persistentObject).getDetalleSiniestro().getTipoDetalle();
        Class clase = DetalleSiniestro.class;
        if (td.compareTo(APS.class.getSimpleName()) == 0) {
            clase = APS.class;
        } else {
            try {
                TipoDetalleSiniestro tipo = TipoDetalleSiniestro.valueOf(td);
                clase = Class.forName(tipo.getClase());
            } catch (Exception ex) {
                Logger.getLogger(ListaDiagnosticoGridInternalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        new DetalleSiniestroDetailFrameController(
                DetalleSiniestroDetailFrame.class.getName(), miGrid,
                ((ListaDiagnostico) persistentObject).getDetalleSiniestro(), Boolean.TRUE,
                clase);
    }

    public Response loadData(int action,
            int startIndex,
            Map filteredColumns,
            ArrayList currentSortedColumns,
            ArrayList currentSortedVersusColumns,
            Class valueObjectType,
            Map otherGridParams) {
        List al = null;
        if (beanVO != null) {
            Session s = null;
            try {

                String select = miGrid.getVOListTableModel().
                        createSelect("C", AliasedTupleSRT.SEPARATOR);
                select += ", C.detalleSiniestro.id as detalleSiniestro_id , C.detalleSiniestro.etapaSiniestro.idPropio as detalleSiniestro_etapaSiniestro_idPropio";
                String sql = select + " FROM " + ListaDiagnostico.class.getName() + " C "
                        + " WHERE C.siniestro.id=?";

                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Response res = HibernateUtils.getBlockFromQuery(new AliasedTupleSRT(ListaDiagnostico.class),
                        action,
                        startIndex,
                        General.licencia.getBlockSize(),
                        filteredColumns,
                        currentSortedColumns,
                        currentSortedVersusColumns,
                        valueObjectType,
                        sql,
                        new Object[]{((Siniestro) beanVO).getId()},
                        new Type[]{new LongType()},
                        "C",
                        sf,
                        s);
                return res;
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "loadData", ex);
                return new ErrorResponse(ex.getMessage());
            } finally {
                s.close();
            }
        }
        return new VOListResponse();
    }
}
