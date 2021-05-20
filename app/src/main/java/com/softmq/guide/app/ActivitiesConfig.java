package com.softmq.guide.app;

import com.softmq.guide.app.items.ItemActivityConfig;
import com.softmq.guide.app.items.ListActivityConfig;
import com.softmq.guide.app.main.MainActivityConfig;
import com.softmq.guide.app.splashscreen.SplashActivityConfig;
import com.softmq.guide.app.start.StartActivityConfig;

public class ActivitiesConfig {
    private final SplashActivityConfig splash;
    private final StartActivityConfig start;
    private final ListActivityConfig list;
    private final ItemActivityConfig item;
    private final MainActivityConfig main;

    public ActivitiesConfig(SplashActivityConfig splash, StartActivityConfig start, MainActivityConfig main, ListActivityConfig list, ItemActivityConfig item) {
        this.start = start;
        this.splash = splash;
        this.main = main;
        this.list = list;
        this.item = item;
    }

    public ActivitiesConfig() {
        this(new SplashActivityConfig(), new StartActivityConfig(), new MainActivityConfig(), new ListActivityConfig(), new ItemActivityConfig());
    }

    public SplashActivityConfig splash() {
        return splash;
    }

    public StartActivityConfig start() {
        return start;
    }

    public ListActivityConfig list() {
        return list;
    }

    public ItemActivityConfig item() {
        return item;
    }

    public MainActivityConfig main() {
        return main;
    }
}
