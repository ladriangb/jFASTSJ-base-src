package com.jswitch.siniestros.vista;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultGridFrame;
import com.jswitch.fas.modelo.Dominios;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author bc
 */
public class SiniestroGridFrame extends DefaultGridFrame {

    public SiniestroGridFrame() {
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
        comboColumn1 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn1 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn2 = new org.openswing.swing.table.columns.client.DateColumn();

        setTitle("Siniestros");
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
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filterButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        gridData.setExportButton(exportButton1);
        gridData.setFilterButton(filterButton1);
        gridData.setInsertRowsOnTop(false);
        gridData.setNavBar(navigatorBar1);
        gridData.setReloadButton(reloadButton1);
        gridData.setSearchAdditionalRows(true);
        gridData.setValueObjectClassName(Siniestro.class.getName());

        decimalColumn1.setColumnFilterable(true);
        decimalColumn1.setColumnName("id");
        decimalColumn1.setColumnRequired(false);
        decimalColumn1.setColumnSortable(true);
        decimalColumn1.setPreferredWidth(40);
        decimalColumn1.setSortVersus(org.openswing.swing.util.java.Consts.DESC_SORTED);
        gridData.getColumnContainer().add(decimalColumn1);

        comboColumn1.setColumnFilterable(true);
        comboColumn1.setColumnName("estatusSiniestro");
        comboColumn1.setColumnRequired(false);
        comboColumn1.setColumnSortable(true);
        comboColumn1.setDomainId(Dominios.EstadoSiniestro().getDomainId());
        gridData.getColumnContainer().add(comboColumn1);

        textColumn3.setColumnFilterable(true);
        textColumn3.setColumnName("numero");
        textColumn3.setColumnRequired(false);
        textColumn3.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn3);

        textColumn1.setColumnFilterable(true);
        textColumn1.setColumnName("certificado.titular.persona.nombreLargo");
        textColumn1.setColumnRequired(false);
        textColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn1);

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("asegurado.persona.nombreLargo");
        textColumn2.setColumnRequired(false);
        textColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn2);

        textColumn4.setColumnFilterable(true);
        textColumn4.setColumnName("auditoria.usuarioInsert");
        textColumn4.setColumnRequired(false);
        textColumn4.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn4);

        dateColumn1.setColumnFilterable(true);
        dateColumn1.setColumnName("auditoria.fechaInsert");
        dateColumn1.setColumnRequired(false);
        dateColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn1);

        textColumn5.setColumnFilterable(true);
        textColumn5.setColumnName("auditoria.usuarioUpdate");
        textColumn5.setColumnRequired(false);
        textColumn5.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn5);

        dateColumn2.setColumnFilterable(true);
        dateColumn2.setColumnName("auditoria.fechaUpdate");
        dateColumn2.setColumnRequired(false);
        dateColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn2);

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
                .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void inicializar(GridDataLocator gridDataLocator, GridController gridController, String valueObjectClassName, String titulo, boolean addToMDIFrame) {
        initComponents();
        gridData.setGridDataLocator(gridDataLocator);
        gridData.setController(gridController);       
        //setTitle(titulo);
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
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn1;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn1;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn1;
    private org.openswing.swing.client.ExportButton exportButton1;
    private org.openswing.swing.client.FilterButton filterButton1;
    private org.openswing.swing.client.GridControl gridData;
    private javax.swing.JPanel jPanel1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    // End of variables declaration//GEN-END:variables
}
