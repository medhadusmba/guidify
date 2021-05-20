package com.softmq.guide.app.common.ads.facebook;

public class TestFacebookConfig extends FacebookConfig {
    public TestFacebookConfig() {
        super("IMG_16_9_APP_INSTALL#173186393601951_299214384332484",
                "IMG_16_9_APP_INSTALL#173186393601951_288058788781377",
                "VID_HD_9_16_39S_APP_INSTALL#173186393601951_356298065290782",
                "IMG_16_9_APP_INSTALL#173186393601951_299214384332484"
        );
    }

    private TestFacebookConfig(String facebookBannerUnitId, String facebookInterUnitId, String facebookNativeUnitId, String facebookMediumRectId) {
        super(facebookBannerUnitId, facebookInterUnitId, facebookNativeUnitId, facebookMediumRectId);
    }
}
