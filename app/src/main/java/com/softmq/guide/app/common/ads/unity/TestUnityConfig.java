package com.softmq.guide.app.common.ads.unity;

public class TestUnityConfig extends UnityConfig {
    private TestUnityConfig(String UnityGameID, String bannerUnitId, String interUnitId, String rewardedVideoUnitId) {
        super(UnityGameID, bannerUnitId, interUnitId, rewardedVideoUnitId);
    }

    public TestUnityConfig() {
        super("4100687", "Banner_Android", "Interstitial_Android", "");
    }
}
