package com.softmq.guide.app.items;

import androidx.databinding.ViewDataBinding;

import com.github.nitrico.lastadapter.ItemType;
import com.softmq.guide.R;
import com.softmq.guide.app.App;
import com.softmq.guide.databinding.ItemCardBinding;

public class ListItemLayout {
    private final App app;

    public ListItemLayout(App app) {
        this.app = app;

    }

    public int asId() {
        switch (app.config().activities().list().item().type()) {
            case "text":
                return R.layout.item_text;
            case "image":
                return R.layout.item_image;
            default:
                return R.layout.item_card;
        }
    }

    public ItemType<? extends ViewDataBinding> asType() {
        switch (app.config().activities().list().item().type()) {
            case "text":
                new ItemType<ItemCardBinding>(R.layout.item_text) {

                };
            case "image":
                return new ItemType<ItemCardBinding>(R.layout.item_image);
            default:
                return new ItemType<ItemCardBinding>(R.layout.item_card);
        }
    }
}
