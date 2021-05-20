package com.softmq.guide.app.splashscreen;

public class SplashActivityConfig {
    private final long duration;

    public SplashActivityConfig(long duration) {
        this.duration = duration;
    }

    public SplashActivityConfig() {
        this.duration = 1000;
    }

    public long duration() {
        return duration;

    }
}
