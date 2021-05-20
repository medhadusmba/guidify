package com.softmq.guide.app.common.ads.facebook.banners;

import android.view.View;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdView;
import com.softmq.guide.app.common.ads.core.banners.BannerAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class FacebookBannerAd implements BannerAd {

    private final Ad ad;
    private final AdView origin;

    public FacebookBannerAd(Ad ad, AdView origin) {
        this.ad = ad;
        this.origin = origin;
    }

    @NonNull
    private View asView() {
        return origin;
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
