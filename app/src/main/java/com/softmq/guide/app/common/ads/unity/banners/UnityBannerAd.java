package com.softmq.guide.app.common.ads.unity.banners;

import android.view.View;

import androidx.annotation.NonNull;

import com.softmq.guide.app.common.ads.core.banners.BannerAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class UnityBannerAd implements BannerAd {
    private final View origin;

    public UnityBannerAd(View origin) {
        this.origin = origin;
    }

    @NonNull
    private View asView() {
        return origin;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        placement.show(asView());
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
