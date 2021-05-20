package com.softmq.guide.app.common.ads.core.placements;

import android.view.View;
import android.view.ViewGroup;

/**
 * Using Visitor design pattern
 */
public class MixedAdPlacements implements AdPlacement {
    private final InlineAdPlacement inline;
    private final StandaloneAdPlacement standalone;

    public MixedAdPlacements(ViewGroup inlineAd) {
        this(new InlineAdPlacement(inlineAd), new StandaloneAdPlacement());
    }

    public MixedAdPlacements(InlineAdPlacement inline, StandaloneAdPlacement standalone) {
        this.inline = inline;
        this.standalone = standalone;
    }

    @Override
    public void show(View ad) {
        inline.show(ad);
    }

    @Override
    public void show() {
        standalone.show();
    }
}
