package com.yzd.jutils.fileExt;

import com.yzd.jutils.config.ConfigUtil;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class _MainTest {
    /**
     * 读取根目录文件,两种方式
     * FileUtil.class.getClass().getResourceAsStream(path);
     * FileUtil.class.getClassLoader().getResourceAsStream(jsonPath);
     */
    @Test
    public void fileUtil_Test(){
        String insurerXmlStr = FileUtil.read("/project.properties", "utf-8");
        System.out.println(insurerXmlStr);
        String strValue=FileUtil.readJson("project.properties");
        System.out.println(strValue);
    }

    /**
     *
     */
    @Test
    public void ConfigUtil_Test() {
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
