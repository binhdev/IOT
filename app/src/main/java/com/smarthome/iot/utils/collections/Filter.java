package com.smarthome.iot.utils.collections;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    public static <T> List<T> filter(List<T> col, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T element: col) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }
}
