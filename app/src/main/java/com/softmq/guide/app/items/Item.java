package com.softmq.guide.app.items;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.softmq.guide.app.common.core.Delayed;
import com.softmq.guide.app.common.ui.image.NetworkImage;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {
    public static final String TITLE = "title";
    public static final String IMAGE = "image";
    public static final String DESCRIPTION = "description";
    public static final String TYPE = "type";
    public static final String URL = "url";
    public static final String TYPE_WEBVIEW = "webview";
    public static final String TYPE_NATIVE_AD = "native_ad";
    public static final String TYPE_ERROR = "error";
    public static final String TYPE_ARTICLE = "article";
    private final ItemListener itemClickListener;
    private String title;
    private String description;
    private String image;
    private String type;
    private String url;
    private JSONObject locker;
    public Item(String title, String description, String image, ItemListener callback, String type, JSONObject locker, String url) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.itemClickListener = callback;
        this.type = type;
        this.url = url;
        this.locker = locker;
    }
    public Item(String title, String description, String image, ItemListener callback, String type, JSONObject locker) {
        this(title, description, image, callback, type, locker, "");

    }
    public Item(String title, String description, String image, ItemListener itemClickListener, String type) {
        this(title, description, image, itemClickListener, type, null);
    }

    public Item(String title, String description, String image, ItemListener itemClickListener) {
        this(title, description, image, itemClickListener, "");
    }

    public Item() {
        this("");

    }

    public Item(String title) {
        this(title, "");

    }

    public Item(String title, String description) {
        this(title, description, "");
    }

    public Item(String title, String description, String image) {
        this(title, description, image, (view) -> {
        });
    }


    public Item(Item item) {
        this(item.title, item.description, item.image, item.itemClickListener, item.type,item.locker ,item.url);
    }

    @BindingAdapter({"imageUrl"})
    public static void into(ImageView view, String imageUrl) {
        //TODO: maintain this code

            new Delayed(400, () -> {
                new NetworkImage(imageUrl, view).show();
            }).run();

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void click(View v) {
        itemClickListener.accept(this);
    }

    public JSONObject getLocker() {
        return locker;
    }

    public void setLocker(JSONObject locker) {
        this.locker = locker;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
