package com.yzd.jutils.shardingExt.idGeneratorExt;

/**
 * Created by zd.yao on 2017/11/13.
 */
public abstract class AbstractClock {
    public AbstractClock() {
    }

    public static AbstractClock systemClock() {
        return new AbstractClock.SystemClock();
    }

    public abstract long millis();

    private static final class SystemClock extends AbstractClock {
        private SystemClock() {
        }

        public long millis() {
            return System.currentTimeMillis();
        }
    }
}
