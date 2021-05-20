package com.softmq.guide.app.common.ads.adcolony.banners;

import android.content.Context;
import android.util.Log;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdSize;
import com.adcolony.sdk.AdColonyAdView;
import com.adcolony.sdk.AdColonyAdViewListener;
import com.adcolony.sdk.AdColonyZone;
import com.softmq.guide.app.common.ads.adcolony.AdcolonyConfig;
import com.softmq.guide.app.common.ads.core.banners.AsyncBannerAd;
import com.softmq.guide.app.common.ads.core.banners.BannerAd;
import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;

import java9.util.concurrent.CompletableFuture;

public class AdcolonyBannerAds implements BannerAdSource {
    private final Context context;
    private final AdcolonyConfig config;

    public AdcolonyBannerAds(Context context, AdcolonyConfig config) {
        this.context = context;
        this.config = config;
    }


    @Override
    public BannerAd bannerAd() {
        CompletableFuture<BannerAd> result = new CompletableFuture<>();
        AdColonyAdViewListener listener = new AdColonyAdViewListener() {
            @Override
            public void onRequestFilled(AdColonyAdView ad) {
                result.complete(new AdcolonyBannerAd(ad));
                Log.d("todo", "adcolony: banner loaded");

            }

            @Override
            public void onRequestNotFilled(AdColonyZone zone) {
                Log.d("todo", "adcolony: banner  failed to load");
            }
        };

        AdColony.requestAdView(config.adcolonyBannerId(), listener, AdColonyAdSize.BANNER);

        return new AsyncBannerAd(result);
    }
}
