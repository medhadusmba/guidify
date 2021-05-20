package com.softmq.guide.app.common.ads.core;

import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java.util.Optional;

import java9.util.concurrent.CompletableFuture;

public class OptionalAd implements Ad {
    private final Optional<? extends Ad> origin;

    public OptionalAd(Optional<? extends Ad> origin) {
        this.origin = origin;
    }

    public Ad get() {
        return origin.get();
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        if (origin.isPresent()) {
            return origin.get().show(placement);
        }

        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
