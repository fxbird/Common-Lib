package com.xdg;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XdgUtil {
    private final static Log log = LogFactory.getLog(XdgUtil.class);
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }

        if (str.toString().trim().length() == 0) {
            return true;
        }

        return false;
    }

    public static String judgeEmpOrNull(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }

    /**
     * take effects in the whole frame
     *
     * @param comp
     * @param key
     * @param action
     */
    public static void addShortcut(JComponent comp, KeyStroke key, Action action) {
        InputMap imap = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap amap = comp.getActionMap();
        String mapKey = "sc-" + new Random().nextInt();
        imap.put(key, mapKey);
        amap.put(mapKey, action);
    }

    public static void addShortcut(JComponent comp, String key, Action action) {
        addShortcut(comp, KeyStroke.getKeyStroke(key), action);
    }

    /**
     * take effects in the specific frame
     * @param comp
     * @param key
     * @param action
     */
    public static void addShortcutFocus(JComponent comp, KeyStroke key, Action action) {
        InputMap imap = comp.getInputMap();
        ActionMap amap = comp.getActionMap();
        String mapKey = "sc-" + new Random().nextInt();
        imap.put(key, mapKey);
        amap.put(mapKey, action);
    }

    public static void addShortcutFocus(JComponent comp, String key, Action action) {
        addShortcutFocus(comp, KeyStroke.getKeyStroke(key), action);
    }

    public static void enableInsertTabOnNewLine(final JTextArea ta) {
        addShortcutFocus(ta, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK), new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                ta.insert("\n\t", ta.getCaretPosition());
//                    taMeaning.setCaretPosition(taMeaning.getCaretPosition()+2);

            }
        });
    }

    /**
     * delete current line in the content
     * the line deleted actually will be current pure line content(no ending '\n') and previous '\n'
     *
     * @param ta
     */
    public static void deleteCurrentRow(JTextArea ta) {
        if (!ta.hasFocus()) {
            return;
        }

        if (ta.getText().indexOf("\n") == -1) {
            ta.setText("");
            return;
        }

        int posNow = ta.getCaretPosition();
        int posPrev = posNow;
        int posLatter = posNow;
        String text = ta.getText();
        boolean firstRow=false;
        boolean lastRow=false;

        //look for the previous '\n'
        while (--posPrev >= 0) {
            if (text.charAt(posPrev) == '\n') {
                break;
            }
        }

        if (posPrev < 0) {
            firstRow=true;
        }


        //look for current line content without ending '\n'
        while (posLatter < text.length()) {
            if (text.charAt(posLatter) == '\n') {
//                if (firstRow){
//                    if (posLatter+1<text.length()){
//                       if (text.charAt(posLatter+1)=='r'){
//
//                       }
//                    }
//                }
                break;
            }

            posLatter++;
        }

        if (posLatter >=text.length()){
            lastRow=true;
        }



        if (lastRow){//if last row,delete content and left '\n'
            posLatter =text.length()-1;
        }else{ //if first row or middle row,delete content and right '\n'
            if (firstRow){
                posPrev=0;
            }else {
                posPrev++;
            }
        }
        System.out.printf("posPrev=%d,posLatter=%d\n", posPrev, posLatter);
        ta.replaceRange("", posPrev, posLatter+1);
    }

    public static String readFromClipboard() throws IOException, UnsupportedFlavorException {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tra = clip.getContents(null);
        String str = (String) tra.getTransferData(DataFlavor.stringFlavor);
        return str;
    }

    public static void copy(String str) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(str);//将字符串包装
        clip.setContents(selection, null);
    }

    public static void displayInMiddle(Window win) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        win.setLocation(size.width / 2 - win.getWidth() / 2,
                size.height / 2 - win.getHeight() / 2);
    }



    public static void setEnglishInput(Component f) {
        f.getInputContext().selectInputMethod(Locale.US);
    }

    public static void setChineseInput(Component f) {
        f.getInputContext().selectInputMethod(Locale.CHINA);
    }







    public static void showMsg(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg);
    }

    public static void showError(Component parent, Exception e) {
        StringBuffer s = new StringBuffer(e.toString() + "\n");
        StackTraceElement[] trace = e.getStackTrace();
        for (int i = 0; i < trace.length; i++)
            s.append("\tat " + trace[i] + "\n");
        JOptionPane.showMessageDialog(parent, s.toString());
    }

    public static int showConfirm(Component parent, String prompt) {
        int rst = JOptionPane.showConfirmDialog(parent, prompt, "Prompt", JOptionPane.YES_NO_CANCEL_OPTION);
        switch (rst) {
            case JOptionPane.YES_OPTION:
                return 1;
            case JOptionPane.NO_OPTION:
                return 2;
            default:
                return 3;
        }
    }


    public static void tablizeTextArea(final JTextArea ta) {
        ta.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                int end = ta.getSelectionEnd();
                int start = ta.getSelectionStart();
                if (evt.getKeyCode() == KeyEvent.VK_TAB) {
                    String selText = ta.getSelectedText();
                    evt.isControlDown();

                    if (isNotEmptyOrNull(selText)) {

                        int rows = (selText.split("\n").length == 0 ? 1 : selText.split("\n").length);
                        selText = "\t" + selText.replaceAll("\n", "\n\t");
                        ta.replaceSelection(selText);
                        ta.select(start, end + rows);


//                        ta.replaceSelection(selText.replaceAll("^|\\n(?!$)", "$0"));
                    } else {
                        int curPos = ta.getCaretPosition();
                        ta.insert("\t", curPos);
                    }
                }
            }
        });


    }

    public static boolean isNotEmptyOrNull(String str) {
        if (str == null) {
            return false;
        } else {
            return str.trim().length() > 0;
        }
    }

    public static boolean checkFileExist(String path){
        return new File(path).exists();
    }

    public static boolean fileCopy(String souName, String destName) {
        boolean succ = true;
        FileOutputStream fos = null;
        try {
            FileInputStream fis = new FileInputStream(new File(souName));
            File destFile = new File(destName);
            fos = new FileOutputStream(destFile);
            byte[] data = new byte[1024 * 10];
            int cnt = -1;
            while ((cnt = fis.read(data)) != -1) {
                fos.write(data, 0, cnt);
            }
        } catch (Exception ex) {
            succ = false;
        }

        return succ;
    }



}
