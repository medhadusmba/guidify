package com.softmq.guide.app.items;

import java9.util.concurrent.CompletableFuture;

public interface AppItems extends Iterable<Item> {
    CompletableFuture<Void> read();

}
