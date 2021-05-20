package com.softmq.guide.app.common.ads.core.queue;

import com.softmq.guide.app.common.core.Listener;
import com.softmq.guide.app.common.core.NoListener;

import java.util.Optional;

public class ListenableCircularQueue<T> {
    private final CircularQueue<T> origin;
    private Listener<T> onPolledListener;
    private Runnable onEmptyListener;
    private Listener<T> onAddedListener;

    public ListenableCircularQueue() {
        this.origin = new CircularQueue<T>();
        onPolledListener = new NoListener<>();
        onEmptyListener = () -> {
        };
        onAddedListener = new NoListener<>();
    }

    public boolean isEmpty() {
        return origin.isEmpty();
    }

    public Optional<T> peek() {
        return origin.peek();
    }

    public Optional<T> poll() {
        Optional<T> result = origin.poll();
        result.ifPresent(element -> onPolledListener.accept(element));
        if (isEmpty()) {
            onEmptyListener.run();
        }
        return result;
    }

    public void add(T current) {
        peek().ifPresent(previous -> onPolledListener.accept(previous));
        origin.add(current);
        onAddedListener.accept(current);
    }


    public void setOnPolledListener(Listener<T> onPolledListener) {
        this.onPolledListener = onPolledListener;
    }

    public void setOnEmptyListener(Runnable onEmptyListener) {
        this.onEmptyListener = onEmptyListener;
        if (isEmpty()) {
            onEmptyListener.run();
        }
    }

    public void setOnAddedListener(Listener<T> onAddedListener) {
        this.onAddedListener = onAddedListener;
    }
}
