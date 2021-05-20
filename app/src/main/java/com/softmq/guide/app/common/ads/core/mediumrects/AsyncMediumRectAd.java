package com.softmq.guide.app.common.ads.core.mediumrects;

import com.softmq.guide.app.common.ads.core.AsyncInlineAd;

import java9.util.concurrent.CompletableFuture;

public class AsyncMediumRectAd extends AsyncInlineAd implements MediumRectAd {
    public AsyncMediumRectAd(CompletableFuture<MediumRectAd> origin) {
        super(origin.thenApply(bannerAd -> bannerAd));
    }
}
