package com.softmq.guide.app.common.ads.core.rewarded;

public class NoRewardedAds implements RewardedAdSource {
    @Override
    public RewardedAd rewardedAd() {
        return new NoRewardedAd();
    }
}
