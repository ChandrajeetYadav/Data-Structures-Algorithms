package com.sde.chandu.dynamicprogramming;

public class PrintLongestCommonSubsequence {
    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        System.out.println("Recursive approach, LCS is: " + printLCSRecursive(s1, s2));
        System.out.println("Memoization approach, LCS is: " + printLCSMemoization(s1, s2));
        System.out.println("Bottom-up approach, LCS is: " + printLCSBottomUp(s1, s2));
    }

    // Time complexity: O(2 ^ max(m + n))
    // Space complexity: O(1), ignoring recursion stack
    private static String printLCSRecursive(String s1, String s2) {
        if (s1 == null || s2 == null)
            return "";
        return printLCSRecursive(s1, s2, s1.length(), s2.length());
    }

    private static String printLCSRecursive(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0)
            return "";
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            return printLCSRecursive(s1, s2, m - 1, n - 1) + s1.charAt(m - 1);
        else {
            String a = printLCSRecursive(s1, s2, m - 1, n);
            String b = printLCSRecursive(s1, s2, m, n - 1);
            return a.length() > b.length() ? a : b;
        }
    }

    // Time complexity: O(m * n))
    // Space complexity: O(m * n)
    private static String printLCSMemoization(String s1, String s2) {
        if (s1 == null || s2 == null)
            return "";
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++)
                dp[i][j] = -1;
        }
        printLCSMemoization(s1, s2, s1.length(), s2.length(), dp);
        return getLCSFromDPTable(s1, s2, dp);
    }

    private static int printLCSMemoization(String s1, String s2, int m, int n, int[][] dp) {
        if (m == 0 || n == 0)
            return 0;
        if (dp[m][n] != -1)
            return dp[m][n];
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            dp[m][n] = 1 + printLCSMemoization(s1, s2, m - 1, n - 1, dp);
        } else {
            dp[m][n] = Math.max(printLCSMemoization(s1, s2, m - 1, n, dp), printLCSMemoization(s1, s2, m, n - 1, dp));
        }
        return dp[m][n];
    }

    // Time complexity: O(m * n))
    // Space complexity: O(m * n)
    private static String printLCSBottomUp(String s1, String s2) {
        if (s1 == null || s2 == null)
            return "";
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return getLCSFromDPTable(s1, s2, dp);
    }

    private static String getLCSFromDPTable(String s1, String s2, int[][] dp) {
        int i = s1.length(), j = s2.length(), k = dp[i][j];
        char[] lcs = new char[k];
        while (i > 0 && j > 0 && k > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs[--k] = s1.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return new String(lcs);
    }
}
