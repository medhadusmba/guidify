package com.softmq.guide.app.common.ads.mopub;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.nativead.NativeAdLoader;
import com.softmq.guide.app.common.ads.core.natives.AsyncNativeAd;
import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;
import com.softmq.huxter.core.Huxter;

import java9.util.concurrent.CompletableFuture;

public class MopubNativeAds implements NativeAdSource {

    private final Activity activity;
    private final String nativeAdId;

    public MopubNativeAds(Activity activity, String nativeAdId) {
        this.activity = activity;
        this.nativeAdId = nativeAdId;
    }

    @Override
    public NativeAd nativeAd() {
        CompletableFuture<NativeAd> result = new CompletableFuture<>();
        new Huxter.InlineAd(activity, nativeAdId, new Huxter.InlineAd.Listener() {
            @Override
            public void onReady(String network, View adView) {
                result.complete(new MopubNativeAd(adView));
            }

            @Override
            public void onFail(Huxter.AdError error) {
                result.completeExceptionally(new Exception(error.toString()));
            }
        }, Huxter.InlineAd.Binder.DEFAULT).load();

        return new AsyncNativeAd(result);
    }
}
