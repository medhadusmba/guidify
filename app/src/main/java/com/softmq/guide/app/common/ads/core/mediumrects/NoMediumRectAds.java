package com.softmq.guide.app.common.ads.core.mediumrects;

public class NoMediumRectAds implements MediumRectAdSource {

    @Override
    public MediumRectAd mediumrect() {
        return new NoMediumRectAd();
    }
}
