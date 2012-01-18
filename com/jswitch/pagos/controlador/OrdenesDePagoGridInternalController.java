package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.vista.OrdenDePagoDetailFrame;
import java.util.ArrayList;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Luis Adrian Gonzalez Benavides
 */
public class OrdenesDePagoGridInternalController extends DefaultGridInternalController {

    public OrdenesDePagoGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new OrdenDePagoDetailFrameController(
                OrdenDePagoDetailFrame.class.getName(), miGrid,
                (BeanVO) persistentObject, Boolean.TRUE);

    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            for (Object o : persistentObjects) {
                if (getSet() != null) {
                    getSet().remove(o);
                }
            }
            s.update(beanVO);

            for (Object object : persistentObjects) {
                Long l = ((OrdenDePago) object).getId();
                OrdenDePago ordenDePago = (OrdenDePago) s.get(OrdenDePago.class, l);
                Hibernate.initialize(ordenDePago.getNotasTecnicas());
                Hibernate.initialize(ordenDePago.getObservaciones());
                Hibernate.initialize(ordenDePago.getDocumentos());
                Hibernate.initialize(ordenDePago.getDetalleSiniestros());
                ordenDePago.setEstatusPago(EstatusPago.PENDIENTE);
                s.save(ordenDePago);
            }
            s.getTransaction().commit();
            return new VOResponse(true);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "deleteRecords", ex);
            return new ErrorResponse(ex.getMessage());
        } finally {
            s.close();
        }
    }
}
