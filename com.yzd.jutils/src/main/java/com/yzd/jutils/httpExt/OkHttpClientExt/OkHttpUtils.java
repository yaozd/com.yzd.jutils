package com.yzd.jutils.httpExt.OkHttpClientExt;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private OkHttpClient client;

    private OkHttpUtils(){
        client = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
    }
    public static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    return okHttpUtils=new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }
    public void getPost(String url, Map<String,String> map){
        FormBody.Builder builder = new FormBody.Builder();
        for (String key:map.keySet()) {
            builder.add(key,map.get(key));
        }
        FormBody build = builder.build();
        final Request request = new Request.Builder()
                .post(build)
                .url(url)
                .build();
        Call call = client.newCall(request);
        try {
            Response response=call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

