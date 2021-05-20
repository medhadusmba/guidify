package com.softmq.guide.app.common.ads.core;

import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class NoAd implements Ad {
    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
