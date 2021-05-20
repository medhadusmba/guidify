package com.softmq.guide.app.common.ads.adcolony.interstitials;

import android.util.Log;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyZone;
import com.softmq.guide.app.common.ads.adcolony.AdcolonyConfig;
import com.softmq.guide.app.common.ads.core.interstitials.AsyncInterstitialAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;

import java9.util.concurrent.CompletableFuture;

public class AdcolonyInterstitialAds implements InterstitialAdSource {
    private final AdcolonyConfig config;

    public AdcolonyInterstitialAds(AdcolonyConfig config) {
        this.config = config;
    }

    @Override
    public InterstitialAd interstitialAd() {
        CompletableFuture<InterstitialAd> result = new CompletableFuture<>();

        AdColonyInterstitialListener adColonyInterstitialListener = new AdColonyInterstitialListener() {
            @Override
            public void onRequestFilled(AdColonyInterstitial interstitial) {
                result.complete(new AdcolonyInterstitialAd(interstitial));
                Log.d("todo", "adcolony: Interstitial loaded");
            }

            @Override
            public void onRequestNotFilled(AdColonyZone zone) {
                result.completeExceptionally(new Exception("adcolony: interstitial failed to load"));
                Log.d("todo", "adcolony: Interstitial failed to load");

            }

            @Override
            public void onOpened(AdColonyInterstitial p_ad) {

            }

            @Override
            public void onClosed(AdColonyInterstitial ad) {

            }

            @Override
            public void onExpiring(AdColonyInterstitial p_ad) {

            }
        };
        AdColony.requestInterstitial(config.adcolonyInterstitialId(), adColonyInterstitialListener);

        return new AsyncInterstitialAd(result);
    }
}
