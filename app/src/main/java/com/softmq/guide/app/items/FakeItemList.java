package com.softmq.guide.app.items;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.nitrico.lastadapter.BR;
import com.github.nitrico.lastadapter.LastAdapter;
import com.softmq.guide.app.R;
import com.softmq.guide.app.common.data.fake.FakeGenerator;

import java.util.ArrayList;
import java.util.List;

public class FakeItemList {
    private final List<Item> items;
    private final LastAdapter adapter;
    private final RecyclerView view;
    private final FakeGenerator fake;
    private final Activity activity;
    private final ItemListener callback;
    private final int number;

    public FakeItemList(int number, Activity activity, RecyclerView view, ItemListener callback) {
        this.number = number;
        this.activity = activity;
        this.callback = callback;
        this.items = new ArrayList<>();
        this.adapter = new LastAdapter(this.items, BR.item).map(Item.class, R.layout.item_card);
        this.view = view;
        view.setLayoutManager(new LinearLayoutManager(activity));
        this.fake = new FakeGenerator();
    }

    public void show() {
        items.addAll(generateItems());
        adapter.into(view);
    }

    private List<Item> generateItems() {
        for (int i = 0; i < number; i++) {
            Item item = new Item(fake.title(), fake.paragraph(), fake.image(), callback);
            items.add(item);
        }
        return items;
    }
}
