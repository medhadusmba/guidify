package com.softmq.guide.app.common.ui.internet;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class Link {
    private final Activity activity;
    private final String action;

    public Link(Activity activity, String action) {
        this.activity = activity;
        this.action = action;
    }

    public void open() {
        if (action.isEmpty()) {
            try {
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + activity.getPackageName())));
            } catch (android.content.ActivityNotFoundException anfe) {
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + activity.getPackageName())));
            }
        } else {
            if (action.contains("https://")) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(action));
                activity.startActivity(intent);
            } else {
                try {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + action)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + action)));
                }
            }
        }
    }
}
