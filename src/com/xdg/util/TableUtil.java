package com.xdg.util;


import javax.swing.*;
import javax.swing.table.*;
import java.util.Enumeration;
import java.util.List;

public class TableUtil {
    public static void setRender(JTable table, int col, TableCellRenderer render) {
        table.getColumnModel().getColumn(col).setCellRenderer(render);
    }

    public static void setEditor(JTable table, int col, TableCellEditor editor) {
        table.getColumnModel().getColumn(col).setCellEditor(editor);
    }

    public static void selectRow(JTable t, int row) {
        t.changeSelection(row, 0, false, false);
    }

    public static void setCellWidth(JTable view, int idx, int width) {
        TableColumn col = view.getColumnModel().getColumn(idx);
        //        col.sizeWidthToFit();
        col.setPreferredWidth(width);
        col.setMaxWidth(width + 5);
        ////        col.setMaxWidth(width);
        //        col.setPreferredWidth(width);
    }

    public static void stopEdit(JTable table) {
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }
    }

    public static void autoCellWidth(JTable jtable) {
        JTableHeader header = jtable.getTableHeader();
        int rowCount = jtable.getRowCount();
        Enumeration columns = jtable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) jtable.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(jtable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) jtable.getCellRenderer(row, col)
                        .getTableCellRendererComponent(jtable, jtable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column); //important
            column.setWidth(width + jtable.getIntercellSpacing().width + 5);
        }

        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    public static boolean hasSelectedRows(JTable table) {
        return table.getSelectedRow() >= 0;
    }

    public static boolean hasNotSelectedRows(JTable table) {
        return !hasSelectedRows(table);
    }

    public static void removeSelectedRow(JTable table) {
        if (table.getModel() instanceof AbstractTableModel) {
            AbstractTableModel model = (AbstractTableModel) table.getModel();
            for (int i = table.getSelectedRows().length - 1; i > -1; i--) {
                int row = table.getSelectedRows()[i];
                model.fireTableRowsDeleted(row, row);
            }
        }
    }

    public static void removeSelectedRowAndData(JTable table, List data) {
        if (table.getModel() instanceof AbstractTableModel) {
            AbstractTableModel model = (AbstractTableModel) table.getModel();
            for (int i = table.getSelectedRows().length - 1; i > -1; i--) {
                int row = table.getSelectedRows()[i];
                model.fireTableRowsDeleted(row, row);
                data.remove(row);
            }
        }
    }

}
