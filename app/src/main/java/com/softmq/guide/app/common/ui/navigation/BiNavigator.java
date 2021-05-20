package com.softmq.guide.app.common.ui.navigation;

import android.app.Activity;

import com.softmq.guide.app.common.core.BiSelector;

public class BiNavigator extends Navigator {
    public BiNavigator(Activity activity) {
        super(activity);
    }

    public void navigateTo(Class<? extends Activity> main, Class<? extends Activity> secondary, boolean isFirst) {
        super.navigateTo(new BiSelector<>(main, secondary).select(isFirst));
    }
}
