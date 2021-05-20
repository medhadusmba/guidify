package com.softmq.guide.app.items;

import java.util.ArrayList;
import java.util.List;

import java9.util.concurrent.CompletableFuture;

public class ItemList extends ArrayList<Item> implements AppItems {
    private AppItems origin;

    public ItemList() {
        super();
    }


    public ItemList(List<Item> items) {
        super(items);
    }

    public ItemList(ItemList items) {
        super(items);
        origin = items;
    }


    @Override
    public CompletableFuture<Void> read() {
        if (origin != null) {
            return origin.read();
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public Item get(int index) {
        if (origin != null) {
            return null;
        }
        return super.get(index);
    }
}
