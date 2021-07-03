package com.softmq.guide.app.common.ads.core;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAdSource;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;
import com.softmq.guide.app.common.ads.core.networks.AdNetwork;
import com.softmq.guide.app.common.ads.core.networks.AdNetworksInConfig;
import com.softmq.guide.app.common.ads.core.networks.NoAdNetwork;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAdSource;

import java9.util.concurrent.CompletableFuture;

public class AdsInConfig implements Ads {
    private final AdNetwork mixed;

    public AdsInConfig(Activity activity, AdsConfig config) {
        if (config.isEnabled()) {
            this.mixed = new AdNetworksInConfig(activity, config);
        } else {
            this.mixed = new NoAdNetwork();
        }
    }

    @NonNull
    public CompletableFuture<Void> initialize() {
        return mixed.initialize();
    }

    @NonNull
    public Boolean isInitialized() {
        return mixed.isInitialized();
    }

    @Override
    public BannerAdSource banners() {
        return mixed.ads().banners();
    }

    @Override
    public InterstitialAdSource interstitials() {
        return mixed.ads().interstitials();
    }

    @Override
    public NativeAdSource natives() {
        return mixed.ads().natives();
    }

    @Override
    public MediumRectAdSource mediumrects() {
        return mixed.ads().mediumrects();
    }

    @Override
    public RewardedAdSource rewardedAds() {
        return mixed.ads().rewardedAds();
    }
}
