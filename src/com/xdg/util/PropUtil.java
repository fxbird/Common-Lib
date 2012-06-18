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
    private static final Map<String, PropertiesConfiguration> propConfigs=new HashMap<String, PropertiesConfiguration>();
    public static URL getFileURL(String pkgPath) {
        return PropUtil.class.getResource(pkgPath);
    }

    public static PropertiesConfiguration getPropConfig(String pkgPath) {
    	if (propConfigs.get(pkgPath)==null){
    		try {
				propConfigs.put(pkgPath, new PropertiesConfiguration(getFileURL(pkgPath)));
			} catch (ConfigurationException e) {
				log.error(e,e);
			}
    	}
    	
    	return propConfigs.get(pkgPath);
    }

    public static PropertiesConfiguration getPropConfigWithoutExtName(String path) {
        return getPropConfig(path + ".properties");
    }

    public static String getFromProp(PropertiesConfiguration config, String defaultValue, String... keys) {
        for (String key : keys) {
            if (StringUtils.isNotEmpty(config.getString(key))) {
                return config.getString(key);
            }
        }

        return defaultValue;
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
    
//    public static int addAndSave(PropertiesConfiguration config,String key,String value) throws ConfigurationException {
//        int cnt=1;
//        String oldValue= config.getString(key);
//        if (StringUtils.isEmpty(oldValue)){
//            config.setProperty(key, value);
//        } else{
//            String[] values=oldValue.split(",");
//            Set set=ArrayUtil.array2Set(values);
//            set.add(value);
//            cnt=set.size();
//            config.setProperty(key,ArrayUtil.set2StringByDeli(set,Const.PROP_DELI));
//        }
//        
//        config.save();
//        
//        return  cnt;
//    }
    
    public static void addAndSave(PropertiesConfiguration config,String key,String value) throws ConfigurationException {
    	config.setAutoSave(true);
        config.setProperty(key, value);
//        config.save();
        
    } 

}
