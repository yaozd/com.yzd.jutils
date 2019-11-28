package com.yzd.jutils.benchmark.t3;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import java.util.concurrent.TimeUnit;
/**
 * - [使用JMH进行基准性能测试](https://blog.csdn.net/cndmss/article/details/93771981)
 * @ Description：jmh使用第二个例子
 * @ Author     ：xiaojiang
 * @ Date       ：Created in 2019-06-19
 * @ Version    ：0.0.1
 */
@OutputTimeUnit(TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
@State(Scope.Thread)
public class JmhDemoTwo {
    public static void main(String[] args) throws Exception{
        Options options = new OptionsBuilder()
                .include(JmhDemoTwo.class.getName())
                .build();
        new Runner(options).run();
    }
    /**
     * 字符串个数
     */
    @Param({"10", "100", "1000"})
    private int number;
    /**
     * 字符串直接相加方式
     */
    @Benchmark
    public void StringAddMode(){
        String str = "";
        for(int i=0;i<number;i++){
            str = str + i;
        }
    }
    /**
     * 字符串通过StringBuilder的append方式
     */
    @Benchmark
    public void StringBuilderMode(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<number;i++){
            sb.append(i);
        }
    }
}

