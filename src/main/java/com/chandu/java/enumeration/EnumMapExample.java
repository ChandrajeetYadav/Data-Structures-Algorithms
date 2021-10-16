package com.chandu.java.enumeration;

import java.util.EnumMap;

public class EnumMapExample {
    public static void main(String[] args) {
        EnumMap<COLOR_ENUM, String> enumMap = new EnumMap<>(COLOR_ENUM.class);
        enumMap.put(COLOR_ENUM.RED, "Red Color");
        enumMap.put(COLOR_ENUM.BLACK, "Black Color");
        enumMap.put(COLOR_ENUM.GREEN, "Green Color");
        enumMap.put(COLOR_ENUM.WHITE, "White Color");
        enumMap.put(COLOR_ENUM.ORANGE, "Orange Color");

        System.out.println("EnumMap colors: " + enumMap);
        System.out.println("Key/Value mappings: " + enumMap.entrySet());
        System.out.println("Keys: " + enumMap.keySet());
        System.out.println("Values: " + enumMap.values());

        // GET method
        System.out.println("Value of RED: " + enumMap.get(COLOR_ENUM.RED));
        System.out.println("Value of GREEN: " + enumMap.get("GREEN"));

        //remove method
        String value = enumMap.remove(COLOR_ENUM.RED);
        System.out.println("Removed value: " + value);

        boolean result = enumMap.remove(COLOR_ENUM.GREEN, "Green Color");
        System.out.println("Is the entry {GREEN, Hello} removed? " + result);

        System.out.println("EnumMap colors: " + enumMap);

        // replace method
        enumMap.replace(COLOR_ENUM.BLACK, "Black replaced value");
        enumMap.replace(COLOR_ENUM.ORANGE, "Orange Color", "Orange replaced value");
        System.out.println("EnumMap colors: " + enumMap);
    }

    public enum COLOR_ENUM {
        RED, BLACK, GREEN, WHITE, ORANGE
    }
}
