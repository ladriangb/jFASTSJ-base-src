package com.jswitch.asegurados.controlador;

import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.asegurados.vista.AseguradoDetailFrame;
import org.openswing.swing.client.GridControl;
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
}
