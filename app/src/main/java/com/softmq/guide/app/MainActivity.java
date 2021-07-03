package com.softmq.guide.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.softmq.guide.app.advertising.AppPopup;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ui.LoadedView;
import com.softmq.guide.app.config.SmartConfig;
import com.softmq.guide.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int WAIT_TIME_OPTIONS = 2500;
    private App app;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        app = new App(this);

        app.ads().natives().nativeAd().show(binding.appAdNatives);
        binding.options.animate().alpha(1).setDuration(WAIT_TIME_OPTIONS);
        if (app.config().activities().start().loader().isEnabled()) {
            new LoadedView(binding.start, binding.activityMainLoader, app.config().activities().start().loader().duration()).load();
        } else {
            binding.start.setVisibility(View.VISIBLE);
            binding.activityMainLoader.setVisibility(View.GONE);
        }
        binding.start.setOnClickListener(v -> interstitial.showNow().whenComplete((aVoid, throwable) -> app.navigator().navigateTo(next(app.config()))));
        binding.rate.setOnClickListener(v -> app.rating().show());
        binding.share.setOnClickListener(v -> app.sharing().show());
        //binding.more.setOnClickListener(v -> new DeveloperPage(this, app.config().activities().main().more()).show());
        if (!app.config().activities().start().isEnabled()) {
            app.rating().show();
        }
        app.updating().show();
        if (app.config().popup().isEnabled())
            new AppPopup(app, this).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        interstitial = app.ads().interstitials().interstitialAd();
    }

    private Class<? extends Activity> next(SmartConfig config) {
        boolean isStartEnabled = config.activities().start().isEnabled();
        boolean canStartSecondTime = config.activities().start().count() == 2;
        if (isStartEnabled && canStartSecondTime) {
            return StartActivity.class;
        } else {
            return ListActivity.class;
        }
    }

    @Override
    public void onBackPressed() {
        if (app.config().activities().start().isEnabled()) {
            app.config().activities().start().counter().reset();
        }
        if (!isTaskRoot()) {
            super.onBackPressed();
        } else {
            interstitial.showNow();
            app.exiting().show();
        }
    }


}