package com.softmq.guide.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.softmq.guide.R;
import com.softmq.guide.app.common.time.ElapsedTime;
import com.softmq.guide.app.common.time.RemainingTime;
import com.softmq.guide.app.common.ui.navigation.DelayedNavigator;
import com.softmq.guide.app.splashscreen.SplashActivityConfig;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {
    private final ElapsedTime elapsed = new ElapsedTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        elapsed.start();
        setContentView(R.layout.activity_splash);
        App app = new App(this);
        app.initialize()
                .completeOnTimeout(null, app.config().activities().splash().duration(), TimeUnit.MILLISECONDS)
                .whenComplete((aVoid, throwable) -> navigate(app));
    }

    private void navigate(App app) {
        SplashActivityConfig config = app.config().activities().splash();
        elapsed.finish();
        new DelayedNavigator(this, new RemainingTime(elapsed.value(), config.duration()).value())
                .navigateTo(app.activities().home());
    }
}