

package com.jswitch.configuracion.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultAllGridFrameController;
import com.jswitch.base.modelo.Dominios.Sexo;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.AuditLogInterceptor;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.configuracion.modelo.maestra.ConfiguracionPrima;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;

/**
 *
 * @author orlandobcrra
 */
public class ConfiguracionPrimaAllGridFrameController extends DefaultAllGridFrameController {

    public ConfiguracionPrimaAllGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public boolean isCellEditable(GridControl grid, int row, String attributeName) {
//        System.out.println(attributeName);
        if (attributeName.equals("maternidad")) {
            ConfiguracionPrima configuracionPrima = (ConfiguracionPrima) grid.getVOListTableModel().getObjectForRow(row);
            if (configuracionPrima != null) {
                boolean femenino = configuracionPrima.getSexo().equals(Sexo.FEMENINO);
                if (!femenino) {
                    configuracionPrima.setMaternidad(false);
                }
                return femenino;
            }
        }
        return super.isCellEditable(grid, row, attributeName);
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
            for (Object o : newValueObjects) {
                if (o instanceof Auditable) {
                    ((Auditable) o).setAuditoria(ab);
                }

                if (((ConfiguracionPrima) o).getMaternidad()) {
                }
                s.save(o);
            }
            t.commit();
            return new VOListResponse(newValueObjects, false, newValueObjects.size());
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecords", ex));
        } finally {
            s.close();
        }
    }
//    @Override
//    public boolean validateCell(int rowNumber, String attributeName, Object oldValue, Object newValue) {
//        if(attributeName.compareTo("sexo")==0)
//        {
//
//        }
//    }
}
