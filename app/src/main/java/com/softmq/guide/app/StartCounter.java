package com.softmq.guide.app;

import com.softmq.guide.app.common.Counter;

public class StartCounter {
    private static final Counter counter = new Counter();

    public static Counter get() {
        return counter;
    }

}
