package com.yzd.jutils.JWTExt2.t1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class JwtUtilTest {

    /**
     * 1.用户Token信息指纹（识别用户做流控与数据分析）
     */
    @Test
    public void createToken() {
        log.info("创建TOKEN实体");
        //CurrentToken token4Mode = JwtUtil.build4Login(1l, "yzd");
        CurrentToken token4Mode = JwtUtil.build4NoLogin();
        String token4Encode = JwtUtil.createToken(token4Mode);
        log.info("加密后TOKEN=" + token4Encode);
        String token4Decode = JwtUtil.verifyToken(token4Encode);
        log.info("解密后TOKEN=" + token4Decode);
        log.info("转为TOKEN实体");
        CurrentToken token4DecodeMode = JwtUtil.jsonToUser(token4Decode, CurrentToken.class);
        //通过不同的类型，页面做出相应业务逻辑处理
        if (CurrentTokenEnum.DataType.noLogin.CODE.equals(token4DecodeMode.getT())) {
            log.info("======非登录情况下TOKEN======");
        }
        if (CurrentTokenEnum.DataType.login.CODE.equals(token4DecodeMode.getT())) {
            log.info("======登录情况下TOKEN======");
        }
        boolean isExpireTime = JwtUtil.isExpireTime(token4DecodeMode.getE());
        log.info("TOKEN是否过期=" + isExpireTime);
    }

    @Test
    public void isExpireTime() {
        String token4Encode = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ5emQiLCJleHAiOjMyNDcyMTE1MjAwLCJVU0VSIjoie1wiZVwiOjE1NjA0Nzg3NTE1MjQsXCJpZFwiOjEsXCJuYW1lXCI6XCJ5emRcIixcInRcIjoxLFwidXVpZFwiOlwiYzYxM2IxODUtNWI4YS00ODYwLTlkMGUtMDg4MDE4ZWRmODFkXCIsXCJ2XCI6MX0iLCJpYXQiOjE1NjA0Nzg0NTF9.gr4AkcLpw4XPo-L9r0wzI1Y1PgoMaiBMfea3vbHnxZE";
        String token4Decode = JwtUtil.verifyToken(token4Encode);
        log.info("解密后TOKEN=" + token4Decode);
        CurrentToken token4DecodeMode = JwtUtil.jsonToUser(token4Decode, CurrentToken.class);
        log.info("转为TOKEN实体");
        boolean isExpireTime = JwtUtil.isExpireTime(token4DecodeMode.getE());
        log.info("TOKEN是否过期=" + isExpireTime);
    }

    @Test
    public void verifyToken() {
    }


}