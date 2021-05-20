package com.softmq.guide.app.common.ads.core;

import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class AsyncAd implements Ad {
    private final CompletableFuture<Ad> result;

    public AsyncAd(CompletableFuture<Ad> result) {
        this.result = result;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        return result.thenCompose(ad -> ad.show(placement));
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return result.getNow(new NoAd()).showNow(placement);
    }
}
