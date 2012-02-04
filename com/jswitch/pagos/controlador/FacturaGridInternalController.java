package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.pagos.vista.FacturaDetailFrame;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.client.ReloadButton;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Adrian
 */
public class FacturaGridInternalController extends DefaultGridInternalController {

    ReloadButton reload;

    public FacturaGridInternalController(GridControl miGrid, ReloadButton reload) {
        super(miGrid);
        this.reload = reload;
        try {
            t = Class.forName(DetalleSiniestro.class.getName());
            getMethod = t.getMethod("getPagos");
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        if (beanVO != null) {
            Session s = null;
            try {
                String sql = "FROM " + Factura.class.getName() + " C "
                        + "WHERE C.detalleSiniestro.id=?";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Response res = HibernateUtils.getAllFromQuery(
                        filteredColumns,
                        currentSortedColumns,
                        currentSortedVersusColumns,
                        valueObjectType,
                        sql,
                        new Object[]{((DetalleSiniestro) beanVO).getId()},
                        new Type[]{new LongType()},
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
        return new VOResponse();
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            for (Object object : persistentObjects) {
                Long l = ((Factura) object).getId();
                Factura sin = (Factura) s.get(Factura.class, l);
                Hibernate.initialize(sin.getDesgloseCobertura());
                Hibernate.initialize(sin.getDesgloseSumaAsegurada());
                sin.setDetalleSiniestro(null);
                s.delete(sin);
            }
            s.getTransaction().commit();
            return new VOResponse(true);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "deleteRecords", ex);
            return new ErrorResponse(ex.getMessage());
        } finally {
            s.close();
            reload.doClick();
        }
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new FacturaDetailFrameController(FacturaDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, (DetalleSiniestro) beanVO, Boolean.TRUE, reload);
    }
}