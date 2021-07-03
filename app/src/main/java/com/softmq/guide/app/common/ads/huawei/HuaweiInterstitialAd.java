package com.softmq.guide.app.common.ads.huawei;


import android.app.Activity;

import com.huawei.hms.ads.AdListener;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class HuaweiInterstitialAd implements InterstitialAd {
    private com.huawei.hms.ads.InterstitialAd origin;
    private Activity activity;

    public HuaweiInterstitialAd(com.huawei.hms.ads.InterstitialAd origin, Activity activity) {
        this.origin = origin;
        this.activity = activity;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        CompletableFuture<Void> result = new CompletableFuture<>();
        origin.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                result.complete(null);
            }
        });
        origin.show(activity);
        return result;
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
