package com.softmq.guide.app.items;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.Iterator;

import java9.util.concurrent.CompletableFuture;

public class NoItems implements AppItems {
    @Override
    public CompletableFuture<Void> read() {
        return CompletableFuture.completedFuture(null);
    }

    @NonNull
    @Override
    public Iterator<Item> iterator() {
        return Collections.emptyIterator();
    }
}
