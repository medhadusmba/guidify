package com.softmq.guide.app.common.ads.core.interstitials;

import com.softmq.guide.app.common.ads.core.AsyncStandaloneAd;

import java9.util.concurrent.CompletableFuture;

public class AsyncInterstitialAd extends AsyncStandaloneAd implements InterstitialAd {

    public AsyncInterstitialAd(CompletableFuture<InterstitialAd> result) {
        super(result.thenApply(interstitialAd -> interstitialAd));
    }
}