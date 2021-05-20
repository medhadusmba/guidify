package com.softmq.guide.app.common.time;

public class ElapsedTime {
    private long begin;
    private long end;

    public ElapsedTime() {

    }

    public void start() {
        begin = System.currentTimeMillis();

    }

    public void finish() {
        end = System.currentTimeMillis();
    }

    public long value() {
        return end - begin;
    }
}