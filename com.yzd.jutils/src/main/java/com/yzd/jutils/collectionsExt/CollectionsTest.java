package com.yzd.jutils.collectionsExt;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zd.yao on 2017/7/13.
 */
public class CollectionsTest {
    public static void main(String[] args){
        //相同的KEY数据是替换的
        Map<String, Object> finalDataMap = new ConcurrentHashMap<>();
        finalDataMap.put("now","1");
        finalDataMap.put("now","3");
        //finalDataMap.size()=1
        System.out.println("finalDataMap.size()="+finalDataMap.size());
        //Collections unmodifiableMap 这个的作用是什么
        //不可修改集合
        //https://zhidao.baidu.com/question/448914033.html
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> map2 = Collections.unmodifiableMap(map);
        map2.put("1", "1");
        //java怎么用一行代码初始化ArrayList
        List<String> places = Arrays.asList("Buenos Aires", "Córdoba", "La Plata");
    }
}
