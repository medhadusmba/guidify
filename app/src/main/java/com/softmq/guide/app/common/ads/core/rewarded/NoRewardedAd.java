package com.softmq.guide.app.common.ads.core.rewarded;

import com.softmq.guide.app.common.ads.core.NoStandaloneAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class NoRewardedAd extends NoStandaloneAd implements RewardedAd {
    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        return CompletableFuture.failedFuture(new Exception("Check later to get your reward!"));
    }
}
