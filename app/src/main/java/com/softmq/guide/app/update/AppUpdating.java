package com.softmq.guide.app.update;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.softmq.guide.R;
import com.softmq.guide.app.common.ui.internet.Link;
import com.softmq.guide.app.config.SmartConfig;

public class AppUpdating {
    private final Activity activity;
    private final SmartConfig config;

    public AppUpdating(Activity activity, SmartConfig config) {
        this.activity = activity;
        this.config = config;
    }

    public void show() {
        if (config.update().state()) {
            showDialog();
        }
    }

    private void showDialog() {
        Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_update);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView textTitle = dialog.findViewById(R.id.title);
        TextView textMsg = dialog.findViewById(R.id.msg);
        Button btnUpdate = dialog.findViewById(R.id.btn_update);
        Button btnClose = dialog.findViewById(R.id.btn_close);
        if (!config.update().isCloseable()) {
            btnClose.setEnabled(false);
            btnClose.setVisibility(View.GONE);
        }
        btnClose.setOnClickListener(view -> dialog.dismiss());
        textTitle.setText(config.update().title());
        textMsg.setText(config.update().message());
        btnUpdate.setOnClickListener(view -> {
            String updateAction = config.update().action();
            new Link(activity, updateAction).open();

            if (config.update().isCloseable()) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}
