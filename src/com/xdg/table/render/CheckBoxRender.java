/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xdg.table.render;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 *
 * @author fxbird
 */
//public class CheckBoxRender implements TableCellRenderer{
//    private int valueTrue;
//    private int valueFalse;
//    public CheckBoxRender(int vluTrue,int vluFalse){
//        this.valueTrue=vluTrue;
//        this.valueFalse=vluFalse;
//    }
//
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//      JCheckBox cb=new JCheckBox();
//      cb.setSelected(value.toString().equals(valueTrue+""));
//      JPanel in=new JPanel();
//      in.setLayout(new BoxLayout(in, BoxLayout.Y_AXIS));
//      in.add(Box.createGlue());
//      in.add(cb);
//      in.add(Box.createGlue());
//      JPanel out=new JPanel();
//      out.add(in,BorderLayout.CENTER);
////      return cb;
//      return out;
//    }
//}


public class CheckBoxRender implements TableCellRenderer{
    private Object valueTrue;
    private Object valueFalse;
    TableCellRenderer render;
    public CheckBoxRender(Object vluTrue,Object vluFalse,JTable table){
        this.valueTrue=vluTrue;
        this.valueFalse=vluFalse;
        render = table.getDefaultRenderer(Boolean.class);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp= render.getTableCellRendererComponent(table,valueTrue.equals(value), isSelected,hasFocus,row,column);
        return comp;
        
    }
}

