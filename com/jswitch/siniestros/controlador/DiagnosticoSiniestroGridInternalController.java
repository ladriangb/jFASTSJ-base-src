package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.configuracion.modelo.transaccional.SumaAmparada;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
import com.jswitch.siniestros.vista.DiagnosticoSiniestroDetailFrame;
import com.jswitch.vistasbd.ListaDiagnostico;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.transform.AliasedTupleSRT;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Adrian
 */
public class DiagnosticoSiniestroGridInternalController extends DefaultGridInternalController {

    DefaultDetailFrame vista;

    public DiagnosticoSiniestroGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultDetailFrame vista, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
        this.vista = vista;
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new DiagnosticoSiniestroDetailFrameController(DiagnosticoSiniestroDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, Boolean.TRUE, vista);
    }

    public Response loadData(int action,
            int startIndex,
            Map filteredColumns,
            ArrayList currentSortedColumns,
            ArrayList currentSortedVersusColumns,
            Class valueObjectType,
            Map otherGridParams) {
        List al = null;
        if (beanVO != null) {
            Session s = null;
            try {

                String select = miGrid.getVOListTableModel().
                        createSelect("C", AliasedTupleSRT.SEPARATOR);
                String sql = select + "FROM " + DiagnosticoSiniestro.class.getName() + " C "
                        + " WHERE C.detalleSiniestro.id=?";

                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Response res = HibernateUtils.getBlockFromQuery(new AliasedTupleSRT(DiagnosticoSiniestro.class),
                        action,
                        startIndex,
                        General.licencia.getBlockSize(),
                        filteredColumns,
                        currentSortedColumns,
                        currentSortedVersusColumns,
                        valueObjectType,
                        sql,
                        new Object[]{((Auditable) beanVO).getId()},
                        new Type[]{new LongType()},
                        "C",
                        sf,
                        s);
                return res;
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "loadData", ex);
                return new ErrorResponse(ex.getMessage());
            } finally {
                s.close();
            }
        } else {
            al = new ArrayList<SumaAmparada>(0);
        }
        return new VOListResponse(al, false, al.size());
    }
}
