package com.softmq.guide.app.common.ads.mopub;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.banner.BannerView;
import com.softmq.guide.app.common.ads.core.banners.AsyncBannerAd;
import com.softmq.guide.app.common.ads.core.banners.BannerAd;
import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;
import com.softmq.huxter.core.Huxter;

import java9.util.concurrent.CompletableFuture;

public class MopubBannerAds implements BannerAdSource {
    private final Activity activity;
    private final String bannerAdId;

    public MopubBannerAds(Activity activity, String bannerAdId) {
        this.activity = activity;
        this.bannerAdId = bannerAdId;
    }

    @Override

    public BannerAd bannerAd() {
        CompletableFuture<BannerAd> result = new CompletableFuture<>();
        new Huxter.InlineAd(activity, bannerAdId, new Huxter.InlineAd.Listener() {
            @Override
            public void onReady(String network, View adView) {
                result.complete(new MopubBannerAd(adView));
            }

            @Override
            public void onFail(Huxter.AdError error) {
                result.completeExceptionally(new Exception(error.toString()));
            }
        }).load();
        return new AsyncBannerAd(result);
    }
}
