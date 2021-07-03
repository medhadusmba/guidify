package com.softmq.guide.app.common.ads.mopub;


import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.huawei.hms.ads.nativead.MediaView;
import com.huawei.hms.ads.nativead.NativeView;
import com.softmq.guide.app.R;
import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class MopubNativeAd implements NativeAd {


    private View adView;

    public MopubNativeAd(View adView) {

        this.adView = adView;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        placement.show(adView);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
