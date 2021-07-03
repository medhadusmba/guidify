package com.softmq.guide.app.common.ads.admob;

import android.app.Activity;
import android.util.Log;

import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;

import java.util.List;
import java.util.stream.Collectors;

public class RotatedAdmobInterstitialAds implements InterstitialAdSource {
    private static int current = 0;
    private final List<AdmobInterstitialAds> interstitialAds;

    public RotatedAdmobInterstitialAds(Activity activity, List<String> interstitialAdUnitId) {
        interstitialAds = interstitialAdUnitId.stream().map(s -> new AdmobInterstitialAds(activity, s)).collect(Collectors.toList());
    }

    @Override
    public InterstitialAd interstitialAd() {
        if (current >= interstitialAds.size()) {
            current = 0;
        }
        InterstitialAd result;
        result = interstitialAds.get(current).interstitialAd();
        Log.d("todo", "admob: interstitial number:" + current);
        current++;
        return result;
    }
}
