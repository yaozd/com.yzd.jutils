package com.yzd.jutils.junitPerferExt;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import junit.textui.TestRunner;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author: yaozhendong
 * @create: 2019-11-28 14:55
 **/


public class HelloWorldTest {
    public void t(){

    }
    @JunitPerfConfig(duration = 1000,warmUp = 10)
    public void helloTest() throws InterruptedException {
        junit.textui.TestRunner runner=new TestRunner();
        Thread.sleep(100);
        System.out.println("Hello Junit5");
    }

}

