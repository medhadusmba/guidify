package com.softmq.guide.app.items;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.github.nitrico.lastadapter.ItemType;
import com.softmq.guide.app.Config;
import com.softmq.guide.app.common.ui.Showable;

import java.util.Iterator;
import java.util.function.BiFunction;

import java9.util.concurrent.CompletableFuture;

public class SmartAppItems implements AppItems, Showable {
    private ShowableItems items;

    public SmartAppItems(View view, ItemListener itemClickListener, int itemLayout) {
        if (Config.online) {
            Log.d("todo", "online items");
            items = new ShowedOnlineItems(view, itemClickListener, itemLayout);
        } else {
            Log.d("todo", "offline items");
            items = new ShowedOfflineItems(view, itemClickListener, itemLayout);
        }
    }

    public SmartAppItems(View view, ItemListener itemClickListener, BiFunction<Object, Integer, ItemType<? extends ViewDataBinding>> itemLayout) {
        if (Config.online) {
            Log.d("todo", "online items");
            items = new ShowedOnlineItems(view, itemClickListener, itemLayout);
        } else if (Config.offline) {
            Log.d("todo", "offline items");
            items = new ShowedOfflineItems(view, itemClickListener, itemLayout);
        }
    }



    public void show() {
        items.show();
    }

    @Override
    public CompletableFuture<Void> read() {
        return items.read();
    }

    @Override
    public int size() {
        return items.size();
    }


    @NonNull
    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }

    public Item get(int i) {
        return items.get(i);
    }
}
