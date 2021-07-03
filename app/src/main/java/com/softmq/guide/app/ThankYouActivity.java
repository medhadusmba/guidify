package com.softmq.guide.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAd;
import com.softmq.guide.app.databinding.ActivityItemBinding;
import com.softmq.guide.app.databinding.ActivityThankYouBinding;

public class ThankYouActivity extends AppCompatActivity {

    private InterstitialAd interstitial;
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        @NonNull ActivityThankYouBinding binding = ActivityThankYouBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        app = new App(this);
        if(interstitial==null){
            interstitial= app.ads().interstitials().interstitialAd();
        }
        app.ads().natives().nativeAd().show(binding.appAdNatives);
        app.ads().banners().bannerAd().show(binding.banner);
        binding.exitNow.setOnClickListener(v -> finishAffinity());
        binding.rateNow.setOnClickListener(v -> app.rating().show());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(interstitial==null){
            interstitial= app.ads().interstitials().interstitialAd();
        }
        interstitial.show();
    }

}