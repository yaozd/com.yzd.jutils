package com.yzd.jutils.loadBalance.support.roundRobinWithWeight;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: yaozhendong
 * @create: 2019-10-11 17:49
 **/

public class RoundRobinWithWeight {

    public Node doSelect(List<Node> nodeList) {
        if (CollectionUtils.isEmpty(nodeList)) {
            return null;
        }
        if (nodeList.size() == 1) {
            return nodeList.get(0);
        }
        int totalWeight = 0;
        Integer maxWeight = null;
        Node bestNode = null;
        for (Node node : nodeList) {
            node.setCurrentWeight(node.getWeight() + node.getCurrentWeight());
            if (maxWeight == null || node.getCurrentWeight() > maxWeight) {
                maxWeight = node.getCurrentWeight();
                bestNode = node;
            }
            totalWeight += node.getWeight();
        }
        bestNode.setCurrentWeight(maxWeight - totalWeight);
        return bestNode;
    }
}
