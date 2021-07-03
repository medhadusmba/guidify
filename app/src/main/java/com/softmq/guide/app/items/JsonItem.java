package com.softmq.guide.app.items;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonItem {

    private final ItemListener callback;
    private final String title;
    private final String description;
    private final String image;
    private final String type;
    private final String url;
    private final JSONObject locker;

    public JsonItem(JSONObject item, ItemListener callback) throws JSONException {
        if (item.has("type")) {
            this.type = item.getString("type");
        } else {
            if (!item.has("description") || item.getString(Item.DESCRIPTION).isEmpty()) {

                type = Item.TYPE_ERROR;
            } else {
                type = Item.TYPE_ARTICLE;
            }
        }
        if (item.has("title")) {
            this.title = item.getString("title");
        } else {
            title = "";
        }
        if (item.has("image")) {
            this.image = item.getString("image");
        } else {
            image = "";
        }
        if (item.has("description")) {
            this.description = item.getString("description");
        } else {
            description = "";
        }
        if (item.has("locker")) {
            this.locker = item.getJSONObject("locker");
        } else {
            this.locker = null;
        }
        if (item.has("url")) {
            this.url = item.getString("url");
        } else {
            this.url = "";
        }
        this.callback = callback;
    }

    public Item asItem() {
        return new Item(title, description, image, callback, type, locker, url);
    }
}
