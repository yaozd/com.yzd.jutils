package com.yzd.jutils.benchmark.t4;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * - [使用JMH进行基准性能测试](https://blog.csdn.net/cndmss/article/details/93771981)
 *
 * @ Description：jmh使用第三个例子
 * 测试常用序列化json库fastJson、gson、jackson的性能（均为截止2019.06最新版本）
 * @ Date       ：Created in 2019-06-19
 * @ Version    ：0.0.1
 */
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.SingleShotTime)
@Warmup(iterations = 5)
@Measurement(iterations = 1)
@State(Scope.Benchmark)
@Fork(1)
public class JmhDemoThree {
    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(JmhDemoThree.class.getName())
                .build();
        new Runner(options).run();
    }

    /**
     * 序列化次数
     */
    @Param({"100", "10000", "1000000"})
    private int number;
    private Userinfo userinfo;
    private String fastjson_jsonStr;
    private String gson_jsonStr;
    private String jackson_jsonStr;

    /**
     * fastjson bean2Json
     */
    @Benchmark
    public void fastjson_bean2Json() {
        for (int i = 0; i < number; i++) {
            JsonUtil.fastjson_bean2Json(userinfo);
        }
    }

    /**
     * gson bean2Json
     */
    @Benchmark
    public void gson_bean2Json() {
        for (int i = 0; i < number; i++) {
            JsonUtil.gson_bean2Json(userinfo);
        }
    }

    /**
     * jackson bean2Json
     */
    @Benchmark
    public void jackson_bean2Json() {
        for (int i = 0; i < number; i++) {
            JsonUtil.jackson_bean2Json(userinfo);
        }
    }

    /**
     * fastjson json2Bean
     */
    @Benchmark
    public void fastjson_json2Bean() {
        for (int i = 0; i < number; i++) {
            JsonUtil.fastjson_json2Bean(fastjson_jsonStr, Userinfo.class);
        }
    }

    /**
     * gson json2Bean
     */
    @Benchmark
    public void gson_json2Bean() {
        for (int i = 0; i < number; i++) {
            JsonUtil.gson_json2Bean(gson_jsonStr, Userinfo.class);
        }
    }

    /**
     * jackson json2Bean
     */
    @Benchmark
    public void jackson_json2Bean() {
        for (int i = 0; i < number; i++) {
            JsonUtil.jackson_json2Bean(jackson_jsonStr, Userinfo.class);
        }
    }

    /**
     * 初始化参数
     */
    @Setup
    public void init() {
        userinfo = new Userinfo();
        userinfo.setUsername("张三");
        userinfo.setGender("男");
        userinfo.setAge(18);
        userinfo.setBirthday(new Date());
        userinfo.setCreateTime(System.currentTimeMillis());
        List<String> list = new ArrayList<>();
        list.add("北京三里屯儿那条街那条巷那一号");
        list.add("上海三里屯儿那条街那条巷那一号");
        list.add("深圳三里屯儿那条街那条巷那一号");
        userinfo.setAddress(list);

        fastjson_jsonStr = JsonUtil.fastjson_bean2Json(userinfo);
        gson_jsonStr = JsonUtil.gson_bean2Json(userinfo);
        jackson_jsonStr = JsonUtil.jackson_bean2Json(userinfo);
    }
}
