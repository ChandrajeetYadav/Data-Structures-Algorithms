package com.sde.chandu.string;

public class BinaryStringSubstringWhichStartAndEndWith1 {
    public static void main(String[] args) {
        String s1 = "1111"; // O/p: 6
        String s2 = "01101"; // O/p: 3
        System.out.println("Substring in " + s1 + " , brute: " + countSubstringBrute(s1));
        System.out.println("Substring in " + s2 + " , brute: " + countSubstringBrute(s2));
        System.out.println();

        System.out.println("Substring in " + s1 + " , efficient: " + countSubstringEfficient(s1));
        System.out.println("Substring in " + s2 + " , efficient: " + countSubstringEfficient(s2));
    }

    // Time complexity : O(n^2) , n=s.length()
    // Space complexity : O(1)
    private static int countSubstringBrute(String s) {
        if (s == null || s.length() <= 1)
            return 0;
        int count = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '1') {
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(j) == '1')
                        count++;
                }
            }
        }
        return count;
    }

    // Time complexity : O(n) , n=s.length()
    // Space complexity : O(1)
    private static int countSubstringEfficient(String s) {
        if (s == null || s.length() <= 1)
            return 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1')
                count++;
        }
        return count * (count - 1) / 2;
    }
}
