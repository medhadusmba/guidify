package com.softmq.guide.app.items;

public class ItemActivityConfig {
    private final boolean nativeAd;
    private final boolean bannerAd;
    private final boolean interstitialAd;

    public ItemActivityConfig(boolean nativeAd, boolean bannerAd, boolean interstitialAd) {
        this.nativeAd = nativeAd;
        this.bannerAd = bannerAd;
        this.interstitialAd = interstitialAd;
    }

    public ItemActivityConfig() {
        this(false, false, false);
    }


    public boolean hasNativeAd() {
        return nativeAd;
    }

    public boolean hasBannerAd() {
        return bannerAd;
    }

    public boolean hasInterstitial() {
        return interstitialAd;
    }
}
