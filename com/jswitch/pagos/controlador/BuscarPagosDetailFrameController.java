package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.modelo.utilitario.BuscarPagos;
import com.jswitch.pagos.vista.BuscarOrdenDePagoDetailFrame;
import com.jswitch.pagos.vista.BuscarRemesaDetailFrame;
import java.awt.event.ActionEvent;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 * Pagar Siniestro
 * @author Luis Adrian Gonzalez Benavides
 */
public class BuscarPagosDetailFrameController extends DefaultDetailFrameController {

    private Class clase;

    public BuscarPagosDetailFrameController(Class clase) {
        super(clase.equals(OrdenDePago.class)
                ? BuscarOrdenDePagoDetailFrame.class.getName()
                : BuscarRemesaDetailFrame.class.getName(), null, null, false);
        this.clase = clase;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        vista.getMainPanel().getSaveButton().doClick();
        vista.getMainPanel().setMode(Consts.EDIT);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return insertRecord(persistentObject);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        if(clase.equals(OrdenDePago.class))
        new OrdenDePagoGridFrameController((BuscarPagos) newPersistentObject);
        else
            new RemesaGridFrameController((BuscarPagos) newPersistentObject);
        return new VOResponse(newPersistentObject);

    }
}
