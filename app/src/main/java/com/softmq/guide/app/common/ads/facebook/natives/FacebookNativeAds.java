package com.softmq.guide.app.common.ads.facebook.natives;

import android.content.Context;

import com.facebook.ads.AdError;
import com.facebook.ads.NativeAdListener;
import com.softmq.guide.app.common.ads.core.Ad;
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
    public CompletableFuture<? extends Ad> ad() {
        CompletableFuture<NativeAd> result = new CompletableFuture<>();
        com.facebook.ads.NativeAd nativeAd = new com.facebook.ads.NativeAd(context, config.getFacebookNativeUnitId());
        nativeAd.loadAd(
                nativeAd.buildLoadAdConfig()
                        .withAdListener(new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(com.facebook.ads.Ad ad) {

                            }

                            @Override
                            public void onError(com.facebook.ads.Ad ad, AdError adError) {
                                result.completeExceptionally(new Exception("facebook: failed to load native ad with error code: "+adError.getErrorCode()));
                            }

                            @Override
                            public void onAdLoaded(com.facebook.ads.Ad ad) {
                                // Race condition, load() called again before last ad was displayed
                                if (nativeAd == null || nativeAd != ad) {
                                    return;
                                }
                                result.complete(new FacebookNativeAd(context, nativeAd));

                            }

                            @Override
                            public void onAdClicked(com.facebook.ads.Ad ad) {
                            }

                            @Override
                            public void onLoggingImpression(com.facebook.ads.Ad ad) {
                            }
                        })
                        .build());
        return result;
    }

    @Override
    public NativeAd nativeAd() {
        CompletableFuture<NativeAd> result = new CompletableFuture<>();
        com.facebook.ads.NativeAd nativeAd = new com.facebook.ads.NativeAd(context, config.getFacebookNativeUnitId());
        nativeAd.loadAd(
                nativeAd.buildLoadAdConfig()
                        .withAdListener(new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(com.facebook.ads.Ad ad) {
                            }

                            @Override
                            public void onError(com.facebook.ads.Ad ad, AdError adError) {
                                result.completeExceptionally(new Exception("facebook: failed to load native ad with error code: "+adError.getErrorCode()));
                            }

                            @Override
                            public void onAdLoaded(com.facebook.ads.Ad ad) {
                                result.complete(new FacebookNativeAd(context, nativeAd));
                            }

                            @Override
                            public void onAdClicked(com.facebook.ads.Ad ad) {
                            }

                            @Override
                            public void onLoggingImpression(com.facebook.ads.Ad ad) {
                            }
                        })
                        .build());
        return new AsyncNativeAd(result);
    }


}
