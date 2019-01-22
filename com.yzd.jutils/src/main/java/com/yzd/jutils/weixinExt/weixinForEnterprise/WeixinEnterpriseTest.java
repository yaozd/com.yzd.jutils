package com.yzd.jutils.weixinExt.weixinForEnterprise;

import com.yzd.jutils.httpExt.HttpUtil;
import org.junit.Test;

import java.util.HashMap;

public class WeixinEnterpriseTest {

    //开始开发-推荐参考byArvin
    //https://work.weixin.qq.com/api/doc#10013
    //https://work.weixin.qq.com/api/doc#10013

    /**
     * 获取Token
     * corpid=我的企业》企业ID
     * corpsecret=应用与小程序》自建程序》TEST(自建程序名)》Secret
     * 参考：
     * [简易教程]
     * https://work.weixin.qq.com/api/doc#90000/90003/90487
     */
    @Test
    public void getToken() {
        //corpid=你好技术
        //corpsecret=TEST应用
        String baseUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww128eccxxxxxx&corpsecret=m4RCad_oeuQIkenZMpmAxxxxx";
        String result = HttpUtil.sendGet(baseUrl, new HashMap<>());
        System.out.println(result);
    }

    /**
     * 通过TOKEN发送信息
     * PS:建议通过管理平台来控制“自建应用”的显示的部门，这样可以更加简单的控制权限。
     * 参考：
     */
    @Test
    public void sendText() {
        //token的有效时间为2个小时-可以把Token存放在Redis中
        String accessToken = "SgZ_AJjLHfnISGitztlJtrCB2gBp6IgzD9_kbiE3Zt8QdS0liz_iOWCzg4k8Cx13HuwooNNUwOXhtDytJ9b3vg9eephXZILAS3efwkBtXh-nfa53eWKUii9xkTig3HbOyauA9NwGZjPaz8fusXYXbZWQpfhjkagFJpaKdUVqMIMbxg1HjiisgRar-5XfZ27T1IfV2k4A7nWCnL8OUF2sJQ";
        String baseUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + accessToken;
        String param4json = "{\n" +
                "\t\"touser\": \"@all\",\n" +
                "\t\"msgtype\": \"text\",\n" +
                "\t\"agentid\": 1000002,\n" +
                "\t\"text\": {\n" +
                "\t\t\"content\": \"你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。\"\n" +
                "\t},\n" +
                "\t\"safe\": 0\n" +
                "}";
        String response = HttpUtil.sendPost2(baseUrl, param4json, "utf-8", "utf-8", null);
        System.out.println(response);
    }
}
