package com.softmq.guide.app.common.ads.admob;

import android.view.View;

import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class AdmobMediumRectAd implements MediumRectAd {
    private final View origin;

    public AdmobMediumRectAd(View origin) {
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
