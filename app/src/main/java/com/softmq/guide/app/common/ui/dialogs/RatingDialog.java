package com.softmq.guide.app.common.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.hsalf.smileyrating.SmileyRating;
import com.softmq.guide.app.R;
import com.softmq.guide.app.common.core.Listener;
import com.softmq.guide.app.common.data.KeyValueStore;
import com.softmq.guide.app.common.data.Preferences;


public class RatingDialog {
    private static final String DIALOG_RATING = "dialog_rating";
    private final Dialog dialog;
    private final KeyValueStore<String, String> store;

    private final Listener<Integer> callback;

    public RatingDialog(Activity activity) {
        this(activity, (rating) -> {
        });
    }

    public RatingDialog(Activity activity, Listener<Integer> callback) {
        dialog = new Dialog(activity);
        store = new Preferences(activity.getApplicationContext());

        this.callback = callback;
    }

    public void show() {
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_rating);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView btnClose = dialog.findViewById(R.id.dialog_close);
        btnClose.setOnClickListener(view -> dialog.dismiss());
        SmileyRating smileyRating = dialog.findViewById(R.id.smile_rating);
        smileyRating.setRating(SmileyRating.Type.GREAT, true);
        smileyRating.setRating(4, true);
        smileyRating.setSmileySelectedListener(type -> {
            store.put(DIALOG_RATING, Integer.toString(type.getRating()));
            callback.accept(type.getRating());
            dialog.dismiss();
        });
        dialog.show();
    }


    public boolean isRated() {
        return store.has(DIALOG_RATING);

    }

    public int value() {
        return Integer.parseInt(store.get(DIALOG_RATING));
    }

    public void reset() {
        store.remove(DIALOG_RATING);
    }
}
