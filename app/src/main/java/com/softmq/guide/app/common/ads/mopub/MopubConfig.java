package com.softmq.guide.app.common.ads.mopub;

public class MopubConfig {
    private final String bannerAdId;
    private final String interstitialAdId;
    private final String nativeAdId;
    private final String rewardedAdId;

    public MopubConfig(String bannerAdId, String interstitialAdId, String nativeAdId, String rewardedAdId) {
        this.bannerAdId = bannerAdId;
        this.interstitialAdId = interstitialAdId;
        this.nativeAdId = nativeAdId;
        this.rewardedAdId = rewardedAdId;
    }
    public MopubConfig() {
        this("","","","");
    }
    public String getBannerAdId() {
        return bannerAdId;
    }

    public String getInterstitialAdId() {
        return interstitialAdId;
    }

    public String getNativeAdId() {
        return nativeAdId;
    }

    public String getRewardedAdId() {
        return rewardedAdId;
    }
}
