package com.yzd.jutils.insertBigData;

import java.util.Random;

public class RandomValue {
    static Random rand = new Random();
    public static String name_sex = "M";

    public static String getChineseName() {
        return "getChineseName" + rand.nextLong();
    }

    public static int getNum(int i, int i1) {
        return 1;
    }

    public static String getEmail(int i, int i1) {
        return "getEmail" + rand.nextLong();
    }

    public static String getTel() {
        return "getTel" + rand.nextLong();
    }

    public static String getRoad() {
        return "getRoad" + rand.nextLong();
    }
}
