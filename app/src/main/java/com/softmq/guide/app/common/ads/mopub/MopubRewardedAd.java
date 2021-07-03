package com.softmq.guide.app.common.ads.huawei;

import android.app.Activity;

import com.huawei.hms.ads.reward.Reward;
import com.huawei.hms.ads.reward.RewardAd;
import com.huawei.hms.ads.reward.RewardAdStatusListener;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAd;

import java9.util.concurrent.CompletableFuture;

public class HuaweiRewardedAd implements RewardedAd {
    private final Activity activity;
    private final RewardAd origin;

    public HuaweiRewardedAd(Activity activity, RewardAd origin) {
        this.activity = activity;
        this.origin = origin;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        CompletableFuture<Void> result=new CompletableFuture<>();
        origin.show(activity, new RewardAdStatusListener() {
            @Override
            public void onRewardAdFailedToShow(int i) {
                result.completeExceptionally(new Exception("Huahei: Rewarded failed to show with error code: "+i));
            }

            @Override
            public void onRewarded(Reward reward) {
                result.complete(null);
            }
        });

        return result;
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
