package com.softmq.guide.app.common.ui.navigation;

import android.app.Activity;

import com.softmq.guide.app.common.core.Delayed;

/**
 * Navigates after a delay to specified destination
 */
public class DelayedNavigator extends Navigator {
    private final Activity current;
    private final long delayMillis;
    private final Runnable listener;

    public DelayedNavigator(Activity current, long delayMillis, Runnable listener) {
        super(current);
        this.current = current;
        this.delayMillis = delayMillis;
        this.listener = listener;
    }

    public DelayedNavigator(Activity current, long delayMillis) {
        this(current, delayMillis, () -> {
        });
    }

    @Override
    public void navigateTo(Class<? extends Activity> other) {
        new Delayed(delayMillis, () -> {
            super.navigateTo(other);
            listener.run();
        }).run();
    }
}
