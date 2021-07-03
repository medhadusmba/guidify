package com.softmq.guide.app.common.ads.core.limit;

import com.softmq.guide.app.common.ads.core.Ads;
import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAdSource;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAdSource;

public class LimitedAds implements Ads {


    @Override
    public BannerAdSource banners() {
        return null;
    }

    @Override
    public InterstitialAdSource interstitials() {
        return null;
    }

    @Override
    public NativeAdSource natives() {
        return null;
    }

    @Override
    public MediumRectAdSource mediumrects() {
        return null;
    }

    @Override
    public RewardedAdSource rewardedAds() {
        return null;
    }
}
