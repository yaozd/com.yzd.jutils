package com.yzd.jutils.shardingExt.idGeneratorExt;

import com.yzd.jutils.dateExt.DateUtil2;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zd.yao on 2017/11/13.
 */
public class _MainTest {

    //twitter的snowflake算法
    //64位=毫秒级时间41位+毫秒内序列12位+机器ID10位
    //参考：http://blog.csdn.net/wenxuechaozhe/article/details/51759016
    //详解Twitter开源分布式自增ID算法snowflake(附演算验证过程)
    //http://blog.csdn.net/jackpk/article/details/78248351
    @Test
    public void IdGenerator(){
        IdGenerator t=new IdGenerator();
        long t1=t.generateId().longValue();
        System.out.println(t1);
        //获得--41bit时间位
        long t2=t1>>22;
        System.out.println(t2);
        //将41bit时间位转为时间戳
        long t3= IdGenerator.SJDBC_EPOCH+t2;
        System.out.println(t3);
        //将41bit时间位转为时间戳，再转为真实的时间类型
        //然后再使用真实的时间进行分库分表
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(t3);
        String t4 = simpleDateFormat.format(date);
        System.out.println(t4);
        //新纪元
        //IdGenerator.SJDBC_EPOCH=2016-11-01 00:00:00
        System.out.println("====1");
        Date date1 = new Date(1477929600000L);
        String t5 = simpleDateFormat.format(date1);
        System.out.println(t5);
        System.out.println("====通过时间反向推理出id的值是多少");
        System.out.println("====2");
        Date d1= DateUtil2.string2Date("2017-11-13");
        long currentDate= d1.getTime();
        long tt1=Long.valueOf(currentDate - IdGenerator.SJDBC_EPOCH << 22 );
        System.out.println("反向推理出id的值="+tt1);
        long tt2=t1>>22;
        //将41bit时间位转为时间戳
        long tt3= IdGenerator.SJDBC_EPOCH+tt2;
        System.out.println(tt3);
        //将41bit时间位转为时间戳，再转为真实的时间类型
        //然后再使用真实的时间进行分库分表
        Date date11 = new Date(tt3);
        String tt4 = simpleDateFormat.format(date11);
        System.out.println(tt4);

    }


}
