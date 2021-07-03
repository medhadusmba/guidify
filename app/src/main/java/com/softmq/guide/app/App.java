package com.softmq.guide.app;

import android.app.Activity;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.github.nitrico.lastadapter.ItemType;
import com.onesignal.OneSignal;
import com.softmq.guide.app.ads.AppAds;
import com.softmq.guide.app.common.core.Listener;
import com.softmq.guide.app.common.ui.navigation.Navigator;
import com.softmq.guide.app.config.SmartConfig;
import com.softmq.guide.app.exit.AppExiting;
import com.softmq.guide.app.items.ItemListener;
import com.softmq.guide.app.items.SmartAppItems;
import com.softmq.guide.app.rate.AppRating;
import com.softmq.guide.app.share.AppSharing;
import com.softmq.guide.app.update.AppUpdating;

import java.util.function.BiFunction;

import java9.util.concurrent.CompletableFuture;

public class App {
    private final Navigator navigator;
    private final SmartConfig config;
    private final AppRating rating;
    private final AppAds ads;
    private final AppActivities activities;
    private final AppSharing sharing;
    private final AppExiting exiting;
    private final AppUpdating updating;
    private Activity activity;


    public App(Activity activity) {
        this.config = new SmartConfig(activity);
        this.ads = new AppAds(activity, config);
        this.rating = new AppRating(activity, config);
        this.navigator = new Navigator(activity);
        this.activities = new AppActivities(activity, config);
        this.sharing = new AppSharing(activity);
        this.exiting = new AppExiting(this, activity, config);
        this.updating = new AppUpdating(activity, config);

        this.activity = activity;
    }

    public CompletableFuture<Void> initialize() {

        if(Config.ONESIGNAL_ENABLED){
            // Enable verbose OneSignal logging to debug issues if needed.
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

            // OneSignal Initialization
            OneSignal.initWithContext(activity);
            OneSignal.setAppId(Config.ONESIGNAL_APP_ID);
        }
        return config.read().thenCompose(aVoid -> ads.initialize());
    }

    private void initConfig() {
        config.read();
    }


    private void initAds(Listener<App> listener) {
        ads.initialize().thenRun(() -> listener.accept(this)).exceptionally(throwable -> {
            listener.accept(this);
            return null;
        });
    }


    public SmartConfig config() {
        return config;
    }

    public AppAds ads() {
        return ads;
    }

    public Navigator navigator() {
        return navigator;
    }

    public SmartAppItems items(View view, ItemListener clickListener, int itemLayout) {
        return new SmartAppItems(view, clickListener, itemLayout);
    }

    public SmartAppItems items(View view, ItemListener clickListener, BiFunction<Object, Integer, ItemType<? extends ViewDataBinding>> itemLayout) {
        return new SmartAppItems(view, clickListener, itemLayout);
    }

    public AppActivities activities() {
        return activities;
    }

    public AppRating rating() {
        return rating;
    }

    public AppExiting exiting() {
        return exiting;
    }

    public AppSharing sharing() {
        return sharing;
    }

    public AppUpdating updating() {
        return updating;
    }
}
