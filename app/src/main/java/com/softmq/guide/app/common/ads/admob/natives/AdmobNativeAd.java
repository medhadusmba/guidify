package com.softmq.guide.app.common.ads.admob.natives;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.nativead.NativeAdView;
import com.softmq.guide.R;
import com.softmq.guide.app.common.ads.core.natives.NativeAd;
import com.softmq.guide.app.common.ads.core.placements.AdPlacement;

import java9.util.concurrent.CompletableFuture;

public class AdmobNativeAd implements NativeAd {
    private final Context context;
    private final com.google.android.gms.ads.nativead.NativeAd nativeAd;

    public AdmobNativeAd(Context context, com.google.android.gms.ads.nativead.NativeAd nativeAd) {
        this.context = context;
        this.nativeAd = nativeAd;
    }

    @NonNull

    private View asView() {
        NativeAdView nativeAdView = (NativeAdView) View.inflate(context, R.layout.ads_native_admob, null);

        nativeAdView.setMediaView(nativeAdView.findViewById(R.id.ad_media));
        nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.ad_headline));
        nativeAdView.setBodyView(nativeAdView.findViewById(R.id.ad_body));
        nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.ad_call_to_action));
        nativeAdView.setIconView(nativeAdView.findViewById(R.id.ad_icon));
        nativeAdView.setPriceView(nativeAdView.findViewById(R.id.ad_price));
        nativeAdView.setStarRatingView(nativeAdView.findViewById(R.id.ad_rating));
        nativeAdView.setStoreView(nativeAdView.findViewById(R.id.ad_store));
        nativeAdView.setAdvertiserView(nativeAdView.findViewById(R.id.ad_advertiser));
        nativeAdView.getMediaView().setMediaContent(nativeAd.getMediaContent());
        ((TextView) nativeAdView.getHeadlineView()).setText(nativeAd.getHeadline());

        if (nativeAd.getBody() == null) {
            nativeAdView.getBodyView().setVisibility(View.GONE);
        } else {
            ((TextView) nativeAdView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            nativeAdView.getCallToActionView().setVisibility(View.GONE);
        } else {
            ((Button) nativeAdView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            nativeAdView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) nativeAdView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
        }

        if (nativeAd.getPrice() == null) {
            nativeAdView.getPriceView().setVisibility(View.GONE);
        } else {
            ((TextView) nativeAdView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStarRating() == null) {
            nativeAdView.getStarRatingView().setVisibility(View.GONE);
        } else {
            ((RatingBar) nativeAdView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
        }

        if (nativeAd.getStore() == null) {
            nativeAdView.getStoreView().setVisibility(View.GONE);
        } else {
            ((TextView) nativeAdView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getAdvertiser() == null) {
            nativeAdView.getAdvertiserView().setVisibility(View.GONE);
        } else {
            ((TextView) nativeAdView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
        }

        nativeAdView.setNativeAd(nativeAd);
        return nativeAdView;
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
