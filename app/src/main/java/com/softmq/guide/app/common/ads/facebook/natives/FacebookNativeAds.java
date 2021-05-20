package com.softmq.guide.app.common.ads.facebook.natives;

import android.content.Context;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.NativeAdListener;
import com.softmq.guide.app.common.ads.core.natives.AsyncNativeAd;
import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;
import com.softmq.guide.app.common.ads.facebook.FacebookConfig;

import java9.util.concurrent.CompletableFuture;


public class FacebookNativeAds implements NativeAdSource {
    private final Context context;
    private final FacebookConfig config;

    public FacebookNativeAds(Context context, FacebookConfig config) {
        this.context = context;
        this.config = config;
    }

    @Override
    public NativeAd nativeAd() {
        CompletableFuture<NativeAd> result = new CompletableFuture<>();
        com.facebook.ads.NativeAd nativeAd = new com.facebook.ads.NativeAd(context, config.getFacebookNativeUnitId());
        nativeAd.loadAd(
                nativeAd.buildLoadAdConfig()
                        .withAdListener(new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                result.complete(new FacebookNativeAd(context, nativeAd));
                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                            }
                        })
                        .build());
        return new AsyncNativeAd(result);
    }

}
