package com.sde.chandu.dynamicprogramming;

public class RodCutting {
    public static void main(String[] args) {
        int[] price = {1, 5, 8, 9, 10, 17, 17, 20};
        int n = 8;
        System.out.println("Recursive approach, max price after cutting rod: " + cutRodRecursive(price, n));
        System.out.println("Memoization approach, max price after cutting rod: " + cutRodMemoization(price, n));
        System.out.println("Iterative approach, max price after cutting rod: " + cutRodIterative(price, n));
    }

    // Time complexity : O(2^n)
    // Space complexity : O(1), ignoring recursion stack
    private static int cutRodRecursive(int[] price, int n) {
        return cutRodRecursive(price, n, n);
    }

    private static int cutRodRecursive(int[] price, int len, int n) {
        if (n == 0 || len == 0)
            return 0;
        if (n <= len)
            return Math.max(price[n - 1] + cutRodRecursive(price, len - n, n),
                    cutRodRecursive(price, len, n - 1));
        else
            return cutRodRecursive(price, len, n - 1);
    }

    // Time complexity : O(n * n)
    // Space complexity : O(n * n)
    private static int cutRodMemoization(int[] price, int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++)
                dp[i][j] = -1;
        }
        return cutRodMemoization(price, n, n, dp);
    }

    private static int cutRodMemoization(int[] price, int len, int n, int[][] dp) {
        if (n == 0 || len == 0)
            return 0;
        if (dp[n][len] != -1)
            return dp[n][len];
        if (n <= len)
            dp[n][len] = Math.max(price[n - 1] + cutRodMemoization(price, len - n, n, dp),
                    cutRodMemoization(price, len, n - 1, dp));
        else
            dp[n][len] = cutRodMemoization(price, len, n - 1, dp);
        return dp[n][len];
    }

    // Time complexity : O(n * n)
    // Space complexity : O(n * n)
    private static int cutRodIterative(int[] price, int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i <= j)
                    dp[i][j] = Math.max(price[i - 1] + dp[i][j - i], dp[i - 1][j]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][n];
    }
}
