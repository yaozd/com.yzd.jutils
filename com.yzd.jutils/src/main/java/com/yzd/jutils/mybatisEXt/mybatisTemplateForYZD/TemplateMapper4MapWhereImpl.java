package com.yzd.jutils.mybatisEXt.mybatisTemplateForYZD;

import com.yzd.jutils.person.Person;

import java.util.ArrayList;
import java.util.List;

public class TemplateMapper4MapWhereImpl {
    Person selectOne() {
        //自定义条件
        MapWhere.newMap().put("条件名", "条件值");
        //分页条件
        PageWhere pageWhere1 = PageWhere.newPage(0, 100);
        //只取一条记录
        PageWhere pageWhere2 = PageWhere.newPage4OnlyOne();
        //List<TbIfcertPushLog> selectListForPage(@Param("pojo")TbIfcertPushLog where, @Param("mapWhere")Map<String, Object> mapWhere, @Param("pageWhere")Map<String, Object> pageWhere);
        //PS:参数说明：
        //mapWhere:自定义查询条件
        //pageWhere:分页查询条件
        List<Person> itemList4Person = new ArrayList<>();
        return itemList4Person.stream().findFirst().orElseGet(() -> null);
    }

}
