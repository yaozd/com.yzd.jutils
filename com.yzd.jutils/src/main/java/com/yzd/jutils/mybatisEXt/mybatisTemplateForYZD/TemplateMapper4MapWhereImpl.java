package com.yzd.jutils.mybatisEXt.mybatisTemplateForYZD;

import com.yzd.jutils.person.Person;

import java.util.ArrayList;
import java.util.List;

public class TemplateMapper4MapWhereImpl {
    Person selectOne(){
        //只取一条记录
        //PageWhere pageWhere=limitOne();
        //List<TbIfcertPushLog> selectListForPage(@Param("pojo")TbIfcertPushLog where, @Param("mapWhere")Map<String, Object> mapWhere, @Param("pageWhere")Map<String, Object> pageWhere);
        //PS:参数说明：
        //mapWhere:自定义查询条件
        //pageWhere:分页查询条件
        List<Person> itemList4Person=new ArrayList<>();

        return itemList4Person.stream().findFirst().orElseGet(()->null);
    }
    public PageWhere limitOne(){
        PageWhere pageWhere=new PageWhere();
        pageWhere.setBegin(0);
        pageWhere.setPageSize(1);
        return pageWhere;
    }
    class PageWhere{
        private Integer begin;
        private Integer pageSize;

        public Integer getBegin() {
            return begin;
        }

        public void setBegin(Integer begin) {
            this.begin = begin;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }
    }
}
