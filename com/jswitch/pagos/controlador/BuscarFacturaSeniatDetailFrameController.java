package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.pagos.modelo.utilitario.BuscarFactura;
import com.jswitch.pagos.vista.BuscarFacturaSeniatDetailFrame;
import com.jswitch.pagos.vista.FacturaSeniatGridFrame;
import java.awt.event.ActionEvent;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 * Pagar Siniestro
 * @author Luis Adrian Gonzalez Benavides
 */
public class BuscarFacturaSeniatDetailFrameController extends DefaultDetailFrameController {

    private String gridPath;

    public BuscarFacturaSeniatDetailFrameController(String gridPath) {
        super(BuscarFacturaSeniatDetailFrame.class.getName(), null, null, false);
        this.gridPath = gridPath;
        
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return insertRecord(persistentObject);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        new FacturaSeniatGridFrameController((BuscarFactura) newPersistentObject,
                gridPath);
        return new VOResponse(newPersistentObject);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        vista.getMainPanel().getSaveButton().doClick();
        vista.getMainPanel().setMode(Consts.EDIT);
    }
}
