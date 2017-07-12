package com.yzd.jutils.hashExt2;

/**
 * Created by zd.yao on 2017/7/5.
 */
import com.google.common.collect.Sets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.Set;

/**
 * Cassandra 之中的Hash 算法： MurMur3
 http://www.flyml.net/2016/09/05/cassandra-tutorial-murmurhash/
 */
public class GuavaHashTest {

    public static void main(String[] args) {

        /***
         *guava 冲突碰撞的情况发生
         * 结论：在我的测试之中，2KW条测试记录（虽然都是int）完全没有冲突发生的情况。 如果是使用Guava自带的32bit，或者是128-bit但是返回int值，就会出现冲突碰撞的情况发生。
         *
         */
        HashFunction hf = Hashing.murmur3_128();

        Integer testSize = 20000000;

        Set<Integer> set = Sets.newHashSetWithExpectedSize(testSize);

        int containsSize = 0;

        for(int i = 0; i < testSize; i++) {
            int tmp = hf.newHasher().putInt(i).hash().asInt();
            if(i % 5000 == 0 )
                System.out.println("i=" + i);
            if(set.contains(tmp)) {
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
