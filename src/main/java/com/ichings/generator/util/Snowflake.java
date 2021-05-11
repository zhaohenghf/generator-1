package com.ichings.generator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Random;

/**
 * 雪花算法
 * 共64位
 * 0       - 0000000000 0000000000 0000000000 0000000000 0 - 0000000000 - 000000000000
 * 正号|1位 - 毫秒|41位                                     - 机器id|10位 - 序列|12位
 *
 * @author 宋欢
 */
public final class Snowflake {

    private static final Logger LOGGER = LoggerFactory.getLogger(Snowflake.class);

    /**
     * java.util.Random
     */
    private static final Random RANDOM = new Random();

    /**
     * 新时间戳时，开始序列用随机数，设置最大随机数，随机数不包含此数
     * 2的幂次方
     */
    private static final int MAX_RAND_SEQUENCE = 64;

    /**
     * 起始年
     */
    public static final int YEAR = 2020;

    /**
     * 起始时间戳，毫秒
     */
    public static final long EPOCH;

    /**
     * 机器id占位
     */
    public static final long WORKER_ID_BITS = 10L;

    /**
     * 最大机器id
     */
    public static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 序列占位
     */
    public static final long SEQUENCE_BITS = 12L;

    /**
     * 时间戳，毫秒，左移22位
     */
    public static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 机器id，左移12位
     */
    public static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 序列掩码
     */
    public static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /**
     * 上次毫秒
     */
    private long lastTimestamp;

    /**
     * 机器id
     */
    private long workerId;

    /**
     * 序列
     */
    private long sequence;

    static {
        EPOCH = newCalendar(YEAR).getTimeInMillis();
    }

    /**
     * 日历，某年的起始时间，如：2020-01-01 00:00:00 000
     */
    public static Calendar newCalendar(int year) {
        Calendar r = Calendar.getInstance();
        r.set(year, Calendar.JANUARY, 1);
        r.set(Calendar.HOUR_OF_DAY, 0);
        r.set(Calendar.MINUTE, 0);
        r.set(Calendar.SECOND, 0);
        r.set(Calendar.MILLISECOND, 0);
        return r;
    }

    /**
     * 初始化，上次毫秒、机器id、开始序列
     */
    public Snowflake(long workerId) {
        Assert.checkArgument((workerId >= 0L && workerId <= MAX_WORKER_ID),
                String.format("workerId can't be less than 0 or greater than %d", MAX_WORKER_ID));

        this.lastTimestamp = -1L;
        this.workerId = workerId;
        this.sequence = 0L;

        LOGGER.info("workerId: {}", this.workerId);
    }

    /**
     * 生成新id
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        Assert.checkArgument(timestamp >= lastTimestamp,
                String.format("clock moved backwards. refusing to generate id for %d milliseconds", lastTimestamp - timestamp));

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1L) & SEQUENCE_MASK;
            if (sequence == 0L) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = RANDOM.nextInt(MAX_RAND_SEQUENCE);
        }

        lastTimestamp = timestamp;

        return ((timestamp - EPOCH) << TIMESTAMP_LEFT_SHIFT) |
                (workerId << WORKER_ID_SHIFT) |
                sequence;
    }

    /**
     * 阻塞，等下次时间，单位：毫秒
     */
    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 当前时间，单位：毫秒
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

}
