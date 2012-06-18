package com.xdg.util;

import javax.swing.*;
import java.awt.*;

public class AlertUtil {
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

    public static void showError(Component parent, Exception e) {
        StringBuffer s = new StringBuffer(e.toString() + "\n");
        StackTraceElement[] trace = e.getStackTrace();
        for (int i = 0; i < trace.length; i++)
            s.append("\tat " + trace[i] + "\n");
        JOptionPane.showMessageDialog(parent, s.toString());
    }

    public static void showMsg(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg);
    }
}
