package com.softmq.guide.app.common.ui.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.util.Supplier;

import com.softmq.guide.app.common.core.BiSelector;


public class Navigator {
    private final Activity activity;

    public Navigator(Activity activity) {
        this.activity = activity;
    }

    public void navigateTo(Class<? extends Activity> other) {
        navigateTo(other, new Bundle());
    }

    public void navigateTo(Supplier<Class<? extends Activity>> other) {
        this.navigateTo(other.get());
    }

    public void navigateTo(Class<? extends Activity> main, Class<? extends Activity> secondary, boolean isSelected) {
        this.navigateTo(new BiSelector<>(main, secondary).select(isSelected));
    }

    public void navigateTo(Class<? extends Activity> other, Bundle parameters) {
        Intent intent = new Intent(activity, other);
        intent.putExtras(parameters);
        activity.startActivity(intent);
    }

}
