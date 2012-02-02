package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.modelo.utilitario.BuscarSiniestro;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 * Pagar Siniestro
 * @author Luis Adrian Gonzalez Benavides
 */
public class PagarDetrailFrameController extends DefaultDetailFrameController {

    public PagarDetrailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        BuscarSiniestro bs = (BuscarSiniestro) newPersistentObject;
        Session s = null;
      
        try {
            s=HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.getTransaction().commit();
            
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "insertRecord", e);
        } finally {
            s.close();
        }

        return null;

    }
}
