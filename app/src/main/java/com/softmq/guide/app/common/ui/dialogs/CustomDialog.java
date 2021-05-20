package com.softmq.guide.app.common.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import com.softmq.guide.app.common.ui.Showable;

public class CustomDialog implements Showable, Closeable {
    private Activity activity;
    private Dialog dialog;

    public CustomDialog(Activity activity, int layout, boolean fullscreen) {
        createDialog(activity, fullscreen);
        dialog.setContentView(layout);

    }

    public CustomDialog(Activity activity, int layout) {
        this(activity, layout, false);
    }

    public CustomDialog(Activity activity, View view) {
        this(activity, view, false);
    }

    public CustomDialog(Activity activity, View view, boolean fullscreen) {
        createDialog(activity, fullscreen);
        dialog.setContentView(view);

    }

    private void createDialog(Activity activity, boolean fullscreen) {
        this.activity = activity;
        if (fullscreen) {
            dialog = new Dialog(activity, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        } else {
            dialog = new Dialog(activity);
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void show() {

        dialog.show();
    }

    public void close() {
        this.dialog.dismiss();
    }

    public View findViewById(int id) {
        return this.dialog.findViewById(id);
    }
}