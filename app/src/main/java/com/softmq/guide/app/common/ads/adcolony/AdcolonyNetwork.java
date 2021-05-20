package com.softmq.guide.app.common.ads.adcolony;

import android.app.Activity;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAppOptions;
import com.softmq.guide.app.common.ads.adcolony.banners.AdcolonyBannerAds;
import com.softmq.guide.app.common.ads.adcolony.interstitials.AdcolonyInterstitialAds;
import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.common.ads.core.DefaultAds;

import org.jetbrains.annotations.NotNull;

import java9.util.concurrent.CompletableFuture;

public class AdcolonyNetwork extends DefaultAds {
    private static Boolean initialized = false;
    private final Activity activity;
    private final AdsConfig config;

    public AdcolonyNetwork(Activity activity, AdsConfig config) {
        super(new AdcolonyBannerAds(activity.getApplicationContext(), config.adcolony()), new AdcolonyInterstitialAds(config.adcolony()));
        this.activity = activity;
        this.config = config;
    }

    @NotNull
    @Override
    public CompletableFuture<Void> initialize() {
        AdColonyAppOptions appOptions = new AdColonyAppOptions();
        appOptions.setKeepScreenOn(true);
        appOptions.setPrivacyFrameworkRequired(AdColonyAppOptions.GDPR, true);
        appOptions.setPrivacyConsentString(AdColonyAppOptions.GDPR, "1");
        appOptions.setPrivacyConsentString(AdColonyAppOptions.CCPA, "1");
        AdColony.configure(activity, appOptions, config.adcolony().getAdcolonyAppId(), config.adcolony().adcolonyInterstitialId(), config.adcolony().adcolonyBannerId(), config.adcolony().adcolonyRewardedId());
        initialized = true;
        return CompletableFuture.completedFuture(null);
    }

    @NotNull
    @Override
    public Boolean isInitialized() {
        return initialized;
    }


}
