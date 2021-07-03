package com.softmq.guide.app.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.softmq.guide.app.R;
import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class ScrollableNativeAd implements NativeAd {
    private final NativeAd nativeAd;
    private final Context context;

    public ScrollableNativeAd(NativeAd nativeAd, Context context) {
        this.nativeAd = nativeAd;
        this.context = context;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        View parent = View.inflate(context, R.layout.app_ads_native, null);
        parent.findViewById(R.id.app_ads_native_scrollview).setVisibility(View.VISIBLE);
        ViewGroup child = parent.findViewById(R.id.app_ads_native_framelayout);
        placement.show(parent);
        return nativeAd.show(child);
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return nativeAd.showNow(placement);
    }

}
