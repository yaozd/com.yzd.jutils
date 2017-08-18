package com.yzd.jutils.encrypt;

import com.yzd.jutils.print.PrintUtil;

/**
 * RC4算法--可破解-不推荐使用
 * 长度：密文是明文的2倍
 * 使得子密钥序列在不到100万字节内就发生了完全的重复，如果是部分重复，则可能在不到10万字节内就能发生重复，因此，推荐在使用RC4算法时，必须对加密密钥进行测试，判断其是否为弱密钥。
 * 在2001年就有以色列科学家指出RC4加密算法存在着漏洞，这可能对无线通信网络的安全构成威胁
 * RC4算法
 * https://baike.so.com/doc/5499478-5736914.html
 * Created by zd.yao on 2017/8/18.
 */
public class RC4Util2 {
    public static String decryRC4(byte[] data, String key) {
        if ((data == null) || (key == null)) {
            return null;
        }
        return asString(RC4Base(data, key));
    }

    public static String decryRC4(String data, String key) {
        if ((data == null) || (key == null)) {
            return null;
        }
        return new String(RC4Base(HexString2Bytes(data), key));
    }

    public static byte[] encryRC4Byte(String data, String key) {
        if ((data == null) || (key == null)) {
            return null;
        }
        byte[] bData = data.getBytes();
        return RC4Base(bData, key);
    }

    public static String encryRC4String(String data, String key) {
        if ((data == null) || (key == null)) {
            return null;
        }
        return toHexString(asString(encryRC4Byte(data, key)));
    }

    private static String asString(byte[] buf) {
        StringBuffer strbuf = new StringBuffer(buf.length);
        for (int i = 0; i < buf.length; i++) {
            strbuf.append((char) buf[i]);
        }
        return strbuf.toString();
    }

    private static byte[] initKey(String aKey) {
        byte[] bkey = aKey.getBytes();
        byte[] state = new byte[256];

        for (int i = 0; i < 256; i++) {
            state[i] = ((byte) i);
        }
        int index1 = 0;
        int index2 = 0;
        if ((bkey == null) || (bkey.length == 0)) {
            return null;
        }
        for (int i = 0; i < 256; i++) {
            index2 = (bkey[index1] & 0xFF) + (state[i] & 0xFF) + index2 & 0xFF;
            byte tmp = state[i];
            state[i] = state[index2];
            state[index2] = tmp;
            index1 = (index1 + 1) % bkey.length;
        }
        return state;
    }

    private static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch & 0xFF);
            if (s4.length() == 1) {
                s4 = '0' + s4;
            }
            str = str + s4;
        }
        return str;
    }

    private static byte[] HexString2Bytes(String src) {
        int size = src.length();
        byte[] ret = new byte[size / 2];
        byte[] tmp = src.getBytes();
        for (int i = 0; i < size / 2; i++) {
            ret[i] = uniteBytes(tmp[(i * 2)], tmp[(i * 2 + 1)]);
        }
        return ret;
    }

    private static byte uniteBytes(byte src0, byte src1) {
        char _b0 = (char) Byte.decode("0x" + new String(new byte[]{src0})).byteValue();
        _b0 = (char) (_b0 << '\004');
        char _b1 = (char) Byte.decode("0x" + new String(new byte[]{src1})).byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    private static byte[] RC4Base(byte[] input, String mKkey) {
        int x = 0;
        int y = 0;
        byte[] key = initKey(mKkey);

        byte[] result = new byte[input.length];

        for (int i = 0; i < input.length; i++) {
            x = x + 1 & 0xFF;
            y = (key[x] & 0xFF) + y & 0xFF;
            byte tmp = key[x];
            key[x] = key[y];
            key[y] = tmp;
            int xorIndex = (key[x] & 0xFF) + (key[y] & 0xFF) & 0xFF;
            result[i] = ((byte) (input[i] ^ key[xorIndex]));
        }
        return result;
    }

    public static void main(String[] args) {
        String plainTxt="156><?(*75455659156><?(*754555659156><?(*75455659";
        String key="71147114b4c61a6b4c61a6";
        String encryptStr=RC4Util2.encryRC4String(plainTxt, key);
        String decryptStr=RC4Util2.decryRC4(encryptStr,key);
        PrintUtil.outLn(encryptStr);
        PrintUtil.outLn(decryptStr);
    }
}
