package com.jswitch.fas.modelo;

import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.APS;
import com.jswitch.siniestros.modelo.maestra.detalle.AyudaSocial;
import com.jswitch.siniestros.modelo.maestra.detalle.CartaAval;
import com.jswitch.siniestros.modelo.maestra.detalle.Emergencia;
import com.jswitch.siniestros.modelo.maestra.detalle.Funerario;
import com.jswitch.siniestros.modelo.maestra.detalle.Reembolso;
import com.jswitch.siniestros.modelo.maestra.detalle.Vida;
import java.util.Hashtable;
import org.openswing.swing.domains.java.Domain;

/**
 *
 * @author Orlando Becerra
 */
public class Dominios {

    public static Hashtable getDominios() {
        Hashtable domains = new Hashtable();
        domains.put(
                Dominios.CategoriaReporte().getDomainId(),
                Dominios.CategoriaReporte());
        domains.put(
                Dominios.TipoBusqueda().getDomainId(),
                Dominios.TipoBusqueda());
        domains.put(
                Dominios.CategoriaSiniestro().getDomainId(),
                Dominios.CategoriaSiniestro());
        domains.put(
                Dominios.EstatusPago().getDomainId(),
                Dominios.EstatusPago());
        domains.put(
                Dominios.TipoTramiteSiniestro().getDomainId(),
                Dominios.TipoTramiteSiniestro());

        domains.put(
                Dominios.TratamientoEfectuado().getDomainId(),
                Dominios.TratamientoEfectuado());
        domains.put(
                Dominios.TipoEnfermedad().getDomainId(),
                Dominios.TipoEnfermedad());
        domains.put(
                Dominios.TipoPago().getDomainId(),
                Dominios.TipoPago());
        domains.put(
                Dominios.DuracionCheque().getDomainId(),
                Dominios.DuracionCheque());
        domains.put(
                Dominios.TipoDetalleSiniestro().getDomainId(),
                Dominios.TipoDetalleSiniestro());
        return domains;
    }

    public static enum TipoBusqueda {

        TITULARES, ASEGURADOS, GRUPO_FAMILIAR
    }

    public static Domain TipoBusqueda() {
        Domain dominio = new Domain("TipoBusqueda");
        TipoBusqueda o[] = TipoBusqueda.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum CategoriaReporte {

        PERSONAS, POLIZAS, RECIBOS, ASEGURADOS, SINIESTROS, PAGOS, AUDITORIAS
    }

    public static Domain CategoriaReporte() {
        Domain dominio = new Domain("CategoriaReporte");
        CategoriaReporte o[] = CategoriaReporte.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum CategoriaSiniestro {

        EMERGENCIA, CARTA_AVAL, REEMBOLSO, APS
    }

    public static Domain CategoriaSiniestro() {
        Domain dominio = new Domain("CategoriaSiniestro");
        CategoriaSiniestro o[] = CategoriaSiniestro.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum EstatusPago {

        PENDIENTE, SELECCIONADO, PAGADO, ANULADO
    }

    public static Domain EstatusPago() {
        Domain dominio = new Domain("EstatusPago");
        EstatusPago o[] = EstatusPago.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum TipoTramiteSiniestro {

        MEDICO, QUIRURGICO
    }

    public static Domain TipoTramiteSiniestro() {
        Domain dominio = new Domain("TipoTramiteSiniestro");
        TipoTramiteSiniestro o[] = TipoTramiteSiniestro.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum TratamientoEfectuado {

        DESCONOCIDO, MEDICO, QUIRURGICO
    }

    public static Domain TratamientoEfectuado() {
        Domain dominio = new Domain("TratamientoEfectuado");
        TratamientoEfectuado o[] = TratamientoEfectuado.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum TipoPago {

        TRANSFERENCIA_SWIFT,
        ABONO_EN_CUENTA_BANCO_DE_VENEZUELA,
        CHEQUE_DE_GERENCIA
    }

    public static Domain TipoPago() {
        Domain dominio = new Domain("TipoPago");
        TipoPago o[] = TipoPago.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum DuracionCheque {

        _30, _45, _60, _90, _120, _180
    }

    public static Domain DuracionCheque() {
        Domain dominio = new Domain("DuracionCheque");
        DuracionCheque o[] = DuracionCheque.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum TipoEnfermedad {

        DESCONOCIDO, AGUDA, PREEXISTENTE, AGUDA_PREEXISTENTE
    }

    public static Domain TipoEnfermedad() {
        Domain dominio = new Domain("TipoEnfermedad");
        TipoEnfermedad o[] = TipoEnfermedad.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum TipoDetalleSiniestro {

        Todos(DetalleSiniestro.class.getName()),
        Reembolso(Reembolso.class.getName()),
        Aps(APS.class.getName()),
        CartaAval(CartaAval.class.getName()),
        AyudaSocial(AyudaSocial.class.getName()),
        Emergencia(Emergencia.class.getName()),
        Funerario(Funerario.class.getName()),
        Vida(Vida.class.getName());
        private String clase;

        private TipoDetalleSiniestro(String clase) {
            this.clase = clase;
        }

        public String getClase() {
            return clase;
        }
    }

    public static Domain TipoDetalleSiniestro() {
        Domain dominio = new Domain("TipoDetalleSiniestro");
        TipoDetalleSiniestro o[] = TipoDetalleSiniestro.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }
}
