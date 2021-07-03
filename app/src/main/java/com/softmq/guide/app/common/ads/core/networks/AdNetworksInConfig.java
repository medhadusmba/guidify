package com.softmq.guide.app.common.ads.core.networks;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.softmq.guide.app.common.ads.adcolony.AdcolonyNetwork;
import com.softmq.guide.app.common.ads.core.Ads;
import com.softmq.guide.app.common.ads.core.AdsConfig;
import com.softmq.guide.app.common.ads.core.MixedAds;
import com.softmq.guide.app.common.ads.core.NoAds;
import com.softmq.guide.app.common.ads.core.banners.BannerAdSource;
import com.softmq.guide.app.common.ads.core.interstitials.InterstitialAdSource;
import com.softmq.guide.app.common.ads.core.mediumrects.MediumRectAdSource;
import com.softmq.guide.app.common.ads.core.natives.NativeAdSource;
import com.softmq.guide.app.common.ads.core.rewarded.RewardedAdSource;
import com.softmq.guide.app.common.ads.facebook.FacebookNetwork;

import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.MultiMapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import java9.util.concurrent.CompletableFuture;

public class AdNetworksInConfig implements AdNetwork, Ads {
    public static final String BANNERS = "banners";
    public static final String INTERSTITIALS = "interstitials";
    public static final String NATIVES = "natives";
    private static final String MEDIUMRECTS = "mediumrecrts";
    private static final String REWARDEDADS = "rewardedads";
    private final Activity activity;
    private final AdsConfig config;
    private final MixedAds mixed;

    public AdNetworksInConfig(Activity activity, AdsConfig config) {
        this.activity = activity;
        this.config = config;
        this.mixed = getMixedAdsInConfig();
    }

    @NotNull
    @Override
    public CompletableFuture<Void> initialize() {
        return mixed.initialize();
    }

    @NotNull
    @Override
    public Boolean isInitialized() {
        return mixed.isInitialized();
    }

    @Override
    public @NonNull
    Ads ads() {
        return mixed.ads();
    }

    @NotNull
    private MixedAds getMixedAdsInConfig() {
        ListValuedMap<String, String> mixed = MultiMapUtils.newListValuedHashMap();
        mixed.put(config.banners(), BANNERS);
        mixed.put(config.interstitials(), INTERSTITIALS);
        mixed.put(config.natives(), NATIVES);
        mixed.put(config.mediumrects(), MEDIUMRECTS);
        mixed.put(config.rewardedAds(), REWARDEDADS);
        Map<Ads, Collection<String>> map = mixed.asMap().entrySet().stream()
                .map(entry -> {
                    Pair<Ads, Collection<String>> pair;
                    Ads ads = null;
                    try {
                        if (StringUtils.isNotBlank(entry.getKey())) {
                            ads = getNetworkAdsByName(entry);
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    } finally {
                        pair = Pair.of(Optional.ofNullable(ads).orElse(new NoAds()), entry.getValue());
                    }
                    return pair;
                }).collect(Collectors.toMap(Pair::getKey, Pair::getValue));
        return new MixedAds(getAdsByFormat(map, BANNERS), getAdsByFormat(map, INTERSTITIALS), getAdsByFormat(map, NATIVES), getAdsByFormat(map, MEDIUMRECTS), getAdsByFormat(map, REWARDEDADS));
    }

    @NotNull
    private Ads getNetworkAdsByName(Map.Entry<String, Collection<String>> entry) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
        return getNetworkAdsByNameUsingClassName(entry);
    }


    @NotNull
    private Ads getNetworkAdsByNameUsingClassName(Map.Entry<String, Collection<String>> entry) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
        return (Ads) Class.forName(activity.getPackageName() + ".common.ads." + entry.getKey() + "." + StringUtils.capitalize(entry.getKey()) + "Network")
                .getConstructor(Activity.class, AdsConfig.class)
                .newInstance(activity, config);
    }

    private Ads getAdsByFormat(Map<Ads, Collection<String>> networks, String format) {
        return networks.entrySet().stream().filter(entry -> entry.getValue().contains(format)).findFirst().map(Map.Entry::getKey).orElse(new NoAds());
    }

    @Override
    public BannerAdSource banners() {
        return mixed.banners();
    }

    @Override
    public InterstitialAdSource interstitials() {
        return mixed.interstitials();
    }

    @Override
    public NativeAdSource natives() {
        return mixed.natives();
    }

    @Override
    public MediumRectAdSource mediumrects() {
        return mixed.mediumrects();
    }

    @Override
    public RewardedAdSource rewardedAds() {
        return mixed.rewardedAds();
    }
}
