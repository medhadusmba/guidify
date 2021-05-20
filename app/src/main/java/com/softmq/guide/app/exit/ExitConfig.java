package com.softmq.guide.app.exit;

public class ExitConfig {
    private final Type type;
    private final LoaderConfig loader;

    public ExitConfig() {
        type = new TextConfig();
        loader = new LoaderConfig();
    }

    public ExitConfig(Type type, LoaderConfig loader) {
        this.type = type;
        this.loader = loader;
    }

    public Type type() {
        return type;
    }

    public LoaderConfig loader() {
        return loader;
    }

    public interface Type {

    }

    public static class TextConfig implements Type {
        private final String content;

        public TextConfig(String content) {
            this.content = content;
        }

        public TextConfig() {
            content = "Every exit is an entry somewhere else - Tom Stoppard";
        }

        public String content() {
            return content;
        }
    }

    public static class BannerConfig implements Type {
        private final String image;
        private final String link;

        public BannerConfig(String image, String link) {

            this.image = image;
            this.link = link;
        }

        public String image() {
            return image;
        }

        public String link() {
            return link;
        }
    }

    public static class NativeAdConfig implements Type {
        public NativeAdConfig() {
        }


    }

    public static class LoaderConfig {
        private final boolean enabled;
        private final long duration;

        public LoaderConfig(boolean enabled, long duration) {
            this.enabled = enabled;
            this.duration = duration;
        }

        public LoaderConfig() {
            this(false, 0);
        }

        public boolean isEnabled() {
            return enabled;
        }

        public long duration() {
            return duration;
        }
    }
}
