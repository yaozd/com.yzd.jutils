package com.yzd.jutils.loadBalance.support.roundRobinSimple;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yaozhendong
 * @create: 2019-10-11 17:32
 **/

public class RoundRobinSimple {
    private static final int MAX_REWIND_SIZE = 1000000;

    private AtomicInteger currentCount = new AtomicInteger(0);

    public String doSelect(List<String> nodeList) {
        if (CollectionUtils.isEmpty(nodeList)) {
            return null;
        }
        int nodeSize = nodeList.size();
        if (nodeSize == 1) {
            return nodeList.get(0);
        }
        int index = currentCount.incrementAndGet();
        if (index > MAX_REWIND_SIZE) {
            index = 0;
            currentCount.set(index);
        }
        return nodeList.get(index % nodeSize);
    }
}
