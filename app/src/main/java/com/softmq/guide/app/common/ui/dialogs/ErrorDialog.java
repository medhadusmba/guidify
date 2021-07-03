package com.softmq.guide.app.common.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.softmq.guide.app.R;
import com.softmq.guide.app.common.ui.Showable;

public class ErrorDialog implements Showable {
    private final Activity activity;
    private final String message;
    private final Dialog dialog;
    private final int layout;

    public ErrorDialog(Activity activity, String message) {
        this.activity = activity;
        this.message = message;
        this.layout = R.layout.dialog_error;
        dialog = new Dialog(activity);
    }

    public void show() {

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(layout);
        TextView text = dialog.findViewById(R.id.message);
        text.setText(message);
        Button dialogButton = dialog.findViewById(R.id.close);
        dialogButton.setOnClickListener(v -> dialog.dismiss());
        dialog.show();

    }
}