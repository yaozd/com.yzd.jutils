package com.yzd.jutils.httpExt;

import com.google.common.base.Splitter;
import com.yzd.jutils.regex.RegExUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zd.yao on 2017/9/26.
 */
public class _MainTest {
    public static void main(String[] args) {
        String url = "http://localhost:10001/openExe";
        Map<String, String> getParam = new HashMap<>();
        getParam.put("id", "中国");
        //HttpClient-HttpDelete支持HttpEntity
        String baiduUrl = "https://www.baidu.com/";
        String result1 = HttpUtil.sendDelete2(baiduUrl, "", "utf-8", "utf-8", null);
        String result = HttpUtil.sendPostXML(url, getParam, "5678", "utf-8", "utf-8");
    }

    @Test
    public void t1() {
        String url = "http://hq.stock.sohu.com/cn/137/cn_002137-1.html";
        //String url="http://hq.stock.sohu.com/cn/004/cn_000004-1.html";
        String result1 = HttpUtil.sendGet(url, new HashMap<>());
        System.out.println(result1);
        List<String> matchList = RegExUtil.regMatchAll2list("'price_A1':\\[[^\\]]+\\]", result1);
        if (matchList.size() == 0) {
            System.out.println("step-01");
            return;
        }
        List<String> resultList = Splitter.on(",").trimResults().splitToList(matchList.get(0));
        if (resultList.size() == 0) {
            System.out.println("step-02");
            return;
        }
        if (resultList.size() != 7) {
            System.out.println("step-03");
            return;
        }
        String percent = resultList.get(4);
        String price = resultList.get(2);
        double zfVal = Double.parseDouble(percent.replace("'", "").replace("%", "").replace("+", ""));
        System.out.println("step-04=" + zfVal);
        //在JAVA中怎么比较Double类型数据的大小？
        //非整型数，运算由于精度问题，可能会有误差，建议使用BigDecimal类型！
        //当此 BigDecimal 在数字上小于、等于或大于 val 时，返回 -1、0 或 1。
        String d2 = "5";
        BigDecimal data1 = new BigDecimal(zfVal);
        BigDecimal data2 = new BigDecimal(d2);
        int copareResult = data1.compareTo(data2);
        if (copareResult == 1) {
            System.out.println("大于" + d2 + "%;当前值=" + zfVal + "%;当前价格=" + price);
            return;
        }
        if (copareResult == 0) {
            System.out.println("等于" + d2 + "%;当前值=" + zfVal + "%;当前价格=" + price);
            ;
            return;
        }
        if (copareResult == -1) {
            System.out.println("小于" + d2 + "%;当前值=" + zfVal + "%;当前价格=" + price);
            ;
            return;
        }
    }

    //测试完整版
    @Test
    public void t2() {
        String fullCode = "000004";
        getContent(fullCode);
    }

    private void getContent(String fullCode) {
        if (fullCode == null || fullCode.length() != 6) {
            throw new IllegalStateException("fullCode=[" + fullCode + "]格式不正确！");
        }
        String subCode = fullCode.substring(3);
        //String url="http://hq.stock.sohu.com/cn/004/cn_000004-1.html";
        //%1$s %2$s 代表顺序
        String url = String.format("http://hq.stock.sohu.com/cn/%1$s/cn_%2$s-1.html", subCode, fullCode);
        //
        String result1 = HttpUtil.sendGet(url, new HashMap<>());
        System.out.println(result1);
        List<String> matchList = RegExUtil.regMatchAll2list("'price_A1':\\[[^\\]]+\\]", result1);
        if (matchList.size() == 0) {
            System.out.println("step-01");
            return;
        }
        List<String> resultList = Splitter.on(",").trimResults().splitToList(matchList.get(0));
        if (resultList.size() == 0) {
            System.out.println("step-02");
            return;
        }
        if (resultList.size() != 7) {
            System.out.println("step-03");
            return;
        }
        String percent = resultList.get(4);
        String price = resultList.get(2);
        double zfVal = Double.parseDouble(percent.replace("'", "").replace("%", "").replace("+", ""));
        System.out.println("step-04=" + zfVal);
        //在JAVA中怎么比较Double类型数据的大小？
        //非整型数，运算由于精度问题，可能会有误差，建议使用BigDecimal类型！
        //当此 BigDecimal 在数字上小于、等于或大于 val 时，返回 -1、0 或 1。
        String d2 = "5";
        BigDecimal data1 = new BigDecimal(zfVal);
        BigDecimal data2 = new BigDecimal(d2);
        int copareResult = data1.compareTo(data2);
        if (copareResult == 1) {
            System.out.println("大于" + d2 + "%;当前值=" + zfVal + "%;当前价格=" + price);
            return;
        }
        if (copareResult == 0) {
            System.out.println("等于" + d2 + "%;当前值=" + zfVal + "%;当前价格=" + price);
            ;
            return;
        }
        if (copareResult == -1) {
            System.out.println("小于" + d2 + "%;当前值=" + zfVal + "%;当前价格=" + price);
            ;
            return;
        }
    }
}
