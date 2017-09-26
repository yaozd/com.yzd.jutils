package com.yzd.jutils.httpExt;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zd.yao on 2017/9/26.
 */
public class _MainTest {
    public static void main(String[] args){
        String url="http://localhost:10001/openExe";
        Map<String,String> getParam=new HashMap<>();
        getParam.put("id","中国");
        String result= HttpUtil.sendPostXML(url,getParam,"5678","utf-8","utf-8");
    }
}
