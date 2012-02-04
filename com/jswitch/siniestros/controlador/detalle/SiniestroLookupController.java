package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import java.awt.Dimension;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author Orlando Becerra
 */
public class SiniestroLookupController extends DefaultLookupController {

    public SiniestroLookupController() {
        this.setLookupDataLocator(new PersonaLookupDataLocator(Siniestro.class.getName()));
        this.setLookupGridController(new DefaultLookupGridController());
        setLookupValueObjectClassName(Siniestro.class.getName());
           setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_LAST_VALID_CODE);
        setAllColumnVisible(false);
        setVisibleColumn("id", true);
        setPreferredWidthColumn("id", 50);
        setSortableColumn("id", true);
        setSortedColumn("id", Consts.DESC_SORTED, 0);
        setFramePreferedSize(new Dimension(300, 330));

    }

    class PersonaLookupDataLocator extends DefaultLookupDataLocator {

        public PersonaLookupDataLocator(String classFullName) {
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
                String sql = "FROM " + classFullName
                        + " C WHERE  AND C.auditoria.activo=?";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Query q = s.createQuery(sql);
                q.setBoolean(0, true);
                List l = q.list();
                return new VOListResponse(l, false, l.size());
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

                String sql = "FROM " + classFullName
                        + " C WHERE " 
                        + " AND C.auditoria.activo=? AND upper(C.nombre) like upper(?)";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Query q = s.createQuery(sql);
                q.setBoolean(0, true);
                q.setString(1, "%" + code.toUpperCase().trim() + "%");
                List l = q.list();
                return new VOListResponse(l, false, l.size());
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "validateCode", ex);
                return new ErrorResponse(ex.getMessage());
            } finally {
                s.close();
            }
        }
    }
}
