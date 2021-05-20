package com.softmq.guide.app.common.time;

public class RemainingTime {
    private final long current;
    private final long duration;

    public RemainingTime(long current, long duration) {

        this.current = current;
        this.duration = duration;
    }

    public long value() {
        long result = duration - current;
        if (result < 0) {
            result = 0;
        }
        return result;
    }
}
