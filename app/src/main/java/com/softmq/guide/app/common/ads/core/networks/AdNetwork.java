package com.softmq.guide.app.common.ads.core.networks;

import androidx.annotation.NonNull;

import com.softmq.guide.app.common.ads.core.Ads;

import org.jetbrains.annotations.NotNull;

import java9.util.concurrent.CompletableFuture;

public interface AdNetwork {
    @NonNull
    Ads ads();

    @NotNull
    CompletableFuture<Void> initialize();

    @NotNull
    Boolean isInitialized();
}
