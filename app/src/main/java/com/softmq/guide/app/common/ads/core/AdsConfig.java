package com.softmq.guide.app.common.ads.core;


import com.softmq.guide.app.common.ads.adcolony.AdcolonyConfig;
import com.softmq.guide.app.common.ads.admob.AdmobConfig;
import com.softmq.guide.app.common.ads.facebook.FacebookConfig;
import com.softmq.guide.app.common.ads.unity.UnityConfig;

import java.util.List;

public class AdsConfig {
    private final FacebookConfig facebook;
    private final UnityConfig unity;
    private final AdcolonyConfig adcolony;
    private final AdmobConfig admob;
    private boolean enabled = false;
    private String bannerAdsMode = "";
    private String interstitialAdsMode = "";
    private String rewardedAdsMode = "";
    private String nativeAdsMode = "";
    private String mediumrectMode = "";

    public AdsConfig(boolean enabled, String admobUnitId, String admobBannerUnitId, List<String> admobInterUnitId, String admobRewardedVideoUnitId, String admobMediumRectId,
                     String admobNativeUnitId, String facebookBannerUnitId, String facebookInterUnitId, String facebookNativeUnitId, String facebookMediumRectId,
                     String unityGameID, String unityAdBannerUnitId, String unityAdInterUnitId, String unityAdRewardedVideoUnitId, String appodealAppKey,
                     String adColonyAppId, String adcolonyBannerId, String adcolonyInterstitialId, String adcolonyRewardedId, String bannerAdsMode,
                     String interstitialAdsMode, String rewardedAdsMode, String nativeAdsMode, String mediumrectMode) {
        this.enabled = enabled;
        admob = new AdmobConfig(admobUnitId, admobBannerUnitId, admobInterUnitId, admobRewardedVideoUnitId, admobNativeUnitId, admobMediumRectId);
        facebook = new FacebookConfig(facebookBannerUnitId, facebookInterUnitId, facebookNativeUnitId, facebookMediumRectId);
        this.unity = new UnityConfig(unityGameID, unityAdBannerUnitId, unityAdInterUnitId, unityAdRewardedVideoUnitId);
        this.adcolony = new AdcolonyConfig(adColonyAppId, adcolonyBannerId, adcolonyInterstitialId, adcolonyRewardedId);
        this.bannerAdsMode = bannerAdsMode;
        this.interstitialAdsMode = interstitialAdsMode;
        this.rewardedAdsMode = rewardedAdsMode;
        this.nativeAdsMode = nativeAdsMode;
        this.mediumrectMode = mediumrectMode;
    }

    public AdsConfig(boolean enabled, String admobUnitId, String admobBannerUnitId, List<String> admobInterUnitId, String admobRewardedVideoUnitId, String admobMediumRectId,
                     String admobNativeUnitId, String facebookBannerUnitId, String facebookInterUnitId, String facebookNativeUnitId, String facebookMediumRectId,
                     String unityGameID, String unityAdBannerUnitId, String unityAdInterUnitId, String unityAdRewardedVideoUnitId, String appodealAppKey,
                     String adColonyAppId, String adcolonyBannerId, String adcolonyInterstitialId, String adcolonyRewardedId, String bannerAdsMode,
                     String interstitialAdsMode, String rewardedAdsMode, String nativeAdsMode) {
        this(enabled, admobUnitId, admobBannerUnitId, admobInterUnitId, admobRewardedVideoUnitId, admobMediumRectId,
                admobNativeUnitId, facebookBannerUnitId, facebookInterUnitId, facebookNativeUnitId, facebookMediumRectId,
                unityGameID, unityAdBannerUnitId, unityAdInterUnitId, unityAdRewardedVideoUnitId, appodealAppKey,
                adColonyAppId, adcolonyBannerId, adcolonyInterstitialId, adcolonyRewardedId, bannerAdsMode,
                interstitialAdsMode, rewardedAdsMode, nativeAdsMode, "");
    }

    public AdsConfig() {
        admob = new AdmobConfig();
        facebook = new FacebookConfig();
        unity = new UnityConfig();
        adcolony = new AdcolonyConfig();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String banners() {
        return bannerAdsMode;
    }


    public String interstitials() {
        return interstitialAdsMode;
    }


    public String getRewardedVideoAdsMode() {
        return rewardedAdsMode;
    }


    public String natives() {
        return nativeAdsMode;
    }


    public String mediumrects() {
        return mediumrectMode;
    }

    public AdmobConfig admob() {
        return admob;
    }

    public FacebookConfig facebook() {
        return facebook;
    }

    public UnityConfig unity() {
        return unity;
    }

    public AdcolonyConfig adcolony() {
        return adcolony;
    }
}
