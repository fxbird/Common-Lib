/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xdg.table.editor;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class CheckBoxEditor extends AbstractCellEditor
        implements TableCellEditor {

    private JCheckBox cb;
    private Object valueTrue;
    private Object valueFalse;
	private TableCellEditor editor;

    public CheckBoxEditor(Object vluTrue, Object vluFalse,JTable table) {
        this.valueTrue=vluTrue;
        this.valueFalse=vluFalse;
        editor = table.getDefaultEditor(Boolean.class);
    }

    public Object getCellEditorValue() {
        
        if (editor.getCellEditorValue().equals(Boolean.TRUE)){
        	return valueTrue;
        } else {
        	return valueFalse;
        }
        
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
      return editor.getTableCellEditorComponent(table,(value.equals(valueTrue)? new Boolean(true):new Boolean(false)),isSelected,row,column);
    }
}
