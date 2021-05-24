package com.sde.chandu.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnagramOfString {
    public static void main(String[] args) {
        String s1 = "bcadeh";
        String s2 = "hea";

        String s3 = "listen";
        String s4 = "silent";

        System.out.println("Are s1 and s2 anagram, sorting: " + areAnagramSorting(s1, s2));
        System.out.println("Are s3 and s4 anagram, sorting: " + areAnagramSorting(s3, s4));

        System.out.println();
        System.out.println("Are s1 and s2 anagram, array: " + areAnagramUsingArray(s1, s2));
        System.out.println("Are s3 and s4 anagram, array: " + areAnagramUsingArray(s3, s4));

        System.out.println();
        System.out.println("Are s1 and s2 anagram, array efficient: " + areAnagramUsingArrayEfficient(s1, s2));
        System.out.println("Are s3 and s4 anagram, array efficient: " + areAnagramUsingArrayEfficient(s3, s4));

        System.out.println();
        System.out.println("Are s1 and s2 anagram, hashing: " + areAnagramUsingHashing(s1, s2));
        System.out.println("Are s3 and s4 anagram, hashing: " + areAnagramUsingHashing(s3, s4));
    }

    // Time complexity : O(n)
    // Space complexity : O(26)
    private static boolean areAnagramUsingArray(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length())
            return false;
        final int SIZE = 26;
        int[] count1 = new int[SIZE];
        int[] count2 = new int[SIZE];
        for (int i = 0; i < s1.length(); i++) {
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < SIZE; i++) {
            if (count1[i] != count2[i])
                return false;
        }
        return true;
    }

    // Time complexity : O(n)
    // Space complexity : O(26)
    private static boolean areAnagramUsingArrayEfficient(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length())
            return false;
        final int SIZE = 26;
        int[] count = new int[SIZE];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < SIZE; i++) {
            if (count[i] != 0)
                return false;
        }
        return true;
    }

    // Time complexity : O(n)
    // Space complexity : O(26)
    private static boolean areAnagramUsingHashing(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length())
            return false;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0) + 1);
            map.put(s2.charAt(i), map.getOrDefault(s2.charAt(i), 0) - 1);
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0)
                return false;
        }
        return true;
    }

    // Time complexity : O(n log n)
    // Space complexity : O(1)
    private static boolean areAnagramSorting(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length())
            return false;
        s1 = sort(s1);
        s2 = sort(s2);

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                return false;
        }
        return true;
    }

    private static String sort(String s1) {
        char[] ch = s1.toCharArray();
        Arrays.sort(ch);
        return new String(ch);
    }
}
