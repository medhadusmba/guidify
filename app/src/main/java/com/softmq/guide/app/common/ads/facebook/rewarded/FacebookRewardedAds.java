package com.softmq.guide.app.common.ads.facebook.rewarded;

import android.content.Context;
import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.softmq.guide.app.common.ads.core.rewarded.AsyncRewardedAd;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAd;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAdSource;
import com.softmq.guide.app.common.ads.facebook.FacebookConfig;

import java9.util.concurrent.CompletableFuture;

public class FacebookRewardedAds implements RewardedAdSource {
    private final Context context;
    private final FacebookConfig config;

    public FacebookRewardedAds(Context context, FacebookConfig config) {
        this.context = context;
        this.config = config;
    }

    @Override
    public RewardedAd rewardedAd() {
        CompletableFuture<RewardedAd> result = new CompletableFuture<>();
        CompletableFuture<Void> onRewarded=new CompletableFuture<>();
        RewardedVideoAd rewardedVideoAd = new RewardedVideoAd(context, config.getFacebookRewardVideoUnitId());
        RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
            @Override
            public void onError(Ad ad, AdError error) {
                // Rewarded video ad failed to load
                Log.e("todo", "Rewarded video ad failed to load: " + error.getErrorMessage());
                result.completeExceptionally(new Exception(error.getErrorCode()+": "+error.getErrorMessage()));
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Rewarded video ad is loaded and ready to be displayed
                Log.d("todo", "Rewarded video ad is loaded and ready to be displayed!");
                result.complete(new FacebookRewardedAd(rewardedVideoAd, onRewarded));
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Rewarded video ad clicked
                Log.d("todo", "Rewarded video ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Rewarded Video ad impression - the event will fire when the
                // video starts playing
                Log.d("todo", "Rewarded video ad impression logged!");
            }

            @Override
            public void onRewardedVideoCompleted() {
                // Rewarded Video View Complete - the video has been played to the end.
                // You can use this event to initialize your reward
                Log.d("todo", "Rewarded video completed!");

                onRewarded.complete(null);
            }

            @Override
            public void onRewardedVideoClosed() {
                // The Rewarded Video ad was closed - this can occur during the video
                // by closing the app, or closing the end card.
                Log.d("todo", "Rewarded video ad closed!");
                result.completeExceptionally(new Exception("closed"));

            }
        };
        rewardedVideoAd.loadAd(
                rewardedVideoAd.buildLoadAdConfig()
                        .withAdListener(rewardedVideoAdListener)
                        .build());
        return new AsyncRewardedAd(result);
    }
}
