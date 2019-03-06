package com.yzd.jutils.cookieExt;


import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil2 {

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, String domain) {
        if (value != null) {
            value = EncryptDESUtil.toEncrypt(value);
        }
        Cookie cookies = new Cookie(name, value);
        cookies.setPath("/");
        if (!StringUtils.isBlank(domain)) {
            cookies.setDomain(domain);
        }
        //cookies.setMaxAge(-1);//设置cookie经过多长秒后被删除。如果0，就说明立即删除。如果是负数就表明当浏览器关闭时自动删除。
        cookies.setMaxAge(maxAge);
        response.addCookie(cookies);
    }
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        if (cookieName != null) {
            Cookie cookie = getCookie(request,cookieName);
            if (cookie != null) {
                String cookieValue = cookie.getValue();
                return EncryptDESUtil.toDecrypt(cookieValue);
            }
        }
        return "";
    }

    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        try {
            if (cookies != null && cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    cookie = cookies[i];
                    if (cookie.getName().equals(cookieName)) {
                        return cookie;
                    }
                }
                //注意：如果这里不做设置的话可能返回的cookie不是你想要的值
                cookie = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookie;
    }
    public boolean deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        if (cookieName != null) {
            Cookie cookie = getCookie(request,cookieName);
            if (cookie != null) {
                cookie.setMaxAge(0);//如果0，就说明立即删除
                cookie.setPath("/");//不要漏掉
                response.addCookie(cookie);
                return true;
            }
        }
        return false;
    }
    public boolean deleteAllCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        try {
            if (cookies != null && cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    deleteCookie(request,response,cookie.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
