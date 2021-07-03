package com.softmq.guide.app.common.ads.huawei;

public class HuaweiConfig {
    private final String bannerAdId;
    private final String interstitialAdId;
    private final String nativeAdId;
    private final String rewardedAdId;

    public HuaweiConfig(String bannerAdId, String interstitialAdId, String nativeAdId, String rewardedAdId) {
        this.bannerAdId = bannerAdId;
        this.interstitialAdId = interstitialAdId;
        this.nativeAdId = nativeAdId;
        this.rewardedAdId = rewardedAdId;
    }
    public HuaweiConfig() {
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
