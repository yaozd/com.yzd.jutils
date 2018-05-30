package com.yzd.jutils.httpExt_XXLJOB;

import com.yzd.jutils.fastjson.FastJsonUtil;
import com.yzd.jutils.httpExt_XXLJOB.XXLJob_Login;
import com.yzd.jutils.httpExt_XXLJOB.XXLJob_R_Data;
import com.yzd.jutils.httpExt_XXLJOB.XXLJob_RecordsFiltered;
import com.yzd.jutils.httpExt_XXLJOB.XXLJob_Trigger;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class T1 {
    //测试完整版
    //XXL-登录后触发JOB
    @Test
    public void t2(){
        String url_Login="http://192.168.3.213:7702/login";
        HashMap<String,String> parma_Login=new HashMap<>();
        parma_Login.put("userName","XXXX");
        parma_Login.put("password","XXXX");
        String cookie_Login= getCookieByPost(url_Login,parma_Login);
        //
        String url_JobList="http://192.168.3.213:7702/jobinfo/pageList";
        HashMap<String,String> parma_JobList=new HashMap<>();
        parma_JobList.put("jobGroup","4");
        parma_JobList.put("length","10");
        String content_JobList=sendGet(url_JobList,parma_JobList,cookie_Login);
        XXLJob_RecordsFiltered jsonToRecordsFiltered= FastJsonUtil.deserialize(content_JobList,XXLJob_RecordsFiltered.class);
        XXLJob_RecordsFiltered recordsFiltered = Optional.ofNullable(jsonToRecordsFiltered).orElse(new XXLJob_RecordsFiltered());
        String jobDesc="N03国商数据推送任务";
        Integer id=null;
        for(XXLJob_R_Data data:recordsFiltered.getData()){
            if(data.getJobDesc().equals(jobDesc)){
                id=data.getId();
                break;
            }
        }
        if(id==null){
            throw new IllegalStateException("没有找,[jobDesc="+jobDesc+"]对应的ID");
        }
        //
        String url_Trigger="http://192.168.3.213:7702/jobinfo/trigger";
        HashMap<String,String> parma_Trigger=new HashMap<>();
        parma_Trigger.put("id",id.toString());
        String content_Trigger=sendGet(url_Trigger,parma_Trigger,cookie_Login);
        //System.out.println(content_Trigger);
        XXLJob_Trigger jsonToTrigger= FastJsonUtil.deserialize(content_Trigger,XXLJob_Trigger.class);
        if(jsonToTrigger.getCode()==200){
            System.out.println("执行成功");
            return;
        }
        System.out.println("执行失败");
    }
    public static String sendGet(String url, Map<String, String> params,String cookieValue) {
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
            httpGet.addHeader("Cookie", cookieValue);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
            httpClient.close();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return response.toString();
    }
    public static String getCookieByPost(String url, Map<String, String> params) {
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
            XXLJob_Login responseEntity= FastJsonUtil.deserialize(response,XXLJob_Login.class);
            if(responseEntity.getCode()==200){
                String cookie= httpResponse.getFirstHeader("Set-Cookie").getValue();
                return cookie;
            }
            throw new IllegalStateException("用户名或方法错误,无法获得cookie");
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
