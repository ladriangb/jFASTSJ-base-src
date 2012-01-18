package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.siniestros.modelo.maestra.detalle.APS;
import com.jswitch.siniestros.modelo.maestra.detalle.AyudaSocial;
import com.jswitch.siniestros.modelo.maestra.detalle.CartaAval;
import com.jswitch.siniestros.modelo.maestra.detalle.Emergencia;
import com.jswitch.siniestros.modelo.maestra.detalle.Funerario;
import com.jswitch.siniestros.modelo.maestra.detalle.Reembolso;
import com.jswitch.siniestros.modelo.maestra.detalle.Vida;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;

/**
 *
 * @author Orlando Becerra
 */
public class SiniestroLookupController extends DefaultLookupController {

    public SiniestroLookupController(String classname, String detalleS) {
        this.setLookupDataLocator(new PersonaLookupDataLocator(classname, detalleS));
        this.setLookupGridController(new DefaultLookupGridController());
        setLookupValueObjectClassName(classname);
        defaultModel();

    }

    class PersonaLookupDataLocator extends DefaultLookupDataLocator {

        String att;

        public PersonaLookupDataLocator(String classFullName, String idPropio) {
            super(classFullName);
            if (idPropio.equals(APS.class.getName())) {
                att = "aps";
            }
            if (idPropio.equals(CartaAval.class.getName())) {
                att = "cartaAval";
            }
            if (idPropio.equals(Emergencia.class.getName())) {
                att = "emergencia";
            }
            if (idPropio.equals(Reembolso.class.getName())) {
                att = "reembolso";
            }
            if (idPropio.equals(AyudaSocial.class.getName())) {
                att = "ayudaSocial";
            }
            if (idPropio.equals(Vida.class.getName())) {
                att = "vida";
            }
            if (idPropio.equals(Funerario.class.getName())) {
                att = "funerario";
            }

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
                        + " C WHERE C." + att
                        + "=? AND C.auditoria.activo=?";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Query q = s.createQuery(sql);
                q.setBoolean(0, true);
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
                        + " C WHERE C." + att
                        + "=? AND C.auditoria.activo=? AND upper(C.nombre) like upper(?)";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Query q = s.createQuery(sql);
                q.setBoolean(0, true);
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
