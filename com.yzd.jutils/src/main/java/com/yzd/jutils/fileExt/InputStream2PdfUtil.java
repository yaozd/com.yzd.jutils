package com.yzd.jutils.fileExt;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 参考：
 * <p>
 * <p>
 * 从网上下载pdf到本地
 * Created by zd.yao on 2017/8/4.
 */
public class InputStream2PdfUtil {
    public static void main(String[] args) {
        byte[] getData = getInputStreamByPost("", null, null);
        try {
            InputStream2Pdf(getData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void InputStream2Pdf(byte[] getData) throws IOException {
        //byte[] getData = readInputStream(inputStream);
        File file = new File("G:\\1.pdf");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
    }

    public static String sendPost(String url, Map<String, String> params, String inEncoding) {
        String outEncoding = inEncoding;
        return sendPost(url, params, inEncoding, outEncoding, new BasicHeader("Mozilla/5.0", "User-Agent"));
    }

    public static String sendPost(String url, Map<String, String> params, String inEncoding, String outEncoding, Header... requestHeaders) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (requestHeaders != null && requestHeaders.length > 0) {
            for (Header header : requestHeaders) {
                httpPost.addHeader(header);
            }
        }
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>(params.size());
        for (String key : params.keySet()) {
            urlParameters.add(new BasicNameValuePair(key, params.get(key)));
        }
        String response;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(urlParameters, inEncoding));
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity, outEncoding);
            httpClient.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public static String sendPostXML(String url, String xml) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("User-Agent", "Mozilla/5.0");
        HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String response = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
        httpClient.close();
        return response;
    }

    public static byte[] getInputStreamByPost(String url, Map<String, String> params, String inEncoding) {
        String outEncoding = inEncoding;
        return getInputStreamByPost(url, params, inEncoding, outEncoding, new BasicHeader("Mozilla/5.0", "User-Agent"));
    }

    public static byte[] getInputStreamByPost(String url, Map<String, String> params, String inEncoding, String outEncoding, Header... requestHeaders) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (requestHeaders != null && requestHeaders.length > 0) {
            for (Header header : requestHeaders) {
                httpPost.addHeader(header);
            }
        }
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>(params.size());
        for (String key : params.keySet()) {
            urlParameters.add(new BasicNameValuePair(key, params.get(key)));
        }
        byte[] response = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(urlParameters, inEncoding));
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            InputStream inputStream = httpResponse.getEntity().getContent();
            response = readInputStream(inputStream);
            httpClient.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
