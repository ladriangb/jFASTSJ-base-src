package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.controlador.detalle.DetalleSiniestroDetailFrameController;
import com.jswitch.siniestros.controlador.detalle.DetalleSiniestroGridFrameController;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.utilitario.BuscarSiniestro;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroGridFrame;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 * Buscador de Siniestros
 * @author Luis Adrian Gonzalez Benavides
 */
public class BuscarDetalleSiniestroDetrailController extends DefaultDetailFrameController {

    public BuscarDetalleSiniestroDetrailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return insertRecord(persistentObject);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        BuscarSiniestro bs = (BuscarSiniestro) newPersistentObject;
        new DetalleSiniestroGridFrameController(
                DetalleSiniestroGridFrame.class.getName(),
                DetalleSiniestroDetailFrame.class.getName(),
                bs.getTipoDetalleSiniestro().getClase(),
                "Detalle Siniestro", bs);

        return new VOResponse(newPersistentObject);

    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        return super.logicaNegocio(persistentObject);
    }

    @Override
    public Response logicaNegocioDespuesSave(ValueObject persistentObject, Session s) {
        return super.logicaNegocioDespuesSave(persistentObject, s);
    }
}
