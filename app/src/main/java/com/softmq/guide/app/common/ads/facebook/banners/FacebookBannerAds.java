package com.softmq.guide.app.common.ads.facebook.banners;

import android.content.Context;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.softmq.guide.app.common.ads.core.banners.AsyncBannerAd;
import com.softmq.guide.app.common.ads.core.banners.BannerAd;
import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;
import com.softmq.guide.app.common.ads.facebook.FacebookConfig;

import java9.util.concurrent.CompletableFuture;

public class FacebookBannerAds implements BannerAdSource {
    private final Context context;
    private final String id;
    private AdSize size;

    public FacebookBannerAds(Context context, FacebookConfig config, AdSize size) {

        this.id = config.getFacebookBannerUnitId();
        this.context = context;
        this.size = size;
    }

    public FacebookBannerAds(Context context, String id, AdSize size) {

        this.context = context;
        this.id = id;
        this.size = size;
    }

    public FacebookBannerAds(Context context, FacebookConfig config) {
        this(context, config, AdSize.BANNER_HEIGHT_90);
    }

    @Override
    public BannerAd bannerAd() {
        CompletableFuture<BannerAd> result = new CompletableFuture<>();
        AdView adView = new AdView(context, id, size);

        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                result.completeExceptionally(new Exception("facebook: banner failed to load with error: " + adError.getErrorMessage()));
            }

            @Override
            public void onAdLoaded(Ad ad) {
                result.complete(new FacebookBannerAd(ad, adView));
            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        };
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());

        return new AsyncBannerAd(result);
    }
}
