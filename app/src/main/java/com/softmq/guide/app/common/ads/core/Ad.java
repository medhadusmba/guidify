package com.softmq.guide.app.common.ads.core;

import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public interface Ad {
    CompletableFuture<Void> show(AdPlacement placement);

    CompletableFuture<Void> showNow(AdPlacement placement);

}