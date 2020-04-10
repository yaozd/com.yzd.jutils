package com.yzd.jutils.collectionsExt;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _SetTest {
    @Test
    public void test() {
        Set<String> nodes = new HashSet<>();
        nodes.add("1");
        nodes.add("1");
        System.out.println(nodes.size());
        //Set<String> s=new CopyOnWriteArraySet<>();
        Set<String> temp = new HashSet<>();
        temp.addAll(nodes);
        nodes.clear();
        //temp.clear();
        System.out.println(temp.size());
        System.out.println(nodes.size());
        Map<String, Set<String>> nodeMap = new HashMap<>();
        //静态节点
        Map<String, Set<String>> staticNodeMap = new HashMap<>();
        //动态节点
        Map<String, Set<String>> dynamicNodeMap = new HashMap<>();
        //

    }
}
