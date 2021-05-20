package com.softmq.guide.app.items;

import android.content.Context;

import com.softmq.guide.app.Config;
import com.softmq.guide.app.common.data.json.OnlineJson;

import org.json.JSONException;

import java9.util.concurrent.CompletableFuture;

public class OnlineItemList extends ItemList implements AppItems {
    private final OnlineJson json;
    private final ItemListener itemClickListener;

    public OnlineItemList(Context context, ItemListener itemClickListener) {
        super();
        this.json = new OnlineJson(context, Config.url, "data");
        this.itemClickListener = itemClickListener;
    }

    @Override
    public CompletableFuture<Void> read() {
        return json.read().thenCompose((json) -> {
            try {
                JsonItemList items = new JsonItemList(json.getJSONArray("items"), itemClickListener);
                CompletableFuture<Void> result = items.read();
                addAll(items);
                return result;
            } catch (JSONException e) {
                e.printStackTrace();
                return CompletableFuture.failedFuture(e);
            }
        });
    }
}
