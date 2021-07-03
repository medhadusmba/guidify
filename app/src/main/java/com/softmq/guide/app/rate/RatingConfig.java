package com.softmq.guide.app.rate;

public class RatingConfig {

    private final boolean enable;

    public RatingConfig(boolean enable) {
        this.enable = enable;
    }

    public RatingConfig() {
        this.enable = false;
    }

    public boolean isEnabled() {
        return enable;
    }
}
