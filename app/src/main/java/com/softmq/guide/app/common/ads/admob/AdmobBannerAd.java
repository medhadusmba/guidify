package com.softmq.guide.app.common.ads.admob;

import android.view.View;

import com.softmq.guide.app.common.ads.core.banners.BannerAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class AdmobBannerAd implements BannerAd {
    private final View origin;

    public AdmobBannerAd(View origin) {
        this.origin = origin;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        placement.show(origin);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
