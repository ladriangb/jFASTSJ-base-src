package com.jswitch.asegurados.controlador;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.asegurados.modelo.utilitario.RevisionAsegurado;
import com.jswitch.asegurados.vista.RevisionGridFrame;
import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.transform.AliasedTupleSRT;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Adrian
 */
public class RevisionesGridFrameController extends DefaultGridFrameController {

    public RevisionesGridFrameController() {
        super(RevisionGridFrame.class.getName(), null, null, null);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {
            String tableName = "";

            String sql = "SELECT  "
                    + " C.persona.rif.rif as persona_rif_rif, "
                    + " C.persona.nombreLargo as persona_nombreLargo,"
                    + " R.personaBuscadora.rif.rif as personaBuscadora_rif_rif,"
                    + " R.personaBuscadora.nombreLargo as personaBuscadora_nombreLargo,"
                    + " R.detalle as detalle,"
                    + " R.auditoria.fechaInsert as auditoria_fechaInsert, "
                    + " R.auditoria.usuarioInsert as  auditoria_usuarioInsert "
                    + " FROM "
                    + Asegurado.class.getName()
                    + " C JOIN C.revisiones R ";
            Map mp = new HashMap();
            mp.put("persona.rif.rif", "C.persona.rif.rif");
            mp.put("persona.nombreLargo", "C.persona.nombreLargo");
            mp.put("personaBuscadora.rif.rif", "R.personaBuscadora.rif.rif");
            mp.put("detalle", "R.detalle");
            mp.put("auditoria.fechaInsert", "R.auditoria.fechaInsert");
            mp.put("auditoria.usuarioInsert", "R.auditoria.usuarioInsert");


            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            Response res = HibernateUtils.getBlockFromQuery(
                    new AliasedTupleSRT(RevisionAsegurado.class),
                    mp,
                    action,
                    startIndex,
                    General.licencia.getBlockSize(),
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    sql,
                    new Object[0],
                    new Type[0],
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
}
