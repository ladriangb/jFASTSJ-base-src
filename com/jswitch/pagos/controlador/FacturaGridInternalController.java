/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.pagos.vista.FacturaDetailFrame;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.util.ArrayList;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.client.ReloadButton;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class FacturaGridInternalController extends DefaultGridInternalController {

    ReloadButton reload;
    public FacturaGridInternalController(GridControl miGrid,ReloadButton reload) {
        super(miGrid);
        this.reload=reload;
        try {
            t = Class.forName(DetalleSiniestro.class.getName());
            getMethod = t.getMethod("getPagos");
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }

    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        return super.deleteRecords(persistentObjects);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new FacturaDetailFrameController(FacturaDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject,(DetalleSiniestro)beanVO, Boolean.TRUE,reload);
    }
}