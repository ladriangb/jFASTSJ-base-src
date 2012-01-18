/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.vista.util.DefaultGridFrame;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
import com.jswitch.siniestros.modelo.transaccional.MantenimientoDiagnostico;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Adrian
 */
public class MantenimientoDiagnosticoGridController extends DefaultGridFrameController {

    DiagnosticoSiniestro diagnosticoSiniestro;

    public MantenimientoDiagnosticoGridController(DiagnosticoSiniestro diagnosticoSiniestro) {
        this.claseModeloFullPath = MantenimientoDiagnostico.class.getName();
        this.diagnosticoSiniestro = diagnosticoSiniestro;
    }

    @Override
    public void loadDataCompleted(boolean error) {
    }

    public DefaultGridFrame getGridFrame() {
        return gridFrame;
    }

    public void setGridFrame(DefaultGridFrame gridFrame) {
        this.gridFrame = gridFrame;
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {
            String sql = "FROM " + claseModeloFullPath + " C "
                    + "WHERE C.diagnosticoSiniestro.id=?";
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
                    new Object[]{diagnosticoSiniestro.getId()},
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
}
