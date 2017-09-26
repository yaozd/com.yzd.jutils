package com.yzd.jutils.httpExt;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by zd.yao on 2017/4/12.
 */
public class HttpUtil {
    public static String sendPost(String url, Map<String, String> params) {
        List<NameValuePair> urlParameters = getNameValuePairs(params);
        String response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("User-Agent", "Mozilla/5.0");
            httpPost.setEntity(new UrlEncodedFormEntity(urlParameters, StandardCharsets.UTF_8));
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    public static String sendGet(String url, Map<String, String> params) {
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>(params.size());
        for (String key : params.keySet()) {
            urlParameters.add(new BasicNameValuePair(key, params.get(key)));
        }
        //
        String response = null;
        try {
            URI uri = new URIBuilder(url).addParameters(urlParameters).build();
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(uri);
            httpGet.addHeader("User-Agent", "Mozilla/5.0");
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
            httpClient.close();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return response.toString();
    }

    /**
     * 场景：同时使用get与post方法
     * Get参数	sign=签名串
                comId=渠道ID
                如：http://www.xxxx.xxx/interfaceChannel?sign=xxx&comId=xxx
     * POST参数	原始XML数据
     * @param url
     * @param getParamMap
     * @param postParamStr
     * @param inEncoding
     * @param outEncoding
     * @return
     */
    public static String sendPostXML(String url,Map<String, String> getParamMap, String postParamStr,String inEncoding,String outEncoding) {
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>(getParamMap.size());
        for (String key : getParamMap.keySet()) {
            urlParameters.add(new BasicNameValuePair(key, getParamMap.get(key)));
        }
        //
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //
        String response = null;
        try {
            URI uri = new URIBuilder(url).addParameters(urlParameters).build();
            HttpPost httpPost = new HttpPost();
            httpPost.setURI(uri);//如果使用new HttpPost(uri)初始化则地址问号后面的请求参数就会被丢掉
            httpPost.addHeader("User-Agent", "Mozilla/5.0");
            HttpEntity entity = new ByteArrayEntity(postParamStr.getBytes(inEncoding));
            httpPost.setEntity(entity);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            //response = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
            response = EntityUtils.toString(httpEntity, outEncoding);
            httpClient.close();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return response.toString();
    }
    public static String sendPostXML(String url, String xml) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader("User-Agent", "Mozilla/5.0");
        //
        String response = null;
        try {
            HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
            httpPost.setEntity(entity);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
            httpClient.close();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return response.toString();
    }
    private static List<NameValuePair> getNameValuePairs(Map<String, String> params) {
        if (params == null) {
            return new ArrayList<NameValuePair>();
        }
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>(params.size());
        for (String key : params.keySet()) {
            urlParameters.add(new BasicNameValuePair(key, params.get(key)));
        }
        return urlParameters;
    }

}

