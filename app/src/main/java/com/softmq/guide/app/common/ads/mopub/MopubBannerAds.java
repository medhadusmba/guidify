package com.softmq.guide.app.common.ads.huawei;

import android.content.Context;
import android.util.Log;

import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.banner.BannerView;
import com.softmq.guide.app.common.ads.core.banners.AsyncBannerAd;
import com.softmq.guide.app.common.ads.core.banners.BannerAd;
import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;

import java9.util.concurrent.CompletableFuture;

public class HuaweiBannerAds implements BannerAdSource {
    private final Context context;
    private final String bannerAdId;

    public HuaweiBannerAds(Context context, String bannerAdId) {
        this.context = context;
        this.bannerAdId = bannerAdId;
    }

    @Override
    public BannerAd bannerAd() {
        CompletableFuture<BannerAd> result = new CompletableFuture<>();

        // Obtain BannerView.
        BannerView bannerView = new BannerView(context);

        // Set the ad unit ID and ad dimensions. "testw6vs28auh3" is a dedicated test ad unit ID.
        bannerView.setAdId(bannerAdId);
        bannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_360_57);
        // Set the refresh interval to 30 seconds.
        bannerView.setBannerRefresh(30);
        bannerView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                result.complete(new HuaweiBannerAd(bannerView));
                Log.d("todo", "huawei: ad loaded successfully");
            }

            @Override
            public void onAdFailed(int i) {
                result.completeExceptionally(new Exception("huawei: banner ad failed to load with error code:" + i));
                Log.e("todo", "huawei: ad not loaded with error code: " + i);
            }
        });
        // Create an ad request to load an ad.
        AdParam adParam = new AdParam.Builder().build();
        bannerView.loadAd(adParam);
        return new AsyncBannerAd(result);
    }
}
