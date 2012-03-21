package com.jswitch.asegurados.controlador;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.asegurados.vista.AseguradoDetailFrame;
import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.certificados.modelo.maestra.Certificado;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.transform.AliasedTupleSRT;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Luis Adrian Gonzalez Benavides
 */
public class AseguradosGridInternalController extends DefaultGridInternalController {

    public AseguradosGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new AseguradoDetailFrameController(AseguradoDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, true);
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            for (Object o : persistentObjects) {
                ((Asegurado) o).setCertificado(null);
                s.update(o);
            }
            s.update(beanVO);
            t.commit();
            return new VOResponse(true);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "deleteRecords", ex);
            return new ErrorResponse(ex.getMessage());
        } finally {
            s.close();
        }

    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns,
            ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns,
            Class valueObjectType, Map otherGridParams) {
        if (beanVO != null) {
            Session s = null;
            try {
                String select = createSelect("C", AliasedTupleSRT.SEPARATOR);
                String sql = select + " FROM " + Asegurado.class.getName() + " C "
                        + "  WHERE C.certificado.id=?";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                ResultTransformer resultTransformer = new AliasedTupleSRT(
                        Asegurado.class);
                Response res = HibernateUtils.getBlockFromQuery(
                        resultTransformer,
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
        }
        return new VOListResponse();
    }

    /**
     * Crea el select basado en los campos necesarios por la columna
     * @param gridControl
     * @param tableName
     * @param separator 
     * @return 
     */
    public String createSelect(String tableName, String separator) {
        String select = "";
        int length = miGrid.getVOListTableModel().getColumnCount();
        for (int i = 0; i < length; i++) {
            String columnName = miGrid.getVOListTableModel().getColumnName(i);
            if (columnName.contains(".edad")) {
                columnName = columnName.replaceAll("\\.edad", ".fechaNacimiento");
            }
            if (select.isEmpty()) {
                select = "SELECT ";
            } else {
                select += ", ";
            }
            select += tableName + "." + columnName
                    + " AS "
                    + columnName.replaceAll("\\.", separator);


        }
        if (!select.contains(" " + tableName + ".id ")) {
            select += ", " + tableName + ".id AS id";
        }
        return select + " ";
    }
}
