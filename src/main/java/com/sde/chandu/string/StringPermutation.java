package com.sde.chandu.string;

public class StringPermutation {
    public static void main(String[] args) {
        String s = "ABC";
        System.out.println("Permutations of string " + s + " are:");
        permutation(s);
    }

    // Time complexity : O(n * n! )
    // Space complexity : O(1)
    private static void permutation(String s) {
        if (s == null)
            return;
        printPermutation(s, "");
    }

    private static void printPermutation(String s, String ans) {
        if (s.length() == 0) {
            System.out.print(ans + " ");
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            String ros = s.substring(0, i) + s.substring(i + 1);
            printPermutation(ros, ans + ch);
        }
    }
}
