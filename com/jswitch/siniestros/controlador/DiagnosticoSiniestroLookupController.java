package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
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
import org.openswing.swing.message.send.java.FilterWhereClause;
import org.openswing.swing.util.java.Consts;
import org.openswing.swing.util.server.HibernateUtils;

/**
 * @author adrian
 */
public class DiagnosticoSiniestroLookupController extends DefaultLookupController {

    private DetalleSiniestro detalleSiniestro;

    public DiagnosticoSiniestroLookupController() {
        this.detalleSiniestro = null;
        this.setLookupDataLocator(new DiagnosticoSiniestroLookupDataLocator(DiagnosticoSiniestro.class.getName()));
        this.setLookupGridController(new DefaultLookupGridController());
        setLookupValueObjectClassName(DiagnosticoSiniestro.class.getName());
        setAllColumnVisible(false);
        setVisibleColumn("diagnostico.especialidad.ramo.nombre", true);
        setVisibleColumn("diagnostico.especialidad.nombre", true);
        setVisibleColumn("diagnostico.nombre", true);
        setVisibleColumn("montoPendiente", true);
        setVisibleColumn("montoPagado", true);
        setPreferredWidthColumn("diagnostico.especialidad.ramo.nombre", 70);
        setPreferredWidthColumn("diagnostico.especialidad.nombre", 100);
        setPreferredWidthColumn("diagnostico.nombre", 100);
        setPreferredWidthColumn("montoPendiente", 50);
        setPreferredWidthColumn("montoPagado", 50);
        setFilterableColumn("diagnostico.especialidad.ramo.nombre", true);
        setFilterableColumn("diagnostico.especialidad.nombre", true);
        setFilterableColumn("diagnostico.nombre", true);
        setFilterableColumn("montoPendiente", true);
        setFilterableColumn("montoPagado", true);
        setSortableColumn("diagnostico.especialidad.ramo.nombre", true);
        setSortableColumn("diagnostico.especialidad.nombre", true);
        setSortableColumn("diagnostico.nombre", true);
        setSortableColumn("montoPendiente", true);
        setSortableColumn("montoPagado", true);
        setSortedColumn("diagnostico.especialidad.ramo.nombre", Consts.ASC_SORTED);
        setFramePreferedSize(new java.awt.Dimension(400, 340));
        setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_FOCUS);

    }

    public void setDetalleSiniestro(DetalleSiniestro detalleSiniestro) {
        this.detalleSiniestro = detalleSiniestro;
    }

    class DiagnosticoSiniestroLookupDataLocator extends DefaultLookupDataLocator {

        public DiagnosticoSiniestroLookupDataLocator(String classFullName) {
            super(classFullName);
        }

        @Override
        public Response loadData(int action,
                int startIndex,
                Map filteredColumns,
                ArrayList currentSortedColumns,
                ArrayList currentSortedVersusColumns,
                Class valueObjectType) {
            if (detalleSiniestro != null) {
                Session s = null;
                try {
                    String sql = "FROM " + DiagnosticoSiniestro.class.getName()
                            + " C WHERE C.auditoria.activo=? AND C.montoPendiente > 0";
                    filteredColumns.put(
                            "detalleSiniestro.id",
                            new FilterWhereClause[]{
                                new FilterWhereClause("detalleSiniestro.id", "=", detalleSiniestro.getId()),
                                null
                            });
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
            } else {
                return new VOListResponse();
            }
        }

        @Override
        public Response validateCode(String code) {
            if (detalleSiniestro != null) {
                Session s = null;
                try {
                    String sql = "FROM " + DiagnosticoSiniestro.class.getName()
                            + " C WHERE C.detalleSiniestro.id = ?";// + detalleSiniestro.getId() ;
                    sql += " AND C.auditoria.activo=? AND upper(C.diagnostico.nombre) like ?";
                    s = HibernateUtil.getSessionFactory().openSession();
                    List list = s.createQuery(sql).
                            setLong(0, detalleSiniestro.getId()).
                            setBoolean(1, Boolean.TRUE).
                            setString(2, "%" + code.toUpperCase().trim() + "%").list();
                    return new VOListResponse(list, false, list.size());
                } catch (Exception ex) {
                    LoggerUtil.error(this.getClass(), "validateCode", ex);
                    return new ErrorResponse(ex.getMessage());
                } finally {
                    s.close();
                }
            } else {
                return new VOListResponse();
            }
        }
    }
}
