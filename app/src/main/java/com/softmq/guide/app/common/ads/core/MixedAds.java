package com.softmq.guide.app.common.ads.core;

import androidx.annotation.NonNull;

import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAdSource;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;
import com.softmq.guide.app.common.ads.core.networks.AdNetworks;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAdSource;

import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

import java9.util.concurrent.CompletableFuture;

public class MixedAds implements AdNetworks, Ads {
    private final Ads banners;
    private final Ads interstitials;
    private final Ads natives;
    private Ads mediumrects;
    private Ads rewardedAds;

    public MixedAds(Ads banners, Ads interstitials, Ads natives, Ads mediumrects, Ads rewardedAds) {
        this.banners = banners;
        this.interstitials = interstitials;
        this.natives = natives;
        this.mediumrects = mediumrects;
        this.rewardedAds = rewardedAds;
    }

    @NonNull
    @Override
    public Ads ads() {
        return new MixedAds(banners, interstitials, natives, mediumrects, rewardedAds);
    }

    @NotNull
    @Override
    public CompletableFuture<Void> initialize() {
        return CompletableFuture.allOf(Stream.of(banners, interstitials, natives).distinct().map(Ads::initialize).toArray(CompletableFuture[]::new));
    }

    @NotNull
    @Override
    public Boolean isInitialized() {
        return Stream.of(banners, interstitials, natives).allMatch(Ads::isInitialized);
    }

    @Override
    public BannerAdSource banners() {
        return banners.banners();
    }

    @Override
    public InterstitialAdSource interstitials() {
        return interstitials.interstitials();
    }

    @Override
    public NativeAdSource natives() {
        return natives.natives();
    }

    @Override
    public MediumRectAdSource mediumrects() {
        return mediumrects.mediumrects();
    }

    @Override
    public RewardedAdSource rewardedAds() {
        return rewardedAds.rewardedAds();
    }
}
