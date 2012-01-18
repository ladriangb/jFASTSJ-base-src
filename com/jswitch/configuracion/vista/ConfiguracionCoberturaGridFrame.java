package com.jswitch.configuracion.vista;

import com.jswitch.base.controlador.util.DefaultLookupControllerPorNombre;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import com.jswitch.base.vista.util.DefaultGridFrame;
import com.jswitch.configuracion.modelo.dominio.Cobertura;
import com.jswitch.configuracion.modelo.dominio.Ramo;

/**
 * 
 * @author Orlando Becerra
 */
public class ConfiguracionCoberturaGridFrame extends DefaultGridFrame {

    public ConfiguracionCoberturaGridFrame() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        editButton1 = new org.openswing.swing.client.EditButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        exportButton1 = new org.openswing.swing.client.ExportButton();
        jPanel2 = new javax.swing.JPanel();
        gridData = new org.openswing.swing.client.GridControl();
        decimalColumn1 = new org.openswing.swing.table.columns.client.DecimalColumn();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        checkBoxColumn2 = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        checkBoxColumn5 = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        checkBoxColumn4 = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        checkBoxColumn6 = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        checkBoxColumn7 = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        checkBoxColumn1 = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        dateTimeColumn1 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn2 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();

        setTitle("Configuracion Cobertura");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(insertButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(378, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insertButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridData.setDeleteButton(deleteButton1);
        gridData.setEditButton(editButton1);
        gridData.setExportButton(exportButton1);
        gridData.setFunctionId("Mantenimiento");
        gridData.setInsertButton(insertButton1);
        gridData.setMaxNumberOfRowsOnInsert(20);
        gridData.setReloadButton(reloadButton1);
        gridData.setSaveButton(saveButton1);
        gridData.setValueObjectClassName(Cobertura.class.getName());

        decimalColumn1.setColumnFilterable(true);
        decimalColumn1.setColumnName("id");
        decimalColumn1.setColumnRequired(false);
        decimalColumn1.setColumnSortable(true);
        decimalColumn1.setGrouping(false);
        decimalColumn1.setPreferredWidth(50);
        gridData.getColumnContainer().add(decimalColumn1);

        codLookupColumn1.setColumnFilterable(true);
        codLookupColumn1.setColumnName("ramo.nombre");
        codLookupColumn1.setColumnSortable(true);
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        gridData.getColumnContainer().add(codLookupColumn1);

        textColumn4.setColumnFilterable(true);
        textColumn4.setColumnName("nombre");
        textColumn4.setColumnSortable(true);
        textColumn4.setEditableOnEdit(true);
        textColumn4.setEditableOnInsert(true);
        gridData.getColumnContainer().add(textColumn4);

        checkBoxColumn2.setColumnFilterable(true);
        checkBoxColumn2.setColumnName("baseImponible");
        checkBoxColumn2.setColumnSortable(true);
        checkBoxColumn2.setEditableOnEdit(true);
        checkBoxColumn2.setEditableOnInsert(true);
        checkBoxColumn2.setPreferredWidth(70);
        gridData.getColumnContainer().add(checkBoxColumn2);

        checkBoxColumn5.setColumnFilterable(true);
        checkBoxColumn5.setColumnName("islr");
        checkBoxColumn5.setColumnRequired(false);
        checkBoxColumn5.setColumnSortable(true);
        checkBoxColumn5.setEditableOnEdit(true);
        checkBoxColumn5.setEditableOnInsert(true);
        checkBoxColumn5.setPreferredWidth(70);
        gridData.getColumnContainer().add(checkBoxColumn5);

        checkBoxColumn4.setColumnFilterable(true);
        checkBoxColumn4.setColumnName("iva");
        checkBoxColumn4.setColumnRequired(false);
        checkBoxColumn4.setColumnSortable(true);
        checkBoxColumn4.setEditableOnEdit(true);
        checkBoxColumn4.setEditableOnInsert(true);
        checkBoxColumn4.setPreferredWidth(70);
        gridData.getColumnContainer().add(checkBoxColumn4);

        checkBoxColumn6.setColumnFilterable(true);
        checkBoxColumn6.setColumnName("gastosClinicos");
        checkBoxColumn6.setColumnRequired(false);
        checkBoxColumn6.setColumnSortable(true);
        checkBoxColumn6.setEditableOnEdit(true);
        checkBoxColumn6.setEditableOnInsert(true);
        checkBoxColumn6.setPreferredWidth(70);
        gridData.getColumnContainer().add(checkBoxColumn6);

        checkBoxColumn7.setColumnFilterable(true);
        checkBoxColumn7.setColumnName("honorariosMedicos");
        checkBoxColumn7.setColumnRequired(false);
        checkBoxColumn7.setColumnSortable(true);
        checkBoxColumn7.setEditableOnEdit(true);
        checkBoxColumn7.setEditableOnInsert(true);
        checkBoxColumn7.setPreferredWidth(70);
        gridData.getColumnContainer().add(checkBoxColumn7);

        checkBoxColumn1.setColumnFilterable(true);
        checkBoxColumn1.setColumnName("auditoria.activo");
        checkBoxColumn1.setColumnSortable(true);
        checkBoxColumn1.setEditableOnEdit(true);
        checkBoxColumn1.setEditableOnInsert(true);
        gridData.getColumnContainer().add(checkBoxColumn1);

        dateTimeColumn1.setColumnFilterable(true);
        dateTimeColumn1.setColumnName("auditoria.fechaInsert");
        dateTimeColumn1.setColumnRequired(false);
        dateTimeColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(dateTimeColumn1);

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("auditoria.usuarioInsert");
        textColumn2.setColumnRequired(false);
        textColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn2);

        dateTimeColumn2.setColumnFilterable(true);
        dateTimeColumn2.setColumnName("auditoria.fechaUpdate");
        dateTimeColumn2.setColumnRequired(false);
        dateTimeColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(dateTimeColumn2);

        textColumn3.setColumnFilterable(true);
        textColumn3.setColumnName("auditoria.usuarioUpdate");
        textColumn3.setColumnRequired(false);
        textColumn3.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn1;
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn2;
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn4;
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn5;
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn6;
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn7;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn1;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.ExportButton exportButton1;
    private org.openswing.swing.client.GridControl gridData;
    private org.openswing.swing.client.InsertButton insertButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    // End of variables declaration//GEN-END:variables

    @Override
    public void inicializar(GridDataLocator gridDataLocator,
            GridController gridController, String valueObjectClassName, String titulo, boolean addToMDIFrame) {
        initComponents();
        if (titulo != null) {
            this.setTitle(titulo);
        }
        gridData.setValueObjectClassName(valueObjectClassName);
        gridData.setGridDataLocator(gridDataLocator);
        gridData.setController(gridController);
        DefaultLookupControllerPorNombre l= new DefaultLookupControllerPorNombre(Ramo.class.getName());
        l.addLookup2ParentLink("ramo");
        codLookupColumn1.setLookupController(l);

        if (addToMDIFrame) {
            pack();
        } else {
            setBounds(0, 0, 0, 0);
        }
        MDIFrame.add(this);
    }

    @Override
    public GridControl getGridControl() {
        return gridData;
    }
}
