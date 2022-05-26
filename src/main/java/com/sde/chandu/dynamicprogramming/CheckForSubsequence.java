package com.sde.chandu.dynamicprogramming;

public class CheckForSubsequence {
    public static void main(String[] args) {
        String s1 = "AXY";
        String s2 = "ADXCPY";
        System.out.println("Non-DP approach, Is s1 subsequence of s2: " + isSubsequence(s1, s2));
        System.out.println("Recursive approach, Is s1 subsequence of s2: " + isSubsequenceRecursive(s1, s2));
        System.out.println("Memoization approach, Is s1 subsequence of s2: " + isSubsequenceMemoization(s1, s2));
        System.out.println("Bottom-up / Iterative approach, Is s1 subsequence of s2: " + isSubsequenceBottomUp(s1, s2));
    }

    // Time complexity: O(n)
    // Space complexity: O(1)
    private static boolean isSubsequence(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length())
            return false;
        int k = 0;
        int m = s1.length(), n = s2.length();
        for (int i = 0; i < n && k < m; i++) {
            if (s1.charAt(k) == s2.charAt(i))
                k++;
        }
        return k == m;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static boolean isSubsequenceRecursive(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length())
            return false;
        int m = s1.length();
        int n = s2.length();
        return isSubsequenceRecursive(s1, s2, m, n);
    }

    private static boolean isSubsequenceRecursive(String s1, String s2, int m, int n) {
        if (m == 0)
            return true;
        if (n == 0)
            return false;
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            return isSubsequenceRecursive(s1, s2, m - 1, n - 1);
        else
            return isSubsequenceRecursive(s1, s2, m, n - 1);
    }

    // Time complexity: O(m * n)
    // Space complexity: O(m * n)
    private static boolean isSubsequenceMemoization(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length())
            return false;
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++)
                dp[i][j] = -1;
        }
        int lcs = isSubsequenceMemoization(s1, s2, m, n, dp);
        return lcs == m;
    }

    private static int isSubsequenceMemoization(String s1, String s2, int m, int n, int[][] dp) {
        if (m == 0 || n == 0)
            return 0;
        if (dp[m][n] != -1)
            return dp[m][n];
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            return dp[m][n] = 1 + isSubsequenceMemoization(s1, s2, m - 1, n - 1, dp);
        else
            return dp[m][n] = Math.max(isSubsequenceMemoization(s1, s2, m - 1, n, dp),
                    isSubsequenceMemoization(s1, s2, m, n - 1, dp));
    }

    // Time complexity: O(m * n)
    // Space complexity: O(m * n)
    private static boolean isSubsequenceBottomUp(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length())
            return false;
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        int lcs = dp[m][n];
        return lcs == m;
    }
}
