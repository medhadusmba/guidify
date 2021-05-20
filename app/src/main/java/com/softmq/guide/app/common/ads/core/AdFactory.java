package com.softmq.guide.app.common.ads.core;

import com.softmq.guide.app.common.core.Provider;

import java9.util.concurrent.CompletableFuture;

public interface AdFactory extends Provider<CompletableFuture<? extends Ad>> {

}
