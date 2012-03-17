package com.jswitch.siniestros.vista;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultGridFrame;
import com.jswitch.fas.modelo.Dominios;
import com.jswitch.siniestros.controlador.ListaDiagnosticoGridInternalController;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.vistasbd.ListaDiagnostico;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author Luis Adrian Gonzalez Benavides
 */
public class SiniestroGridFrame extends DefaultGridFrame {

    ListaDiagnosticoGridInternalController listaController;
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        exportButton1 = new org.openswing.swing.client.ExportButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        gridData = new org.openswing.swing.client.GridControl();
        decimalColumn1 = new org.openswing.swing.table.columns.client.DecimalColumn();
        comboColumn1 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        integerColumn1 = new org.openswing.swing.table.columns.client.IntegerColumn();
        integerColumn2 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn1 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn2 = new org.openswing.swing.table.columns.client.DateColumn();
        gridControl1 = new org.openswing.swing.client.GridControl();
        textColumn10 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        exportButton2 = new org.openswing.swing.client.ExportButton();

        setTitle("Siniestros");
        setPreferredSize(new java.awt.Dimension(700, 540));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(396, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        gridData.setExportButton(exportButton1);
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
        comboColumn1.setColumnName("estadoSiniestro");
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

        integerColumn1.setColumnName("ayo");
        gridData.getColumnContainer().add(integerColumn1);

        integerColumn2.setColumnName("mes");
        gridData.getColumnContainer().add(integerColumn2);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        gridControl1.setExportButton(exportButton2);
        gridControl1.setValueObjectClassName(ListaDiagnostico.class.getName());

        textColumn10.setColumnFilterable(true);
        textColumn10.setColumnName("siniestro.numero");
        textColumn10.setColumnRequired(false);
        textColumn10.setColumnSortable(true);
        gridControl1.getColumnContainer().add(textColumn10);

        textColumn6.setColumnName("detalleSiniestro.tipoDetalle");
        textColumn6.setPreferredWidth(200);
        gridControl1.getColumnContainer().add(textColumn6);

        textColumn7.setColumnName("diagnostico.nombre");
        textColumn7.setPreferredWidth(200);
        gridControl1.getColumnContainer().add(textColumn7);

        textColumn8.setColumnName("diagnostico.especialidad.nombre");
        textColumn8.setPreferredWidth(200);
        gridControl1.getColumnContainer().add(textColumn8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exportButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exportButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gridControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void inicializar(GridDataLocator gridDataLocator, GridController gridController, String valueObjectClassName, String titulo, boolean addToMDIFrame) {
        initComponents();
        listaController = new ListaDiagnosticoGridInternalController(gridControl1);
        gridControl1.setController(listaController);
        gridControl1.setGridDataLocator(listaController);
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
        gridControl1.reloadData();
    }

    @Override
    public void clearGridsData() {
        gridControl1.clearData();
    }

    @Override
    public void setOwnerVO(BeanVO beanVO) {
        listaController.setBeanVO(beanVO);
        reloadGridsData();
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
    private org.openswing.swing.client.ExportButton exportButton2;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.client.GridControl gridData;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn1;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn10;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    private org.openswing.swing.table.columns.client.TextColumn textColumn8;
    // End of variables declaration//GEN-END:variables
}
