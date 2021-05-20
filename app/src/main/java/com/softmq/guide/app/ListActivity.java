package com.softmq.guide.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.softmq.guide.R;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.items.Item;
import com.softmq.guide.app.items.ListItemLayout;
import com.softmq.guide.app.items.LoadedItem;
import com.softmq.guide.app.items.SmartAppItems;
import com.softmq.guide.databinding.ActivityListBinding;
import com.softmq.guide.databinding.ItemNativeAdBinding;

import org.jetbrains.annotations.NotNull;

public class ListActivity extends AppCompatActivity {

    private LoadedItem loader;
    private ActivityListBinding binding;
    private App app;
    private InterstitialAd interstitialAd;
    private int itemClikCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.activityList.setLayoutManager(new GridLayoutManager(this, 1));
        app = new App(this);

        itemClikCount = 1;
        SmartAppItems items = app.items(binding.activityList, this::showLoader, (item, position) -> {
            if (((Item) item).getType().equalsIgnoreCase("native_ad")) {
                return new ItemType<ItemNativeAdBinding>(R.layout.item_native_ad) {
                    @Override
                    public void onBind(@NotNull Holder<ItemNativeAdBinding> holder) {
                        holder.getBinding().appAdNatives.removeAllViews();
                        app.ads().natives().nativeAd().show(holder.getBinding().appAdNatives);
                    }

                };
            } else {
                return new ListItemLayout(app).asType();
            }
        });
        items.read();
        items.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        interstitialAd = app.ads().interstitials().interstitialAd();
    }

    private void showLoader(Item item) {
        loader = new LoadedItem(app, binding.activityListLoader, item);
        itemClikCount++;
        interstitialAd.show().whenComplete((aVoid, throwable) -> loader.show());
    }

    @Override
    public void onBackPressed() {
        if (loader == null || loader.isNotLoading()) {
            super.onBackPressed();
        }

    }
}