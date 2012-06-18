package com.xdg.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class PathUtil {
    private static Log log= LogFactory.getLog(PathUtil.class);
    public static String replaceDot(String path) {
        return path.replace(".", "/");
    }

    public static String replaceAntiSlash(String path) {
        return path.replace("\\", "/");
    }

    public static String replaceSlash(String path) {
        return path.replace("/", "\\");
    }


    public static String getFilePhyPath(String proj, String src, String pkg, String name, String ext) {
        String rst = proj + "/" + src + "/";

        if (StringUtils.isNotEmpty(pkg)) {
            rst += replaceDot(pkg);
        }
        rst = replaceAntiSlash(rst);
        new File(rst).mkdirs();

        return rst + "/" + name + "." + ext;
    }

    public static String getFilePhyPath(String proj, String src, String pkg, String name) {
        return getFilePhyPath(proj, src, pkg, name, "java");
    }


    public static String getDirPhyPath(String pathBeforePkg, String pkg) {
        pathBeforePkg = replaceAntiSlash(pathBeforePkg);
        return pathBeforePkg + "/" + replaceDot(pkg);
    }

    public static String getDirPath(String filePath) {
        filePath=replaceAntiSlash(filePath);
        if (isFile(filePath)){
            String fname=getFileNameFromPath(filePath);
            return filePath.substring(0,filePath.lastIndexOf(fname));
        } else {
            return filePath;
        }
    }

    public static String trimEndingSlash(String path) {
        if (path.endsWith("\\") || path.endsWith("/")) {
            return path.substring(0, path.length() - 1);
        } else {
            return path;
        }
    }

    public static String wellformPath4Java(String path) {
        path = PathUtil.replaceAntiSlash(path);
        path = PathUtil.trimEndingSlash(path);
        path = StringUtils.capitalise(path);

        return path;
    }

    public static String getFileNameFromPath(String projPath) {
        projPath = replaceAntiSlash(projPath);
        if (projPath.endsWith("/")) {
            projPath = projPath.substring(0, projPath.length() - 1);
        }

        int lastIdx = projPath.lastIndexOf("/");
        return projPath.substring(lastIdx + 1);
    }

    public static String getExtName(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static String getPathFromURL(URL url) {
        try {
            return new File(url.toURI()).getAbsolutePath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static URL getFileUrlOfJar(String jarPath, String relativePath) {
        String fPath = relativePath.startsWith("/") ? relativePath : "/" + relativePath;
        URL url = null;
        try {
            url = new URL("jar:file:/" + jarPath + "!" + fPath);
        } catch (MalformedURLException e) {
            log.error(e);
            return null;
        }
        
        return url;
    }

    public static String convertQNamePhyPath(String qName){
        return replaceDot(qName)+".java";
    }

    public static String getPkgFromQName(String qName){
        return qName.substring(0,qName.lastIndexOf("."));
    }

    public static String getClsNameFromQName(String qName){
        return qName.substring(qName.lastIndexOf(".")+1);
    }
    
    public static boolean isFile(String path){
        String tempPath =replaceAntiSlash(path);
        if (tempPath.lastIndexOf(".")>tempPath.lastIndexOf("/")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isDirectory(String path){
        return !isFile(path);
    }
    
    public static String remove(String path,String remove){
    	path=replaceSlash(path);
    	remove=replaceSlash(remove);
    	return StringUtils.remove(path, remove);
    }

    public static String getRelativePath(String path,String base){
        base=replaceAntiSlash(base);
        path=replaceAntiSlash(path);
        if (!path.contains(base)){
            return path;
        }

        path=path.replace(base,"");
        return path.substring(1);
    }

    public static String removeExtName(String path){
        return path.substring(0,path.lastIndexOf("."));
    }
}
