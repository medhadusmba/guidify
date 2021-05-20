package com.softmq.guide.app.config;

import android.content.Context;

import com.softmq.guide.app.ActivitiesConfig;
import com.softmq.guide.app.Config;
import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.common.data.json.OnlineJson;
import com.softmq.guide.app.exit.ExitConfig;
import com.softmq.guide.app.rate.RatingConfig;
import com.softmq.guide.app.update.UpdateConfig;

import java9.util.concurrent.CompletableFuture;

public class OnlineConfig implements AppConfig {
    private final Context context;
    private AppConfig config;


    public OnlineConfig(Context context) {
        this.context = context;
        this.config = new DefaultConfig();
    }

    public CompletableFuture<Void> read() {
        return new OnlineJson(context, Config.url, "config").read().thenCompose((json) -> {
            config = new JsonConfig(json);
            try {
                return config.read();
            } catch (Throwable e) {
                e.printStackTrace();
                return CompletableFuture.failedFuture(e);
            }


        });
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
