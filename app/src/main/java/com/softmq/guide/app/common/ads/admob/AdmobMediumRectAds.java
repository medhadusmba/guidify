package com.softmq.guide.app.common.ads.admob;

import android.app.Activity;
import android.view.View;

import com.softmq.guide.app.common.ads.core.mediumrects.AsyncMediumRectAd;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAd;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAdSource;
import com.softmq.huxter.core.Huxter;

import java9.util.concurrent.CompletableFuture;

public class AdmobMediumRectAds implements MediumRectAdSource {
    private final Activity activity;
    private final String mediumrectId;

    public AdmobMediumRectAds(Activity activity, String mediumrectId) {
        this.activity = activity;
        this.mediumrectId = mediumrectId;
    }

    @Override

    public MediumRectAd mediumrect() {
        CompletableFuture<MediumRectAd> result = new CompletableFuture<>();
        new Huxter.InlineAd(activity, mediumrectId, Huxter.AdFormat.MediumRect, new Huxter.InlineAd.Listener() {
            @Override
            public void onReady(String network, View adView) {
                result.complete(new AdmobMediumRectAd(adView));
            }

            @Override
            public void onFail(Huxter.AdError error) {
                result.completeExceptionally(new Exception(error.toString()));
            }
        }).load();
        return new AsyncMediumRectAd(result);
    }
}
