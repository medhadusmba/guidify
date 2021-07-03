package com.softmq.guide.app.common.ads.core;

import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;
import com.softmq.guide.app.common.ads.core.banners.NoBannerAds;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;
import com.softmq.guide.app.common.ads.core.interstitials.NoInterstitialAds;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAdSource;
import com.softmq.guide.app.common.ads.core.mediumrects.NoMediumRectAds;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;
import com.softmq.guide.app.common.ads.core.natives.NoNativeAds;
import com.softmq.guide.app.common.ads.core.rewarded.NoRewardedAds;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAdSource;

public class BaseAds implements Ads {
    private final BannerAdSource banners;
    private final InterstitialAdSource interstitials;
    private final NativeAdSource natives;
    private final MediumRectAdSource mediumrects;
    private RewardedAdSource rewardedAds;

    public BaseAds(BannerAdSource banners, InterstitialAdSource interstitials, NativeAdSource natives, MediumRectAdSource mediumrects, RewardedAdSource rewardedAds) {
        this.banners = banners;
        this.interstitials = interstitials;
        this.natives = natives;
        this.mediumrects = mediumrects;
        this.rewardedAds = rewardedAds;

    }
    public BaseAds(BannerAdSource banners, InterstitialAdSource interstitials, NativeAdSource natives, MediumRectAdSource mediumrects) {
        this(banners, interstitials, natives, new NoMediumRectAds(), new NoRewardedAds());

    }

    public BaseAds(BannerAdSource banners, InterstitialAdSource interstitials, NativeAdSource natives) {
        this(banners, interstitials, natives, new NoMediumRectAds());
    }

    public BaseAds(BannerAdSource banners, InterstitialAdSource interstitials) {

        this(banners, interstitials, new NoNativeAds());
    }

    public BaseAds(BannerAdSource banners) {

        this(banners, new NoInterstitialAds(), new NoNativeAds());
    }

    public BaseAds(InterstitialAdSource interstitials) {

        this(new NoBannerAds(), new NoInterstitialAds(), new NoNativeAds());
    }

    public BaseAds(NativeAdSource natives) {

        this(new NoBannerAds(), new NoInterstitialAds(), natives);
    }

    public BaseAds(BaseAds ads) {
        this(ads.banners, ads.interstitials(), ads.natives());
    }

    public BannerAdSource banners() {
        return banners;
    }

    public InterstitialAdSource interstitials() {
        return interstitials;
    }

    public NativeAdSource natives() {
        return natives;
    }

    @Override
    public MediumRectAdSource mediumrects() {
        return mediumrects;
    }

    @Override
    public RewardedAdSource rewardedAds() {
        return rewardedAds;
    }
}
