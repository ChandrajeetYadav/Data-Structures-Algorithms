package com.sde.chandu.string;

import java.util.Arrays;

public class LengthOfLongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String s = "geeksforgeeks";
        System.out.println("Length of longest substring without repeating characters, brute: " + longestUniqueSubstringBrute(s));

        System.out.println();
        System.out.println("Length of longest substring without repeating characters, sliding window: " + longestUniqueSubstringUsingSlidingWindowTechnique(s));

        System.out.println();
        System.out.println("Length of longest substring without repeating characters, efficient: " + longestUniqueSubstringEfficient(s));
    }

    // Time complexity : O(n^3)
    // Space complexity : O(1)
    private static int longestUniqueSubstringBrute(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int maxLength = 0;
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isDistinct(s, i, j) && maxLength < j - i + 1) {
                    maxLength = j - i + 1;
                    ans = s.substring(i, i + maxLength);
                }
            }
        }
        System.out.println("Longest substring without repeating characters: " + ans);
        return maxLength;
    }

    private static boolean isDistinct(String s, int i, int j) {
        boolean[] visited = new boolean[26];
        char ch;
        for (int k = i; k <= j; k++) {
            ch = s.charAt(k);
            if (visited[ch - 'a'])
                return false;
            visited[ch - 'a'] = true;
        }
        return true;
    }

    // Time complexity : O(n^2)
    // Space complexity : O(1)
    private static int longestUniqueSubstringUsingSlidingWindowTechnique(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int maxLength = 0;
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            boolean[] visited = new boolean[26];
            for (int j = i; j < s.length(); j++) {
                if (visited[s.charAt(j) - 'a'])
                    break;
                if (maxLength < j - i + 1) {
                    maxLength = j - i + 1;
                    ans = s.substring(i, i + maxLength);
                }
                visited[s.charAt(j) - 'a'] = true;
            }
        }
        System.out.println("Longest substring without repeating characters: " + ans);
        return maxLength;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static int longestUniqueSubstringEfficient(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int[] lastIndex = new int[26];
        Arrays.fill(lastIndex, -1);
        int i = 0, maxLength = 0;
        String ans = "";
        for (int j = 0; j < s.length(); j++) {
            i = Math.max(i, lastIndex[s.charAt(j) - 'a'] + 1);
            if (maxLength < j - i + 1) {
                maxLength = j - i + 1;
                ans = s.substring(i, i + maxLength);
            }
            lastIndex[s.charAt(j) - 'a'] = j;
        }
        System.out.println("Longest substring without repeating characters: " + ans);
        return maxLength;
    }
}
