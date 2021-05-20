package com.softmq.guide;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;

import com.softmq.guide.app.Config;
import com.softmq.guide.app.config.SmartConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class EndToEndTest {
    private static TestedApp app;

    @Before
    public void setup() {
        Context appContext = getInstrumentation().getTargetContext();
        Config.offline = true;
        Config.online = false;
        SmartConfig config = new SmartConfig(appContext);
        app = new TestedApp().withConfig(config);
        app.start();
    }

    @Test
    public void happyPath() {
        app.showsSplashscreen();
        app.showsStartActivity(1);
        app.clickOnStartButton(1);
        app.showsMainActivity();
        app.clickOnStartButton(2);
        app.showsStartActivity(2);
        app.clickOnStartButton(3);
        app.showsListActivity();
        app.showsItems(1);
        app.clickOnItem(0);
        app.showsItemActivity();
        app.showsItem("title", "description", "image.png");
    }

    @After
    public void tearDown() throws Exception {
        app.stop();
    }
}