package com.softmq.guide.app.common.data.json;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import java9.util.concurrent.CompletableFuture;

public class OnlineJson {
    private final Context context;
    private final String url;
    private final String root;


    public OnlineJson(Context context, String url, String root) {
        this.context = context;
        this.url = url;
        this.root = root;
    }

    public CompletableFuture<JSONObject> read() {
        CompletableFuture<JSONObject> result = new CompletableFuture<>();
        RequestQueue requests = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                response -> {
                    try {
                        result.complete(new JSONObject(response).getJSONObject(root));
                    } catch (final JSONException e) {
                        Log.e("todo", "Json parsing error: " + e.getMessage());
                        result.completeExceptionally(e);
                    } catch (Throwable t) {
                        Log.e("todo", "Unpredictable Json parsing error: " + t.getMessage());
                        result.completeExceptionally(t);
                    }
                },
                error -> {
                    Log.e("todo", "Can't download online json file: " + error.getMessage());
                    result.completeExceptionally(error);
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                return new HashMap<String, String>();
            }
        };

        request.setShouldCache(false);
        requests.add(request);
        return result;
    }
}