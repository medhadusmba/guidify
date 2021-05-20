package com.softmq.guide.app.common.ui.image;

import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class NetworkImage {
    private final String url;
    private final ImageView view;

    public NetworkImage(String url, ImageView view) {

        this.url = url;
        this.view = view;
    }

    public void show() {
        if (!url.isEmpty()) {
            Picasso picasso = getPicasso(view);
            picasso
                    .load(url)
                    .fit()
                    .centerCrop()
                    // .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                    .into(view);


        }
    }

    private Picasso getPicasso(View view) {
        Picasso picasso = null;
        try {
            picasso = Picasso.get();
        } catch (IllegalStateException e) {
            picasso = buildPicasso(view);
        } finally {
            if (picasso == null) {
                picasso = buildPicasso(view);
            }
        }
        return picasso;
    }

    private Picasso buildPicasso(View view) {
        Picasso picasso;
        picasso = new Picasso.Builder(view.getContext()).build();
        return picasso;
    }

}
