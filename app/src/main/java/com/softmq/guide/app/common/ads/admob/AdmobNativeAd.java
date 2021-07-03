package com.softmq.guide.app.common.ads.admob;


import android.view.View;

import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class AdmobNativeAd implements NativeAd {


    private View adView;

    public AdmobNativeAd(View adView) {

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
