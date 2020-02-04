package com.yzd.jutils.mybatisEXt.mybatisTemplateForYZD;

/**
 * 分页条件
 */
public class PageWhere {
    /**
     * 分页条件
     *
     * @param pageIndex 页号-开始页为0页
     * @param pageSize  页大小-显示的行数
     */
    private PageWhere(int pageIndex, int pageSize) {
        pageIndex = pageIndex < 0 ? 0 : pageIndex;
        pageSize = pageSize < 0 ? 0 : pageSize;
        this.setBegin(pageIndex * pageSize);
        this.setSize(pageSize);
    }

    private Integer begin;
    private Integer size;

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }


    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @param pageIndex 页号-开始页为0页
     * @param pageSize  页大小-显示的行数
     * @return
     */
    public static PageWhere newPage(int pageIndex, int pageSize) {
        return new PageWhere(pageIndex, pageSize);
    }

    /**
     * SELECT ONLY ONE
     * 只取一条记录
     *
     * @return
     */
    public static PageWhere newPage4OnlyOne() {
        return new PageWhere(0, 1);
    }
}
