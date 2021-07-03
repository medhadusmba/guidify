package com.softmq.guide.app.common.ads.core;

import java9.util.concurrent.CompletableFuture;

public interface AdSource  {
    default CompletableFuture<? extends Ad> ad(){
        return CompletableFuture.completedFuture(new NoAd());
    }
}
