package com.xdg.util;

import com.xdg.bean.Pair;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PropUtil {
    private static final Log log = LogFactory.getLog(PropUtil.class);
    private static final Map<String, PropUtil> propConfigs = new HashMap<String, PropUtil>();
    private PropertiesConfiguration config;

    private PropUtil(String pkgPath) throws ConfigurationException {
        config = new PropertiesConfiguration(getFileURL(pkgPath));
    }

    public static URL getFileURL(String pkgPath) {
        return PropUtil.class.getResource(pkgPath);
    }

    public static PropUtil getInstance(String pkgPath) {
        if (propConfigs.get(pkgPath) != null) {
            return propConfigs.get(pkgPath);
        } else {
            try {
                propConfigs.put(pkgPath, new PropUtil(pkgPath));
                return propConfigs.get(pkgPath);
            } catch (ConfigurationException e) {
                log.error(e);
                return null;
            }
        }
    }

    public String getString(String key) {
        return getConfig().getString(key);
    }

    public String getString(String key, String dftValue) {
        return getConfig().getString(key,dftValue);
    }

    public long getLong(String key){
        return getConfig().getLong(key);
    }

    public long getLong(String key,long dftValue){
        return getConfig().getLong(key,dftValue);
    }

    public int getInt(String key){
        return getConfig().getInt(key);
    }

    public int getInt(String key,int dftValue){
        return getConfig().getInt(key,dftValue);
    }

    public static List<Pair<String>> getAllEntries(PropertiesConfiguration config) {
        Iterator<String> it = config.getKeys();
        List<Pair<String>> entries = new ArrayList<Pair<String>>();
        while (it.hasNext()) {
            String key = it.next().toString();
            Pair<String> entry = new Pair<String>(key, config.getString(key));
            entries.add(entry);
            log.debug("url key : " + key);
        }

        return entries;
    }

    public void addAndSave(String key, String value)  {
        getConfig().setAutoSave(true);
        getConfig().setProperty(key, value);
        try {
            getConfig().save();
        } catch (ConfigurationException e) {
            log.error(e);
        }
    }

    public PropertiesConfiguration getConfig() {
        return config;
    }
}
