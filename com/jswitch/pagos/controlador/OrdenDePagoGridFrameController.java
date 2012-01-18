package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class OrdenDePagoGridFrameController extends DefaultGridFrameController {

    public OrdenDePagoGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new OrdenDePagoDetailFrameController(detailFramePath, null,
                (BeanVO) persistentObject, Boolean.TRUE);
    }
}
