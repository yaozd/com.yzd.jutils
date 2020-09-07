package com.yzd.jutils.httpExt.lensclient;

/**
 * @Author: yaozh
 * @Description:
 */
public class BaiduClientWrapper implements BaiduClient {
    private final BaiduClient restClient;

    public BaiduClientWrapper(String url) {
        //restClient = HttpServiceClient.getRestClient(BaiduClient.class, url);
        restClient = HttpServiceClient.getClient(BaiduClient.class, url);
    }

    @Override
    public String getBaidu() {
        return restClient.getBaidu();
    }
}
