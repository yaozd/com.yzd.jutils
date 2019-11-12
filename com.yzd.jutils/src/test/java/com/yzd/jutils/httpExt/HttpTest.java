package com.yzd.jutils.httpExt;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;

/**
 * @author: yaozhendong
 * @create: 2019-11-08 10:01
 **/

public class HttpTest {
    String requestUrl = "http://hello.hualala.com:5555/hello";

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
    @PerfTest(threads = 1, duration = 150000000)
    public void gateway_Test() throws IOException {
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
     * 每次都重新建立连接，进行3次握手
     * @throws IOException
     */
    @Test
    @PerfTest(threads = 1, duration = 150000000)
    public void gateway2_Test() throws IOException {

        HttpUtil.sendGet(requestUrl, Collections.emptyMap());
    }

    @Test
    public void handleResponse_Test() throws IOException {

        Request.Get(requestUrl)
                .connectTimeout(3000)
                .socketTimeout(3000)
                .execute().handleResponse(new ResponseHandler<String>() {
            @Override
            public String handleResponse(final HttpResponse response) throws IOException {
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();
                if (statusLine.getStatusCode() >= 300) {
                    throw new HttpResponseException(
                            statusLine.getStatusCode(),
                            statusLine.getReasonPhrase());
                }
                if (entity == null) {
                    throw new ClientProtocolException("Response contains no content");
                }
                ContentType contentType = ContentType.getOrDefault(entity);
                Charset charset = contentType.getCharset();
                //String  result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                String result = EntityUtils.toString(entity, charset.name());
                return result;

            }
        });
    }

}
