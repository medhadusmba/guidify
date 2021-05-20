package com.softmq.guide.app.common.ads.facebook;

public class FacebookConfig {
    private final String FacebookBannerUnitId;
    private final String FacebookInterUnitId;
    private final String FacebookNativeUnitId;
    private final String facebookMediumRectId;

    public FacebookConfig() {
        this("", "", "", "");
    }

    public FacebookConfig(String facebookBannerUnitId, String facebookInterUnitId, String facebookNativeUnitId, String facebookMediumRectId) {
        FacebookBannerUnitId = facebookBannerUnitId;
        FacebookInterUnitId = facebookInterUnitId;
        FacebookNativeUnitId = facebookNativeUnitId;
        this.facebookMediumRectId = facebookMediumRectId;
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
}
