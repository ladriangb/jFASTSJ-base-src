package com.jswitch.pagos.vista;

import com.jswitch.base.controlador.documentosAnexos.TipoDocumentoLookupController;
import com.jswitch.base.controlador.util.DefaultDocumentosAnexosGridController;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.entidades.Documento;
import com.jswitch.base.modelo.entidades.NotaTecnica;
import com.jswitch.base.modelo.entidades.Observacion;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.configuracion.controlador.PartidaPresupuestariaGridInternalController;
import com.jswitch.fas.modelo.Dominios;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.pagos.controlador.DetalleSiniestroLiquidadosGridInternalController;
import com.jswitch.pagos.controlador.OrdenDePagoDetailFrameController;
import com.jswitch.pagos.controlador.RemesaDetailFrameController;
import com.jswitch.pagos.controlador.RemesaLookupController;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.modelo.maestra.Remesa;
import com.jswitch.persona.controlador.CuentaBancariaPorPersonaLookupController;
import com.jswitch.persona.controlador.PersonaLookupControllerPorNombre;
import com.jswitch.persona.controlador.PersonasDetailController;
import com.jswitch.persona.modelo.maestra.Rif;
import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.siniestros.controlador.detalle.reportes.ReporteGridInternalController;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.vistasbd.SumaPartida;
import java.awt.event.ActionListener;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author Luis Adrian Gonzalez Benavides
 */
public class OrdenDePagoDetailFrame extends DefaultDetailFrame {

    private PartidaPresupuestariaGridInternalController partidaPresupuestaria;
    private DetalleSiniestroLiquidadosGridInternalController detalleSiniestros;
    protected DefaultDocumentosAnexosGridController controllerDocumentosAnexosX;
    protected DefaultGridInternalController controllerObservaciones;
    protected DefaultGridInternalController controllerNotasTecnicas;
    private ReporteGridInternalController controllerReportes;

    public OrdenDePagoDetailFrame() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        editButton1 = new org.openswing.swing.client.EditButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        form1 = new org.openswing.swing.form.client.Form();
        codLookupControl1 = new org.openswing.swing.client.CodLookupControl();
        dateControl1 = new org.openswing.swing.client.DateControl();
        textControl1 = new org.openswing.swing.client.TextControl();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        comboBoxControl1 = new org.openswing.swing.client.ComboBoxControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        checkBoxControl1 = new org.openswing.swing.client.CheckBoxControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        comboBoxControl2 = new org.openswing.swing.client.ComboBoxControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        codLookupControl3 = new org.openswing.swing.client.CodLookupControl();
        jPanel14 = new javax.swing.JPanel();
        gridControl5 = new org.openswing.swing.client.GridControl();
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        currencyColumn1 = new org.openswing.swing.table.columns.client.CurrencyColumn();
        checkBoxColumn2 = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn2 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        textColumn11 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn4 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        jPanel15 = new javax.swing.JPanel();
        insertButton7 = new org.openswing.swing.client.InsertButton();
        deleteButton7 = new org.openswing.swing.client.DeleteButton();
        reloadButton7 = new org.openswing.swing.client.ReloadButton();
        navigatorBar2 = new org.openswing.swing.client.NavigatorBar();
        observaciones = new javax.swing.JPanel();
        jPanelObs = new javax.swing.JPanel();
        gridControlObs = new org.openswing.swing.client.GridControl();
        decimalColumnObs = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumnObs1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumnObs2 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumnObs1 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        textColumnObs3 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumnObs2 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        jPanel13 = new javax.swing.JPanel();
        insertButtonObs = new org.openswing.swing.client.InsertButton();
        editButtonObs = new org.openswing.swing.client.EditButton();
        deleteButtonObs = new org.openswing.swing.client.DeleteButton();
        saveButtonObs = new org.openswing.swing.client.SaveButton();
        reloadButtonObs = new org.openswing.swing.client.ReloadButton();
        notasTecnicas = new javax.swing.JPanel();
        jPanelNot = new javax.swing.JPanel();
        gridControlNot = new org.openswing.swing.client.GridControl();
        decimalColumnNot = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumnNot1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumnNot2 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn1 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        textColumnNot3 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn3 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        jPanel16 = new javax.swing.JPanel();
        insertButtonNot = new org.openswing.swing.client.InsertButton();
        saveButtonNot = new org.openswing.swing.client.SaveButton();
        reloadButtonNot = new org.openswing.swing.client.ReloadButton();
        DocAnexos = new javax.swing.JPanel();
        jPanelDoc = new javax.swing.JPanel();
        gridControlDoc = new org.openswing.swing.client.GridControl();
        buttonColumnDoc = new org.openswing.swing.table.columns.client.ButtonColumn();
        decimalColumnNot1 = new org.openswing.swing.table.columns.client.DecimalColumn();
        codLookupColumnDoc = new org.openswing.swing.table.columns.client.CodLookupColumn();
        textColumnDoc = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumnDoc = new org.openswing.swing.table.columns.client.DateColumn();
        jPanel11 = new javax.swing.JPanel();
        insertButtonDoc = new org.openswing.swing.client.InsertButton();
        editButtonDoc = new org.openswing.swing.client.EditButton();
        deleteButtonDoc = new org.openswing.swing.client.DeleteButton();
        saveButtonDoc = new org.openswing.swing.client.SaveButton();
        reloadButtonDoc = new org.openswing.swing.client.ReloadButton();
        filterButtonDoc = new org.openswing.swing.client.FilterButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        reloadButton2 = new org.openswing.swing.client.ReloadButton();
        exportButton1 = new org.openswing.swing.client.ExportButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        filterButton1 = new org.openswing.swing.client.FilterButton();
        gridPartidas = new org.openswing.swing.client.GridControl();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn2 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn3 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn4 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn6 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn25 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn26 = new org.openswing.swing.table.columns.client.DecimalColumn();
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
        decimalColumn24 = new org.openswing.swing.table.columns.client.DecimalColumn();
        Reportes = new javax.swing.JPanel();
        gridReportes = new org.openswing.swing.client.GridControl();
        textColumn12 = new org.openswing.swing.table.columns.client.TextColumn();

        setTitle("Orden de Pago");

        jButton1.setText("Anular");
        jButton1.setPreferredSize(new java.awt.Dimension(33, 33));

        jButton2.setText("Liquidar");
        jButton2.setPreferredSize(new java.awt.Dimension(33, 33));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        form1.setVOClassName(OrdenDePago.class.getName());
        form1.setEditButton(editButton1);
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);

        codLookupControl1.setAttributeName("personaPago.nombreLargo");
        codLookupControl1.setEnabled(false);
        codLookupControl1.setEnabledOnEdit(false);
        codLookupControl1.setEnabledOnInsert(false);
        codLookupControl1.setLookupButtonVisible(false);
        codLookupControl1.setRequired(true);

        dateControl1.setAttributeName("fechaPagado");
        dateControl1.setEnabledOnEdit(false);
        dateControl1.setEnabledOnInsert(false);

        textControl1.setAttributeName("numeroOrden");
        textControl1.setEnabledOnEdit(false);
        textControl1.setEnabledOnInsert(false);

        labelControl1.setLabel("numeroOrden");

        labelControl2.setLabel("fechaPagado");

        labelControl3.setLabel("personaPago");

        comboBoxControl1.setAttributeName("estatusPago");
        comboBoxControl1.setDomainId(Dominios.EstatusPago().getDomainId());
        comboBoxControl1.setEnabledOnEdit(false);
        comboBoxControl1.setEnabledOnInsert(false);

        labelControl4.setLabel("estatusPago");

        checkBoxControl1.setText("autoSearch");
        checkBoxControl1.setAttributeName("autoSearch");
        checkBoxControl1.setEnabledOnEdit(false);

        textControl2.setAttributeName("referencia");
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);

        labelControl5.setLabel("referencia");

        textControl3.setAttributeName("codigoSIGECOF");
        textControl3.setEnabledOnEdit(false);
        textControl3.setEnabledOnInsert(false);

        labelControl6.setLabel("codigoSIGECOF");

        labelControl7.setLabel("tipoDetalleSiniestro");

        comboBoxControl2.setAttributeName("tipoDetalleSiniestro");
        comboBoxControl2.setDomainId(Dominios.TipoDetalleSiniestro().getDomainId());
        comboBoxControl2.setRequired(true);

        labelControl8.setLabel("remesa.numRefLot");

        codLookupControl2.setAttributeName("remesa.refLot");
        codLookupControl2.setEnabledOnEdit(false);
        codLookupControl2.setEnabledOnInsert(false);
        codLookupControl2.setLookupButtonVisible(false);

        labelControl9.setLabel("cuentaBancaria.numero");

        codLookupControl3.setAttributeName("cuentaBancaria.numero");

        javax.swing.GroupLayout form1Layout = new javax.swing.GroupLayout(form1);
        form1.setLayout(form1Layout);
        form1Layout.setHorizontalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form1Layout.createSequentialGroup()
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(checkBoxControl1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelControl4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(labelControl7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                            .addComponent(comboBoxControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelControl8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelControl1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelControl5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelControl6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelControl2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelControl3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codLookupControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                            .addComponent(dateControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                            .addComponent(textControl3, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                            .addComponent(textControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                            .addComponent(codLookupControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                            .addComponent(textControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codLookupControl3, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)))
                .addContainerGap())
        );

        form1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelControl1, labelControl2, labelControl3, labelControl4, labelControl5, labelControl6, labelControl7, labelControl8, labelControl9});

        form1Layout.setVerticalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codLookupControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codLookupControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(codLookupControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        form1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {codLookupControl1, codLookupControl2, comboBoxControl1, comboBoxControl2, dateControl1, labelControl1, labelControl2, labelControl3, labelControl4, labelControl5, labelControl6, labelControl7, labelControl8, labelControl9, textControl1, textControl2, textControl3});

        jTabbedPane1.addTab("Detalle Orden de Pago", form1);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        gridControl5.setAutoLoadData(false);
        gridControl5.setDeleteButton(deleteButton7);
        gridControl5.setNavBar(navigatorBar2);
        gridControl5.setReloadButton(reloadButton7);
        gridControl5.setValueObjectClassName(DetalleSiniestro.class.getName());

        decimalColumn5.setColumnName("id");
        decimalColumn5.setColumnRequired(false);
        decimalColumn5.setPreferredWidth(40);
        gridControl5.getColumnContainer().add(decimalColumn5);

        textColumn3.setColumnName("siniestro.asegurado.persona.nombreLargo");
        gridControl5.getColumnContainer().add(textColumn3);

        textColumn1.setColumnName("tipoDetalle");
        gridControl5.getColumnContainer().add(textColumn1);

        textColumn2.setColumnName("etapaSiniestro.nombre");
        gridControl5.getColumnContainer().add(textColumn2);

        currencyColumn1.setColumnName("sumaDetalle.totalLiquidado");
        currencyColumn1.setCurrencySymbolOnLeft(false);
        gridControl5.getColumnContainer().add(currencyColumn1);

        checkBoxColumn2.setColumnName("auditoria.activo");
        checkBoxColumn2.setColumnRequired(false);
        checkBoxColumn2.setEditableOnEdit(true);
        gridControl5.getColumnContainer().add(checkBoxColumn2);

        textColumn9.setColumnName("auditoria.usuarioInsert");
        textColumn9.setColumnRequired(false);
        gridControl5.getColumnContainer().add(textColumn9);

        dateTimeColumn2.setColumnName("auditoria.fechaInsert");
        dateTimeColumn2.setColumnRequired(false);
        gridControl5.getColumnContainer().add(dateTimeColumn2);

        textColumn11.setColumnName("auditoria.usuarioUpdate");
        textColumn11.setColumnRequired(false);
        gridControl5.getColumnContainer().add(textColumn11);

        dateTimeColumn4.setColumnName("auditoria.fechaUpdate");
        dateTimeColumn4.setColumnRequired(false);
        gridControl5.getColumnContainer().add(dateTimeColumn4);

        insertButton7.setButtonId("insetDetail");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reloadButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navigatorBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(insertButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(259, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reloadButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(navigatorBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insertButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gridControl5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridControl5, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Detalles de Siniestro", jPanel14);

        jPanelObs.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        gridControlObs.setDeleteButton(deleteButtonObs);
        gridControlObs.setEditButton(editButtonObs);
        gridControlObs.setInsertButton(insertButtonObs);
        gridControlObs.setMaxNumberOfRowsOnInsert(4);
        gridControlObs.setReloadButton(reloadButtonObs);
        gridControlObs.setSaveButton(saveButtonObs);
        gridControlObs.setValueObjectClassName(Observacion.class.getName());
        gridControlObs.setVisibleStatusPanel(false);

        decimalColumnObs.setColumnName("id");
        decimalColumnObs.setColumnRequired(false);
        decimalColumnObs.setPreferredWidth(40);
        gridControlObs.getColumnContainer().add(decimalColumnObs);

        textColumnObs1.setColumnName("observacion");
        textColumnObs1.setEditableOnEdit(true);
        textColumnObs1.setEditableOnInsert(true);
        textColumnObs1.setMaxCharacters(1024);
        textColumnObs1.setPreferredWidth(400);
        gridControlObs.getColumnContainer().add(textColumnObs1);

        textColumnObs2.setColumnName("auditoria.usuarioInsert");
        textColumnObs2.setColumnRequired(false);
        gridControlObs.getColumnContainer().add(textColumnObs2);

        dateTimeColumnObs1.setColumnName("auditoria.fechaInsert");
        dateTimeColumnObs1.setColumnRequired(false);
        dateTimeColumnObs1.setColumnSortable(true);
        dateTimeColumnObs1.setSortVersus(org.openswing.swing.util.java.Consts.DESC_SORTED);
        gridControlObs.getColumnContainer().add(dateTimeColumnObs1);

        textColumnObs3.setColumnFilterable(true);
        textColumnObs3.setColumnName("auditoria.usuarioUpdate");
        textColumnObs3.setColumnRequired(false);
        textColumnObs3.setColumnSortable(true);
        gridControlObs.getColumnContainer().add(textColumnObs3);

        dateTimeColumnObs2.setColumnFilterable(true);
        dateTimeColumnObs2.setColumnName("auditoria.fechaUpdate");
        dateTimeColumnObs2.setColumnRequired(false);
        dateTimeColumnObs2.setColumnSortable(true);
        gridControlObs.getColumnContainer().add(dateTimeColumnObs2);

        jPanel13.setLayout(new java.awt.GridLayout(3, 2, 2, 2));
        jPanel13.add(insertButtonObs);
        jPanel13.add(editButtonObs);
        jPanel13.add(deleteButtonObs);
        jPanel13.add(saveButtonObs);
        jPanel13.add(reloadButtonObs);

        javax.swing.GroupLayout jPanelObsLayout = new javax.swing.GroupLayout(jPanelObs);
        jPanelObs.setLayout(jPanelObsLayout);
        jPanelObsLayout.setHorizontalGroup(
            jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelObsLayout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridControlObs, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
        );
        jPanelObsLayout.setVerticalGroup(
            jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelObsLayout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(235, Short.MAX_VALUE))
            .addComponent(gridControlObs, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout observacionesLayout = new javax.swing.GroupLayout(observaciones);
        observaciones.setLayout(observacionesLayout);
        observacionesLayout.setHorizontalGroup(
            observacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
            .addGroup(observacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(observacionesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelObs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        observacionesLayout.setVerticalGroup(
            observacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
            .addGroup(observacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(observacionesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelObs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Observaciones", observaciones);

        jPanelNot.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        gridControlNot.setInsertButton(insertButtonNot);
        gridControlNot.setMaxNumberOfRowsOnInsert(4);
        gridControlNot.setReloadButton(reloadButtonNot);
        gridControlNot.setSaveButton(saveButtonNot);
        gridControlNot.setValueObjectClassName(NotaTecnica.class.getName());
        gridControlNot.setVisibleStatusPanel(false);

        decimalColumnNot.setColumnName("id");
        decimalColumnNot.setColumnRequired(false);
        decimalColumnNot.setPreferredWidth(40);
        gridControlNot.getColumnContainer().add(decimalColumnNot);

        textColumnNot1.setColumnName("observacion");
        textColumnNot1.setEditableOnEdit(true);
        textColumnNot1.setEditableOnInsert(true);
        textColumnNot1.setMaxCharacters(1024);
        textColumnNot1.setPreferredWidth(400);
        gridControlNot.getColumnContainer().add(textColumnNot1);

        textColumnNot2.setColumnName("auditoria.usuarioInsert");
        textColumnNot2.setColumnRequired(false);
        gridControlNot.getColumnContainer().add(textColumnNot2);

        dateTimeColumn1.setColumnName("auditoria.fechaInsert");
        dateTimeColumn1.setColumnRequired(false);
        dateTimeColumn1.setColumnSortable(true);
        dateTimeColumn1.setSortVersus(org.openswing.swing.util.java.Consts.DESC_SORTED);
        gridControlNot.getColumnContainer().add(dateTimeColumn1);

        textColumnNot3.setColumnFilterable(true);
        textColumnNot3.setColumnName("auditoria.usuarioUpdate");
        textColumnNot3.setColumnRequired(false);
        textColumnNot3.setColumnSortable(true);
        gridControlNot.getColumnContainer().add(textColumnNot3);

        dateTimeColumn3.setColumnFilterable(true);
        dateTimeColumn3.setColumnName("auditoria.fechaUpdate");
        dateTimeColumn3.setColumnRequired(false);
        dateTimeColumn3.setColumnSortable(true);
        gridControlNot.getColumnContainer().add(dateTimeColumn3);

        jPanel16.setLayout(new java.awt.GridLayout(3, 2, 2, 2));
        jPanel16.add(insertButtonNot);
        jPanel16.add(saveButtonNot);
        jPanel16.add(reloadButtonNot);

        javax.swing.GroupLayout jPanelNotLayout = new javax.swing.GroupLayout(jPanelNot);
        jPanelNot.setLayout(jPanelNotLayout);
        jPanelNotLayout.setHorizontalGroup(
            jPanelNotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNotLayout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridControlNot, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
        );
        jPanelNotLayout.setVerticalGroup(
            jPanelNotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNotLayout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(235, Short.MAX_VALUE))
            .addComponent(gridControlNot, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout notasTecnicasLayout = new javax.swing.GroupLayout(notasTecnicas);
        notasTecnicas.setLayout(notasTecnicasLayout);
        notasTecnicasLayout.setHorizontalGroup(
            notasTecnicasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
            .addGroup(notasTecnicasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(notasTecnicasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelNot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        notasTecnicasLayout.setVerticalGroup(
            notasTecnicasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
            .addGroup(notasTecnicasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(notasTecnicasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelNot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Notas Tecnicas", notasTecnicas);

        jPanelDoc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        gridControlDoc.setDeleteButton(deleteButtonDoc);
        gridControlDoc.setEditButton(editButtonDoc);
        gridControlDoc.setFilterButton(filterButtonDoc);
        gridControlDoc.setInsertButton(insertButtonDoc);
        gridControlDoc.setMaxNumberOfRowsOnInsert(4);
        gridControlDoc.setReloadButton(reloadButtonDoc);
        gridControlDoc.setSaveButton(saveButtonDoc);
        gridControlDoc.setValueObjectClassName(Documento.class.getName());
        gridControlDoc.setVisibleStatusPanel(false);

        buttonColumnDoc.setColumnName("button");
        buttonColumnDoc.setEditableOnEdit(true);
        buttonColumnDoc.setEnableInReadOnlyMode(true);
        buttonColumnDoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
        buttonColumnDoc.setPreferredWidth(20);
        buttonColumnDoc.setText("");
        gridControlDoc.getColumnContainer().add(buttonColumnDoc);

        decimalColumnNot1.setColumnName("id");
        decimalColumnNot1.setColumnRequired(false);
        decimalColumnNot1.setPreferredWidth(40);
        gridControlDoc.getColumnContainer().add(decimalColumnNot1);

        codLookupColumnDoc.setColumnName("tipoDocumento.nombre");
        codLookupColumnDoc.setControllerMethodName("getTipoDocAnex");
        codLookupColumnDoc.setEditableOnEdit(true);
        codLookupColumnDoc.setEditableOnInsert(true);
        gridControlDoc.getColumnContainer().add(codLookupColumnDoc);

        textColumnDoc.setColumnName("observacion");
        textColumnDoc.setColumnRequired(false);
        textColumnDoc.setEditableOnEdit(true);
        textColumnDoc.setEditableOnInsert(true);
        gridControlDoc.getColumnContainer().add(textColumnDoc);

        dateColumnDoc.setColumnName("fechaVencimiento");
        dateColumnDoc.setColumnRequired(false);
        dateColumnDoc.setEditableOnEdit(true);
        dateColumnDoc.setEditableOnInsert(true);
        gridControlDoc.getColumnContainer().add(dateColumnDoc);

        jPanel11.setLayout(new java.awt.GridLayout(3, 2, 2, 2));
        jPanel11.add(insertButtonDoc);
        jPanel11.add(editButtonDoc);
        jPanel11.add(deleteButtonDoc);
        jPanel11.add(saveButtonDoc);
        jPanel11.add(reloadButtonDoc);
        jPanel11.add(filterButtonDoc);

        javax.swing.GroupLayout jPanelDocLayout = new javax.swing.GroupLayout(jPanelDoc);
        jPanelDoc.setLayout(jPanelDocLayout);
        jPanelDocLayout.setHorizontalGroup(
            jPanelDocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDocLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridControlDoc, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelDocLayout.setVerticalGroup(
            jPanelDocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDocLayout.createSequentialGroup()
                .addGroup(jPanelDocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gridControlDoc, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout DocAnexosLayout = new javax.swing.GroupLayout(DocAnexos);
        DocAnexos.setLayout(DocAnexosLayout);
        DocAnexosLayout.setHorizontalGroup(
            DocAnexosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
            .addGroup(DocAnexosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DocAnexosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelDoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        DocAnexosLayout.setVerticalGroup(
            DocAnexosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
            .addGroup(DocAnexosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DocAnexosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelDoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Documentos Anexos", DocAnexos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reloadButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(251, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filterButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reloadButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        gridPartidas.setExportButton(exportButton1);
        gridPartidas.setFilterButton(filterButton1);
        gridPartidas.setFunctionId("partidaGrid");
        gridPartidas.setInsertRowsOnTop(false);
        gridPartidas.setMaxSortedColumns(3);
        gridPartidas.setNavBar(navigatorBar1);
        gridPartidas.setReloadButton(reloadButton2);
        gridPartidas.setSearchAdditionalRows(true);
        gridPartidas.setValueObjectClassName(SumaPartida.class.getName());

        textColumn4.setColumnFilterable(true);
        textColumn4.setColumnName("partidaPresupuestaria.nombre");
        textColumn4.setColumnSortable(true);
        gridPartidas.getColumnContainer().add(textColumn4);

        textColumn5.setColumnFilterable(true);
        textColumn5.setColumnName("partidaPresupuestaria.numero");
        textColumn5.setColumnSortable(true);
        gridPartidas.getColumnContainer().add(textColumn5);

        textColumn6.setColumnFilterable(true);
        textColumn6.setColumnName("tipoContrato.nombre");
        textColumn6.setColumnSortable(true);
        gridPartidas.getColumnContainer().add(textColumn6);

        decimalColumn2.setColumnFilterable(true);
        decimalColumn2.setColumnName("cantidadDetalles");
        decimalColumn2.setColumnSortable(true);
        gridPartidas.getColumnContainer().add(decimalColumn2);

        decimalColumn3.setColumnFilterable(true);
        decimalColumn3.setColumnName("cantidadFacturas");
        decimalColumn3.setColumnSortable(true);
        gridPartidas.getColumnContainer().add(decimalColumn3);

        decimalColumn4.setColumnFilterable(true);
        decimalColumn4.setColumnName("numeroSiniestrosFamiliar");
        decimalColumn4.setColumnSortable(true);
        gridPartidas.getColumnContainer().add(decimalColumn4);

        decimalColumn6.setColumnFilterable(true);
        decimalColumn6.setColumnName("numeroSiniestrosFamiliar");
        decimalColumn6.setColumnSortable(true);
        gridPartidas.getColumnContainer().add(decimalColumn6);

        decimalColumn25.setColumnFilterable(true);
        decimalColumn25.setColumnName("montoTitulares");
        decimalColumn25.setColumnSortable(true);
        decimalColumn25.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn25);

        decimalColumn26.setColumnFilterable(true);
        decimalColumn26.setColumnName("montoFamiliares");
        decimalColumn26.setColumnSortable(true);
        decimalColumn26.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn26);

        decimalColumn9.setColumnFilterable(true);
        decimalColumn9.setColumnName("montoRetencionTM");
        decimalColumn9.setColumnSortable(true);
        decimalColumn9.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn9);

        decimalColumn10.setColumnFilterable(true);
        decimalColumn10.setColumnName("baseIslr");
        decimalColumn10.setColumnSortable(true);
        decimalColumn10.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn10);

        decimalColumn11.setColumnFilterable(true);
        decimalColumn11.setColumnName("montoRetencionIslr");
        decimalColumn11.setColumnSortable(true);
        decimalColumn11.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn11);

        decimalColumn12.setColumnFilterable(true);
        decimalColumn12.setColumnName("baseIva");
        decimalColumn12.setColumnSortable(true);
        decimalColumn12.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn12);

        decimalColumn13.setColumnFilterable(true);
        decimalColumn13.setColumnName("montoIva");
        decimalColumn13.setColumnSortable(true);
        decimalColumn13.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn13);

        decimalColumn14.setColumnFilterable(true);
        decimalColumn14.setColumnName("montoRetencionIva");
        decimalColumn14.setColumnSortable(true);
        decimalColumn14.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn14);

        decimalColumn15.setColumnFilterable(true);
        decimalColumn15.setColumnName("montoRetencionProntoPago");
        decimalColumn15.setColumnSortable(true);
        decimalColumn15.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn15);

        decimalColumn16.setColumnFilterable(true);
        decimalColumn16.setColumnName("montoRetencionDeducible");
        decimalColumn16.setColumnSortable(true);
        decimalColumn16.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn16);

        decimalColumn17.setColumnFilterable(true);
        decimalColumn17.setColumnName("gastosClinicos");
        decimalColumn17.setColumnSortable(true);
        decimalColumn17.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn17);

        decimalColumn18.setColumnFilterable(true);
        decimalColumn18.setColumnName("honorariosMedicos");
        decimalColumn18.setColumnSortable(true);
        decimalColumn18.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn18);

        decimalColumn19.setColumnFilterable(true);
        decimalColumn19.setColumnName("montoNoAmparado");
        decimalColumn19.setColumnSortable(true);
        decimalColumn19.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn19);

        decimalColumn20.setColumnFilterable(true);
        decimalColumn20.setColumnName("montoAmparado");
        decimalColumn20.setColumnSortable(true);
        decimalColumn20.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn20);

        decimalColumn21.setColumnFilterable(true);
        decimalColumn21.setColumnName("totalFacturado");
        decimalColumn21.setColumnSortable(true);
        decimalColumn21.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn21);

        decimalColumn22.setColumnFilterable(true);
        decimalColumn22.setColumnName("totalLiquidado");
        decimalColumn22.setColumnSortable(true);
        decimalColumn22.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn22);

        decimalColumn23.setColumnFilterable(true);
        decimalColumn23.setColumnName("totalRetenido");
        decimalColumn23.setColumnSortable(true);
        decimalColumn23.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn23);

        decimalColumn24.setColumnFilterable(true);
        decimalColumn24.setColumnName("totalACancelar");
        decimalColumn24.setColumnSortable(true);
        decimalColumn24.setDecimals(2);
        gridPartidas.getColumnContainer().add(decimalColumn24);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(gridPartidas, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(gridPartidas, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Partidas", jPanel2);

        gridReportes.setValueObjectClassName(Reporte.class.getName());
        gridReportes.setVisibleStatusPanel(false);

        textColumn12.setColumnName("titulo");
        textColumn12.setEditableOnEdit(true);
        textColumn12.setEditableOnInsert(true);
        textColumn12.setMaxCharacters(1024);
        textColumn12.setPreferredWidth(400);
        gridReportes.getColumnContainer().add(textColumn12);

        javax.swing.GroupLayout ReportesLayout = new javax.swing.GroupLayout(Reportes);
        Reportes.setLayout(ReportesLayout);
        ReportesLayout.setHorizontalGroup(
            ReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
            .addGroup(ReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ReportesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(gridReportes, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        ReportesLayout.setVerticalGroup(
            ReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
            .addGroup(ReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ReportesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(gridReportes, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Reportes", Reportes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(414, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    public GridControl getGridDesgloseSumaAsegurada() {

        return gridControl5;
    }

    @Override
    public void inicializar(FormController formController, boolean addToMDIFrame) {
        initComponents();
        Class<?> esta = OrdenDePago.class;
        //<editor-fold defaultstate="collapsed" desc="modulo de obs, not and doc">
        org.openswing.swing.table.columns.client.PictureCaptureColumn pcc =
                new org.openswing.swing.table.columns.client.PictureCaptureColumn();
        pcc.setColumnName("file");
        pcc.setEditableOnInsert(true);
        pcc.setEditableOnEdit(false);
        pcc.setFileNameAttributeName("fileName");
        pcc.setPreferredWidth(220);
        gridControlDoc.getColumnContainer().add(pcc);
        controllerDocumentosAnexosX = new DefaultDocumentosAnexosGridController(esta, gridControlDoc);
        buttonColumnDoc.addActionListener(controllerDocumentosAnexosX);
        gridControlDoc.setController(controllerDocumentosAnexosX);
        gridControlDoc.setGridDataLocator(controllerDocumentosAnexosX);

        com.jswitch.base.controlador.documentosAnexos.TipoDocumentoLookupController lookupDocumentoAnexo2 =
                new TipoDocumentoLookupController(com.jswitch.base.modelo.Dominios.Modulos.BENEFICIARIOS);
        lookupDocumentoAnexo2.addLookup2ParentLink("tipoDocumento");
        codLookupColumnDoc.setLookupController(lookupDocumentoAnexo2);

        controllerObservaciones =
                new DefaultGridInternalController(esta.getName(), "getObservaciones", gridControlObs);
        gridControlObs.setGridDataLocator(controllerObservaciones);
        gridControlObs.setController(controllerObservaciones);

        controllerNotasTecnicas =
                new DefaultGridInternalController(esta.getName(), "getNotasTecnicas", gridControlNot);
        gridControlNot.setGridDataLocator(controllerNotasTecnicas);
        gridControlNot.setController(controllerNotasTecnicas);
        //</editor-fold>
        partidaPresupuestaria =
                new PartidaPresupuestariaGridInternalController(gridPartidas);
        gridPartidas.setGridDataLocator(partidaPresupuestaria);
        gridPartidas.setController(partidaPresupuestaria);

        detalleSiniestros =
                new DetalleSiniestroLiquidadosGridInternalController(OrdenDePago.class.getName(),
                "getDetalleSiniestros", gridControl5,
                (OrdenDePagoDetailFrameController) formController);
        gridControl5.setGridDataLocator(detalleSiniestros);
        gridControl5.setController(detalleSiniestros);

        RemesaLookupController rem = new RemesaLookupController(Remesa.class.getName());
        rem.addLookup2ParentLink("remesa");
        codLookupControl2.setLookupController(rem);
        codLookupControl2.setOpenDetail("remesa", RemesaDetailFrameController.class.getName(),
                new Class[]{String.class, GridControl.class, BeanVO.class, Boolean.class},
                new Object[]{RemesaDetailFrame.class.getName(), null, null, Boolean.TRUE}, 2);

        PersonaLookupControllerPorNombre lookupPersonas =
                new PersonaLookupControllerPorNombre("CLI");
        lookupPersonas.addLookup2ParentLink("personaPago");
        codLookupControl1.setLookupController(lookupPersonas);
        codLookupControl1.setOpenDetail("personaPago", PersonasDetailController.class.getName(), new Class[]{GridControl.class, BeanVO.class, Rif.class}, new Object[]{null, null, null}, 1);

        controllerReportes =
                new ReporteGridInternalController(OrdenDePago.class.getName(), "getReportes", gridReportes);
        gridReportes.setGridDataLocator(controllerReportes);
        gridReportes.setController(controllerReportes);
        //  codLookupControl1.setNewDetail("personaPago", RifDialog.class.getName(), new Class[]{Form.class, String.class, Object[].class}, new Object[]{null, null, new Object[]{"ASE"}}, 0);
        insertButton7.addActionListener((ActionListener) formController);
        jButton1.addActionListener((ActionListener) formController);
        jButton2.addActionListener((ActionListener) formController);
        CuentaBancariaPorPersonaLookupController emp = new CuentaBancariaPorPersonaLookupController(
                (OrdenDePagoDetailFrameController)formController);
        emp.addLookup2ParentLink("cuentaBancaria");
        codLookupControl3.setLookupController(emp);
        form1.setCreateInnerVO(false);
        form1.setFormController(formController);
        if (addToMDIFrame) {
            pack();
        } else {
            setBounds(0, 0, 0, 0);
        }

        MDIFrame.add(this);
    }

    @Override
    public void saveGridsData() {
//        gridControl5.getSaveButton().doClick();
    }

    @Override
    public void reloadGridsData() {
        gridControl5.getReloadButton().doClick();
        gridControlDoc.getReloadButton().doClick();
        gridControlNot.getReloadButton().doClick();
        gridControlObs.getReloadButton().doClick();
        gridReportes.reloadData();
    }

    @Override
    public void clearGridsData() {
        gridControl5.clearData();
    }

    @Override
    public void setOwnerVO(BeanVO beanVO) {
        Long id = null;
        if (beanVO != null) {
            id = ((OrdenDePago) beanVO).getId();
        }
        detalleSiniestros.setBeanVO(beanVO);
        controllerDocumentosAnexosX.setBeanVO(beanVO, id);
        controllerObservaciones.setBeanVO(beanVO);
        controllerNotasTecnicas.setBeanVO(beanVO);
        partidaPresupuestaria.setBeanVO(beanVO);
        controllerReportes.setBeanVO(beanVO);
        reloadGridsData();
    }

    @Override
    public Form getMainPanel() {
        return form1;
    }

    @Override
    public void modeChanged(int currentMode) {
        if (currentMode == Consts.INSERT) {
            clearGridsData();
        }
        if (currentMode == Consts.INSERT) {
            setEnableGridInternalButtons(false);
        } else {
            setEnableGridInternalButtons(true);
        }
    }

    public void hideAll(EstatusPago estatusPago) {
        switch (estatusPago) {
            case PENDIENTE:
                getMainPanel().setEditButton(editButton1);
                getMainPanel().setSaveButton(saveButton1);
                editButton1.setVisible(true);
                saveButton1.setVisible(true);
                gridControl5.setDeleteButton(deleteButton7);
                gridControlDoc.setEditButton(editButtonDoc);
                gridControlDoc.setDeleteButton(deleteButtonDoc);
                gridControlDoc.setInsertButton(insertButtonDoc);
                gridControlNot.setSaveButton(saveButtonNot);
                gridControlNot.setInsertButton(insertButtonNot);
                gridControlObs.setEditButton(editButtonObs);
                gridControlObs.setDeleteButton(deleteButtonObs);
                gridControlObs.setInsertButton(insertButtonObs);
                jButton1.setVisible(true);
                jButton2.setVisible(true);
                setEnableGridInternalButtons(true);
                break;
            default:
                getMainPanel().setEditButton(null);
                getMainPanel().setSaveButton(null);
                editButton1.setVisible(false);
                saveButton1.setVisible(false);
                gridControl5.setDeleteButton(null);
                gridControl5.setInsertButton(null);
                gridControlDoc.setEditButton(null);
                gridControlDoc.setDeleteButton(null);
                gridControlDoc.setInsertButton(null);
                gridControlNot.setSaveButton(null);
                gridControlNot.setInsertButton(null);
                gridControlObs.setEditButton(null);
                gridControlObs.setDeleteButton(null);
                gridControlObs.setInsertButton(null);
                jButton1.setVisible(false);
                jButton2.setVisible(false);
                setEnableGridInternalButtons(false);
                break;
        }
    }

    private void setEnableGridInternalButtons(boolean enabled) {
        deleteButton7.setVisible(enabled);
        insertButton7.setVisible(enabled);
        jPanel13.setVisible(enabled);
        jPanel16.setVisible(enabled);
        jPanel11.setVisible(enabled);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DocAnexos;
    private javax.swing.JPanel Reportes;
    private org.openswing.swing.table.columns.client.ButtonColumn buttonColumnDoc;
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn2;
    private org.openswing.swing.client.CheckBoxControl checkBoxControl1;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumnDoc;
    private org.openswing.swing.client.CodLookupControl codLookupControl1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl1;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl2;
    private org.openswing.swing.table.columns.client.CurrencyColumn currencyColumn1;
    private org.openswing.swing.table.columns.client.DateColumn dateColumnDoc;
    private org.openswing.swing.client.DateControl dateControl1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn2;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn3;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn4;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumnObs1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumnObs2;
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
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn24;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn25;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn26;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn3;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn4;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn9;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumnNot;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumnNot1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumnObs;
    private org.openswing.swing.client.DeleteButton deleteButton7;
    private org.openswing.swing.client.DeleteButton deleteButtonDoc;
    private org.openswing.swing.client.DeleteButton deleteButtonObs;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButtonDoc;
    private org.openswing.swing.client.EditButton editButtonObs;
    private org.openswing.swing.client.ExportButton exportButton1;
    private org.openswing.swing.client.FilterButton filterButton1;
    private org.openswing.swing.client.FilterButton filterButtonDoc;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GridControl gridControl5;
    private org.openswing.swing.client.GridControl gridControlDoc;
    private org.openswing.swing.client.GridControl gridControlNot;
    private org.openswing.swing.client.GridControl gridControlObs;
    private org.openswing.swing.client.GridControl gridPartidas;
    private org.openswing.swing.client.GridControl gridReportes;
    private org.openswing.swing.client.InsertButton insertButton7;
    private org.openswing.swing.client.InsertButton insertButtonDoc;
    private org.openswing.swing.client.InsertButton insertButtonNot;
    private org.openswing.swing.client.InsertButton insertButtonObs;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelDoc;
    private javax.swing.JPanel jPanelNot;
    private javax.swing.JPanel jPanelObs;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.NavigatorBar navigatorBar2;
    private javax.swing.JPanel notasTecnicas;
    private javax.swing.JPanel observaciones;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButton2;
    private org.openswing.swing.client.ReloadButton reloadButton7;
    private org.openswing.swing.client.ReloadButton reloadButtonDoc;
    private org.openswing.swing.client.ReloadButton reloadButtonNot;
    private org.openswing.swing.client.ReloadButton reloadButtonObs;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButtonDoc;
    private org.openswing.swing.client.SaveButton saveButtonNot;
    private org.openswing.swing.client.SaveButton saveButtonObs;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn11;
    private org.openswing.swing.table.columns.client.TextColumn textColumn12;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.table.columns.client.TextColumn textColumn9;
    private org.openswing.swing.table.columns.client.TextColumn textColumnDoc;
    private org.openswing.swing.table.columns.client.TextColumn textColumnNot1;
    private org.openswing.swing.table.columns.client.TextColumn textColumnNot2;
    private org.openswing.swing.table.columns.client.TextColumn textColumnNot3;
    private org.openswing.swing.table.columns.client.TextColumn textColumnObs1;
    private org.openswing.swing.table.columns.client.TextColumn textColumnObs2;
    private org.openswing.swing.table.columns.client.TextColumn textColumnObs3;
    private org.openswing.swing.client.TextControl textControl1;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl3;
    // End of variables declaration//GEN-END:variables
}
