package com.softmq.guide.app.main;

public class MainActivityConfig {
    private final String more;

    public MainActivityConfig(String more) {
        this.more = more;
    }

    public MainActivityConfig() {
        this("5700313618786177705");
    }

    public String more() {
        return more;
    }
}
