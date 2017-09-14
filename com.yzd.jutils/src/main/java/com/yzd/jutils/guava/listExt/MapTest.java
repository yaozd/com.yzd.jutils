package com.yzd.jutils.guava.listExt;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zd.yao on 2017/9/14.
 */
public class MapTest {
    //Java中如何遍历Map对象的4种方法
    //http://blog.csdn.net/tjcyjd/article/details/11111401
    //===
    //在for-each循环中使用entries来遍历
    @Test
    public void t1() {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    @Test
    public void t2() {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        //遍历map中的键
        for (Integer key : map.keySet()) {
            System.out.println("Key = " + key);
        }
        //遍历map中的值
        for (Integer value : map.values()) {
            System.out.println("Value = " + value);
        }
    }
    // region t3 与 t4是同一种类型 不推荐
    @Test
    public void t3() {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Integer> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    @Test
    public void t4() {
        Map map = new HashMap();
        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            Integer key = (Integer)entry.getKey();
            Integer value = (Integer)entry.getValue();
            System.out.println("Key = " + key + ", Value = " + value);
        }
    }
    //endregion
    //===
    //通过键找值遍历（效率低）不推荐
    @Test
    public void t5() {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Integer key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println("Key = " + key + ", Value = " + value);
        }
    }
}
