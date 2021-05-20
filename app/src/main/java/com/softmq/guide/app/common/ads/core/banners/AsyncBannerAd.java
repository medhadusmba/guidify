package com.softmq.guide.app.common.ads.core.banners;

import com.softmq.guide.app.common.ads.core.AsyncInlineAd;

import java9.util.concurrent.CompletableFuture;

public class AsyncBannerAd extends AsyncInlineAd implements BannerAd {
    public AsyncBannerAd(CompletableFuture<BannerAd> origin) {
        super(origin.thenApply(bannerAd -> bannerAd));
    }
}
