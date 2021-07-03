package com.softmq.guide.app.ads;

import android.content.Context;

import com.softmq.guide.app.common.ads.core.Ad;
import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;

import java9.util.concurrent.CompletableFuture;

public class ScrollableNativeAds implements NativeAdSource {
    private final NativeAdSource nativeAds;
    private final Context context;

    public ScrollableNativeAds(NativeAdSource nativeAds, Context context) {
        this.nativeAds = nativeAds;
        this.context = context;
    }

    @Override
    public NativeAd nativeAd() {
        return new ScrollableNativeAd(nativeAds.nativeAd(), context);
    }

}
