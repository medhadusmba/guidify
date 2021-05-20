package com.softmq.photoify;


import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

public class Plugin {
    public void load(Activity activity) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
        appCompatActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, EditImageActivity.newInstance(appCompatActivity))
                .commitNow();

    }
}
