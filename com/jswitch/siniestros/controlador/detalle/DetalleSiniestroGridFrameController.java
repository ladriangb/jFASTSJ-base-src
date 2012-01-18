/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class DetalleSiniestroGridFrameController extends DefaultGridFrameController {

    public DetalleSiniestroGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }
  
    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        if (persistentObject != null && persistentObject.getClass() != null) {
            new DetalleSiniestroDetailFrameController(DetalleSiniestroDetailFrame.class.getName(), gridFrame.getGridControl(), (BeanVO) persistentObject, true, persistentObject.getClass());
        }
    }
}
