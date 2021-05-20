package com.softmq.guide.app.common.ads.unity.interstitials;

import android.app.Activity;

import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

import java9.util.concurrent.CompletableFuture;

public class UnityInterstitialAd extends CompletableFuture<InterstitialAd> implements InterstitialAd {
    private final Activity activity;
    private final String unityAdInterstitialUnitId;

    public UnityInterstitialAd(Activity activity, String unityAdInterstitialUnitId) {
        this.activity = activity;
        this.unityAdInterstitialUnitId = unityAdInterstitialUnitId;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        CompletableFuture<Void> result = new CompletableFuture<>();
        UnityAds.show(activity, unityAdInterstitialUnitId, new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                result.completeExceptionally(new Exception("unity: interstitial failed to show with message: " + message));

            }

            @Override
            public void onUnityAdsShowStart(String placementId) {

            }

            @Override
            public void onUnityAdsShowClick(String placementId) {

            }

            @Override
            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                result.complete(null);

            }
        });
        return result;
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
