package com.softmq.guide.app.common.ads.huawei;

import android.app.Activity;

import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.reward.RewardAd;
import com.huawei.hms.ads.reward.RewardAdLoadListener;
import com.softmq.guide.app.common.ads.core.rewarded.AsyncRewardedAd;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAd;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAdSource;

import java9.util.concurrent.CompletableFuture;

public class HuaweiRewardedAds implements RewardedAdSource {
    private final Activity activity;
    private final String rewardedAdId;

    public HuaweiRewardedAds(Activity activity, String rewardedAdId) {
        this.activity = activity;
        this.rewardedAdId = rewardedAdId;
    }

    @Override
    public RewardedAd rewardedAd() {
        CompletableFuture<RewardedAd> result = new CompletableFuture<>();

        RewardAd origin = new RewardAd(activity.getApplicationContext(), rewardedAdId);
        origin.loadAd(new AdParam.Builder().build(), new RewardAdLoadListener(){
            @Override
            public void onRewardAdFailedToLoad(int i) {
                result.completeExceptionally(new Exception("Huawei: Rewarded ad failed to load with error code: "+i));
            }

            @Override
            public void onRewardedLoaded() {
                result.complete(new HuaweiRewardedAd(activity, origin));
            }
        });
        return new AsyncRewardedAd(result);
    }
}
