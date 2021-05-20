package com.softmq.guide.app.common.ads.admob.natives;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.softmq.guide.app.common.ads.admob.AdmobConfig;
import com.softmq.guide.app.common.ads.core.natives.AsyncNativeAd;
import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;

import java9.util.concurrent.CompletableFuture;

public class AdmobNativeAds implements NativeAdSource {
    private final AdmobConfig config;
    private final Context context;


    public AdmobNativeAds(Context context, AdmobConfig config) {
        this.context = context;
        this.config = config;
    }

    @Override
    public NativeAd nativeAd() {
        CompletableFuture<NativeAd> result = new CompletableFuture<>();
        new AdLoader.Builder(context, config.getAdmobNativeUnitId())
                .forNativeAd(nativeAd -> {
                    result.complete(new AdmobNativeAd(context, nativeAd));
                })
                .withNativeAdOptions(new NativeAdOptions.Builder().build())
                .build()
                .loadAd(new AdRequest.Builder().build());
        Log.d("todo", "admob: load native");
        return new AsyncNativeAd(result);

    }
}
