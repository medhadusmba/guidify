package com.softmq.guide.app.common.ui;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

public class LoadedView {
    private final View loader;
    private final long delayMillis;
    private final View view;

    public LoadedView(View view, View loader, long delayMillis) {
        this.view = view;
        this.loader = loader;
        this.delayMillis = delayMillis;
    }

    public void load() {
        loader.setVisibility(View.VISIBLE);
        view.setVisibility(View.GONE);
        new Handler(Looper.getMainLooper()).postDelayed(
                () -> {
                    loader.setVisibility(View.GONE);
                    view.setVisibility(View.VISIBLE);
                }, delayMillis
        );
    }
}
