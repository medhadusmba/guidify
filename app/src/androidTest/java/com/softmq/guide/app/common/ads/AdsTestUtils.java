package com.softmq.guide.app.common.ads;

import android.content.Context;
import android.util.Log;

import androidx.test.internal.runner.junit4.statement.UiThreadStatement;

import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import java9.util.function.Consumer;

import static org.junit.Assert.assertTrue;

public class AdsTestUtils {
    private final Context context;

    public AdsTestUtils(Context context) {
        this.context = context;
    }

    public void testBannerAds(BannerAdSource banners) {
        test((latch) -> {
            banners.get().thenAccept(bannerAd -> {
                latch.countDown();
            }).exceptionally(throwable -> {
                Log.d("todo", throwable.getMessage());
                return null;
            });
            Log.e("todo", "test");

        });
    }

    public void testInterstitialAds(InterstitialAdSource interstitials) {
        test((latch) -> {
            interstitials.get().thenAccept(interstitialAd -> {
                interstitialAd.show();
                latch.countDown();
            }).exceptionally(throwable -> {
                Log.d("todo", throwable.getMessage());
                return null;
            });

        });
    }

    public void testNativeAds(NativeAdSource natives) {
        test((latch) -> {
            natives.get().thenAccept(bannerAd -> {
                latch.countDown();
            });

        });
    }

    private void test(Consumer<CountDownLatch> test) {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            UiThreadStatement.runOnUiThread(() -> {

                test.accept(latch);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        boolean result = false;
        try {
            result = latch.await(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("Timeout", result);

    }
}
