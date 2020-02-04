package com.yzd.jutils.jsonExt.jsoniterExt;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;

public class JsoniterHelper {

    public static <T> String serialize(T object) {
        return JsonStream.serialize(object);
    }

    public static <T> T deserialize(String json, Class<T> clz) {
        return JsonIterator.deserialize(json, clz);
    }
}
