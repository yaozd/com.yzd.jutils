package com.yzd.jutils.collectionsExt;

import java.util.*;

/**
 * Created by zd.yao on 2017/7/13.
 */
public class CollectionsTest {
    public static void main(String[] args){
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
