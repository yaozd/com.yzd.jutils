package com.yzd.jutils.fastjson;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/***
 * json格式的响应数据--Jackson
 */
public class responseModelForDate {
    //Jackson 时间格式化，时间注解 @JsonFormat 用法、时差问题说明
    //https://www.sojson.com/blog/246.html
    //@JsonFormat 相差8小时问题--解决方法：timezone = "GMT+8"
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date editDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date shortDate;
}
