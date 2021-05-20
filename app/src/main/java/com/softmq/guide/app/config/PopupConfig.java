package com.softmq.guide.app.config;

public class PopupConfig {
    private final boolean enabled;
    private final String image;
    private final String title;
    private final String description;
    private final String link;

    public PopupConfig() {
        this(false, "", "", "", "");
    }

    public PopupConfig(boolean enabled, String image, String title, String description, String link) {
        this.enabled = enabled;
        this.image = image;
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String image() {
        return image;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public String link() {
        return link;
    }
}
