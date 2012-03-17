
package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.fas.modelo.Dominios.EstadoSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.utilitario.BuscarSiniestro;
import com.jswitch.siniestros.vista.SiniestroDetailFrame;
import com.jswitch.siniestros.vista.SiniestroGridFrame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Luis Adrian Gonzalez Benavides
 */
public class SiniestroGridFrameController extends DefaultGridFrameController {

    /**
     * Resonse lista de datos 
     */
    private Response res;
    /**
     * criterios de busqueda 
     */
    private BuscarSiniestro bs;

    /**
     * genera una vista grid de Siniestros
     */
    public SiniestroGridFrameController() {
        this.detailFramePath = SiniestroDetailFrame.class.getName();
        this.claseModeloFullPath = Siniestro.class.getName();
        gridFrame = new SiniestroGridFrame();
        gridFrame.inicializar(this, this, claseModeloFullPath, claseModeloFullPath, true);
    }

    /**
     * 
     */
    public SiniestroGridFrameController(BuscarSiniestro bs) {
        this();
        this.bs = bs;
    }

    /**
     * Generea una lista de siniestros a raiz de un Response q la contenga
     * @param list 
     */
    public SiniestroGridFrameController(Response list) {
        this();
        res = list;
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new SiniestroDetailFrameController(SiniestroDetailFrame.class.getName(), gridFrame.getGridControl(), (BeanVO) persistentObject, false);
    }

    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {
        if (attributeName.equalsIgnoreCase("estadoSiniestro")) {
            if (value != null) {
                switch ((EstadoSiniestro) value) {
                    case ABIERTO:
                        return Color.GREEN;
                    case CERRADO:
                        return Color.RED;
                }
            }
        }
        return super.getBackgroundColor(row, attributeName, value);
    }

    /**
     * Generea el grid dependiendo de los criterios iniciales pasados por 
     * parametros a la clase en el buscar Siniestro
     * @param action
     * @param startIndex
     * @param filteredColumns
     * @param currentSortedColumns
     * @param currentSortedVersusColumns
     * @param valueObjectType
     * @param otherGridParams
     * @return 
     */
    public Response loadBuscarSiniestro(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;

        try {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            boolean Snum = false, Sayo = false, Smes = false,
                    Anombre = false, Arif = false, Tnombre = false,
                    Trif = false, diag = false;
            List<Long> list = null;
            String where = "";
            String and = "";
            //<editor-fold defaultstate="collapsed" desc="Siniestro">
            if (bs.getSiniestro().getNumero() != null
                    && !bs.getSiniestro().getNumero().trim().isEmpty()) {
                where += " C.numero like ?";
                Snum = true;
                and = " AND ";
            }
            if (bs.getSiniestro().getAyo() != null) {
                where += and + " C.ayo=?";
                Sayo = true;
                and = " AND ";
            }
            if (bs.getSiniestro().getMes() != null) {
                where += and + " C.mes=?";
                Smes = true;
                and = " AND ";
            }
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Asegurado">
            if (bs.getPersonaAseg().getRif().getRif() != null
                    && !bs.getPersonaAseg().getRif().getRif().trim().isEmpty()) {
                where += and + " C.asegurado.persona.rif.rif like ?";
                Arif = true;
                and = " AND ";
            }
            if (bs.getPersonaAseg().getNombreLargo() != null
                    && !bs.getPersonaAseg().getNombreLargo().trim().isEmpty()) {
                where += and + " C.asegurado.persona.nombreLargo like ?";
                Anombre = true;
                and = " AND ";
            }
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Titular">
            if (bs.getPersonaTit().getRif().getRif() != null
                    && !bs.getPersonaTit().getRif().getRif().trim().isEmpty()) {
                where += and + " C.certificado.titular.persona.rif.rif like ?";
                Trif = true;
                and = " AND ";
            }
            if (bs.getPersonaTit().getNombreLargo() != null
                    && !bs.getPersonaTit().getNombreLargo().trim().isEmpty()) {
                where += and + " C.certificado.titular.persona.nombreLargo like ?";
                Tnombre = true;
                and = " AND ";
            }
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Diagnostico">
            if (bs.getDiagnostico().getId() != null) {
                where += and + " DI.id=?";
                diag = true;
            }
            //</editor-fold>
            String sql2 = "";
            ArrayList<Object> ob = new ArrayList<Object>(0);
            ArrayList<Type> ty = new ArrayList<Type>(0);

            try {
                
                sql2 = "SELECT DISTINCT C FROM " + Siniestro.class.getName() + " C "
                        + " JOIN C.detalleSiniestro as DE JOIN DE.diagnosticoSiniestros as DI ";
                sql2 = where.trim().isEmpty() ? sql2 : (sql2 + "WHERE " + where);
                //<editor-fold defaultstate="collapsed" desc="siniestro">
                if (Snum) {
                    ob.add("%" + bs.getSiniestro().getNumero() + "%");
                    ty.add(new StringType());
                }
                if (Sayo) {
                    ty.add(new IntegerType());
                    ob.add(bs.getSiniestro().getAyo());
                }
                if (Smes) {
                    ty.add(new IntegerType());
                    ob.add(bs.getSiniestro().getMes());
                }
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Asegurado">
                if (Anombre) {
                    ty.add(new StringType());
                    ob.add("%" + bs.getPersonaAseg().getNombreLargo() + "%");
                }
                if (Arif) {
                    ty.add(new StringType());
                    ob.add("%" + bs.getPersonaAseg().getRif().getRif() + "%");
                }
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="titular">
                if (Tnombre) {
                    ty.add(new StringType());
                    ob.add("%" + bs.getPersonaTit().getNombreLargo() + "%");
                }
                if (Trif) {
                    ty.add(new StringType());
                    ob.add("%" + bs.getPersonaTit().getRif().getRif() + "%");
                }
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Diagnostico">
                if (diag) {
                    ty.add(new LongType());
                    ob.add(bs.getDiagnostico().getId());
                }
                //</editor-fold>

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            Response res = HibernateUtils.getBlockFromQuery(
                    action,
                    startIndex,
                    General.licencia.getBlockSize(),
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    sql2,
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
    public Response loadData(int action,
            int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        if (res != null) {
            return res;
        }
        if (bs != null) {
            return loadBuscarSiniestro(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, valueObjectType, otherGridParams);
        }
        return super.loadData(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, valueObjectType, otherGridParams);
    }
}
