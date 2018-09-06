package com.yzd.jutils.stringExt;

import com.yzd.jutils.regex.RegExUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;

/***
 * 把字符串按行读取
 * byArvin推荐使用StringUtils.split方式做字符串按行读取
 * Created by yzd on 2018/9/6 9:00.
 */

public class ReadLineTest {
    public static void main(String[] args) throws IOException {
        //String s="1\r\n2\r\n3\r\n \r\nabd\rb";
        String s="java_lang_GarbageCollector_LastGcInfo_memoryUsageAfterGc_init{name=\"G1 Young Generation\",key=\"G1 Survivor Space\",} 2.7262976E7\njava_lang_GarbageCollector_LastGcInfo_memoryUsageAfterGc_init{name=\"G1 Young Generation\",key=\"Compressed Class Space\",} 0.0";
        //byArvin推荐使用StringUtils.split方式做字符串按行读取
        //把字符串按行读取--方式一:split 方式10000000次的速度大约是4s
        String[] itemArr= StringUtils.split(s,"[\r\n]");
        for(String item:itemArr){
            System.out.println(item);
            String numStr= RegExUtil.fetchStr("[\\d\\.E]+$",item);
            System.out.println(numStr);
            boolean isNum=NumberUtils.isCreatable(numStr);
            if(BooleanUtils.isNotTrue(isNum)){continue;}
            System.out.println(isNum);
            Number number= NumberUtils.createNumber(numStr);
            //2.7262976E7 显示：27262976
            BigDecimal decimalVal= NumberUtils.createBigDecimal(numStr);
            System.out.println("number.BigDecimal()="+decimalVal.toString());
            double doubleVal= NumberUtils.createDouble(numStr);
            System.out.println("doubleVal="+doubleVal);
        }
        System.out.println("=========");
        //不区分字母大小写的方式
        int firstIndex= StringUtils.indexOfIgnoreCase("a11","A");
        System.out.println(firstIndex);
        if(firstIndex==0){
            System.out.println("在字符串首位");
        }
        System.out.println("=========");
        //把字符串按行读取--方式二:BufferedReader 方式10000000次的速度大约是34s
        BufferedReader br =null;
        StringBuffer strbuf=new StringBuffer();
        try{
            br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(s.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
            String line;
            while ( (line = br.readLine()) != null ) {
                if(!line.trim().equals("")){
                    line="<br>"+line;//每行可以做加工
                    strbuf.append(line+"\r\n");
                }
            }
        }finally {
            IOUtils.closeQuietly(br);
        }

        System.out.println(strbuf.toString());
    }

    /***
     * BufferedReader 方式10000000次的速度大约是34s
     * 暂时没有出现Too many open files in system
     */
    @Test
    public void TooManyOpenFilesInSystem() throws IOException {
        for (int i = 0; i <10000000 ; i++) {
            t1();
        }
    }
    private void t1() throws IOException {
        String s="1\r\n2\r\n3\r\n \r\nabd\rb";
        BufferedReader br =null;
        StringBuffer strbuf=new StringBuffer();
        try{
            br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(s.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
            String line;
            while ( (line = br.readLine()) != null ) {
                if(!line.trim().equals("")){
                    line="<br>"+line;//每行可以做加工
                    strbuf.append(line+"\r\n");
                }
            }
        }finally {
            IOUtils.closeQuietly(br);
        }
    }

    /***
     * split 方式10000000次的速度大约是4s
     */
    @Test
    public void splitTest(){
        for (int i = 0; i <10000000 ; i++) {
            t2();
        }
    }
    private void t2(){
        String s="1\r\n2\r\n3\r\n \r\nabd\rb";
        //把字符串按行读取--方式一
        String[] itemArr= StringUtils.split(s,"[\r\n]");
        StringBuffer strbuf=new StringBuffer();
        for(String item:itemArr){
            //System.out.println(item);
            strbuf.append(item);
        }
    }

}
