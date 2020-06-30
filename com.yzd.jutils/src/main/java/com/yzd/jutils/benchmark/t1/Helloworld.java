package com.yzd.jutils.benchmark.t1;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;

/**
 * @author: yaozhendong
 * @create: 2019-11-27 17:52
 **/
@State(Scope.Benchmark)
public class Helloworld {

    @Benchmark
    public void m() {

    }
    /**
     * 初始化参数
     */
    @Setup
    public void init() {
        System.out.println("初始化参数");
    }

    /**
     * TearDown marks the fixture method to be run after the benchmark.
     * TearDown的作用域与State相关联
     *
     * Scope有三种：
     * Scope.Thread：默认的State，每个测试线程分配一个实例；
     * Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能；
     * Scope.Group：每个线程组共享一个实例；
     */
    @TearDown
    public void end(){
        System.out.println("Benchmark执行完成");
    }
}