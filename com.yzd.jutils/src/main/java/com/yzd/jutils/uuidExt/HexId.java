package com.yzd.jutils.uuidExt;

import java.util.Random;

/**
 * @Author: yaozh
 * @Description:
 */
public class HexId {
    private static final Random RANDOM = new Random();

    public String randomString() {
        return Long.toHexString(RANDOM.nextLong());
    }

    public static void main(String[] args) {
        HexId hexId=new HexId();
        System.out.println(hexId.randomString());
    }
}
