package com.yzd.jutils.guava.preconditionsExt;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by zd.yao on 2018/9/9.
 */
public class PreconditionsExample {

    public static void main(String[] args) {

        /**
         * 对象判空处理
         */
        UserInfo userInfo = null;
        Preconditions.checkNotNull(userInfo, "userInfo不能为null");

        /**
         * List对象判空处理
         */
        List<String> list = Lists.newArrayList();
        Preconditions.checkNotNull(list, "传入的list不能为null");

        /**
         * 数值类型判断处理
         */
        Long projectId = -12L;
        Preconditions.checkNotNull(projectId, "projectId不能为null");
        Preconditions.checkArgument(projectId > 0, "输入projectId必须大于0", projectId);
    }

    class UserInfo {
        private String name;
    }
}

