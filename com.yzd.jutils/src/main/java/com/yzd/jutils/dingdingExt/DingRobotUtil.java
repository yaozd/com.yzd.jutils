package com.yzd.jutils.dingdingExt;

import com.yzd.jutils.print.PrintUtil;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DingRobotUtil {
    private static final Logger logger = LoggerFactory.getLogger(DingRobotUtil.class);

    public static void send(String webHookToken, String content) {
        HttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(webHookToken);
            httppost.addHeader("Content-Type", "application/json; charset=utf-8");
            content = content.replace("\"", "");
            String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"#content#\"}}".replace("#content#", content);
            StringEntity se = new StringEntity(textMsg, "utf-8");
            httppost.setEntity(se);
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity(), "utf-8");
                if (result.indexOf("\"errmsg\":\"ok\"") == -1) {
                    throw new IllegalStateException("钉钉机器人发送信息失败：信息=" + content + ";返回结果=" + result);
                }
                PrintUtil.outLn(result);
            }
        } catch (Exception ex) {
            logger.error("钉钉机器人发送失败", ex);
        }
    }
}
