package com.softmq.guide.app.exit;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.softmq.guide.R;
import com.softmq.guide.app.App;
import com.softmq.guide.app.common.ui.LoadedView;

public class ExitView {
    private final App app;
    private final Activity activity;
    private final ExitConfig.Type type;

    public ExitView(App app, Activity activity, ExitConfig.Type type) {
        this.app = app;
        this.activity = activity;
        this.type = type;
    }

    public View asView() {
        View result = new FrameLayout(activity);
        if (type instanceof ExitConfig.TextConfig) {
            result = activity.getLayoutInflater().inflate(R.layout.dialog_exit_text, null);
            ((TextView) result.findViewById(R.id.dialog_exit_text_content)).setText(((ExitConfig.TextConfig) type).content());

        } else if (type instanceof ExitConfig.NativeAdConfig) {
            result = activity.getLayoutInflater().inflate(R.layout.dialog_exit_native_ad, null);
            app.ads().natives().nativeAd().show((ViewGroup) result.findViewById(R.id.app_ad_natives));
        } else if (type instanceof ExitConfig.BannerConfig) {
            result = activity.getLayoutInflater().inflate(R.layout.dialog_exit_banner, null);
            new Banner((ExitConfig.BannerConfig) type, result.findViewById(R.id.dialog_exit_banner_image), activity).show();
        }
        if (app.config().exit().loader().isEnabled()) {
            new LoadedView(result.findViewById(R.id.dialog_exit_confirm), result.findViewById(R.id.dialog_exit_loader), app.config().exit().loader().duration()).load();
        } else {
            result.findViewById(R.id.dialog_exit_confirm).setVisibility(View.VISIBLE);
            result.findViewById(R.id.dialog_exit_loader).setVisibility(View.GONE);
        }
        return result;
    }

}
