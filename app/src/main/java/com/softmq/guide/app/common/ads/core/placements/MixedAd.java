package com.softmq.guide.app.common.ads.core.placements;

import com.softmq.guide.app.common.ads.core.Ad;
import com.softmq.guide.app.common.ads.core.InlineAd;
import com.softmq.guide.app.common.ads.core.StandaloneAd;

import java9.util.concurrent.CompletableFuture;

public class MixedAd implements Ad {
    private final InlineAd inlineAd;
    private final StandaloneAd standaloneAd;

    public MixedAd(InlineAd inlineAd, StandaloneAd standaloneAd) {
        this.inlineAd = inlineAd;
        this.standaloneAd = standaloneAd;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        return null;
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return null;
    }
}
