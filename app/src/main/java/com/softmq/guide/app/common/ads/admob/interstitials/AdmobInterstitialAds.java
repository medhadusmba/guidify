package com.softmq.guide.app.common.ads.admob.interstitials;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.softmq.guide.app.common.ads.core.interstitials.AsyncInterstitialAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;

import java9.util.concurrent.CompletableFuture;

public class AdmobInterstitialAds implements InterstitialAdSource {
    private final Activity activity;
    private final String id;

    public AdmobInterstitialAds(Activity activity, String id) {
        this.activity = activity;
        this.id = id;
    }

    @Override
    public InterstitialAd interstitialAd() {
        CompletableFuture<InterstitialAd> result = new CompletableFuture<>();
//        com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd = new com.google.android.gms.ads.interstitial.InterstitialAd(context);
//        interstitialAd.setAdUnitId(id);
//        interstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                result.complete(new AdmobInterstitialAd(interstitialAd));
//            }
//
//            @Override
//            public void onAdFailedToLoad(LoadAdError loadAdError) {
//                result.completeExceptionally(new Exception("admob: interstitial failed to load with error: " + loadAdError.getMessage()));
//
//            }
//
//        });
//        interstitialAd.loadAd(new AdRequest.Builder().build());
        AdRequest adRequest = new AdRequest.Builder().build();

        com.google.android.gms.ads.interstitial.InterstitialAd.load(activity.getApplicationContext(), id, adRequest, new com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                result.complete(new AdmobInterstitialAd(interstitialAd, activity));
                Log.i("todo", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i("todo", loadAdError.getMessage());
                result.completeExceptionally(new Exception("admob: interstitial failed to load with error: " + loadAdError.getMessage()));

            }
        });
        return new AsyncInterstitialAd(result);
    }
}
