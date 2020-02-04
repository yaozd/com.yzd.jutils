package com.yzd.jutils.dingdingExt;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class _MainTest {
    //在电脑上如何给钉钉添加机器人？
    //https://jingyan.baidu.com/article/49711c618fa933fa441b7cf9.html
    // 获取自定义机器人webhook
    // https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.karFPe&treeId=257&articleId=105735&docType=1#
    // 消息发送频率限制
    // 每个机器人每分钟最多发送20条。
    //消息发送太频繁会严重影响群成员的使用体验，大量发消息的场景（譬如系统监控报警）可以将这些信息进行整合，通过markdown消息以摘要的形式发送到群里。

    public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=XXXXXX";

    public static void main(String args[]) throws Exception {
        DingRobotUtil.send(WEBHOOK_TOKEN, "我就是我, 是不一样的烟火-测试");
    }

    public static void example() throws IOException {
        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"我就是我, 是不一样的烟火\"}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
    }
}
