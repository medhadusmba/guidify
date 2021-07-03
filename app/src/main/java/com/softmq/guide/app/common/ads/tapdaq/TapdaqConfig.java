package com.softmq.guide.app.common.ads.tapdaq;

public class TapdaqConfig {
    private String clientKey;
    private final String bannerAdId;
    private final String interstitialAdId;
    private final String nativeAdId;
    private final String rewardedAdId;
    private String appId;

    public TapdaqConfig(String appId, String clientKey, String bannerAdId, String interstitialAdId, String nativeAdId, String rewardedAdId) {
        this.clientKey = clientKey;
        this.bannerAdId = bannerAdId;
        this.interstitialAdId = interstitialAdId;
        this.nativeAdId = nativeAdId;
        this.rewardedAdId = rewardedAdId;
        this.appId = appId;
    }
    public TapdaqConfig() {
        this("","","","", "","");
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

    public String getAppId() {
        return appId;
    }

    public String getClientKey() {
        return clientKey;
    }
}
