package com.yzd.jutils.prometheus;

import com.yzd.jutils.regex.RegExUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.math.BigDecimal;

/***
 *
 *  读取Prometheus采集的数据，然后把数据放在influxdb中，再通过grafana进行数据展示
 *
 * Created by yzd on 2018/9/6 13:30.
 */

public class PrometheusTest {
    /***
     * 1.
     *  过滤注释信息
     *  #[^\n\r]+
     *  # TYPE java_lang_MemoryPool_PeakUsage_committed untyped
     *  2.
     *  提取指标信息
     *  [\d\.]+$
     *  Tomcat_ProtocolHandler_maxExtensionSize{port="2001",} 8192.0
     *  3.
     *  java进行正则数据提取
     */
    @Test
    public void t1() {
        //String s="1\r\n2\r\n3\r\n \r\nabd\rb";
        String s = "java_lang_GarbageCollector_LastGcInfo_memoryUsageAfterGc_init{name=\"G1 Young Generation\",key=\"G1 Survivor Space\",} 2.7262976E7\njava_lang_GarbageCollector_LastGcInfo_memoryUsageAfterGc_init{name=\"G1 Young Generation\",key=\"Compressed Class Space\",} 0.0";
        //byArvin推荐使用StringUtils.split方式做字符串按行读取
        //把字符串按行读取--方式一:split 方式10000000次的速度大约是4s
        String[] itemArr = StringUtils.split(s, "[\r\n]");
        for (String item : itemArr) {
            System.out.println(item);
            if (StringUtils.isBlank(item)) {
                continue;
            }
            String numStr = RegExUtil.fetchStr("[\\d\\.E]+$", item);
            System.out.println(numStr);
            boolean isNum = NumberUtils.isCreatable(numStr);
            System.out.println(isNum);
            if (BooleanUtils.isNotTrue(isNum)) {
                continue;
            }
            //Prometheus JVM_Export的数据大部分是科学计数据：2.7262976E7 显示：27262976
            BigDecimal decimalVal = NumberUtils.createBigDecimal(numStr);
            System.out.println("number.BigDecimal()=" + decimalVal.toString());
        }
        System.out.println("=========");
        //判断是否包括指标：不区分字母大小写的方式
        int firstIndex = StringUtils.indexOfIgnoreCase("a11", "A");
        System.out.println(firstIndex);
        if (firstIndex == 0) {
            System.out.println("在字符串首位");
        }
        System.out.println("=========");
    }
}
