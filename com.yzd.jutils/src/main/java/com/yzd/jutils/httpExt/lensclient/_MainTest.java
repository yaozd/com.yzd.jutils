package com.yzd.jutils.httpExt.lensclient;

/**
 * 本项目连接：
 * {@link com.yzd.jutils.feignExt._FeignTest#t1()}
 * @Author: yaozh
 * @Description:
 */
public class _MainTest {
    public static void main(String[] args) {
        BaiduClient client = new BaiduClientWrapper("http://www.baidu.com");
        String baidu = client.getBaidu();
        System.out.println(baidu);
        //
    }
}
