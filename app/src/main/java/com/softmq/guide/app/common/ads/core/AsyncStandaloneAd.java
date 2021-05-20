package com.softmq.guide.app.common.ads.core;

import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class AsyncStandaloneAd implements StandaloneAd {
    private final CompletableFuture<StandaloneAd> result;

    public AsyncStandaloneAd(CompletableFuture<StandaloneAd> result) {
        this.result = result;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        return result.thenCompose(interstitialAd -> interstitialAd.show(placement));
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        try {
            return result.getNow(new NoStandaloneAd()).showNow(placement);
        } catch (Throwable t) {
            return new NoStandaloneAd().showNow(placement);
        }
    }
}
