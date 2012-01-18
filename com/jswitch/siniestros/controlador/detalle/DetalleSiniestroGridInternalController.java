package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.siniestros.controlador.detalle.DetalleSiniestroDetailFrameController;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Luis Adrian Gonzalez Benavides
 */
public class DetalleSiniestroGridInternalController extends DefaultGridInternalController {

    public DetalleSiniestroGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new DetalleSiniestroDetailFrameController(DetalleSiniestroDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, true, persistentObject.getClass());
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {

        ArrayList<DetalleSiniestro> al = null;
        int max = 0;

        if (beanVO != null) {
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Query q2 = s.createQuery("SELECT c.detalleSiniestro  FROM " + Siniestro.class.getName() + " c "
                        + "  WHERE c.id=?").setLong(0, ((Siniestro) beanVO).getId());
                al = new ArrayList<DetalleSiniestro>(q2.list());
            } catch (Exception ex) {
                return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
            } finally {
                s.close();
            }
            max = al.size();
        }
        return new VOListResponse(al, false, max);

    }
}
