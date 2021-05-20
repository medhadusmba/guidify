package com.softmq.guide.app.common.ads.facebook.mediumrect;

import com.softmq.guide.app.common.ads.core.banners.BannerAd;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class FacebookMediumRectAd implements MediumRectAd {


    private final BannerAd origin;

    public FacebookMediumRectAd(BannerAd origin) {
        this.origin = origin;
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
