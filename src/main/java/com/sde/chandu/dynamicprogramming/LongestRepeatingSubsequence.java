package com.sde.chandu.dynamicprogramming;

public class LongestRepeatingSubsequence {
    public static void main(String[] args) {
        String s = "AABEBCDD";
        System.out.println("Recursive approach, longest repeating subsequence: " + longestRepeatingSubsequenceRecursive(s));
        System.out.println("Memoization approach, longest repeating subsequence: " + longestRepeatingSubsequenceMemoization(s));
        System.out.println("Bottom-up / Iterative approach, longest repeating subsequence: " + longestRepeatingSubsequenceBottomUp(s));
    }

    // Time complexity: O(2 ^ n)
    // Space complexity: O(n)
    private static int longestRepeatingSubsequenceRecursive(String s) {
        if (s == null)
            return 0;
        int n = s.length();
        return longestRepeatingSubsequenceRecursive(s, n, n);
    }

    private static int longestRepeatingSubsequenceRecursive(String s, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (s.charAt(m - 1) == s.charAt(n - 1) && m != n)
            return 1 + longestRepeatingSubsequenceRecursive(s, m - 1, n - 1);
        else
            return Math.max(longestRepeatingSubsequenceRecursive(s, m - 1, n),
                    longestRepeatingSubsequenceRecursive(s, m, n - 1));
    }

    // Time complexity: O(n * n) = O(n ^ 2)
    // Space complexity: O(n * n) = O(n ^ 2)
    private static int longestRepeatingSubsequenceMemoization(String s) {
        if (s == null)
            return 0;
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++)
                dp[i][j] = -1;
        }
        return longestRepeatingSubsequenceMemoization(s, n, n, dp);
    }

    private static int longestRepeatingSubsequenceMemoization(String s, int m, int n, int[][] dp) {
        if (m == 0 || n == 0)
            return 0;
        if (dp[m][n] != -1)
            return dp[m][n];
        if (s.charAt(m - 1) == s.charAt(n - 1) && m != n)
            return dp[m][n] = 1 + longestRepeatingSubsequenceMemoization(s, m - 1, n - 1, dp);
        else
            return dp[m][n] = Math.max(longestRepeatingSubsequenceMemoization(s, m - 1, n, dp),
                    longestRepeatingSubsequenceMemoization(s, m, n - 1, dp));
    }

    // Time complexity: O(n * n) = O(n ^ 2)
    // Space complexity: O(n * n) = O(n ^ 2)
    private static int longestRepeatingSubsequenceBottomUp(String s) {
        if (s == null)
            return 0;
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1) && i != j)
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[n][n];
    }
}
