package com.yzd.jutils.hashExt2;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 将字符串转为数字
 * MurmurHash一致性Hash算法JAVA版
 * http://www.bubuko.com/infodetail-1000051.html
 * MurmurHash PK CityHash
 * http://blog.csdn.net/yfkiss/article/details/7337382
 * 陌生但默默一统江湖的MurmurHash
 * http://calvin1978.blogcn.com/articles/murmur.html?utm_source=tuicool&utm_medium=referral
 * MurmurHash。在Java的实现，Guava的Hashing类里有，上面提到的Jedis，Cassandra里都有Util类。
 * Created by zd.yao on 2017/7/5.
 */
public class MurmurHashTest {
    public static void main(String[] args) {
        String value = Murmurs.hashUnsigned("chensadddddddddddddddddddddsssssssssssssdddddddddddddddddddddddddaaaaahuo").toString();
        System.out.println(value);
        //2KW条测试记录情况下-guava 冲突碰撞的情况发生,而下面代码不会出现
        //目前建议使用Murmurs类
        //一个测试碰撞率的实验代码
        Integer testSize = 20000000;

        Set<String> set = Sets.newHashSetWithExpectedSize(testSize);

        int containsSize = 0;

        for (int i = 0; i < testSize; i++) {
            String tmp = Murmurs.hashUnsigned(String.valueOf(i)).toString();
            if (i % 5000 == 0)
                System.out.println("i=" + i);
            if (set.contains(tmp)) {
                System.out.println("stopped at :" + i);
                containsSize++;
            } else {
                set.add(tmp);
            }
        }

        System.out.println("containsSize=" + containsSize);
        System.out.println("dup_ratio   =" + containsSize / (1.0 * testSize));
    }
}
