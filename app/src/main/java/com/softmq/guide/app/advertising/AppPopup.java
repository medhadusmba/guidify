package com.softmq.guide.app.advertising;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.softmq.guide.R;
import com.softmq.guide.app.App;
import com.softmq.guide.app.common.ui.image.NetworkImage;
import com.softmq.guide.app.common.ui.internet.Link;

import org.jetbrains.annotations.NotNull;

public class AppPopup {
    private final App app;
    private final Activity activity;

    public AppPopup(App app, Activity activity) {
        this.app = app;
        this.activity = activity;
    }

    public void show() {
        if (app.config().popup().isEnabled()) {
            Dialog dialog = new Dialog(activity);
            WindowManager.LayoutParams layoutParams = configureDialog(dialog);
            dialog.show();
            dialog.getWindow().setAttributes(layoutParams);
            listenToCloseEvents(dialog);
            populateView(dialog);
        }

    }

    @NotNull
    private WindowManager.LayoutParams configureDialog(Dialog dialog) {
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_popup);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        return layoutParams;
    }

    private void listenToCloseEvents(Dialog dialog) {
        ImageView btnClose = dialog.findViewById(R.id.dialog_close);
        btnClose.setOnClickListener(view -> dialog.dismiss());
    }

    private void populateView(Dialog dialog) {
        ImageView image = dialog.findViewById(R.id.dialog_popup_image);
        TextView title = dialog.findViewById(R.id.dialog_popup_title);
        TextView description = dialog.findViewById(R.id.dialog_popup_description);
        ImageButton link = dialog.findViewById(R.id.dialog_popup_link);
        new NetworkImage(app.config().popup().image(), image).show();
        title.setText(app.config().popup().title());
        description.setText(app.config().popup().description());
        link.setOnClickListener(v -> new Link(activity, app.config().popup().link()).open());

    }
}
