package com.jswitch.siniestros.vista.detalle;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultGridFrame;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.Emergencia;
import java.awt.Color;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author bc
 */
public class DetalleSiniestroGridFrame extends DefaultGridFrame {

    public DetalleSiniestroGridFrame() {
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
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn10 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn11 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn12 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn3 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn4 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn5 = new org.openswing.swing.table.columns.client.DateColumn();
        decimalColumn6 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn11 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn12 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn13 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn14 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn15 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn16 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn17 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn18 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn19 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn20 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn21 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn22 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn23 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn24 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn25 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn26 = new org.openswing.swing.table.columns.client.DecimalColumn();
        auditoria = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn1 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn2 = new org.openswing.swing.table.columns.client.DateColumn();

        setPreferredSize(new java.awt.Dimension(700, 540));

        gridData.setExportButton(exportButton1);
        gridData.setFilterButton(filterButton1);
        gridData.setFunctionId("detalleSinGrid");
        gridData.setInsertRowsOnTop(false);
        gridData.setMaxSortedColumns(3);
        gridData.setNavBar(navigatorBar1);
        gridData.setReloadButton(reloadButton1);
        gridData.setSearchAdditionalRows(true);
        gridData.setValueObjectClassName(Emergencia.class.getName());

        decimalColumn1.setColumnFilterable(true);
        decimalColumn1.setColumnName("id");
        decimalColumn1.setColumnRequired(false);
        decimalColumn1.setColumnSortable(true);
        decimalColumn1.setGrouping(false);
        decimalColumn1.setPreferredWidth(40);
        gridData.getColumnContainer().add(decimalColumn1);

        textColumn9.setColumnFilterable(true);
        textColumn9.setColumnName("etapaSiniestro.estatusSiniestro.nombre");
        textColumn9.setColumnRequired(false);
        textColumn9.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn9);

        textColumn10.setColumnFilterable(true);
        textColumn10.setColumnName("etapaSiniestro.nombre");
        textColumn10.setColumnRequired(false);
        textColumn10.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn10);

        textColumn8.setColumnFilterable(true);
        textColumn8.setColumnName("siniestro.numero");
        textColumn8.setColumnRequired(false);
        textColumn8.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn8);

        textColumn11.setColumnFilterable(true);
        textColumn11.setColumnName("tipoDetalle");
        textColumn11.setColumnRequired(false);
        textColumn11.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn11);

        textColumn4.setColumnFilterable(true);
        textColumn4.setColumnName("personaPago.rif.rif");
        textColumn4.setColumnRequired(false);
        textColumn4.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn4);

        textColumn1.setColumnFilterable(true);
        textColumn1.setColumnName("personaPago.nombreLargo");
        textColumn1.setColumnRequired(false);
        textColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn1);

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("tipoPersona.nombre");
        textColumn2.setColumnRequired(false);
        textColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn2);

        textColumn3.setColumnFilterable(true);
        textColumn3.setColumnName("tratamientoEfectuado");
        textColumn3.setColumnRequired(false);
        textColumn3.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn3);

        textColumn6.setColumnFilterable(true);
        textColumn6.setColumnName("tipoSiniestro.nombre");
        textColumn6.setColumnRequired(false);
        textColumn6.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn6);

        textColumn7.setColumnFilterable(true);
        textColumn7.setColumnName("tipoEnfermedad");
        textColumn7.setColumnRequired(false);
        textColumn7.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn7);

        textColumn12.setColumnFilterable(true);
        textColumn12.setColumnName("ramo.nombre");
        textColumn12.setColumnRequired(false);
        textColumn12.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn12);

        dateColumn3.setColumnFilterable(true);
        dateColumn3.setColumnName("fechaPagado");
        dateColumn3.setColumnRequired(false);
        dateColumn3.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn3);

        dateColumn4.setColumnFilterable(true);
        dateColumn4.setColumnName("fechaVencimiento");
        dateColumn4.setColumnRequired(false);
        dateColumn4.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn4);

        dateColumn5.setColumnFilterable(true);
        dateColumn5.setColumnName("fechaLiquidado");
        dateColumn5.setColumnRequired(false);
        dateColumn5.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn5);

        decimalColumn6.setColumnFilterable(true);
        decimalColumn6.setColumnName("sumaDetalle.cantidadFacturas");
        decimalColumn6.setColumnRequired(false);
        decimalColumn6.setColumnSortable(true);
        gridData.getColumnContainer().add(decimalColumn6);

        decimalColumn11.setColumnFilterable(true);
        decimalColumn11.setColumnName("sumaDetalle.montoRetencionTM");
        decimalColumn11.setColumnRequired(false);
        decimalColumn11.setColumnSortable(true);
        decimalColumn11.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn11);

        decimalColumn12.setColumnFilterable(true);
        decimalColumn12.setColumnName("sumaDetalle.baseIslr");
        decimalColumn12.setColumnRequired(false);
        decimalColumn12.setColumnSortable(true);
        decimalColumn12.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn12);

        decimalColumn13.setColumnFilterable(true);
        decimalColumn13.setColumnName("sumaDetalle.montoRetencionIslr");
        decimalColumn13.setColumnRequired(false);
        decimalColumn13.setColumnSortable(true);
        decimalColumn13.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn13);

        decimalColumn14.setColumnFilterable(true);
        decimalColumn14.setColumnName("sumaDetalle.baseIva");
        decimalColumn14.setColumnRequired(false);
        decimalColumn14.setColumnSortable(true);
        decimalColumn14.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn14);

        decimalColumn15.setColumnFilterable(true);
        decimalColumn15.setColumnName("sumaDetalle.montoIva");
        decimalColumn15.setColumnRequired(false);
        decimalColumn15.setColumnSortable(true);
        decimalColumn15.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn15);

        decimalColumn16.setColumnFilterable(true);
        decimalColumn16.setColumnName("sumaDetalle.montoRetencionIva");
        decimalColumn16.setColumnRequired(false);
        decimalColumn16.setColumnSortable(true);
        decimalColumn16.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn16);

        decimalColumn17.setColumnFilterable(true);
        decimalColumn17.setColumnName("sumaDetalle.montoRetencionProntoPago");
        decimalColumn17.setColumnRequired(false);
        decimalColumn17.setColumnSortable(true);
        decimalColumn17.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn17);

        decimalColumn18.setColumnFilterable(true);
        decimalColumn18.setColumnName("sumaDetalle.montoRetencionDeducible");
        decimalColumn18.setColumnRequired(false);
        decimalColumn18.setColumnSortable(true);
        decimalColumn18.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn18);

        decimalColumn19.setColumnFilterable(true);
        decimalColumn19.setColumnName("sumaDetalle.gastosClinicos");
        decimalColumn19.setColumnRequired(false);
        decimalColumn19.setColumnSortable(true);
        decimalColumn19.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn19);

        decimalColumn20.setColumnFilterable(true);
        decimalColumn20.setColumnName("sumaDetalle.honorariosMedicos");
        decimalColumn20.setColumnRequired(false);
        decimalColumn20.setColumnSortable(true);
        decimalColumn20.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn20);

        decimalColumn21.setColumnFilterable(true);
        decimalColumn21.setColumnName("sumaDetalle.montoNoAmparado");
        decimalColumn21.setColumnRequired(false);
        decimalColumn21.setColumnSortable(true);
        decimalColumn21.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn21);

        decimalColumn22.setColumnFilterable(true);
        decimalColumn22.setColumnName("sumaDetalle.montoAmparado");
        decimalColumn22.setColumnRequired(false);
        decimalColumn22.setColumnSortable(true);
        decimalColumn22.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn22);

        decimalColumn23.setColumnFilterable(true);
        decimalColumn23.setColumnName("sumaDetalle.totalFacturado");
        decimalColumn23.setColumnRequired(false);
        decimalColumn23.setColumnSortable(true);
        decimalColumn23.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn23);

        decimalColumn24.setColumnFilterable(true);
        decimalColumn24.setColumnName("sumaDetalle.totalLiquidado");
        decimalColumn24.setColumnRequired(false);
        decimalColumn24.setColumnSortable(true);
        decimalColumn24.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn24);

        decimalColumn25.setColumnFilterable(true);
        decimalColumn25.setColumnName("sumaDetalle.totalRetenido");
        decimalColumn25.setColumnRequired(false);
        decimalColumn25.setColumnSortable(true);
        decimalColumn25.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn25);

        decimalColumn26.setColumnFilterable(true);
        decimalColumn26.setColumnName("sumaDetalle.totalACancelar");
        decimalColumn26.setColumnRequired(false);
        decimalColumn26.setColumnSortable(true);
        decimalColumn26.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn26);

        auditoria.setColumnFilterable(true);
        auditoria.setColumnName("auditoria.usuarioInsert");
        auditoria.setColumnRequired(false);
        auditoria.setColumnSortable(true);
        gridData.getColumnContainer().add(auditoria);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void inicializar(GridDataLocator gridDataLocator, GridController gridController, String valueObjectClassName, String title, boolean addToMDIFrame) {
        initComponents();
        setTitle(title);
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
    private org.openswing.swing.table.columns.client.TextColumn auditoria;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn1;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn2;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn3;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn4;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn5;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn11;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn12;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn13;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn14;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn15;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn16;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn17;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn18;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn19;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn20;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn21;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn22;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn23;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn24;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn25;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn26;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
    private org.openswing.swing.client.ExportButton exportButton1;
    private org.openswing.swing.client.FilterButton filterButton1;
    private org.openswing.swing.client.GridControl gridData;
    private javax.swing.JPanel jPanel1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn10;
    private org.openswing.swing.table.columns.client.TextColumn textColumn11;
    private org.openswing.swing.table.columns.client.TextColumn textColumn12;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    private org.openswing.swing.table.columns.client.TextColumn textColumn8;
    private org.openswing.swing.table.columns.client.TextColumn textColumn9;
    // End of variables declaration//GEN-END:variables
}
