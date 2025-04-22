package som.make.mock.server.extend;

import java.util.concurrent.atomic.AtomicLong;

public class SnowflakeIdGenerator {

    private final long startTimeStamp = 1609459200000L; // 起始时间戳
    private final long workerIdBits = 5L;
    private final long datacenterIdBits = 5L;
    private final long sequenceBits = 12L;

    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private final long workerId;
    private final long datacenterId;
    private final AtomicLong sequence = new AtomicLong(0L);
    private volatile long lastTimestamp = -1L;

    public SnowflakeIdGenerator(long workerId, long datacenterId) {
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public long nextId() {
        long currentTimestamp = System.currentTimeMillis();
        long currentSequence;

        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id for " + (lastTimestamp - currentTimestamp) + " " +
                    "milliseconds");
        }

        if (currentTimestamp == lastTimestamp) {
            currentSequence = sequence.incrementAndGet() & sequenceMask;
            if (currentSequence == 0) {
                currentTimestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence.set(0L);
            currentSequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        return ((currentTimestamp - startTimeStamp) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                currentSequence;
    }

    private long waitNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}