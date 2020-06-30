package com.yzd.jutils.benchmark.t1;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

/**
 * @author: yaozhendong
 * @create: 2019-11-27 17:52
 **/

public class HelloworldRunner {
    /**
     * Options opt = new OptionsBuilder()
     *                 //正则：方法：com.yzd.jutils.benchmark.t1.Helloworld.m
     *                 .include(BenchmarkDemo.class.getName())
     *                 //.include("com.yzd.jutils.benchmark.t1.Helloworld.m")
     *                 //.exclude("Pref")
     *                 //预热次数
     *                 .warmupIterations(0)
     *                 //实际每次运行时间
     *                 //.measurementTime(TimeValue.seconds(10))
     *                 .measurementTime(TimeValue.seconds(1))
     *                 //.timeout(TimeValue.NONE)
     *                 //.threads(10)
     *                 //实际每次迭代次数
     *                 .measurementIterations(1)
     *                 //forks:进程
     *                 .forks(1)
     *                 .build();
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                //正则：方法：com.yzd.jutils.benchmark.t1.Helloworld.m
                .include("com.yzd.jutils.benchmark.t1.Helloworld.m")
                //.include("com.yzd.jutils.benchmark.t1.Helloworld.m")
                //.exclude("Pref")
                //预热次数
                .warmupIterations(2)
                //实际每次运行时间
                .measurementTime(TimeValue.seconds(10))
                //实际每次迭代次数
                .measurementIterations(1)
                //forks:进程
                .forks(3)
                .build();

        new Runner(opt).run();
    }

}
