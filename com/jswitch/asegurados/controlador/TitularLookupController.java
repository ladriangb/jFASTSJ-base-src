

package com.jswitch.asegurados.controlador;


import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.asegurados.modelo.maestra.Titular;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.type.BooleanType;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.util.java.Consts;
import org.openswing.swing.util.server.HibernateUtils;


/**
 *
 * @author Orlando Becerra
 */
public class TitularLookupController extends DefaultLookupController {

    public TitularLookupController() {
	this.setLookupDataLocator(new TitularLookupDataLocator(Titular.class.getName()));
	this.setLookupGridController(new DefaultLookupGridController());
	setLookupValueObjectClassName(Titular.class.getName());
        
	setAllColumnVisible(false);
	setVisibleColumn("id", true);
	setVisibleColumn("persona.rif.rif", true);
	setVisibleColumn("persona.nombreLargo", true);
	setPreferredWidthColumn("id", 40);
	setPreferredWidthColumn("persona.rif.rif", 100);
	setPreferredWidthColumn("persona.nombreLargo", 200);
	setSortableColumn("id", true);
	setSortedColumn("id", Consts.DESC_SORTED, 0);
	setSortableColumn("persona.rif.rif", true);
	setSortableColumn("persona.nombreLargo", true);
	setFilterableColumn("persona.rif.rif", true);
	setFilterableColumn("persona.nombreLargo", true);
	setFramePreferedSize(new java.awt.Dimension(380, 340));
	setCodeSelectionWindow(GRID_FRAME);
	setOnInvalidCode(ON_INVALID_CODE_RESTORE_FOCUS);
    }

    class TitularLookupDataLocator extends DefaultLookupDataLocator {


	public TitularLookupDataLocator(String classFullName) {
	    super(classFullName);
	}

	@Override
	public Response loadData(int action,
		int startIndex,
		Map filteredColumns,
		ArrayList currentSortedColumns,
		ArrayList currentSortedVersusColumns,
		Class valueObjectType) {
	    Session s = null;
	    try {
		String sql = "SELECT C FROM " + Titular.class.getName() +
                        " C WHERE C.auditoria.activo=?";
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		s = sf.openSession();
		//Map de=new HashMap(0);
		//de.put("rif.cedulaCompleta", "C.rif.cedulaCompleta");
		Response res = HibernateUtils.getAllFromQuery(
			//     de,
			filteredColumns,
			currentSortedColumns,
			currentSortedVersusColumns,
			valueObjectType,
			sql,
			new Object[]{Boolean.TRUE},
			new Type[]{new BooleanType()},
			"C",
			sf,
			s);
		//System.out.println("*************");
		//System.out.println(((VOListResponse)res).getRows().size());
		return res;
	    } catch (Exception ex) {
		LoggerUtil.error(this.getClass(), "loadData", ex);
		return new ErrorResponse(ex.getMessage());
	    } finally {
		s.close();
	    }
	}

	@Override
	public Response validateCode(String code) {
	    Session s = null;
	    Query q;
	    try {
		s = HibernateUtil.getSessionFactory().openSession();
		
		    String sql = "SELECT DISTINCT C FROM " + Titular.class.getName()
			    + " WHERE  C.auditoria.activo=? AND upper(C.persona.rif.rif) like ?";
		    q = s.createQuery(sql);
		    q.setBoolean(0, Boolean.TRUE).
			    setString(1, "%" + code.toUpperCase().trim() + "%");
		Transaction t = s.beginTransaction();
		List list = q.list();
		t.commit();
		return new VOListResponse(list, false, list.size());
	    } catch (Exception ex) {
		LoggerUtil.error(this.getClass(), "validateCode", ex);
		return new ErrorResponse(ex.getMessage());
	    } finally {
		s.close();
	    }
	}

    }
}
