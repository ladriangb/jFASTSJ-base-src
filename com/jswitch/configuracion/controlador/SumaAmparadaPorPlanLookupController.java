package com.jswitch.configuracion.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.maestra.Plan;
import com.jswitch.configuracion.modelo.transaccional.SumaAmparada;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.BooleanType;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.util.java.Consts;
import org.openswing.swing.util.server.HibernateUtils;

/**
 * @author bc
 */
public class SumaAmparadaPorPlanLookupController extends DefaultLookupController {

    public SumaAmparadaPorPlanLookupController() {
        this.setLookupDataLocator(new MarcaModeloLookupDataLocator(SumaAmparada.class.getName()));
        this.setFramePreferedSize(new Dimension(300, 300));
        this.setCodeSelectionWindow(SumaAmparadaPorPlanLookupController.GRID_FRAME);
        this.getLookupDataLocator().setNodeNameAttribute("nombre");
        this.setLookupValueObjectClassName(SumaAmparada.class.getName());
        this.setAllColumnVisible(false);
        this.setVisibleColumn("nombre", true);
        this.setVisibleColumn("monto", true);
        this.setPreferredWidthColumn("nombre", 200);
        this.setPreferredWidthColumn("monto", 70);
        this.setFilterableColumn("nombre", true);
        this.setFilterableColumn("monto", true);
        this.setSortableColumn("nombre", true);
        this.setSortableColumn("monto", true);
        this.setSortedColumn("nombre", Consts.ASC_SORTED);

    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
    private Plan plan;

    class MarcaModeloLookupDataLocator extends DefaultLookupDataLocator {

        public MarcaModeloLookupDataLocator(String classFullName) {
            super(classFullName);
        }

        @Override
        public Response loadData(int action,
                int startIndex,
                Map filteredColumns,
                ArrayList currentSortedColumns,
                ArrayList currentSortedVersusColumns,
                Class valueObjectType) {
            Session s = null;
            try {
                String sql = "FROM " + SumaAmparada.class.getName()
                        + " C WHERE C.auditoria.activo=? AND plan.id=?";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Response res = HibernateUtils.getAllFromQuery(
                        filteredColumns,
                        currentSortedColumns,
                        currentSortedVersusColumns,
                        valueObjectType,
                        sql,
                        new Object[]{new Boolean(true), plan.getId()},
                        new Type[]{new BooleanType(), new LongType()},
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
}
