package com.yzd.jutils.collectionsExt;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import static io.protostuff.CollectionSchema.MessageFactories.CopyOnWriteArraySet;

/**
 * Created by zd.yao on 2017/7/13.
 */
public class CollectionsTest {
    public static void main(String[] args) {
        //相同的KEY数据是替换的
        Map<String, Object> finalDataMap = new ConcurrentHashMap<>();
        finalDataMap.put("now", "1");
        finalDataMap.put("now", "3");
        //finalDataMap.size()=1
        System.out.println("finalDataMap.size()=" + finalDataMap.size());
        //Collections unmodifiableMap 这个的作用是什么
        //不可修改集合
        //https://zhidao.baidu.com/question/448914033.html
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> map2 = Collections.unmodifiableMap(map);
        map2.put("1", "1");
        //java怎么用一行代码初始化ArrayList
        List<String> places = Arrays.asList("Buenos Aires", "Córdoba", "La Plata");
        // Java中如何遍历Map对象的4种方法
        // http://blog.csdn.net/tjcyjd/article/details/11111401
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    @Test
    public void collectionTest() {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        set1.add("a");
        set1.add("b");
        set1.add("c");
        set1.add("d");
        set2.add("c");
        set2.add("d");
        set2.add("e");
        set2.add("f");
        //交集
        Sets.SetView<String> inter = Sets.intersection(set1, set2);
        //差集
        Sets.SetView<String> diff = Sets.difference(set1, set2);
        //并集
        Sets.SetView<String> union = Sets.union(set1, set2);
    }

    /**
     * 集合框架学习之Guava Collection
     * https://www.cnblogs.com/dooor/p/5285484.html
     */
    @Test
    public void collectionAddressTest(){
        Set<InetSocketAddress> set1 = new HashSet<>();
        Set<InetSocketAddress> set2 = new HashSet<>();
        set1.add(new InetSocketAddress("127.0.0.3",100));
        set1.add(new InetSocketAddress("127.0.0.2",100));
        set1.add(new InetSocketAddress("127.0.0.1",100));
        //set2.add(new InetSocketAddress("127.0.0.2",100));
        //set2.add(new InetSocketAddress("127.0.0.3",100));
        //交集
        Sets.SetView<InetSocketAddress> inter = Sets.intersection(set1, set2);
        //差集
        Sets.SetView<InetSocketAddress> diff = Sets.difference(set1, set2);
        //并集
        Sets.SetView<InetSocketAddress> union = Sets.union(set1, set2);
        //
        String key="namespace";
        Map<String,InetSocketAddress[]> addressMap=new HashMap<>();
        if(diff.size()>0){
            addressMap.put(key,set2.toArray(new InetSocketAddress[set2.size()]));
        }
        InetSocketAddress[] addressArray=addressMap.get(key+"1");
        if(addressArray==null||0==addressArray.length){
            System.out.println("not found address");
            //empty array
            InetSocketAddress[] addresses = {};
            return ;
        }
        InetSocketAddress address=addressArray[1];
        System.out.println(address);

    }
}
