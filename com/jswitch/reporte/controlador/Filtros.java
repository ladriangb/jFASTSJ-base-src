package com.jswitch.reporte.controlador;

import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.base.vista.util.DefaultGridFrame;
import com.jswitch.reporte.vista.xFiltro;

/**
 *
 * @author orlandobcrra
 */
public class Filtros {

    public DefaultGridFrame mostrarFiltro(Reporte reporte, boolean sencillo) {
        if (sencillo) {
            switch (reporte.getCategoria()) {
                case x1: {
                    new xFiltro(reporte).setVisible(true);
                    break;
                }
            }

        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
