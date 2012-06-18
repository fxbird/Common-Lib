package com.xdg.util;

public class StringUtil {
    public static String after(String ori, String sub) {
        int idx = ori.indexOf(sub);
        if (idx < 0) {
            return "";
        } else {
            return ori.substring(idx + sub.length());
        }
    }
    
    public static String before(String ori,String sub){
        int idx=ori.indexOf(sub);
        if (idx==-1){
            return "";
        }else {
            return ori.substring(0,idx);
        }
    }

    public static String insertStr2LineHead(String original,String ins){
        original=original.replaceAll("\n","\n"+ins);
        original+=ins;

        return original;
    }
}
