package com.softmq.guide.app.common.ads.tapdaq;


import android.app.Activity;

import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;
import com.softmq.huxter.core.Huxter;

import java9.util.concurrent.CompletableFuture;

public class TapdaqInterstitialAd implements InterstitialAd {
    private Activity activity;
    private final CompletableFuture<String> onDismiss;
    private Huxter.StandaloneAd origin;

    public TapdaqInterstitialAd(Huxter.StandaloneAd origin, Activity activity, CompletableFuture<String> onDismiss) {
        this.origin = origin;
        this.activity = activity;
        this.onDismiss = onDismiss;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        CompletableFuture<Void> result = new CompletableFuture<>();
        onDismiss.whenComplete((s, throwable) -> result.complete(null));
        origin.show();
        return result;
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
