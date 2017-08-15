package com.yzd.jutils.rsaExt;

import com.yzd.jutils.fastjson.FastJsonUtil;
import com.yzd.jutils.mapExt.BeanToMapUtils;
import com.yzd.jutils.rsaExt.obj.ParamClass;

import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by zd.yao on 2017/8/15.
 */
public class _MainTest {
    public static void main(String[] args) throws Exception {
        ParamClass paramClass=new ParamClass();
        paramClass.setProductCode("abc");
        //paramClass.setAaaa("aa");
        paramClass.setProductId("123");
        //ASCII 码从小到大排序（字典序）
        Map<String, Object> paramClassMap= BeanToMapUtils.convertBean(paramClass);
        SortedMap<String,Object> sort=new TreeMap<String,Object>(paramClassMap);
        //
        String mapStr= FastJsonUtil.serialize(sort);
        String publicKeyStr="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
        String filepath="G:/tmp/";
        //RSAEncrypt.genKeyPair(filepath);
        System.out.println("--------------公钥加密私钥解密过程-------------------");
        String plainText="ihep_公钥加密私钥解密";
        RSAPublicKey publicKey=RSAEncrypt.loadPublicKeyByStr(publicKeyStr);
        //公钥加密过程
        byte[] cipherData=RSAEncrypt.encrypt(publicKey,plainText.getBytes());
        String cipher=Base64.encode(cipherData);
        //私钥解密过程
        byte[] res=RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(filepath)), Base64.decode(cipher));
        String restr=new String(res);
        System.out.println("原文："+plainText);
        System.out.println("加密："+cipher);
        System.out.println("解密："+restr);
        System.out.println();

        System.out.println("--------------私钥加密公钥解密过程-------------------");
        plainText="ihep_私钥加密公钥解密";
        //私钥加密过程
        cipherData=RSAEncrypt.encrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(filepath)),plainText.getBytes());
        cipher=Base64.encode(cipherData);
        //公钥解密过程
        res=RSAEncrypt.decrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(filepath)), Base64.decode(cipher));
        restr=new String(res);
        System.out.println("原文："+plainText);
        System.out.println("加密："+cipher);
        System.out.println("解密："+restr);
        System.out.println();

        System.out.println("---------------私钥签名过程------------------");
        String content="ihep_这是用于签名的原始数据";
        String signstr=RSASignature.sign(content,RSAEncrypt.loadPrivateKeyByFile(filepath));
        System.out.println("签名原串："+content);
        System.out.println("签名串："+signstr);
        System.out.println();

        System.out.println("---------------公钥校验签名------------------");
        System.out.println("签名原串："+content);
        System.out.println("签名串："+signstr);

        System.out.println("验签结果："+RSASignature.doCheck(content, signstr, RSAEncrypt.loadPublicKeyByFile(filepath)));
        System.out.println();

    }
}
