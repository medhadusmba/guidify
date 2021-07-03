package com.softmq.guide.app.ads;

import android.app.Activity;
import android.view.ViewGroup;

import com.softmq.guide.app.common.ads.core.Ads;
import com.softmq.guide.app.common.ads.core.AdsInConfig;
import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAdSource;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAdSource;
import com.softmq.guide.app.config.SmartConfig;

import java9.util.concurrent.CompletableFuture;

public class AppAds implements Ads {

    private final Activity activity;
    private final SmartConfig config;
    private AdsInConfig origin;

    public AppAds(Activity activity, SmartConfig config) {
        this.activity = activity;
        this.config = config;
        origin = new AdsInConfig(activity, config.ads());
    }

    public CompletableFuture<Void> initialize() {
        origin = new AdsInConfig(activity, config.ads());
        return this.origin.initialize();
    }

    @Override
    public BannerAdSource banners() {
        return origin.banners();
    }

    @Override
    public InterstitialAdSource interstitials() {
        return origin.interstitials();
    }

    @Override
    public NativeAdSource natives() {
        return new ScrollableNativeAds(origin.natives(), activity.getApplicationContext());
    }

    @Override
    public MediumRectAdSource mediumrects() {
        return origin.mediumrects();
    }

    @Override
    public RewardedAdSource rewardedAds() {
        return origin.rewardedAds();
    }

    public void interstitial() {
    }

    public void interstitial(Runnable callback) {

    }

    public void showInterstitial() {

    }

    public void showInterstitial(Runnable callback) {

    }

    public boolean hasInterstitial() {
        return false;
    }

    public void createNative(Runnable runnable) {
    }

    public void createNative(ViewGroup holder, Runnable onAdLoadedListener) {
    }

    public void showNative() {
    }

    public void showNative(Runnable onAdShowedListener) {
    }

    public boolean hasNative() {

        return false;
    }

    public void createBanner(ViewGroup view) {
    }

    public void showBanner() {

    }

    public void close() {

    }

}
