package com.jswitch.configuracion.controlador;

import com.jswitch.base.controlador.General;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Map;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.vista.util.ProgressDialog;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import com.jswitch.configuracion.modelo.maestra.Plan;
import com.jswitch.configuracion.modelo.transaccional.ConfiguracionSiniestro;
import com.jswitch.configuracion.modelo.transaccional.SumaAmparada;
import com.jswitch.configuracion.modelo.transaccional.SumaAsegurada;
import com.jswitch.configuracion.vista.PlanesGridFrame;
import com.jswitch.siniestros.modelo.maestra.detalle.APS;
import com.jswitch.siniestros.modelo.maestra.detalle.AyudaSocial;
import com.jswitch.siniestros.modelo.maestra.detalle.CartaAval;
import com.jswitch.siniestros.modelo.maestra.detalle.Emergencia;
import com.jswitch.siniestros.modelo.maestra.detalle.Funerario;
import com.jswitch.siniestros.modelo.maestra.detalle.Reembolso;
import com.jswitch.siniestros.modelo.maestra.detalle.Vida;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Orlando Becerra
 * @author Adrian Gonzalez
 */
public class PlanesGridFrameController extends DefaultGridFrameController implements GridDataLocator, ActionListener {
    
    public PlanesGridFrameController() {
    }
    
    public PlanesGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }
    
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns,
            ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {
            String sql = "FROM " + Plan.class.getName() + " C ";
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
                    new Object[0],
                    new Type[0],
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
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Response res = super.insertRecords(rowNumbers, newValueObjects);
        for (Object object : newValueObjects) {
            Plan plan = (Plan) object;
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
                String[] clases = {APS.class.getSimpleName(),
                    AyudaSocial.class.getSimpleName(),
                    CartaAval.class.getSimpleName(),
                    Emergencia.class.getSimpleName(),
                    Funerario.class.getSimpleName(),
                    Reembolso.class.getSimpleName(),
                    Vida.class.getSimpleName()};
                for (String o : clases) {
                    ConfiguracionSiniestro cs = new ConfiguracionSiniestro(o, 0d, Double.POSITIVE_INFINITY, plan, ab);
                    s.save(cs);
                    plan.getConfiguracionSiniestros().add(cs);
                }
                s.update(plan);
                t.commit();
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "insertRecord", ex);
            } finally {
                s.close();
            }
        }
        return res;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        final Plan plan = ((PlanesGridFrame) gridFrame).getPlan();
        if (plan != null) {
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    Session s = null;
                    try {
                        s = HibernateUtil.getSessionFactory().openSession();
                        
                        Long lon = (Long) s.createQuery("SELECT COUNT(D) FROM " + SumaAsegurada.class.getName() + " as D WHERE D.plan.id=? ").setLong(0, plan.getId()).uniqueResult();
                        if (lon > 0) {
                            JOptionPane.showMessageDialog(gridFrame, "Ya Existen Diagnosticos Asociados \nDebe Ser un plan Nuevo", "Agregar Diagnostocos", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        Transaction t = s.beginTransaction();
                        AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
                        SumaAmparada sa = new SumaAmparada();
                        sa.setAuditoria(ab);
                        sa.setMonto(0d);
                        sa.setNombre("AUTO_CREADA");
                        sa.setPlan(plan);
                        s.save(sa);
                        final List<Diagnostico> diag = s.createQuery("FROM " + Diagnostico.class.getName() + " as D ").
                                list();
                        ProgressDialog pr = new ProgressDialog("Agregando Diagnosticos", "Diagnosticos  numero: ", diag.size());
                        int v = 0;
                        for (Diagnostico diagnostico : diag) {
                            v++;
                            pr.setValue(v);
                            SumaAsegurada ob = new SumaAsegurada();
                            ob.setPlan(plan);
                            ob.setSumaAmparada(sa);
                            ob.setAuditoria(ab);
                            ob.setDiagnostico(diagnostico);
                            s.save(ob);
                        }
                        pr.setEventoActual("Guardando Registros Creados");
                        t.commit();
                        pr.dispose();
                    } catch (Exception ex) {
                        LoggerUtil.error(this.getClass(), "insertRecord", ex);
                    } finally {
                        s.close();
                    }
                }
            }).start();
        }
    }
}
