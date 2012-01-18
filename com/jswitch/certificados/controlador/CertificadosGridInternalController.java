package com.jswitch.certificados.controlador;

import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.certificados.vista.CertificadoDetailFrame;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Luis Adrian Gonzalez Benavides
 */
public class CertificadosGridInternalController extends DefaultGridInternalController {

    public CertificadosGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid,DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new CertificadoDetailController(CertificadoDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, true);
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        ArrayList al;
        Collection c = getSet();
        if (c != null) {
            al = new ArrayList(c);
        } else {
            al = new ArrayList(0);
        }
        return new VOListResponse(al, false, al.size());
    }
}
