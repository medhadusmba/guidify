package com.softmq.guide.app.common.ads.admob.interstitials;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class AdmobInterstitialAd implements InterstitialAd {

    private final com.google.android.gms.ads.interstitial.InterstitialAd origin;
    private Activity activity;

    public AdmobInterstitialAd(com.google.android.gms.ads.interstitial.InterstitialAd origin, Activity activity) {

        this.origin = origin;
        this.activity = activity;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        CompletableFuture<Void> result = new CompletableFuture<>();
//        origin.setAdListener(new AdListener() {
//            @Override
//            public void onAdClosed() {
//                result.complete(null);
//            }
//        });
//        origin.show();
        origin.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when fullscreen content is dismissed.
                Log.d("TAG", "The ad was dismissed.");
                result.complete(null);
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when fullscreen content failed to show.
                Log.d("TAG", "The ad failed to show.");
                result.completeExceptionally(new Exception(adError.getMessage()));
            }

            @Override
            public void onAdShowedFullScreenContent() {
                // Called when fullscreen content is shown.
                // Make sure to set your reference to null so you don't
                // show it a second time.
                Log.d("TAG", "The ad was shown.");
            }
        });
        origin.show(activity);
        return result;
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
