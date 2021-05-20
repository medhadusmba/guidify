package com.softmq.guide.app.common.ads.inhouse;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class InHouseNativeAd implements NativeAd {
    private final Context context;
    private final String title;

    public InHouseNativeAd(Context context, String title) {
        this.context = context;

        this.title = title;
    }

    @NonNull
    public View asView() {
        TextView result = new TextView(context);
        result.setText(title);
        return result;
    }

    public void close() {

    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        return null;
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return null;
    }
}
