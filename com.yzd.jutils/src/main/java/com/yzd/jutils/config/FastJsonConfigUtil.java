package com.yzd.jutils.config;

import com.yzd.jutils.fastjson.FastJsonUtil;
import com.yzd.jutils.print.PrintUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zd.yao on 2017/8/18.
 */
public class FastJsonConfigUtil {
    //通过json配置文件转为map的格式来实现灵活的数据配置
    public static String getValue(String jsonConfig, String key) {
        Map<String, Object> m = FastJsonUtil.deserialize(jsonConfig, HashMap.class);
        return m.get(key).toString();
    }

    public static void main(String[] args) {
        String PUBLIC_KEY_ZA = "PUBLIC_KEY_ZA";
        String PRIVATE_KEY_HB = "PRIVATE_KEY_HB";
        String APP_KEY = "APP_KEY";
        String EVN = "EVN";
        String jsonConfig = "{\"PUBLIC_KEY_ZA\":\"123456\"}";
        String val = getValue(jsonConfig, PUBLIC_KEY_ZA);
        PrintUtil.outLn(val);
    }
}
