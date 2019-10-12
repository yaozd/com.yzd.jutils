package com.yzd.jutils.loadBalance.support.randomLoadBalance;

import lombok.NoArgsConstructor;

/**
 * @author: yaozhendong
 * @create: 2019-10-11 17:48
 **/
@NoArgsConstructor
public class Node {
    private String name;
    private Integer weight;

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
}
