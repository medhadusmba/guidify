package com.softmq.guide.app.common.ads;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.appodeal.ads.TestActivity;
import com.softmq.ads.appodeal.banners.AppodealBannerAds;
import com.softmq.ads.appodeal.interstitials.AppodealInterstitialAds;
import com.softmq.guide.app.common.ads.admob.TestAdmobConfig;
import com.softmq.guide.app.common.ads.admob.banners.AdmobBannerAds;
import com.softmq.guide.app.common.ads.admob.interstitials.AdmobInterstitialAds;
import com.softmq.guide.app.common.ads.admob.natives.AdmobNativeAds;
import com.softmq.guide.app.common.ads.appodeal.TestAppodealConfig;
import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;
import com.softmq.guide.app.common.ads.facebook.TestFacebookConfig;
import com.softmq.guide.app.common.ads.facebook.banners.FacebookBannerAds;
import com.softmq.guide.app.common.ads.facebook.interstitials.FacebookInterstitialAds;
import com.softmq.guide.app.common.ads.facebook.natives.FacebookNativeAds;
import com.softmq.guide.app.common.ads.unity.TestUnityConfig;
import com.softmq.guide.app.common.ads.unity.banners.UnityBannerAds;
import com.softmq.guide.app.common.ads.unity.interstitials.UnityInterstitialAds;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AdFactoryTest extends TestCase {

    private Context context;
    private AdsTestUtils tests;


    @Before
    public void setUp() throws Exception {
        context = getInstrumentation().getTargetContext();
        tests = new AdsTestUtils(context);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void get() throws Throwable {
        ActivityScenario.launch(TestActivity.class).onActivity(activity -> {
            // do something with your activity instance

            BannerAdSource[] banners = new BannerAdSource[]{
                    new AdmobBannerAds(context, new TestAdmobConfig()),
                    new FacebookBannerAds(context, new TestFacebookConfig()),
                    new AppodealBannerAds(activity, new TestAppodealConfig()),
                    new UnityBannerAds(activity, new TestUnityConfig())
            };
            for (BannerAdSource provider : banners) {
                tests.testBannerAds(provider);
            }
            InterstitialAdSource[] interstitials = new InterstitialAdSource[]{
                    new AdmobInterstitialAds(context, new TestAdmobConfig()),
                    new FacebookInterstitialAds(context, new TestFacebookConfig()),
                    new AppodealInterstitialAds(activity, new TestAppodealConfig()),
                    new UnityInterstitialAds(activity, new TestUnityConfig())
            };
            for (InterstitialAdSource provider : interstitials) {
                tests.testInterstitialAds(provider);

            }

            NativeAdSource[] natives = new NativeAdSource[]{
                    new AdmobNativeAds(context, new TestAdmobConfig()),
                    new FacebookNativeAds(context, new TestFacebookConfig())
            };
            for (NativeAdSource provider : natives) {

                tests.testNativeAds(provider);

            }
        });

    }
}