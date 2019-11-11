package com.yzd.jutils.httpExt;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.yzd.jutils.httpExt.OkHttpClientExt.OkHttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
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
 * @create: 2019-11-08 15:14
 **/

public class HyperspaceTest {
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

    /**
     * 复用http连接池--keepalive
     * 将每个路由的默认最大连接数设置为100
     *
     * @throws IOException
     */
    @Test
    @PerfTest(threads = 100, duration = 150000000)
    public void proxy1_Test() throws IOException {
        String requestUrl="http://dohko.online.h5api.hualala.com:6666";
        Request.Get(requestUrl)
                .connectTimeout(3000)
                .socketTimeout(30000)
                .execute().handleResponse(new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                return null;
            }
        });
    }
    @Test
    @PerfTest(threads = 100, duration = 150000000)
    public void proxy2_Test() throws IOException {
        String requestUrl="http://172.20.60.45:5555/";
        Request.Get(requestUrl)
                .connectTimeout(3000)
                .socketTimeout(30000)
                .execute().handleResponse(new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                return null;
            }
        });
    }

    /**
     * 不推荐使用OKHttp
     * @throws IOException
     */
    @Test
    //@PerfTest(threads = 100, duration = 600)
    @PerfTest(threads = 10, invocations=10000)
    public void proxy3_okHttp_Test() throws IOException {
        String requestUrl="http://172.20.60.45:5555/";
        OkHttpUtils.getInstance().get(requestUrl);
    }

    /**
     * 推荐使用fluent-hc
     * @throws IOException
     */
    @Test
    //@PerfTest(threads = 100, duration = 150000000)
    @PerfTest(threads = 100, invocations=100000)
    public void proxy3_fluentRequest_Test() throws IOException {
        //String requestUrl="http://172.20.60.45:5555/";
        String requestUrl="http://172.20.60.45:8081/";
        Request.Get(requestUrl)
                .connectTimeout(3000)
                .socketTimeout(30000)
                .execute().handleResponse(new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                return null;
            }
        });
    }
}
