package com.softmq.guide.app.common.ui.internet;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class AppPageOnPlayStore {
    private final Context context;

    public AppPageOnPlayStore(Context context) {
        this.context = context;
    }

    public void open() {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + context.getPackageName())));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

}
