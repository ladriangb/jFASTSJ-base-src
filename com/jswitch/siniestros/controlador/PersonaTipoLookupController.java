package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.persona.modelo.maestra.Persona;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.BooleanType;
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
public class PersonaTipoLookupController extends DefaultLookupController {

    private TipoPersona tipoPersona;
    private String[] tp;

    /**
     * pone por defauld clinicas laboratiorios y medicos
     */
    public PersonaTipoLookupController(String[] tiposPersona) {
        this.tp = tiposPersona;
        this.setLookupDataLocator(new MarcaModeloLookupDataLocator(Persona.class.getName()));
        this.setFramePreferedSize(new Dimension(500, 500));
        this.setCodeSelectionWindow(PersonaTipoLookupController.TREE_GRID_FRAME);
        this.getLookupDataLocator().setNodeNameAttribute("nombre");
        this.setLookupValueObjectClassName(Persona.class.getName());
        this.setAllColumnVisible(false);
        this.setVisibleColumn("rif.rif", true);
        this.setVisibleColumn("nombreLargo", true);
        this.setPreferredWidthColumn("rif.rif", 100);
        this.setPreferredWidthColumn("nombreLargo", 200);
        this.setFilterableColumn("rif.rif", true);
        this.setFilterableColumn("nombreLargo", true);
        this.setSortedColumn("nombreLargo", Consts.ASC_SORTED);

        addLookup2ParentLink("personaPago");

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
                    TipoPersona tipoPersona = (TipoPersona) map.get(Consts.TREE_FILTER);
                    if (tipoPersona.getId() != -1) {
                        setTipoPersona(tipoPersona);
                        Session s = null;
                        try {

                            String sql = "SELECT DISTINCT C FROM " + Persona.class.getName()
                                    + " C LEFT JOIN C.tiposPersona T "
                                    + "WHERE T.idPropio = '" + tipoPersona.getIdPropio() + "'";
                            sql += " AND C.auditoria.activo=?";
                            SessionFactory sf = HibernateUtil.getSessionFactory();
                            s = sf.openSession();
                            Response res = HibernateUtils.getAllFromQuery(
                                    filteredColumns,
                                    currentSortedColumns,
                                    currentSortedVersusColumns,
                                    valueObjectType,
                                    sql,
                                    new Object[]{Boolean.TRUE},
                                    new Type[]{new BooleanType()},
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
                String sql = "FROM " + TipoPersona.class.getName();
                if (tp != null) {
                    sql += " M WHERE ";
                    for (int i = 0; i < tp.length; i++) {
                        if (i > 0) {
                            sql += "OR ";
                        }
                        sql += "M.idPropio=? ";
                    }
                }
                DefaultMutableTreeNode root = new OpenSwingTreeNode(null);
                s = HibernateUtil.getSessionFactory().openSession();
                Query q1 = s.createQuery(sql
                        + " ORDER BY M.nombre"); //.setCacheable(true)
                if (tp != null) {
                    for (int i = 0; i < tp.length; i++) {
                        q1.setString(i, tp[i]);
                    }
                }
                List titulares = q1.list();
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

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
}
