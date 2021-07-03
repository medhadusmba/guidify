package com.softmq.guide.app.common.ads.mopub;

import android.app.Activity;

import com.huawei.hms.ads.HwAds;
import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.common.ads.core.BaseAds;
import com.softmq.guide.app.common.ads.core.mediumrects.NoMediumRectAds;
import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.huxter.core.Huxter;
import com.softmq.huxter.mopub.MopubAdapter;

import java9.util.concurrent.CompletableFuture;

public class MopubNetwork extends BaseAds {
    private final Activity activity;
    @org.jetbrains.annotations.NotNull
    private static Boolean initialized = false;

    public MopubNetwork(Activity activity, AdsConfig config) {
        super(new MopubBannerAds(activity, config.mopub().getBannerAdId()),
                new MopubInterstitialAds(activity, config.mopub().getInterstitialAdId()),
                new MopubNativeAds(activity, config.mopub().getNativeAdId()), new NoMediumRectAds(),
                new MopubRewardedAds(activity, config.mopub().getRewardedAdId()));
        this.activity = activity;
    }

    @Override
    public CompletableFuture<Void> initialize() {
        CompletableFuture<Void> result = new CompletableFuture<>();
        Huxter.registerAdapter(new MopubAdapter());
        Huxter.getInstance().initialize(new Huxter.Config.Builder(activity, "").build(), new Huxter.InitializationListener() {
            @Override
            public void onComplete() {
                result.complete(null);
            }

            @Override
            public void onFail(Huxter.InitializationError error) {
                result.completeExceptionally(new Exception("mopub init error"));

            }
        });
        initialized = true;
        return result;
    }

    @Override
    public Boolean isInitialized() {
        return initialized;
    }
}
