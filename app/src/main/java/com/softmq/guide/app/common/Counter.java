package com.softmq.guide.app.common;

public class Counter {
    public static final int DEFAULT_START = 0;
    private final int start;
    private final int max;
    private int current;
    private boolean isReset;

    public Counter(int max) {
        this(DEFAULT_START, max);
    }

    public Counter(int start, int max) {
        this.start = start;
        this.current = start;
        this.max = max;
        isReset = false;
    }

    public Counter() {
        this(Integer.MAX_VALUE);
    }

    public void increment() {


        current++;

    }

    public boolean isReset() {
        return isReset;
    }

    public boolean isReached(int other) {
        return this.current == other;
    }

    public void reset() {
        this.current = start;
        isReset = true;
    }

    public int value() {
        return current;
    }
}
