package com.softmq.guide.app.items;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.github.nitrico.lastadapter.ItemType;
import com.softmq.guide.R;

import java.util.Iterator;
import java.util.function.BiFunction;

import java9.util.concurrent.CompletableFuture;

public class ShowedOfflineItems implements ShowableItems {
    private final ShowedItemList showed;

    public ShowedOfflineItems(RecyclerView view, ItemListener clicked) {
        this(view, clicked, R.layout.item_card);
    }

    public ShowedOfflineItems(RecyclerView view, ItemListener itemClickListener, int itemLayout) {
        this.showed = new ShowedItemList(new OfflineItemList(view.getContext(), itemClickListener), view, itemLayout);
    }

    public ShowedOfflineItems(RecyclerView view, ItemListener itemClickListener, BiFunction<Object, Integer, ItemType<? extends ViewDataBinding>> itemLayout) {
        this.showed = new ShowedItemList(new OfflineItemList(view.getContext(), itemClickListener), view, itemLayout);
    }

    public CompletableFuture<Void> read() {
        return showed.read();
    }

    @Override
    public void show() {
        showed.show();
    }

    @NonNull
    @Override
    public Iterator<Item> iterator() {
        return showed.iterator();
    }

    @Override
    public Item get(int i) {
        return showed.get(i);
    }
}
