package com.yzd.jutils.imageExt;



import com.yzd.jutils.rsaExt.Base64;

import java.io.*;

public class ImageHelper {
    /**
     * @param imgStr base64编码字符串
     * @param path   图片路径-具体到文件
     * @return
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     */
    public static boolean base64ToImage(String imgStr, String path) {
        if (imgStr == null) {
            return false;
        }
        try {
            // 解密
            byte[] b = Base64.decode(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @return
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     */
    public static String imageToBase64(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        return Base64.encode(data);
    }

    /**
     * 示例
     * Java 处理图片 base64 编码的相互转换
     * https://www.cnblogs.com/libra0920/p/5754356.html
     */
    public static void main(String[] args) {
        String strImg = imageToBase64("F:/2点033m.jpg");
        System.out.println(strImg);
        base64ToImage(strImg, "F:/86619-107.jpg");
    }
}
