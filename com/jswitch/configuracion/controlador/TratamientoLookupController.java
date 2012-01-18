package com.jswitch.configuracion.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.dominio.Cobertura;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import com.jswitch.configuracion.modelo.dominio.patologias.Tratamiento;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.BooleanType;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.send.java.FilterWhereClause;
import org.openswing.swing.util.java.Consts;
import org.openswing.swing.util.server.HibernateUtils;

/**
 * @author adrian
 */
public class TratamientoLookupController extends DefaultLookupController {

    private Diagnostico diagnostico;

    public TratamientoLookupController(Diagnostico d) {
        this.diagnostico = d;
        this.setLookupDataLocator(new TratamientoLookupDataLocator(Tratamiento.class.getName()));
        this.setLookupGridController(new DefaultLookupGridController());
        setLookupValueObjectClassName(Tratamiento.class.getName());
        setAllColumnVisible(false);
        setVisibleColumn("nombre", true);
        setPreferredWidthColumn("nombre", 200);
        setSortedColumn("nombre", Consts.DESC_SORTED, 0);
        setSortableColumn("nombre", true);
        setFramePreferedSize(new java.awt.Dimension(200, 340));
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
            filteredColumns.put(
                    "diagnostico.id",
                    new FilterWhereClause[]{
                        new FilterWhereClause("diagnostico.id", "=", diagnostico.getId()),
                        null
                    });
            //return new VOListResponse(new ArrayList(marca.getModelos()), false, marca.getModelos().size());
            Session s = null;
            try {
                String sql = "FROM " + Tratamiento.class.getName()
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
    }
}
