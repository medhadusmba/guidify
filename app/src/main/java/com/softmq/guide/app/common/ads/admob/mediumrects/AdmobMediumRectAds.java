package com.softmq.guide.app.common.ads.admob.mediumrects;

import android.app.Activity;

import com.google.android.gms.ads.AdSize;
import com.softmq.guide.app.common.ads.admob.AdmobConfig;
import com.softmq.guide.app.common.ads.admob.banners.AdmobBannerAds;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAd;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAdSource;

public class AdmobMediumRectAds implements MediumRectAdSource {

    private final AdmobBannerAds origin;

    public AdmobMediumRectAds(Activity activity, AdmobConfig config) {
        this.origin = new AdmobBannerAds(activity, config.getAdmobMediumRectId(), AdSize.MEDIUM_RECTANGLE);
    }

    @Override
    public MediumRectAd mediumrect() {
        return new AdmobMediumRectAd(this.origin.bannerAd());
    }
}
