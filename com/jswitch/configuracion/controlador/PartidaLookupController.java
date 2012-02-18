package com.jswitch.configuracion.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.dominio.PartidaPresupuestaria;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.BooleanType;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.util.java.Consts;
import org.openswing.swing.util.server.HibernateUtils;

/**
 * @author adrian
 */
public class PartidaLookupController extends DefaultLookupController {

    public PartidaLookupController() {

        this.setLookupDataLocator(new TratamientoLookupDataLocator(PartidaPresupuestaria.class.getName()));
        this.setLookupGridController(new DefaultLookupGridController());
        setLookupValueObjectClassName(PartidaPresupuestaria.class.getName());
        setAllColumnVisible(false);
        setVisibleColumn("nombre", true);
        setVisibleColumn("numero", true);
        setPreferredWidthColumn("nombre", 200);
        setPreferredWidthColumn("numero", 200);
        setSortableColumn("nombre", true);
        setSortableColumn("numero", true);
        setSortedColumn("nombre", Consts.DESC_SORTED, 0);

        setFramePreferedSize(new java.awt.Dimension(430, 340));
        setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_FOCUS);
    }

    class TratamientoLookupDataLocator extends DefaultLookupDataLocator {

        public TratamientoLookupDataLocator(String classFullName) {
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
                String sql = "FROM " + PartidaPresupuestaria.class.getName()
                        + " C WHERE  C.auditoria.activo=?";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Response res = HibernateUtils.getAllFromQuery(
                        filteredColumns,
                        currentSortedColumns,
                        currentSortedVersusColumns,
                        valueObjectType,
                        sql,
                        new Object[]{new Boolean(true)},
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
//            Session s = null;
//            try {
//                s = HibernateUtil.getSessionFactory().openSession();
//                List l = s.createQuery("FROM " + Tratamiento.class.getName() + " T "
//                        + "WHERE T.diagnostico.id=?").setLong(0, d.getId()).list();
//                return new VOListResponse(l, false, l.size());
//            } catch (Exception e) {
//                return new ErrorResponse(e.getMessage());
//            } finally {
//                s.close();
//            }

        }

        @Override
        public Response validateCode(String code) {
            Session s = null;
            try {
                String sql = "FROM " + PartidaPresupuestaria.class.getName()
                        + " C WHERE  C.auditoria.activo=? and UPPER(nombre) like ?";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                List l = s.createQuery(sql).setBoolean(0, Boolean.TRUE).setString(1, "%" + code + "%").list();
                int i = 0;
                if (l != null) {
                    return new VOListResponse();
                }
                return new VOListResponse(l, false, l.size());
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "loadData", ex);
                return new ErrorResponse(ex.getMessage());
            } finally {
                s.close();
            }
        }
    }
}
