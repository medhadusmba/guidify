package com.softmq.guide.app.common.ads.huawei;

import android.app.Activity;
import android.content.Context;

import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.nativead.NativeAdLoader;
import com.softmq.guide.app.common.ads.core.natives.AsyncNativeAd;
import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;

import java9.util.concurrent.CompletableFuture;

public class HuaweiNativeAds implements NativeAdSource {
    private final Context context;
    private final String nativeAdId;

    public HuaweiNativeAds(Context context, String nativeAdId) {
        this.context = context;
        this.nativeAdId = nativeAdId;
    }

    @Override
    public NativeAd nativeAd() {
        CompletableFuture<NativeAd> result = new CompletableFuture<>();
        NativeAdLoader.Builder builder = new NativeAdLoader.Builder(context, nativeAdId);
        builder.setNativeAdLoadedListener(nativeAd -> {
            result.complete(new HuaweiNativeAd(context, nativeAd));
        }).setAdListener(new AdListener() {
            @Override
            public void onAdFailed(int errorCode) {
                result.completeExceptionally(new Exception("Huawei: native ad failed to load with error code: "+errorCode));
            }
        });
        NativeAdLoader nativeAdLoader = builder.build();
        nativeAdLoader.loadAd(new AdParam.Builder().build());

        return new AsyncNativeAd(result);
    }
}
