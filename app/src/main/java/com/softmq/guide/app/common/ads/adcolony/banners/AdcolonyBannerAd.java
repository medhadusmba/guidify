package com.softmq.guide.app.common.ads.adcolony.banners;

import com.adcolony.sdk.AdColonyAdView;
import com.softmq.guide.app.common.ads.core.banners.BannerAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class AdcolonyBannerAd implements BannerAd {
    private final AdColonyAdView origin;

    public AdcolonyBannerAd(AdColonyAdView origin) {

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
