package com.yzd.jutils.shardingExt;

import com.yzd.jutils.print.PrintUtil;

/**
 * 目前没有写完没有参考意义
 * 后面可参考-【分库分表-总结-byArvin】在
 * Created by zd.yao on 2017/8/24.
 */
public class _MainTest {
    public static void main(String[] args){
        for(int i=100;i<130;i++){
            String db1=ShardUtil.getDBInfoByUserId(i);
            PrintUtil.outLn(db1);
        }

    }
}
