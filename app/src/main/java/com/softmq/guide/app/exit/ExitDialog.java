package com.softmq.guide.app.exit;

import android.app.Activity;

import com.softmq.guide.app.App;
import com.softmq.guide.app.ThankYouActivity;
import com.softmq.guide.app.common.ui.Showable;
import com.softmq.guide.app.common.ui.dialogs.ConfirmDialog;
import com.softmq.guide.app.common.ui.navigation.Navigator;

public class ExitDialog implements Showable {
    private final App app;
    private final Activity activity;
    private final ExitConfig.Type type;
    private ConfirmDialog dialog;

    public ExitDialog(App app, Activity activity, ExitConfig.Type type) {
        this.app = app;
        this.activity = activity;
        this.type = type;
    }

    public void show() {
        dialog = new ConfirmDialog(activity, new ExitView(app, activity, type).asView(), (answer) -> {
            if (answer) {
                new Navigator(activity).navigateTo(ThankYouActivity.class);
            }
        }, true);
        dialog.show();
    }
}
