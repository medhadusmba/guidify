package com.softmq.guide.app.common.ads.core;

import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAdSource;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAdSource;

import java9.util.concurrent.CompletableFuture;

public interface Ads {
    BannerAdSource banners();

    InterstitialAdSource interstitials();

    NativeAdSource natives();

    MediumRectAdSource mediumrects();
    RewardedAdSource rewardedAds();

    default Boolean isInitialized() {
        return false;
    }

    default CompletableFuture<Void> initialize() {
        return CompletableFuture.completedFuture(null);
    }
}
