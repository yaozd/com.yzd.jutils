package com.yzd.jutils.loadBalance.support.randomLoadBalance;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author: yaozhendong
 * @create: 2019-10-12 09:36
 **/

public class _MainTest {
    @Test
    public void randomLoadTest() {
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(new Node().setName("1").setWeight(1));
        nodeList.add(new Node().setName("2").setWeight(2));
        nodeList.add(new Node().setName("3").setWeight(3));
        RandomLoadBalance randomLoadBalance=new RandomLoadBalance();
        Multimap<String, String> countMap = LinkedListMultimap.create();
        for (int i = 0; i < 9; i++) {
            Node node=randomLoadBalance.doSelect(nodeList);
            System.out.println(node.getName());
            countMap.put(node.getName(),node.getName());
        }
        for (Map.Entry<String, Collection<String>> e : countMap.asMap().entrySet()) {
            System.out.printf(e.getKey() + "=");
            System.out.println(e.getValue().size());
        }
    }
}
