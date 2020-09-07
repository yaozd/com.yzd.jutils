package com.yzd.jutils.httpExt.lensclient;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@SuppressWarnings("unchecked")
@Slf4j
public class HttpUtil {

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static ResponseEntity get(String url, Map<String, Object> params, Header headers, int timeoutMils) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String parmstr = "";
        if (params != null && !params.isEmpty()) {
            parmstr = "?".concat(Joiner.on("&").withKeyValueSeparator("=").join(params));
        }

        HttpGet get = new HttpGet(url + parmstr);
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(timeoutMils)
                .setConnectTimeout(timeoutMils).setRedirectsEnabled(true)
                .setSocketTimeout(timeoutMils).build();
        get.setConfig(config);
        if (null != headers && 0 != headers.getElements().length) {
            get.setHeader(headers);
        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            ResponseEntity responseEntity = new ResponseEntity(JSONObject.parseObject(result),
                    HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
            log.debug("GET result :" + result);
            return responseEntity;
        } catch (Exception e) {
            log.error("GET is error {}", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                log.error("close http {}", e);
            }
        }
        return new ResponseEntity("", HttpStatus.SERVICE_UNAVAILABLE);
    }

    public static ResponseEntity post(String url, Map<String, Object> params, int timeoutMils) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = setHttpPostConfig(url, timeoutMils);
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        CloseableHttpResponse response = null;
        try {
            List formParams = new ArrayList();
            for (String key : params.keySet()) {
                formParams.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
            }
            post.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
            response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            return new ResponseEntity(JSONObject.parseObject(result), HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
        } catch (Exception e) {
            log.error("post is error {}", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                log.error("post is error {}", e);
            }
        }
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (null != response) {
            httpStatus = HttpStatus.valueOf(response.getStatusLine().getStatusCode());
        }
        return new ResponseEntity("", httpStatus);
    }

    public static ResponseEntity post(String url, String json, int timeout, HttpClientContext context) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        JSONObject jsonObject = new JSONObject();
        try {
            HttpPost post = setHttpPostConfig(url, timeout);
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            post.setEntity(new StringEntity(json, "UTF-8"));
            response = httpClient.execute(post, context);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            ResponseEntity responseEntity = new ResponseEntity(JSONObject.parseObject(result),
                    HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
            return responseEntity;
        } catch (Exception e) {
            log.error("post request error {}", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                // ignore close exception
            }
        }
        return new ResponseEntity(jsonObject, HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
    }

    public static ResponseEntity post(String url, String json, int timeout) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        JSONObject jsonObject = new JSONObject();
        try {
            HttpPost post = setHttpPostConfig(url, timeout);
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            post.setEntity(new StringEntity(json, "UTF-8"));
            response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            ResponseEntity responseEntity = new ResponseEntity(JSONObject.parseObject(result), HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
            return responseEntity;
        } catch (Exception e) {
            log.error("post is error {}", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("post is error {}", e);
            }
        }
        return new ResponseEntity(jsonObject, HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
    }

    private static HttpPost setHttpPostConfig(String url, int timeout) {
        HttpPost post = new HttpPost(url);
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(timeout)
                .setConnectTimeout(timeout).setRedirectsEnabled(true)
                .setSocketTimeout(timeout).build();
        post.setConfig(config);
        return post;
    }


}
