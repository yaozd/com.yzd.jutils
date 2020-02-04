package com.yzd.jutils.shardingExt;

/**
 * 分库分表信息计算工具类
 * 每秒处理10万订单乐视集团支付架构
 * http://geek.csdn.net/news/detail/72973
 * github:
 * https://github.com/yaozd/letv-payorder
 * ///
 *
 * @author ash
 */
public class ShardUtil {

    /**
     * 根据用户id获得分库信息，虽然当前db数量是8，但为了之后扩容，进行了分库信息冗余，所以 mod 64
     */
    public static String getDBInfoByUserId(int userId) {
        int mod64Num = (userId / 10) % 64 + 1;
        int mod8 = (mod64Num - 1) % 2 + 1;
        String num = String.valueOf(mod8);
        // 如果是个位数，前面补0
        return num.length() == 2 ? num : "0" + num;
    }

    /**
     * 根据用户id获得分表信息
     */
    public static String getTableInfoByUserId(int userId) {
        return String.valueOf(userId % 10);
    }

    /**
     * 根据订单id获得分库信息
     */
    public static String getDBInfoByOrderId(String orderId) {
        return orderId.substring(1, 3);
    }

    /**
     * 根据订单id获得分表信息
     */
    public static String getTableInfoByOrderId(String orderId) {
        return orderId.substring(3, 4);
    }

}