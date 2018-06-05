package com.yzd.jutils.dosExt;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class T1 {
    /**
     * 在JAVA中调用DOS命令
     * http://fengzhongtian.blog.163.com/blog/static/2000742720091734845446/
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void t() throws IOException, InterruptedException {
        while (true){
            String ls_1;
            //AB 压力测试
            Process process = Runtime.getRuntime().exec("cmd /c G:\\压力测试\\ab.exe -n1000 -c100  http://192.168.3.77:9000/other01/doSelectAll");
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(process.getInputStream()));
            while ( (ls_1=bufferedReader.readLine()) != null){
                System.out.println(ls_1);
            }
            process.waitFor();
        }
    }
}
