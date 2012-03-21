package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.siniestros.controlador.detalle.DetalleSiniestroDetailFrameController;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.transform.AliasedTupleSRT;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Luis Adrian Gonzalez Benavides
 */
public class DetalleSiniestroLiquidadosGridInternalController extends DefaultGridInternalController {

    OrdenDePagoDetailFrameController controller;

    public DetalleSiniestroLiquidadosGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, OrdenDePagoDetailFrameController controller, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
        this.controller = controller;
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new DetalleSiniestroDetailFrameController(
                DetalleSiniestroDetailFrame.class.getName(), miGrid,
                (BeanVO) persistentObject, true, persistentObject.getClass());
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {

        if (beanVO != null) {
            Session s = null;
            try {
                String select = miGrid.getVOListTableModel().
                        createSelect("C", AliasedTupleSRT.SEPARATOR);
                select += ", C.etapaSiniestro.idPropio as etapaSiniestro_idPropio ";
                String sql = select + "FROM " + DetalleSiniestro.class.getName() + " C "
                        + "WHERE C.ordenDePago.id=?";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Response res = HibernateUtils.getBlockFromQuery(
                        new AliasedTupleSRT(DetalleSiniestro.class),
                        action,
                        startIndex,
                        General.licencia.getBlockSize(),
                        filteredColumns,
                        currentSortedColumns,
                        currentSortedVersusColumns,
                        valueObjectType,
                        sql,
                        new Object[]{((OrdenDePago) beanVO).getId()},
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
        EtapaSiniestro es = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            es = (EtapaSiniestro) s.createQuery("FROM "
                    + EtapaSiniestro.class.getName() + " C WHERE "
                    + "idPropio=?").setString(0, "LIQ").uniqueResult();
            for (Object object : persistentObjects) {
                Long l = ((DetalleSiniestro) object).getId();
                DetalleSiniestro sin = (DetalleSiniestro) s.get(DetalleSiniestro.class, l);
//                Hibernate.initialize(sin.getNotasTecnicas());
//                Hibernate.initialize(sin.getObservaciones());
//                Hibernate.initialize(sin.getPagos());
//                Hibernate.initialize(sin.getDiagnosticoSiniestros());
//                Hibernate.initialize(sin.getDocumentos());
                sin.setEtapaSiniestro(es);
                sin.setOrdenDePago(null);
                s.update(sin);
            }
            s.getTransaction().commit();
            // s.update(beanVO);
            return new VOResponse(true);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "deleteRecords", ex);
            return new ErrorResponse(ex.getMessage());
        } finally {
            s.close();
            controller.getVista().getMainPanel().getReloadButton().doClick();
        }
    }
}
