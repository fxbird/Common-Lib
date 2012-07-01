package com.xdg.util;

import com.xdg.bean.JarFileItem;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReflectUtil {
    public static List<String> getPublicField(Class clazz) {
        List<String> rst = new ArrayList<String>();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            String fieldName = field.getName();
//            if (Modifier.isPublic(field.getModifiers())){
            rst.add(fieldName);
//            }
        }

        return rst;
    }

    public static List<String> getFieldFromGetter(Class clazz) {
        List<String> rst = new ArrayList<String>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("get") || name.startsWith("is") || Modifier.isPublic(method.getModifiers()) && !method.getReturnType().equals(Void.TYPE) &&
                    !method.isVarArgs()) {
                if (name.startsWith("get")) {
                    rst.add(StringUtils.uncapitalize(name.substring(3)));
                } else {
                    rst.add(StringUtils.uncapitalize(name.substring(2)));
                }
            }
        }

        if (!rst.isEmpty()) {
            Collections.sort(rst, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
        }

        return rst;
    }

    public static List<Class> findClass(String... path) throws IOException, ClassNotFoundException {
        List<Class> clazzs = new ArrayList<Class>();
        for (int i = 0; i < path.length; i++) {
            String p = path[i];
            if (p.endsWith(".jar")) {
                clazzs.addAll(findClassFromJar(p));
            } else {
                clazzs.addAll(findClass(p));
            }
        }

        return clazzs;
    }

//    public static List<Class> findClass(String proj) throws ClassNotFoundException, MalformedURLException {
//
//        ClassLoader originalLoader=Thread.currentThread().getContextClassLoader();
//
//        URLClassLoader classLoader=new URLClassLoader(HGUtil.getClassURLs(proj),originalLoader);
//
//        Thread.currentThread().setContextClassLoader(classLoader);
//        List<File> classes = FileUtil.findFilesByKeyword(proj+"\\web\\WEB-INF\\classes", "$", false, "class");
//        List<Class> rst = new ArrayList<Class>();
//        for (File clazz : classes) {
//            String qname = PathUtil.getRelativePath(clazz.getAbsolutePath(), proj+"\\web\\WEB-INF\\classes").replace("\\", ".");
//            rst.add(classLoader.loadClass(qname));
//        }
//
//        return rst;
//    }

    public static List<Class> findClassFromJar(String jarPath) throws ClassNotFoundException, IOException {
        List<JarFileItem> items = FileUtil.findFilesFromJarByKeyword(jarPath, "$", false, "class");
        List<Class> result = new ArrayList<Class>();
        for (JarFileItem item : items) {
            result.add(Class.forName(PathUtil.removeExtName(item.getItemPath())));
        }

        return result;
    }


}
