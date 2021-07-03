package com.softmq.guide.app.items;

import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.softmq.guide.app.BR;
import com.softmq.guide.app.R;
import com.softmq.guide.app.common.ui.Showable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java9.util.concurrent.CompletableFuture;

public class ShowedItemList extends ItemList implements Showable {
    private LastAdapter adapter;
    private final ItemList items;
    private final View view;



    public ShowedItemList(ItemList items, View view, int itemLayout) {
        super(items);
        this.items = items;
        this.view = view;
        adapter = new LastAdapter(items, BR.item)
                .map(Item.class, itemLayout);

    }

    public ShowedItemList(ItemList items, View view, BiFunction<Object, Integer, ItemType<? extends ViewDataBinding>> itemType) {
        super(items);
        this.items = items;
        this.view = view;
        adapter = new LastAdapter(items, BR.item)
                .type(itemType::apply);

    }

    public ShowedItemList(List<Item> items, RecyclerView view, int itemLayout) {
        this(new ItemList(items),(View) view, R.layout.item_card);
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
        if (view instanceof RecyclerView) {
            adapter.into((RecyclerView) view);
        } else if (view instanceof ViewPager2){

            ( (ViewPager2)view).setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public int size() {
        return items.size();
    }
}
