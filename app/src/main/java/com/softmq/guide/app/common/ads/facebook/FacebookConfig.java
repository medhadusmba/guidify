package com.softmq.guide.app.common.ads.facebook;

public class FacebookConfig {
    private final String FacebookBannerUnitId;
    private final String FacebookInterUnitId;
    private final String FacebookNativeUnitId;
    private final String facebookMediumRectId;
    private final String facebookRewardVideoUnitId;

    public FacebookConfig() {
        this("", "", "", "", "");
    }

    public FacebookConfig(String facebookBannerUnitId, String facebookInterUnitId, String facebookNativeUnitId, String facebookMediumRectId, String facebookRewardVideoUnitId) {
        FacebookBannerUnitId = facebookBannerUnitId;
        FacebookInterUnitId = facebookInterUnitId;
        FacebookNativeUnitId = facebookNativeUnitId;
        this.facebookMediumRectId = facebookMediumRectId;
        this.facebookRewardVideoUnitId = facebookRewardVideoUnitId;
    }

    public String getFacebookBannerUnitId() {
        return FacebookBannerUnitId;
    }

    public String getFacebookInterUnitId() {
        return FacebookInterUnitId;
    }

    public String getFacebookNativeUnitId() {
        return FacebookNativeUnitId;
    }

    public String getFacebookMediumRectId() {
        return facebookMediumRectId;
    }

    public String getFacebookRewardVideoUnitId() {
     return facebookRewardVideoUnitId;
    }
}
