package com.softmq.guide.app.config;

import android.content.Context;

import com.softmq.guide.app.ActivitiesConfig;
import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.common.data.json.OfflineJson;
import com.softmq.guide.app.exit.ExitConfig;
import com.softmq.guide.app.rate.RatingConfig;
import com.softmq.guide.app.update.UpdateConfig;

import org.json.JSONException;

import java.io.IOException;

import java9.util.concurrent.CompletableFuture;

public class OfflineConfig implements AppConfig {
    Context context;
    private AppConfig config;


    public OfflineConfig(Context context) {
        this.config = new DefaultConfig();
        this.context = context;
    }

    public CompletableFuture<Void> read() {
        try {
            config = new JsonConfig(new OfflineJson(context, "offline/config.json", "config").asJson());
            return config.read();
        } catch (IOException | JSONException e) {
            return CompletableFuture.failedFuture(e);
        }
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

