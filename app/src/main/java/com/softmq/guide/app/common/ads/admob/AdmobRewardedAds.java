package com.softmq.guide.app.common.ads.admob;

import android.app.Activity;

import com.softmq.guide.app.common.ads.core.rewarded.AsyncRewardedAd;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAd;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAdSource;
import com.softmq.huxter.core.Huxter;

import java9.util.concurrent.CompletableFuture;

public class AdmobRewardedAds implements RewardedAdSource {
    private final Activity activity;
    private final String rewardedAdId;
    private static Huxter.StandaloneAd origin ;
    public AdmobRewardedAds(Activity activity, String rewardedAdId) {
        this.activity = activity;
        this.rewardedAdId = rewardedAdId;
    }

    @Override
    public RewardedAd rewardedAd() {
        CompletableFuture<RewardedAd> result = new CompletableFuture<>();
        CompletableFuture<String> onDismiss = new CompletableFuture<>();

        origin=new Huxter.StandaloneAd(activity, rewardedAdId, Huxter.AdFormat.Rewarded, new Huxter.StandaloneAd.Listener() {
            @Override
            public void onDismiss(String network) {
                onDismiss.complete(network);
            }

            @Override
            public void onFail(Huxter.AdError error) {
                result.completeExceptionally(new Exception(error.toString()));
                onDismiss.completeExceptionally(new Exception(error.toString()));
            }

            @Override
            public void onReady(String network) {
                result.complete(new AdmobRewardedAd(origin, activity, onDismiss));
            }
        });
        origin.refreshAd(false);
        return new AsyncRewardedAd(result);
    }
}
