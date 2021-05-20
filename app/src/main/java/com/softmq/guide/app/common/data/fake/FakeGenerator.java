package com.softmq.guide.app.common.data.fake;

import com.softmq.guide.app.items.Item;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class FakeGenerator {
    private static final String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    public String title() {
        return loremIpsum.substring(0, 26);
    }

    public String image() {
        return "https://picsum.photos/400/400";
    }

    public String paragraph() {
        return loremIpsum;
    }

    public <T> List<T> generate(Class<T> type, int number) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<T> result = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            Item item = new Item("", "", " https://placebeard.it/640x360?" + i, (item1) -> {
            });
            List<Class> parameters = new ArrayList<>();
            for (Class parameter : type.getConstructor().getParameterTypes()) {
                parameters.add(parameter);
            }
            result.add(type.getConstructor().newInstance());
        }

        return result;
    }

}
