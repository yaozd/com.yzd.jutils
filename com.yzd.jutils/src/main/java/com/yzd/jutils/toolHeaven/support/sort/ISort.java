package com.yzd.jutils.toolHeaven.support.sort;

import java.util.List;

/**
 * 排序接口
 *
 * @author binbin.hou
 * @since 0.1.6
 */
public interface ISort<T> {

    /**
     * 对字段列表进行排序，返回新的列表
     *
     * @param list 待排序列表
     * @return 排序后的列表
     */
    List<T> sort(final List<T> list);

}
