package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.siniestros.vista.DiagnosticoSiniestroDetailFrame;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class DiagnosticoSiniestroGridInternalController extends DefaultGridInternalController {

    DefaultDetailFrame vista;

    public DiagnosticoSiniestroGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultDetailFrame vista, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
        this.vista = vista;
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new DiagnosticoSiniestroDetailFrameController(DiagnosticoSiniestroDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, Boolean.TRUE, vista);
    }
}
