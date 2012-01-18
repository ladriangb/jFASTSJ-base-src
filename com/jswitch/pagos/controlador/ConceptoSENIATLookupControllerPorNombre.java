package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultLookupDataLocatorPorNombre;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import java.awt.Dimension;
import org.openswing.swing.lookup.client.LookupController;
/**
 *
 * @author Adrian
 */
public class ConceptoSENIATLookupControllerPorNombre extends LookupController {

    public ConceptoSENIATLookupControllerPorNombre() {
    }

    public ConceptoSENIATLookupControllerPorNombre(
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
        setVisibleColumn("codigo", true);
        setVisibleColumn("nombre", true);
        setPreferredWidthColumn("id", 50);
        setPreferredWidthColumn("codigo", 50);
        setPreferredWidthColumn("nombre", 200);
        setFilterableColumn("nombre", true);
        setFilterableColumn("codigo", true);
        setSortableColumn("id", true);
        setSortableColumn("nombre", true);
        setFramePreferedSize(new Dimension(370, 330));
    }
}
