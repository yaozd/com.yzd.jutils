package com.yzd.jutils.guava.issoe;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zd.yao on 2018/9/9.
 */
public class IssueGuava {
    //问题1-数据转换：为什么对transform结果的修改并不会起效
    @Test
    public void t1(){
        //参考：Guava Lists.transform的一个小坑
        //https://my.oschina.net/u/1013857/blog/1559525
        //解决方法一：解决这个问题，只需要使用lambda即可
        List<Integer> src = new ArrayList<>();
        List<String> to = src.stream().map(e -> e.toString()).collect(Collectors.toList());
        //
        //解决方法二：forEach即可
        List<Integer> result1=new ArrayList<>();
        src.forEach(item->result1.add(item));
        //
    }
    //参考：guava的坑
    //https://blog.csdn.net/lynn349x/article/details/56282666
    //问题2-把业务对象list转为map
    // 在业务中使用时，如果list里面存在重复元素（即key值相等的），则会直接报错。如果你仅仅想重复时直接覆盖，请千万不要使用guava。
    //附：key==null时也是会报错的喔～
    @Test
    public void t2(){
        List<StoreGoods> list = new ArrayList<>();
        //  list.add(new StoreGoods(null));
        list.add(new StoreGoods(1));
        list.add(new StoreGoods(1));

        Map<Integer, StoreGoods> map = Maps.uniqueIndex(list, new Function<StoreGoods, Integer>() {
            @Override
            public Integer apply(StoreGoods input) {
                return input.getId();
            }
        });
        // 使用lambda写法-推荐使用-byArvin
        Map<Integer, StoreGoods> map2 = Maps.uniqueIndex(list, input -> input.getId());
    }
    class StoreGoods{
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        Integer id;

        public StoreGoods(Integer id) {
            this.id = id;
        }
    }
}
