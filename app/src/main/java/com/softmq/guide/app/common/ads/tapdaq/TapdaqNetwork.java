package com.softmq.guide.app.common.ads.tapdaq;

import android.app.Activity;

import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.common.ads.core.BaseAds;
import com.softmq.guide.app.common.ads.core.mediumrects.NoMediumRectAds;
import com.softmq.huxter.core.Huxter;
import com.softmq.huxter.tapdaq.TapdaqAdapter;

import java9.util.concurrent.CompletableFuture;

public class TapdaqNetwork extends BaseAds {
    private final Activity activity;
    private AdsConfig config;
    @org.jetbrains.annotations.NotNull
    private static Boolean initialized = false;

    public TapdaqNetwork(Activity activity, AdsConfig config) {
        super(new TapdaqBannerAds(activity, config.tapdaq().getBannerAdId()),
                new TapdaqInterstitialAds(activity, config.tapdaq().getInterstitialAdId()),
                new TapdaqNativeAds(activity, config.tapdaq().getNativeAdId()), new NoMediumRectAds(),
                new TapdaqRewardedAds(activity, config.tapdaq().getRewardedAdId()));
        this.activity = activity;
        this.config = config;
    }

    @Override
    public CompletableFuture<Void> initialize() {
        CompletableFuture<Void> result = new CompletableFuture<>();
        Huxter.registerAdapter(new TapdaqAdapter());
        Huxter.getInstance().initialize(new Huxter.Config.Builder(activity, config.tapdaq().getAppId()).addExtra("clientKey", config.tapdaq().getClientKey()).build(), new Huxter.InitializationListener() {
            @Override
            public void onComplete() {
                result.complete(null);
            }

            @Override
            public void onFail(Huxter.InitializationError error) {
                result.completeExceptionally(new Exception(error.toString()));

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
