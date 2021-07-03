package com.softmq.guide.app.common.ads.core.natives;

import com.softmq.guide.app.common.ads.core.AsyncAdSource;
import com.softmq.guide.app.common.ads.core.InlineAdSource;

public interface NativeAdSource extends InlineAdSource {
    NativeAd nativeAd();
}
