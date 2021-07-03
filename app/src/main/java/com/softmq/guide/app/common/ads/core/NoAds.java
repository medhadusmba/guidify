package com.softmq.guide.app.common.ads.core;

import androidx.annotation.Nullable;

import com.softmq.guide.app.common.ads.core.banners.NoBannerAds;
import com.softmq.guide.app.common.ads.core.interstitials.NoInterstitialAds;
import com.softmq.guide.app.common.ads.core.natives.NoNativeAds;

public class NoAds extends BaseAds {

    public NoAds() {
        super(new NoBannerAds(), new NoInterstitialAds(), new NoNativeAds());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof NoAds;
    }
}
