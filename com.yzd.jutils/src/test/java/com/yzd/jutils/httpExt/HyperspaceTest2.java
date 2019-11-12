package com.yzd.jutils.httpExt;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author: yaozhendong
 * @create: 2019-11-11 13:42
 **/

public class HyperspaceTest2 {

    Executor executor = Executor.newInstance();
    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    @BeforeClass
    public static void runBeforeTestMethod() {
        String allLevel = "info";
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        if (!StringUtils.isEmpty(allLevel)) {
            //设置全局日志级别
            ch.qos.logback.classic.Logger logger = loggerContext.getLogger("root");
            logger.setLevel(Level.toLevel(allLevel));
        }
    }
    @Test
    @PerfTest(threads = 500, duration = 150000000)
    public void proxy1_Test() throws IOException {
        String requestUrl="http://world.hualala.com:5555";
        executor.execute(Request.Get(requestUrl))
                .returnContent().asString();
    }
}
