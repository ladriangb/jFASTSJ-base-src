package com.jswitch.configuracion.controlador;

import com.jswitch.base.controlador.General;
import java.util.ArrayList;
import java.util.Map;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.configuracion.modelo.maestra.ConfiguracionProntoPago;
import java.util.Date;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Orlando Becerra
 * @author Adrian Gonzalez
 */
public class ConfiguracionProntoPagoGridFrameController extends DefaultGridFrameController implements GridDataLocator {

    public ConfiguracionProntoPagoGridFrameController() {
    }

    public ConfiguracionProntoPagoGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns,
            ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {
            String sql = "FROM " + ConfiguracionProntoPago.class.getName() + " C ";
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            Response res = HibernateUtils.getBlockFromQuery(
                    action,
                    startIndex,
                    General.licencia.getBlockSize(),
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
            return res;
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "loadData", ex);
            return new ErrorResponse(ex.getMessage());
        } finally {
            s.close();
        }
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Session s = null;
        Long val = ((ConfiguracionProntoPago) newValueObjects.get(0)).getPersona().getId();
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            ConfiguracionProntoPago cpp =
                    (ConfiguracionProntoPago) s.createQuery("FROM "
                    + ConfiguracionProntoPago.class.getName() + " C "
                    + "WHERE C.persona.id=?").setLong(0, val).uniqueResult();
            if (cpp != null) {
                return new ErrorResponse("Ya esta persona tiene Configuracion de Pronto Pago");
            }
            Transaction t = s.beginTransaction();
            AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
            for (Object o : newValueObjects) {
                if (o instanceof Auditable) {
                    ((Auditable) o).setAuditoria(ab);
                }
                s.save(o);
                gridFrame.setOwnerVO((BeanVO) o);
            }
            t.commit();

            return new VOListResponse(newValueObjects, false, newValueObjects.size());
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecords", ex));
        } finally {
            s.close();
        }
    }
}
