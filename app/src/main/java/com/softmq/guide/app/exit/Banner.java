package com.softmq.guide.app.exit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

import com.softmq.guide.app.common.ui.image.NetworkImage;

public class Banner {
    private final ExitConfig.BannerConfig banner;
    private final ImageView view;

    public Banner(ExitConfig.BannerConfig banner, ImageView view, Activity activity) {
        this.banner = banner;
        this.view = view;
        view.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse((banner.link())));
            activity.startActivity(browserIntent);
        });
    }

    public void show() {

        new NetworkImage(banner.image(), view).show();
    }
}
