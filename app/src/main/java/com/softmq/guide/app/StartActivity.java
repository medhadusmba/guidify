package com.softmq.guide.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.common.ui.LoadedView;
import com.softmq.guide.app.config.SmartConfig;
import com.softmq.guide.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {
    private static final int BUTTON_LOADER_DURATION = 1000;
    ActivityStartBinding binding;
    App app;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        app = new App(this);
        app.ads().natives().nativeAd().show(binding.appAdNatives);
        binding.activityStartButton.getRoot().setOnClickListener(v -> {
            interstitialAd.showNow().whenComplete((aVoid, throwable) -> app.navigator().navigateTo(next(app.config())));
        });
        if (app.config().activities().start().loader().isEnabled()) {
            new LoadedView(binding.activityStartButton.getRoot(), binding.activityStartLoader, app.config().activities().start().loader().duration()).load();
        } else {
            binding.activityStartButton.getRoot().setVisibility(View.VISIBLE);
            binding.activityStartLoader.setVisibility(View.GONE);
        }
        app.rating().reset();
        if (app.config().activities().start().counter().value() == 0) {
            app.rating().show();
        }

        showInter();
    }

    private void showInter() {

    }


    @Override
    public void onBackPressed() {

        if (!isTaskRoot()) {
            super.onBackPressed();
        } else {
            app.exiting().show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        interstitialAd = app.ads().interstitials().interstitialAd();
    }

    public Class<? extends Activity> next(SmartConfig config) {
        if (config.activities().start().counter().value() == 0) {
            config.activities().start().counter().increment();
            return MainActivity.class;
        } else {
            return ListActivity.class;
        }
    }


}