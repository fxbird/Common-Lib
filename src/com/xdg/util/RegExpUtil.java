package com.xdg.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpUtil {
    public static String getOnce(String pattern, String text){
        Pattern ptn=Pattern.compile(pattern);
        Matcher matcher = ptn.matcher(text);
        if (!matcher.find()){
            return "";
        }else {
            return matcher.group();
        }
    }
}
