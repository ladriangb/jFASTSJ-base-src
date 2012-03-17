package com.jswitch.asegurados.vista;


import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultGridFrame;
import com.jswitch.vistasbd.Agotamiento;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author bc
 */
public class AgotamientoGridFrame extends DefaultGridFrame {

    public AgotamientoGridFrame() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        exportButton1 = new org.openswing.swing.client.ExportButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        gridData = new org.openswing.swing.client.GridControl();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn11 = new org.openswing.swing.table.columns.client.TextColumn();
        currencyColumn4 = new org.openswing.swing.table.columns.client.CurrencyColumn();
        currencyColumn5 = new org.openswing.swing.table.columns.client.CurrencyColumn();
        integerColumn1 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn10 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        currencyColumn1 = new org.openswing.swing.table.columns.client.CurrencyColumn();
        currencyColumn2 = new org.openswing.swing.table.columns.client.CurrencyColumn();
        currencyColumn3 = new org.openswing.swing.table.columns.client.CurrencyColumn();
        dateColumn1 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn2 = new org.openswing.swing.table.columns.client.DateColumn();

        setTitle("Agotamiento");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reloadButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(navigatorBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        gridData.setExportButton(exportButton1);
        gridData.setFunctionId("AgotamientoGrid");
        gridData.setInsertRowsOnTop(false);
        gridData.setMaxSortedColumns(3);
        gridData.setNavBar(navigatorBar1);
        gridData.setReloadButton(reloadButton1);
        gridData.setSearchAdditionalRows(true);
        gridData.setValueObjectClassName(Agotamiento.class.getName());

        textColumn1.setColumnFilterable(true);
        textColumn1.setColumnName("asegurado.persona.rif.rif");
        textColumn1.setColumnRequired(false);
        textColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn1);

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("asegurado.persona.nombreLargo");
        textColumn2.setColumnRequired(false);
        textColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn2);

        textColumn4.setColumnFilterable(true);
        textColumn4.setColumnName("diagnostico.nombre");
        textColumn4.setColumnRequired(false);
        textColumn4.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn4);

        textColumn5.setColumnFilterable(true);
        textColumn5.setColumnName("diagnostico.especialidad.nombre");
        textColumn5.setColumnRequired(false);
        textColumn5.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn5);

        textColumn11.setColumnFilterable(true);
        textColumn11.setColumnName("diagnostico.especialidad.ramo.nombre");
        textColumn11.setColumnRequired(false);
        textColumn11.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn11);

        currencyColumn4.setColumnFilterable(true);
        currencyColumn4.setColumnName("montoPagado");
        currencyColumn4.setCurrencySymbol("VEB");
        gridData.getColumnContainer().add(currencyColumn4);

        currencyColumn5.setColumnFilterable(true);
        currencyColumn5.setColumnName("montoPendiente");
        currencyColumn5.setCurrencySymbol("VEB");
        gridData.getColumnContainer().add(currencyColumn5);

        integerColumn1.setColumnFilterable(true);
        integerColumn1.setColumnName("ayo");
        integerColumn1.setColumnRequired(false);
        integerColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(integerColumn1);

        textColumn10.setColumnFilterable(true);
        textColumn10.setColumnName("asegurado.certificado.poliza.numero");
        textColumn10.setColumnRequired(false);
        textColumn10.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn10);

        textColumn3.setColumnFilterable(true);
        textColumn3.setColumnName("asegurado.certificado.titular.persona.rif.rif");
        textColumn3.setColumnRequired(false);
        textColumn3.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn3);

        textColumn6.setColumnFilterable(true);
        textColumn6.setColumnName("asegurado.certificado.titular.persona.nombreLargo");
        textColumn6.setColumnRequired(false);
        textColumn6.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn6);

        textColumn7.setColumnFilterable(true);
        textColumn7.setColumnName("asegurado.parentesco.nombre");
        textColumn7.setColumnRequired(false);
        textColumn7.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn7);

        textColumn8.setColumnFilterable(true);
        textColumn8.setColumnName("asegurado.plazoEspera.nombre");
        textColumn8.setColumnRequired(false);
        textColumn8.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn8);

        textColumn9.setColumnFilterable(true);
        textColumn9.setColumnName("asegurado.plan.nombre");
        textColumn9.setColumnRequired(false);
        textColumn9.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn9);

        currencyColumn1.setColumnFilterable(true);
        currencyColumn1.setColumnName("asegurado.primaTotal");
        currencyColumn1.setColumnRequired(false);
        currencyColumn1.setColumnSortable(true);
        currencyColumn1.setCurrencySymbol("VEB");
        gridData.getColumnContainer().add(currencyColumn1);

        currencyColumn2.setColumnFilterable(true);
        currencyColumn2.setColumnName("asegurado.primaAporte");
        currencyColumn2.setColumnRequired(false);
        currencyColumn2.setColumnSortable(true);
        currencyColumn2.setCurrencySymbol("VEB");
        gridData.getColumnContainer().add(currencyColumn2);

        currencyColumn3.setColumnFilterable(true);
        currencyColumn3.setColumnName("asegurado.primaAsegurado");
        currencyColumn3.setColumnRequired(false);
        currencyColumn3.setColumnSortable(true);
        currencyColumn3.setCurrencySymbol("VEB");
        gridData.getColumnContainer().add(currencyColumn3);

        dateColumn1.setColumnFilterable(true);
        dateColumn1.setColumnName("asegurado.fechaIngresoFondo");
        dateColumn1.setColumnRequired(false);
        dateColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn1);

        dateColumn2.setColumnFilterable(true);
        dateColumn2.setColumnName("asegurado.fechaEgresoFondo");
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
    private org.openswing.swing.table.columns.client.CurrencyColumn currencyColumn1;
    private org.openswing.swing.table.columns.client.CurrencyColumn currencyColumn2;
    private org.openswing.swing.table.columns.client.CurrencyColumn currencyColumn3;
    private org.openswing.swing.table.columns.client.CurrencyColumn currencyColumn4;
    private org.openswing.swing.table.columns.client.CurrencyColumn currencyColumn5;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn1;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn2;
    private org.openswing.swing.client.ExportButton exportButton1;
    private org.openswing.swing.client.GridControl gridData;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn1;
    private javax.swing.JPanel jPanel1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
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
    // End of variables declaration//GEN-END:variables
}
