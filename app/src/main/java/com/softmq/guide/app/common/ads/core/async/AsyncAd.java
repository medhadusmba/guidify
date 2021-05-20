package com.softmq.guide.app.common.ads.core.async;

import com.softmq.guide.app.common.ads.core.Ad;

import java9.util.concurrent.CompletableFuture;

public interface AsyncAd extends Ad {
    /**
     * shows ad asynchronously
     *
     * @return void
     */
    CompletableFuture<Void> showAsync();
}
