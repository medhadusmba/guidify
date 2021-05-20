package com.softmq.guide;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import com.softmq.guide.app.ListActivity;
import com.softmq.guide.app.MainActivity;
import com.softmq.guide.app.SplashActivity;
import com.softmq.guide.app.StartActivity;
import com.softmq.guide.app.config.SmartConfig;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestedApp {
    private final UiDevice device;
    private final Instrumentation.ActivityMonitor splashActivityMonitor;
    private final Instrumentation.ActivityMonitor mainActivityMonitor;
    private final Instrumentation.ActivityMonitor startActivityMonitor1;
    private final Instrumentation.ActivityMonitor startActivityMonitor2;
    private final Instrumentation.ActivityMonitor listActivityMonitor;


    public TestedApp() {
        device = UiDevice.getInstance(getInstrumentation());
        splashActivityMonitor = getInstrumentation().addMonitor(SplashActivity.class.getName(), null, false);
        mainActivityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
        startActivityMonitor1 = getInstrumentation().addMonitor(StartActivity.class.getName(), null, false);
        startActivityMonitor2 = getInstrumentation().addMonitor(StartActivity.class.getName(), null, false);
        listActivityMonitor = getInstrumentation().addMonitor(ListActivity.class.getName(), null, false);
    }

    public TestedApp withConfig(SmartConfig config) {
        return this;
    }

    public void showsSplashscreen() {
        showsActivity(SplashActivity.class, splashActivityMonitor);
        waitFor(5000);

    }

    public void showsStartActivity(int number) {
        if (number == 1) {
            showsActivity(StartActivity.class, startActivityMonitor1, 5000);
        } else if (number == 2) {
            showsActivity(StartActivity.class, startActivityMonitor2, 5000);
        }
        waitFor(5000);
    }


    public void clickOnStartButton(int number) {
        if (number == 1) {
            device.wait(Until.findObject(By.text("Start")), 5000).click();
        } else if (number == 2) {
            device.wait(Until.findObject(By.text("Start")), 5000).click();
        } else if (number == 3) {
            device.wait(Until.findObject(By.text("Start")), 5000).click();
        }
        waitFor(5000);

    }

    public void showsMainActivity() {
        showsActivity(MainActivity.class, mainActivityMonitor);
        waitFor(5000);

    }


    public void showsListActivity() {
        showsActivity(ListActivity.class, listActivityMonitor);
        waitFor(5000);
    }

    public void showsItems(int number) {
    }

    public void clickOnItem(int index) {

    }

    public void showsItemActivity() {

    }

    public void showsItem(String title, String description, String image) {

    }

    public void start() {
        device.pressHome();
        device.findObject(new UiSelector().description("Apps"));
        device.wait(Until.hasObject(By.pkg("com.android.launcher").depth(0)), 5000);
        final Intent intent = getApplicationContext().getPackageManager()
                .getLaunchIntentForPackage(getApplicationContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        getApplicationContext().startActivity(intent);
        //device.wait(Until.hasObject(By.pkg(getApplicationContext().getPackageName()).depth(0)), 5000);

    }

    public void stop() {
        getInstrumentation().removeMonitor(splashActivityMonitor);
        getInstrumentation().removeMonitor(startActivityMonitor1);
        getInstrumentation().removeMonitor(mainActivityMonitor);
        getInstrumentation().removeMonitor(startActivityMonitor2);
        getInstrumentation().removeMonitor(listActivityMonitor);
    }

    private void showsActivity(Class<? extends Activity> expected, Instrumentation.ActivityMonitor monitor) {
        Activity activity = getInstrumentation().waitForMonitor(monitor);
        assertNotNull(activity);
        assertEquals(expected.getName(), activity.getClass().getName());
        waitFor(5000);
    }

    private void waitFor(long time) {
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void showsActivity(Class<? extends Activity> expected, Instrumentation.ActivityMonitor monitor, long timeout) {


        Activity activity = getInstrumentation().waitForMonitorWithTimeout(monitor, timeout);
        assertNotNull(activity);
        assertEquals(expected.getName(), activity.getClass().getName());
        waitFor(timeout);
    }

}
