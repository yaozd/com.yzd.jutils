package com.yzd.jutils.log4jExt.t1;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 通过System变量设置本机IP地址
 */
public class SystemExtend {
    private SystemExtend() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获得本机的内网地址（String）
     */
    private static String getLocalIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "unknown-ip";
        }
    }

    /**
     * 设置 "local-ip" system 变量，给log4j2,logback 配置使用：
     */
    public static void initProperty() {
        String localIp = getLocalIp();
        System.out.println("localIp for Log4j2: " + localIp);
        System.setProperty("local-ip", localIp);
    }
}
