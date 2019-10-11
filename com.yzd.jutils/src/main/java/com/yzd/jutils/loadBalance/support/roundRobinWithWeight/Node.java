package com.yzd.jutils.loadBalance.support.roundRobinWithWeight;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yaozhendong
 * @create: 2019-10-11 17:48
 **/
@NoArgsConstructor
public class Node {
    private String name;
    private Integer weight;
    private Integer currentWeight=Integer.valueOf(0);

    public String getName() {
        return name;
    }

    public Node setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public Node setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Integer getCurrentWeight() {
        return currentWeight;
    }

    public Node setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
        return this;
    }
}
