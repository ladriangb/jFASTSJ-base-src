/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import java.awt.Color;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class RemesaGridFrameController extends DefaultGridFrameController {

    public RemesaGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {
        if (attributeName.equalsIgnoreCase("estatusPago")) {
            if (value != null) {
                switch ((EstatusPago) value) {
                    case ANULADO:
                        return Color.RED;
                    case PAGADO:
                        return Color.GREEN;
                    case PENDIENTE:
                        return Color.YELLOW;                        
                    case SELECCIONADO:
                        return Color.CYAN;
                }
            }
        }
        return super.getBackgroundColor(row, attributeName, value);
    }     
    
    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
            if (detailFramePath != null) {
            new RemesaDetailFrameController(detailFramePath, gridFrame.getGridControl(), (BeanVO) persistentObject, true);
        }
    }
}
