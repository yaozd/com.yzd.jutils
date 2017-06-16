package com.yzd.jutils.config;

/**
 * Created by zd.yao on 2017/6/5.
 */
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * 读取配置文件类
 * 根据配置文件名和属性key返回属性内容，configUtil.get(configFile, property);
 * ConfigUtil.get("/common/velocity.properties", "input.encoding");
 * Created by zd.yao on 2017/4/12.
 */
public class ConfigUtil {

    private static ConfigUtil initor = new ConfigUtil();

    private static Map<String, PropertiesConfiguration> configMap = new HashMap<String, PropertiesConfiguration>();

    private ConfigUtil() {}

    /**
     * 获取内容
     * @param configFile
     * @param property
     * @return
     */
    public static String get(String configFile, String property) {
        if(!configMap.containsKey(configFile)) {
            initor.initConfig(configFile);
        }
        PropertiesConfiguration config = configMap.get(configFile);
        String value = config.getString(property);
        //TODO LOG
        return value;
    }

    /**
     * 载入配置文件，初始化后加入map
     * @param configFile
     */
    private synchronized void initConfig(String configFile) {
        try {
            PropertiesConfiguration config = new PropertiesConfiguration(configFile);
            configMap.put(configFile, config);

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
