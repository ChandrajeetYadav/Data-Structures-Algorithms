package com.sde.chandu.dynamicprogramming;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String s1 = "ABCDGH";
        String s2 = "AEDFHR";
        System.out.println("Recursive approach, length of LCS: " + lcsRecursive(s1, s2));
        System.out.println("Memoization approach, length of LCS: " + lcsMemoization(s1, s2));
        System.out.println("Iterative / Bottom up approach, length of LCS: " + lcsBottomUp(s1, s2));
        System.out.println("Iterative / Bottom up space optimized approach, length of LCS: " + lcsBottomUpOptimized(s1, s2));
    }

    // Time complexity: O(2 ^ max(m, n))
    // Space complexity: O(1), ignoring recursion stack
    private static int lcsRecursive(String s1, String s2) {
        if (s1 == null || s2 == null)
            return 0;
        char[] x = s1.toCharArray();
        char[] y = s2.toCharArray();
        return lcsRecursive(x, y, s1.length(), s2.length());
    }

    private static int lcsRecursive(char[] x, char[] y, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (x[m - 1] == y[n - 1])
            return 1 + lcsRecursive(x, y, m - 1, n - 1);
        else
            return Math.max(lcsRecursive(x, y, m, n - 1), lcsRecursive(x, y, m - 1, n));
    }

    // Time complexity: O(m * n))
    // Space complexity: O(m * n)
    private static int lcsMemoization(String s1, String s2) {
        if (s1 == null || s2 == null)
            return 0;
        char[] x = s1.toCharArray();
        char[] y = s2.toCharArray();
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++)
                dp[i][j] = -1;
        }
        return lcsMemoization(x, y, s1.length(), s2.length(), dp);
    }

    private static int lcsMemoization(char[] x, char[] y, int m, int n, int[][] dp) {
        if (m == 0 || n == 0)
            return 0;
        if (dp[m][n] != -1)
            return dp[m][n];
        if (x[m - 1] == y[n - 1])
            dp[m][n] = 1 + lcsMemoization(x, y, m - 1, n - 1, dp);
        else
            dp[m][n] = Math.max(lcsMemoization(x, y, m, n - 1, dp), lcsMemoization(x, y, m - 1, n, dp));
        return dp[m][n];
    }

    // Time complexity: O(m * n))
    // Space complexity: O(m * n)
    private static int lcsBottomUp(String s1, String s2) {
        if (s1 == null || s2 == null)
            return 0;
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[m][n];
    }

    // Time complexity: O(m * n))
    // Space complexity: O(n)
    private static int lcsBottomUpOptimized(String s1, String s2) {
        if (s1 == null || s2 == null)
            return 0;
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[2][n + 1];
        int curI = 0;
        for (int i = 1; i <= m; i++) {
            curI = i % 2;
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[curI][j] = 1 + dp[1 - curI][j - 1];
                else
                    dp[curI][j] = Math.max(dp[1 - curI][j], dp[curI][j - 1]);
            }
        }
        return dp[curI][n];
    }
}
