package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 * Controlador de la vista de los detalles de siniestro
 * @author Luis Adrian Gonzalez
 */
public class DetalleSiniestroGridFrameController extends DefaultGridFrameController {

    /**
     * Lista de datos 
     */
    List data;

    public DetalleSiniestroGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    public DetalleSiniestroGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo, List data) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
        this.data = data;
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        if (data != null) {
            return new VOListResponse(data, false, data.size());
        }
        return super.loadData(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, valueObjectType, otherGridParams);
    }

    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {
        if (attributeName.equalsIgnoreCase("etapaSiniestro.estatusSiniestro.nombre")) {
            if (value != null) {
                if (value.toString().equalsIgnoreCase("PENDIENTE")) {
                    return Color.YELLOW;
                }
                if (value.toString().equalsIgnoreCase("ANULADO")) {
                    return Color.RED;
                }
                if (value.toString().equalsIgnoreCase("PAGADO")) {
                    return Color.GREEN;
                }
            }
        }
        return super.getBackgroundColor(row, attributeName, value);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        if (persistentObject != null && persistentObject.getClass() != null) {
            new DetalleSiniestroDetailFrameController(DetalleSiniestroDetailFrame.class.getName(), gridFrame.getGridControl(), (BeanVO) persistentObject, true, persistentObject.getClass());
        }
    }
}
