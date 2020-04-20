package com.yzd.jutils.uuidExt;

import java.util.Random;

/**
 * @Author: yaozh
 * @Description:
 */
public class ZipkinIdGenerator {
    private static final Random ID_RANDOM = new Random();

    public static String nextId() {
        long longId = ID_RANDOM.nextLong();
        return toLowerHex(longId);
    }

    public static String toLowerHex(long v) {
        char[] data = new char[16];
        writeHexLong(data, 0, v);
        return new String(data);
    }

    public static void writeHexLong(char[] data, int pos, long v) {
        writeHexByte(data, pos + 0, (byte) ((v >>> 56L) & 0xff));
        writeHexByte(data, pos + 2, (byte) ((v >>> 48L) & 0xff));
        writeHexByte(data, pos + 4, (byte) ((v >>> 40L) & 0xff));
        writeHexByte(data, pos + 6, (byte) ((v >>> 32L) & 0xff));
        writeHexByte(data, pos + 8, (byte) ((v >>> 24L) & 0xff));
        writeHexByte(data, pos + 10, (byte) ((v >>> 16L) & 0xff));
        writeHexByte(data, pos + 12, (byte) ((v >>> 8L) & 0xff));
        writeHexByte(data, pos + 14, (byte) (v & 0xff));
    }

    private static final char[] HEX_DIGITS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static void writeHexByte(char[] data, int pos, byte b) {
        data[pos + 0] = HEX_DIGITS[(b >> 4) & 0xf];
        data[pos + 1] = HEX_DIGITS[b & 0xf];
    }
}
