package com.jswitch.asegurados.vista;


import com.jswitch.asegurados.modelo.maestra.Beneficiario;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultGridFrame;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author bc
 */
public class BeneficiarioGridFrame extends DefaultGridFrame {

    public BeneficiarioGridFrame() {
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
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        integerColumn2 = new org.openswing.swing.table.columns.client.IntegerColumn();
        decimalColumn1 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        auditoria = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn2 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();

        setTitle("Beneficiarios");
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reloadButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(navigatorBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(6, Short.MAX_VALUE))
        );

        gridData.setExportButton(exportButton1);
        gridData.setFilterButton(filterButton1);
        gridData.setFunctionId("VehiculosGrid");
        gridData.setInsertRowsOnTop(false);
        gridData.setNavBar(navigatorBar1);
        gridData.setReloadButton(reloadButton1);
        gridData.setSearchAdditionalRows(true);
        gridData.setValueObjectClassName(Beneficiario.class.getName());

        decimalColumn5.setColumnFilterable(true);
        decimalColumn5.setColumnName("id");
        decimalColumn5.setColumnRequired(false);
        decimalColumn5.setColumnSortable(true);
        decimalColumn5.setPreferredWidth(40);
        decimalColumn5.setSortVersus(org.openswing.swing.util.java.Consts.DESC_SORTED);
        gridData.getColumnContainer().add(decimalColumn5);

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("persona.rif.rif");
        textColumn2.setColumnRequired(false);
        textColumn2.setColumnSortable(true);
        textColumn2.setMaxCharacters(1024);
        gridData.getColumnContainer().add(textColumn2);

        textColumn5.setColumnFilterable(true);
        textColumn5.setColumnName("persona.nombreLargo");
        textColumn5.setColumnRequired(false);
        textColumn5.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn5);

        integerColumn2.setColumnFilterable(true);
        integerColumn2.setColumnName("persona.edad");
        integerColumn2.setColumnRequired(false);
        integerColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(integerColumn2);

        decimalColumn1.setColumnFilterable(true);
        decimalColumn1.setColumnName("indemnizacion");
        decimalColumn1.setColumnRequired(false);
        decimalColumn1.setColumnSortable(true);
        decimalColumn1.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn1);

        textColumn3.setColumnFilterable(true);
        textColumn3.setColumnName("certificado.titular.persona.rif.rif");
        textColumn3.setColumnRequired(false);
        textColumn3.setColumnSortable(true);
        textColumn3.setMaxCharacters(1024);
        gridData.getColumnContainer().add(textColumn3);

        textColumn6.setColumnFilterable(true);
        textColumn6.setColumnName("certificado.titular.persona.nombreLargo");
        textColumn6.setColumnRequired(false);
        textColumn6.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn6);

        auditoria.setColumnFilterable(true);
        auditoria.setColumnName("auditoria.activo");
        auditoria.setColumnRequired(false);
        auditoria.setColumnSortable(true);
        gridData.getColumnContainer().add(auditoria);

        textColumn9.setColumnFilterable(true);
        textColumn9.setColumnName("auditoria.usuarioInsert");
        textColumn9.setColumnRequired(false);
        textColumn9.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn9);

        dateTimeColumn2.setColumnFilterable(true);
        dateTimeColumn2.setColumnName("auditoria.fechaInsert");
        dateTimeColumn2.setColumnRequired(false);
        dateTimeColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(dateTimeColumn2);

        textColumn4.setColumnFilterable(true);
        textColumn4.setColumnName("certificado.poliza.numero");
        textColumn4.setColumnRequired(false);
        textColumn4.setColumnSortable(true);
        textColumn4.setMaxCharacters(1024);
        gridData.getColumnContainer().add(textColumn4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
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
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.client.ExportButton exportButton1;
    private org.openswing.swing.client.FilterButton filterButton1;
    private org.openswing.swing.client.GridControl gridData;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn2;
    private javax.swing.JPanel jPanel1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.table.columns.client.TextColumn textColumn9;
    // End of variables declaration//GEN-END:variables
}
