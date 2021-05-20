package com.softmq.guide.app.common.ads.admob.mediumrects;

import com.softmq.guide.app.common.ads.core.banners.BannerAd;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class AdmobMediumRectAd implements MediumRectAd {
    private BannerAd origin;

    public AdmobMediumRectAd(BannerAd bannerAd) {
        this.origin = bannerAd;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        return origin.show(placement);
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
