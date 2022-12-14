/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author Joan Manuel
 */
public class CheckBoxEditor extends DefaultCellEditor implements ItemListener {
  private JCheckBox button;

  public CheckBoxEditor(JCheckBox checkBox) {
    super(checkBox);
  }

  @Override
  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    if (value == null)
      return null;
    //button = (JRadioButton) value;
    button = (JCheckBox)value;
    button.addItemListener(this);
    return (Component) value;
  }

  public Object getCellEditorValue() {
    button.removeItemListener(this);
    return button;
  }

  public void itemStateChanged(ItemEvent e) {
    super.fireEditingStopped();
  }
}