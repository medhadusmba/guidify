package com.softmq.guide.app.exit;

import android.app.Activity;

import com.softmq.guide.app.App;
import com.softmq.guide.app.config.SmartConfig;

public class AppExiting {
    private final ExitDialog dialog;

    public AppExiting(App app, Activity activity, SmartConfig config) {
        dialog = new ExitDialog(app, activity, config.exit().type());
    }

    public void show() {
        dialog.show();
    }
}
