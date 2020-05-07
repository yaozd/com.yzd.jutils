package com.yzd.jutils.algorithmExt.byteArrayExt;

import org.apache.commons.lang3.StringUtils;
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

    /**
     * 最后的方案：
     * 通过netty的byteBuf进行字符的拼接，然后直接传给netty进行发送，解决的。
     * @throws CharacterCodingException
     */
    @Test
    public void t1() throws CharacterCodingException {
        ByteArrayJava byteArrayJava=new ByteArrayJava(13);
        "ttt".getBytes();
        byteArrayJava.add("{\"traceId\":\"".getBytes(charset));
        byteArrayJava.add("{\"traceId\":\"".getBytes(charset));
        byte[] bytes = byteArrayJava.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        // 将ByteBuffer的数据解码成字符序列
        System.out.println(charsetDecoder.decode(byteBuffer));
        ByteBuffer buffer = charset.encode("txt");
        byte[] bytes1 = charset.encode("txt").array();
        buffer.array();
        byteBuffer.put(buffer);
    }
    @Test
    public void t2(){
        int i=0;
        System.out.println(i++>0);
    }

}
