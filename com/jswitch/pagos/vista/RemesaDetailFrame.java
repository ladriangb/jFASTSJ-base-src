package com.jswitch.pagos.vista;

import com.jswitch.base.controlador.documentosAnexos.TipoDocumentoLookupController;
import com.jswitch.base.controlador.util.DefaultDocumentosAnexosGridController;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.controlador.util.DefaultLookupControllerPorNombre;
import com.jswitch.base.modelo.entidades.Documento;
import com.jswitch.base.modelo.entidades.NotaTecnica;
import com.jswitch.base.modelo.entidades.Observacion;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.configuracion.controlador.PartidaPresupuestariaGridInternalController;
import com.jswitch.fas.modelo.Dominios;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.pagos.controlador.OrdenesDePagoGridInternalController;
import com.jswitch.pagos.controlador.RemesaDetailFrameController;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.modelo.maestra.Remesa;
import com.jswitch.persona.controlador.CuentaBancariaEmpresaLookupController;
import com.jswitch.persona.modelo.dominio.TipoCuentaBancaria;
import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.reporte.vista.EsperaDialog;
import com.jswitch.siniestros.controlador.detalle.reportes.ReporteGridInternalController;
import com.jswitch.vistasbd.SumaPartidaRemesa;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author adrian
 */
public class RemesaDetailFrame extends DefaultDetailFrame {

    private OrdenesDePagoGridInternalController ordenDePagos;
    protected DefaultDocumentosAnexosGridController controllerDocumentosAnexosX;
    protected DefaultGridInternalController controllerObservaciones;
    protected DefaultGridInternalController controllerNotasTecnicas;
    private PartidaPresupuestariaGridInternalController partidaPresupuestaria;
    private ReporteGridInternalController controllerReportes;
    // final JDialog d = new EsperaDialog(null, false);
    //  private Reporte reporteDetail = new Reporte(CategoriaReporte.REMESAS, 0, "REM-D001", "FONDO AUTOADMINISTRADO DE SALUD", "", "FROM " + Remesa.class.getName(), "Carta", false, false, true, false);

    public RemesaDetailFrame() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        editButton1 = new org.openswing.swing.client.EditButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        exportButton1 = new org.openswing.swing.client.ExportButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        form1 = new org.openswing.swing.form.client.Form();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        numericControl2 = new org.openswing.swing.client.NumericControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        comboBoxControl2 = new org.openswing.swing.client.ComboBoxControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        checkBoxControl1 = new org.openswing.swing.client.CheckBoxControl();
        jPanel7 = new javax.swing.JPanel();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        comboBoxControl4 = new org.openswing.swing.client.ComboBoxControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        comboBoxControl1 = new org.openswing.swing.client.ComboBoxControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        dateControl2 = new org.openswing.swing.client.DateControl();
        labelControl16 = new org.openswing.swing.client.LabelControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        numericControl3 = new org.openswing.swing.client.NumericControl();
        textControl1 = new org.openswing.swing.client.TextControl();
        numericControl4 = new org.openswing.swing.client.NumericControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        codLookupControl1 = new org.openswing.swing.client.CodLookupControl();
        dateControl1 = new org.openswing.swing.client.DateControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        dateControl3 = new org.openswing.swing.client.DateControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        dateControl4 = new org.openswing.swing.client.DateControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        comboBoxControl3 = new org.openswing.swing.client.ComboBoxControl();
        jPanel14 = new javax.swing.JPanel();
        gridControl5 = new org.openswing.swing.client.GridControl();
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
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
        Partidas = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        reloadButton2 = new org.openswing.swing.client.ReloadButton();
        exportButton2 = new org.openswing.swing.client.ExportButton();
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

        setTitle("Remesa");

        jButton2.setText("Pagar");
        jButton2.setPreferredSize(new java.awt.Dimension(33, 33));

        jButton3.setText("Anular");
        jButton3.setPreferredSize(new java.awt.Dimension(33, 33));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(exportButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reloadButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        form1.setVOClassName(Remesa.class.getName());
        form1.setEditButton(editButton1);
        form1.setPreferredSize(new java.awt.Dimension(741, 574));
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);

        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        labelControl15.setLabel("detalle");

        textControl2.setAttributeName("detalle");
        textControl2.setRequired(true);

        labelControl1.setLabel("numeroReferenciaLote");

        labelControl6.setLabel("Numero Negociacion");

        numericControl2.setAttributeName("numNeg");
        numericControl2.setEnabledOnEdit(false);
        numericControl2.setEnabledOnInsert(false);
        numericControl2.setMaxCharacters(8);
        numericControl2.setToolTipText("Valor asignado por el Banco.  Será informado por éste a la implantación del servicio. Ej. 00002100");

        textControl3.setAttributeName("refLot");
        textControl3.setEnabledOnEdit(false);
        textControl3.setEnabledOnInsert(false);
        textControl3.setToolTipText("Identificacion del lote del pago.   Valor asignado por la Empresa Ej. 00002100");

        comboBoxControl2.setAttributeName("tipoPago");
        comboBoxControl2.setDomainId(Dominios.TipoPago().getDomainId());
        comboBoxControl2.setRequired(true);
        comboBoxControl2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxControl2ActionPerformed(evt);
            }
        });

        labelControl7.setLabel("tipoPago");

        checkBoxControl1.setText("autoSearch");
        checkBoxControl1.setAttributeName("autoSearch");
        checkBoxControl1.setEnabledOnEdit(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelControl15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addComponent(textControl3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelControl6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numericControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(labelControl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {checkBoxControl1, labelControl1, labelControl15, labelControl6, labelControl7});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numericControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comboBoxControl2, labelControl1, labelControl15, labelControl6, labelControl7, numericControl2, textControl2, textControl3});

        jPanel8.add(jPanel3);

        labelControl4.setLabel("estatusPago");

        comboBoxControl4.setAttributeName("tipoDetalleSiniestro");
        comboBoxControl4.setDomainId(Dominios.TipoDetalleSiniestro().getDomainId());
        comboBoxControl4.setEnabledOnEdit(false);
        comboBoxControl4.setRequired(true);

        labelControl14.setLabel("tipoDetalleSiniestro");

        comboBoxControl1.setAttributeName("estatusPago");
        comboBoxControl1.setDomainId(Dominios.EstatusPago().getDomainId());
        comboBoxControl1.setEnabledOnEdit(false);
        comboBoxControl1.setEnabledOnInsert(false);
        comboBoxControl1.setRequired(true);

        labelControl5.setLabel("fechaPagado");

        dateControl2.setAttributeName("fechaPagado");
        dateControl2.setEnabledOnEdit(false);
        dateControl2.setEnabledOnInsert(false);
        dateControl2.setToolTipText("Para uso interno de la empresa, con el objeto de identificar la fecha de envío del archivo de pago.  Formato: dd/mm/aaaa");

        labelControl16.setLabel("numeroRemesa");

        textControl4.setAttributeName("numeroRemesa");
        textControl4.setEnabledOnEdit(false);
        textControl4.setEnabledOnInsert(false);
        textControl4.setToolTipText("Identificacion del lote del pago.   Valor asignado por la Empresa Ej. 00002100");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(labelControl14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxControl4, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(labelControl16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl4, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelControl14, labelControl16, labelControl4, labelControl5});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comboBoxControl1, comboBoxControl4, dateControl2, labelControl14, labelControl16, labelControl4, labelControl5, textControl4});

        jPanel8.add(jPanel7);

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        numericControl3.setAttributeName("numRefCre");
        numericControl3.setMaxCharacters(8);

        textControl1.setAttributeName("cuentaBancaria.tipoCuenta.nombre");
        textControl1.setEnabledOnEdit(false);
        textControl1.setEnabledOnInsert(false);
        textControl1.setMaxCharacters(20);

        numericControl4.setAttributeName("numRefDeb");
        numericControl4.setMaxCharacters(8);

        labelControl8.setLabel("numRefDeb");

        labelControl12.setLabel("numRefCre");

        labelControl13.setLabel("numeroCuentaDebitar");

        labelControl10.setLabel("tipoCuentaDebitar");

        codLookupControl1.setAttributeName("cuentaBancaria.numero");

        dateControl1.setAttributeName("fechaEnvio");
        dateControl1.setToolTipText("Para uso interno de la empresa, con el objeto de identificar la fecha de envío del archivo de pago.  Formato: dd/mm/aaaa");

        labelControl2.setLabel("fechaEnvio");

        dateControl3.setAttributeName("fechaPropuestaPago");
        dateControl3.setToolTipText("Para uso interno de la empresa a objeto de identificar la fecha de generación del archivo de pago. Formato: dd/mm/aaaa");

        labelControl3.setLabel("fechaPropuestaPago");

        labelControl9.setLabel("fechaValor");

        dateControl4.setAttributeName("fechaValor");
        dateControl4.setToolTipText("Fecha efectiva del Débito. dd/mm/aaaa");

        labelControl11.setLabel("duracionCheque");

        comboBoxControl3.setAttributeName("duracionCheque");
        comboBoxControl3.setDomainId(Dominios.DuracionCheque().getDomainId());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelControl13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codLookupControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelControl10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelControl8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelControl12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(numericControl4, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                            .addComponent(numericControl3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateControl3, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelControl9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateControl4, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelControl11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxControl3, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelControl10, labelControl11, labelControl12, labelControl13, labelControl2, labelControl3, labelControl8, labelControl9});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numericControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numericControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codLookupControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {codLookupControl1, dateControl1, dateControl3, dateControl4, labelControl10, labelControl11, labelControl12, labelControl13, labelControl2, labelControl3, labelControl8, labelControl9, numericControl3, numericControl4, textControl1});

        jPanel6.add(jPanel5);

        javax.swing.GroupLayout form1Layout = new javax.swing.GroupLayout(form1);
        form1.setLayout(form1Layout);
        form1Layout.setHorizontalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
                .addContainerGap())
        );
        form1Layout.setVerticalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Detalle de Remesa", form1);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        gridControl5.setDeleteButton(deleteButton7);
        gridControl5.setNavBar(navigatorBar2);
        gridControl5.setReloadButton(reloadButton7);
        gridControl5.setValueObjectClassName(OrdenDePago.class.getName());

        decimalColumn5.setColumnFilterable(true);
        decimalColumn5.setColumnName("id");
        decimalColumn5.setColumnRequired(false);
        decimalColumn5.setColumnSortable(true);
        decimalColumn5.setPreferredWidth(40);
        gridControl5.getColumnContainer().add(decimalColumn5);

        textColumn1.setColumnFilterable(true);
        textColumn1.setColumnName("personaPago.nombreLargo");
        textColumn1.setColumnRequired(false);
        textColumn1.setColumnSortable(true);
        gridControl5.getColumnContainer().add(textColumn1);

        currencyColumn1.setColumnFilterable(true);
        currencyColumn1.setColumnName("sumaOrden.totalACancelar");
        currencyColumn1.setColumnRequired(false);
        currencyColumn1.setColumnSortable(true);
        currencyColumn1.setCurrencySymbol("VEF");
        gridControl5.getColumnContainer().add(currencyColumn1);

        checkBoxColumn2.setColumnFilterable(true);
        checkBoxColumn2.setColumnName("auditoria.activo");
        checkBoxColumn2.setColumnRequired(false);
        checkBoxColumn2.setColumnSortable(true);
        gridControl5.getColumnContainer().add(checkBoxColumn2);

        textColumn9.setColumnFilterable(true);
        textColumn9.setColumnName("auditoria.usuarioInsert");
        textColumn9.setColumnRequired(false);
        textColumn9.setColumnSortable(true);
        gridControl5.getColumnContainer().add(textColumn9);

        dateTimeColumn2.setColumnFilterable(true);
        dateTimeColumn2.setColumnName("auditoria.fechaInsert");
        dateTimeColumn2.setColumnRequired(false);
        dateTimeColumn2.setColumnSortable(true);
        gridControl5.getColumnContainer().add(dateTimeColumn2);

        textColumn11.setColumnFilterable(true);
        textColumn11.setColumnName("auditoria.usuarioUpdate");
        textColumn11.setColumnRequired(false);
        textColumn11.setColumnSortable(true);
        gridControl5.getColumnContainer().add(textColumn11);

        dateTimeColumn4.setColumnFilterable(true);
        dateTimeColumn4.setColumnName("auditoria.fechaUpdate");
        dateTimeColumn4.setColumnRequired(false);
        dateTimeColumn4.setColumnSortable(true);
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
                .addContainerGap(286, Short.MAX_VALUE))
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
            .addComponent(gridControl5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gridControl5, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ordenes de Pago", jPanel14);

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
                .addComponent(gridControlObs, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelObsLayout.setVerticalGroup(
            jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelObsLayout.createSequentialGroup()
                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelObsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(gridControlObs, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout observacionesLayout = new javax.swing.GroupLayout(observaciones);
        observaciones.setLayout(observacionesLayout);
        observacionesLayout.setHorizontalGroup(
            observacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 614, Short.MAX_VALUE)
            .addGroup(observacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, observacionesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelObs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        observacionesLayout.setVerticalGroup(
            observacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
            .addGroup(observacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, observacionesLayout.createSequentialGroup()
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
                .addComponent(gridControlNot, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
        );
        jPanelNotLayout.setVerticalGroup(
            jPanelNotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNotLayout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(313, Short.MAX_VALUE))
            .addComponent(gridControlNot, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout notasTecnicasLayout = new javax.swing.GroupLayout(notasTecnicas);
        notasTecnicas.setLayout(notasTecnicasLayout);
        notasTecnicasLayout.setHorizontalGroup(
            notasTecnicasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 614, Short.MAX_VALUE)
            .addGroup(notasTecnicasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(notasTecnicasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelNot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        notasTecnicasLayout.setVerticalGroup(
            notasTecnicasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
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
                .addComponent(gridControlDoc, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelDocLayout.setVerticalGroup(
            jPanelDocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDocLayout.createSequentialGroup()
                .addGroup(jPanelDocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gridControlDoc, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout DocAnexosLayout = new javax.swing.GroupLayout(DocAnexos);
        DocAnexos.setLayout(DocAnexosLayout);
        DocAnexosLayout.setHorizontalGroup(
            DocAnexosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 614, Short.MAX_VALUE)
            .addGroup(DocAnexosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DocAnexosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelDoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        DocAnexosLayout.setVerticalGroup(
            DocAnexosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
            .addGroup(DocAnexosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DocAnexosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelDoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Documentos Anexos", DocAnexos);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reloadButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(278, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filterButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reloadButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        gridPartidas.setExportButton(exportButton2);
        gridPartidas.setFilterButton(filterButton1);
        gridPartidas.setFunctionId("partidaRemesaGrid");
        gridPartidas.setInsertRowsOnTop(false);
        gridPartidas.setMaxSortedColumns(3);
        gridPartidas.setNavBar(navigatorBar1);
        gridPartidas.setReloadButton(reloadButton2);
        gridPartidas.setSearchAdditionalRows(true);
        gridPartidas.setValueObjectClassName(SumaPartidaRemesa.class.getName());

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
        decimalColumn4.setColumnName("cantidadFacturas");
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

        javax.swing.GroupLayout PartidasLayout = new javax.swing.GroupLayout(Partidas);
        Partidas.setLayout(PartidasLayout);
        PartidasLayout.setHorizontalGroup(
            PartidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 614, Short.MAX_VALUE)
            .addGroup(PartidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PartidasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(PartidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PartidasLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(gridPartidas, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        PartidasLayout.setVerticalGroup(
            PartidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
            .addGroup(PartidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PartidasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(gridPartidas, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Partidas", Partidas);

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
            .addGap(0, 614, Short.MAX_VALUE)
            .addGroup(ReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ReportesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(gridReportes, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        ReportesLayout.setVerticalGroup(
            ReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
            .addGroup(ReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ReportesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(gridReportes, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Reportes", Reportes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

        private void comboBoxControl2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxControl2ActionPerformed
            Boolean sw = (Dominios.TipoPago.CHEQUE_DE_GERENCIA.equals(comboBoxControl2.getValue()));
            comboBoxControl3.setVisible(sw);
            labelControl11.setVisible(sw);
            sw = !(Dominios.TipoPago.PAGO_SIGECOF.equals(comboBoxControl2.getValue()));
            jPanel6.setVisible(sw);
    }//GEN-LAST:event_comboBoxControl2ActionPerformed

    public GridControl getGridDesgloseSumaAsegurada() {

        return gridControl5;
    }

    @Override
    public void inicializar(FormController formController, boolean addToMDIFrame) {
        initComponents();
        Class<?> esta = Remesa.class;
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

        ordenDePagos =
                new OrdenesDePagoGridInternalController(Remesa.class.getName(),
                "getOrdenDePagos", gridControl5, (RemesaDetailFrameController) formController);
        gridControl5.setGridDataLocator(ordenDePagos);
        gridControl5.setController(ordenDePagos);

        DefaultLookupControllerPorNombre lookupPersonas =
                new DefaultLookupControllerPorNombre(TipoCuentaBancaria.class.getName());
        lookupPersonas.addLookup2ParentLink("tipoCuenta");
        codLookupControl1.setLookupController(lookupPersonas);
        controllerReportes =
                new ReporteGridInternalController(Remesa.class.getName(), "getReportes", gridReportes);
        gridReportes.setGridDataLocator(controllerReportes);
        gridReportes.setController(controllerReportes);

        insertButton7.addActionListener((ActionListener) formController);
        exportButton1.addActionListener((ActionListener) formController);
        form1.setCreateInnerVO(false);
        form1.setFormController(formController);

        CuentaBancariaEmpresaLookupController emp = new CuentaBancariaEmpresaLookupController();
        emp.addLookup2ParentLink("cuentaBancaria");
        codLookupControl1.setLookupController(emp);

        jButton2.addActionListener((ActionListener) formController);
        jButton3.addActionListener((ActionListener) formController);

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
            id = ((Remesa) beanVO).getId();
        }
        ordenDePagos.setBeanVO(beanVO);
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
            case ANULADO:
            case PAGADO:
            case SELECCIONADO:
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
                jButton3.setVisible(false);
                jButton2.setVisible(false);
                setEnableGridInternalButtons(false);
                break;
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
                jButton3.setVisible(true);
                jButton2.setVisible(true);
                setEnableGridInternalButtons(true);
        }
    }

    /**
     * pone las cosas invisibles mientras el grid no lo permita
     * @param enabled 
     */
    private void setEnableGridInternalButtons(boolean enabled) {
        insertButton7.setVisible(enabled);
        deleteButton7.setVisible(enabled);
        jPanel13.setVisible(enabled);
        jPanel16.setVisible(enabled);
        jPanel11.setVisible(enabled);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DocAnexos;
    private javax.swing.JPanel Partidas;
    private javax.swing.JPanel Reportes;
    private org.openswing.swing.table.columns.client.ButtonColumn buttonColumnDoc;
    private javax.swing.ButtonGroup buttonGroup1;
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn2;
    private org.openswing.swing.client.CheckBoxControl checkBoxControl1;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumnDoc;
    private org.openswing.swing.client.CodLookupControl codLookupControl1;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl1;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl2;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl3;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl4;
    private org.openswing.swing.table.columns.client.CurrencyColumn currencyColumn1;
    private org.openswing.swing.table.columns.client.DateColumn dateColumnDoc;
    private org.openswing.swing.client.DateControl dateControl1;
    private org.openswing.swing.client.DateControl dateControl2;
    private org.openswing.swing.client.DateControl dateControl3;
    private org.openswing.swing.client.DateControl dateControl4;
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
    private org.openswing.swing.client.ExportButton exportButton2;
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelDoc;
    private javax.swing.JPanel jPanelNot;
    private javax.swing.JPanel jPanelObs;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl10;
    private org.openswing.swing.client.LabelControl labelControl11;
    private org.openswing.swing.client.LabelControl labelControl12;
    private org.openswing.swing.client.LabelControl labelControl13;
    private org.openswing.swing.client.LabelControl labelControl14;
    private org.openswing.swing.client.LabelControl labelControl15;
    private org.openswing.swing.client.LabelControl labelControl16;
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
    private org.openswing.swing.client.NumericControl numericControl2;
    private org.openswing.swing.client.NumericControl numericControl3;
    private org.openswing.swing.client.NumericControl numericControl4;
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
    private org.openswing.swing.client.TextControl textControl4;
    // End of variables declaration//GEN-END:variables
    public void hideButtons(Remesa rem) {
        jButton3.setVisible(true);
        if (rem.getEstatusPago().equals(EstatusPago.ANULADO)) {
            jButton3.setVisible(false);
        }
    }
}
