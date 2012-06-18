package com.xdg.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

public class FMUtil {
    //    public static Configuration config;
    public static Configuration getConfigIns(String ftlBasePath) {
//        if (config!=null)  {
//            return config;
//        }
        Configuration config = new Configuration();
        try {
            config.setDirectoryForTemplateLoading(new File(ftlBasePath));
            config.setObjectWrapper(new DefaultObjectWrapper());
            return config;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Template createTemplate(Configuration config, String tplName) {
        try {
            return config.getTemplate(tplName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void process(Template tpl, String outFilePath, Map root) throws IOException, TemplateException {
        FileUtil.makeDirsOfFileWhenNeeded(outFilePath);
        FileWriter fileWriter = new FileWriter(outFilePath);
        tpl.process(root, fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }
    
    public static String processWithRtn(Template tpl, Map root)throws IOException, TemplateException {
        StringWriter writer=new StringWriter();
        tpl.process(root,writer);
        writer.flush();
        writer.close();
        return writer.toString();
    }
}
