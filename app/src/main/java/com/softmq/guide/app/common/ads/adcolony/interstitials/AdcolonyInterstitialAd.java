package com.softmq.guide.app.common.ads.adcolony.interstitials;

import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyZone;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class AdcolonyInterstitialAd implements InterstitialAd {
    private final AdColonyInterstitial origin;

    public AdcolonyInterstitialAd(AdColonyInterstitial origin) {
        this.origin = origin;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        CompletableFuture<Void> result = new CompletableFuture<>();
        origin.setListener(new AdColonyInterstitialListener() {
            @Override
            public void onClosed(AdColonyInterstitial ad) {
                result.complete(null);
            }

            @Override
            public void onRequestFilled(AdColonyInterstitial adColonyInterstitial) {

            }

            @Override
            public void onRequestNotFilled(AdColonyZone zone) {
                result.completeExceptionally(new Exception("adcolony: interstitial failed to load"));
            }
        });
        origin.show();
        return result;
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
