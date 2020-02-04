package com.yzd.jutils.config;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zd.yao on 2017/6/15.
 */
public class ConfigUtilTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getValueByProjectPropertiesFile("project.name"));
        System.out.println(getPayWxHBInterfaceUrl("test/api"));

    }

    private static String getPayWxHBInterfaceUrl(String relativeUrl) {
        String baseUrl = getValueByProjectPropertiesFile("interface.url");
        try {
            URL mergedURL = new URL(new URL(baseUrl), relativeUrl);
            return mergedURL.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getValueByProjectPropertiesFile(String key) {
        return ConfigUtil.get("project.properties", key);
    }
}
