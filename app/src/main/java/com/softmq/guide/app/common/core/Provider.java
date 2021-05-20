package com.softmq.guide.app.common.core;

@FunctionalInterface
public interface Provider<T> {
    T get();
}
