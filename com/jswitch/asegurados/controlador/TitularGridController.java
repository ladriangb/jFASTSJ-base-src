package com.jswitch.asegurados.controlador;

import com.jswitch.asegurados.vista.TitularDetailFrame;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Orlando Becerra
 */
public class TitularGridController extends DefaultGridFrameController {

    public TitularGridController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new TitularDetailFrameController(TitularDetailFrame.class.getName(), null, (BeanVO) persistentObject, false);
    }
}
