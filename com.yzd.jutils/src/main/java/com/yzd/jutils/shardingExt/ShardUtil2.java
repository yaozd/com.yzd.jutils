package com.yzd.jutils.shardingExt;

/**
 * Created by zd.yao on 2017/8/24.
 */
public class ShardUtil2 {
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
}
