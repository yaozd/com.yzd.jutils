package com.yzd.jutils.loadBalance.support.roundRobinSimple;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yaozhendong
 * @create: 2019-10-11 17:40
 **/

public class _MainTest {
    @Test
    public void roundRobinSimpleTest() {
        List<String> nodeList = new ArrayList<>();
        nodeList.add("1");
        nodeList.add("2");
        nodeList.add("3");
        RoundRobinSimple roundRobinSimple = new RoundRobinSimple();
        for (int i = 0; i < 100; i++) {

            System.out.println(roundRobinSimple.doSelect(nodeList));
        }
    }
}
