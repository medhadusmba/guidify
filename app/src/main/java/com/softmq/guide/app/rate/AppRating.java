package com.softmq.guide.app.rate;

import android.app.Activity;
import android.util.Log;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;
import com.softmq.guide.app.common.ui.dialogs.RatingDialog;
import com.softmq.guide.app.common.ui.internet.AppPageOnPlayStore;
import com.softmq.guide.app.config.SmartConfig;

public class AppRating {
    private final RatingDialog dialog;
    private final SmartConfig config;
    private final AppPageOnPlayStore page;

    public AppRating(Activity activity, SmartConfig config) {
        page = new AppPageOnPlayStore(activity);
        dialog = new RatingDialog(activity, (rating) -> {
            if (rating > 3) {
                // page.open();
                showReview(activity);
            }
        });
        this.config = config;
    }

    private void showReview(Activity activity) {
        ReviewManager manager = ReviewManagerFactory.create(activity.getApplicationContext());
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();
                Task<Void> flow = manager.launchReviewFlow(activity, reviewInfo);
                flow.addOnCompleteListener(flowTask -> {
                    if (task.isSuccessful()) {

                    } else {
                        Log.d("todo", task.getException().getMessage());
                    }

                });
            } else {
                Log.d("todo", task.getException().getMessage());
            }
        });

    }

    public void show() {
        if (config.rating().isEnabled() && !isRated()) {
            dialog.show();
        }

    }

    public boolean isRated() {
        return dialog.isRated();
    }

    public void reset() {
        dialog.reset();
    }

}
