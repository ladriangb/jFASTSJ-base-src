package com.jswitch.asegurados.controlador;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.asegurados.modelo.maestra.Asegurado;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import com.jswitch.certificados.modelo.maestra.Certificado;
import com.jswitch.asegurados.modelo.maestra.Titular;
import java.util.Set;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;

/**
 *
 * @author Adrian
 */
public class TitularDetailFrameController extends DefaultDetailFrameController {

    public TitularDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Titular titu = (Titular) s.get(Titular.class, ((Titular) beanVO).getId());
        Hibernate.initialize(titu.getNotasTecnicas());
        Hibernate.initialize(titu.getObservaciones());
        Hibernate.initialize(titu.getDocumentos());
        s.close();
        beanVO = titu;
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {


        Response res = super.insertRecord(newPersistentObject);
        if (res instanceof VOResponse) {
            Session s = null;
            List data = null;
            Asegurado aseg = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = s.beginTransaction();
                Query query = s.createQuery("From " + Certificado.class.getName());
                data = query.list();

                Query query2 = s.createQuery("From " + Asegurado.class.getName()
                        + " Where persona.rif.rif=?").setString(0,
                        ((Titular) newPersistentObject).getPersona().getRif().getRif());
                aseg = (Asegurado) query2.uniqueResult();

                if (aseg != null) {
                    if (JOptionPane.showConfirmDialog(MDIFrame.getInstance(), "Este nuevo Titular es Asegurado en otros grupos Familiares.\n"
                            + "Se eliminara este titular de los de demas grupos familaires.\n"
                            + "Desea continuar?.", "Mensaje de Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
                        for (int i = 0; aseg != null && i < data.size(); i++) {
                            Certificado certificado = (Certificado) data.get(i);
                            Set<Asegurado> asegur = certificado.getAsegurados();
                            if (asegur != null) {
                                if (asegur.remove(aseg)) {
                                    s.update(certificado);
                                }
                            }

                        }
                    } else {
                        s.delete(newPersistentObject);
                        res = new ErrorResponse("Titular no fue guardado.");
                    }
                }
                tx.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                s.close();
            }
        }
        return res;
    }
}
