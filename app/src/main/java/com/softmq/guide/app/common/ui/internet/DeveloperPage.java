package com.softmq.guide.app.common.ui.internet;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class DeveloperPage {
    private final Activity activity;
    private final String id;

    public DeveloperPage(Activity activity, String id) {
        this.activity = activity;
        this.id = id;
    }

    public void show() {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://dev?id=" + id)));
        } catch (android.content.ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/dev?id=" + id)));
        }
    }
}
