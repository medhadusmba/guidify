package com.softmq.guide.app.config;

import com.softmq.guide.app.ActivitiesConfig;
import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.exit.ExitConfig;
import com.softmq.guide.app.items.ItemActivityConfig;
import com.softmq.guide.app.items.ListActivityConfig;
import com.softmq.guide.app.main.MainActivityConfig;
import com.softmq.guide.app.rate.RatingConfig;
import com.softmq.guide.app.splashscreen.SplashActivityConfig;
import com.softmq.guide.app.start.StartActivityConfig;
import com.softmq.guide.app.update.UpdateConfig;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import java9.util.concurrent.CompletableFuture;

public class JsonConfig implements AppConfig {
    private final JSONObject origin;
    private AppConfig config;


    public JsonConfig(JSONObject origin) {
        this.origin = origin;
        this.config = new DefaultConfig();
    }

    private void readSync() throws JSONException {

        AdsConfig adsConfig = getAdsConfig();
        UpdateConfig updateConfig = getUpdateConfig();
        ActivitiesConfig activitiesConfig = getActivitiesConfig();
        RatingConfig ratingConfig = getRatingConfig();
        PopupConfig advertisingConfig = getPopupConfig();
        ExitConfig exitConfig = getExitConfig();
        config = new DefaultConfig(adsConfig, ratingConfig, advertisingConfig, activitiesConfig, exitConfig, updateConfig);
    }

    @NotNull
    private ExitConfig getExitConfig() throws JSONException {
        JSONObject exit = origin.getJSONObject("exit");
        String exitType = exit.getString("type");
        JSONObject exitLoader = exit.getJSONObject("loader");
        boolean exitLoaderEnabled = exitLoader.getBoolean("enabled");
        long exitLoaderDuration = exitLoader.getLong("duration");
        ExitConfig.LoaderConfig loaderConfig = new ExitConfig.LoaderConfig(exitLoaderEnabled, exitLoaderDuration);
        ExitConfig exitConfig = new ExitConfig();
        if (exitType.equalsIgnoreCase("banner")) {
            String exitImage = exit.getString("image");
            String exitLink = exit.getString("link");
            exitConfig = new ExitConfig(new ExitConfig.BannerConfig(exitImage, exitLink), loaderConfig);
        } else if (exitType.equalsIgnoreCase("native_ad")) {
            exitConfig = new ExitConfig(new ExitConfig.NativeAdConfig(), loaderConfig);
        } else if (exitType.equalsIgnoreCase("text")) {
            String exitContent = exit.getString("content");
            exitConfig = new ExitConfig(new ExitConfig.TextConfig(exitContent), loaderConfig);
        }
        return exitConfig;
    }

    @NotNull
    private PopupConfig getPopupConfig() throws JSONException {
        JSONObject popup = origin.getJSONObject("popup");
        boolean popupEnabled = popup.getBoolean("enabled");
        String popupImage = popup.getString("image");
        String popupTitle = popup.getString("title");
        String popupDescription = popup.getString("description");
        String popupLink = popup.getString("link");

        return new PopupConfig(popupEnabled, popupImage, popupTitle, popupDescription, popupLink);
    }

    @NotNull
    private RatingConfig getRatingConfig() throws JSONException {
        JSONObject rating = origin.getJSONObject("rating");
        boolean ratingEnabled = rating.getBoolean("enabled");
        return new RatingConfig(ratingEnabled);
    }

    @NotNull
    private UpdateConfig getUpdateConfig() throws JSONException {
        JSONObject update = origin.getJSONObject("update");
        boolean updateState = update.getBoolean("state");
        String updateTitle = update.getString("title");
        String updateMsg = update.getString("message");
        String updateAction = update.getString("action");
        boolean updateCloseable = update.getBoolean("closeable");
        return new UpdateConfig(updateState, updateTitle, updateMsg, updateAction, updateCloseable);
    }

    @NotNull
    private ActivitiesConfig getActivitiesConfig() throws JSONException {
        JSONObject activities = origin.getJSONObject("activities");
        JSONObject splashActivity = activities.getJSONObject("splash");
        long splashActivityDuration = splashActivity.getLong("duration");
        SplashActivityConfig splashActivityConfig = new SplashActivityConfig(splashActivityDuration);

        JSONObject startActivity = activities.getJSONObject("start");
        boolean startActivityEnabled = startActivity.getBoolean("enabled");
        JSONObject startActivityLoader = startActivity.getJSONObject("loader");
        boolean startActivityLoaderEnabled = startActivityLoader.getBoolean("enabled");
        int startActivityLoaderDuration = startActivityLoader.getInt("duration");
        StartActivityConfig startActivityConfig = new StartActivityConfig(startActivityEnabled, new StartActivityConfig.LoaderConfig(startActivityLoaderEnabled, startActivityLoaderDuration));

        JSONObject mainActivity = activities.getJSONObject("main");
        String mainActivityMore = mainActivity.getString("more");
        MainActivityConfig mainActivityConfig = new MainActivityConfig(mainActivityMore);

        JSONObject listActivity = activities.getJSONObject("list");
        JSONObject listActivityItem = listActivity.getJSONObject("item");
        String listActivityItemType = listActivityItem.getString("type");
        JSONObject listActivityItemLoader = listActivityItem.getJSONObject("loader");
        boolean listActivityItemLoaderEnable = listActivityItemLoader.getBoolean("enabled");
        int listActivityItemLoaderDuration = listActivityItemLoader.getInt("duration");
        ListActivityConfig listActivityConfig = new ListActivityConfig(
                new ListActivityConfig.ItemConfig(listActivityItemType,
                        new ListActivityConfig.ItemConfig.Loader(listActivityItemLoaderEnable, listActivityItemLoaderDuration)));
        JSONObject itemActivity = activities.getJSONObject("item");
        boolean itemActivityNativeAd = itemActivity.getBoolean("native_ad");
        boolean itemActivityBannerAd = itemActivity.getBoolean("banner_ad");
        boolean itemActivityInterstitialAd = itemActivity.getBoolean("interstitial_ad");
        ItemActivityConfig itemActivityConfig = new ItemActivityConfig(itemActivityNativeAd, itemActivityBannerAd, itemActivityInterstitialAd);

        return new ActivitiesConfig(splashActivityConfig,
                startActivityConfig,
                mainActivityConfig,
                listActivityConfig,
                itemActivityConfig);
    }

    @NotNull
    private AdsConfig getAdsConfig() throws JSONException {
        JSONObject ads;
        ads = origin.getJSONObject("ads");
        boolean adsEnabled = ads.getBoolean("enabled");
        String bannerAdsMode = ads.getString("mode_ads_banner");
        String interstitialAdsMode = ads.getString("mode_ads_interstitial");
        String nativeAdsMode = ads.getString("mode_ads_native");
        String rewardedAdsMode = ads.getString("mode_ads_rewarded");
        String mediumRectMode = ads.getString("mode_ads_mediumrect");

        JSONObject fb = ads.getJSONObject("facebook");
        String fbBanner = fb.getString("facebook_banner_id");
        String fbInterstitial = fb.getString("facebook_interstitial_id");
        String fbNative = fb.getString("facebook_native_id");
        String fbMediumRectId = fb.getString("facebook_mediumrect_id");

        JSONObject admob = ads.getJSONObject("admob");
        String GoogleUnitId = admob.getString("admob_unit_id");
        String GoogleBanner = admob.getString("admob_banner_id");
        String googleMediumRectId = admob.getString("admob_mediumrect_id");
        JSONArray JsonGoogleInterstitial = admob.getJSONArray("admob_interstitial_id");
        List<String> GoogleInterstitial = new ArrayList<>();
        for (int i = 0; i < JsonGoogleInterstitial.length(); i++) {
            GoogleInterstitial.add(JsonGoogleInterstitial.getString(i));
        }
        String GoogleNative = admob.getString("admob_native_id");
        String GoogleVideo = admob.getString("admob_rewarded_id");

        JSONObject appodeal = ads.getJSONObject("appodeal");
        String AppKeyAppodeal = appodeal.getString("appodeal_app_key");

        JSONObject unity = ads.getJSONObject("unity");
        String UnityGameId = unity.getString("unity_app_id");
        String UnityBanner = unity.getString("unity_banner_id");
        String UnityInterstitial = unity.getString("unity_interstitial_id");
        String UnityVideo = unity.getString("unity_rewarded_id");

        JSONObject adColony = ads.getJSONObject("adcolony");
        String AdColonyAppId = adColony.getString("adcolony_app_id");
        String adcolonyInterstitialId = adColony.getString("adcolony_interstitial_id");
        String adcolonyBannerId = adColony.getString("adcolony_banner_id");
        String adcolonyRewardedId = adColony.getString("adcolony_rewarded_id");
        return new AdsConfig(adsEnabled, GoogleUnitId, GoogleBanner, GoogleInterstitial,
                GoogleVideo, googleMediumRectId, GoogleNative, fbBanner, fbInterstitial, fbNative, fbMediumRectId, UnityGameId, UnityBanner,
                UnityInterstitial, UnityVideo, AppKeyAppodeal, AdColonyAppId, adcolonyBannerId, adcolonyInterstitialId, adcolonyRewardedId,
                bannerAdsMode, interstitialAdsMode, rewardedAdsMode, nativeAdsMode, mediumRectMode);
    }

    @Override
    public PopupConfig popup() {
        return config.popup();
    }

    @Override
    public CompletableFuture<Void> read() {
        try {
            readSync();
            return CompletableFuture.completedFuture(null);
        } catch (JSONException e) {
            e.printStackTrace();
            return CompletableFuture.failedFuture(e);
        }
    }

    public AdsConfig ads() {
        return config.ads();
    }

    public ActivitiesConfig activities() {
        return config.activities();
    }

    public RatingConfig rating() {
        return config.rating();
    }

    public ExitConfig exit() {
        return config.exit();
    }

    public UpdateConfig update() {
        return config.update();
    }


}
