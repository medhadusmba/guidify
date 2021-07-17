package com.softmq.guide.app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.softmq.guide.app.common.time.ElapsedTime;
import com.softmq.guide.app.common.time.RemainingTime;
import com.softmq.guide.app.common.ui.navigation.DelayedNavigator;
import com.softmq.guide.app.databinding.ActivitySplashBinding;
import com.softmq.guide.app.splashscreen.SplashActivityConfig;
import com.softmq.huxter.core.Huxter;
import com.softmq.huxter.gdpr.GDPR;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {
    private final ElapsedTime elapsed = new ElapsedTime();
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        elapsed.start();
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        App app = new App(this);
        app.initialize()
                .completeOnTimeout(null, app.config().activities().splash().duration(), TimeUnit.MILLISECONDS)
                .whenComplete((aVoid, throwable) -> navigate(app));
    }


    private void navigate(App app) {
        SplashActivityConfig config = app.config().activities().splash();
        elapsed.finish();
        new GDPR.UserConsent(this).isGranted().whenComplete((ok, throwable) ->{
            if(!ok){
                finish();
            } else{
                new DelayedNavigator(this, new RemainingTime(elapsed.value(), config.duration()).value())
                        .navigateTo(app.activities().home());
            }

        } );
    }

}
