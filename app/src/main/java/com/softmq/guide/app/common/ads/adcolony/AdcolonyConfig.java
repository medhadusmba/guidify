package com.softmq.guide.app.common.ads.adcolony;

public class AdcolonyConfig {

    private final String adcolonyAppId;
    private final String adcolonyBannerId;
    private final String adcolonyInterstitialId;
    private final String adcolonyRewardedId;

    public AdcolonyConfig(String adcolonyAppId, String adcolonyBannerId, String adcolonyInterstitialId, String adcolonyRewardedId) {

        this.adcolonyAppId = adcolonyAppId;
        this.adcolonyBannerId = adcolonyBannerId;
        this.adcolonyInterstitialId = adcolonyInterstitialId;
        this.adcolonyRewardedId = adcolonyRewardedId;
    }

    public AdcolonyConfig() {
        this("", "", "", "");
    }


    public String getAdcolonyAppId() {
        return adcolonyAppId;
    }


    public String adcolonyInterstitialId() {
        return adcolonyInterstitialId;
    }

    public String adcolonyRewardedId() {
        return adcolonyRewardedId;
    }

    public String adcolonyBannerId() {
        return adcolonyBannerId;
    }


}
