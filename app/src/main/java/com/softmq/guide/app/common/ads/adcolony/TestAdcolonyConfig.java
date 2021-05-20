package com.softmq.guide.app.common.ads.adcolony;

public class TestAdcolonyConfig extends AdcolonyConfig {
    private TestAdcolonyConfig(String adcolonyAppId, String adcolonyBannerId, String adcolonyInterstitialId, String adcolonyRewardedId) {
        super(adcolonyAppId, adcolonyBannerId, adcolonyInterstitialId, adcolonyRewardedId);
    }

    public TestAdcolonyConfig() {
        super("appaa814d2551b94cdb81", "vz8a72e7b691d04847bb",
                "vz50e276396cb0423581", "");
    }
}
