package com.softmq.guide.app.common.ads.huawei;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.softmq.guide.app.common.ads.core.interstitials.AsyncInterstitialAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;

import java9.util.concurrent.CompletableFuture;

public class HuaweiInterstitialAds implements InterstitialAdSource {
    private final Activity activity;
    private final String interstitialAdId;

    public HuaweiInterstitialAds(Activity activity, String interstitialAdId) {
        this.activity = activity;
        this.interstitialAdId = interstitialAdId;
    }

    @Override
    public InterstitialAd interstitialAd() {
        CompletableFuture<InterstitialAd> result = new CompletableFuture<>();
        com.huawei.hms.ads.InterstitialAd origin = new com.huawei.hms.ads.InterstitialAd(activity);
        // "testb4znbuh3n2" is a dedicated test ad unit ID. Before releasing your app, replace the test ad unit ID with the formal one.
        origin.setAdId(interstitialAdId);
        // Load an interstitial ad.
        AdParam adParam = new AdParam.Builder().build();
        origin.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                result.complete(new HuaweiInterstitialAd(origin, activity));
            }

            @Override
            public void onAdFailed(int i) {
                result.completeExceptionally(new Exception("huawei: interstitial ad failed to load with error code:" + i));
                Log.e("todo", "huawei: interstitial not loaded with error code: " + i);
            }
        });
        origin.loadAd(adParam);
        return new AsyncInterstitialAd(result);
    }
}
