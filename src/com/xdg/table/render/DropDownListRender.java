package com.xdg.table.render;

import com.xdg.util.ArrayUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DropDownListRender implements TableCellRenderer {
    private JLabel text = new JLabel();
    private Map mapData = new HashMap();

    public DropDownListRender(List data, String descPropName) {
        this(data, null, descPropName);
    }

    public DropDownListRender(List data) {
        this(data, null, null);
    }

    public DropDownListRender(Object[] data) {
        this(ArrayUtil.array2List(data), null, null);
    }

    public DropDownListRender(Object[] data, String descPropName) {
        this(ArrayUtil.array2List(data), descPropName);
    }

    public DropDownListRender(Object[] data, String idPropName, String descPropName) {
        this(ArrayUtil.array2List(data), idPropName, descPropName);
    }

    public DropDownListRender(List data, String idPropName, String descPropName) {
        try {
            for (int i = 0; i < data.size(); i++) {
                Object elem = data.get(i);
                Object key = null;
                if (StringUtils.isEmpty(idPropName)) {
                    key = elem;
                } else {
                    key = PropertyUtils.getProperty(elem, idPropName);
                }

                Object value = StringUtils.isEmpty(descPropName) ? elem.toString() : PropertyUtils.getProperty(elem, descPropName);
                mapData.put(key, value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        text.setOpaque(true);
        if (mapData.containsKey(value)) {
            text.setText(mapData.get(value).toString());
        } else {
            text.setText("");
        }
        text.setHorizontalAlignment(JLabel.CENTER);
//            System.out.println("test name: "+mapData.get(value).toString()+" test id: "+value.toString());

        //        text.setBackground(Color.red);
        return text;
    }

    public JLabel getComponent() {
        return text;
    }
}
