package com.xdg.util;

import org.apache.commons.lang.StringUtils;

import javax.swing.text.JTextComponent;

public class ValidatorUtil {
    public static boolean isEmpty(JTextComponent field, String name) {
        if (StringUtils.isEmpty(field.getText())) {
            AlertUtil.showMsg(null, name + " isn't filled");
            field.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(JTextComponent field){
        return StringUtils.isEmpty(field.getText());
    }

    public static boolean isNotEmpty(JTextComponent field){
        return !isEmpty(field);
    }

    public static boolean allHaveOrNot(String... values) {
        boolean firstAssigned = isAssignValue(values[0]);
        for (int i = 1; i < values.length; i++) {
            String value = values[i];
            if (isAssignValue(value)!=firstAssigned){
                return false;
            }
        }

        return true;
    }
    
    private static boolean isAssignValue(Object obj){
        if (obj==null) {
            return false;
        }else if (obj.toString().length()==0){
            return false;
        }else{
            return true;
        }

    }
}
