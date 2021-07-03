package com.softmq.guide.app.common.ads.core.rewarded;

import com.softmq.guide.app.common.ads.core.AsyncStandaloneAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;

import java9.util.concurrent.CompletableFuture;

public class AsyncInterstitialAd extends AsyncStandaloneAd implements InterstitialAd {

    public AsyncInterstitialAd(CompletableFuture<InterstitialAd> result) {
        super(result.thenApply(interstitialAd -> interstitialAd));
    }
}