package com.softmq.guide.app.common.ads.mopub;

import android.app.Activity;

import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.reward.RewardAd;
import com.huawei.hms.ads.reward.RewardAdLoadListener;
import com.softmq.guide.app.common.ads.core.rewarded.AsyncRewardedAd;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAd;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAdSource;
import com.softmq.huxter.core.Huxter;

import java9.util.concurrent.CompletableFuture;

public class MopubRewardedAds implements RewardedAdSource {
    private final Activity activity;
    private final String rewardedAdId;

    public MopubRewardedAds(Activity activity, String rewardedAdId) {
        this.activity = activity;
        this.rewardedAdId = rewardedAdId;
    }

    @Override
    public RewardedAd rewardedAd() {
        CompletableFuture<RewardedAd> result = new CompletableFuture<>();
        CompletableFuture<String> onDismiss = new CompletableFuture<>();
        Huxter.StandaloneAd origin = null;
        Huxter.StandaloneAd finalOrigin = origin;
        origin=new Huxter.StandaloneAd(activity, rewardedAdId, new Huxter.StandaloneAd.Listener() {
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
                result.complete(new MopubRewardedAd(finalOrigin, activity, onDismiss));
            }
        });
        origin.refreshAd(false);
        return new AsyncRewardedAd(result);
    }
}
