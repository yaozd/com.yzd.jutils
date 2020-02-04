package com.yzd.jutils.config;

import com.yzd.jutils.rsaExt.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class ConfigUtil2 {
    /**
     * 私钥 ,
     */
    public static PrivateKey privateKey;
    /**
     * 公钥，
     */
    public static PublicKey publicKey;
    /**
     * 私钥文件路径 如：/rsa/prkey.key
     */
    private final static String privateKeyPath = "/rsa/prkey.key";

    /**
     * 公钥文件路径 如：/rsa/pbkey.key
     */
    private final static String publicKeyPath = "/rsa/pbkey.key";

    /**
     * init:初始化私钥
     */
    public static void initPrivateKey() {
        try {
            if (privateKey == null) {
                privateKey = getPrivateKey(privateKeyPath);
            }
        } catch (Exception e) {
            System.out.println("SecurityUtils初始化失败" + e.getMessage());
            e.printStackTrace();
            System.out.println("密钥初始化失败");
        }
    }

    /**
     * 初始化公钥
     */
    public static void initPublicKey() {
        try {
            if (publicKey == null) {
                publicKey = getPublicKey(publicKeyPath);
            }
        } catch (Exception e) {
            System.out.println("SecurityUtils初始化失败" + e.getMessage());
            e.printStackTrace();
            System.out.println("密钥初始化失败");
        }
    }

    /**
     * 对传入字符串进行签名
     *
     * @param inputStr
     * @return
     * @
     */
    public static String sign(String inputStr) {
        String result = null;
        try {
            if (privateKey == null) {
                //初始化
                initPrivateKey();
            }
            byte[] tByte;
            Signature signature = Signature.getInstance("SHA1withRSA", "BC");
            signature.initSign(privateKey);
            signature.update(inputStr.getBytes("UTF-8"));
            tByte = signature.sign();
            result = Base64.encode(tByte);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("密钥初始化失败");
        }
        return result;
    }

    /**
     * 对富友返回的数据进行验签
     *
     * @param src       返回数据明文
     * @param signValue 返回数据签名
     * @return
     */
    public static boolean verifySign(String src, String signValue) {
        boolean bool = false;
        try {
            if (publicKey == null) {
                initPublicKey();
            }
            Signature signature = Signature.getInstance("SHA1withRSA", "BC");
            signature.initVerify(publicKey);
            signature.update(src.getBytes("UTF-8"));
            bool = signature.verify(Base64.decode(signValue));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("密钥初始化失败");
        }
        return bool;
    }

    private static PrivateKey getPrivateKey(String filePath) {
        String base64edKey = new Resource().readFile(filePath);
        KeyFactory kf;
        PrivateKey privateKey = null;
        try {
            kf = KeyFactory.getInstance("RSA", "BC");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(base64edKey));
            privateKey = kf.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("密钥初始化失败");
        }
        return privateKey;
    }

    private static PublicKey getPublicKey(String filePath) {
        String base64edKey = new Resource().readFile(filePath);
        KeyFactory kf;
        PublicKey publickey = null;
        try {
            kf = KeyFactory.getInstance("RSA", "BC");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(base64edKey));
            publickey = kf.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("密钥初始化失败");
        }
        return publickey;
    }

    //读取当前Resource（资源文件夹下）的配置文件
    static class Resource {
        public String readFile(String path) {
            InputStream in = null;
            try {

                in = getClass().getResourceAsStream(path);
                return inputStream2String(in);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        private String inputStream2String(InputStream in) {
            try {
                StringBuffer out = new StringBuffer();
                byte[] b = new byte[4096];
                for (int n; (n = in.read(b)) != -1; ) {
                    out.append(new String(b, 0, n));
                }
                return out.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
}
