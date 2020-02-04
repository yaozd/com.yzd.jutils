package com.yzd.jutils.shardingExt.idGeneratorExt;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zd.yao on 2017/11/13.
 */
public class IdGenerator {
    private static final Logger log = LoggerFactory.getLogger(IdGenerator.class);
    public static final long SJDBC_EPOCH;
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_BITS = 10L;
    private static final long SEQUENCE_MASK = 4095L;
    private static final long WORKER_ID_LEFT_SHIFT_BITS = 12L;
    private static final long TIMESTAMP_LEFT_SHIFT_BITS = 22L;
    private static final long WORKER_ID_MAX_VALUE = 1024L;
    private static AbstractClock clock = AbstractClock.systemClock();
    private static long workerId;
    private long sequence;
    private long lastTime;

    public IdGenerator() {
    }

    static void initWorkerId() {
        String workerId = System.getProperty("sjdbc.self.id.generator.worker.id");
        if (!Strings.isNullOrEmpty(workerId)) {
            setWorkerId(Long.valueOf(workerId));
        } else {
            workerId = System.getenv("SJDBC_SELF_ID_GENERATOR_WORKER_ID");
            if (!Strings.isNullOrEmpty(workerId)) {
                setWorkerId(Long.valueOf(workerId));
            }
        }
    }

    public static void setWorkerId(Long workerId) {
        Preconditions.checkArgument(workerId.longValue() >= 0L && workerId.longValue() < 1024L);
        workerId = workerId.longValue();
    }

    public static long getWorkerIdLength() {
        return 10L;
    }

    public synchronized Number generateId() {
        long time = clock.millis();
        Preconditions.checkState(this.lastTime <= time, "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", new Object[]{Long.valueOf(this.lastTime), Long.valueOf(time)});
        if (this.lastTime == time) {
            if (0L == (++this.sequence & 4095L)) {
                time = this.waitUntilNextTime(time);
            }
        } else {
            this.sequence = 0L;
        }

        this.lastTime = time;
        if (log.isDebugEnabled() || true) {
            log.debug("{}-{}-{}", new Object[]{(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date(this.lastTime)), Long.valueOf(workerId), Long.valueOf(this.sequence)});
            log.debug("{}-{}-{}-{}", time, SJDBC_EPOCH, workerId, sequence);
            log.debug("{}-{}-{}-{}", time - SJDBC_EPOCH, SJDBC_EPOCH << 22, workerId, sequence);
            log.debug("{}-{}-{}-{}", time - SJDBC_EPOCH << 22, SJDBC_EPOCH << 22, workerId, sequence);
        }

        return Long.valueOf(time - SJDBC_EPOCH << 22 | workerId << 12 | this.sequence);
    }

    private long waitUntilNextTime(long lastTime) {
        long time;
        for (time = clock.millis(); time <= lastTime; time = clock.millis()) {
            ;
        }

        return time;
    }

    public long getSequence() {
        return this.sequence;
    }

    public long getLastTime() {
        return this.lastTime;
    }

    public static void setClock(AbstractClock clock) {
        clock = clock;
    }

    public static long getWorkerId() {
        return workerId;
    }

    static {
        //2016-11-1-0:0:0
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 10, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        SJDBC_EPOCH = calendar.getTimeInMillis();
        initWorkerId();
    }
}

