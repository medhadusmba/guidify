package com.softmq.guide.app.common.ads.core;

import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;
import com.softmq.guide.app.common.ads.core.banners.NoBannerAds;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;
import com.softmq.guide.app.common.ads.core.interstitials.NoInterstitialAds;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAdSource;
import com.softmq.guide.app.common.ads.core.mediumrects.NoMediumRectAds;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;
import com.softmq.guide.app.common.ads.core.natives.NoNativeAds;

public class DefaultAds implements Ads {
    private final BannerAdSource banners;
    private final InterstitialAdSource interstitials;
    private final NativeAdSource natives;
    private final MediumRectAdSource mediumrects;

    public DefaultAds(BannerAdSource banners, InterstitialAdSource interstitials, NativeAdSource natives, MediumRectAdSource mediumrects) {
        this.banners = banners;
        this.interstitials = interstitials;
        this.natives = natives;
        this.mediumrects = mediumrects;

    }

    public DefaultAds(BannerAdSource banners, InterstitialAdSource interstitials, NativeAdSource natives) {
        this(banners, interstitials, natives, new NoMediumRectAds());
    }

    public DefaultAds(BannerAdSource banners, InterstitialAdSource interstitials) {

        this(banners, interstitials, new NoNativeAds());
    }

    public DefaultAds(BannerAdSource banners) {

        this(banners, new NoInterstitialAds(), new NoNativeAds());
    }

    public DefaultAds(InterstitialAdSource interstitials) {

        this(new NoBannerAds(), new NoInterstitialAds(), new NoNativeAds());
    }

    public DefaultAds(NativeAdSource natives) {

        this(new NoBannerAds(), new NoInterstitialAds(), natives);
    }

    public DefaultAds(DefaultAds ads) {
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
}
