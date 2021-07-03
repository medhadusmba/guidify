package com.softmq.guide.app.items;

public class ItemActivityConfig {
    private final boolean nativeAd;
    private final boolean bannerAd;
    private final boolean interstitialAd;
    private boolean mediumrectAd;

    public ItemActivityConfig(boolean nativeAd, boolean bannerAd, boolean interstitialAd, boolean mediumrectAd) {
        this.nativeAd = nativeAd;
        this.bannerAd = bannerAd;
        this.interstitialAd = interstitialAd;
        this.mediumrectAd = mediumrectAd;
    }

    public ItemActivityConfig() {
        this(false, false, false, false);
    }


    public boolean hasNativeAd() {
        return nativeAd;
    }

    public boolean hasBannerAd() {
        return bannerAd;
    }

    public boolean hasInterstitialAd() {
        return interstitialAd;
    }
    public boolean hasMediumrectAd() {
        return mediumrectAd;
    }
}
