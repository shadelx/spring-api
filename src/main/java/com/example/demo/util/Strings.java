package com.example.demo.util;

public interface Strings {

    static String toUpperCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.toUpperCase();
    }
}
