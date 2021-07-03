package com.softmq.guide.app.common.ads.unity;

import android.app.Activity;

import com.softmq.guide.app.BuildConfig;
import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.common.ads.core.BaseAds;
import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;
import com.softmq.guide.app.common.ads.unity.banners.UnityBannerAds;
import com.softmq.guide.app.common.ads.unity.interstitials.UnityInterstitialAds;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.UnityAds;

import org.jetbrains.annotations.NotNull;

import java9.util.concurrent.CompletableFuture;

public class UnityNetwork extends BaseAds {
    private final Activity activity;
    private final AdsConfig config;

    public UnityNetwork(Activity activity, AdsConfig config) {
        super(new UnityBannerAds(activity, config.unity()), new UnityInterstitialAds(activity, config.unity()));
        this.activity = activity;
        this.config = config;
    }

    @NotNull
    @Override
    public CompletableFuture<Void> initialize() {
        CompletableFuture<Void> result = new CompletableFuture<>();

        UnityAds.initialize(activity.getApplicationContext(), config.unity().getUnityGameId(), BuildConfig.DEBUG, true, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                result.complete(null);
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {
                result.completeExceptionally(new Exception("unity: failed to initialize with error:" + message));
            }
        });

        return result;
    }

    @NotNull
    @Override
    public Boolean isInitialized() {
        return UnityAds.isInitialized();
    }

    @Override
    public BannerAdSource banners() {
        return new UnityBannerAds(activity, config.unity());
    }

    @Override
    public InterstitialAdSource interstitials() {
        return new UnityInterstitialAds(activity, config.unity());
    }
}
