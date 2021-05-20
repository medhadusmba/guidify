package com.softmq.guide.app.items;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.softmq.guide.BR;
import com.softmq.guide.R;
import com.softmq.guide.app.common.ui.Showable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import java9.util.concurrent.CompletableFuture;

public class ShowedItemList extends ItemList implements Showable {
    private final LastAdapter adapter;
    private final ItemList items;
    private final RecyclerView view;


    public ShowedItemList(ItemList items, RecyclerView view, int itemLayout) {
        super(items);
        this.items = items;
        this.view = view;
        adapter = new LastAdapter(items, BR.item)
                .map(Item.class, itemLayout);

    }

    public ShowedItemList(ItemList items, RecyclerView view, BiFunction<Object, Integer, ItemType<? extends ViewDataBinding>> itemType) {
        super(items);
        this.items = items;
        this.view = view;

        adapter = new LastAdapter(items, BR.item)
                .type(itemType::apply);

    }

    public ShowedItemList(List<Item> items, RecyclerView view, int itemLayout) {
        this(new ItemList(items), view, R.layout.item_card);
    }

    public ShowedItemList(List<Item> items, RecyclerView view) {
        this(items, view, R.layout.item_card);
    }

    public ShowedItemList(RecyclerView view) {
        this(new ArrayList<>(), view);

    }

    public ShowedItemList(RecyclerView view, int itemLayout) {
        this(new ArrayList<>(), view, itemLayout);
    }

    @Override
    public CompletableFuture<Void> read() {
        return items.read().thenRun(this::show);
    }

    @Override
    public void show() {
        adapter.into(view);
        adapter.notifyDataSetChanged();
    }
}
