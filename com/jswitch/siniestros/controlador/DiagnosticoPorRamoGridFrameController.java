package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import com.jswitch.configuracion.modelo.transaccional.SumaAsegurada;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.vista.DiagnosticoPorRamoGridFrame;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
import com.jswitch.vistasbd.ListaDiagnostico;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.transform.AliasedTupleSRT;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author adrian
 */
public class DiagnosticoPorRamoGridFrameController extends DefaultGridFrameController {

    DetalleSiniestro detalleSiniestro;
    DetalleSiniestroDetailFrame vista;

    public DiagnosticoPorRamoGridFrameController(DetalleSiniestroDetailFrame vista, DetalleSiniestro detalleSiniestro) {
        this.detalleSiniestro = detalleSiniestro;
        this.vista = vista;
        this.detailFramePath = null;
        this.claseModeloFullPath = Diagnostico.class.getName();
        gridFrame = new DiagnosticoPorRamoGridFrame();
        gridFrame.inicializar(this, this, claseModeloFullPath, claseModeloFullPath, true);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new DiagnosticoSiniestroDetailFrameController(
                vista.getGridDiagnosticos(),
                true, detalleSiniestro,
                vista, (Diagnostico) persistentObject);
        gridFrame.setVisible(false);
        gridFrame.dispose();
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {

        Session s = null;

        try {
            String sql = "SELECT D.diagnostico FROM " + SumaAsegurada.class.getName() + " D "
                    + "WHERE D.plan.id=? "
                    + "AND D.diagnostico.especialidad.ramo.id=?";
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();

            Response res = HibernateUtils.getAllFromQuery(
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    sql,
                    new Object[]{
                        detalleSiniestro.getSiniestro().getAsegurado().getPlan().getId(),
                        detalleSiniestro.getRamo().getId()
                    },
                    new Type[]{new LongType(), new LongType()},
                    "D.diagnostico",
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
