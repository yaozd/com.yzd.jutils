package com.yzd.jutils.algorithmExt.byteArrayExt;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @Author: yaozh
 * @Description:
 */
public class _MainTest {
    /**
     * 参考：
     * java Charset（字符集）类的操作
     * https://blog.csdn.net/qq_36691683/article/details/81608244
     * System：System.arraycopy方法详解
     * https://segmentfault.com/a/1190000009922279
     */
    // 创建简体中文对应的Charset
    Charset charset = Charset.forName("utf-8");
    // 获取charset对象对应的编码器
    CharsetDecoder charsetDecoder = charset.newDecoder();
    @Test
    public void t1() throws CharacterCodingException {
        ByteArrayJava byteArrayJava=new ByteArrayJava(13);
        byteArrayJava.add("{\"traceId\":\"".getBytes(charset));
        byteArrayJava.add("{\"traceId\":\"".getBytes(charset));
        byte[] bytes = byteArrayJava.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        // 将ByteBuffer的数据解码成字符序列
        System.out.println(charsetDecoder.decode(byteBuffer));
    }
}
