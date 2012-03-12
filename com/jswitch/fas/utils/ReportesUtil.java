package com.jswitch.fas.utils;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.asegurados.modelo.maestra.Titular;
import com.jswitch.base.controlador.General;
import com.jswitch.base.modelo.Dominios.Sexo;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.Documento;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.persona.modelo.maestra.PersonaNatural;
import com.jswitch.reporte.controlador.ReporteController;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.classic.Session;

/**
 *
 * @author Adrian
 */
public class ReportesUtil {

    private static String server = General.empresa.getRutaDocDigitales();

    public static Image getImageFOTO(Auditable object) {
        Image i = null;
        Sexo sexo = Sexo.MASCULINO;
        i = getImage(object, "FOTO");
        if (i == null) {
            if (object instanceof Titular) {
                sexo = ((Titular) object).getPersona().getSexo();
            }
            if (object instanceof Asegurado) {
                sexo = ((Asegurado) object).getPersona().getSexo();
            }
            if (object instanceof PersonaNatural) {
                sexo = ((PersonaNatural) object).getSexo();
            }
            if (sexo.equals(Sexo.FEMENINO)) {
                i = ReporteController.getIcon("female");
            } else {
                i = ReporteController.getIcon("male");;
            }
        }

        return i;
    }

    public static Image getImage(Auditable object, String tipoDocumento) {
        String path = server + "\\" + object.getClass().getSimpleName() + "\\" + object.getId() + "\\";
        Image i = null;
        Session s = null;
        List<Documento> doc = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            doc = s.createQuery("SELECT D FROM "
                    + object.getClass().getName() + " C "
                    + " JOIN C.documentos ").list();
            s.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            doc = new ArrayList<Documento>(0);
        }
        for (Documento documento : doc) {
            if (documento.getTipoDocumento().getNombre().
                    compareToIgnoreCase(tipoDocumento) == 0) {
                try {
                    i = Toolkit.getDefaultToolkit().createImage(JRLoader.loadBytes(
                            new File(path + documento.getFileName())));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (i != null) {
            MediaTracker traker = new MediaTracker(new Panel());
            traker.addImage(i, 0);
            try {
                traker.waitForID(0);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return i;
    }
}
