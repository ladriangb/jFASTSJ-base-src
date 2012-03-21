package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.modelo.maestra.Remesa;
import com.jswitch.pagos.vista.OrdenDePagoDetailFrame;
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
public class OrdenesDePagoGridInternalController extends DefaultGridInternalController {

    private final RemesaDetailFrameController controller;

    public OrdenesDePagoGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, RemesaDetailFrameController controller, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
        this.controller = controller;
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new OrdenDePagoDetailFrameController(
                OrdenDePagoDetailFrame.class.getName(), miGrid,
                (BeanVO) persistentObject, Boolean.TRUE);

    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {

        if (beanVO != null) {
            Session s = null;
            try {
                String select = miGrid.getVOListTableModel().
                        createSelect("C", AliasedTupleSRT.SEPARATOR);

                String sql = select + " FROM " + OrdenDePago.class.getName()
                        + " C WHERE C.remesa.id=?";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Response res = HibernateUtils.getBlockFromQuery(
                        new AliasedTupleSRT(OrdenDePago.class),
                        action,
                        startIndex,
                        General.licencia.getBlockSize(),
                        filteredColumns,
                        currentSortedColumns,
                        currentSortedVersusColumns,
                        valueObjectType,
                        sql,
                        new Object[]{((Auditable) beanVO).getId()},
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
                Long l = ((OrdenDePago) object).getId();
                OrdenDePago ordenDePago = (OrdenDePago) s.get(OrdenDePago.class, l);
                Hibernate.initialize(ordenDePago.getRemesa());
                ordenDePago.setEstatusPago(EstatusPago.EN_ADMINISTRACION);
                ordenDePago.setRemesa(null);
                s.save(ordenDePago);
            }
            s.getTransaction().commit();
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
