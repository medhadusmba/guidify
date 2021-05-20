package com.softmq.guide.app.items;

import android.content.Context;
import android.util.Log;

import com.softmq.guide.app.common.data.json.OfflineJson;

import org.json.JSONException;

import java.io.IOException;

import java9.util.concurrent.CompletableFuture;

public class OfflineItemList extends ItemList implements AppItems {
    private final ItemListener callback;
    private final OfflineJson data;

    public OfflineItemList(Context context, ItemListener clickListener) {
        this.callback = clickListener;
        data = new OfflineJson(context, "offline/config.json", "data");
    }

    @Override
    public CompletableFuture<Void> read() {
        Log.d("todo", "reading offline items");
        try {
            JsonItemList items = new JsonItemList(data.asJson().getJSONArray("items"), callback);
            CompletableFuture<Void> result = items.read();
            addAll(items);
            return result;

        } catch (final JSONException | IOException e) {
            Log.e("todo", "Json offline error: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

}
