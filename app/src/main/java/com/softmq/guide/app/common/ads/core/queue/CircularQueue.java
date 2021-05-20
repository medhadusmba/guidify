package com.softmq.guide.app.common.ads.core.queue;

import org.apache.commons.collections4.QueueUtils;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.Optional;
import java.util.Queue;

public class CircularQueue<T> {
    private static final int DEFAULT_SIZE = 32;
    private final int capacity;
    Queue<T> queue;

    public CircularQueue() {
        this(DEFAULT_SIZE);
    }

    public CircularQueue(int capacity) {
        this.queue = QueueUtils.synchronizedQueue(new CircularFifoQueue<>(capacity));
        this.capacity = capacity;
    }

    public void add(T ad) {
        this.queue.add(ad);
    }

    public Optional<T> poll() {
        return Optional.ofNullable(this.queue.poll());
    }


    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public Optional<T> peek() {
        return Optional.ofNullable(queue.peek());
    }

    public int size() {
        return queue.size();
    }

    public Boolean isAtFullCapacity() {
        return queue.size() == capacity;
    }

}
