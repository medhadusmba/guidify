package com.softmq.guide.app.common.ads.core.banners;

public class NoBannerAds implements BannerAdSource {

    @Override
    public BannerAd bannerAd() {
        return new NoBannerAd();
    }
}
