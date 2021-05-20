package com.softmq.guide.app;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ui.image.NetworkImage;
import com.softmq.guide.app.items.BundleItem;
import com.softmq.guide.databinding.ActivityItemBinding;

public class ItemActivity extends AppCompatActivity {

    App app;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityItemBinding binding;
        binding = ActivityItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        app = new App(this);
        binding.appAdMediumrectLinear.setVisibility(View.VISIBLE);
        app.ads().mediumrects().mediumrect().show(binding.appAdMediumrectAdLinear);
        if (app.config().activities().item().hasInterstitial()) {
            interstitialAd = app.ads().interstitials().interstitialAd();
        }

        if (app.config().activities().item().hasBannerAd()) {
            binding.bnrlinear.setVisibility(View.VISIBLE);
            app.ads().banners().bannerAd().show(binding.adlinear);
        }

        if (app.config().activities().item().hasNativeAd()) {
            app.ads().natives().nativeAd().show(binding.appAdNatives);
        }
        BundleItem item = new BundleItem(getIntent().getExtras());
        binding.activityItemTitle.setTitle(item.getTitle());
        new NetworkImage(item.getImage(), binding.activityItemImage).show();
        binding.activityItemContent.setText(item.getDescription());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (app != null && app.config().activities().item().hasInterstitial()) {
            interstitialAd.showNow().thenRun(this::back);
        } else {
            this.back();
        }

    }

    private void back() {
        super.onBackPressed();

    }

    @Override
    public void onDestroy() {
        app.ads().close();
        super.onDestroy();
    }
}