package com.jswitch.polizas.controlador;

import com.jswitch.base.controlador.util.DefaultLookupDataLocatorPorNombre;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import java.awt.Dimension;
import org.openswing.swing.lookup.client.LookupController;
/**
 *
 * @author Orlando Becerra
 */
public class PolizaLookupControllerPorNombre extends LookupController {

    public PolizaLookupControllerPorNombre() {
    }

    public PolizaLookupControllerPorNombre(
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
        setVisibleColumn("numero", true);
        setVisibleColumn("nombre", true);
        setPreferredWidthColumn("id", 50);
        setPreferredWidthColumn("numero", 70);
        setPreferredWidthColumn("nombre", 200);
        setFilterableColumn("nombre", true);
        setFilterableColumn("numero", true);
        setSortableColumn("id", true);
        setSortableColumn("nombre", true);
        setFramePreferedSize(new Dimension(370, 330));
    }
}
