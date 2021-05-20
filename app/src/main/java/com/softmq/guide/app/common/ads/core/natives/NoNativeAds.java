package com.softmq.guide.app.common.ads.core.natives;

public class NoNativeAds implements NativeAdSource {

    public NoNativeAds() {

    }

    @Override
    public NativeAd nativeAd() {
        return new NoNativeAd();
    }
}
