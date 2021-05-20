package com.softmq.guide.app.common.ads.admob;

import java.util.Arrays;
import java.util.List;

public class TestAdmobConfig extends AdmobConfig {
    private TestAdmobConfig(String admobUnitId, String admobBannerUnitId, List<String> admobInterUnitId, String admobRewardedVideoUnitId, String admobMediumRectId, String admobNativeUnitId) {
        super(admobUnitId, admobBannerUnitId, admobInterUnitId, admobRewardedVideoUnitId, admobMediumRectId, admobNativeUnitId);
    }

    public TestAdmobConfig() {
        super("", "ca-app-pub-3940256099942544/6300978111", Arrays.asList("ca-app-pub-3940256099942544/1033173712"), "", "ca-app-pub-3940256099942544/6300978111", "ca-app-pub-3940256099942544/2247696110");
    }
}
