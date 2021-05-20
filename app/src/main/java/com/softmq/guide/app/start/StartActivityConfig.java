package com.softmq.guide.app.start;

import com.softmq.guide.app.StartCounter;
import com.softmq.guide.app.common.Counter;

public class StartActivityConfig {
    private final boolean enabled;
    private final LoaderConfig loaderConfig;

    public StartActivityConfig(boolean enabled, LoaderConfig loaderConfig) {

        this.enabled = enabled;
        this.loaderConfig = loaderConfig;
    }

    public StartActivityConfig() {
        this(false, new LoaderConfig());
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Counter counter() {
        return StartCounter.get();
    }

    public LoaderConfig loader() {
        return loaderConfig;
    }

    public static class LoaderConfig {
        private final boolean enabled;
        private final int duration;

        public LoaderConfig(boolean enabled, int duration) {
            this.enabled = enabled;
            this.duration = duration;
        }

        public LoaderConfig() {
            this(false, 0);
        }

        public boolean isEnabled() {
            return enabled;
        }

        public int duration() {
            return duration;
        }
    }
}
