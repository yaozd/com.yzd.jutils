package com.yzd.jutils.httpExt.lensclient;

import feign.RequestLine;

/**
 * @Author: yaozh
 * @Description:
 */
public interface BaiduClient {

    @RequestLine("GET /")
    String getBaidu();
}
