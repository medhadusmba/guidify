package com.softmq.guide.app.common.ads.core.natives;

import com.softmq.guide.app.common.ads.core.Ad;

import java9.util.concurrent.CompletableFuture;

public class NoNativeAds implements NativeAdSource {

    public NoNativeAds() {

    }

    @Override
    public NativeAd nativeAd() {
        return new NoNativeAd();
    }

}
