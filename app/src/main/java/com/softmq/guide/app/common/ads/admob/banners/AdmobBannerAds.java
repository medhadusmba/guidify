package com.softmq.guide.app.common.ads.admob.banners;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.softmq.guide.app.common.ads.admob.AdmobConfig;
import com.softmq.guide.app.common.ads.core.banners.AsyncBannerAd;
import com.softmq.guide.app.common.ads.core.banners.BannerAd;
import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;

import org.jetbrains.annotations.NotNull;

import java9.util.concurrent.CompletableFuture;

public class AdmobBannerAds implements BannerAdSource {
    private final Context context;
    private final String id;
    private final AdSize size;

    public AdmobBannerAds(Context context, AdmobConfig config) {
        this.context = context;
        this.id = config.getAdmobBannerUnitId();
        this.size = AdSize.BANNER;
    }

    public AdmobBannerAds(Context context, AdmobConfig config, AdSize size) {
        this.context = context;
        this.id = config.getAdmobBannerUnitId();
        this.size = size;
    }

    public AdmobBannerAds(Context context, String id, AdSize size) {
        this.context = context;
        this.id = id;
        this.size = size;
    }

    @Override
    public BannerAd bannerAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        CompletableFuture<BannerAd> result = new CompletableFuture<>();
        AdView adView = new AdView(context);
        adView.setBackgroundColor(Color.TRANSPARENT);
        adView.setAdUnitId(id);
        adView.setAdSize(size);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                result.complete(new AdmobBannerAd(adView));
            }

            @Override
            public void onAdFailedToLoad(@NotNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }
        });

        adView.setVisibility(View.INVISIBLE);
        adView.loadAd(adRequest);
        return new AsyncBannerAd(result);
    }
}
