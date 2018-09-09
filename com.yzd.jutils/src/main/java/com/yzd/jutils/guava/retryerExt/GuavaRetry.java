package com.yzd.jutils.guava.retryerExt;

/**
 * Created by zd.yao on 2018/9/9.
 */


import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

//Guava retryer优雅的实现接口重调机制
//https://blog.csdn.net/qq_27501889/article/details/79658726
@Slf4j
public class GuavaRetry {

    static Retryer<String> retryer;

    static int count = 0;

    static {
        retryer = RetryerBuilder.<String>newBuilder().retryIfResult(Predicates.equalTo("")) // 返回false时重试
                .withWaitStrategy(WaitStrategies.fixedWait(1000, TimeUnit.MILLISECONDS)) // 1000ms后重试
                .withStopStrategy(StopStrategies.stopAfterAttempt(20)) // 重试20次后停止
                .build();
    }

    public static void main(String[] args) throws InterruptedException {
        sendSMS();
    }

    public static String sendSMS() {

        try {
            return retryer.call(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    //
                    count++;
                    //
                    String res = "";
                    try {
                        //res = Requests.get("https://www.baidu.com/").send().readToText();
                    } catch (Exception e) {
                        log.debug(e.getMessage());
                    }
                    log.debug(count + "--" + String.valueOf(res.length()));
                    //
                    return res;
                }
            });
        } catch (Exception e) {
            return "";
        }
    }
}

