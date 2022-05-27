package com.sde.chandu.dynamicprogramming;

public class MinimumInsertionInStringToMakePalindrome {
    public static void main(String[] args) {
        String s = "abcda";
        System.out.println("Recursive approach, minimum number of insertion: " + minInsertionRecursive(s));
        System.out.println("Memoization approach, minimum number of insertion: " + minInsertionMemoization(s));
        System.out.println("Bottom-up / Iterative approach, minimum number of insertion: " + minInsertionBottomUp(s));
    }

    // Time complexity: O(2 ^ n) , Size of recursion tree will be 2(n+n) = 2n
    // Space complexity: O(n), The depth of the recursion tree will go up to n
    private static int minInsertionRecursive(String s) {
        if (s == null)
            return 0;
        int n = s.length();
        String reversedString = new StringBuilder(s).reverse().toString();
        int lcs = lcsRecursive(s, reversedString, n, n);
        return n - lcs;
    }

    private static int lcsRecursive(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            return 1 + lcsRecursive(s1, s2, m - 1, n - 1);
        else
            return Math.max(lcsRecursive(s1, s2, m - 1, n), lcsRecursive(s1, s2, m, n - 1));
    }

    // Time complexity: O(n * n) = O(n ^ 2)
    // Space complexity: O(n * n) = O(n ^ 2)
    private static int minInsertionMemoization(String s) {
        if (s == null)
            return 0;
        int n = s.length();
        String reversedString = new StringBuilder(s).reverse().toString();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++)
                dp[i][j] = -1;
        }
        int lcs = lcsMemoization(s, reversedString, n, n, dp);
        return n - lcs;
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

    // Time complexity: O(n * n) = O(n ^ 2)
    // Space complexity: O(n * n) = O(n ^ 2)
    private static int minInsertionBottomUp(String s) {
        if (s == null)
            return 0;
        int n = s.length();
        String reversedString = new StringBuilder(s).reverse().toString();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == reversedString.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        int lcs = dp[n][n];
        return n - lcs;
    }
}
