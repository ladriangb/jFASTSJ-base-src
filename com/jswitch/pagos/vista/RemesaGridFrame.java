package com.jswitch.pagos.vista;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultGridFrame;
import com.jswitch.fas.modelo.Dominios;
import com.jswitch.pagos.modelo.maestra.Remesa;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author bc
 */
public class RemesaGridFrame extends DefaultGridFrame {

    public RemesaGridFrame() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        exportButton1 = new org.openswing.swing.client.ExportButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        gridData = new org.openswing.swing.client.GridControl();
        decimalColumn1 = new org.openswing.swing.table.columns.client.DecimalColumn();
        comboColumn1 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        comboColumn3 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        comboColumn2 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn10 = new org.openswing.swing.table.columns.client.TextColumn();
        integerColumn1 = new org.openswing.swing.table.columns.client.IntegerColumn();
        integerColumn2 = new org.openswing.swing.table.columns.client.IntegerColumn();
        dateColumn1 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn2 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn3 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn4 = new org.openswing.swing.table.columns.client.DateColumn();
        decimalColumn24 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn25 = new org.openswing.swing.table.columns.client.DecimalColumn();
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

        setTitle("Remesa");
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
                .addContainerGap(406, Short.MAX_VALUE))
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
        gridData.setFunctionId("RemesaGrid");
        gridData.setInsertRowsOnTop(false);
        gridData.setMaxSortedColumns(3);
        gridData.setNavBar(navigatorBar1);
        gridData.setReloadButton(reloadButton1);
        gridData.setSearchAdditionalRows(true);
        gridData.setValueObjectClassName(Remesa.class.getName());

        decimalColumn1.setColumnFilterable(true);
        decimalColumn1.setColumnName("id");
        decimalColumn1.setColumnRequired(false);
        decimalColumn1.setColumnSortable(true);
        decimalColumn1.setPreferredWidth(40);
        decimalColumn1.setSortVersus(org.openswing.swing.util.java.Consts.DESC_SORTED);
        gridData.getColumnContainer().add(decimalColumn1);

        comboColumn1.setColumnFilterable(true);
        comboColumn1.setColumnName("estatusPago");
        comboColumn1.setColumnRequired(false);
        comboColumn1.setColumnSortable(true);
        comboColumn1.setDomainId(Dominios.EstatusPago().getDomainId());
        gridData.getColumnContainer().add(comboColumn1);

        textColumn3.setColumnFilterable(true);
        textColumn3.setColumnName("refLot");
        textColumn3.setColumnRequired(false);
        textColumn3.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn3);

        textColumn7.setColumnFilterable(true);
        textColumn7.setColumnName("detalle");
        textColumn7.setColumnRequired(false);
        textColumn7.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn7);

        comboColumn3.setColumnFilterable(true);
        comboColumn3.setColumnName("tipoPago");
        comboColumn3.setColumnRequired(false);
        comboColumn3.setColumnSortable(true);
        comboColumn3.setDomainId(Dominios.TipoPago().getDomainId());
        gridData.getColumnContainer().add(comboColumn3);

        textColumn1.setColumnFilterable(true);
        textColumn1.setColumnName("numeroCuentaDebitar");
        textColumn1.setColumnRequired(false);
        textColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn1);

        comboColumn2.setColumnFilterable(true);
        comboColumn2.setColumnName("duracionCheque");
        comboColumn2.setColumnRequired(false);
        comboColumn2.setColumnSortable(true);
        comboColumn2.setDomainId(Dominios.DuracionCheque().getDomainId());
        gridData.getColumnContainer().add(comboColumn2);

        textColumn10.setColumnFilterable(true);
        textColumn10.setColumnName("numNegociacion");
        textColumn10.setColumnRequired(false);
        textColumn10.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn10);

        integerColumn1.setColumnFilterable(true);
        integerColumn1.setColumnName("numRefCre");
        integerColumn1.setColumnRequired(false);
        integerColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(integerColumn1);

        integerColumn2.setColumnFilterable(true);
        integerColumn2.setColumnName("numRefDeb");
        integerColumn2.setColumnRequired(false);
        integerColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(integerColumn2);

        dateColumn1.setColumnFilterable(true);
        dateColumn1.setColumnName("fechaEnvio");
        dateColumn1.setColumnRequired(false);
        dateColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn1);

        dateColumn2.setColumnFilterable(true);
        dateColumn2.setColumnName("fechaPagado");
        dateColumn2.setColumnRequired(false);
        dateColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn2);

        dateColumn3.setColumnFilterable(true);
        dateColumn3.setColumnName("fechaPropuestaPago");
        dateColumn3.setColumnRequired(false);
        dateColumn3.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn3);

        dateColumn4.setColumnFilterable(true);
        dateColumn4.setColumnName("fechaValor");
        dateColumn4.setColumnRequired(false);
        dateColumn4.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn4);

        decimalColumn24.setColumnFilterable(true);
        decimalColumn24.setColumnName("sumaRemesa.cantidadOrdenes");
        decimalColumn24.setColumnRequired(false);
        decimalColumn24.setColumnSortable(true);
        gridData.getColumnContainer().add(decimalColumn24);

        decimalColumn25.setColumnFilterable(true);
        decimalColumn25.setColumnName("sumaRemesa.cantidadDetalles");
        decimalColumn25.setColumnRequired(false);
        decimalColumn25.setColumnSortable(true);
        gridData.getColumnContainer().add(decimalColumn25);

        decimalColumn3.setColumnFilterable(true);
        decimalColumn3.setColumnName("sumaRemesa.cantidadFacturas");
        decimalColumn3.setColumnRequired(false);
        decimalColumn3.setColumnSortable(true);
        gridData.getColumnContainer().add(decimalColumn3);

        decimalColumn4.setColumnFilterable(true);
        decimalColumn4.setColumnName("sumaRemesa.numeroSiniestrosTitular");
        decimalColumn4.setColumnRequired(false);
        decimalColumn4.setColumnSortable(true);
        gridData.getColumnContainer().add(decimalColumn4);

        decimalColumn5.setColumnFilterable(true);
        decimalColumn5.setColumnName("sumaRemesa.numeroSiniestrosFamiliar");
        decimalColumn5.setColumnRequired(false);
        decimalColumn5.setColumnSortable(true);
        gridData.getColumnContainer().add(decimalColumn5);

        decimalColumn6.setColumnFilterable(true);
        decimalColumn6.setColumnName("sumaRemesa.montoTitulares");
        decimalColumn6.setColumnRequired(false);
        decimalColumn6.setColumnSortable(true);
        decimalColumn6.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn6);

        decimalColumn7.setColumnFilterable(true);
        decimalColumn7.setColumnName("sumaRemesa.montoFamiliares");
        decimalColumn7.setColumnRequired(false);
        decimalColumn7.setColumnSortable(true);
        decimalColumn7.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn7);

        decimalColumn8.setColumnFilterable(true);
        decimalColumn8.setColumnName("sumaRemesa.montoRetencionTM");
        decimalColumn8.setColumnRequired(false);
        decimalColumn8.setColumnSortable(true);
        decimalColumn8.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn8);

        decimalColumn9.setColumnFilterable(true);
        decimalColumn9.setColumnName("sumaRemesa.baseIslr");
        decimalColumn9.setColumnRequired(false);
        decimalColumn9.setColumnSortable(true);
        decimalColumn9.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn9);

        decimalColumn10.setColumnFilterable(true);
        decimalColumn10.setColumnName("sumaRemesa.montoRetencionIslr");
        decimalColumn10.setColumnRequired(false);
        decimalColumn10.setColumnSortable(true);
        decimalColumn10.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn10);

        decimalColumn11.setColumnFilterable(true);
        decimalColumn11.setColumnName("sumaRemesa.baseIva");
        decimalColumn11.setColumnRequired(false);
        decimalColumn11.setColumnSortable(true);
        decimalColumn11.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn11);

        decimalColumn12.setColumnFilterable(true);
        decimalColumn12.setColumnName("sumaRemesa.montoIva");
        decimalColumn12.setColumnRequired(false);
        decimalColumn12.setColumnSortable(true);
        decimalColumn12.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn12);

        decimalColumn13.setColumnFilterable(true);
        decimalColumn13.setColumnName("sumaRemesa.montoRetencionIva");
        decimalColumn13.setColumnRequired(false);
        decimalColumn13.setColumnSortable(true);
        decimalColumn13.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn13);

        decimalColumn14.setColumnFilterable(true);
        decimalColumn14.setColumnName("sumaRemesa.montoRetencionProntoPago");
        decimalColumn14.setColumnRequired(false);
        decimalColumn14.setColumnSortable(true);
        decimalColumn14.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn14);

        decimalColumn15.setColumnFilterable(true);
        decimalColumn15.setColumnName("sumaRemesa.montoRetencionDeducible");
        decimalColumn15.setColumnRequired(false);
        decimalColumn15.setColumnSortable(true);
        decimalColumn15.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn15);

        decimalColumn16.setColumnFilterable(true);
        decimalColumn16.setColumnName("sumaRemesa.gastosClinicos");
        decimalColumn16.setColumnRequired(false);
        decimalColumn16.setColumnSortable(true);
        decimalColumn16.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn16);

        decimalColumn17.setColumnFilterable(true);
        decimalColumn17.setColumnName("sumaRemesa.honorariosMedicos");
        decimalColumn17.setColumnRequired(false);
        decimalColumn17.setColumnSortable(true);
        decimalColumn17.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn17);

        decimalColumn18.setColumnFilterable(true);
        decimalColumn18.setColumnName("sumaRemesa.montoNoAmparado");
        decimalColumn18.setColumnRequired(false);
        decimalColumn18.setColumnSortable(true);
        decimalColumn18.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn18);

        decimalColumn19.setColumnFilterable(true);
        decimalColumn19.setColumnName("sumaRemesa.montoAmparado");
        decimalColumn19.setColumnRequired(false);
        decimalColumn19.setColumnSortable(true);
        decimalColumn19.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn19);

        decimalColumn20.setColumnFilterable(true);
        decimalColumn20.setColumnName("sumaRemesa.totalFacturado");
        decimalColumn20.setColumnRequired(false);
        decimalColumn20.setColumnSortable(true);
        decimalColumn20.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn20);

        decimalColumn21.setColumnFilterable(true);
        decimalColumn21.setColumnName("sumaRemesa.totalLiquidado");
        decimalColumn21.setColumnRequired(false);
        decimalColumn21.setColumnSortable(true);
        decimalColumn21.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn21);

        decimalColumn22.setColumnFilterable(true);
        decimalColumn22.setColumnName("sumaRemesa.totalRetenido");
        decimalColumn22.setColumnRequired(false);
        decimalColumn22.setColumnSortable(true);
        decimalColumn22.setDecimals(2);
        gridData.getColumnContainer().add(decimalColumn22);

        decimalColumn23.setColumnFilterable(true);
        decimalColumn23.setColumnName("sumaRemesa.totalACancelar");
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
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn1;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn2;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn3;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn1;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn2;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn3;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn4;
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
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn20;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn21;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn22;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn23;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn24;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn25;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn3;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn4;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn8;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn9;
    private org.openswing.swing.client.ExportButton exportButton1;
    private org.openswing.swing.client.GridControl gridData;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn1;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn2;
    private javax.swing.JPanel jPanel1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn10;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    // End of variables declaration//GEN-END:variables
}
