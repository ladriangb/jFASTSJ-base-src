package com.jswitch.asegurados.vista;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultGridFrame;
import com.jswitch.asegurados.modelo.maestra.Titular;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author bc
 */
public class TitularGridFrame extends DefaultGridFrame {

    public TitularGridFrame() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        exportButton1 = new org.openswing.swing.client.ExportButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        filterButton1 = new org.openswing.swing.client.FilterButton();
        gridData = new org.openswing.swing.client.GridControl();
        decimalColumn1 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn10 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn1 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn2 = new org.openswing.swing.table.columns.client.DateColumn();
        auditoria = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn1 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn2 = new org.openswing.swing.table.columns.client.DateTimeColumn();

        setTitle("Titulares");
        setPreferredSize(new java.awt.Dimension(700, 540));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(368, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reloadButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(navigatorBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        gridData.setExportButton(exportButton1);
        gridData.setFilterButton(filterButton1);
        gridData.setFunctionId("VehiculosGrid");
        gridData.setInsertRowsOnTop(false);
        gridData.setNavBar(navigatorBar1);
        gridData.setReloadButton(reloadButton1);
        gridData.setSearchAdditionalRows(true);
        gridData.setValueObjectClassName(Titular.class.getName());

        decimalColumn1.setColumnFilterable(true);
        decimalColumn1.setColumnName("id");
        decimalColumn1.setColumnSortable(true);
        decimalColumn1.setPreferredWidth(40);
        decimalColumn1.setSortVersus(org.openswing.swing.util.java.Consts.DESC_SORTED);
        gridData.getColumnContainer().add(decimalColumn1);

        textColumn1.setColumnFilterable(true);
        textColumn1.setColumnName("persona.rif.rif");
        textColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn1);

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("persona.nombreLargo");
        textColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn2);

        textColumn10.setColumnFilterable(true);
        textColumn10.setColumnName("certificado.poliza.numero");
        textColumn10.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn10);

        textColumn7.setColumnFilterable(true);
        textColumn7.setColumnName("tipoContrato.nombre");
        textColumn7.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn7);

        textColumn8.setColumnFilterable(true);
        textColumn8.setColumnName("departamento.nombre");
        textColumn8.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn8);

        textColumn9.setColumnFilterable(true);
        textColumn9.setColumnName("codigoEmpleado");
        textColumn9.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn9);

        dateColumn1.setColumnName("fechaIngresoEmpresa");
        gridData.getColumnContainer().add(dateColumn1);

        dateColumn2.setColumnName("fechaInduccion");
        gridData.getColumnContainer().add(dateColumn2);

        auditoria.setColumnFilterable(true);
        auditoria.setColumnName("auditoria.activo");
        auditoria.setColumnSortable(true);
        gridData.getColumnContainer().add(auditoria);

        textColumn4.setColumnFilterable(true);
        textColumn4.setColumnName("auditoria.usuarioInsert");
        textColumn4.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn4);

        dateTimeColumn1.setColumnFilterable(true);
        dateTimeColumn1.setColumnName("auditoria.fechaInsert");
        dateTimeColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(dateTimeColumn1);

        textColumn5.setColumnFilterable(true);
        textColumn5.setColumnName("auditoria.usuarioUpdate");
        textColumn5.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn5);

        dateTimeColumn2.setColumnFilterable(true);
        dateTimeColumn2.setColumnName("auditoria.fechaUpdate");
        dateTimeColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(dateTimeColumn2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void inicializar(GridDataLocator gridDataLocator, GridController gridController, String valueObjectClassName, String titulo, boolean addToMDIFrame) {
        initComponents();
        gridData.setGridDataLocator(gridDataLocator);
        gridData.setController(gridController);       

        if (addToMDIFrame) {
            pack();
        } else {
            gridData.setAutoLoadData(false);
            setBounds(0, 0, 0, 0);
        }
        MDIFrame.add(this);
    }

    @Override
    public void reloadGridsData() {
        //gridData.reloadData();
    }

    @Override
    public void clearGridsData() {
    }

    @Override
    public void setOwnerVO(BeanVO beanVO) {
    }

    @Override
    public GridControl getGridControl() {
        return gridData;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CheckBoxColumn auditoria;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn1;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn2;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn1;
    private org.openswing.swing.client.ExportButton exportButton1;
    private org.openswing.swing.client.FilterButton filterButton1;
    private org.openswing.swing.client.GridControl gridData;
    private javax.swing.JPanel jPanel1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn10;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    private org.openswing.swing.table.columns.client.TextColumn textColumn8;
    private org.openswing.swing.table.columns.client.TextColumn textColumn9;
    // End of variables declaration//GEN-END:variables
}
