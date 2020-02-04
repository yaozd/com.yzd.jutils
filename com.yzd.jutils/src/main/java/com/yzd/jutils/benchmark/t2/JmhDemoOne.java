package com.yzd.jutils.benchmark.t2;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * - [使用JMH进行基准性能测试](https://blog.csdn.net/cndmss/article/details/93771981)
 *
 * @author: yaozhendong
 * @create: 2019-11-27 18:27
 **/

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
public class JmhDemoOne {
    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(JmhDemoOne.class.getName())
                .build();
        new Runner(options).run();
    }

    /**
     * 测试sayHello的平局耗时
     *
     * @throws Exception
     */
    @Benchmark
    public void sayHello() throws Exception {
        //TODO 业务方法 ，此处用休眠的方式模拟业务耗时10 ms
        TimeUnit.MILLISECONDS.sleep(10);
    }
}

