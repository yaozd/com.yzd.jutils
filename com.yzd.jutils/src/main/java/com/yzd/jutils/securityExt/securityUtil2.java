package com.yzd.jutils.securityExt;

import com.yzd.jutils.encrypt.MD5Util;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Created by zd.yao on 2017/8/11.
 */
public class securityUtil2 {
    public static void main(String[] args) {
        // 用户输入原始密码
        //String pwo = "original_password";
        //String pwo = "original_password"+公司标识（如：ARVIN）//就算对方只到了算法不知道不到公司标识也是很难破解-就是撞库在方式也会很困难
        String pwo = "original_password" + "ARVIN";
        System.out.println("pwd = " + pwo);
        // 客户端md5后提交给后端服务
        String pwd = MD5Util.getMd5(pwo, "utf-8");
        System.out.println("pwd = " + pwd);
        // 服务端按客户端md5后的数据生成密码保存至DB
        String password = BCrypt.hashpw(pwd, BCrypt.gensalt());
        System.out.println("password = " + password);
        // 验证密码是否有效
        boolean rs = BCrypt.checkpw(pwd, password);
        System.out.println("rs = " + rs);
        //
        for (int i = 1; i < 100; i++) {
            System.out.println(MD5Util.getMd5(String.valueOf(i), "utf-8"));
        }
        for (int i = 1; i < 100; i++) {
            System.out.println(i);
        }
        for (int i = 1; i < 100; i++)
            System.out.println(PaySignUtil2.hashpw("111"));
    }
}
