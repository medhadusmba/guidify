package com.softmq.guide.app.items;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.softmq.guide.app.common.core.Delayed;
import com.softmq.guide.app.common.ui.image.NetworkImage;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {
    public static final String TITLE = "title";
    public static final String IMAGE = "image";
    public static final String DESCRIPTION = "description";
    private final ItemListener itemClickListener;
    private String title;
    private String description;
    private String image;
    private String type;

    public Item(String title, String description, String image, ItemListener itemClickListener) {
        this(title, description, image, itemClickListener, "");
    }

    public Item(String title, String description, String image, ItemListener itemClickListener, String type) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.itemClickListener = itemClickListener;
        this.type = type;
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
        this(item.title, item.description, item.image, item.itemClickListener, item.type);
    }

    @BindingAdapter({"imageUrl"})
    public static void into(ImageView view, String imageUrl) {
        //TODO: maintain this code
        if (StringUtils.isNotBlank(imageUrl)
                && Objects.nonNull(view)
                && Objects.nonNull(view.getRootView())
                && Objects.nonNull(view.getRootView().getContext())) {
            new Delayed(400, () -> {
                new NetworkImage(imageUrl, view).show();
            }).run();
        }
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

}
