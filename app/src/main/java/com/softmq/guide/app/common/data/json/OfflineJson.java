package com.softmq.guide.app.common.data.json;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class OfflineJson {
    private final Context context;
    private final String asset;
    private final String root;

    public OfflineJson(Context context, String asset, String root) {
        this.context = context;
        this.asset = asset;
        this.root = root;
    }

    public JSONObject asJson() throws IOException, JSONException {
        return new AssetJson(context, asset).asJson().getJSONObject(root);
    }
}
