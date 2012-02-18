package com.jswitch.configuracion.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.transaccional.SumaAmparada;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.vistasbd.SumaPartida;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Luis Adrian Gonzalez
 */
public class PartidaPresupuestariaGridInternalController extends DefaultGridInternalController {

    public PartidaPresupuestariaGridInternalController(GridControl miGrid) {
        super(miGrid);
    }

    public Response loadData(int action,
            int startIndex,
            Map filteredColumns,
            ArrayList currentSortedColumns,
            ArrayList currentSortedVersusColumns,
            Class valueObjectType,
            Map otherGridParams) {
        List al;
        if (beanVO != null) {
            Session s = null;
            try {
                String sql = "FROM " + SumaPartida.class.getName() + " C "
                        + " WHERE C.ordenDePago.id=?";

                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Response res = HibernateUtils.getBlockFromQuery(
                        action,
                        startIndex,
                        General.licencia.getBlockSize(),
                        filteredColumns,
                        currentSortedColumns,
                        currentSortedVersusColumns,
                        valueObjectType,
                        sql,
                        new Object[]{((OrdenDePago) beanVO).getId()},
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
        } else {
            al = new ArrayList<SumaAmparada>(0);
        }
        return new VOListResponse(al, false, al.size());
    }
}
