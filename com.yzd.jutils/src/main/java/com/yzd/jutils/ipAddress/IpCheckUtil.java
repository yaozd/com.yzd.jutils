package com.yzd.jutils.ipAddress;

import org.apache.commons.lang.StringUtils;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * java 判定1个IP地址是否是合法IP
 * https://blog.csdn.net/weixin_38232749/article/details/91879853
 *
 * @Author: yaozh
 * @Description:
 */
public class IpCheckUtil {
    public static void main(String[] args) {
        List<String> ipList = new ArrayList<>();
        ipList.add("0.0.0.0");
        ipList.add(" 255    .255.255.255 ");
        ipList.add(" 255.255.255.255 ");
        ipList.add("1.3.*.4");
        ipList.add("s.23.32.12");
        ipList.add("!?.d.feewfe.se");
        for (String ip : ipList) {
            System.out.println(isRightIP(ip) + ip);
        }
        SocketAddress inetAddress1 = new InetSocketAddress("www.baidu.com", 111);
        System.out.println(inetAddress1);
        SocketAddress inetAddress2 = new InetSocketAddress(" 255  .255.255.255 ", 111);
        System.out.println(inetAddress2);

    }

    public static boolean isRightIP(String ip) {
        //排除空值
        if (ip == null) {
            return false;
        }
        //
        ip = StringUtils.remove(ip, " ");
        //基于IP地址长度进行排除
        if (ip.length() < 7 || ip.length() > 15) {
            return false;
        }
        //避免Integer.parseInt转换时报错，大家也可以注销如下的if代码调试。
        if (ip.charAt(0) == '.' || ip.charAt(ip.length() - 1) == '.') {
            return false;
        }
        String[] Inip = ip.split("\\.");
        if (Inip.length != 4) {
            return false;
        }
        //判断所有的字符是否均是数字
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < Inip[i].length(); j++) {
                if (Inip[i].charAt(j) < '0' || Inip[i].charAt(j) > '9') {
                    return false;
                }
            }
        }
        //IP范围进行判断（0.0.0.0-255.255.255.255）
        for (int i = 0; i < Inip.length; i++) {
            int temp = Integer.parseInt(Inip[i]);
            if (temp < 0 || temp > 255) {
                return false;
            }
        }
        return true;
    }
}
