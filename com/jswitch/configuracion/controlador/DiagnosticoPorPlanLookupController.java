package com.jswitch.configuracion.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import com.jswitch.configuracion.modelo.dominio.patologias.Especialidad;
import com.jswitch.configuracion.modelo.maestra.Plan;
import com.jswitch.configuracion.modelo.transaccional.SumaAsegurada;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.BooleanType;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.tree.java.OpenSwingTreeNode;
import org.openswing.swing.util.java.Consts;
import org.openswing.swing.util.server.HibernateUtils;

/**
 * @author adrian
 */
public class DiagnosticoPorPlanLookupController extends DefaultLookupController {

    private Plan plan;
    private String idPropioRamo;

    public DiagnosticoPorPlanLookupController() {
        this.setLookupDataLocator(new MarcaModeloLookupDataLocator(Diagnostico.class.getName()));
        this.setFramePreferedSize(new Dimension(500, 500));
        this.setCodeSelectionWindow(DiagnosticoPorPlanLookupController.TREE_GRID_FRAME);
        this.getLookupDataLocator().setNodeNameAttribute("nombre");
        this.setLookupValueObjectClassName(Diagnostico.class.getName());
        this.setAllColumnVisible(false);
        this.setVisibleColumn("nombre", true);
        this.setPreferredWidthColumn("nombre", 300);
        this.idPropioRamo = null;

    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public String getIdPropioRamo() {
        return idPropioRamo;
    }

    public void setIdPropioRamo(String idPropio) {
        this.idPropioRamo = idPropio;
    }

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
            try {
                Map map = getLookupFrameParams();
                if (map.get(Consts.TREE_FILTER) != null) {
                    Object ob = map.get(Consts.TREE_FILTER);
                    if (!(ob instanceof Especialidad)) {
                        return new VOListResponse();
                    }
                    Especialidad especialidad = (Especialidad) ob;
                    if (especialidad.getId() != -1) {
                        Session s = null;
                        try {
                            String sql = "SELECT C.diagnostico as T FROM "
                                    + SumaAsegurada.class.getName()
                                    + " C WHERE C.diagnostico.especialidad.id=? AND C.plan.id=? "
                                    + "AND C.diagnostico.auditoria.activo=?";
                            SessionFactory sf = HibernateUtil.getSessionFactory();
                            s = sf.openSession();
                            Response res = HibernateUtils.getAllFromQuery(
                                    filteredColumns,
                                    currentSortedColumns,
                                    currentSortedVersusColumns,
                                    valueObjectType,
                                    sql,
                                    new Object[]{especialidad.getId(), plan.getId(), new Boolean(true)},
                                    new Type[]{new LongType(), new LongType(), new BooleanType()},
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
                        return new VOListResponse(new ArrayList(0), false, 0);
                    }
                }
                return new VOListResponse(new ArrayList(0), false, 0);
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "loadData", ex);
                return new ErrorResponse(ex.getMessage());
            }

        }

        @Override
        public Response getTreeModel(JTree tree) {
            if (plan == null) {
                return new VOResponse(new DefaultTreeModel(new OpenSwingTreeNode()));
            }
            Session s = null;
            String validateRamo = "";
            if (getIdPropioRamo() != null && !getIdPropioRamo().isEmpty()) {
                validateRamo = " AND M.diagnostico.especialidad.ramo.idPropio='" + getIdPropioRamo() + "' ";
            }
            try {
                OpenSwingTreeNode root = new OpenSwingTreeNode(null);
                s = HibernateUtil.getSessionFactory().openSession();
                String sql = "SELECT DISTINCT M.diagnostico.especialidad FROM "
                        + SumaAsegurada.class.getName()
                        + " M WHERE M.plan.id= ? "
                        + validateRamo
                        + "AND M.diagnostico.especialidad.auditoria.activo=? ";
                List<Especialidad> especialidades = s.createQuery(sql).
                        setLong(0, plan.getId()).//.setCacheable(true)
                        setBoolean(1, Boolean.TRUE).
                        list();

                Collections.sort(especialidades, new Comparator<Especialidad>() {

                    @Override
                    public int compare(Especialidad o1, Especialidad o2) {
                        int ramo = o1.getRamo().getNombre().compareTo(o2.getRamo().getNombre());
                        if (ramo == 0) {
                            return o1.getNombre().compareTo(o2.getNombre());
                        }
                        return ramo;
                    }
                });
                for (Especialidad especialidad : especialidades) {
                    getNodoRamo(especialidad, root).add(new OpenSwingTreeNode(especialidad, false));
                }

                return new VOResponse(new DefaultTreeModel(root));

            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "getTreeModel", ex);
                return new VOResponse(new DefaultTreeModel(new OpenSwingTreeNode()));
            } finally {
                s.close();
            }
        }

        private OpenSwingTreeNode getNodoRamo(Especialidad especialidad, OpenSwingTreeNode root) {
            for (int i = 0; i < root.getChildCount(); i++) {
                if (((OpenSwingTreeNode) root.getChildAt(i)).getUserObject().equals(especialidad.getRamo())) {
                    return (OpenSwingTreeNode) root.getChildAt(i);
                }
            }
            OpenSwingTreeNode ramo = new OpenSwingTreeNode(especialidad.getRamo());
            root.add(ramo);
            return ramo;
        }
    }
}
