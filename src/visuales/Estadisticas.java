/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visuales;

import base_de_datos.GestionBD;
import dialogs.AbstractFrame;
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
   
        Vector<Integer> annosEncuesta = GestionBD.getAnnosEncuestas();
        
        for (Integer anno : annosEncuesta) {
            annos.addItem(anno+"");
        }
        
        Vector<int[]> estadisticas = GestionBD.getEstadisticasDepartamentosXAno(Integer.parseInt(annos.getSelectedItem()+""));        
        actualizarTablaEstadisticas(estadisticas);
    }

    @Override
     public void inputDialog_returnValue(Object returnValue, int selection) {
       int nuevoTotal = Integer.parseInt(returnValue+"");
       
       GestionBD.actualizarTotalTrabajadores(tablaEstadisticas.getValueAt(tablaEstadisticas.getSelectedRow(), 0)+"", Integer.parseInt(annos.getSelectedItem()+""), nuevoTotal);
       Vector<int[]> estadisticas = GestionBD.getEstadisticasDepartamentosXAno(Integer.parseInt(annos.getSelectedItem()+""));        
       actualizarTablaEstadisticas(estadisticas);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(actualizarTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mostrarDatos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(annos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salirBoton)))
                .addContainerGap(14, Short.MAX_VALUE))
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
                .addContainerGap(26, Short.MAX_VALUE))
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
       
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.db", "db");
        
        SeleccionarBD.setFileFilter(filtro);
        int seleccion = SeleccionarBD.showOpenDialog(this);
        
        if(seleccion == JFileChooser.APPROVE_OPTION){
            File file = SeleccionarBD.getSelectedFile();
            
            String direccion = file.getAbsolutePath();
            
            GestionBD.integrateDatabase(direccion);
            Vector<int[]> estadisticas = GestionBD.getEstadisticasDepartamentosXAno(Integer.parseInt(annos.getSelectedItem()+""));        
            actualizarTablaEstadisticas(estadisticas);
        }
       
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
        d.addColumn("<- Porciento");
        d.addColumn("No");
        d.addColumn("<- Porciento");
        d.addColumn("En ocasiones");
        d.addColumn("<- Porciento");
        d.addColumn("Total de encuestados");
        d.addColumn("Numero de encuestados");
        d.addColumn("<- Porciento");
        
        for (int i = 0; i < est.size()-1; i++) {
            
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
}
