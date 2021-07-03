package com.softmq.guide.app.config;

import android.content.Context;

import com.softmq.guide.app.ActivitiesConfig;
import com.softmq.guide.app.Config;
import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.exit.ExitConfig;
import com.softmq.guide.app.rate.RatingConfig;
import com.softmq.guide.app.update.UpdateConfig;

import java.util.Objects;

import java9.util.concurrent.CompletableFuture;

public class SmartConfig implements AppConfig {
    private static AppConfig config;
    private final Context context;


    public SmartConfig(Context context) {
        this.context = context;
        if (Objects.isNull(config)) {
            config = new DefaultConfig();
        }
    }


    public CompletableFuture<Void> read() {
        if (Config.offline) {
            config = new OfflineConfig(context);
        } else if (Config.online) {
            config = new OnlineConfig(context);
        }
        return config.read();


    }

    @Override
    public PopupConfig popup() {
        return config.popup();
    }

    public AdsConfig ads() {
        return config.ads();
    }

    public ActivitiesConfig activities() {
        return config.activities();
    }

    public RatingConfig rating() {
        return config.rating();
    }

    public ExitConfig exit() {
        return config.exit();
    }

    public UpdateConfig update() {
        return config.update();
    }

}
