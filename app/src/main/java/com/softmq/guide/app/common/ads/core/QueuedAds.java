package com.softmq.guide.app.common.ads.core;

import com.softmq.guide.app.common.ads.core.queue.ListenableCircularQueue;
import com.softmq.guide.app.common.core.Provider;

import java9.util.concurrent.CompletableFuture;

public class QueuedAds<T> implements Provider<CompletableFuture<T>> {
    private final Provider<CompletableFuture<T>> provider;
    ListenableCircularQueue<T> queue;


    public QueuedAds(Provider<CompletableFuture<T>> provider) {
        this.provider = provider;

        this.queue = new ListenableCircularQueue<>();
        //this.queue.setOnEmptyListener(()->provider.get().thenAccept(nativeAd -> queue.add(nativeAd)));
    }

    public CompletableFuture<T> get() {
        return queue.poll().map(CompletableFuture::completedFuture).orElseGet(() -> {
            CompletableFuture<T> result = new CompletableFuture<>();
            provider.get().thenAccept(result::complete);
            return result;
        });
    }


}
