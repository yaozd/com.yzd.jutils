package com.yzd.jutils.guava.listExt;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.yzd.jutils.fastjson.FastJsonUtil;
import com.yzd.jutils.print.PrintUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zd.yao on 2017/9/13.
 */
public class MultimapTest {
    public static void main(String[] args) {
        int id = 0;
        Multimap<String, String> shardMap = LinkedListMultimap.create();
        //构建模拟数据
        for (; id < 160; id++) {
            String dbVal = getDBInfoByUserId(id);
            String tbVal = getTableInfoByUserId(id);
            boolean isExist = shardMap.get(dbVal).contains(tbVal);
            if (isExist) {
                continue;
            }
            ;
            shardMap.put(dbVal, tbVal);
        }
        //Multimap<String, String>转为Map<String,List<String>>
        Map<String, List<String>> m = new HashMap<>();
        for (Map.Entry<String, Collection<String>> e : shardMap.asMap().entrySet()) {
            String dbVal = e.getKey();
            List<String> itemList = Lists.newArrayList(e.getValue());
            m.put(dbVal, itemList);
        }
        String v2 = FastJsonUtil.serialize(m);
        PrintUtil.outLn(v2);
    }

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
}
