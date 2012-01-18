package com.jswitch.configuracion.vista.patologias;

import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultGridFrame;
import com.jswitch.configuracion.controlador.patologias.DiagnosticoGridInternalController;
import com.jswitch.configuracion.controlador.patologias.EspecialidadGridInternalController;
import com.jswitch.configuracion.controlador.patologias.RamoGridFrameController;
import com.jswitch.configuracion.controlador.patologias.TratamientoGridInternalController;
import com.jswitch.configuracion.modelo.dominio.Ramo;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import com.jswitch.configuracion.modelo.dominio.patologias.Especialidad;
import com.jswitch.configuracion.modelo.dominio.patologias.Tratamiento;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author adrian
 */
public class PatologiasGridFrame extends DefaultGridFrame {

    DefaultGridInternalController controllerDiagnosticos;
    DefaultGridInternalController controllerEspecialidades;
    DefaultGridInternalController controllerTratamientos;

    public PatologiasGridFrame() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelR = new javax.swing.JPanel();
        ramo = new org.openswing.swing.client.GridControl();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn11 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel9 = new javax.swing.JPanel();
        insertButtonR = new org.openswing.swing.client.InsertButton();
        editButtonR = new org.openswing.swing.client.EditButton();
        deleteButtonR = new org.openswing.swing.client.DeleteButton();
        saveButtonR = new org.openswing.swing.client.SaveButton();
        reloadButtonR = new org.openswing.swing.client.ReloadButton();
        exportButtonR = new org.openswing.swing.client.ExportButton();
        jPanelE = new javax.swing.JPanel();
        especialidad = new org.openswing.swing.client.GridControl();
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel10 = new javax.swing.JPanel();
        insertButtonE = new org.openswing.swing.client.InsertButton();
        editButtonE = new org.openswing.swing.client.EditButton();
        deleteButtonE = new org.openswing.swing.client.DeleteButton();
        saveButtonE = new org.openswing.swing.client.SaveButton();
        reloadButtonE = new org.openswing.swing.client.ReloadButton();
        exportButtonE = new org.openswing.swing.client.ExportButton();
        jPanelD = new javax.swing.JPanel();
        diagnostico = new org.openswing.swing.client.GridControl();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel8 = new javax.swing.JPanel();
        insertButtonD = new org.openswing.swing.client.InsertButton();
        editButtonD = new org.openswing.swing.client.EditButton();
        deleteButtonD = new org.openswing.swing.client.DeleteButton();
        saveButtonD = new org.openswing.swing.client.SaveButton();
        reloadButtonD = new org.openswing.swing.client.ReloadButton();
        exportButtonD = new org.openswing.swing.client.ExportButton();
        jPanelT = new javax.swing.JPanel();
        tratamiento = new org.openswing.swing.client.GridControl();
        textColumn10 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel11 = new javax.swing.JPanel();
        insertButton7 = new org.openswing.swing.client.InsertButton();
        editButton7 = new org.openswing.swing.client.EditButton();
        deleteButton7 = new org.openswing.swing.client.DeleteButton();
        saveButton7 = new org.openswing.swing.client.SaveButton();
        reloadButton7 = new org.openswing.swing.client.ReloadButton();
        exportButton7 = new org.openswing.swing.client.ExportButton();

        setTitle("Lista de patologias por Ramo");

        jPanelR.setBorder(javax.swing.BorderFactory.createTitledBorder("Ramo"));

        ramo.setDeleteButton(deleteButtonR);
        ramo.setEditButton(editButtonR);
        ramo.setExportButton(exportButtonR);
        ramo.setInsertButton(insertButtonR);
        ramo.setReloadButton(reloadButtonR);
        ramo.setSaveButton(saveButtonR);
        ramo.setValueObjectClassName(Ramo.class.getName());

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("nombre");
        textColumn2.setColumnSortable(true);
        textColumn2.setEditableOnEdit(true);
        textColumn2.setEditableOnInsert(true);
        textColumn2.setMaxCharacters(120);
        textColumn2.setPreferredWidth(400);
        textColumn2.setSortVersus(org.openswing.swing.util.java.Consts.ASC_SORTED);
        textColumn2.setTrimText(true);
        textColumn2.setUpperCase(true);
        ramo.getColumnContainer().add(textColumn2);

        textColumn11.setColumnName("idPropio");
        textColumn11.setEditableOnInsert(true);
        textColumn11.setTrimText(true);
        textColumn11.setUpperCase(true);
        ramo.getColumnContainer().add(textColumn11);

        jPanel9.setLayout(new java.awt.GridLayout(3, 2, 2, 2));
        jPanel9.add(insertButtonR);
        jPanel9.add(editButtonR);
        jPanel9.add(deleteButtonR);
        jPanel9.add(saveButtonR);
        jPanel9.add(reloadButtonR);
        jPanel9.add(exportButtonR);

        javax.swing.GroupLayout jPanelRLayout = new javax.swing.GroupLayout(jPanelR);
        jPanelR.setLayout(jPanelRLayout);
        jPanelRLayout.setHorizontalGroup(
            jPanelRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ramo, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );
        jPanelRLayout.setVerticalGroup(
            jPanelRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
            .addComponent(ramo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ramos", jPanelR);

        jPanelE.setBorder(javax.swing.BorderFactory.createTitledBorder("Diagnostico"));

        especialidad.setDeleteButton(deleteButtonE);
        especialidad.setEditButton(editButtonE);
        especialidad.setExportButton(exportButtonE);
        especialidad.setInsertButton(insertButtonE);
        especialidad.setReloadButton(reloadButtonE);
        especialidad.setSaveButton(saveButtonE);
        especialidad.setValueObjectClassName(Especialidad.class.getName());

        textColumn9.setColumnName("nombre");
        textColumn9.setEditableOnEdit(true);
        textColumn9.setEditableOnInsert(true);
        textColumn9.setPreferredWidth(250);
        textColumn9.setTrimText(true);
        textColumn9.setUpperCase(true);
        especialidad.getColumnContainer().add(textColumn9);

        textColumn4.setColumnName("ramo.nombre");
        textColumn4.setColumnRequired(false);
        textColumn4.setPreferredWidth(150);
        especialidad.getColumnContainer().add(textColumn4);

        jPanel10.setLayout(new java.awt.GridLayout(3, 2, 2, 2));
        jPanel10.add(insertButtonE);
        jPanel10.add(editButtonE);
        jPanel10.add(deleteButtonE);
        jPanel10.add(saveButtonE);
        jPanel10.add(reloadButtonE);
        jPanel10.add(exportButtonE);

        javax.swing.GroupLayout jPanelELayout = new javax.swing.GroupLayout(jPanelE);
        jPanelE.setLayout(jPanelELayout);
        jPanelELayout.setHorizontalGroup(
            jPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelELayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(especialidad, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );
        jPanelELayout.setVerticalGroup(
            jPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelELayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
            .addComponent(especialidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Especialidades", jPanelE);

        jPanelD.setBorder(javax.swing.BorderFactory.createTitledBorder("Diagnostico"));

        diagnostico.setDeleteButton(deleteButtonD);
        diagnostico.setEditButton(editButtonD);
        diagnostico.setExportButton(exportButtonD);
        diagnostico.setInsertButton(insertButtonD);
        diagnostico.setReloadButton(reloadButtonD);
        diagnostico.setSaveButton(saveButtonD);
        diagnostico.setValueObjectClassName(Diagnostico.class.getName());

        textColumn1.setColumnName("nombre");
        textColumn1.setEditableOnEdit(true);
        textColumn1.setEditableOnInsert(true);
        textColumn1.setPreferredWidth(200);
        textColumn1.setTrimText(true);
        textColumn1.setUpperCase(true);
        diagnostico.getColumnContainer().add(textColumn1);

        textColumn6.setColumnName("especialidad.ramo.nombre");
        textColumn6.setColumnRequired(false);
        diagnostico.getColumnContainer().add(textColumn6);

        textColumn3.setColumnName("especialidad.nombre");
        textColumn3.setColumnRequired(false);
        diagnostico.getColumnContainer().add(textColumn3);

        jPanel8.setLayout(new java.awt.GridLayout(3, 2, 2, 2));
        jPanel8.add(insertButtonD);
        jPanel8.add(editButtonD);
        jPanel8.add(deleteButtonD);
        jPanel8.add(saveButtonD);
        jPanel8.add(reloadButtonD);
        jPanel8.add(exportButtonD);

        javax.swing.GroupLayout jPanelDLayout = new javax.swing.GroupLayout(jPanelD);
        jPanelD.setLayout(jPanelDLayout);
        jPanelDLayout.setHorizontalGroup(
            jPanelDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diagnostico, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );
        jPanelDLayout.setVerticalGroup(
            jPanelDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
            .addComponent(diagnostico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Diagnosticos", jPanelD);

        jPanelT.setBorder(javax.swing.BorderFactory.createTitledBorder("Diagnostico"));

        tratamiento.setDeleteButton(deleteButton7);
        tratamiento.setEditButton(editButton7);
        tratamiento.setExportButton(exportButton7);
        tratamiento.setInsertButton(insertButton7);
        tratamiento.setReloadButton(reloadButton7);
        tratamiento.setSaveButton(saveButton7);
        tratamiento.setValueObjectClassName(Tratamiento.class.getName());

        textColumn10.setColumnName("nombre");
        textColumn10.setEditableOnEdit(true);
        textColumn10.setEditableOnInsert(true);
        textColumn10.setTrimText(true);
        textColumn10.setUpperCase(true);
        tratamiento.getColumnContainer().add(textColumn10);

        textColumn5.setColumnName("diagnostico.especialidad.ramo.nombre");
        textColumn5.setColumnRequired(false);
        tratamiento.getColumnContainer().add(textColumn5);

        textColumn7.setColumnName("diagnostico.especialidad.nombre");
        textColumn7.setColumnRequired(false);
        tratamiento.getColumnContainer().add(textColumn7);

        textColumn8.setColumnName("diagnostico.nombre");
        textColumn8.setColumnRequired(false);
        tratamiento.getColumnContainer().add(textColumn8);

        jPanel11.setLayout(new java.awt.GridLayout(3, 2, 2, 2));
        jPanel11.add(insertButton7);
        jPanel11.add(editButton7);
        jPanel11.add(deleteButton7);
        jPanel11.add(saveButton7);
        jPanel11.add(reloadButton7);
        jPanel11.add(exportButton7);

        javax.swing.GroupLayout jPanelTLayout = new javax.swing.GroupLayout(jPanelT);
        jPanelT.setLayout(jPanelTLayout);
        jPanelTLayout.setHorizontalGroup(
            jPanelTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tratamiento, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );
        jPanelTLayout.setVerticalGroup(
            jPanelTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
            .addComponent(tratamiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Tratamientos", jPanelT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void inicializar(GridDataLocator gridDataLocator, GridController gridController, String valueObjectClassName, String titulo, boolean addToMDIFrame) {
        initComponents();
        ramo.setGridDataLocator(gridDataLocator);
        ramo.setController(gridController);


        controllerTratamientos =
                new TratamientoGridInternalController(Diagnostico.class.getName(), "getTratamientos", tratamiento);

        tratamiento.setGridDataLocator(controllerTratamientos);
        tratamiento.setController(controllerTratamientos);

        controllerDiagnosticos =
                new DiagnosticoGridInternalController(Especialidad.class.getName(), "getDiagnosticos", diagnostico,
                controllerTratamientos);

        diagnostico.setGridDataLocator(controllerDiagnosticos);
        diagnostico.setController(controllerDiagnosticos);


        controllerEspecialidades =
                new EspecialidadGridInternalController(Ramo.class.getName(), "getEspecialidades", especialidad,
                controllerDiagnosticos);

        especialidad.setGridDataLocator(controllerEspecialidades);
        especialidad.setController(controllerEspecialidades);


        ((RamoGridFrameController) gridController).setPane(jTabbedPane1);
        ((EspecialidadGridInternalController) controllerEspecialidades).setPane(jTabbedPane1);
        ((DiagnosticoGridInternalController) controllerDiagnosticos).setPane(jTabbedPane1);

        if (addToMDIFrame) {
            pack();
        } else {
            setBounds(0, 0, 0, 0);
        }
        MDIFrame.add(this);
    }

    @Override
    public void reloadGridsData() {
        diagnostico.reloadData();
        especialidad.reloadData();
        tratamiento.reloadData();
    }

    @Override
    public void clearGridsData() {
        diagnostico.clearData();
        especialidad.clearData();
        tratamiento.clearData();
    }

    @Override
    public void setOwnerVO(BeanVO beanVO) {

        controllerEspecialidades.setBeanVO(beanVO);

        reloadGridsData();
    }

    @Override
    public GridControl getGridControl() {
        return ramo;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.DeleteButton deleteButton7;
    private org.openswing.swing.client.DeleteButton deleteButtonD;
    private org.openswing.swing.client.DeleteButton deleteButtonE;
    private org.openswing.swing.client.DeleteButton deleteButtonR;
    private org.openswing.swing.client.GridControl diagnostico;
    private org.openswing.swing.client.EditButton editButton7;
    private org.openswing.swing.client.EditButton editButtonD;
    private org.openswing.swing.client.EditButton editButtonE;
    private org.openswing.swing.client.EditButton editButtonR;
    private org.openswing.swing.client.GridControl especialidad;
    private org.openswing.swing.client.ExportButton exportButton7;
    private org.openswing.swing.client.ExportButton exportButtonD;
    private org.openswing.swing.client.ExportButton exportButtonE;
    private org.openswing.swing.client.ExportButton exportButtonR;
    private org.openswing.swing.client.InsertButton insertButton7;
    private org.openswing.swing.client.InsertButton insertButtonD;
    private org.openswing.swing.client.InsertButton insertButtonE;
    private org.openswing.swing.client.InsertButton insertButtonR;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelD;
    private javax.swing.JPanel jPanelE;
    private javax.swing.JPanel jPanelR;
    private javax.swing.JPanel jPanelT;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.openswing.swing.client.GridControl ramo;
    private org.openswing.swing.client.ReloadButton reloadButton7;
    private org.openswing.swing.client.ReloadButton reloadButtonD;
    private org.openswing.swing.client.ReloadButton reloadButtonE;
    private org.openswing.swing.client.ReloadButton reloadButtonR;
    private org.openswing.swing.client.SaveButton saveButton7;
    private org.openswing.swing.client.SaveButton saveButtonD;
    private org.openswing.swing.client.SaveButton saveButtonE;
    private org.openswing.swing.client.SaveButton saveButtonR;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn10;
    private org.openswing.swing.table.columns.client.TextColumn textColumn11;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    private org.openswing.swing.table.columns.client.TextColumn textColumn8;
    private org.openswing.swing.table.columns.client.TextColumn textColumn9;
    private org.openswing.swing.client.GridControl tratamiento;
    // End of variables declaration//GEN-END:variables
}
