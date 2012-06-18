package com.xdg.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MiscUtil {
    private final static Log log = LogFactory.getLog(MiscUtil.class);

    public static File selectFile(Component parent, String currentPath, boolean canSelDir) {
        JFileChooser jfc = new JFileChooser(new File(currentPath));
        if (canSelDir) {
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        }

        int rst = jfc.showOpenDialog(parent);
        if (rst != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        return jfc.getSelectedFile();
    }

    public static File selectFile(Component parent, String currentPath) {
        return selectFile(parent, currentPath, true);
    }

    public static void fillCombox(JComboBox cb, Collection values, boolean sort) {
        cb.removeAllItems();
        if (values == null) {
            return;
        }

        if (sort == false) {
            for (Object obj : values) {
                cb.addItem(obj);
            }
        } else {
            TreeSet set = new TreeSet();
            for (Object obj : values) {
                set.add(obj);
            }

            fillCombox(cb, set, false);
        }
    }

    public static void fillCombox(JComboBox cb, Collection values) {
        fillCombox(cb, values, true);
    }

    public static void fillList(JList list, Collection values) {
        DefaultListModel model = new DefaultListModel();
        for (Object obj : values) {
            model.addElement(obj);
        }

        list.setModel(model);


    }

    public static void displayInMiddle(Window win) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        win.setLocation(size.width / 2 - win.getWidth() / 2,
                size.height / 2 - win.getHeight() / 2);
    }

    public static void setTextSafely(final JLabel label, final String text) {
//        try {
//            SwingUtilities.invokeAndWait(new Runnable() {
//                public void run() {
//                    label.setText(text);
//                }
//            });
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

//
//        new Thread(new Runnable() {
//            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                           public void run() {
                               label.setText(text);
                           }
                       });
//            }
//        }).start();

    }

    public static void serialize(Object obj,String fullPath) throws IOException {
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(fullPath));
            oos.writeObject(obj);
            oos.close();
        }

        public static Object deserialize(String fullPath){
            ObjectInputStream ois= null;
            try {
                ois = new ObjectInputStream(new FileInputStream(fullPath));
                return ois.readObject();
            } catch (IOException e) {
                log.error(e,e);
                return null;
            } catch (ClassNotFoundException e) {
                log.error(e,e);
                return null;
            }
        }

}
