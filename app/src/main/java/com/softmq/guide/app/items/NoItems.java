package com.softmq.guide.app.items;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.function.Consumer;

import java9.util.concurrent.CompletableFuture;

public class NoItems implements AppItems {
    @Override
    public CompletableFuture<Void> read() {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public int size() {
        return 0;
    }

    @NonNull
    @Override
    public Iterator<Item> iterator() {
        return Collections.emptyIterator();
    }
}
