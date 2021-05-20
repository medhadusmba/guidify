package com.softmq.guide.app.common.data.json;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import okio.BufferedSource;
import okio.Okio;

public class AssetJson {
    private final Context context;
    private final String asset;

    public AssetJson(Context context, String asset) {
        this.context = context;
        this.asset = asset;
    }

    public JSONObject asJson() throws IOException, JSONException {
        InputStream input = context.getAssets().open(asset);
        BufferedSource source = Okio.buffer(Okio.source(input));

        return new JSONObject(source.readByteString().string(StandardCharsets.UTF_8));
    }
}
