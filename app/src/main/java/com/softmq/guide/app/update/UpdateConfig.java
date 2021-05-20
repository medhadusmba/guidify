package com.softmq.guide.app.update;

public class UpdateConfig {
    private final boolean state;
    private final String title;
    private final String message;
    private final String action;
    private final boolean closeable;

    public UpdateConfig() {
        this(false, "New Version Available", "Download Now!", "", false);


    }

    public UpdateConfig(boolean state, String title, String message, String action, boolean closeable) {
        this.state = state;
        this.title = title;
        this.message = message;
        this.action = action;
        this.closeable = closeable;
    }


    public String title() {
        return title;
    }

    public String message() {
        return message;
    }

    public String action() {
        return action;
    }

    public boolean isCloseable() {
        return closeable;
    }

    public boolean state() {
        return state;
    }
}
