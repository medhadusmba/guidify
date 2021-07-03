package com.softmq.guide.app.common.ads.huawei;

import android.app.Activity;

import com.huawei.hms.ads.HwAds;
import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.common.ads.core.BaseAds;
import com.softmq.guide.app.common.ads.core.mediumrects.NoMediumRectAds;

import java9.util.concurrent.CompletableFuture;

public class HuaweiNetwork extends BaseAds {
    private final Activity activity;
    @org.jetbrains.annotations.NotNull
    private static Boolean initialized=false;

    public HuaweiNetwork(Activity activity, AdsConfig config) {
        super(new HuaweiBannerAds(activity.getApplicationContext(), config.huawei().getBannerAdId()),
                new HuaweiInterstitialAds(activity, config.huawei().getInterstitialAdId()),
                new HuaweiNativeAds(activity.getApplicationContext(), config.huawei().getNativeAdId()),new NoMediumRectAds(),
                new HuaweiRewardedAds(activity, config.huawei().getRewardedAdId()));
        this.activity = activity;
    }

    @Override
    public CompletableFuture<Void> initialize() {
        HwAds.init(activity.getApplicationContext());
        initialized=true;
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public Boolean isInitialized() {
        return initialized;
    }
}
