package com.xdg.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ListUtil {
    private final static Log log = LogFactory.getLog(ListUtil.class);

    public static void swap(List list, int a, int b) {
        Object oldA = list.get(a);
        list.set(a, list.get(b));
        list.set(b, oldA);
    }

    public static List filter(List data, String field, String search) {
        return filter(data, field, search, false);
    }

    public static List filter(List data, String field, String search, boolean blur) {
        ArrayList list = new ArrayList();
        for (Object obj : data) {
            try {
                if (blur) {
                    if (BeanUtils.getProperty(obj, field).toLowerCase().contains(search.toLowerCase())) {
                        list.add(obj);
                    }
                } else if (search.equalsIgnoreCase(BeanUtils.getProperty(obj, field))) {
                    list.add(obj);
                }
            } catch (IllegalAccessException e) {
                log.error(e, e);
            } catch (InvocationTargetException e) {
                log.error(e, e);
            } catch (NoSuchMethodException e) {
                log.error(e, e);
            }
        }

        return list;
    }

    public static void putToQueue(List list, Queue queue) {
        for (Object obj : list) {
            queue.add(obj);
        }
    }

    public static List subList(List list, int start, int end) {
        if (start + 1 > list.size()) {
            return null;
        }

        if (end + 1 > list.size()) {
            end = list.size() - 1;
        }

        ArrayList rst = new ArrayList();
        for (int i = start; i <= end; i++) {
            rst.add(list.get(i));
        }

        return rst;
    }

    public static List<List> split(List list, int groupSize) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        } else {
            ArrayList rst = new ArrayList();
            int i = 0;
            List temp = null;
            while (true) {
                if ((temp = subList(list, i, i + groupSize - 1)) != null) {
                    i=i+groupSize;
                    rst.add(temp);
                } else {
                    return rst;
                }
            }
        }
    }

    public static String toString(List list){
        StringBuffer sb=new StringBuffer();
        sb.append("{");
        for (Object ele : list) {
            sb.append(ele+",");
        }

        if (sb.length()>1){
            sb.delete(sb.length()-1,sb.length());
        }
        sb.append("}");

        return sb.toString();
    }


}
