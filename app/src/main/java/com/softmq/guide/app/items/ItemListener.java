package com.softmq.guide.app.items;

@FunctionalInterface
public interface ItemListener {
    void accept(Item item);
}
