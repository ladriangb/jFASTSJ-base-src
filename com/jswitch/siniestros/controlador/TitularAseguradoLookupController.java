package com.jswitch.siniestros.controlador;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.certificados.modelo.maestra.Certificado;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
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
public class TitularAseguradoLookupController extends DefaultLookupController {

    public TitularAseguradoLookupController() {
        this.setLookupDataLocator(new MarcaModeloLookupDataLocator(Asegurado.class.getName()));
        this.setFramePreferedSize(new Dimension(500, 500));
        this.setCodeSelectionWindow(TitularAseguradoLookupController.TREE_GRID_FRAME);
        this.getLookupDataLocator().setNodeNameAttribute("id");
        this.setLookupValueObjectClassName(Asegurado.class.getName());
        //this.addLookup2ParentLink("marcaModelo");
        this.setAllColumnVisible(false);
//        this.setVisibleColumn("id", true);
        this.setVisibleColumn("persona.nombreLargo", true);
        //this.setVisibleColumn("nombre", true);
        //this.setPreferredWidthColumn("id", 50);
        this.setPreferredWidthColumn("persona.nombreLargo", 250);
        //this.setPreferredWidthColumn("marca.nombre", 150);
        this.setFilterableColumn("persona.nombreLargo", true);
        //this.setFilterableColumn("marca.nombre", true);
        //this.setSortableColumn("marca.nombre", true);
        this.setSortableColumn("persona.nombreLargo", true);
        this.setSortedColumn("persona.nombreLargo", Consts.ASC_SORTED);

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
                    Certificado certificado = (Certificado) map.get(Consts.TREE_FILTER);
                    if (certificado.getId() != -1) {
                        filteredColumns.put(
                                "certificado.id",
                                new FilterWhereClause[]{
                                    new FilterWhereClause("certificado.id", "=", certificado.getId()),
                                    null
                                });
                        //return new VOListResponse(new ArrayList(marca.getModelos()), false, marca.getModelos().size());
                        Session s = null;
                        try {
                            String sql = "FROM " + Asegurado.class.getName() + " C ";
                            SessionFactory sf = HibernateUtil.getSessionFactory();
                            s = sf.openSession();
                            Response res = HibernateUtils.getAllFromQuery(
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
                            filteredColumns = new HashMap(0);
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
                DefaultMutableTreeNode root = new OpenSwingTreeNode(null);
                s = HibernateUtil.getSessionFactory().openSession();
                List titulares = s.createQuery("FROM " + Certificado.class.getName() + " M ORDER BY M.titular.persona.nombreLargo") //.setCacheable(true)
                        .list();
                for (Object titular : titulares) {
                    root.add(new OpenSwingTreeNode(titular));
                }
                return new VOResponse(new DefaultTreeModel(root));
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "getTreeModel", ex);
                return new VOResponse(new DefaultTreeModel(new OpenSwingTreeNode()));
            } finally {
                s.close();
            }
        }
    }
}
