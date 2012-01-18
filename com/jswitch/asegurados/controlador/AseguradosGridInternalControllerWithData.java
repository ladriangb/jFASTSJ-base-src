package com.jswitch.asegurados.controlador;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.asegurados.vista.BuscarAseguradoDialog;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.certificados.modelo.maestra.Certificado;
import com.jswitch.fas.modelo.Dominios.TipoBusqueda;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Luis Adrian Gonzalez Benavides
 */
public class AseguradosGridInternalControllerWithData extends DefaultGridInternalController {

    private String sql, name, rif;
    private boolean sw;
    private TipoBusqueda tb;
    private BuscarAseguradoDialog dialog;

    public AseguradosGridInternalControllerWithData(BuscarAseguradoDialog dialog) {
        this.dialog = dialog;
    }

    public void setData(String name, String rif, TipoBusqueda tb, boolean sw) {
        this.sql = "FROM " + Asegurado.class.getName() + " A WHERE ";
        this.name = name;
        this.rif = rif;
        this.sw = sw;
        this.tb = tb;
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        dialog.openNext((Asegurado) persistentObject);
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        int ced = -1;
        try {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            String where = "";
            String alias = " A.";
            String flag = "A";
            if (tb.equals(TipoBusqueda.GRUPO_FAMILIAR)) {
                alias += "titular.";
                sql = "SELECT P FROM " + Certificado.class.getName()
                        + " A JOIN A.asegurados as P WHERE ";
                flag = "P";
            }

            if (name != null && !name.isEmpty()) {

                where += alias + "persona.nombreLargo like '%" + name + "%'";
            }
            if (rif != null && !rif.isEmpty()) {
                if (!where.isEmpty()) {
                    where += " AND";
                }
                try {
                    ced = Integer.parseInt(rif);
                    if (sw) {
                        where += alias + "persona.rif.rif like '%" + rif + "%'";
                    } else {
                        where += alias + "persona.rif.numeroCedula = " + ced;
                    }
                } catch (Exception e) {
                    where += alias + "persona.rif.cedulaCompleta = " + rif;
                }
            }
            if (tb.equals(TipoBusqueda.TITULARES)) {
                where += " AND" + alias + "persona.id =" + alias + "certificado.titular.persona.id";
            }

            Response res = HibernateUtils.getAllFromQuery(
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    sql + where,
                    new Object[0],
                    new Type[0],
                    flag,
                    sf,
                    s);

            return res;
        } catch (Exception ex) {
            return new ErrorResponse("data.faild");
        } finally {
            s.close();
        }
    }
}
