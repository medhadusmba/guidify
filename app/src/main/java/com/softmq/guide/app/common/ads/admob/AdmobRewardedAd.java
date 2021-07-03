package com.softmq.guide.app.common.ads.admob;

import android.app.Activity;

import com.softmq.guide.app.common.ads.core.placements.AdPlacement;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAd;
import com.softmq.huxter.core.Huxter;

import java9.util.concurrent.CompletableFuture;

public class AdmobRewardedAd implements RewardedAd {

    private final Huxter.StandaloneAd origin;
    private final Activity activity;
    private final CompletableFuture<String> onDismiss;

    public AdmobRewardedAd(Huxter.StandaloneAd origin, Activity activity, CompletableFuture<String> onDismiss) {
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
