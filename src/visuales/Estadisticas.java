/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visuales;

import base_de_datos.GestionBD;
import dialogs.AbstractFrame;
import dialogs.ConfirmDialog;
import dialogs.InputDialog;
import dialogs.MessageDialog;
import java.awt.Font;
import java.io.File;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author electro-zombie
 */
public class Estadisticas extends AbstractFrame {

    /**
     * Creates new form Estadisticas
     */
    public Estadisticas() {
        initComponents();
        this.setLocationRelativeTo(null);
   
        Vector<Integer> annosEncuesta = GestionBD.getAnnosEncuestas();
        
        if(annosEncuesta.isEmpty()){
            ConfirmDialog confirm = new ConfirmDialog(1, "Aun no se ha realizado ninguna encuesta. Desea importar una base de datos?", "Error", Language.ES, this);
        }
        else{
        
        for (Integer anno : annosEncuesta) {
            annos.addItem(anno+"");
        }
        
        Vector<int[]> estadisticas = GestionBD.getEstadisticasDepartamentosXAno(Integer.parseInt(annos.getSelectedItem()+""));        
        actualizarTablaEstadisticas(estadisticas);
        
        }
    }

    @Override
     public void inputDialog_returnValue(Object returnValue, int selection) {
       int nuevoTotal = 0;
       
        try {
            nuevoTotal = Integer.parseInt(returnValue+"");
        } catch (NumberFormatException e) {
            MessageDialog message = new MessageDialog("El valor escogido debe ser un numero", "Error", Language.ES, this);
            return;
        }
       
       if(nuevoTotal < Integer.parseInt(tablaEstadisticas.getValueAt(tablaEstadisticas.getSelectedRow(), 8)+"")){
           MessageDialog message = new MessageDialog("Debe escoger un valor menor o igual al valor de encuestados", "Error", Language.ES, this);
           return;
       }
       
       GestionBD.actualizarTotalTrabajadores(tablaEstadisticas.getValueAt(tablaEstadisticas.getSelectedRow(), 0)+"", Integer.parseInt(annos.getSelectedItem()+""), nuevoTotal);
       Vector<int[]> estadisticas = GestionBD.getEstadisticasDepartamentosXAno(Integer.parseInt(annos.getSelectedItem()+""));        
       actualizarTablaEstadisticas(estadisticas);
     }

    @Override
    public void confirmDialog_returnValue(Object returnValue, int selection) {
        Boolean confirm = (boolean)returnValue;
        if(confirm){
         selectBD();
        }
        else{
            Main M = new Main();
            M.setVisible(true);
            dispose();
        }
    }
     
     
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SeleccionarBD = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEstadisticas = new javax.swing.JTable();
        salirBoton = new javax.swing.JButton();
        actualizarTotal = new javax.swing.JButton();
        mostrarDatos = new javax.swing.JButton();
        annos = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        selectBD = new javax.swing.JMenuItem();

        SeleccionarBD.setAcceptAllFileFilterUsed(false);
        SeleccionarBD.setAccessory(selectBD);
        SeleccionarBD.setBackground(java.awt.Color.darkGray);
        SeleccionarBD.setDialogTitle("Seleccionar Base de Datos");
        SeleccionarBD.setForeground(java.awt.Color.black);
        SeleccionarBD.setToolTipText("");
        SeleccionarBD.setDragEnabled(true);
        SeleccionarBD.setName(""); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaEstadisticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaEstadisticas);

        salirBoton.setText("Salir");
        salirBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirBotonActionPerformed(evt);
            }
        });

        actualizarTotal.setText("Modificar Total de Trabajadores");
        actualizarTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarTotalActionPerformed(evt);
            }
        });

        mostrarDatos.setText("Datos por Departamento");
        mostrarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarDatosActionPerformed(evt);
            }
        });

        annos.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                annosPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jMenuBar1.setMaximumSize(new java.awt.Dimension(60, 21));
        jMenuBar1.setMinimumSize(new java.awt.Dimension(60, 21));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(60, 21));

        jMenu1.setText("Integrar Base de Datos");

        selectBD.setText("Seleccionar Base de Datos");
        selectBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBDActionPerformed(evt);
            }
        });
        jMenu1.add(selectBD);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(actualizarTotal)
                        .addGap(48, 48, 48)
                        .addComponent(mostrarDatos)
                        .addGap(48, 48, 48)
                        .addComponent(annos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salirBoton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actualizarTotal)
                    .addComponent(mostrarDatos)
                    .addComponent(annos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salirBoton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void annosPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_annosPopupMenuWillBecomeInvisible
        
        Vector<int[]> estadisticas = GestionBD.getEstadisticasDepartamentosXAno(Integer.parseInt(annos.getSelectedItem()+""));        
        actualizarTablaEstadisticas(estadisticas);
        
    }//GEN-LAST:event_annosPopupMenuWillBecomeInvisible

    private void actualizarTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarTotalActionPerformed
        
        if(tablaEstadisticas.getSelectedRow() >= 0){
            InputDialog inputDialog = new InputDialog(1, "Introduzca el total de trabajadores", "Informacion", "", Language.ES, this);
        }
        else{
            MessageDialog messageDialog = new MessageDialog("Debe seleccionar un departamento de la tabla", "Error", Language.ES, this);
        }
        
    }//GEN-LAST:event_actualizarTotalActionPerformed

    private void mostrarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarDatosActionPerformed
        
        if(tablaEstadisticas.getSelectedRow() >= 0){
            EstadisticasDepartamento EstDep = new EstadisticasDepartamento(tablaEstadisticas.getValueAt(tablaEstadisticas.getSelectedRow(), 0)+"", Integer.parseInt(annos.getSelectedItem()+""));
            EstDep.setVisible(true);
            dispose();
        }
        else{
            MessageDialog messageDialog = new MessageDialog("Debe seleccionar un departamento de la tabla", "Error", Language.ES, this);
        }
        
    }//GEN-LAST:event_mostrarDatosActionPerformed

    private void salirBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirBotonActionPerformed
        
        Main M = new Main();
        M.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_salirBotonActionPerformed

    private void selectBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBDActionPerformed
       
        selectBD();
       
    }//GEN-LAST:event_selectBDActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser SeleccionarBD;
    private javax.swing.JButton actualizarTotal;
    private javax.swing.JComboBox<String> annos;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mostrarDatos;
    private javax.swing.JButton salirBoton;
    private javax.swing.JMenuItem selectBD;
    private javax.swing.JTable tablaEstadisticas;
    // End of variables declaration//GEN-END:variables

 private void actualizarTablaEstadisticas(Vector<int[]> est) {
        
        DefaultTableModel d = new DefaultTableModel() {
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
        Object[] OBJ = new Object[10];
        d.addColumn("Departamento");
        d.addColumn("Si");
        d.addColumn("<- %");
        d.addColumn("No");
        d.addColumn("<- %");
        d.addColumn("En ocasiones");
        d.addColumn("<- %");
        d.addColumn("Total Encuestados");
        d.addColumn("# Encuestados");
        d.addColumn("<- %");
        
        for (int i = 0; i < est.size()-1; i++) {
            
            if(est.elementAt(i)[3]==0){
                continue;
            }
            
            OBJ[0] = GestionBD.getDepartamento(i+1).getNombreDepartamento();
            OBJ[1] = est.elementAt(i)[0];
            OBJ[3] = est.elementAt(i)[1];
            OBJ[5] = est.elementAt(i)[2];
            OBJ[2] = (double)est.elementAt(i)[0]*100.0/((double)est.elementAt(i)[0]+(double)est.elementAt(i)[1] + (double)est.elementAt(i)[2]);
            OBJ[4] = (double)est.elementAt(i)[1]*100.0/((double)est.elementAt(i)[0]+(double)est.elementAt(i)[1] + (double)est.elementAt(i)[2]);
            OBJ[6] = (double)est.elementAt(i)[2]*100.0/((double)est.elementAt(i)[0]+(double)est.elementAt(i)[1] + (double)est.elementAt(i)[2]);
            OBJ[7] = est.elementAt(i)[3];
            OBJ[8] = est.elementAt(i)[4];
            OBJ[9] = (double)est.elementAt(i)[4]*100.0/(double)est.elementAt(i)[3];
            
            d.addRow(OBJ);
        }
        
        tablaEstadisticas = new JTable(d);
        
        tablaEstadisticas.setFont(new Font("arial", Font.BOLD, 14));
        tablaEstadisticas.setRowHeight(30);
        tablaEstadisticas.setShowGrid(true);
        
        jScrollPane1.setViewportView(tablaEstadisticas);
        
    }
 
    private void selectBD(){
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.db", "db");
        
        SeleccionarBD.setFileFilter(filtro);
        int seleccion = SeleccionarBD.showOpenDialog(this);
        
        if(seleccion == JFileChooser.APPROVE_OPTION){
            File file = SeleccionarBD.getSelectedFile();
            
            String direccion = file.getAbsolutePath();
            
            GestionBD.integrateDatabase(direccion);
            
            Vector<Integer> annosEncuesta = GestionBD.getAnnosEncuestas();
            
            for (Integer anno : annosEncuesta) {
                annos.addItem(anno+"");
            }
            
            Vector<int[]> estadisticas = GestionBD.getEstadisticasDepartamentosXAno(Integer.parseInt(annos.getSelectedItem()+""));        
            actualizarTablaEstadisticas(estadisticas);
        }
    }
}
