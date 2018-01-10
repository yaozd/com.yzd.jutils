package com.yzd.jutils.patterns.strategy.t1;

public enum SalesChannel {
    渠道1(1),
    渠道2(2),
    渠道3(3);
    SalesChannel(Integer id){
        this.id=id;
    }
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
