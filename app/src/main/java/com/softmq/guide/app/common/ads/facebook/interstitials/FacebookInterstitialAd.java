package com.softmq.guide.app.common.ads.facebook.interstitials;


import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class FacebookInterstitialAd implements InterstitialAd {

    private final com.facebook.ads.InterstitialAd origin;
    private final CompletableFuture<Void> onInterstitialAdDismissed;

    public FacebookInterstitialAd(com.facebook.ads.InterstitialAd origin, CompletableFuture<Void> onInterstitialAdDismissed) {
        this.origin = origin;
        this.onInterstitialAdDismissed = onInterstitialAdDismissed;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        CompletableFuture<Void> result = new CompletableFuture<>();
        onInterstitialAdDismissed.thenRun(() -> result.complete(null));
        origin.show();
        return result;
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
