package com.yzd.jutils.loadBalance.support.randomLoadBalance;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 参考：dubbo 2.7.4
 * RandomLoadBalance
 * @author: yaozhendong
 * @create: 2019-10-12 09:35
 **/

public class RandomLoadBalance {

    public Node doSelect(List<Node> nodeList) {
        if (CollectionUtils.isEmpty(nodeList)) {
            return null;
        }
        int nodeListSize=nodeList.size();
        if (nodeListSize == 1) {
            return nodeList.get(0);
        }
        boolean sameWeight = true;
        int firstWeight=nodeList.get(0).getWeight();
        int totalWeight=firstWeight;
        for (Node node : nodeList) {
            int weight=node.getWeight();
            totalWeight+=weight;
            if(sameWeight&&firstWeight!=weight){
                sameWeight=false;
            }
        }
        if(sameWeight){
            return nodeList.get(ThreadLocalRandom.current().nextInt(nodeListSize));
        }
        int offset=ThreadLocalRandom.current().nextInt(totalWeight);
        for (Node node : nodeList) {
            offset=offset-node.getWeight();
            if(offset<1){
                return node;
            }
        }
        throw new IllegalStateException("not found node");
    }
}
