package com.softmq.guide.app.common.ads.core.placements;

import android.view.View;
import android.view.ViewGroup;

public class InlineAdPlacement implements AdPlacement {
    private final ViewGroup origin;

    public InlineAdPlacement(ViewGroup origin) {
        this.origin = origin;
    }


    @Override
    public void show(View ad) {
        origin.addView(ad);
        origin.setVisibility(View.VISIBLE);
        ad.setVisibility(View.VISIBLE);
    }

    @Override
    public void show() {
    }

}
