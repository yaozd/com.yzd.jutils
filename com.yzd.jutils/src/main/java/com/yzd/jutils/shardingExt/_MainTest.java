package com.yzd.jutils.shardingExt;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.yzd.jutils.print.PrintUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 目前没有写完没有参考意义
 * 后面可参考-【分库分表-总结-byArvin】在
 * 参考：一种可以避免数据迁移的分库分表scale-out扩容方式
 * http://www.cnblogs.com/tommyli/p/3767362.html
 * 目前是一个库2个表的结构
 * Created by zd.yao on 2017/8/24.
 */
public class _MainTest {

    public static void main(String[] args) {
        //目前是一个库2个表的结构
        //目前的分段是20、40、80、160
        dbRuleTest();
        tbRuleTest();
        ruleTest_DB_TB();
        ruleTest_DB_TB2();
        int pause = 0;
    }

    //分库分表的计算结果-整理版
    private static void ruleTest_DB_TB2() {
        PrintUtil.outLn("分库分表的计算结果-整理版");
        Integer id = 0;
        //分库分表的计算结果
        //LinkedListMultimap与LinkedHashMultimap有先后序顺的集合
        Multimap<String, String> shardMap = LinkedListMultimap.create();
        //Multimap<String, String> shardMap = LinkedHashMultimap.create();
        //
        for (; id < 160; id++) {
            String dbVal = getDBInfoByUserId(id);
            String tbVal = getTableInfoByUserId(id);
            boolean isExist = shardMap.get(dbVal).contains(tbVal);
            if (isExist) {
                continue;
            }
            ;
            shardMap.put(dbVal, tbVal);
            PrintUtil.outLn("dbVal=" + dbVal + "|" + "tbVal=" + tbVal);
        }
        PrintUtil.outLn("输出分库分表的计算结果:");
        for (Map.Entry<String, Collection<String>> e : shardMap.asMap().entrySet()) {
            String dbVal = e.getKey();
            System.out.print(dbVal + "= ");
            List<String> itemList = Lists.newArrayList(e.getValue());
            int n = 0;
            for (String i : itemList) {
                System.out.print(i);
                if (n == 0) {
                    System.out.print(" | ");
                    n++;
                }
            }
            PrintUtil.outLn("");
        }
        int pause = 0;
    }

    //分库分表的计算结果
    private static void ruleTest_DB_TB() {
        Integer id = 0;
        //分库分表的计算结果
        Multimap<String, String> shardMap = ArrayListMultimap.create();
        //
        for (; id < 160; id++) {
            String dbVal = getDBInfoByUserId(id);
            String tbVal = getTableInfoByUserId(id);
            shardMap.put(dbVal, tbVal);
            PrintUtil.outLn("dbVal=" + dbVal + "|" + "tbVal=" + tbVal);
        }
        int pause = 0;
    }

    private static void tbRuleTest() {
        Integer id = 0;
        Multimap<String, Integer> tbMap = ArrayListMultimap.create();
        //
        for (; id < 160; id++) {
            String val = getTableInfoByUserId(id);
            tbMap.put(val, id);
            PrintUtil.outLn(getTableInfoByUserId(id));
        }
        int pause = 0;
    }

    //region 版本1-表分片计算规则
    //以用户id作为唯度
    private static String getTableInfoByUserId(int id) {
        if (id < 10) {
            //2个表
            return "t" + (id % 2);
        }
        if (id < 20) {
            //4个表
            return "t" + (id % 2) + "_1";
        }
        if (id < 40) {
            //8个表
            return "t" + (id % 4) + "_2";
        }
        if (id < 80) {
            //16个表
            return "t" + (id % 8) + "_3";
        }
        if (id < 160) {
            //32个表
            return "t" + (id % 16) + "_4";
        }
        throw new IllegalArgumentException("[tbRule]id out of range. id:" + id);
    }

    //endregion
    private static void dbRuleTest() {
        Integer id = 0;
        Multimap<String, Integer> dbMap = ArrayListMultimap.create();
        ;
        //
        for (; id < 160; id++) {
            String val = getDBInfoByUserId(id);
            dbMap.put(val, id);
            PrintUtil.outLn(getDBInfoByUserId(id));
        }
        int pause = 0;
    }

    //region 版本1-库分片计算规则
    //以用户id作为唯度
    private static String getDBInfoByUserId(int id) {
        if (id < 10) {
            //当只有一个数据库时是不需要进行库的分片计算的
            return "db" + (id % 2);
        }
        if (id < 20) {
            //2个库
            return "db" + ((id % 2) + 2);
        }
        if (id < 40) {
            //4个库
            return "db" + ((id % 4) + 4);
        }
        if (id < 80) {
            //8个库
            return "db" + ((id % 8) + 8);
        }
        if (id < 160) {
            //16个库
            return "db" + (id % 16);
        }
        //
        throw new IllegalArgumentException("[dbRule]id out of range. id:" + id);
    }
    //endregion
}
