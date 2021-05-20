package com.softmq.guide.app.common.ads.core.interstitials;

public class NoInterstitialAds implements InterstitialAdSource {
    @Override
    public InterstitialAd interstitialAd() {
        return new NoInterstitialAd();
    }
}
