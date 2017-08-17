package com.yzd.jutils.rsaExt2;

import com.alibaba.fastjson.JSONObject;

import javax.crypto.Cipher;
import java.io.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by zd.yao on 2017/8/17.
 */
public class SignatureUtils {
    public static String encryptAndSign(String bizContent, String publicKey, String privateKey, String charset, boolean isEncrypt, boolean isSign) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (isEmpty(charset)) {
            charset = "GBK";
        }
        if (isEncrypt) {
            String encrypted = rsaEncrypt(bizContent, publicKey, charset);
            sb.append("bizContent=").append(encrypted);
            if (isSign) {
                String sign = rsaSign(encrypted, privateKey, charset);
                sb.append("&sign=").append(sign);
            }
        } else if (isSign) {
            sb.append("bizConent=").append(bizContent);
            String sign = rsaSign(bizContent, privateKey, charset);
            sb.append("&sign=").append(sign);
        }
        return sb.toString();
    }

    public static String rsaEncrypt(Map<String, Object> map, String publicKey, String charset) throws Exception {
        String bizContent = getSignContent(map);
        return rsaEncrypt(bizContent, publicKey, charset);
    }

    public static String rsaEncrypt(String content, String publicKey, String charset) throws Exception {
        PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, pubKey);
        byte[] data = isEmpty(charset) ? content.getBytes() : content.getBytes(charset);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        int i = 0;
        while (inputLen - offSet > 0) {
            byte[] cache;
            if (inputLen - offSet > 117)
                cache = cipher.doFinal(data, offSet, 117);
            else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 117;
        }
        byte[] encryptedData = Base64.encodeBase64(out.toByteArray());
        out.close();
        return isEmpty(charset) ? new String(encryptedData) : new String(encryptedData, charset);
    }

    public static String rsaDecrypt(String content, String privateKey, String charset) throws Exception {
        return rsaDecrypt(content, privateKey, charset, null);
    }

    public static String rsaDecrypt(String content, String privateKey, String charset, String signType) throws Exception {
        int maxDecryptBlock = 128;
        if ("RSA2048".equalsIgnoreCase(signType)) maxDecryptBlock = 256;
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8("RSA",
                    new ByteArrayInputStream(privateKey.getBytes()));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(2, priKey);
            byte[] encryptedData = isEmpty(charset) ? Base64.decodeBase64(content.getBytes()) :
                    Base64.decodeBase64(content.getBytes(charset));
            int inputLen = encryptedData.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            int i = 0;
            if (privateKey.length() > 1000) {
                maxDecryptBlock = 256;
            }
            while (inputLen - offSet > 0) {
                byte[] cache;
                if (inputLen - offSet > maxDecryptBlock)
                    cache = cipher.doFinal(encryptedData, offSet, maxDecryptBlock);
                else {
                    cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * maxDecryptBlock;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();
            return isEmpty(charset) ? new String(decryptedData) : new String(decryptedData, charset);
        } catch (Exception e) {
            throw new Exception("RSA解密失败. EncodeContent = " + content + ",charset = " + charset, e);
        }
    }

    public static String rsaSign(Map<String, Object> params, String privateKey, String charset) throws Exception {
        String content = getSignContent(params);
        return rsaSign(content, privateKey, charset);
    }

    public static String rsaSign(String content, String privateKey, String charset) throws Exception {
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8("RSA", new ByteArrayInputStream(privateKey.getBytes()));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(priKey);
            if (isEmpty(charset))
                signature.update(content.getBytes());
            else {
                signature.update(content.getBytes(charset));
            }
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64(signed));
        } catch (Exception e) {
            throw new Exception("RSAcontent = " + content + "; charset = " + charset, e);
        }
    }

    public static Boolean checkRSASign(String sign, String content, String publicKey, String charset) throws Exception {
        return rsaCheckContent(content, sign, publicKey, charset);
    }

    public static String getSignContent(Map<String, Object> sortedParams) {
        JSONObject js = new JSONObject();
        List keys = new ArrayList(sortedParams.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = (String) sortedParams.get(key);
            js.put(key, value);
        }
        return js.toJSONString();
    }

    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
        if ((ins == null) || (isEmpty(algorithm))) {
            return null;
        }
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        byte[] encodedKey = StreamUtils.readText(ins).getBytes();
        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    public static boolean rsaCheckContent(String content, String sign, String publicKey, String charset) throws Exception {
        try {
            PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(pubKey);
            if (isEmpty(charset))
                signature.update(content.getBytes());
            else {
                signature.update(content.getBytes(charset));
            }
            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (Exception e) {
            throw new Exception("RSAcontent=" + content + ",sign=" + sign + ",charset=" + charset, e);
        }
    }

    public static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        StringWriter writer = new StringWriter();
        StreamUtils.io(new InputStreamReader(ins), writer);
        byte[] encodedKey = writer.toString().getBytes();
        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    private static boolean isEmpty(String value) {
        int strLen;
        if ((value == null) || ((strLen = value.length()) == 0))
            return true;
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
