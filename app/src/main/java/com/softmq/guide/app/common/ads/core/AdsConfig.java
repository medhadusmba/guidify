package com.softmq.guide.app.common.ads.core;


import com.softmq.guide.app.common.ads.adcolony.AdcolonyConfig;
import com.softmq.guide.app.common.ads.admob.AdmobConfig;
import com.softmq.guide.app.common.ads.facebook.FacebookConfig;
import com.softmq.guide.app.common.ads.huawei.HuaweiConfig;
import com.softmq.guide.app.common.ads.mopub.MopubConfig;
import com.softmq.guide.app.common.ads.tapdaq.TapdaqConfig;
import com.softmq.guide.app.common.ads.unity.UnityConfig;

import java.util.List;

public class AdsConfig {

    private boolean enabled = false;
    private String bannerAdsMode = "";
    private String interstitialAdsMode = "";
    private String rewardedAdsMode = "";
    private String nativeAdsMode = "";
    private String mediumrectMode = "";
    private final FacebookConfig facebook;
    private final UnityConfig unity;
    private final AdcolonyConfig adcolony;
    private final AdmobConfig admob;
    private final HuaweiConfig huawei;
    private MopubConfig mopub;
    private TapdaqConfig tapdaq;

    public AdsConfig(boolean enabled, String admobUnitId, String admobBannerUnitId, List<String> admobInterUnitId, String admobRewardedVideoUnitId, String admobMediumRectId,
                     String admobNativeUnitId, String facebookBannerUnitId, String facebookInterUnitId, String facebookNativeUnitId, String facebookMediumRectId, String facebookRewardedVideoUnitId,
                     String unityGameID, String unityAdBannerUnitId, String unityAdInterUnitId, String unityAdRewardedVideoUnitId, String appodealAppKey,
                     String adColonyAppId, String adcolonyBannerId, String adcolonyInterstitialId, String adcolonyRewardedId, String huaweiBannerUnitId, String huaweiInterUnitId, String huaweiNativeUnitId, String huaweiRewardedVideoUnitId, MopubConfig mopub, TapdaqConfig tapdaq, String bannerAdsMode,
                     String interstitialAdsMode, String rewardedAdsMode, String nativeAdsMode, String mediumrectMode) {
        this.enabled = enabled;
        this.mopub = mopub;
        this.tapdaq = tapdaq;
        admob = new AdmobConfig(admobUnitId, admobBannerUnitId, admobInterUnitId, admobRewardedVideoUnitId, admobNativeUnitId, admobMediumRectId);
        facebook = new FacebookConfig(facebookBannerUnitId, facebookInterUnitId, facebookNativeUnitId, facebookMediumRectId, facebookRewardedVideoUnitId);
        this.unity = new UnityConfig(unityGameID, unityAdBannerUnitId, unityAdInterUnitId, unityAdRewardedVideoUnitId);
        this.adcolony = new AdcolonyConfig(adColonyAppId, adcolonyBannerId, adcolonyInterstitialId, adcolonyRewardedId);
        this.huawei=new HuaweiConfig(huaweiBannerUnitId, huaweiInterUnitId, huaweiNativeUnitId, huaweiRewardedVideoUnitId);
        this.bannerAdsMode = bannerAdsMode;
        this.interstitialAdsMode = interstitialAdsMode;
        this.nativeAdsMode = nativeAdsMode;
        this.mediumrectMode = mediumrectMode;
        this.rewardedAdsMode = rewardedAdsMode;
    }


    public AdsConfig() {
        admob = new AdmobConfig();
        facebook = new FacebookConfig();
        unity = new UnityConfig();
        adcolony = new AdcolonyConfig();
        huawei = new HuaweiConfig();
        mopub = new MopubConfig();
        tapdaq = new TapdaqConfig();
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


    public String rewardedAds() {
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
    public HuaweiConfig huawei() {
        return huawei;
    }
    public MopubConfig mopub() {
        return mopub;
    }
    public TapdaqConfig tapdaq() {
        return tapdaq;
    }
}
