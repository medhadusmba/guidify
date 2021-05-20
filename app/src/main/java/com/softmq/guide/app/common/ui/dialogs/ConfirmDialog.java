package com.softmq.guide.app.common.ui.dialogs;

import android.app.Activity;
import android.view.View;

import com.softmq.guide.R;
import com.softmq.guide.app.common.core.Listener;
import com.softmq.guide.app.common.ui.Showable;

public class ConfirmDialog implements Showable, Closeable {
    private final CustomDialog dialog;
    private final Listener<Boolean> answerListener;

    public ConfirmDialog(Activity activity, View view, Listener<Boolean> answerListener, boolean fullscreen) {
        this.answerListener = answerListener;
        this.dialog = new CustomDialog(activity, view, fullscreen);

    }

    public ConfirmDialog(Activity activity, int layout, Listener<Boolean> answerListener) {
        this.answerListener = answerListener;
        this.dialog = new CustomDialog(activity, layout);

    }

    public ConfirmDialog(Activity activity, View view, Listener<Boolean> answerListener) {
        this.answerListener = answerListener;
        this.dialog = new CustomDialog(activity, view);

    }

    public void show() {

        this.dialog.findViewById(R.id.dialog_confirm_yes).setOnClickListener((v) -> {
            answerListener.accept(true);
            close();
        });
        this.dialog.findViewById(R.id.dialog_confirm_no).setOnClickListener((v) -> {
            answerListener.accept(false);
            close();
        });
        this.dialog.show();
    }

    @Override
    public void close() {
        this.dialog.close();
    }
}
