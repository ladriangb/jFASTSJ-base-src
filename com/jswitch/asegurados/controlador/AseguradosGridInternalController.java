package com.jswitch.asegurados.controlador;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.asegurados.vista.AseguradoDetailFrame;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Transaction;
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
public class AseguradosGridInternalController extends DefaultGridInternalController {

    public AseguradosGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new AseguradoDetailFrameController(AseguradoDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, true);
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
      Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            for (Object o : persistentObjects) {
                if (getSet() != null) {
                    getSet().remove(o);
                }
                ((Asegurado)o).setCertificado(null);
                s.update(o);
            }
            s.update(beanVO);
            t.commit();
            return new VOResponse(true);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "deleteRecords", ex);
            return new ErrorResponse(ex.getMessage());
        } finally {
            s.close();
        }
        
    }
    
    
}
