/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jswitch.asegurados.controlador;

import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class BeneficiarioGridFrameController extends DefaultGridFrameController{

    public BeneficiarioGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
   super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }


    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
       if (detailFramePath != null) {
            new BeneficiarioDetailFrameController(detailFramePath, gridFrame.getGridControl(), (BeanVO) persistentObject, false);
        }
    }

}
