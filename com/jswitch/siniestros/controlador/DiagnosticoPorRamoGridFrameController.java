package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.vista.DiagnosticoPorRamoGridFrame;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.classic.Session;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;

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
        gridFrame.dispose();
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {

        Session s = null;
        VOListResponse response = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            List k = s.createQuery("FROM " + Diagnostico.class.getName() + " D "
                    + "WHERE D.especialidad.ramo.id=?").setLong(0, detalleSiniestro.getRamo().getId()).list();
            response = new VOListResponse(k, false, k.size());
        } catch (Exception ex) {
        } finally {
            s.close();
        }
        return response;

    }
}
