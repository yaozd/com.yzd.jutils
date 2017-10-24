package com.yzd.jutils.varnishExt;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by zd.yao on 2017/10/24.
 */
public class _MainTest {
    //清理varnish缓存
    @Test
    public void purge(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String varnishUrl="https://www.baidu.com/";
        HttpUriRequest purgeRequest = RequestBuilder.create("PURGE").setUri(varnishUrl).build();
        try {
            httpclient.execute(purgeRequest);
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * varnish 3.x 中清理缓存的命令是 ban
     * Varnish常用相关命令工具
     * http://www.cnblogs.com/kcen/p/3200244.html
     * ban是手动清楚
     * ​Varnish命令行管理工具：varnishadm
       http://www.sohu.com/a/120341271_494937
     * @throws IOException
     */
    @Test
    public void ban() throws IOException {
        //varnish purge缓存清理技术总结（附相关的代码）
        //http://lisily.blog.163.com/blog/static/24414520127203845453/
        String url="";
        //
        Socket socket=new Socket();
        socket.connect(new InetSocketAddress("192.168.1.1", 3500),5000);
        socket.setSendBufferSize(1000);
        socket.setSoTimeout(5000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        //ban.url .*//清除所有的
        String baseCMD = "ban";
        String command = baseCMD + ".url " + url;
        writer.println(command);
        writer.flush();
        String result=null;
        while((result = reader.readLine()) != null){
            result = result.trim();
            System.out.println(result);
            if(result.equals("200 0")){
                break;
            }
        }
    }
}
