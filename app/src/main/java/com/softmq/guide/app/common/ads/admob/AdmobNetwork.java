package com.softmq.guide.app.common.ads.admob;

import android.app.Activity;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.softmq.guide.app.common.ads.admob.banners.AdmobBannerAds;
import com.softmq.guide.app.common.ads.admob.interstitials.RotatedAdmobInterstitialAds;
import com.softmq.guide.app.common.ads.admob.mediumrects.AdmobMediumRectAds;
import com.softmq.guide.app.common.ads.admob.natives.AdmobNativeAds;
import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.common.ads.core.DefaultAds;

import org.jetbrains.annotations.NotNull;

import java9.util.concurrent.CompletableFuture;

public class AdmobNetwork extends DefaultAds {
    private final Activity activity;
    private final AdsConfig config;

    public AdmobNetwork(Activity activity, AdsConfig config) {
        super(new AdmobBannerAds(activity.getApplicationContext(), config.admob()),
                new RotatedAdmobInterstitialAds(activity, config.admob().interstitialAdUnitId()),
                new AdmobNativeAds(activity.getApplicationContext(), config.admob()), new AdmobMediumRectAds(activity, config.admob()));
        this.activity = activity;
        this.config = config;
    }

    @NotNull
    @Override
    public CompletableFuture<Void> initialize() {
        CompletableFuture<Void> result = new CompletableFuture<>();
        MobileAds.initialize(activity.getApplicationContext(), initializationStatus -> {
            if (isReady(initializationStatus)) {
                result.complete(null);
            } else {
                result.completeExceptionally(new Exception("admob: failed to initialize"));
            }
        });
        return result;
    }

    @NotNull
    @Override
    public Boolean isInitialized() {
        return isReady(MobileAds.getInitializationStatus());
    }

    private boolean isReady(InitializationStatus initializationStatus) {
        return initializationStatus
                .getAdapterStatusMap()
                .entrySet()
                .stream()
                .anyMatch(stringAdapterStatusEntry -> stringAdapterStatusEntry
                        .getValue()
                        .getInitializationState()
                        .equals(AdapterStatus.State.NOT_READY));
    }

}
