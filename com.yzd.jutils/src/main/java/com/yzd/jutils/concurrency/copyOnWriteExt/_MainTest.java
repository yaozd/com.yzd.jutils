package com.yzd.jutils.concurrency.copyOnWriteExt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: yaozhendong
 * @create: 2019-10-12 10:38
 **/

public class _MainTest {
    /**
     * 1. 减少扩容开销。根据实际需要，初始化CopyOnWriteMap的大小，避免写时CopyOnWriteMap扩容的开销。
     * 2. 使用批量添加。因为每次添加，容器每次都会进行复制，所以减少添加次数，可以减少容器的复制次数。如使用上面代码里的addBlackList方法。
     */
    @Test
    public void copyOnWriteTest() {
        //CopyOnWriteArrayList和CopyOnWriteArraySet
        List<String> itemList = new ArrayList<>();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(itemList);
        CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
        Set<String> itemSet = new HashSet<>();
        copyOnWriteArraySet.addAll(itemSet);

    }
}
