package com.softmq.guide.app.items;

import android.view.View;
import android.view.ViewGroup;

import com.softmq.guide.app.R;
import com.softmq.guide.app.App;
import com.softmq.guide.app.ItemActivity;
import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.guide.app.common.core.Delayed;

public class LoadedItem {

    private final App app;
    private final View loader;
    private final Item item;
    private final NativeAd nativeAd;
    private boolean notLoading;

    public LoadedItem(App app, View loader, Item item) {
        this.app = app;
        this.loader = loader;
        this.item = item;
        this.notLoading = true;
        loader.setOnTouchListener((v, event) -> true);
        nativeAd = app.ads().natives().nativeAd();

    }

    public void show() {
        if (app.config().activities().list().item().loader().isEnabled()) {
            final long WAIT_TIME_LOADER = 500;
            final long WAIT_TIME_ITEMS = app.config().activities().list().item().loader().duration();
            notLoading = false;
            loader.setVisibility(View.VISIBLE);
            loader.animate().alpha(1).setDuration(WAIT_TIME_LOADER);
            if (WAIT_TIME_ITEMS > 1000) {
                nativeAd.show((ViewGroup) loader.findViewById(R.id.app_ad_natives));
            }

            new Delayed(WAIT_TIME_ITEMS, () -> {
                loader.animate().alpha(0).setDuration(WAIT_TIME_LOADER);
                new Delayed(WAIT_TIME_LOADER, this::showItem).run();
            }).run();
        } else {
            showItem();
        }

    }

    private void showItem() {
        loader.setVisibility(View.GONE);
        app.navigator().navigateTo(ItemActivity.class, new BundleItem(item).asBundle());
        notLoading = true;
    }

    public boolean isNotLoading() {
        return notLoading;
    }
}
