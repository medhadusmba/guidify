package com.softmq.guide.app.common.core;

@FunctionalInterface
public interface Listener<T> {
    void accept(T value);
}
