package com.yzd.jutils.uuidExt;

import java.util.UUID;

/**
 * HyperspaceIdGenerator
 * 通过UUID+ThreadLocal 加速唯一ID生成的速度
 * @author ethan
 */
public class UuIdGenerator {

    private static final char DELIMITER = '-';

    private static final int ID_PREFIX_LENGTH = 33;

    private static final int ID_LENGTH = 38;

    private static final int RANDOM_REFRESH_PERIOD = 1000000;

    private char[] value = new char[ID_LENGTH];

    private int seq;

    private static final ThreadLocal<UuIdGenerator> ID_GENERATOR_THREAD_LOCAL =
            ThreadLocal.withInitial(UuIdGenerator::new);

    private UuIdGenerator() {
        String randomStr = UUID.randomUUID().toString()
                .replaceAll(String.valueOf(DELIMITER), "");
        for (int i = 0; i < ID_PREFIX_LENGTH - 1; i++) {
            this.value[i] = randomStr.charAt(i);
        }
        this.value[randomStr.length()] = DELIMITER;
    }

    /**
     * Generate global unique id, max length 38 bytes
     * @return
     */
    public static String generate() {
        UuIdGenerator idGenerator = ID_GENERATOR_THREAD_LOCAL.get();
        //转为16进制表示
        String seqStr = Integer.toHexString(idGenerator.seq);
        seqStr.getChars(0, seqStr.length(), idGenerator.value, ID_PREFIX_LENGTH);
        idGenerator.next();
        return new String(idGenerator.value, 0, ID_PREFIX_LENGTH + seqStr.length());
    }

    private void next() {
        seq++;
        if (seq == RANDOM_REFRESH_PERIOD) {
            ID_GENERATOR_THREAD_LOCAL.set(new UuIdGenerator());
        }
    }

}
