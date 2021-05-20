package com.softmq.guide.app.common.ads.unity.banners;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.softmq.guide.app.common.ads.core.banners.AsyncBannerAd;
import com.softmq.guide.app.common.ads.core.banners.BannerAd;
import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;
import com.softmq.guide.app.common.ads.unity.UnityConfig;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;

import java9.util.concurrent.CompletableFuture;

public class UnityBannerAds implements BannerAdSource {
    private final Activity activity;
    private final UnityConfig config;

    public UnityBannerAds(Activity activity, UnityConfig config) {
        this.activity = activity;
        this.config = config;
    }

    @Override
    public BannerAd bannerAd() {
        CompletableFuture<BannerAd> result = new CompletableFuture<>();
        UnityBanners.setBannerListener(new IUnityBannerListener() {
            @Override
            public void onUnityBannerLoaded(String placementId, View view) {
                // When the banner content loads, add it to the view hierarchy:
                if (placementId.equals(config.getUnityAdBannerUnitId())) {
                    result.complete(new UnityBannerAd(view));
                }
                Log.d("todo", "unity: banner loaded successfully");
            }

            @Override
            public void onUnityBannerUnloaded(String placementId) {
                // When the banner’s no longer in use, remove it from the view hierarchy:


            }

            @Override
            public void onUnityBannerShow(String placementId) {

            }

            @Override
            public void onUnityBannerClick(String placementId) {
                // Called when the banner is clicked.
            }

            @Override
            public void onUnityBannerHide(String placementId) {

            }

            @Override
            public void onUnityBannerError(String message) {
                result.completeExceptionally(new Exception("unity: banner failed to load with error message: " + message));
                Log.d("todo", "unity: banner failed to load");
                // Called when an error occurred, and the banner failed to load or show.
            }
        });
        //    UnityAds.setDebugMode(true);
        UnityBanners.loadBanner(activity, config.getUnityAdBannerUnitId());
        return new AsyncBannerAd(result);
    }
}
