package com.yzd.jutils.httpExt.lensclient;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;

/**
 * describe:
 * 本项目连接：
 * {@link com.yzd.jutils.feignExt._FeignTest#t1()}
 * @author wangyiming
 * @date 2019/05/20
 */
public class HttpServiceClient {
    private static Gson gsonWithNulls = new GsonBuilder().serializeNulls().create();
    private static Gson gsonWithoutNulls = new GsonBuilder().create();
    public static <T> T getRestClient(Class<T> clientType, String url) {
        T client = Feign.builder().client(new OkHttpClient())
                .encoder(new GsonEncoder(gsonWithNulls)).decoder(new GsonDecoder(gsonWithNulls)).target(clientType, url);
        return client;
    }

    public static <T> T getRestClient(Class<T> clientType, String url, boolean serializeNull) {
        Gson gson = serializeNull ? gsonWithNulls : gsonWithoutNulls;
        T client = Feign.builder().client(new OkHttpClient())
                .encoder(new GsonEncoder(gson)).decoder(new GsonDecoder(gson)).target(clientType, url);
        return client;
    }

    /**
     * 针对非restful client
     * @param clientType
     * @param url
     * @param <T>
     * @return
     */
    public static <T> T getClient(Class<T> clientType, String url) {
        T client = Feign.builder().client(new OkHttpClient()).target(clientType, url);
        return client;
    }
}
