package com.yzd.jutils.rsaExt2;

/**
 * 参考-众安的投保的SDK的代码-实现RSA的分片加密
 * 关于RSA算法密钥长度/密文长度/明文长度
 * http://blog.sina.com.cn/s/blog_76550fd7010147tp.html
 * 解决RSA的明文长度问题就要进行分片加密
 * Created by zd.yao on 2017/8/17.
 */
public class _MainTest {
    public static void main(String[] args) throws Exception {
        String publicKeyStr="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLdiot3lkdwj+C5I8wnH8eM/17ZedGs894vgOVnyHm1tI+ngHw9+k2jTTSlFGLqd+sJHzITiqpfPFKHaZkL9gcdZRViGOrjKAcfBWeVnWlx9WdLrDetLj/PRc2TP4qeJnqtoenvqG5QZRFAO2Y/stVJ6wCu8tydYaD0auIFh9HhQIDAQAB";
        String privateKeyStr="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMt2Ki3eWR3CP4LkjzCcfx4z/Xtl50azz3i+A5WfIebW0j6eAfD36TaNNNKUUYup36wkfMhOKql88UodpmQv2Bx1lFWIY6uMoBx8FZ5WdaXH1Z0usN60uP89FzZM/ip4meq2h6e+oblBlEUA7Zj+y1UnrAK7y3J1hoPRq4gWH0eFAgMBAAECgYEAl14Y8TkLicU7JPFPyjWTqO+69LQShRo/DzAmUIksWqWVkvpQrWpy+Z/CHaw0orhF7LbUktz8hk2q4Z3/bWfe2bHTK07z35V9an5hY2XH8kUxEHnEDOFLhCv/Qfc2Ie4sLcvoIeZBo6VQbPV/WF1WXjEAbkh4uMlPyztsNY7mG6ECQQDmNWrfic5ccyoNQCgEAFlJq2ABsHIe1WobzE2uaFadggyPSdByXVuItI2XlhoecQa3QB8S8e9EEx+ZAlspunPNAkEA4kGeNLwR2ATqxKuLC4hCGJd+Y2OdHpxUln4eBSdgb+czZ9mFKsgXhIE3TIvZbxRLB5S0lXb1bcFZNbfd1uJamQJAPuBgLGYLQx0BY1UemNkvifuQ4WU8BkfsD2Oh4/0Di0m1/H0AvfvZwGDwO3L391CPsQWGdzo1S7adbsFB/Qe8EQJABI27iGYsmcNnkjO9parFYcdSxN9RXzdYQHsjUYk8WKQ3laR8GKNUTddn7IN4CdNHu0xT4ho10FKu3/TmYxfFKQJBALAlbITFl2hy9b+4pudJ+tNz2FNa/XDMM0Qr5+fMxnPTg1rlKrCIZKq7oQAWg8qGje3GYAiGFnIJDvJQP8yl+Jg=";
        System.out.println("--------------公钥加密私钥解密过程-------------------");
        String plainText="ihep_公钥加密私钥解密";
        //明文长度(bytes) <= 密钥长度(bytes)-11.这样的话,对于上限密钥长度1024bits能加密的明文上限就是117bytes
        //下面是明文长度超过117字节的情况-如果使用RSA的分片加密的方法将不会报明文长度超长的问题
        //模拟超长的报文
        plainText="{\"campaignId\":\"xxx\",\"channelOrderNo\":\"xxx\",\"policyHolderBirthDay\":\"xxx\",\"policyHolderCertiNo\":\"xxx\",\"policyHolderCertiType\":\"xxx\",\"policyHolderGender\":\"xxx\",\"policyHolderPhone\":\"xxx\",\"policyHolderUserName\":\"xxx\",\"productPackageId\":\"xxx\",\"relationToPolicyHolder\":\"xxx\"}";
        //
        String cipher=SignatureUtils.rsaEncrypt(plainText,publicKeyStr,"UTF-8");
        String restr=SignatureUtils.rsaDecrypt(cipher,privateKeyStr,"UTF-8");
        System.out.println("原文："+plainText);
        System.out.println("加密："+cipher);
        System.out.println("解密："+restr);
        System.out.println();
        //
        System.out.println("---------------私钥签名过程------------------");
        String content="ihep_这是用于签名的原始数据";
        //
        content="{\"campaignId\":\"xxx\",\"channelOrderNo\":\"xxx\",\"policyHolderBirthDay\":\"xxx\",\"policyHolderCertiNo\":\"xxx\",\"policyHolderCertiType\":\"xxx\",\"policyHolderGender\":\"xxx\",\"policyHolderPhone\":\"xxx\",\"policyHolderUserName\":\"xxx\",\"productPackageId\":\"xxx\",\"relationToPolicyHolder\":\"xxx\"}";
        //
        String signstr= SignatureUtils.rsaSign(content, privateKeyStr, "UTF-8");;
        System.out.println("签名原串：" + content);
        System.out.println("签名串：" + signstr);
        System.out.println();
        //
        System.out.println("---------------公钥校验签名------------------");
        System.out.println("签名原串："+content);
        System.out.println("签名串："+signstr);
        System.out.println("验签结果："+SignatureUtils.checkRSASign(signstr,content,publicKeyStr,"UTF-8"));
        System.out.println();

    }
}
