package com.softmq.guide.app.common.ads.core;

import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class AsyncInlineAd extends AsyncAd implements InlineAd {
    private final CompletableFuture<InlineAd> origin;

    public AsyncInlineAd(CompletableFuture<InlineAd> origin) {
        super(origin.thenApply(inlineAd -> inlineAd));
        this.origin = origin;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        return origin.thenCompose(inlineAd -> inlineAd.show(placement));

    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        try {
            return origin.getNow(new NoInlineAd()).show(placement);
        } catch (Throwable t) {
            return CompletableFuture.completedFuture(null);

        }
    }

}
