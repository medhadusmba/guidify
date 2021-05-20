package com.softmq.guide.app.common.core;

public class BiSelector<T> {
    private final T first;
    private final T second;

    public BiSelector(T first, T second) {
        this.first = first;
        this.second = second;

    }

    /**
     * @param isFirst true to choose the first one, false for the second.
     * @return
     */
    public T select(Boolean isFirst) {
        if (isFirst) {
            return first;
        }
        return second;
    }
}
