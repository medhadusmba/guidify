package com.softmq.guide.app.common.ads.core;

import com.softmq.guide.app.common.ads.core.placements.StandaloneAdPlacement;

import java9.util.concurrent.CompletableFuture;

public interface StandaloneAd extends Ad {
    default CompletableFuture<Void> show() {
        return show(new StandaloneAdPlacement());
    }

    default CompletableFuture<Void> showNow() {
        return showNow(new StandaloneAdPlacement());
    }
}
