package com.softmq.guide.app.common.ads.huawei;

import com.huawei.hms.ads.banner.BannerView;
import com.softmq.guide.app.common.ads.core.banners.BannerAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class HuaweiBannerAd implements BannerAd {
    private final BannerView origin;

    public HuaweiBannerAd(BannerView origin) {
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
