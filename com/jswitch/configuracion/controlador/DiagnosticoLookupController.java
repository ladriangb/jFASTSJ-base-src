package com.jswitch.configuracion.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import com.jswitch.configuracion.modelo.dominio.patologias.Especialidad;
import java.awt.Dimension;
import java.util.ArrayList;
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
import org.openswing.swing.message.send.java.FilterWhereClause;
import org.openswing.swing.tree.java.OpenSwingTreeNode;
import org.openswing.swing.util.java.Consts;
import org.openswing.swing.util.server.HibernateUtils;

/**
 * @author bc
 */
public class DiagnosticoLookupController extends DefaultLookupController {

    public DiagnosticoLookupController() {
        this.setLookupDataLocator(new MarcaModeloLookupDataLocator(Diagnostico.class.getName()));
        this.setFramePreferedSize(new Dimension(500, 500));
        this.setCodeSelectionWindow(DiagnosticoLookupController.TREE_GRID_FRAME);
        this.getLookupDataLocator().setNodeNameAttribute("nombre");
        this.setLookupValueObjectClassName(Diagnostico.class.getName());
        this.setAllColumnVisible(false);
        this.setVisibleColumn("nombre", true);
        this.setPreferredWidthColumn("nombre", 300);
        this.setFilterableColumn("nombre", true);
        this.setSortableColumn("nombre", true);
        this.setSortedColumn("nombre", Consts.ASC_SORTED);

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
                        filteredColumns.put(
                                "especialidad.id",
                                new FilterWhereClause[]{
                                    new FilterWhereClause("especialidad.id", "=", especialidad.getId()),
                                    null
                                });
                        //return new VOListResponse(new ArrayList(marca.getModelos()), false, marca.getModelos().size());
                        Session s = null;
                        try {
                            String sql = "FROM " + Diagnostico.class.getName()
                                    + " C WHERE C.auditoria.activo=?";
                            SessionFactory sf = HibernateUtil.getSessionFactory();
                            s = sf.openSession();
                            Response res = HibernateUtils.getAllFromQuery(
                                    //     de,
                                    filteredColumns,
                                    currentSortedColumns,
                                    currentSortedVersusColumns,
                                    valueObjectType,
                                    sql,
                                    new Object[]{ new Boolean(true)},
                                    new Type[]{ new BooleanType()},
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

            Session s = null;
            try {
                OpenSwingTreeNode root = new OpenSwingTreeNode(null);
                s = HibernateUtil.getSessionFactory().openSession();
                List<Especialidad> especialidades = s.createQuery("FROM " + Especialidad.class.getName()
                        + " M WHERE M.auditoria.activo=? "
                        + " ORDER BY M.ramo.nombre,M.nombre").//.setCacheable(true)
                        setBoolean(0, Boolean.TRUE).
                        list();
                for (Especialidad especialidad : especialidades) {
                    getRamo(especialidad, root).add(new OpenSwingTreeNode(especialidad, false));
                }

                return new VOResponse(new DefaultTreeModel(root));

            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "getTreeModel", ex);
                return new VOResponse(new DefaultTreeModel(new OpenSwingTreeNode()));
            } finally {
                s.close();
            }
        }

        public OpenSwingTreeNode getRamo(Especialidad especialidad, OpenSwingTreeNode root) {
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
