package com.bitgrind.meetup.api.options;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by mrenouf on 1/12/15.
 */
public class ParameterMap extends AbstractMap<String, String> {
    private final Set<Map.Entry<String, String>> entrySet = new HashSet<>();

    public void set(String name, String value) {
        put(name, value);
    }
    public void set(String name, String first, String... remainder) {
        put(name, csv(first, remainder));
    }

    public void set(String name, int value) {
        put(name, Integer.toString(value));
    }

    public void set(String name, int first, int... remainder) {
        put(name, csv(first, remainder));
    }

    public void set(String name, long value) {
        put(name, Long.toString(value));
    }

    public void set(String name, long first, long... remainder) {
        put(name, csv(first, remainder));
    }

    public void set(String name, float value) {
        put(name, Float.toString(value));
    }

    public void set(String name, float first, float... remainder) {
        put(name, csv(first, remainder));
    }

    public void set(String name, double value) {
        put(name, Double.toString(value));
    }

    public void set(String name, double first, double... remainder) {
        put(name, csv(first, remainder));
    }

    public void set(String name, char value) {
        put(name, Character.toString(value));
    }

    public void set(String name, char first, char... remainder) {
        put(name, csv(first, remainder));
    }

    public void set(String name, boolean value) {
        put(name, Boolean.toString(value));
    }

    public void set(String name, boolean first, boolean... remainder) {
        put(name, csv(first, remainder));
    }

    public void set(String name, short value) {
        put(name, Short.toString(value));
    }

    public void set(String name, short first, short... remainder) {
        put(name, csv(first, remainder));
    }

    public void set(String name, Object value) {
        put(name, Objects.toString(value));
    }

    public void set(String name, Object first, Object... remainder) {
        put(name, csv(first, remainder));
    }

    static String csv(Object first, Object... more) {
        StringBuilder sb = new StringBuilder(Objects.toString(first));
        for (Object obj : more) {
            sb.append(',').append(Objects.toString(obj));
        }
        return sb.toString();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return entrySet;
    }

    @Override
    public String put(String key, String value) {
        String existingValue = get(key);
        if (existingValue == null || !value.equals(existingValue)) {
            entrySet.add(new SimpleImmutableEntry<String, String>(key, value));
        }
        return existingValue;
    }
}
