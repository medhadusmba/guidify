package com.softmq.guide.app.common.ads.facebook.natives;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdLayout;
import com.softmq.guide.R;
import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java.util.ArrayList;
import java.util.List;

import java9.util.concurrent.CompletableFuture;

public class FacebookNativeAd implements NativeAd {
    private final com.facebook.ads.NativeAd nativeAd;
    private final Context context;

    public FacebookNativeAd(Context context, com.facebook.ads.NativeAd nativeAd) {
        this.nativeAd = nativeAd;
        this.context = context;
    }

    @NonNull
    private View asView() {
        nativeAd.unregisterView();
        NativeAdLayout nativeAdLayout = new NativeAdLayout(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout nativeAdView = (LinearLayout) inflater.inflate(R.layout.ads_native_facebook, nativeAdLayout, false);
        nativeAdLayout.addView(nativeAdView);
        LinearLayout adChoices = nativeAdView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(context, nativeAd, nativeAdLayout);
        if (adChoices != null) {
            adChoices.removeAllViews();
            adChoices.addView(adOptionsView, 0);
        }

        MediaView nativeAdIcon = nativeAdView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = nativeAdView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = nativeAdView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = nativeAdView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = nativeAdView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = nativeAdView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = nativeAdView.findViewById(R.id.native_ad_call_to_action);

        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        nativeAd.registerViewForInteraction(
                nativeAdView,
                nativeAdMedia,
                nativeAdIcon,
                clickableViews);

        return nativeAdLayout;
    }

    public void close() {
        nativeAd.destroy();
    }

    @Override
    public CompletableFuture<Void> show(AdPlacement placement) {
        placement.show(asView());
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> showNow(AdPlacement placement) {
        return show(placement);
    }
}
