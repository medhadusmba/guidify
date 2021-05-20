package com.softmq.guide.app.common.core;

public class NoListener<T> implements Listener<T> {
    @Override
    public void accept(T value) {

    }
}
