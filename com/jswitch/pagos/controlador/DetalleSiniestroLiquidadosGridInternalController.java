package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.siniestros.controlador.detalle.DetalleSiniestroDetailFrameController;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
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
public class DetalleSiniestroLiquidadosGridInternalController extends DefaultGridInternalController {

    public DetalleSiniestroLiquidadosGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new DetalleSiniestroDetailFrameController(
                DetalleSiniestroDetailFrame.class.getName(), miGrid,
                (BeanVO) persistentObject, true, persistentObject.getClass());
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        Session s = null;
        EtapaSiniestro es = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            for (Object o : persistentObjects) {
                if (getSet() != null) {
                    getSet().remove(o);
                }
            }
            s.update(beanVO);
            es = (EtapaSiniestro) s.createQuery("FROM "
                    + EtapaSiniestro.class.getName() + " C WHERE "
                    + "idPropio=?").setString(0, "LIQ").uniqueResult();
            for (Object object : persistentObjects) {
                Long l = ((DetalleSiniestro) object).getId();
                DetalleSiniestro sin = (DetalleSiniestro) s.get(DetalleSiniestro.class, l);
                Hibernate.initialize(sin.getNotasTecnicas());
                Hibernate.initialize(sin.getObservaciones());
                Hibernate.initialize(sin.getPagos());
                Hibernate.initialize(sin.getDiagnosticoSiniestros());
                Hibernate.initialize(sin.getDocumentos());
                sin.setEtapaSiniestro(es);
                s.update(sin);
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
