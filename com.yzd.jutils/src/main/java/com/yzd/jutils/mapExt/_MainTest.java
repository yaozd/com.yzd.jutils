package com.yzd.jutils.mapExt;

import com.yzd.jutils.fastjson.FastJsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zd.yao on 2017/8/16.
 */
public class _MainTest {
    public static void main(String[] args) {
        //begin 通过map数据类型与json字符类型串的转换灵活配置信息
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("PRIVATE_KEY_HB", "1");
        mapParam.put("PUBLIC_KEY_ZA", "2");
        String mapStr = FastJsonUtil.serialize(mapParam);
        Map<String, Object> map = FastJsonUtil.deserialize(mapStr, HashMap.class);
        //end

    }
}
