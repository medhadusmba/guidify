package com.softmq.guide.app.common.ads.core.natives;

import com.softmq.guide.app.common.ads.core.AsyncInlineAd;

import java9.util.concurrent.CompletableFuture;

public class AsyncNativeAd extends AsyncInlineAd implements NativeAd {

    public AsyncNativeAd(CompletableFuture<NativeAd> origin) {
        super(origin.thenApply(nativeAd -> nativeAd));
    }
}
