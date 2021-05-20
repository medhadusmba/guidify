package com.softmq.guide.app.common.core;

import android.os.Handler;
import android.os.Looper;

public class Delayed {
    private final Handler handler;
    private final long delayMillis;
    private final Runnable runnable;

    public Delayed(long delayMillis, Runnable runnable) {
        this.delayMillis = delayMillis;
        this.runnable = runnable;
        this.handler = new Handler(Looper.getMainLooper());
    }

    public void run() {
        handler.postDelayed(runnable, delayMillis);
    }
}
