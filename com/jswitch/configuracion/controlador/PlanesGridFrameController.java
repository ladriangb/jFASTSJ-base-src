package com.jswitch.configuracion.controlador;

import com.jswitch.base.controlador.General;
import java.util.ArrayList;
import java.util.Map;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.configuracion.modelo.maestra.Plan;
import com.jswitch.configuracion.modelo.transaccional.ConfiguracionSiniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.APS;
import com.jswitch.siniestros.modelo.maestra.detalle.AyudaSocial;
import com.jswitch.siniestros.modelo.maestra.detalle.CartaAval;
import com.jswitch.siniestros.modelo.maestra.detalle.Emergencia;
import com.jswitch.siniestros.modelo.maestra.detalle.Funerario;
import com.jswitch.siniestros.modelo.maestra.detalle.Reembolso;
import com.jswitch.siniestros.modelo.maestra.detalle.Vida;
import java.util.Date;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Orlando Becerra
 * @author Adrian Gonzalez
 */
public class PlanesGridFrameController extends DefaultGridFrameController implements GridDataLocator {

    public PlanesGridFrameController() {
    }

    public PlanesGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns,
            ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {
            String sql = "FROM " + Plan.class.getName() + " C ";
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
                    new Object[0],
                    new Type[0],
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

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Response res = super.insertRecords(rowNumbers, newValueObjects);
        for (Object object : newValueObjects) {
            Plan plan = (Plan) object;
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
                String[] clases = {APS.class.getSimpleName(),
                    AyudaSocial.class.getSimpleName(),
                    CartaAval.class.getSimpleName(),
                    Emergencia.class.getSimpleName(),
                    Funerario.class.getSimpleName(),
                    Reembolso.class.getSimpleName(),
                    Vida.class.getSimpleName()};
                for (String o : clases) {
                    ConfiguracionSiniestro cs = new ConfiguracionSiniestro(o, Double.POSITIVE_INFINITY, 0d, plan, ab);
                    s.save(cs);
                    plan.getConfiguracionSiniestros().add(cs);
                }
                s.update(plan);
                t.commit();
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "insertRecord", ex);
            } finally {
                s.close();
            }
        }
        return res;
    }
}
