package com.yzd.jutils.mybatisEXt.mybatisTemplateForYZD;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义条件
 */
public class MapWhere {
    private MapWhere(){}
    private Map<String,Object> mapWhere=new HashMap<>();
    public Map<String,Object> put(String key,Object val){
        mapWhere.put(key,val);
        return mapWhere;
    }
    public static MapWhere newMap(){
        return new MapWhere();
    }
}
