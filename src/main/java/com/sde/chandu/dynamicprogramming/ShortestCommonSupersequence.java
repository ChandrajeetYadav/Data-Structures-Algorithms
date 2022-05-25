package com.sde.chandu.dynamicprogramming;

public class ShortestCommonSupersequence {
    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = "xycd";
        System.out.println("Recursive approach, shortest common supersequence: " + shortestCommonSupersequenceRecursive(s1, s2));
        System.out.println("Memoization approach, shortest common supersequence: " + shortestCommonSupersequenceMemoization(s1, s2));
        System.out.println("Bottom-up / Iterative approach, shortest common supersequence: " + shortestCommonSupersequenceBottomUp(s1, s2));
    }

    // Time complexity: O(2 ^ max(m + n))
    // Space complexity: O(1), ignoring recursion stack
    private static int shortestCommonSupersequenceRecursive(String s1, String s2) {
        if (s1 == null || s2 == null)
            return 0;
        int m = s1.length(), n = s2.length();
        int lcs = lcsRecursive(s1, s2, m, n);
        return m + n - lcs;
    }

    private static int lcsRecursive(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            return 1 + lcsRecursive(s1, s2, m - 1, n - 1);
        else
            return Math.max(lcsRecursive(s1, s2, m - 1, n), lcsRecursive(s1, s2, m, n - 1));
    }

    // Time complexity: O(m * n)
    // Space complexity: O(m * n)
    private static int shortestCommonSupersequenceMemoization(String s1, String s2) {
        if (s1 == null || s2 == null)
            return 0;
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++)
                dp[i][j] = -1;
        }
        int lcs = lcsMemoization(s1, s2, m, n, dp);
        return m + n - lcs;
    }

    private static int lcsMemoization(String s1, String s2, int m, int n, int[][] dp) {
        if (m == 0 || n == 0)
            return 0;
        if (dp[m][n] != -1)
            return dp[m][n];
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            return dp[m][n] = 1 + lcsMemoization(s1, s2, m - 1, n - 1, dp);
        else
            return dp[m][n] = Math.max(lcsMemoization(s1, s2, m - 1, n, dp), lcsMemoization(s1, s2, m, n - 1, dp));
    }

    // Time complexity: O(m * n)
    // Space complexity: O(m * n)
    private static int shortestCommonSupersequenceBottomUp(String s1, String s2) {
        if (s1 == null || s2 == null)
            return 0;
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
        return m + n - dp[m][n];
    }
}
