package com.softmq.guide.app.common.ads.admob.banners;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.softmq.guide.app.common.ads.AdsTestUtils;
import com.softmq.guide.app.common.ads.admob.TestAdmobConfig;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AdmobAdsBannerAdsTest extends TestCase {

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
        tests.testBannerAds(new AdmobBannerAds(context, new TestAdmobConfig()));
    }
}