package com.yzd.jutils.hashExt;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.yzd.jutils.math.BigDecimalUtil;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zd.yao on 2017/6/13.
 */
public class MainApp {

    public static void main(String[] args) {

        Set<String> nodes = new HashSet<String>();
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");
        ConsistentHash<String> consistentHash = new ConsistentHash<String>(new HashFunction(), 160, nodes);

        consistentHash.add("D");
        consistentHash.add("E");
        System.out.println(consistentHash.getSize());  //640

        System.out.println(consistentHash.get("test5"));
        Multimap<String, String> countMultimap = ArrayListMultimap.create();
        //循环50次，是为了取50个数来测试效果，当然也可以用其他任何的数据来测试
        int total=5000;
        for(int i=0; i<total; i++) {
            String shardInfoVal= consistentHash.get(String.valueOf(i));
            //System.out.println(shardInfoVal);
            countMultimap.put(shardInfoVal,shardInfoVal);
        }
        for(Map.Entry<String, Collection<String>> e : countMultimap.asMap().entrySet()){
            String v1= BigDecimalUtil.div(String.valueOf(e.getValue().size()) , String.valueOf(total), 4);
            System.out.println(String.format("%s%s%s", e.getKey(), e.getValue().size(), v1));
        }
    }

}