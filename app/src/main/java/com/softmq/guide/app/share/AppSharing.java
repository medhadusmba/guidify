package com.softmq.guide.app.share;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.multidex.BuildConfig;

import com.softmq.guide.R;

public class AppSharing {
    private final Activity activity;

    public AppSharing(Activity activity) {
        this.activity = activity;
    }

    public void show() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, activity.getResources().getString(R.string.app_name));
            String shareMessage = "\nWhat's up! check this out\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            activity.startActivity(Intent.createChooser(shareIntent, "Share"));
        } catch (Exception e) {
            Log.d("todo", "Can't share because: " + e);
        }
    }
}
