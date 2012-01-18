/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jswitch.polizas.controlador;

import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.polizas.vista.PolizaDetailFrame;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author orlandobcrra
 */
public class PolizasGridFrameController extends DefaultGridFrameController{


    public PolizasGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new PolizaDetailFrameController(PolizaDetailFrame.class.getName(), null,(BeanVO) persistentObject , false);
    }





}

