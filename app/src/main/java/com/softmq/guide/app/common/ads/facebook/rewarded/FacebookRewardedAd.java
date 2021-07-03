package com.softmq.guide.app.common.ads.facebook.rewarded;


import android.os.Handler;

import com.facebook.ads.Ad;
import com.facebook.ads.RewardedVideoAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAd;

import java9.util.concurrent.CompletableFuture;

public class FacebookRewardedAd implements RewardedAd {


    private final RewardedVideoAd origin;
    private CompletableFuture<Void> onRewarded;

    public FacebookRewardedAd(RewardedVideoAd origin, CompletableFuture<Void> onRewarded) {
        this.origin = origin;
        this.onRewarded = onRewarded;
    }


    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        CompletableFuture<Void> result = new CompletableFuture<>();
        onRewarded.thenRun(() -> result.complete(null));
        new Handler().postDelayed(() -> {
            // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
            if (origin.isAdInvalidated()) {
                result.completeExceptionally(new Exception("facebook rewarded is already expired or invalidated"));
            }
            origin.show();
        }, 1000); // Show
        return result;
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
