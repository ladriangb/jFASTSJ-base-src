package com.jswitch.configuracion.vista;

import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultGridFrame;
import com.jswitch.configuracion.controlador.ConfiguracionSiniestroPlanGridInternalController;
import com.jswitch.configuracion.controlador.SumaAseguradaPlanGridInternalController;
import com.jswitch.configuracion.controlador.DiagnosticoLookupController;
import com.jswitch.configuracion.controlador.SumaAmparadaPlanGridInternalController;
import com.jswitch.configuracion.controlador.SumaAmparadaPorPlanLookupController;
import com.jswitch.configuracion.modelo.transaccional.SumaAsegurada;
import com.jswitch.configuracion.modelo.maestra.Plan;
import com.jswitch.configuracion.modelo.transaccional.ConfiguracionSiniestro;
import com.jswitch.configuracion.modelo.transaccional.SumaAmparada;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author adrian
 */
public class PlanesGridFrame extends DefaultGridFrame {

    private DefaultGridInternalController controllerSumaAsegurada;
    private DefaultGridInternalController controllerSumaAmparada;
    private DefaultGridInternalController controllerConfiguracionSiniestros;
    private DiagnosticoLookupController lookupDiagnostico;
    private SumaAmparadaPorPlanLookupController lookupSumaAmparada;

    public PlanesGridFrame() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        gridData = new org.openswing.swing.client.GridControl();
        decimalColumnNot = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel7 = new javax.swing.JPanel();
        insertButton3 = new org.openswing.swing.client.InsertButton();
        editButton3 = new org.openswing.swing.client.EditButton();
        deleteButton3 = new org.openswing.swing.client.DeleteButton();
        saveButton3 = new org.openswing.swing.client.SaveButton();
        reloadButton3 = new org.openswing.swing.client.ReloadButton();
        exportButton3 = new org.openswing.swing.client.ExportButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        gridControl1 = new org.openswing.swing.client.GridControl();
        decimalColumnNot1 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn1 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel8 = new javax.swing.JPanel();
        insertButton4 = new org.openswing.swing.client.InsertButton();
        editButton4 = new org.openswing.swing.client.EditButton();
        deleteButton4 = new org.openswing.swing.client.DeleteButton();
        saveButton4 = new org.openswing.swing.client.SaveButton();
        reloadButton4 = new org.openswing.swing.client.ReloadButton();
        exportButton4 = new org.openswing.swing.client.ExportButton();
        jPanel4 = new javax.swing.JPanel();
        gridControl2 = new org.openswing.swing.client.GridControl();
        decimalColumnNot2 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        codLookupColumn2 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel9 = new javax.swing.JPanel();
        insertButton5 = new org.openswing.swing.client.InsertButton();
        editButton5 = new org.openswing.swing.client.EditButton();
        deleteButton5 = new org.openswing.swing.client.DeleteButton();
        saveButton5 = new org.openswing.swing.client.SaveButton();
        reloadButton5 = new org.openswing.swing.client.ReloadButton();
        exportButton5 = new org.openswing.swing.client.ExportButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        jPanel5 = new javax.swing.JPanel();
        gridControl3 = new org.openswing.swing.client.GridControl();
        decimalColumnNot3 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn2 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn3 = new org.openswing.swing.table.columns.client.DecimalColumn();
        jPanel10 = new javax.swing.JPanel();
        insertButton6 = new org.openswing.swing.client.InsertButton();
        editButton6 = new org.openswing.swing.client.EditButton();
        deleteButton6 = new org.openswing.swing.client.DeleteButton();
        saveButton6 = new org.openswing.swing.client.SaveButton();
        reloadButton6 = new org.openswing.swing.client.ReloadButton();
        exportButton6 = new org.openswing.swing.client.ExportButton();

        setTitle("Planes");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Plan"));

        gridData.setDeleteButton(deleteButton3);
        gridData.setEditButton(editButton3);
        gridData.setExportButton(exportButton3);
        gridData.setInsertButton(insertButton3);
        gridData.setReloadButton(reloadButton3);
        gridData.setSaveButton(saveButton3);
        gridData.setValueObjectClassName(Plan.class.getName());

        decimalColumnNot.setColumnName("id");
        decimalColumnNot.setColumnRequired(false);
        decimalColumnNot.setPreferredWidth(40);
        decimalColumnNot.setSortVersus(org.openswing.swing.util.java.Consts.DESC_SORTED);
        gridData.getColumnContainer().add(decimalColumnNot);

        textColumn1.setColumnFilterable(true);
        textColumn1.setColumnName("nombre");
        textColumn1.setColumnSortable(true);
        textColumn1.setEditableOnEdit(true);
        textColumn1.setEditableOnInsert(true);
        textColumn1.setMaxCharacters(120);
        textColumn1.setPreferredWidth(320);
        textColumn1.setSortVersus(org.openswing.swing.util.java.Consts.ASC_SORTED);
        textColumn1.setUpperCase(true);
        gridData.getColumnContainer().add(textColumn1);

        jPanel7.setLayout(new java.awt.GridLayout(3, 2, 2, 2));
        jPanel7.add(insertButton3);
        jPanel7.add(editButton3);
        jPanel7.add(deleteButton3);
        jPanel7.add(saveButton3);
        jPanel7.add(reloadButton3);
        jPanel7.add(exportButton3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
            .addComponent(gridData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
        );

        gridControl1.setDeleteButton(deleteButton4);
        gridControl1.setEditButton(editButton4);
        gridControl1.setExportButton(exportButton4);
        gridControl1.setInsertButton(insertButton4);
        gridControl1.setReloadButton(reloadButton4);
        gridControl1.setSaveButton(saveButton4);
        gridControl1.setValueObjectClassName(SumaAmparada.class.getName());

        decimalColumnNot1.setColumnFilterable(true);
        decimalColumnNot1.setColumnName("id");
        decimalColumnNot1.setColumnRequired(false);
        decimalColumnNot1.setColumnSortable(true);
        decimalColumnNot1.setPreferredWidth(40);
        decimalColumnNot1.setSortVersus(org.openswing.swing.util.java.Consts.DESC_SORTED);
        gridControl1.getColumnContainer().add(decimalColumnNot1);

        textColumn4.setColumnFilterable(true);
        textColumn4.setColumnName("nombre");
        textColumn4.setColumnSortable(true);
        textColumn4.setEditableOnEdit(true);
        textColumn4.setEditableOnInsert(true);
        gridControl1.getColumnContainer().add(textColumn4);

        decimalColumn1.setColumnFilterable(true);
        decimalColumn1.setColumnName("monto");
        decimalColumn1.setColumnSortable(true);
        decimalColumn1.setDecimals(2);
        decimalColumn1.setEditableOnEdit(true);
        decimalColumn1.setEditableOnInsert(true);
        gridControl1.getColumnContainer().add(decimalColumn1);

        textColumn3.setColumnFilterable(true);
        textColumn3.setColumnName("plan.nombre");
        textColumn3.setColumnRequired(false);
        textColumn3.setColumnSortable(true);
        gridControl1.getColumnContainer().add(textColumn3);

        jPanel8.setLayout(new java.awt.GridLayout(3, 2, 2, 2));
        jPanel8.add(insertButton4);
        jPanel8.add(editButton4);
        jPanel8.add(deleteButton4);
        jPanel8.add(saveButton4);
        jPanel8.add(reloadButton4);
        jPanel8.add(exportButton4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(236, Short.MAX_VALUE))
            .addComponent(gridControl1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Cobertura Amparada", jPanel2);

        gridControl2.setDeleteButton(deleteButton5);
        gridControl2.setEditButton(editButton5);
        gridControl2.setExportButton(exportButton5);
        gridControl2.setInsertButton(insertButton5);
        gridControl2.setNavBar(navigatorBar1);
        gridControl2.setReloadButton(reloadButton5);
        gridControl2.setSaveButton(saveButton5);
        gridControl2.setValueObjectClassName(SumaAsegurada.class.getName());

        decimalColumnNot2.setColumnFilterable(true);
        decimalColumnNot2.setColumnName("id");
        decimalColumnNot2.setColumnRequired(false);
        decimalColumnNot2.setColumnSortable(true);
        decimalColumnNot2.setPreferredWidth(40);
        gridControl2.getColumnContainer().add(decimalColumnNot2);

        textColumn5.setColumnFilterable(true);
        textColumn5.setColumnName("diagnostico.especialidad.ramo.nombre");
        textColumn5.setColumnSortable(true);
        gridControl2.getColumnContainer().add(textColumn5);

        textColumn6.setColumnFilterable(true);
        textColumn6.setColumnName("diagnostico.especialidad.nombre");
        textColumn6.setColumnSortable(true);
        gridControl2.getColumnContainer().add(textColumn6);

        codLookupColumn2.setColumnFilterable(true);
        codLookupColumn2.setColumnName("diagnostico.nombre");
        codLookupColumn2.setColumnSortable(true);
        codLookupColumn2.setControllerMethodName("getPatologias");
        codLookupColumn2.setEditableOnEdit(true);
        codLookupColumn2.setEditableOnInsert(true);
        gridControl2.getColumnContainer().add(codLookupColumn2);

        codLookupColumn1.setColumnFilterable(true);
        codLookupColumn1.setColumnName("sumaAmparada.nombre");
        codLookupColumn1.setColumnSortable(true);
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        gridControl2.getColumnContainer().add(codLookupColumn1);

        textColumn7.setColumnFilterable(true);
        textColumn7.setColumnName("plan.nombre");
        textColumn7.setColumnRequired(false);
        textColumn7.setColumnSortable(true);
        gridControl2.getColumnContainer().add(textColumn7);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(insertButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reloadButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exportButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reloadButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insertButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(gridControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gridControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Suma Asegurada", jPanel4);

        gridControl3.setDeleteButton(deleteButton6);
        gridControl3.setEditButton(editButton6);
        gridControl3.setExportButton(exportButton6);
        gridControl3.setInsertButton(insertButton6);
        gridControl3.setReloadButton(reloadButton6);
        gridControl3.setSaveButton(saveButton6);
        gridControl3.setValueObjectClassName(ConfiguracionSiniestro.class.getName());

        decimalColumnNot3.setColumnFilterable(true);
        decimalColumnNot3.setColumnName("id");
        decimalColumnNot3.setColumnRequired(false);
        decimalColumnNot3.setColumnSortable(true);
        decimalColumnNot3.setPreferredWidth(40);
        gridControl3.getColumnContainer().add(decimalColumnNot3);

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("plan.nombre");
        textColumn2.setColumnRequired(false);
        textColumn2.setColumnSortable(true);
        gridControl3.getColumnContainer().add(textColumn2);

        textColumn8.setColumnFilterable(true);
        textColumn8.setColumnName("tipoDetalle");
        textColumn8.setColumnSortable(true);
        textColumn8.setEditableOnEdit(true);
        textColumn8.setEditableOnInsert(true);
        textColumn8.setTrimText(true);
        gridControl3.getColumnContainer().add(textColumn8);

        decimalColumn2.setColumnFilterable(true);
        decimalColumn2.setColumnName("montoTope");
        decimalColumn2.setColumnSortable(true);
        decimalColumn2.setDecimals(2);
        decimalColumn2.setEditableOnEdit(true);
        decimalColumn2.setEditableOnInsert(true);
        gridControl3.getColumnContainer().add(decimalColumn2);

        decimalColumn3.setColumnFilterable(true);
        decimalColumn3.setColumnName("montoDeducible");
        decimalColumn3.setColumnSortable(true);
        decimalColumn3.setDecimals(2);
        decimalColumn3.setEditableOnEdit(true);
        decimalColumn3.setEditableOnInsert(true);
        gridControl3.getColumnContainer().add(decimalColumn3);

        jPanel10.setLayout(new java.awt.GridLayout(3, 2, 2, 2));
        jPanel10.add(insertButton6);
        jPanel10.add(editButton6);
        jPanel10.add(deleteButton6);
        jPanel10.add(saveButton6);
        jPanel10.add(reloadButton6);
        jPanel10.add(exportButton6);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridControl3, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(236, Short.MAX_VALUE))
            .addComponent(gridControl3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Configuracion Tipo Detalle de Siniestro", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void inicializar(GridDataLocator gridDataLocator, GridController gridController, String valueObjectClassName, String titulo, boolean addToMDIFrame) {
        initComponents();

        gridData.setGridDataLocator(gridDataLocator);
        gridData.setController(gridController);


        lookupDiagnostico = new DiagnosticoLookupController();
        lookupDiagnostico.addLookup2ParentLink("diagnostico");
        codLookupColumn2.setLookupController(lookupDiagnostico);

        lookupSumaAmparada = new SumaAmparadaPorPlanLookupController();
        lookupSumaAmparada.addLookup2ParentLink("sumaAmparada");
        codLookupColumn1.setLookupController(lookupSumaAmparada);


        controllerSumaAsegurada =
                new SumaAseguradaPlanGridInternalController(
                Plan.class.getName(),
                "getSumasAseguradas", gridControl2);
        gridControl2.setGridDataLocator(controllerSumaAsegurada);
        gridControl2.setController(controllerSumaAsegurada);

        controllerConfiguracionSiniestros =
                new ConfiguracionSiniestroPlanGridInternalController(
                Plan.class.getName(),
                "getConfiguracionSiniestros", gridControl3);
        gridControl3.setGridDataLocator(controllerConfiguracionSiniestros);
        gridControl3.setController(controllerConfiguracionSiniestros);

        controllerSumaAmparada = new SumaAmparadaPlanGridInternalController(
                Plan.class.getName(), "getSumaAmparadas", gridControl1);
        gridControl1.setGridDataLocator(controllerSumaAmparada);
        gridControl1.setController(controllerSumaAmparada);

        if (addToMDIFrame) {
            pack();
        } else {
            setBounds(0, 0, 0, 0);
        }
        MDIFrame.add(this);
    }

    @Override
    public void reloadGridsData() {
        gridControl1.reloadData();
        gridControl2.reloadData();
        gridControl3.reloadData();
    }

    @Override
    public void clearGridsData() {
        gridControl1.clearData();
        gridControl2.clearData();
        gridControl3.clearData();
    }

    @Override
    public void setOwnerVO(final BeanVO beanVO) {
        lookupSumaAmparada.setPlan((Plan) beanVO);
        controllerSumaAsegurada.setBeanVO(beanVO);
        controllerSumaAmparada.setBeanVO(beanVO);
        controllerConfiguracionSiniestros.setBeanVO(beanVO);
        reloadGridsData();
    }

    @Override
    public GridControl getGridControl() {
        return gridData;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn3;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumnNot;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumnNot1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumnNot2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumnNot3;
    private org.openswing.swing.client.DeleteButton deleteButton3;
    private org.openswing.swing.client.DeleteButton deleteButton4;
    private org.openswing.swing.client.DeleteButton deleteButton5;
    private org.openswing.swing.client.DeleteButton deleteButton6;
    private org.openswing.swing.client.EditButton editButton3;
    private org.openswing.swing.client.EditButton editButton4;
    private org.openswing.swing.client.EditButton editButton5;
    private org.openswing.swing.client.EditButton editButton6;
    private org.openswing.swing.client.ExportButton exportButton3;
    private org.openswing.swing.client.ExportButton exportButton4;
    private org.openswing.swing.client.ExportButton exportButton5;
    private org.openswing.swing.client.ExportButton exportButton6;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.client.GridControl gridControl2;
    private org.openswing.swing.client.GridControl gridControl3;
    private org.openswing.swing.client.GridControl gridData;
    private org.openswing.swing.client.InsertButton insertButton3;
    private org.openswing.swing.client.InsertButton insertButton4;
    private org.openswing.swing.client.InsertButton insertButton5;
    private org.openswing.swing.client.InsertButton insertButton6;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton3;
    private org.openswing.swing.client.ReloadButton reloadButton4;
    private org.openswing.swing.client.ReloadButton reloadButton5;
    private org.openswing.swing.client.ReloadButton reloadButton6;
    private org.openswing.swing.client.SaveButton saveButton3;
    private org.openswing.swing.client.SaveButton saveButton4;
    private org.openswing.swing.client.SaveButton saveButton5;
    private org.openswing.swing.client.SaveButton saveButton6;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    private org.openswing.swing.table.columns.client.TextColumn textColumn8;
    // End of variables declaration//GEN-END:variables
}
