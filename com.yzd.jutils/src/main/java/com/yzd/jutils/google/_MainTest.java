package com.yzd.jutils.google;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author: yaozhendong
 * @create: 2019-10-10 15:33
 **/

public class _MainTest {
    /**
     * 测试已通过-byArvin(2019-10-10-1552)
     * 使用谷歌身份验证器（Google Authenticator）保护你的后台
     * https://blog.csdn.net/m0_37202351/article/details/88841969
     * https://github.com/wstrange/GoogleAuth
     * @param args
     */
    public static void main(String[] args) {
        // 用户注册时使用
        // 获取一个新的密钥，默认16位，该密钥与用户绑定
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        String secret = key.getKey();
        System.out.println(secret);
        //生成 Google Authenticator 二维码
        String qrcode =createGoogleAuthQRCodeData(secret,"yzd","nihao");
        System.out.println(qrcode);
        // 用户登录时使用
        // 根据用户密钥和用户输入的密码，验证是否一致。（近3个密码都有效：前一个，当前，下一个）
        boolean isCodeValid = gAuth.authorize("TAP7SQKJRXO4NWEW", 256263);
        System.out.println(isCodeValid);

        // 根据密钥，获取最新密码（后台用不到，用来开发 谷歌身份验证器 客户端）
        int code = gAuth.getTotpPassword("TAP7SQKJRXO4NWEW");
        System.out.println(code);
    }
    /**
     * 生成 Google Authenticator 二维码所需信息
     * Google Authenticator 约定的二维码信息格式 : otpauth://totp/{issuer}:{account}?secret={secret}&issuer={issuer}
     * 参数需要 url 编码 + 号需要替换成 %20
     * @param secret  密钥 使用 createSecretKey 方法生成
     * @param account 用户账户 如: example@domain.com 138XXXXXXXX
     * @param issuer  服务名称 如: Google Github 印象笔记
     */
    public static String createGoogleAuthQRCodeData(String secret, String account, String issuer) {
        String qrCodeData = "otpauth://totp/%s?secret=%s&issuer=%s";
        try {
            return String.format(qrCodeData, URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20"), URLEncoder.encode(secret, "UTF-8")
                    .replace("+", "%20"), URLEncoder.encode(issuer, "UTF-8").replace("+", "%20"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
