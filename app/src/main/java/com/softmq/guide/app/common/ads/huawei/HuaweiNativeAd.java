package com.softmq.guide.app.common.ads.huawei;


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

public class HuaweiNativeAd implements NativeAd {
    private Context context;
    private com.huawei.hms.ads.nativead.NativeAd origin;

    public HuaweiNativeAd(Context context, com.huawei.hms.ads.nativead.NativeAd origin) {
        this.context = context;
        this.origin = origin;
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        placement.show(asView());
        return CompletableFuture.completedFuture(null);
    }

    private View asView() {
        NativeView nativeView = (NativeView) View.inflate(context, R.layout.ads_native_huawei, null);
        nativeView.setTitleView(nativeView.findViewById(R.id.ad_title));
        ((TextView) nativeView.getTitleView()).setText(origin.getTitle());
        // Register and populate the multimedia view.
        nativeView.setMediaView((MediaView) nativeView.findViewById(R.id.ad_media));
        nativeView.getMediaView().setMediaContent(origin.getMediaContent());
        // Register and populate other asset views.
        nativeView.setAdSourceView(nativeView.findViewById(R.id.ad_source));
        ((TextView) nativeView.getAdSourceView()).setText(origin.getAdSource());
        nativeView.setCallToActionView(nativeView.findViewById(R.id.ad_call_to_action));
        ((Button) nativeView.getCallToActionView()).setText(origin.getCallToAction());
        // Register the native ad object.
        nativeView.setNativeAd(origin);
        return nativeView;
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
