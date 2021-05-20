package com.softmq.guide.app.items;


public class ListActivityConfig {
    private final ItemConfig item;

    public ListActivityConfig() {
        item = new ItemConfig();
    }

    public ListActivityConfig(ItemConfig item) {
        this.item = item;
    }

    public ItemConfig item() {
        return item;
    }

    public static class ItemConfig {

        private final String type;
        private final Loader loader;

        public ItemConfig(String type, Loader loader) {

            this.type = type;
            this.loader = loader;
        }

        public ItemConfig() {
            type = "card";
            loader = new Loader();
        }

        public String type() {
            return type;
        }

        public Loader loader() {
            return loader;
        }

        public static class Loader {
            private final boolean enabled;
            private final int duration;

            public Loader(boolean enabled, int duration) {
                this.enabled = enabled;
                this.duration = duration;
            }

            public Loader() {
                enabled = false;
                duration = 0;
            }

            public boolean isEnabled() {
                return enabled;
            }

            public int duration() {
                return duration;
            }
        }
    }

}
