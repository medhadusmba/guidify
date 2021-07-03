package com.softmq.guide.app.common.ads.tapdaq;


import android.view.View;

import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class TapdaqNativeAd implements NativeAd {


    private View adView;

    public TapdaqNativeAd(View adView) {

        this.adView = adView;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        placement.show(adView);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
