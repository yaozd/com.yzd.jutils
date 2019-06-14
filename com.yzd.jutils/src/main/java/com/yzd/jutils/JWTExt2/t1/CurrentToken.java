package com.yzd.jutils.JWTExt2.t1;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrentToken {

    //region==用户自定义信息
    /**
     * 当前用户ID
     */
    private Long id;
    /**
     * 当前用户名
     */
    private String name;
    /**
     * 唯一性标识：可用于用户没有登录的情况下
     */
    private String uuid;
    //end

    //region==TOKEN相关信息
    /**
     * T:数据类型（0=非登录情况下TOKEN、1=登录情况下TOKEN）
     * 通过不同的类型，页面做出相应业务逻辑处理
     */
    private Integer t;
    /**
     * V:数据结构版本
     */
    private Integer v;
    /**
     * E:过期时间戳(毫秒)
     */
    private Long e;
    //end


}
