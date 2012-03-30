package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.pagos.modelo.utilitario.BuscarFactura;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.transform.AliasedTupleSRT;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 * Frid de facturas para la exportacion 
 * @author Adrian
 */
public class FacturaGridFrameController extends DefaultGridFrameController {

    /**
     * patron de busqueda
     */
    private BuscarFactura bf;

    public FacturaGridFrameController(BuscarFactura bf, String gridFramePath) {
        super(gridFramePath, null, Factura.class.getName(), "Facturas");
        this.bf = bf;
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {
            String where = "";
            String and = "";
            ArrayList<Object> ob = new ArrayList<Object>(0);
            ArrayList<Type> ty = new ArrayList<Type>(0);
            if (bf != null) {
                if (bf.getAyo() != null) {
                    where = " year(fechaPagado)=? ";
                    ob.add(bf.getAyo());
                    ty.add(new IntegerType());
                    and = " AND ";
                }
                if (bf.getMes() != null) {
                    where += and + " month(fechaPagado)=? ";
                    ob.add(bf.getMes());
                    ty.add(new IntegerType());
                    and = " AND ";
                }
                if (bf.getNumeroControl() != null
                        && !bf.getNumeroControl().trim().isEmpty()) {
                    where += and + "numeroControl like ? ";
                    ob.add("%" + bf.getNumeroControl() + "%");
                    ty.add(new StringType());
                    and = " AND ";
                }
                if (bf.getNumeroFactura() != null
                        && !bf.getNumeroFactura().trim().isEmpty()) {
                    where += and + " numeroFactura like ? ";
                    ob.add("%" + bf.getNumeroFactura() + "%");
                    ty.add(new StringType());
                    and = " AND ";
                }
            }
            String tableName = "C";
            String select = gridFrame.getGridControl().getVOListTableModel().
                    createSelect("C", AliasedTupleSRT.SEPARATOR);
            String sql = select + " FROM " + claseModeloFullPath + " " + tableName + " WHERE "
                    + "C.fechaPagado is not null";
            sql += (!where.isEmpty() ? (" AND " + where) : "");
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            Response res = HibernateUtils.getBlockFromQuery(
                    new AliasedTupleSRT(Class.forName(claseModeloFullPath)),
                    action,
                    startIndex,
                    General.licencia.getBlockSize(),
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    sql,
                    ob.toArray(),
                    ty.toArray(new Type[0]),
                    tableName,
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

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new FacturaDetailFrameController((Factura) persistentObject);
    }
}
