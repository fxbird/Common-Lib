package com.xdg.table.editor;

import com.xdg.util.ArrayUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class DropDownListEditor extends AbstractCellEditor implements  TableCellEditor , ItemListener{
    private JComboBox cb;
    private List data;
    private String idPropName;

    public DropDownListEditor(List data, String idPropName){
        cb = new JComboBox(data.toArray());
        this.data = data;
        this.idPropName=idPropName;
        cb.addItemListener(this);
    }

    public DropDownListEditor(List data){
        this(data,null);
    }

    public DropDownListEditor(Object[] data){
        this(ArrayUtil.array2List(data));
    }

    public DropDownListEditor(Object[] data, String idPropName){
        this(ArrayUtil.array2List(data),idPropName);
    }

    public Object getCellEditorValue() {
        try {
            if (StringUtils.isEmpty(idPropName)){
                return cb.getSelectedItem();
            }else{
                return PropertyUtils.getProperty(cb.getSelectedItem(),idPropName);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
             return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
             return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        for (int i = 0; i < data.size(); i++) {
             if (data.get(i).equals(value)){
                 cb.setSelectedIndex(i);
                 break;
             }
        }

        cb.setMaximumRowCount(25);

        return cb;
    }

    public void itemStateChanged(ItemEvent e) {
        stopCellEditing();
    }

    public JComboBox getCB(){
        return cb;
    }
}
