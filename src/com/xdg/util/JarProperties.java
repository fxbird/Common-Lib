package com.xdg.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;

public class JarProperties {
    private final static Log log= LogFactory.getLog(JarProperties.class);
    private Properties  properties=new Properties();;

    public JarProperties(String jarPath, String relativePath) {
        try {
           InputStream is=PathUtil.getFileUrlOfJar(jarPath,relativePath).openStream();
            properties.load(is);
        } catch (IOException e) {
            log.error(e);
        }
    }
    
    public JarProperties(String content){
        try {
            properties.load(new StringReader(content));
        } catch (IOException e) {
             log.error(e);
        }
    }

    public String get(String key, String dftValue){
       return properties.getProperty(key,dftValue);
    }

    public String get(String key){
       return properties.getProperty(key);
    }




}
