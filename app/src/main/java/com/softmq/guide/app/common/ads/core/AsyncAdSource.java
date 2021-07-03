package com.softmq.guide.app.common.ads.core;

import java9.util.concurrent.CompletableFuture;

public interface AsyncAdSource extends AdSource {
    default CompletableFuture<? extends Ad> ad(){
        return null;
    }
}
