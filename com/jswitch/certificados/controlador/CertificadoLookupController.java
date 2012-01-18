/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.certificados.controlador;

import com.jswitch.base.controlador.util.DefaultLookupDataLocatorPorNombre;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import java.awt.Dimension;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author Orlando Becerra
 */
public class CertificadoLookupController extends LookupController {

    public CertificadoLookupController() {
    }

    public CertificadoLookupController(
            String classFullName) {
        this.setLookupDataLocator(new DefaultLookupDataLocatorPorNombre(classFullName));
        this.setLookupGridController(new DefaultLookupGridController());
        setLookupValueObjectClassName(classFullName);
        defaultModel();
    }

    public void defaultModel() {
        setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_LAST_VALID_CODE);
        setAllColumnVisible(false);
        setVisibleColumn("id", true);
        setPreferredWidthColumn("id", 50);
        setSortableColumn("id", true);
        setSortedColumn("id", Consts.DESC_SORTED, 0);
        setFramePreferedSize(new Dimension(70, 330));
    }
}
