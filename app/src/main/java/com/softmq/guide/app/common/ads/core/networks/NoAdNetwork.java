package com.softmq.guide.app.common.ads.core.networks;

import androidx.annotation.NonNull;

import com.softmq.guide.app.common.ads.core.Ads;
import com.softmq.guide.app.common.ads.core.NoAds;

import org.jetbrains.annotations.NotNull;

import java9.util.concurrent.CompletableFuture;

public class NoAdNetwork implements AdNetwork {

    @NonNull
    @Override
    public Ads ads() {
        return new NoAds();
    }

    @NotNull
    @Override
    public CompletableFuture<Void> initialize() {
        return CompletableFuture.completedFuture(null);
    }

    @NotNull
    @Override
    public Boolean isInitialized() {
        return true;
    }
}
