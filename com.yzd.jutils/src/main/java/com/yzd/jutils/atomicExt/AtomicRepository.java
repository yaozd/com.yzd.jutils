package com.yzd.jutils.atomicExt;

import java.util.concurrent.atomic.AtomicInteger;

public enum AtomicRepository {
    PRODUCT;

    //region Pkg的作用主要是用于防止influxdb,批量导入数据丢失问题
    //Pkg的作用主要是用于防止influxdb,批量导入数据丢失问题
    AtomicInteger pkgId = new AtomicInteger(0);
    //目前暂定1万的pkg
    int maxPkgId=2;
    /**
     * pkgId到达最大值maxPkgId后重置pkgId为零。
     * synchronized作用：AtomicInteger实现边界值控制
     *      * 解决：
     *      * 最佳实践：AtomicInteger实现边界值控制
     *      * 使用synchronized 保证顺序，对性能基本没有影响
     * @return
     */
    public synchronized int incrementAndGet() {
        int value= pkgId.incrementAndGet();
        if(value>maxPkgId){
            pkgId.set(0);
            value=pkgId.incrementAndGet();
            pkgIdResetCount.incrementAndGet();
        }
        return value;
    }
    //pkg重置的次数。
    AtomicInteger pkgIdResetCount = new AtomicInteger(0);

    /**
     * 此方法是验证incrementAndGet的
     */
    public void printTest(){
        System.out.println(pkgIdResetCount.get());
    }
    //end
}
