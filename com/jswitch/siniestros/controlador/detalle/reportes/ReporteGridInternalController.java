package com.jswitch.siniestros.controlador.detalle.reportes;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.reporte.controlador.ReporteController;
import com.jswitch.reporte.modelo.Reporte;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ValueObject;



/**
 *
 * @author orlandobcrra
 */
public class ReporteGridInternalController extends DefaultGridInternalController {

    public ReporteGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        if (beanVO != null && ((Auditable) beanVO).getId() != null) {
            Reporte reporte = (Reporte) persistentObject;

            String rutaReporte = General.empresa.getRutaReportes() + "/" + reporte.getFile() + ".jasper";
            Map parameters = new HashMap();
            parameters.put("reporteSQL", reporte.getBaseSQL());
            parameters.put("draco", ReporteController.getIcon("draco_Report"));
            parameters.put("usuario", General.usuario.getUserName());
            parameters.put("oficina", General.oficina.getNombre());
            parameters.put("responsable", General.oficina.getResponsable());



            parameters.put(JRParameter.REPORT_LOCALE, Locale.getDefault());
            parameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, java.util.ResourceBundle.getBundle("Spanish"));
            parameters.put("datosSistema",
                    new String[]{
                        General.edition + " ",
                        General.contacto});
            parameters.put("reporteTitulo", reporte.getTitulo());
            parameters.put("reporteFile", reporte.getFile());
//            parameters.put("reporteEstilo", estilo2);
//            parameters.put("reporteParametros", parametrosFiltro);
            parameters.put("usuario", General.usuario.getUserName());

            parameters.put("empresaNombre", General.empresa.getNombre());
            parameters.put("empresaRif", General.empresa.getRif2());
            parameters.put("empresaTelefono", General.empresa.getTelefonos());
            parameters.put("empresaLogo", ReporteController.getIcon(null));
            parameters.put("empresaObservacion", "");

            Collection c = new ArrayList(1);
            JasperPrint jasperPrint = null;
            if (reporte.getEnviarData()) {
                c.add(beanVO);
                try {
                    jasperPrint = JasperFillManager.fillReport(rutaReporte, parameters, new JRBeanCollectionDataSource(c));
                } catch (JRException ex) {
                    ex.printStackTrace();
                }
            } else if (!reporte.getEnviarData()) {
                Auditable entidad = (Auditable) beanVO;
                Session s = HibernateUtil.getSessionFactory().openSession();
                try {
                    c.add(s.createQuery("FROM " + beanVO.getClass().getName()
                            + " P WHERE P.id=:id").
                            setLong("id", entidad.getId()).
                            uniqueResult());
                    jasperPrint = JasperFillManager.fillReport(rutaReporte, parameters, new JRBeanCollectionDataSource(c));
                } catch (JRException ex) {
                    ex.printStackTrace();
                } finally {
                    s.close();
                }
            }
            if (jasperPrint != null) {
                JasperViewer.viewReport(jasperPrint, false);
            }
            
//            Collection c = new ArrayList(1);
//            c.add(beanVO);
//            try {
//                JasperPrint jasperPrint = JasperFillManager.fillReport(rutaReporte, parameters, new JRBeanCollectionDataSource(c));
//                JasperViewer.viewReport(jasperPrint, false);
//            } catch (JRException ex) {
//                ex.printStackTrace();
//            }
            
        }
    }
}