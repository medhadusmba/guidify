package com.softmq.guide.app;

import android.app.Activity;

import com.softmq.guide.app.config.AppConfig;

public class AppActivities {
    private final AppConfig config;
    private final Activity current;

    public AppActivities(Activity current, AppConfig config) {
        this.current = current;
        this.config = config;
    }

    public Class<? extends Activity> home() {
        if (config.activities().start().isEnabled()) {
            return StartActivity.class;
        } else {
            return MainActivity.class;
        }
    }

    public boolean isHome() {
        return this.home().equals(current.getClass());
    }
}
