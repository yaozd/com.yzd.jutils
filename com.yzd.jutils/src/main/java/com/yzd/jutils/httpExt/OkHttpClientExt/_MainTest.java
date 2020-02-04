package com.yzd.jutils.httpExt.OkHttpClientExt;

import org.junit.Test;

import java.util.HashMap;

public class _MainTest {
    @Test
    public void post() {
        String url = "https://www.baidu.com";
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile", "18611112222");
        map.put("password", "123456");
        OkHttpUtils.getInstance().getPost(url, map);
    }
}
