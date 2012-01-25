package com.jswitch.configuracion.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.maestra.TimbreMunicipal;
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
public class TimbreMunicipalLookupController extends DefaultLookupController {

    public TimbreMunicipalLookupController() {
        this.setLookupDataLocator(new TimbreMunicipalLookupDataLocator(TimbreMunicipal.class.getName()));
        this.setLookupGridController(new DefaultLookupGridController());
        setLookupValueObjectClassName(TimbreMunicipal.class.getName());
        setAllColumnVisible(false);
        setVisibleColumn("zipCode", true);
        setPreferredWidthColumn("zipCode", 100);
        setSortableColumn("zipCode", true);
        setFilterableColumn("zipCode", true);
        setVisibleColumn("nombre", true);
        setPreferredWidthColumn("nombre", 200);
        setSortableColumn("nombre", true);
        setFilterableColumn("nombre", true);
        setSortedColumn("zipCode", Consts.DESC_SORTED, 0);
        setFramePreferedSize(new java.awt.Dimension(300, 340));
        setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_FOCUS);
    }

    class TimbreMunicipalLookupDataLocator extends DefaultLookupDataLocator {

        public TimbreMunicipalLookupDataLocator(String classFullName) {
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
                String sql = "FROM " + TimbreMunicipal.class.getName()
                        + " C WHERE  C.auditoria.activo=?";
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

        }

        @Override
        public Response validateCode(String code) {
            Session s = null;
            try {
                String sql = "FROM " + TimbreMunicipal.class.getName()
                        + " C WHERE  C.auditoria.activo=:ac AND zipCode like :code";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                List l = s.createQuery(sql).
                        setBoolean("ac", Boolean.TRUE).setString("code", "%" + code + "%").list();
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
