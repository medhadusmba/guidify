package com.softmq.guide.app.common.ads.unity.banners;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.softmq.guide.app.MainActivity;
import com.softmq.guide.app.common.ads.AdsTestUtils;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UnityAdsBannerAdsTest extends TestCase {

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
    public void get() {
        ActivityScenario.launch(MainActivity.class).onActivity(activity -> {
            // new UnityNetwork(activity, new TestUnityConfig()).initialize();
            // tests.testBannerAds(new UnityBannerAds(activity, new TestUnityConfig()));
        });
    }
}