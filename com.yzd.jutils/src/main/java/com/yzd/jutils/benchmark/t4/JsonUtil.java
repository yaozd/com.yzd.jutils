package com.yzd.jutils.benchmark.t4;

/**
 * @author: yaozhendong
 * @create: 2019-11-28 10:03
 **/

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * @ Description：json工具类
 * @ Author     ：xiaojiang
 * @ Date       ：Created in 2019-06-19
 * @ Version    ：0.0.1
 */
public class JsonUtil {

    private static com.google.gson.Gson gson = new com.google.gson.GsonBuilder().create();
    private static com.fasterxml.jackson.databind.ObjectMapper jacksonMapper = new com.fasterxml.jackson.databind.ObjectMapper();

    public static String fastjson_bean2Json(Object object) {
        return com.alibaba.fastjson.JSON.toJSONString(object);
    }

    public static <T> T fastjson_json2Bean(String jsonStr, Class<T> objectClass) {
        return JSON.parseObject(jsonStr, objectClass);
    }

    public static String gson_bean2Json(Object object) {
        return gson.toJson(object);
    }

    public static <T> T gson_json2Bean(String jsonStr, Class<T> objectClass) {
        return gson.fromJson(jsonStr, objectClass);
    }

    public static String jackson_bean2Json(Object object) {
        try {
            return jacksonMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T jackson_json2Bean(String jsonStr, Class<T> objectClass) {
        try {
            return jacksonMapper.readValue(jsonStr, objectClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

