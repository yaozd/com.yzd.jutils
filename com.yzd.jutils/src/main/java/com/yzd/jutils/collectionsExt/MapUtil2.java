package com.yzd.jutils.collectionsExt;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zd.yao on 2017/8/1.
 */
public class MapUtil2 {

    public static Map<String, String> string2Map(String str, String firstSplit, String secondSplit) {
        String[] strs = str.split(firstSplit);
        Map<String, String> m = new HashMap<String, String>();
        if (strs.length == 0) {
            return m;
        }
        for (String s : strs) {
            String[] ms = s.split(secondSplit);
            m.put(ms[0], ms[1]);
        }
        return m;
    }

    public static void main(String[] args) {
        t1();
        t2();
    }

    private static void t1() {
        String str = "color:red|font:yahei|width:800|height:300";
        Map<String, String> m = string2Map(str, "\\|", ":");
        for (Map.Entry<String, String> entry : m.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    private static void t2() {
        String str = "color=red1111&font=yahei&width=800&height=300";
        Map<String, String> m = string2Map(str, "&", "=");
        for (Map.Entry<String, String> entry : m.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        String value = m.get("color");
        System.out.println("color=" + value);
        ;
    }
}
