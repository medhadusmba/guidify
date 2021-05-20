package com.softmq.guide.app.config;

import com.softmq.guide.app.ActivitiesConfig;
import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.exit.ExitConfig;
import com.softmq.guide.app.rate.RatingConfig;
import com.softmq.guide.app.update.UpdateConfig;

import java9.util.concurrent.CompletableFuture;

public interface AppConfig {
    AdsConfig ads();

    ActivitiesConfig activities();

    RatingConfig rating();

    ExitConfig exit();

    UpdateConfig update();

    PopupConfig popup();

    CompletableFuture<Void> read();
}

