package com.softmq.guide.app.common.ads.facebook.interstitials;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.softmq.guide.app.common.ads.core.interstitials.AsyncInterstitialAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;
import com.softmq.guide.app.common.ads.facebook.FacebookConfig;

import java9.util.concurrent.CompletableFuture;

public class FacebookInterstitialAds implements InterstitialAdSource {
    private final Context activity;
    private final FacebookConfig config;

    public FacebookInterstitialAds(Activity activity, FacebookConfig config) {
        this.activity = activity;
        this.config = config;
    }

    @Override
    public InterstitialAd interstitialAd() {
        CompletableFuture<InterstitialAd> result = new CompletableFuture<>();
        CompletableFuture<Void> onInterstitialAdDismissed = new CompletableFuture<>();
        com.facebook.ads.InterstitialAd interstitialAd = new com.facebook.ads.InterstitialAd(activity, config.getFacebookInterUnitId());
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                onInterstitialAdDismissed.complete(null);

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                result.completeExceptionally(new Exception("facebook: interstitial failed to load with error: " + adError.getErrorMessage()));
                Log.d("todo", "Facebook error code : " + adError.getErrorCode() + " and message" + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                result.complete(new FacebookInterstitialAd(interstitialAd, onInterstitialAdDismissed));
            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        };
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());
        return new AsyncInterstitialAd(result);
    }
}
