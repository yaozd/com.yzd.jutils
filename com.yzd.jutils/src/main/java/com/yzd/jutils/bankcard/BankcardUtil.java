package com.yzd.jutils.bankcard;

/**
 * Created by zd.yao on 2017/6/16.
 */

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/2/16.
 */
public class BankcardUtil {
     /*
    当你输入信用卡号码的时候，有没有担心输错了而造成损失呢？其实可以不必这么担心，
    因为并不是一个随便的信用卡号码都是合法的，它必须通过Luhn算法来验证通过。
    该校验的过程：
    1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
    2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，则将其减去9），再求和。
    3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
    例如，卡号是：5432123456788881
    则奇数、偶数位（用红色标出）分布：5432123456788881
    奇数位和=35
    偶数位乘以2（有些要减去9）的结果：1 6 2 6 1 5 7 7，求和=35。
    最后35+35=70 可以被10整除，认定校验通过。
    请编写一个程序，从键盘输入卡号，然后判断是否校验通过。通过显示：“成功”，否则显示“失败”。
    比如，用户输入：356827027232780
    程序输出：成功
    */

    /**
     * 校验银行卡卡号
     * 参考地址：JAVA 实现银行卡归属地查询
     * http://blog.csdn.net/luozhuang/article/details/47000715
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        if (StringUtils.isBlank(cardId)) return false;
        cardId = cardId.trim();
        cardId = cardId.replaceAll(" ", "");
        if (cardId.length() != 16 && cardId.length() != 19) return false;
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * 根据Luhn算法：可用于检测银行卡卡号
     * 银行卡卡号的校验位-LuhnVal值
     *
     * @param cardId
     * @return
     */
    public static char getBankCardLuhnVal(String cardId) {
        if (StringUtils.isBlank(cardId)) return 'N';
        cardId = cardId.trim();
        cardId = cardId.replaceAll(" ", "");
        if (cardId.length() != 16 && cardId.length() != 19) return 'N';
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return 'N';
        }
        return bit;
    }

    /**
     * 卡号网查卡号chakahao.com
     * 查询银行归属于地
     * http://www.chakahao.com/bankcha.asp
     * 银行卡号归属地数据库API接口
     * http://cha.yinhangkadata.com/
     * 测试环境的银行卡号：
     * 您查询的银行卡号: 6228201702161049
     * 农业银行 -- 金穗通商卡 -- 准贷记卡
     * 银行卡所在地区: 湖南省 -- 娄底
     *
     * @param args
     */
    public static void main(String[] args) {
//        ## java-generator
//        https://github.com/yaozd/java-generator
//        各种随机生成器，包括身份证号码，银行卡号，姓名，手机号，电子邮箱地址等
//        ```
        //TODO  随机生成一个银行卡卡号
        //String cardNumber = "6228 4828 9820 3884 767";
        //String cardNumber = "6228 2017 0216 1025 019";
        T2();

    }

    /**
     * 卡号网查卡号chakahao.com
     * 卡号网查卡号chakahao.com
     * 查询银行归属于地
     * http://www.chakahao.com/bankcha.asp
     * 测试环境的银行卡号：
     * 您查询的银行卡号: 6222080200011104406
     * 工商银行 -- 理财金卡 -- 借记卡
     * 银行卡所在地区: 北京 -- 北京
     *
     * @param args
     */
    private static void T2() {
        String cardNumber = "622208020001";
        SimpleDateFormat df = new SimpleDateFormat("HHmmss");
        cardNumber = String.format("%s%sX", cardNumber, df.format(new Date()));
        char luhnVal = BankcardUtil.getBankCardLuhnVal(cardNumber);
        System.out.println("luhnVal = " + luhnVal);
        cardNumber = cardNumber.replace('X', luhnVal);
        boolean isOk = BankcardUtil.checkBankCard(cardNumber);
        if (isOk) {
            System.out.println("cardNumber = " + cardNumber);
        }
        if (!isOk) {
            System.out.println("生成银行卡号不正确");
        }
    }

    /**
     * 卡号网查卡号chakahao.com
     * 卡号网查卡号chakahao.com
     * 查询银行归属于地
     * http://www.chakahao.com/bankcha.asp
     * 测试环境的银行卡号：
     * 您查询的银行卡号: 6228201702161049
     * 农业银行 -- 金穗通商卡 -- 准贷记卡
     * 银行卡所在地区: 湖南省 -- 娄底
     *
     * @param args
     */
    private static void T1() {
        String cardNumber = "6228";
        SimpleDateFormat df = new SimpleDateFormat("yyyy MMdd HHmm ss");
        cardNumber = String.format("%s %sX", cardNumber, df.format(new Date()));
        char luhnVal = BankcardUtil.getBankCardLuhnVal(cardNumber);
        System.out.println("luhnVal = " + luhnVal);
        cardNumber = cardNumber.replace('X', luhnVal);
        boolean isOk = BankcardUtil.checkBankCard(cardNumber);
        if (isOk) {
            System.out.println("cardNumber = " + cardNumber);
        }
        if (!isOk) {
            System.out.println("生成银行卡号不正确");
        }
    }
}
