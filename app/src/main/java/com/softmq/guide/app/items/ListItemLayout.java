package com.softmq.guide.app.items;

import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;

import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.softmq.guide.app.R;
import com.softmq.guide.app.App;
import com.softmq.guide.app.databinding.ItemCardBinding;
import com.softmq.guide.app.databinding.ListItemNativeAdBinding;

import org.jetbrains.annotations.NotNull;

public class ListItemLayout {
    private final App app;
    private final Item item;
    private final Integer position;

    public ListItemLayout(App app, Item item, Integer position) {
        this.app = app;

        this.item = item;
        this.position = position;
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
        if (item.getType().equals(Item.TYPE_NATIVE_AD)){
            return new ItemType<ListItemNativeAdBinding>(R.layout.list_item_native_ad) {
                @Override
                public void onBind(@NotNull Holder<ListItemNativeAdBinding> holder) {

                    ViewGroup itemNativeAd=holder.getBinding().itemNativeAd;
                    if(itemNativeAd.getChildCount()==0){
                       itemNativeAd.removeAllViews();
                        app.ads().natives().nativeAd().show(itemNativeAd).thenRun(()->itemNativeAd.setVisibility(View.VISIBLE));
                    }
                }
            };
        }
        switch (app.config().activities().list().item().type()) {
            case "text":
                new ItemType<ItemCardBinding>(R.layout.item_text);
            case "image":
                return new ItemType<ItemCardBinding>(R.layout.item_image);
            default:
                return new ItemType<ItemCardBinding>(R.layout.item_card);
        }
    }
}
