package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.pagos.modelo.utilitario.BuscarFactura;
import com.jswitch.pagos.vista.BuscarFacturaDetailFrame;
import com.jswitch.pagos.vista.FacturaGridFrame;
import java.awt.event.ActionEvent;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 * Pagar Siniestro
 * @author Luis Adrian Gonzalez Benavides
 */
public class BuscarFacturaDetailFrameController extends DefaultDetailFrameController {

  
    public BuscarFacturaDetailFrameController() {
        super(BuscarFacturaDetailFrame.class.getName(), null, null, false);
        
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return insertRecord(persistentObject);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        new FacturaGridFrameController((BuscarFactura) newPersistentObject,
                FacturaGridFrame.class.getName());
        return new VOResponse(newPersistentObject);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        vista.getMainPanel().getSaveButton().doClick();
        vista.getMainPanel().setMode(Consts.EDIT);
    }
}
