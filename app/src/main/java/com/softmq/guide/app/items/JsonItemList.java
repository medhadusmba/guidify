package com.softmq.guide.app.items;


import org.json.JSONArray;
import org.json.JSONException;

import java9.util.concurrent.CompletableFuture;

public class JsonItemList extends ItemList {
    private final JSONArray json;
    private final ItemListener itemClickListener;

    public JsonItemList(JSONArray json, ItemListener itemClickListener) {
        this.json = json;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public CompletableFuture<Void> read() {
        try {
            for (int i = 0; i < json.length(); i++) {
                add(get(i));
            }
            return CompletableFuture.completedFuture(null);
        } catch (Throwable t) {
            return CompletableFuture.failedFuture(t);
        }
    }

    @Override
    public int size() {
        return json.length();
    }

    public Item get(int position) {
        try {
            return new JsonItem(json.getJSONObject(position), itemClickListener).asItem();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new NoItem();
    }

}