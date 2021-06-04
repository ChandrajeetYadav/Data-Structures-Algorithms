package com.sde.chandu.string;

public class DistinctStringPermutation {
    public static void main(String[] args) {
        String s = "ABB";
        System.out.println("Distiinct permutations of " + s + " :");
        distinctPermutation(s);
    }

    // Time complexity : O(n * n! )
    // Space complexity : O(1)
    private static void distinctPermutation(String s) {
        if (s == null)
            return;
        distinctPermutation(s, "");
    }

    private static void distinctPermutation(String s, String ans) {
        if (s.length() == 0) {
            System.out.print(ans + " ");
            return;
        }
        boolean[] alphabet = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            String ros = s.substring(0, i) + s.substring(i + 1);
            if (!alphabet[ch - 'A'])
                distinctPermutation(ros, ans + ch);
            alphabet[ch - 'A'] = true;
        }
    }
}
