package com.softmq.guide.app.common.ads.facebook.mediumrect;

import android.content.Context;

import com.facebook.ads.AdSize;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAd;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAdSource;
import com.softmq.guide.app.common.ads.facebook.FacebookConfig;
import com.softmq.guide.app.common.ads.facebook.banners.FacebookBannerAds;

public class FacebookMediumRectAds implements MediumRectAdSource {

    private final FacebookBannerAds origin;

    public FacebookMediumRectAds(Context context, FacebookConfig config) {
        origin = new FacebookBannerAds(context, config.getFacebookMediumRectId(), AdSize.RECTANGLE_HEIGHT_250);
    }

    @Override
    public MediumRectAd mediumrect() {
        return new FacebookMediumRectAd(origin.bannerAd());
    }
}
