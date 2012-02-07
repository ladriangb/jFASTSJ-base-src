package com.jswitch.pagos.vista;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultGridFrame;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author bc
 */
public class OrdenDePagoGridFrame extends DefaultGridFrame {

    public OrdenDePagoGridFrame() {
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
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn2 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn3 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn4 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn6 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn7 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn8 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn9 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn10 = new org.openswing.swing.table.columns.client.DecimalColumn();
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
        auditoria = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn1 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn2 = new org.openswing.swing.table.columns.client.DateTimeColumn();

        setResizable(true);
        setTitle("Ordenes de Pago");
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
        gridData.setFunctionId("OrdenDePagoGrid");
        gridData.setInsertRowsOnTop(false);
        gridData.setMaxSortedColumns(3);
        gridData.setNavBar(navigatorBar1);
        gridData.setReloadButton(reloadButton1);
        gridData.setSearchAdditionalRows(true);
        gridData.setValueObjectClassName(OrdenDePago.class.getName());

        decimalColumn1.setColumnFilterable(true);
        decimalColumn1.setColumnName("id");
        decimalColumn1.setColumnRequired(false);
        decimalColumn1.setColumnSortable(true);
        decimalColumn1.setGrouping(false);
        decimalColumn1.setPreferredWidth(40);
        decimalColumn1.setSortVersus(org.openswing.swing.util.java.Consts.DESC_SORTED);
        gridData.getColumnContainer().add(decimalColumn1);

        textColumn8.setColumnFilterable(true);
        textColumn8.setColumnName("estatusPago");
        textColumn8.setColumnRequired(false);
        textColumn8.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn8);

        textColumn6.setColumnFilterable(true);
        textColumn6.setColumnName("personaPago.rif.rif");
        textColumn6.setColumnRequired(false);
        textColumn6.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn6);

        textColumn7.setColumnFilterable(true);
        textColumn7.setColumnName("personaPago.nombreLargo");
        textColumn7.setColumnRequired(false);
        textColumn7.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn7);

        textColumn3.setColumnFilterable(true);
        textColumn3.setColumnName("numeroOrden");
        textColumn3.setColumnRequired(false);
        textColumn3.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn3);

        textColumn1.setColumnFilterable(true);
        textColumn1.setColumnName("referencia");
        textColumn1.setColumnRequired(false);
        textColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn1);

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("codigoSIGECOF");
        textColumn2.setColumnRequired(false);
        textColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn2);

        decimalColumn2.setColumnFilterable(true);
        decimalColumn2.setColumnName("sumaOrden.cantidadDetalles");
        decimalColumn2.setColumnRequired(false);
        decimalColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(decimalColumn2);

        decimalColumn3.setColumnFilterable(true);
        decimalColumn3.setColumnName("sumaOrden.cantidadFacturas");
        decimalColumn3.setColumnRequired(false);
        decimalColumn3.setColumnSortable(true);
        gridData.getColumnContainer().add(decimalColumn3);

        decimalColumn4.setColumnFilterable(true);
        decimalColumn4.setColumnName("sumaOrden.numeroSiniestrosTitular");
        decimalColumn4.setColumnRequired(false);
        decimalColumn4.setColumnSortable(true);
        gridData.getColumnContainer().add(decimalColumn4);

        decimalColumn5.setColumnFilterable(true);
        decimalColumn5.setColumnName("sumaOrden.numeroSiniestrosFamiliar");
        decimalColumn5.setColumnRequired(false);
        decimalColumn5.setColumnSortable(true);
        gridData.getColumnContainer().add(decimalColumn5);

        decimalColumn6.setColumnFilterable(true);
        decimalColumn6.setColumnName("sumaOrden.montoTitulares");
        decimalColumn6.setColumnRequired(false);
        decimalColumn6.setColumnSortable(true);
        decimalColumn6.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn6);

        decimalColumn7.setColumnFilterable(true);
        decimalColumn7.setColumnName("sumaOrden.montoFamiliares");
        decimalColumn7.setColumnRequired(false);
        decimalColumn7.setColumnSortable(true);
        decimalColumn7.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn7);

        decimalColumn8.setColumnFilterable(true);
        decimalColumn8.setColumnName("sumaOrden.montoRetencionTM");
        decimalColumn8.setColumnRequired(false);
        decimalColumn8.setColumnSortable(true);
        decimalColumn8.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn8);

        decimalColumn9.setColumnFilterable(true);
        decimalColumn9.setColumnName("sumaOrden.baseIslr");
        decimalColumn9.setColumnRequired(false);
        decimalColumn9.setColumnSortable(true);
        decimalColumn9.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn9);

        decimalColumn10.setColumnFilterable(true);
        decimalColumn10.setColumnName("sumaOrden.montoRetencionIslr");
        decimalColumn10.setColumnRequired(false);
        decimalColumn10.setColumnSortable(true);
        decimalColumn10.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn10);

        decimalColumn11.setColumnFilterable(true);
        decimalColumn11.setColumnName("sumaOrden.baseIva");
        decimalColumn11.setColumnRequired(false);
        decimalColumn11.setColumnSortable(true);
        decimalColumn11.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn11);

        decimalColumn12.setColumnFilterable(true);
        decimalColumn12.setColumnName("sumaOrden.montoIva");
        decimalColumn12.setColumnRequired(false);
        decimalColumn12.setColumnSortable(true);
        decimalColumn12.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn12);

        decimalColumn13.setColumnFilterable(true);
        decimalColumn13.setColumnName("sumaOrden.montoRetencionIva");
        decimalColumn13.setColumnRequired(false);
        decimalColumn13.setColumnSortable(true);
        decimalColumn13.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn13);

        decimalColumn14.setColumnFilterable(true);
        decimalColumn14.setColumnName("sumaOrden.montoRetencionProntoPago");
        decimalColumn14.setColumnRequired(false);
        decimalColumn14.setColumnSortable(true);
        decimalColumn14.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn14);

        decimalColumn15.setColumnFilterable(true);
        decimalColumn15.setColumnName("sumaOrden.montoRetencionDeducible");
        decimalColumn15.setColumnRequired(false);
        decimalColumn15.setColumnSortable(true);
        decimalColumn15.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn15);

        decimalColumn16.setColumnFilterable(true);
        decimalColumn16.setColumnName("sumaOrden.gastosClinicos");
        decimalColumn16.setColumnRequired(false);
        decimalColumn16.setColumnSortable(true);
        decimalColumn16.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn16);

        decimalColumn17.setColumnFilterable(true);
        decimalColumn17.setColumnName("sumaOrden.honorariosMedicos");
        decimalColumn17.setColumnRequired(false);
        decimalColumn17.setColumnSortable(true);
        decimalColumn17.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn17);

        decimalColumn18.setColumnFilterable(true);
        decimalColumn18.setColumnName("sumaOrden.montoNoAmparado");
        decimalColumn18.setColumnRequired(false);
        decimalColumn18.setColumnSortable(true);
        decimalColumn18.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn18);

        decimalColumn19.setColumnFilterable(true);
        decimalColumn19.setColumnName("sumaOrden.montoAmparado");
        decimalColumn19.setColumnRequired(false);
        decimalColumn19.setColumnSortable(true);
        decimalColumn19.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn19);

        decimalColumn20.setColumnFilterable(true);
        decimalColumn20.setColumnName("sumaOrden.totalFacturado");
        decimalColumn20.setColumnRequired(false);
        decimalColumn20.setColumnSortable(true);
        decimalColumn20.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn20);

        decimalColumn21.setColumnFilterable(true);
        decimalColumn21.setColumnName("sumaOrden.totalLiquidado");
        decimalColumn21.setColumnRequired(false);
        decimalColumn21.setColumnSortable(true);
        decimalColumn21.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn21);

        decimalColumn22.setColumnFilterable(true);
        decimalColumn22.setColumnName("sumaOrden.totalRetenido");
        decimalColumn22.setColumnRequired(false);
        decimalColumn22.setColumnSortable(true);
        decimalColumn22.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn22);

        decimalColumn23.setColumnFilterable(true);
        decimalColumn23.setColumnName("sumaOrden.totalACancelar");
        decimalColumn23.setColumnRequired(false);
        decimalColumn23.setColumnSortable(true);
        decimalColumn23.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn23);

        auditoria.setColumnFilterable(true);
        auditoria.setColumnName("auditoria.usuarioInsert");
        auditoria.setColumnRequired(false);
        auditoria.setColumnSortable(true);
        gridData.getColumnContainer().add(auditoria);

        dateTimeColumn1.setColumnFilterable(true);
        dateTimeColumn1.setColumnName("auditoria.fechaInsert");
        dateTimeColumn1.setColumnRequired(false);
        dateTimeColumn1.setColumnSortable(true);
        dateTimeColumn1.setSortVersus(org.openswing.swing.util.java.Consts.DESC_SORTED);
        gridData.getColumnContainer().add(dateTimeColumn1);

        textColumn5.setColumnFilterable(true);
        textColumn5.setColumnName("auditoria.usuarioUpdate");
        textColumn5.setColumnRequired(false);
        textColumn5.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn5);

        dateTimeColumn2.setColumnFilterable(true);
        dateTimeColumn2.setColumnName("auditoria.fechaUpdate");
        dateTimeColumn2.setColumnRequired(false);
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
    private org.openswing.swing.table.columns.client.TextColumn auditoria;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn10;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn11;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn12;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn13;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn14;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn15;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn16;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn17;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn18;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn19;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn20;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn21;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn22;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn23;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn3;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn4;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn8;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn9;
    private org.openswing.swing.client.ExportButton exportButton1;
    private org.openswing.swing.client.FilterButton filterButton1;
    private org.openswing.swing.client.GridControl gridData;
    private javax.swing.JPanel jPanel1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    private org.openswing.swing.table.columns.client.TextColumn textColumn8;
    // End of variables declaration//GEN-END:variables
}
