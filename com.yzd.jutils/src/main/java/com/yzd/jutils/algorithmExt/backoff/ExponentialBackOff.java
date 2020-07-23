package com.yzd.jutils.algorithmExt.backoff;

import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j;

import javax.net.ssl.SSLHandshakeException;
import java.net.SocketTimeoutException;
import java.util.List;

/**
 * @Author: yaozh
 * @Description:
 */
@Log4j
public final class ExponentialBackOff {
    private static final int[] FIBONACCI = new int[]{1, 1, 2, 3, 5, 8, 13};
    private static final List<Class<? extends Exception>> EXPECTED_COMMUNICATION_ERRORS = Lists.newArrayList(
            SSLHandshakeException.class, SocketTimeoutException.class);

    private ExponentialBackOff() {

    }

    public static <T> T execute(ExponentialBackOffFunction<T> fn) {
        for (int attempt = 0; attempt < FIBONACCI.length; attempt++) {
            try {
                return fn.execute();
            } catch (Exception e) {
                handleFailure(attempt, e);
            }
        }
        throw new RuntimeException("Failed to communicate.");
    }

    private static void handleFailure(int attempt, Exception e) {
        if (e.getCause() != null && !EXPECTED_COMMUNICATION_ERRORS.contains(e.getCause().getClass())) {
            throw new RuntimeException(e);
        }
        doWait(attempt);
    }

    private static void doWait(int attempt) {
        try {
            Thread.sleep(FIBONACCI[attempt] * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
