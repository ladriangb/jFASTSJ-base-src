package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.modelo.utilitario.BuscarSiniestro;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
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
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 * Controlador de la vista de los detalles de siniestro
 * @author Luis Adrian Gonzalez
 */
public class DetalleSiniestroGridFrameController extends DefaultGridFrameController {

    /**
     * Lista de datos 
     */
    private List data;
    /**
     * 
     */
    private BuscarSiniestro bs;

    public DetalleSiniestroGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    public DetalleSiniestroGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo, BuscarSiniestro bs) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
        this.bs = bs;
    }

    public DetalleSiniestroGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo, List data) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
        this.data = data;
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        if (data != null) {
            return new VOListResponse(data, false, data.size());
        }
        if (bs != null) {
            return loadBuscarSiniestro(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, valueObjectType, otherGridParams);
        }
        return super.loadData(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, valueObjectType, otherGridParams);
    }

    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {
        if (attributeName.equalsIgnoreCase("etapaSiniestro.estatusSiniestro.nombre")) {
            if (value != null) {
                if (value.toString().equalsIgnoreCase("PENDIENTE")) {
                    return Color.YELLOW;
                }
                if (value.toString().equalsIgnoreCase("ANULADO")) {
                    return Color.RED;
                }
                if (value.toString().equalsIgnoreCase("PAGADO")) {
                    return Color.GREEN;
                }
            }
        }
        return super.getBackgroundColor(row, attributeName, value);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        if (persistentObject != null && persistentObject.getClass() != null) {
            new DetalleSiniestroDetailFrameController(DetalleSiniestroDetailFrame.class.getName(), gridFrame.getGridControl(), (BeanVO) persistentObject, true, persistentObject.getClass());
        }
    }

    private Response loadBuscarSiniestro(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        boolean Snum = false, Sayo = false, Smes = false, Pnombre = false,
                Prif = false, Anombre = false, Arif = false, Tnombre = false,
                Trif = false, eta = false;
        String where = "";
        String and = "";
        //<editor-fold defaultstate="collapsed" desc="Siniestro">
        if (bs.getSiniestro().getNumero() != null
                && !bs.getSiniestro().getNumero().trim().isEmpty()) {
            where += " C.siniestro.numero like ?";
            Snum = true;
            and = " AND ";
        }
        if (bs.getSiniestro().getAyo() != null) {
            where += and + " C.siniestro.ayo=?";
            Sayo = true;
            and = " AND ";
        }
        if (bs.getSiniestro().getMes() != null) {
            where += and + " C.siniestro.mes=?";
            Smes = true;
            and = " AND ";
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="PersonaPago">
        if (bs.getPersonaPago().getRif().getRif() != null
                && !bs.getPersonaPago().getRif().getRif().trim().isEmpty()) {
            where += and + " C.personaPago.rif.rif like ?";
            Prif = true;
            and = " AND ";
        }
        if (bs.getPersonaPago().getNombreLargo() != null
                && !bs.getPersonaPago().getNombreLargo().trim().isEmpty()) {
            where += and + " C.personaPago.nombreLargo like ?";
            Pnombre = true;
            and = " AND ";
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Asegurado">
        if (bs.getPersonaAseg().getRif().getRif() != null
                && !bs.getPersonaAseg().getRif().getRif().trim().isEmpty()) {
            where += and + " C.siniestro.asegurado.persona.rif.rif like ?";
            Arif = true;
            and = " AND ";
        }
        if (bs.getPersonaAseg().getNombreLargo() != null
                && !bs.getPersonaAseg().getNombreLargo().trim().isEmpty()) {
            where += and + " C.siniestro.asegurado.persona.nombreLargo like ?";
            Anombre = true;
            and = " AND ";
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Titular">
        if (bs.getPersonaTit().getRif().getRif() != null
                && !bs.getPersonaTit().getRif().getRif().trim().isEmpty()) {
            where += and + " C.siniestro.certificado.titular.persona.rif.rif like ?";
            Trif = true;
            and = " AND ";
        }
        if (bs.getPersonaTit().getNombreLargo() != null
                && !bs.getPersonaTit().getNombreLargo().trim().isEmpty()) {
            where += and + " C.siniestro.certificado.titular.persona.nombreLargo like ?";
            Tnombre = true;
            and = " AND ";
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Etapa Siniestro">
        if (bs.getEtapaSiniestro().getId() != null) {
            where += and + " C.etapaSiniestro.id=?";
            eta = true;
        }
        //</editor-fold>
        try {
            String sql = "FROM " + bs.getTipoDetalleSiniestro().getClase() + " C ";
            sql = where.trim().isEmpty() ? sql : (sql + "WHERE " + where);
            ArrayList<Object> ob = new ArrayList<Object>(0);
            ArrayList<Type> ty = new ArrayList<Type>(0);
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
            //<editor-fold defaultstate="collapsed" desc="Persona Pago">
            if (Pnombre) {
                ob.add("%" + bs.getPersonaPago().getNombreLargo() + "%");
                ty.add(new StringType());
            }
            if (Prif) {
                ob.add("%" + bs.getPersonaPago().getRif().getRif() + "%");
                ty.add(new StringType());
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
            //<editor-fold defaultstate="collapsed" desc="EtapaSiniestro">
            if (eta) {
                ty.add(new LongType());
                ob.add(bs.getEtapaSiniestro().getId());
            }
            //</editor-fold>
            
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            Response res = HibernateUtils.getBlockFromQuery(
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
}
