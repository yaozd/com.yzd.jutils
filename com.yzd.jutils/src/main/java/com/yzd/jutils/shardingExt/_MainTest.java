package com.yzd.jutils.shardingExt;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.yzd.jutils.print.PrintUtil;

/**
 * 目前没有写完没有参考意义
 * 后面可参考-【分库分表-总结-byArvin】在
 * 参考：一种可以避免数据迁移的分库分表scale-out扩容方式
 * http://www.cnblogs.com/tommyli/p/3767362.html
 * Created by zd.yao on 2017/8/24.
 */
public class _MainTest {
    public static void main(String[] args) {
        Integer id = 0;
        Multimap<String, Integer> dbMap = ArrayListMultimap.create();
        ;
        //
        for (; id < 160; id++) {
            String val = getVal(id);
            dbMap.put(val, id);
            PrintUtil.outLn(getVal(id));
        }
        int pause = 0;
    }

    private static String getVal(int id) {
        if (id < 10) {
            //2个库
            return "db" + (id % 2);
        }
        if (id < 20) {
            //4个库
            return "db" + ((id % 2) + 2);
        }
        if (id < 40) {
            //8个库
            return "db" + ((id % 4) + 4);
        }
        if (id < 80) {
            //16个库
            return "db" + ((id % 8) + 8);
        }
        if (id < 160) {
            //32个库
            return "db" + (id % 16);
        }
        //
        throw new IllegalArgumentException("id out of range. id:" + id);
    }
    //region 版本1-库分片计算规则
    //以用户id作为唯度
    private static String getDBInfoByUserId(int id) {
        if (id < 10) {
            //2个库
            return "db" + (id % 2);
        }
        if (id < 20) {
            //4个库
            return "db" + ((id % 2) + 2);
        }
        if (id < 40) {
            //8个库
            return "db" + ((id % 4) + 4);
        }
        if (id < 80) {
            //16个库
            return "db" + ((id % 8) + 8);
        }
        if (id < 160) {
            //32个库
            return "db" + (id % 16);
        }
        //
        throw new IllegalArgumentException("id out of range. id:" + id);
    }
    //endregion
}
