package com.softmq.guide.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.airbnb.lottie.LottieAnimationView;
import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;

import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAd;
import com.softmq.guide.app.common.ui.dialogs.CustomDialog;
import com.softmq.guide.app.common.ui.image.NetworkImage;
import com.softmq.guide.app.items.Item;
import com.softmq.guide.app.items.ListItemLayout;
import com.softmq.guide.app.items.LoadedItem;
import com.softmq.guide.app.items.SmartAppItems;
import com.softmq.guide.app.webview.Browser;
import com.softmq.guide.app.databinding.ActivityListBinding;
import com.softmq.guide.app.databinding.BacknextItemNativeAdBinding;
import com.softmq.guide.app.databinding.ItemArticleBinding;
import com.softmq.guide.app.databinding.ItemErrorBinding;
import com.softmq.guide.app.databinding.ItemWebviewBinding;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ListActivity extends AppCompatActivity {

    public static final String REQUEST_CODE = "requestCode";
    public static final String RESULT_CODE = "resultCode";
    public static final String DATA = "data";
    private LoadedItem loader;
    private ActivityListBinding binding;
    private App app;
    private InterstitialAd interstitialAd;
    private RewardedAd rewardedAd;
    private Consumer<Bundle> activityResultListener=(args)->{};
    private Browser browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.activityList.setLayoutManager(new GridLayoutManager(this, 1));
        app = new App(this);
        showBannerAd();
        SmartAppItems items;
        if (app.config().activities().list().type().equals("back_next")) {
            final InterstitialAd[] backNextInterstitial = new InterstitialAd[1];
            binding.activityListPagerContainer.setVisibility(View.VISIBLE);
            items = app.items(binding.activityListPager, (item) -> {
            }, (item, position) -> getPagerItemType((Item) item, position));
            binding.activityListPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

                @Override
                public void onPageSelected(int position) {
                    backNextInterstitial[0] = app.ads().interstitials().interstitialAd();
                    boolean isOneItemAtMost = items == null || items.size() <= 1;
                    if(isOneItemAtMost){
                        binding.activityListPagerBack.setVisibility(View.GONE);
                        binding.activityListPagerNext.setVisibility(View.GONE);
                    }else{
                        binding.activityListPagerBack.setVisibility(View.VISIBLE);
                        binding.activityListPagerNext.setVisibility(View.VISIBLE);
                    }
                    boolean isItemNotFirst = position != 0;
                    binding.activityListPagerBack.setEnabled(isItemNotFirst);
                    boolean isItemNotLast = items != null && items.size() - 1 != position;
                    binding.activityListPagerNext.setEnabled(isItemNotLast);

                }
            });
            binding.activityListPagerBack.setOnClickListener(v -> {

                if(app.config().activities().item().hasInterstitialAd() && backNextInterstitial[0]!=null){
                    backNextInterstitial[0].showNow();
                }
                previous();

            });
            binding.activityListPagerNext.setOnClickListener(v -> {
                if(app.config().activities().item().hasInterstitialAd() && backNextInterstitial[0]!=null){
                    backNextInterstitial[0].showNow();
                }
                goToNextItem();

            });
        }
        else {
            binding.activityList.setVisibility(View.VISIBLE);
            items = app.items(binding.activityList, this::showItem, (item, position) -> new ListItemLayout(app,  (Item) item, position).asType());
        }
        items.read().thenRun(items::show);
    }

    private void previous() {
        if ( browser == null || browser.onBackPressed()) {
            if (app != null && app.config().activities().item().hasInterstitialAd()) {
                interstitialAd.showNow().thenRun(this::goToPreviousItem);
            } else {
                goToPreviousItem();
            }
        }
    }

    private void goToPreviousItem() {
        goToItem(binding.activityListPager.getCurrentItem() - 1);
    }

    private void goToItem(int item) {
        binding.activityListPager.setCurrentItem(item);
    }

    private void goToNextItem() {
        goToItem(binding.activityListPager.getCurrentItem() + 1);

    }

    private void showBannerAd() {
        if (app.config().activities().item().hasBannerAd()) {
            binding.bnrlinear.setVisibility(View.VISIBLE);
            app.ads().banners().bannerAd().show(binding.adlinear);
        }
    }

    private ItemType<? extends ViewDataBinding> getPagerItemType(Item item, Integer position) {
        Log.d(getClass().getSimpleName(), "showing item{" + "position:" + position + ", type:" + item.getType() + ", description:" + item.getDescription() + "}");
        switch (item.getType()) {
            case Item.TYPE_NATIVE_AD:
                return new ItemType<BacknextItemNativeAdBinding>(R.layout.backnext_item_native_ad) {
                    @Override
                    public void onBind(@NotNull Holder<BacknextItemNativeAdBinding> holder) {
                        if (holder.getBinding().itemNativeAd.getChildCount() == 0) {
                            holder.getBinding().itemNativeAd.removeAllViews();
                            holder.getBinding().itemNativeAd.setVisibility(View.VISIBLE);
                            app.ads().natives().nativeAd().show(holder.getBinding().itemNativeAd);
                        }
                    }
                };
            case Item.TYPE_WEBVIEW:
                return new ItemType<ItemWebviewBinding>(R.layout.item_webview) {
                    @Override
                    public void onBind(@NotNull Holder<ItemWebviewBinding> holder) {
                        holder.getBinding().itemWebview.removeAllViews();
                        if(item.getTitle().isEmpty()){
                            holder.getBinding().itemWebviewTitle.setVisibility(View.GONE);
                        } else{
                            holder.getBinding().itemWebviewTitle.setVisibility(View.VISIBLE);
                        }
                        holder.getBinding().itemWebviewTitle.setText(item.getTitle());
                        browser = new Browser(ListActivity.this, item.getUrl());
                        activityResultListener=(args)->{
                            browser.onActivityResult(args.getInt(REQUEST_CODE), args.getInt(RESULT_CODE), args.getParcelable(DATA));
                        };
                        browser.show(holder.getBinding().itemWebview);

                    }
                };
            case Item.TYPE_ERROR:
                return new ItemType<ItemErrorBinding>(R.layout.item_error) {
                    @Override
                    public void onRecycle(@NotNull Holder<ItemErrorBinding> holder) {
                        ViewGroup error = holder.getBinding().itemError.getRoot();
                        LottieAnimationView lottie = error.findViewById(R.id.activity_item_error_icon);
                        lottie.clearAnimation();
                    }

                    @Override
                    public void onBind(@NotNull Holder<ItemErrorBinding> holder) {
                        ViewGroup error = holder.getBinding().itemError.getRoot();
                        error.setVisibility(View.VISIBLE);
                        LottieAnimationView lottie = error.findViewById(R.id.activity_item_error_icon);
                        View message = error.findViewById(R.id.activity_item_error_message);
                        lottie.addAnimatorListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                message.setVisibility(View.VISIBLE);
                            }
                        });
                        error.findViewById(R.id.layout_no_content_error).setOnClickListener((v) -> {
                            if (!lottie.isAnimating()) {
                                onBackPressed();
                            }
                        });
                    }
                };
            default:
                return new ItemType<ItemArticleBinding>(R.layout.item_article) {
                    @Override
                    public void onBind(@NotNull Holder<ItemArticleBinding> holder) {
                        holder.getBinding().itemArticleContentScroll.setVisibility(View.VISIBLE);
                        holder.getBinding().itemArticleTitle.setText(item.getTitle());
                        new NetworkImage(item.getImage(), holder.getBinding().itemArticleImage).show();
                        holder.getBinding().itemArticleContent.setText(item.getDescription());
                    }
                };
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle args=new Bundle();
        args.putInt("requestCode", requestCode);
        args.putInt("resultCode", resultCode);
        args.putParcelable("data", data);
        activityResultListener.accept(args);
    }

    @Override
    protected void onResume() {
        super.onResume();
        interstitialAd = app.ads().interstitials().interstitialAd();
        rewardedAd = app.ads().rewardedAds().rewardedAd();
    }

    private void showLoader(Item item) {
        loader = new LoadedItem(app, binding.activityListLoader, item);
        interstitialAd.show().whenComplete((aVoid, throwable) -> loader.show());
    }

    private void showLocker(Item item) {
        loader = new LoadedItem(app, binding.activityListLoader, item);
        FrameLayout progressView = new FrameLayout(this.getApplicationContext());
        progressView.addView(new ProgressBar(this.getApplicationContext()));
        CustomDialog dialog = new CustomDialog(this, progressView);
        dialog.close();
        rewardedAd.show().whenComplete((aVoid, throwable) -> {
            if (throwable == null) {
                loader.show();
            } else {
                Toast.makeText(this, "You should WATCH the VIDEO to be able to unlock", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showItem(Item item) {
        if (item.getLocker() != null) {
            showLocker(item);
        } else {
            showLoader(item);
        }
    }


    @Override
    public void onBackPressed() {
        if(loader == null || loader.isNotLoading()){
            if ( browser == null || browser.onBackPressed()) {
                if (app != null && app.config().activities().item().hasInterstitialAd()) {
                    interstitialAd.showNow().thenRun(this::backAndFinish);
                } else {
                    backAndFinish();
                }
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

}