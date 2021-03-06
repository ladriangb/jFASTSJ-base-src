package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.pagos.modelo.maestra.Remesa;
import com.jswitch.pagos.modelo.utilitario.BuscarPagos;
import com.jswitch.pagos.vista.RemesaDetailFrame;
import com.jswitch.pagos.vista.RemesaGridFrame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.transform.AliasedTupleSRT;
import org.hibernate.type.DateType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Adrian
 */
public class RemesaGridFrameController extends DefaultGridFrameController {

    /**
     * Criterios de busqueda
     */
    private BuscarPagos bp;

    public RemesaGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    RemesaGridFrameController(BuscarPagos bp) {
        super(RemesaGridFrame.class.getName(), RemesaDetailFrame.class.getName(), Remesa.class.getName(), "Remesas");
        this.bp = bp;
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        if (bp != null) {
            return loadBuscarPago(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, valueObjectType, otherGridParams);
        } else {
            return super.loadData(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, valueObjectType, otherGridParams);
        }
    }

    /**
     * Method invoked by the grid to load a block or rows using the
     * <code>BuscarPago</code> object send in the constructor.
     * @param action fetching versus: PREVIOUS_BLOCK_ACTION, NEXT_BLOCK_ACTION or LAST_BLOCK_ACTION
     * @param startPos start position of data fetching in result set
     * @param filteredColumns filtered columns
     * @param currentSortedColumns sorted columns
     * @param currentSortedVersusColumns ordering versus of sorted columns
     * @param valueObjectType v.o. type
     * @param otherGridParams other grid parameters
     * @return response from the server: an object of type VOListResponse if data loading was successfully completed, or an ErrorResponse onject if some error occours
     */
    private Response loadBuscarPago(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        ArrayList<Object> ob = new ArrayList<Object>(0);
        ArrayList<Type> ty = new ArrayList<Type>(0);
        String where = "";
        String and = "";
        if (bp.getNumeroRemesa() != null
                && !bp.getNumeroRemesa().trim().isEmpty()) {
            where += " C.numeroRemesa like ?";
            and = " AND ";
            ob.add("%" + bp.getNumeroRemesa() + "%");
            ty.add(new StringType());
        }
        if (bp.getDetalle() != null
                && !bp.getDetalle().trim().isEmpty()) {
            where += and + " C.detalle like ?";
            and = " AND ";
            ob.add("%" + bp.getDetalle() + "%");
            ty.add(new StringType());
        }
        if (bp.getTipoPago() != null) {
            where += and + " C.tipoPago = ?";
            and = " AND ";
            ob.add(bp.getTipoPago().toString());
            ty.add(new StringType());
        }
        if (bp.getEstatusPago() != null) {
            where += and + " C.estatusPago = ?";
            and = " AND ";
            ob.add(bp.getEstatusPago().toString());
            ty.add(new StringType());
        }
        if (bp.getFechaPagado() != null) {
            where += and + " C.fechaPagado = ?";
            and = " AND ";
            ob.add(bp.getFechaPagado());
            ty.add(new DateType());
        }
        try {
            String select = gridFrame.getGridControl().getVOListTableModel().
                    createSelect("C", AliasedTupleSRT.SEPARATOR);

            String sql = select + " FROM " + Remesa.class.getName() + " C ";
            sql = where.trim().isEmpty() ? sql : (sql + "WHERE " + where);

            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            Response res = HibernateUtils.getBlockFromQuery(
                    new AliasedTupleSRT(Remesa.class),
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

    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {
        if (attributeName.equalsIgnoreCase("estatusPago")) {
            if (value != null) {
                switch ((EstatusPago) value) {
                    case ANULADO:
                        return Color.RED;
                    case PAGADO:
                        return Color.GREEN;
                    case PENDIENTE:
                        return Color.YELLOW;
                    case SELECCIONADO:
                        return Color.CYAN;
                }
            }
        }
        return super.getBackgroundColor(row, attributeName, value);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        if (detailFramePath != null) {
            new RemesaDetailFrameController(detailFramePath, gridFrame.getGridControl(), (BeanVO) persistentObject, true);
        }
    }
}
