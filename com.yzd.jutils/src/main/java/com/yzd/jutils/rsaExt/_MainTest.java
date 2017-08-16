package com.yzd.jutils.rsaExt;

import com.yzd.jutils.fastjson.FastJsonUtil;
import com.yzd.jutils.mapExt.BeanToMapUtils;
import com.yzd.jutils.rsaExt.obj.ParamClass;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 参考：
 * Java使用RSA加密解密签名及校验
 * http://blog.csdn.net/wangqiuyun/article/details/42143957/
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
        String publicKeyStr="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLdiot3lkdwj+C5I8wnH8eM/17ZedGs894vgOVnyHm1tI+ngHw9+k2jTTSlFGLqd+sJHzITiqpfPFKHaZkL9gcdZRViGOrjKAcfBWeVnWlx9WdLrDetLj/PRc2TP4qeJnqtoenvqG5QZRFAO2Y/stVJ6wCu8tydYaD0auIFh9HhQIDAQAB";
        String privateKeyStr="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMt2Ki3eWR3CP4LkjzCcfx4z/Xtl50azz3i+A5WfIebW0j6eAfD36TaNNNKUUYup36wkfMhOKql88UodpmQv2Bx1lFWIY6uMoBx8FZ5WdaXH1Z0usN60uP89FzZM/ip4meq2h6e+oblBlEUA7Zj+y1UnrAK7y3J1hoPRq4gWH0eFAgMBAAECgYEAl14Y8TkLicU7JPFPyjWTqO+69LQShRo/DzAmUIksWqWVkvpQrWpy+Z/CHaw0orhF7LbUktz8hk2q4Z3/bWfe2bHTK07z35V9an5hY2XH8kUxEHnEDOFLhCv/Qfc2Ie4sLcvoIeZBo6VQbPV/WF1WXjEAbkh4uMlPyztsNY7mG6ECQQDmNWrfic5ccyoNQCgEAFlJq2ABsHIe1WobzE2uaFadggyPSdByXVuItI2XlhoecQa3QB8S8e9EEx+ZAlspunPNAkEA4kGeNLwR2ATqxKuLC4hCGJd+Y2OdHpxUln4eBSdgb+czZ9mFKsgXhIE3TIvZbxRLB5S0lXb1bcFZNbfd1uJamQJAPuBgLGYLQx0BY1UemNkvifuQ4WU8BkfsD2Oh4/0Di0m1/H0AvfvZwGDwO3L391CPsQWGdzo1S7adbsFB/Qe8EQJABI27iGYsmcNnkjO9parFYcdSxN9RXzdYQHsjUYk8WKQ3laR8GKNUTddn7IN4CdNHu0xT4ho10FKu3/TmYxfFKQJBALAlbITFl2hy9b+4pudJ+tNz2FNa/XDMM0Qr5+fMxnPTg1rlKrCIZKq7oQAWg8qGje3GYAiGFnIJDvJQP8yl+Jg=";
        String filepath="G:/tmp/";
        //RSAEncrypt.genKeyPair(filepath);
        System.out.println("--------------公钥加密私钥解密过程-------------------");
        String plainText="ihep_公钥加密私钥解密";
        RSAPublicKey publicKey=RSAEncrypt.loadPublicKeyByStr(publicKeyStr);
        //公钥加密过程
        byte[] cipherData=RSAEncrypt.encrypt(publicKey,plainText.getBytes());
        String cipher=Base64.encode(cipherData);
        //====
        RSAPrivateKey privateKey=RSAEncrypt.loadPrivateKeyByStr(privateKeyStr);
        //私钥解密过程
        byte[] res=RSAEncrypt.decrypt(privateKey, Base64.decode(cipher));
        String restr = new String(res);
        System.out.println("原文："+plainText);
        System.out.println("加密："+cipher);
        System.out.println("解密："+restr);
        System.out.println();

        System.out.println("--------------私钥加密公钥解密过程-------------------");
        plainText="ihep_私钥加密公钥解密";
        //私钥加密过程
        cipherData=RSAEncrypt.encrypt(privateKey,plainText.getBytes());
        cipher=Base64.encode(cipherData);
        //公钥解密过程
        res=RSAEncrypt.decrypt(publicKey, Base64.decode(cipher));
        restr=new String(res);
        System.out.println("原文："+plainText);
        System.out.println("加密："+cipher);
        System.out.println("解密："+restr);
        System.out.println();

        System.out.println("---------------私钥签名过程------------------");
        String content="ihep_这是用于签名的原始数据";
        String signstr=RSASignature.sign(content,privateKeyStr);
        System.out.println("签名原串："+content);
        System.out.println("签名串："+signstr);
        System.out.println();

        System.out.println("---------------公钥校验签名------------------");
        System.out.println("签名原串："+content);
        System.out.println("签名串："+signstr);

        System.out.println("验签结果："+RSASignature.doCheck(content, signstr, publicKeyStr));
        System.out.println();

    }
}
