package com.xdg.util;

public class SysPropUtil {
    public static String getTempDir(){
        return System.getenv("TMP");
    }
}
