package com.jswitch.pagos.controlador;


import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;

/**
 *
 * @author Luis Adrian Gonzalez Benavides
 */
public class EtapasPagadasLookupController extends DefaultLookupController {

    public EtapasPagadasLookupController(String classname) {
        this.setLookupDataLocator(new PersonaLookupDataLocator(classname));
        this.setLookupGridController(new DefaultLookupGridController());
        setLookupValueObjectClassName(classname);
        defaultModel();

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
                        + " C WHERE C.estatusSiniestro.nombre"
                        + "=? AND C.auditoria.activo=?";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Query q = s.createQuery(sql);
                q.setString(0, "PAGADO");
                q.setBoolean(1, true);
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
                        + " C WHERE C.estatusSiniestro.nombre" 
                        + "=? AND C.auditoria.activo=? AND upper(C.nombre) like upper(?)";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Query q = s.createQuery(sql);
                q.setString(0, "PAGADO");
                q.setBoolean(1, true);
                q.setString(2, "%" + code.toUpperCase().trim() + "%");
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
