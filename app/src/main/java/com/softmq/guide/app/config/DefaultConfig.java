package com.softmq.guide.app.config;

import com.softmq.guide.app.ActivitiesConfig;
import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.exit.ExitConfig;
import com.softmq.guide.app.rate.RatingConfig;
import com.softmq.guide.app.update.UpdateConfig;

import java9.util.concurrent.CompletableFuture;

public class DefaultConfig implements AppConfig {
    private PopupConfig advertising = new PopupConfig();
    private AdsConfig ads = new AdsConfig();
    private RatingConfig rating = new RatingConfig();
    private ActivitiesConfig activities = new ActivitiesConfig();
    private ExitConfig exit = new ExitConfig();
    private UpdateConfig update = new UpdateConfig();

    public DefaultConfig() {

    }

    public DefaultConfig(AdsConfig ads, RatingConfig rating, PopupConfig popupConfig, ActivitiesConfig activities, ExitConfig exit, UpdateConfig update) {
        this.ads = ads;
        this.rating = rating;
        this.activities = activities;
        this.exit = exit;
        this.update = update;
        this.advertising = popupConfig;
    }

    @Override
    public AdsConfig ads() {
        return ads;
    }

    @Override
    public ActivitiesConfig activities() {
        return activities;
    }

    @Override
    public RatingConfig rating() {
        return rating;
    }

    @Override
    public ExitConfig exit() {
        return exit;
    }

    @Override
    public UpdateConfig update() {
        return update;
    }

    @Override
    public CompletableFuture<Void> read() {
        return CompletableFuture.completedFuture(null);
    }

    public PopupConfig popup() {
        return advertising;
    }
}
