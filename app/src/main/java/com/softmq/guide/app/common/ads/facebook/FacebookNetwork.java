package com.softmq.guide.app.common.ads.facebook;

import android.app.Activity;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.softmq.guide.BuildConfig;
import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.common.ads.core.DefaultAds;
import com.softmq.guide.app.common.ads.facebook.banners.FacebookBannerAds;
import com.softmq.guide.app.common.ads.facebook.interstitials.FacebookInterstitialAds;
import com.softmq.guide.app.common.ads.facebook.mediumrect.FacebookMediumRectAds;
import com.softmq.guide.app.common.ads.facebook.natives.FacebookNativeAds;

import org.jetbrains.annotations.NotNull;

import java9.util.concurrent.CompletableFuture;

public class FacebookNetwork extends DefaultAds {
    private final Activity activity;

    public FacebookNetwork(Activity activity, AdsConfig config) {
        super(new FacebookBannerAds(activity.getApplicationContext(), config.facebook()),
                new FacebookInterstitialAds(activity, config.facebook()),
                new FacebookNativeAds(activity.getApplicationContext(), config.facebook()),
                new FacebookMediumRectAds(activity.getApplicationContext(), config.facebook()));
        this.activity = activity;
    }


    @NotNull
    @Override
    public CompletableFuture<Void> initialize() {
        CompletableFuture<Void> result = new CompletableFuture<>();
        if (BuildConfig.DEBUG) {
            AdSettings.turnOnSDKDebugger(activity.getApplicationContext());
            AdSettings.setTestMode(true);// for get test ad in your device
        }

//        AdSettings.addTestDevice("HASHED ID");

        AudienceNetworkAds.buildInitSettings(activity.getApplicationContext()).withInitListener(initResult -> {
            if (initResult.isSuccess()) {
                result.complete(null);
            } else {
                result.completeExceptionally(new Exception("facebook failed to initialize with message:" + initResult.getMessage()));
            }
        }).initialize();
        return result;
    }

    @NotNull
    @Override
    public Boolean isInitialized() {
        return AudienceNetworkAds.isInitialized(activity.getApplicationContext());
    }

}
