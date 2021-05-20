package com.softmq.guide.app.common.data;

public interface KeyValueStore<T, R> {
    R get(T key);

    void put(T key, R value);

    boolean has(T key);

    void remove(T key);
}
