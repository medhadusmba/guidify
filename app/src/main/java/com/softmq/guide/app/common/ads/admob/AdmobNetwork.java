package com.softmq.guide.app.common.ads.admob;

import android.app.Activity;

import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.common.ads.core.BaseAds;
import com.softmq.huxter.admob.AdmobAdapter;
import com.softmq.huxter.core.Huxter;

import org.jetbrains.annotations.NotNull;

import java9.util.concurrent.CompletableFuture;

public class AdmobNetwork extends BaseAds {
    private final Activity activity;
    private final AdsConfig config;

    public AdmobNetwork(Activity activity, AdsConfig config) {
        super(new AdmobBannerAds(activity, config.admob().getAdmobBannerUnitId()),
                new RotatedAdmobInterstitialAds(activity, config.admob().interstitialAdUnitId()),
                new AdmobNativeAds(activity, config.admob().getAdmobNativeUnitId()), new AdmobMediumRectAds(activity, config.admob().getAdmobMediumRectId()), new AdmobRewardedAds(activity, config.admob().getAdmobRewardedVideoUnitId()));
        this.activity = activity;
        this.config = config;
    }

    @NotNull
    @Override
    public CompletableFuture<Void> initialize() {
        CompletableFuture<Void> result = new CompletableFuture<>();
        Huxter.registerAdapter(new AdmobAdapter());
        Huxter.getInstance().initialize(new Huxter.Config.Builder(activity, "").build(), new Huxter.InitializationListener() {
            @Override
            public void onComplete() {
                result.complete(null);
            }

            @Override
            public void onFail(Huxter.InitializationError error) {
                result.completeExceptionally(new Exception("admob init error"));
            }
        });

        return result;
    }

    @NotNull
    @Override
    public Boolean isInitialized() {
        return Huxter.getInstance().isInitialized();
    }

}
