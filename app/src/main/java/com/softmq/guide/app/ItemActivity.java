package com.softmq.guide.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.softmq.guide.app.R;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ui.image.NetworkImage;
import com.softmq.guide.app.databinding.ActivityItemBinding;
import com.softmq.guide.app.items.BundleItem;
import com.softmq.guide.app.items.Item;
import com.softmq.guide.app.webview.Browser;
import com.softmq.guide.app.databinding.ActivityItemBinding;

import java.util.function.Consumer;

public class ItemActivity extends AppCompatActivity {

    App app;
    InterstitialAd interstitialAd;
    ActivityItemBinding binding;
    private Browser browser;
    private Consumer<Bundle> activityResultListener = (args) -> {
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BundleItem item = new BundleItem(getIntent().getExtras());
        binding = ActivityItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(item.getTitle());
        app = new App(this);
        showMediumRectAd();
        createInterstitialAd();
        showBannerAd();
        showNativeAd();
        setDetails(item);
    }

    private void setDetails(BundleItem item) {
        if (item.getTitle().isEmpty()) {
            binding.activityItemTitle.setVisibility(View.GONE);
        } else {
            binding.activityItemTitle.setVisibility(View.VISIBLE);
        }
        binding.activityItemTitle.setTitle(item.getTitle());
        new NetworkImage(item.getImage(), binding.activityItemImage).show();
        setContent(item);
    }

    private void setContent(BundleItem item) {
        if (item.getType().equals(Item.TYPE_WEBVIEW)) {
            binding.activityItemWebview.setVisibility(View.VISIBLE);
            binding.activityItemWebview.removeAllViews();
            browser = new Browser(this, item.getUrl());
            activityResultListener = (args) -> {
                browser.onActivityResult(args.getInt(ListActivity.REQUEST_CODE), args.getInt(ListActivity.RESULT_CODE), args.getParcelable(ListActivity.DATA));
            };
            browser.show(binding.activityItemWebview);
        } else {

            if (item.getDescription().isEmpty()) {
                ViewGroup error = binding.activityItemError.getRoot();
                error.setVisibility(View.VISIBLE);
                LottieAnimationView lottie = error.findViewById(R.id.activity_item_error_icon);
                error.findViewById(R.id.layout_no_content_error).setOnClickListener((v) -> {
                    onBackPressed();
                });
            } else {
                binding.appBar.setVisibility(View.VISIBLE);
                binding.activityItemContent.setText(item.getDescription());
                binding.activityItemContentScroll.setVisibility(View.VISIBLE);
            }
        }
    }

    private void showNativeAd() {
        if (app.config().activities().item().hasNativeAd()) {
            app.ads().natives().nativeAd().show(binding.appAdNatives).thenRun(() -> {
                binding.activityItemAdsTop.setVisibility(View.VISIBLE);
                binding.appAdNatives.setVisibility(View.VISIBLE);

            });
        }
    }

    private void showBannerAd() {
        if (app.config().activities().item().hasBannerAd()) {
            binding.bnrlinear.setVisibility(View.VISIBLE);
            app.ads().banners().bannerAd().show(binding.adlinear);
        }
    }

    private void createInterstitialAd() {
        if (app.config().activities().item().hasInterstitialAd()) {
            interstitialAd = app.ads().interstitials().interstitialAd();
        }
    }

    private void showMediumRectAd() {
        if (app.config().activities().item().hasMediumrectAd()) {
            app.ads().mediumrects().mediumrect().show(binding.appAdMediumrectAdLinear).thenRun(() -> {
                binding.activityItemAdsTop.setVisibility(View.VISIBLE);
                binding.appAdMediumrectLinear.setVisibility(View.VISIBLE);
                binding.appAdMediumrectContainer.setVisibility(View.VISIBLE);
            });
        }
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
        if (browser == null || browser.onBackPressed()) {
            if (app != null && app.config().activities().item().hasInterstitialAd()) {
                interstitialAd.showNow().thenRun(this::backAndFinish);
            } else {
                backAndFinish();
            }
        }

    }


    private void backAndFinish() {
        super.onBackPressed();
        finish();
    }


    @Override
    public void onDestroy() {
        app.ads().close();
        super.onDestroy();
    }

    public void showInterstitial() {
        if (app != null && app.config().activities().item().hasInterstitialAd()) {

            app.ads().interstitials().interstitialAd().show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle args = new Bundle();
        args.putInt("requestCode", requestCode);
        args.putInt("resultCode", resultCode);
        args.putParcelable("data", data);
        activityResultListener.accept(args);
    }

}