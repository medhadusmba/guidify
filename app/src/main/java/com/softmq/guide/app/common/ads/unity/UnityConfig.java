package com.softmq.guide.app.common.ads.unity;

public class UnityConfig {


    private final String unityGameID;
    private final String bannerUnitId;
    private final String interUnitId;
    private final String rewardedVideoUnitId;

    public UnityConfig(String UnityGameID, String bannerUnitId, String interUnitId, String rewardedVideoUnitId) {


        unityGameID = UnityGameID;
        this.bannerUnitId = bannerUnitId;
        this.interUnitId = interUnitId;
        this.rewardedVideoUnitId = rewardedVideoUnitId;
    }

    public UnityConfig() {
        this("", "", "", "");
    }

    public String getUnityAdBannerUnitId() {
        return bannerUnitId;
    }


    public String getUnityGameId() {
        return unityGameID;
    }

    public String getUnityAdInterstitialUnitId() {
        return interUnitId;
    }


    public String getUnityAdRewardedVideoUnitId() {
        return rewardedVideoUnitId;
    }


}
