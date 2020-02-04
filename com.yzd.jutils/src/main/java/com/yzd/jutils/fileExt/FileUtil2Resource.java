package com.yzd.jutils.fileExt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil2Resource {

    public static void main(String[] args) throws IOException {
        Resource resource = new Resource();
        resource.getInputStream();
    }

    /**
     * 利用类装载器
     * java读取resources下的配置文件+文件相对路径小结
     * https://blog.csdn.net/C_envelope/article/details/82942836
     */
    static class Resource {
        public void getInputStream() {
            String fileName = this.getClass().getClassLoader().getResource("rhino/t0.js").getPath();
            System.out.println(fileName);
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("rhino/t0.js");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String output = null;
            try {
                output = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(output);
        }
    }
}
