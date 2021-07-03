package com.softmq.guide.app.common.ads.core.rewarded;

import com.softmq.guide.app.common.ads.core.AsyncStandaloneAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;

import java9.util.concurrent.CompletableFuture;

public class AsyncRewardedAd extends AsyncStandaloneAd implements RewardedAd {

    public AsyncRewardedAd(CompletableFuture<RewardedAd> result) {
        super(result.thenApply(rewardedAd -> rewardedAd));
    }
}