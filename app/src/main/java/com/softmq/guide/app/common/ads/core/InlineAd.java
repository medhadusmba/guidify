package com.softmq.guide.app.common.ads.core;

import android.view.ViewGroup;

import com.softmq.guide.app.common.ads.core.placements.InlineAdPlacement;

import java9.util.concurrent.CompletableFuture;

public interface InlineAd extends Ad {
    default CompletableFuture<Void> show(ViewGroup placement) {
        return show(new InlineAdPlacement(placement));
    }

    default CompletableFuture<Void> showNow(ViewGroup placement) {
        return showNow(new InlineAdPlacement(placement));
    }

}
