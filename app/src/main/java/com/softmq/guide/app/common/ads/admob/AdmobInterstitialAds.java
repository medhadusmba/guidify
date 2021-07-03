package com.softmq.guide.app.common.ads.admob;

import android.app.Activity;

import com.softmq.guide.app.common.ads.core.StandaloneAd;
import com.softmq.guide.app.common.ads.core.interstitials.AsyncInterstitialAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;
import com.softmq.huxter.core.Huxter;

import java9.util.concurrent.CompletableFuture;

public class AdmobInterstitialAds implements InterstitialAdSource {
    private final Activity activity;
    private final String interstitialAdId;
    private static Huxter.StandaloneAd origin;

    public AdmobInterstitialAds(Activity activity, String interstitialAdId) {
        this.activity = activity;
        this.interstitialAdId = interstitialAdId;
    }
    @Override
    public InterstitialAd interstitialAd() {
        CompletableFuture<InterstitialAd> result = new CompletableFuture<>();
        CompletableFuture<String> onDismiss = new CompletableFuture<>();
        Huxter.StandaloneAd.Listener listener=new Huxter.StandaloneAd.Listener() {
            @Override
            public void onDismiss(String network) {
                onDismiss.complete(network);
            }

            @Override
            public void onFail(Huxter.AdError error) {
                result.completeExceptionally(new Exception(error.toString()));
                onDismiss.completeExceptionally(new Exception(error.toString()));
            }

            @Override
            public void onReady(String network) {
                result.complete(new AdmobInterstitialAd(origin, activity, onDismiss));
            }
        };
        origin=new Huxter.StandaloneAd(activity, interstitialAdId, listener);
        origin.refreshAd(false);
        return new AsyncInterstitialAd(result);
    }
}
