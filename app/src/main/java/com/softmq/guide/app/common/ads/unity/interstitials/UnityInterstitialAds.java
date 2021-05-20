package com.softmq.guide.app.common.ads.unity.interstitials;

import android.app.Activity;

import com.softmq.guide.app.common.ads.core.interstitials.AsyncInterstitialAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;
import com.softmq.guide.app.common.ads.core.queue.CircularQueue;
import com.softmq.guide.app.common.ads.unity.UnityConfig;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.UnityAds;

import java9.util.concurrent.CompletableFuture;

public class UnityInterstitialAds implements InterstitialAdSource {
    private static final CircularQueue<IUnityAdsListener> allInterstitialCallbacks = new CircularQueue<>();
    private final UnityConfig config;
    private final Activity activity;

    public UnityInterstitialAds(Activity activity, UnityConfig config) {
        this.config = config;
        this.activity = activity;
    }

    @Override
    public InterstitialAd interstitialAd() {
        CompletableFuture<InterstitialAd> result = new CompletableFuture<>();

        UnityAds.load(config.getUnityAdInterstitialUnitId(), new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                result.complete(new UnityInterstitialAd(activity, config.getUnityAdInterstitialUnitId()));
            }

            @Override

            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                result.completeExceptionally(new Exception("unity: interstitial #" + placementId + " failed to load with error: " + message));
            }
        });

        return new AsyncInterstitialAd(result);
    }

}
