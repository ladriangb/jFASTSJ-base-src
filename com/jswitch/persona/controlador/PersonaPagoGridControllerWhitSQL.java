/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.persona.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridControllerWhitSQL;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.persona.vista.PersonaDetailFrame;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.transform.AliasedTupleSRT;
import org.hibernate.type.Type;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author PAPA
 */
public class PersonaPagoGridControllerWhitSQL extends DefaultGridControllerWhitSQL {

    public PersonaPagoGridControllerWhitSQL(String gridFramePath,
            String detailFramePath, String claseModeloFullPath,
            String titulo, String sql, Object[] values, Type[] valueType) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo,
                sql, values, valueType);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        if (!detailFramePath.equalsIgnoreCase(
                PersonaDetailFrame.class.getName())) {
            try {

                Class clase = Class.forName(detailFramePath);
                Object object = clase.newInstance();
                Method method = clase.getMethod("init", new Class[]{GridControl.class,
                            BeanVO.class, Boolean.class});
                OrdenDePago or = new OrdenDePago();
                Session s = null;

                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    persistentObject = (Persona) s.get(Persona.class, ((Persona) persistentObject).getId());
                } catch (Exception ex) {
                } finally {
                    s.close();
                }

                or.setPersonaPago((Persona) persistentObject);

                method.invoke(object, new Object[]{null, or, true});


            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "new", ex);
            }
        } else {
            new PersonasDetailController(null, (BeanVO) persistentObject, null);
        }
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            
            String select = gridFrame.getGridControl().getVOListTableModel().
                    createSelect("P.personaPago", AliasedTupleSRT.SEPARATOR);
            select = select.replaceAll("SELECT", "SELECT DISTINCT ");
            Response res = HibernateUtils.getBlockFromQuery(
                    new AliasedTupleSRT(Persona.class),
                    action,
                    startIndex,
                    General.licencia.getBlockSize(),
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    select + sql,
                    values,
                    valueTypes,
                    "P.personaPago",
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
