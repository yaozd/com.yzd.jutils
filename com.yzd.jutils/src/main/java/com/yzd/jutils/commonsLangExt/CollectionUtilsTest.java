package com.yzd.jutils.commonsLangExt;

import org.apache.commons.collections.*;
import org.apache.commons.collections.bidimap.TreeBidiMap;
import org.apache.commons.collections.map.LinkedMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CollectionUtilsTest {
    //apache工具类之CollectionUtils(待续更新)
    //https://blog.csdn.net/wangxy799/article/details/70911064?utm_source=blogxgwz4
    //apache commons collections CollectionUtils工具类简单使用
    //https://blog.csdn.net/zwfwgood/article/details/70500016?utm_source=blogxgwz5
    //Commons Collections4 简单使用
    //https://blog.csdn.net/sinat_34093604/article/details/79551924?utm_source=blogxgwz1

    @Test
    public void t1() {
        List<Integer> a = new ArrayList<Integer>();
        List<Integer> b = null;
        List<Integer> c = new ArrayList<Integer>();
        c.add(5);
        c.add(6);
        //判断集合是否为空
        System.out.println(CollectionUtils.isEmpty(a));   //true
        System.out.println(CollectionUtils.isEmpty(b));   //true
        System.out.println(CollectionUtils.isEmpty(c));   //false

        //判断集合是否不为空
        System.out.println(CollectionUtils.isNotEmpty(a));   //false
        System.out.println(CollectionUtils.isNotEmpty(b));   //false
        System.out.println(CollectionUtils.isNotEmpty(c));   //true

        //两个集合间的操作
        List<Integer> e = new ArrayList<Integer>();
        e.add(2);
        e.add(1);
        List<Integer> f = new ArrayList<Integer>();
        f.add(1);
        f.add(2);
        List<Integer> g = new ArrayList<Integer>();
        g.add(12);
        //比较两集合值
        System.out.println(CollectionUtils.isEqualCollection(e, f));   //true
        System.out.println(CollectionUtils.isEqualCollection(f, g));   //false

        List<Integer> h = new ArrayList<Integer>();
        h.add(1);
        h.add(2);
        h.add(3);
        ;
        List<Integer> i = new ArrayList<Integer>();
        i.add(3);
        i.add(3);
        i.add(4);
        i.add(5);
        //并集
        System.out.println(CollectionUtils.union(i, h));  //[1, 2, 3, 3, 4, 5]
        //交集
        System.out.println(CollectionUtils.intersection(i, h)); //[3]
        //交集的补集
        System.out.println(CollectionUtils.disjunction(i, h)); //[1, 2, 3, 4, 5]
        //e与h的差
        System.out.println(CollectionUtils.subtract(h, i)); //[1, 2]
        System.out.println(CollectionUtils.subtract(i, h)); //[3, 4, 5]
    }

    @Test
    public void Test() {
        IterableMap iterableMap = (IterableMap) new HashMap();
        MapIterator it = iterableMap.mapIterator();
        while (it.hasNext()) {
            Object key = it.next();
            Object value = it.getValue();
//      it.setValue(newValue);
        }

    }

    /***
     * apache.commons.collections4用法
     * https://blog.csdn.net/jiutianhe/article/details/19199573
     */
    @Test
    public void MapTest() {

        /**
         * 得到集合里按顺序存放的key之后的某一Key
         */
        OrderedMap map = new LinkedMap();
        map.put("FIVE", "5");
        map.put("SIX", "6");
        map.put("SEVEN", "7");
        map.firstKey(); // returns "FIVE"
        map.nextKey("FIVE"); // returns "SIX"
        map.nextKey("SIX"); // returns "SEVEN"

        /**
         * BidiMap
         * 通过key得到value
         * 通过value得到key
         * 将map里的key和value对调
         */
        BidiMap bidi = new TreeBidiMap();
        bidi.put("SIX", "6");
        bidi.get("SIX");  // returns "6"
        bidi.getKey("6");  // returns "SIX"
        //       bidi.removeValue("6");  // removes the mapping
        BidiMap inverse = bidi.inverseBidiMap();  // returns a map with keys and values swapped
        System.out.println(inverse);

        /**
         * CollectionUtils.retainAll
         * 得到两个集合中相同的元素
         */
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        list2.add("5");
        Collection c = CollectionUtils.retainAll(list1, list2);
        System.out.println(c);
    }
}
