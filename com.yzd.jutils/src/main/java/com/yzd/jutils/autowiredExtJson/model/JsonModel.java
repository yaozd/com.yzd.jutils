package com.yzd.jutils.autowiredExtJson.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通过JSON的结构的方式实现无结构数据存储
 */
@Data
@NoArgsConstructor
public class  JsonModel  {
    private Long id;
    /**
     * 实体类型：
     */
    private Integer modelType;
    /**
     * 实体结构版本号：
     * 作用：1、实体增加新的字段后的数据的转换；2、实体兼容性
     */
    private Integer modelVersion;
    /**
     * 搜索数据：
     * 作用：对关键数据提供搜索功能
     * eg:用户名或手机号
     */
    private String searchData;
    /**
     * 自定义转换后的json字符串
     */
    private String jsonData;

}
