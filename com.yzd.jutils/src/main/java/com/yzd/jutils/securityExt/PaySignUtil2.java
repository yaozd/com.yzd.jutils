package com.yzd.jutils.securityExt;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Created by zd.yao on 2017/8/11.
 */
public class PaySignUtil2 {
    private static String original_password="hb";
    public static String hashpw(String txt){
        String password = BCrypt.hashpw(original_password+txt, BCrypt.gensalt());
        return password;
    }
    public static boolean checkpw(String txt,String password){
        boolean rs = BCrypt.checkpw(txt, password);
        return rs;
    }
}
