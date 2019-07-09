package com.intellij.util.messages;

public class Topic<T> {
    public static <T> Topic<T> create(String s, Class<T> c) {
        return new Topic<T>();
    }
}
