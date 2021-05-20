package com.softmq.guide.app.common.ads.admob;


import java.util.ArrayList;
import java.util.List;

public class AdmobConfig {
    private final String AdmobUnitId;
    private final String AdmobBannerUnitId;
    private final List<String> AdmobInterUnitId;
    private final String AdmobRewardedVideoUnitId;
    private final String AdmobNativeUnitId;
    private final String admobMediumRectId;


    public AdmobConfig(String admobUnitId, String admobBannerUnitId, List<String> admobInterUnitId, String admobRewardedVideoUnitId,
                       String admobNativeUnitId, String admobMediumRectId) {
        this.AdmobUnitId = admobUnitId;
        this.AdmobBannerUnitId = admobBannerUnitId;
        this.AdmobInterUnitId = admobInterUnitId;
        this.AdmobRewardedVideoUnitId = admobRewardedVideoUnitId;
        this.AdmobNativeUnitId = admobNativeUnitId;
        this.admobMediumRectId = admobMediumRectId;
    }

    public AdmobConfig() {
        this("", "", new ArrayList<>(), "", "", "");
    }

    public String getAdmobUnitId() {
        return AdmobUnitId;
    }

    public String getAdmobBannerUnitId() {
        return AdmobBannerUnitId;
    }


    public List<String> interstitialAdUnitId() {
        return AdmobInterUnitId;
    }


    public String getAdmobRewardedVideoUnitId() {
        return AdmobRewardedVideoUnitId;
    }

    public String getAdmobNativeUnitId() {
        return AdmobNativeUnitId;
    }

    public String getAdmobMediumRectId() {
        return admobMediumRectId;
    }

}
