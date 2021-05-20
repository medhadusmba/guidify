package com.softmq.guide.app.common.ads.inhouse;

import android.content.Context;

import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;

public class InHouseNativeAds implements NativeAdSource {
    private final Context context;

    public InHouseNativeAds(Context context) {
        this.context = context;
    }

    @Override
    public NativeAd nativeAd() {
        return new InHouseNativeAd(context, "hi this is a simple ad");
    }
}
