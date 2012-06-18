package com.xdg.util;

import java.awt.*;

public class CursorUtil {
    public static void normal(Container container){
        container.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    public static void wait(Container container){
        container.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    }
}
