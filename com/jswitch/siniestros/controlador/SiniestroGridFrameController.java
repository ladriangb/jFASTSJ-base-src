/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.vista.SiniestroDetailFrame;
import com.jswitch.siniestros.vista.SiniestroGridFrame;
import java.util.ArrayList;
import java.util.Map;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author adrian
 */
public class SiniestroGridFrameController extends DefaultGridFrameController {

    Response res;

    public SiniestroGridFrameController() {
        this.detailFramePath = SiniestroDetailFrame.class.getName();
        this.claseModeloFullPath = Siniestro.class.getName();
        gridFrame = new SiniestroGridFrame();
        gridFrame.inicializar(this, this, claseModeloFullPath, claseModeloFullPath, true);
    }

    public SiniestroGridFrameController(Response list) {
        this();
        res = list;

    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new SiniestroDetailFrameController(SiniestroDetailFrame.class.getName(), gridFrame.getGridControl(), (BeanVO) persistentObject, false);
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        if (res != null) {
            return res;
        }
        return super.loadData(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, valueObjectType, otherGridParams);
    }
}
