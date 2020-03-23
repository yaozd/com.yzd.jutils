package com.yzd.jutils.algorithmExt.longmapper;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @Author: yaozh
 * @Description:
 */
public class _MainTest {
    @Test
    public void Test() {
        /**
         * https://github.com/codahale/longmapper
         * 使用场景：通过用户ID生成对应longmapper，做为盐值，然后再生成密码保存在数据库中
         */
        final LongMapper mapper = new LongMapper("ayellowsubmarine".getBytes(UTF_8));

        System.out.printf("Mapped %d to %d\n", 200, mapper.map(200));
        System.out.printf("Unmapped %d to %d\n", mapper.map(200), mapper.unmap(mapper.map(200)));
        System.out.printf("Unmapped %d to %d\n", mapper.map(System.currentTimeMillis()), mapper.unmap(mapper.map(System.currentTimeMillis())));
        long intervalTime = Math.abs(System.currentTimeMillis() - System.currentTimeMillis());
        System.err.println(intervalTime);
        long max_interval_time=1000*60*5;
        System.err.println(intervalTime<max_interval_time);
        long l=-2135169630192318840L;
        System.err.println(l);

    }

    /**
     * 加密方法版本#完成性(sha1 short id)#时间限制
     * 保证了数据的完整性与时间限制，加密方法版本可以提供更加灵活的方法控制权限
     */
    @Test
    public void customKeyTest(){
        String key="XXXXXXXXXXXXXXXX";
        String sha1 = DigestUtils.sha1Hex(key.getBytes(UTF_8));
        System.out.println(sha1);
        //git short commit id
        String shortSha1 = sha1.substring(0, 10);
        System.err.println(shortSha1);
        LongMapper mapper=new LongMapper(key.getBytes(UTF_8));
        long val=mapper.map(System.currentTimeMillis());
        int version=1;
        //加密方法版本#完成性(sha1 short id)#时间限制
        //PS:保证了数据的完整性与时间限制，加密方法版本可以提供更加灵活的方法控制权限
        //通过http header进行传送
        String encryption=version+"#"+shortSha1+"#"+val;
        System.err.println(encryption);
        String[] arr = encryption.split("#");
        if(arr.length!=3){
            throw new IllegalStateException("格式不正确");
        }
        //
        String k1=arr[1];
        System.out.println(k1);
        String v1=arr[2];
        System.out.println(v1);
        long unmap = mapper.unmap(Long.parseLong(v1));
        System.out.println(unmap);
    }
}
