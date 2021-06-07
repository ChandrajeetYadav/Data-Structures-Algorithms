package com.sde.chandu.string;

public class NeedleInAHaystack {
    public static void main(String[] args) {
        String s = "AABAACAADAABAABA";
        String pat = "AABA";

        System.out.println("Pattern searching brute:");
        patternSearchingBrute(s, pat);
        System.out.println();

        System.out.println("Pattern searching KMP algorithm:");
        KMP_PatternSearching(s, pat);
        System.out.println();

        System.out.println("Pattern searching using indexOf method:");
        patternSearchingUsingIndexOf(s, pat);
    }

    // Time complexity : O(m * (n - m + 1)) // n=s.length(), m=pat.length()
    // Space complexity : O(1)
    private static void patternSearchingBrute(String s, String pat) {
        if (s == null || pat == null || s.length() < pat.length())
            return;
        for (int i = 0, j; i <= s.length() - pat.length(); i++) {
            for (j = 0; j < pat.length(); j++) {
                if (pat.charAt(j) != s.charAt(i + j))
                    break;
            }
            if (j == pat.length())
                System.out.println("Pattern found at index " + i);
        }
    }

    //  n=s.length(), m=pat.length()
    //  Time complexity : O(n)
    // Space complexity : O(1)
    private static void patternSearchingUsingIndexOf(String s, String pat) {
        if (s == null || pat == null || s.length() < pat.length())
            return;
        int i = 0, index;
        while (i < s.length()) {
            index = s.indexOf(pat, i);
            if (index == -1)
                break;
            System.out.println("Pattern found at index: " + index);
            i = index + 1;
        }
    }

    //  n=s.length(), m=pat.length()
    //  Time complexity : O(m + n) => O(n) as n>m
    // Space complexity : O(m)
    private static void KMP_PatternSearching(String s, String pat) {
        if (s == null || pat == null || s.length() < pat.length())
            return;
        int[] lps = computeLPS(pat);
        int i = 0, j = 0;
        while (i < s.length()) {
            if (s.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            } else {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
            if (j == pat.length()) {
                System.out.println("Pattern found at index: " + (i - j));
                j = lps[j - 1];
            }
        }
    }

    private static int[] computeLPS(String pat) {
        int[] lps = new int[pat.length()];
        lps[0] = 0;
        int len = 0, i = 1;
        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0)
                    len = lps[len - 1];
                else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }
}
