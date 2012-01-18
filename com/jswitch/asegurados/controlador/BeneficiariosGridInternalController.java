package com.jswitch.asegurados.controlador;

import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.asegurados.vista.BeneficiarioDetailFrame;
import com.jswitch.certificados.modelo.maestra.Certificado;
import java.util.ArrayList;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.client.ReloadButton;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author bc
 */
public class BeneficiariosGridInternalController extends DefaultGridInternalController {

    private ReloadButton reload;

    public BeneficiariosGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, ReloadButton reload, DefaultGridInternalController listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
        this.reload = reload;
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new BeneficiarioDetailFrameController(BeneficiarioDetailFrame.class.getName(),
                miGrid, (BeanVO) persistentObject, true, (Certificado) beanVO, reload);

    }
}
